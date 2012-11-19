package uk.ac.glam.smartwps.server.wps;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.opengis.ows.x11.ValueType;
import net.opengis.wps.x100.ComplexDataDescriptionType;
import net.opengis.wps.x100.InputDescriptionType;
import net.opengis.wps.x100.LiteralInputType;
import net.opengis.wps.x100.LiteralOutputType;
import net.opengis.wps.x100.OutputDescriptionType;
import net.opengis.wps.x100.ProcessBriefType;
import net.opengis.wps.x100.ProcessDescriptionType;
import net.opengis.wps.x100.SupportedComplexDataType;
import uk.ac.glam.smartwps.shared.wps.ComplexData;
import uk.ac.glam.smartwps.shared.wps.DetailedProcessDescriptor;
import uk.ac.glam.smartwps.shared.wps.Format;
import uk.ac.glam.smartwps.shared.wps.LiteralData;
import uk.ac.glam.smartwps.shared.wps.ProcessDescriptor;
import uk.ac.glam.smartwps.shared.wps.WPSData;

public class WPSAdapter {
    
    private WPSAdapter() {}
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	
	// Create a ProcessDescriptor from a ProcessBriefType
	public static ProcessDescriptor processBriefAdapter(ProcessBriefType processBrief, String url) {
		ProcessDescriptor processDescriptor = new ProcessDescriptor();
		
		processDescriptor.setId(processBrief.getIdentifier().getStringValue());
		processDescriptor.setTitle(processBrief.getTitle().getStringValue());
		processDescriptor.setProcessVersion(processBrief.getProcessVersion());
		
		// ServiceURL
		
		processDescriptor.setServiceURL(url);
		
		return processDescriptor;
	}

	// Create a DetailedProcessDescriptor from a ProcessDescriptionType
	public static DetailedProcessDescriptor detailedProcessDescriptionAdapter(ProcessDescriptionType processDescriptions, String url) {
		DetailedProcessDescriptor dpd = new DetailedProcessDescriptor();
		
		// Handle primitives
		if (processDescriptions.getAbstract() != null)
			dpd.setAbstract(processDescriptions.getAbstract().getStringValue());
		dpd.setId(processDescriptions.getIdentifier().getStringValue());
		dpd.setTitle(processDescriptions.getTitle().getStringValue());
		dpd.setStoreSupported(processDescriptions.getStoreSupported());
		
		// Handle complex types
		// DataInputs
		InputDescriptionType[] inputDescriptions = processDescriptions.getDataInputs().getInputArray();
		ArrayList<WPSData> newDataInputs = new ArrayList<WPSData>();
		for (int i = 0; i < inputDescriptions.length; i++) {
			newDataInputs.add(inputDescriptionAdapter(inputDescriptions[i]));
		}
		dpd.setDataInputs(newDataInputs);
		
		// Outputs
		OutputDescriptionType[] outputDescriptions = processDescriptions.getProcessOutputs().getOutputArray();
		ArrayList<WPSData> newProcessOutputs = new ArrayList<WPSData>();
		for (int i = 0; i < outputDescriptions.length; i++) {
			newProcessOutputs.add(outputDescriptionAdapter(outputDescriptions[i]));
		}
		dpd.setProcessOutputs(newProcessOutputs);
		
		// ServiceURL
		dpd.setServiceURL(url);
		
		LOGGER.info("Created DetailedProcessDescriptor from ProcessDescriptionType");
		return dpd;
	}
	
	public static WPSData outputDescriptionAdapter(
			OutputDescriptionType outputDescription) {
		WPSData outputData = null;
		if (outputDescription.getLiteralOutput() != null) {		// Literal Data
			outputData = literalDataAdapter(outputDescription.getLiteralOutput());
		} else if (outputDescription.getComplexOutput() != null) {	// Complex data
			outputData = complexDataAdapter(outputDescription.getComplexOutput());
			//ArrayList<Format> formats = ((ComplexData)outputData).getSupportedFormats();
		} else if (outputDescription.getBoundingBoxOutput() != null) {
			// TODO: handle BoundingBox data type
			LOGGER.info("ERROR");
		} else {
			// Sometimes we get an output which doesn't specify - for now we'll assume literal.
			outputData = new LiteralData();
		}
		if (outputData.getAbstract() != null)
			outputData.setAbstract(outputDescription.getAbstract().getStringValue());
		outputData.setIdentifier(outputDescription.getIdentifier().getStringValue());
		outputData.setTitle(outputDescription.getTitle().getStringValue());
		
		return outputData;
	}

	public static WPSData inputDescriptionAdapter(InputDescriptionType inputDescriptions) {
		WPSData inputData = null;
		if (inputDescriptions.getLiteralData() != null) { // Literal Data
			inputData = literalDataAdapter(inputDescriptions.getLiteralData());
		} else if (inputDescriptions.getComplexData() != null) { // Complex data
			inputData = complexDataAdapter(inputDescriptions.getComplexData());
		} else { // Bounding Box data
			// Sometimes we get an input which doesn't specify - for now we'll assume literal.
			inputData = new LiteralData();
		}
				
		if (inputDescriptions.getAbstract() != null)
			inputData.setAbstract(inputDescriptions.getAbstract().getStringValue());
		inputData.setIdentifier(inputDescriptions.getIdentifier().getStringValue());
		if (inputDescriptions.getTitle() != null)
			inputData.setTitle(inputDescriptions.getTitle().getStringValue());
		if (inputDescriptions.getMaxOccurs() != null)
			inputData.setMaxOccurs(inputDescriptions.getMaxOccurs().intValue());
		if (inputDescriptions.getMinOccurs() != null)
			inputData.setMinOccurs(inputDescriptions.getMinOccurs().intValue());
		
		return inputData;
	}
	
	public static Format formatAdapter(ComplexDataDescriptionType cdd) {
		Format format = new Format();
		
		format.setEncoding(cdd.getEncoding());
		format.setMimeType(cdd.getMimeType());
		format.setSchema(cdd.getSchema());
		
		return format;
	}
	
	public static LiteralData literalDataAdapter(LiteralOutputType literal) {
		LiteralData literalData = new LiteralData();
		literalData.setDataType(literal.getDataType().getReference());	// Data type

		// Allowed Values
		if (literal instanceof LiteralInputType) {
			LiteralInputType literalInput = (LiteralInputType) literal;
			
			if (literalInput.getAllowedValues() != null) {
				// Values
				ValueType[] values = literalInput.getAllowedValues().getValueArray();
				ArrayList<String> allowedValues = new ArrayList<String>();
				for (int i = 0; i < values.length; i++) {
					allowedValues.add(values[i].getStringValue());
				}
				literalData.setAllowedValues(allowedValues);
				// TODO: Range
			}
			
			// Default Value
			literalData.setDefaultValue(literalInput.getDefaultValue());
		}
		return literalData;
	}
	
	public static ComplexData complexDataAdapter(SupportedComplexDataType complex) {
		ComplexData complexData = new ComplexData();
		
		// Default format
		complexData.setDefaultFormat(formatAdapter(complex.getDefault().getFormat()));
		
		// Supported format
		ArrayList<Format> supportedFormats = new ArrayList<Format>();
		if (complex.getSupported() != null) {
			ComplexDataDescriptionType[] sFormats = complex.getSupported().getFormatArray();
			for (int i = 0; i < sFormats.length; i++) {
				supportedFormats.add(formatAdapter(sFormats[i]));
			}
			complexData.setSupportedFormats(supportedFormats);
		}
				
		return complexData;
	}

}
