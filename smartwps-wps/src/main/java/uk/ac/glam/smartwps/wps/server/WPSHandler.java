package uk.ac.glam.smartwps.wps.server;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.opengis.ows.x11.ExceptionReportDocument;
import net.opengis.wps.x100.DataType;
import net.opengis.wps.x100.ExecuteResponseDocument;
import net.opengis.wps.x100.OutputDataType;
import net.opengis.wps.x100.ProcessDescriptionType;
import net.opengis.wps.x100.ProcessFailedType;

import org.n52.wps.client.ExecuteRequestBuilder;
import org.n52.wps.client.WPSClientException;
import org.n52.wps.client.WPSClientSession;
import org.xml.sax.SAXException;

import uk.ac.glam.smartwps.base.server.GeoServerREST;
import uk.ac.glam.smartwps.base.server.WPSProperties;
import uk.ac.glam.smartwps.base.shared.RESTConnectionException;
import uk.ac.glam.smartwps.data.server.ServerUtils;
import uk.ac.glam.smartwps.data.shared.util.StringUtils;
import uk.ac.glam.smartwps.wcs.server.WCSHandler;
import uk.ac.glam.smartwps.wcs.shared.WCSConnectionException;
import uk.ac.glam.smartwps.wcs.shared.WCSCoverage;
import uk.ac.glam.smartwps.wfs.server.WFSHandler;
import uk.ac.glam.smartwps.wfs.shared.WFSConnectionException;
import uk.ac.glam.smartwps.wfs.shared.WFSFeatureType;
import uk.ac.glam.smartwps.wms.shared.WMSConnectionException;
import uk.ac.glam.smartwps.wps.shared.ComplexData;
import uk.ac.glam.smartwps.wps.shared.DetailedProcessDescriptor;
import uk.ac.glam.smartwps.wps.shared.Format;
import uk.ac.glam.smartwps.wps.shared.ProcessDescriptor;
import uk.ac.glam.smartwps.wps.shared.WPSConnectionException;
import uk.ac.glam.smartwps.wps.shared.WPSData;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteException;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteRequest;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteResponse;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.wps.shared.input.LiteralProcessInput;
import uk.ac.glam.smartwps.wps.shared.input.ProcessInput;
import uk.ac.glam.smartwps.wps.shared.input.WCSProcessInput;
import uk.ac.glam.smartwps.wps.shared.input.WFSProcessInput;
import uk.ac.glam.smartwps.wps.shared.output.ComplexProcessOutput;
import uk.ac.glam.smartwps.wps.shared.output.LiteralProcessOutput;
import uk.ac.glam.smartwps.wps.shared.output.ProcessOutput;
import uk.ac.glam.smartwps.wps.shared.output.WCSProcessOutput;
import uk.ac.glam.smartwps.wps.shared.output.WFSProcessOutput;
import uk.ac.glam.wcsclient.StoredCoverage;
import uk.ac.glam.wcsclient.WCS111;

/**
 * Handles all WPS requests and responses.
 * 
 * @author Jon Britton
 */
public class WPSHandler {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	private static WPSHandler instance;
	
	private HashMap<String, ProcessDescriptionType[]> wpsProcesses;
	private WPSProperties properties;
	private GeoServerREST geoserverREST;
	
	private WPSHandler() {
		wpsProcesses = new HashMap<String, ProcessDescriptionType[]>();
		// Properties should have been initialised by now
		properties = WPSProperties.getProperties();
		geoserverREST = new GeoServerREST(
				properties.getGeoServerURL(),
				properties.getGeoserverUsername(),
				properties.getGeoserverPassword());
	}
	
	/**
	 * @return the WPSHandler singleton instance
	 */
	public static WPSHandler instance() {
		if (instance == null) {
            instance = new WPSHandler();
        }
		return instance;
	}
	
	/**
	 * Carry out a WPS GetCapabilities request.
	 * @param request the request object
	 * @return the response details
	 * @throws WPSConnectionException
	 */
	public WPSGetCapabilitiesResponse wpsGetCapabilities(WPSGetCapabilitiesRequest request) throws WPSConnectionException {
		WPSGetCapabilitiesResponse response = new WPSGetCapabilitiesResponse();

		ArrayList<ProcessDescriptor> processDescriptors = new ArrayList<ProcessDescriptor>();

		String source = request.getServiceUrl();
		LOGGER.log(Level.INFO, "In wpsGetCapabilities with url {0}", source);

		// Connect to the WPS
		WPSClientSession wpsClient = WPSClientSession.getInstance();
			try {
				wpsClient.connect(source);
				ProcessDescriptionType[] processDescriptions = wpsClient.getAllProcessDescriptions(source);
				// store descriptions so we can make DetailedProcessDesciptors later
				wpsProcesses.put(source, processDescriptions);
				// make simple ProcessDescriptors to send back to the client
				for (int i = 0; i < processDescriptions.length; i++) {
					ProcessDescriptor processDescriptor = WPSAdapter.processBriefAdapter(processDescriptions[i], request.getServiceUrl());
					processDescriptors.add(processDescriptor);
					LOGGER.log(Level.INFO, "Process ID: {0}", processDescriptor.getId());
				}
				response.setProcessDescriptors(processDescriptors);
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Error connecting to the WPS server: " + e.getMessage(), e);
				throw new WPSConnectionException("Error connecting to the WPS server: " + e.getMessage());
			}			

		return response;
	}
	
	/**
	 * Retrieve the detailed process information for the given process id.
	 * @param url the URL of the WPS
	 * @param id the process id
	 * @return the detailed process information
	 */
	public DetailedProcessDescriptor wpsGetDetailedProcessDescriptor(
			String url, String id) {
		ProcessDescriptionType[] processDescriptions = wpsProcesses.get(url);
		for (int i = 0; i < processDescriptions.length; i++) {
			if (processDescriptions[i].getIdentifier().getStringValue().equals(id)) {
				LOGGER.log(Level.INFO, "Found \"{0}\" process details", id);
				return WPSAdapter.detailedProcessDescriptionAdapter(processDescriptions[i], url);
			}
		}
		// TODO: error checking, in case of null
		return null;
	}

	/**
	 * Carry out a WPS Execute request.
	 * 
	 * @param request
	 *            the WPS request information
	 * @return the response details
	 * @throws WPSConnectionException
	 *             if communication with the WPS failed
	 * @throws WPSExecuteException
	 *             if the WPS Execute request failed
	 * @throws RESTConnectionException
	 * @throws WCSConnectionException
	 * @throws WFSConnectionException
	 * @throws IOException
	 */
	public WPSExecuteResponse wpsExecute(WPSExecuteRequest request) throws WPSConnectionException, WPSExecuteException, RESTConnectionException,
			WCSConnectionException, WFSConnectionException, IOException {
		LOGGER.info("Handling WPSExecute Request");
		WPSClientSession wpsClient = WPSClientSession.getInstance();
		WPSExecuteResponse response = new WPSExecuteResponse();

		// Connect to the WPS
		ProcessDescriptionType description = null;
		try {
			wpsClient.connect(request.getServiceUrl());
			description = wpsClient.getProcessDescription(request.getServiceUrl(), request.getProcessDescriptor().getId());
		} catch (WPSClientException e) {
			LOGGER.log(Level.SEVERE, "Error connecting to the WPS server: " + e.getMessage(), e);
			throw new WPSConnectionException("Error connecting to the WPS server: " + e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error connecting to the WPS server: " + e.getMessage(), e);
			throw new WPSConnectionException("Error connecting to the WPS server: " + e.getMessage());
		}
	
		ExecuteRequestBuilder requestBuilder = new ExecuteRequestBuilder(
				description);
	
		// INPUTS
		ArrayList<ProcessInput> inputs = request.getInputs();
        for (ProcessInput processInput : inputs) {
			if (processInput instanceof WCSProcessInput) {
				WCSProcessInput wcsInput = (WCSProcessInput) processInput;
				
				//Get existing WCS
				WCS111 wcs = WCSHandler.instance().getWCS(wcsInput.getCoverageDescription().getServiceURL());
				
				//TODO: deal with null wcs
				
				//Get bbox string & id
				String bbox = wcsInput.getBounds().getWCS111FormattedString();
				String id = wcsInput.getCoverageDescription().getIdentifier();
				
				//GetCoverage
				StoredCoverage remoteCoverage;
				try {
					remoteCoverage = WCSHandler.instance().wcsGetCoverage(wcs, id, bbox, "geotiff");
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Error retrieve WCs input data: " + e.getMessage(), e);
					throw new WCSConnectionException("Error retrieve WCs input data: " + e.getMessage());
				}
								
				// Get the KVP request for this coverage
				requestBuilder.addComplexDataReference(wcsInput.getId(),
						remoteCoverage.getCoverageHref(), null, null, null);
				LOGGER.log(Level.INFO, "WCS URI: {0}", remoteCoverage.getCoverageHref());
			} else if (processInput instanceof WFSProcessInput) {
				WFSProcessInput wfsInput = (WFSProcessInput) processInput;
				String wfsKVP = wfsInput.getKVPRequest();
				
				// TODO: handle different schemas (e.g for WFS 1.1.0)
				requestBuilder.addComplexDataReference(wfsInput.getId(),
						wfsKVP, wfsInput.getSchema(), null, null);
				LOGGER.log(Level.INFO, "WFS KVP: {0}", wfsKVP);
			} else if (processInput instanceof LiteralProcessInput) {
				String value = ((LiteralProcessInput) processInput)
						.getLiteralValue();
				requestBuilder.addLiteralData(processInput.getId(), value);
			}
		}
	
		// OUTPUTS
		List<WPSData> outputs = request.getProcessDescriptor().getProcessOutputs();
		for (WPSData outputData : outputs) {
			// Store all outputs...
			// requestBuilder.setStoreSupport(wpsData.getIdentifier());
	
			// Check formats
			// TODO: how do I know if it's vector or raster?
			// TODO: What about different schemas? Should those be checked?
			if (outputData instanceof ComplexData) {
				ComplexData complexData = (ComplexData) outputData;
				// Set default format at first
				String mimeType = complexData.getDefaultFormat().getMimeType();
				// Check if there are more suitable formats (like WFS, WCS...) which
				// would make life easier
				List<Format> formats = complexData.getSupportedFormats();
				for (Format format : formats) {	
					// WFS and WCS *should* be mutually exclusive... shouldn't they?
					if (format.getMimeType().equals(Format.APPLICATION_WFS)) {
						mimeType = Format.APPLICATION_WFS;
						break;
					} else if (format.getMimeType().equals(Format.APPLICATION_WCS)) {
						mimeType = Format.APPLICATION_WCS;
						break;
					} else if (format.getMimeType().toLowerCase().contains(Format.APPLICATION_X_ZIPPED_SHP)) {
						mimeType = Format.APPLICATION_X_ZIPPED_SHP;
						// Store
//						requestBuilder.setStoreSupport(outputData.getIdentifier());
					}
				}
				
				LOGGER.log(Level.INFO, "Using mimetype {0} for output {1}", new Object[]{mimeType, complexData.getIdentifier()});
	
				requestBuilder.setMimeTypeForOutput(mimeType, outputData
						.getIdentifier());
				
				if (mimeType.equals(Format.IMAGE_TIFF) || mimeType.equals(Format.APPLICATION_X_ZIPPED_SHP)) {
					requestBuilder.setStoreSupport(outputData.getIdentifier());
				}
			}
		}
		
		LOGGER.log(Level.INFO, "Execute Document:\n{0}", requestBuilder.getExecute());
	
		// TODO: This assumed that the process succeeded...
		Object responseObject = null;
		ExecuteResponseDocument erd = null;
		try {
			responseObject = wpsClient.execute(request.getServiceUrl(), requestBuilder.getExecute());
			if (responseObject instanceof ExceptionReportDocument) {
				// an error has occurred
				ExceptionReportDocument exceptionReport = (ExceptionReportDocument) responseObject;
				LOGGER.severe("Failed to execute WPS request, received ExceptionReport: " + exceptionReport.toString());
				throw new WPSExecuteException("Failed to execute WPS process: " + exceptionReport.getExceptionReport().toString());
			}
		} catch (WPSClientException e) {
			LOGGER.log(Level.SEVERE, "Failed to execute WPS process: " + e.getMessage(), e);
			throw new WPSExecuteException("Failed to execute WPS process: " + e.getMessage());
		}
	
		response.setProcessIdentifier(erd.getExecuteResponse().getProcess()
				.getIdentifier().getStringValue());
		response.setProcessTitle(erd.getExecuteResponse().getProcess()
				.getTitle().getStringValue());
		response.setCreationTime(erd.getExecuteResponse().getStatus()
				.getCreationTime().getTime());
		ProcessFailedType failed = erd.getExecuteResponse().getStatus()
				.getProcessFailed();
		if (failed != null) {
			LOGGER.log(Level.SEVERE, "PROCESS FAILED{0}", failed.getExceptionReport());
		}
		// erd.getExecuteResponse().getStatus().getProcessFailed().getExceptionReport();
	
		LOGGER.log(Level.INFO, "RESPONSE: {0}", erd.toString());
	
		OutputDataType[] outData = erd.getExecuteResponse().getProcessOutputs()
				.getOutputArray();
		
		for (int i = 0; i < outData.length; i++) {
			ProcessOutput processOutput = null;
			
			OutputDataType od = outData[i];
			DataType dataType = od.getData();
			String identifier = od.getIdentifier().getStringValue();
			
			// Determine whether this is a literal or complex output
			if (dataType.getLiteralData() != null) {
				// this is a literal data output
				String value = od.getData().getLiteralData().getStringValue();
				processOutput = new LiteralProcessOutput(identifier, value);
			} else if (dataType.getComplexData() != null) {
				// this is a complex data type
				processOutput = createComplexProcessOutput(od);
			}
	
			if (processOutput != null) {
				response.addProcessOutput(processOutput);
			} else {
				throw new WPSExecuteException("Error creating ProcessOutput for "	+ od.getIdentifier());
			}
		}
		return response;
	}

	private ComplexProcessOutput createComplexProcessOutput(OutputDataType outputDataType) throws RESTConnectionException, WCSConnectionException, IOException, WFSConnectionException, WPSExecuteException {
		ComplexProcessOutput processOutput = null;
		if (outputDataType.getReference() != null) {
			// This is just a reference
			String mimeType = outputDataType.getReference().getMimeType();

			if (mimeType.equalsIgnoreCase(Format.IMAGE_TIFF)) {
				// Stored image
				processOutput = new WCSProcessOutput();
				processOutput.setIdentifier(outputDataType.getIdentifier().getStringValue());
				processOutput.setTitle(outputDataType.getTitle().getStringValue());
				// processOutput.setDataUrl(od.getReference().getHref());
				processOutput.setMimeType(outputDataType.getReference().getMimeType());

				LOGGER.log(Level.FINE, "{0} is a TIFF", processOutput.getIdentifier());

				// Create random name
				Random rand = new Random();
				String coverageName = Long.toString(Math.abs(rand
						.nextLong()), 36);

				// Send to GeoServer
				WCSCoverage wcsCoverage;
				try {
					wcsCoverage = WCSHandler.instance().addCoverageToGeoServer(outputDataType.getReference()
							.getHref(), "swps", coverageName);
					((WCSProcessOutput) processOutput)
							.setWcsCoverage(wcsCoverage);
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Enexpected exception", e);
					throw new RESTConnectionException(e.getMessage());
				} catch (WMSConnectionException e) {
					LOGGER.log(Level.SEVERE, "Enexpected exception", e);
					throw new RESTConnectionException(e.getMessage());
				}

			} else if (mimeType.equalsIgnoreCase(Format.TEXT_XML)) { // Stored
				// XML
				// (probably
				// GML)
				// TODO: handle GML output
				// LOGGER.info(processOutput.getDataUrl());
				// gmlToShapefile(processOutput.getDataUrl());
			} else if (mimeType
					.equalsIgnoreCase(Format.APPLICATION_X_ZIPPED_SHP)) {
				// TODO: handle zipped shapefile output
				processOutput = new WFSProcessOutput();
				processOutput.setIdentifier(outputDataType.getIdentifier()
						.getStringValue());
				processOutput.setTitle(outputDataType.getTitle().getStringValue());
				processOutput.setMimeType(outputDataType.getReference().getMimeType());

				LOGGER.log(Level.FINE, "{0} is a zipped shapefile", processOutput.getIdentifier());

				// Create random name
				Random rand = new Random();
				String fileName = Long.toString(Math.abs(rand.nextLong()),
						36);

				// TODO: add shapefile to GeoServer
				WFSFeatureType wfsFeatureType = addShapefileToGeoServer(outputDataType
						.getReference().getHref(), "swps", fileName);
				((WFSProcessOutput) processOutput)
						.setFeatureType(wfsFeatureType);


			} else {
				LOGGER.log(Level.WARNING, "Error handling process result for {0}, Mime type {1} not supported.", 
                        new Object[]{outputDataType.getIdentifier(), mimeType});
			}
		} else {
			// Actual data
			String mimeType = outputDataType.getData().getComplexData().getMimeType();
			if (mimeType.equalsIgnoreCase(Format.TEXT_XML)) {
				// GML data stored inline?
				// LOGGER.info(processOutput.getDataUrl());
				// gmlToShapefile(processOutput.getDataUrl());
			} else if (mimeType.equalsIgnoreCase(Format.APPLICATION_WFS)) {
				// Should contain some WFS info we can use

				processOutput = new WFSProcessOutput();
				processOutput.setIdentifier(outputDataType.getIdentifier()
						.getStringValue());
				processOutput.setTitle(outputDataType.getTitle().getStringValue());
				processOutput.setMimeType(outputDataType.getData().getComplexData()
						.getMimeType());

				WFSOutputData output = null;
				try {
					output = XMLUtils.parseWFSOutput(outputDataType.getData().getComplexData().getDomNode());
				} catch (SAXException e) {
					LOGGER.log(Level.WARNING, "Failed to parse WFS output", e);
				}
				
				if (output == null || StringUtils.isNullOrEmpty(output.getFeatureTypeName()) 
						|| StringUtils.isNullOrEmpty(output.getFeatureTypeName())) {
					throw new WPSExecuteException("Error parsing process output "	+ outputDataType.getIdentifier());
				}
				
				String resourceID = output.getFeatureTypeName();
				String getCapsLink = output.getGetCapsUrl();
				
				if ((resourceID != null) && (getCapsLink != null)) {
					// Load WFSFeatureType, reloading the WFS to reflect
					// changes
					URL getCapsURL = new URL(getCapsLink);
					if (getCapsURL.getHost().equals("localhost")) {
						// localhost - need to convert URL so its public
						getCapsLink = ServerUtils.convertLocalToPublicURL(getCapsLink, new URL(properties.getModuleBaseURL()).getHost());
					}
					// TODO: if WFS module isn't install, WFS options shouldn't be available
					WFSFeatureType wfsFeatureType = WFSHandler.instance().wfsDescribeFeatureType(
							getCapsLink, resourceID, true, 10);
					((WFSProcessOutput) processOutput)
							.setFeatureType(wfsFeatureType);
				} else {
					throw new WPSExecuteException("Error receiving process result "	+ outputDataType.getIdentifier() + 
							": Could not find WFS details in response document.");
				}
			}
		}
		return processOutput;
	}

	private WFSFeatureType addShapefileToGeoServer(String shapefileUrl,
			String workspace, String fileName) throws RESTConnectionException, IOException {
		// Create swps workspace
		geoserverREST.createWorkspace(workspace);
	
		// Download file
		URL url = new URL(shapefileUrl);
		File file = ServerUtils.writeToFile(url.openStream(), fileName + ".zip");
	
		// Add file to geoserver
		geoserverREST.uploadShapefile(file, workspace, fileName);
	
		return null;
	}
}
