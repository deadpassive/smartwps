package uk.ac.glam.smartwps.server.wcs;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.resource.Resource;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;
import uk.ac.glam.smartwps.shared.wcs100.CoverageOffering;
import uk.ac.glam.smartwps.shared.wcs100.CoverageOfferingBrief;
import uk.ac.glam.smartwps.shared.wcs100.DomainSet;
import uk.ac.glam.smartwps.shared.wcs100.Grid;
import uk.ac.glam.smartwps.shared.wcs100.GridEnvelope;
import uk.ac.glam.smartwps.shared.wcs100.RectifiedGrid;
import uk.ac.glam.smartwps.shared.wcs100.Service;
import uk.ac.glam.smartwps.shared.wcs100.SpatialDomain;
import uk.ac.glam.smartwps.shared.wcs100.WCSCapabilities;
import uk.ac.glam.wcsclient.WCS100;
import uk.ac.glam.wcsclient.wcs100.ContentMetadataType;
import uk.ac.glam.wcsclient.wcs100.CoverageOfferingBriefType;
import uk.ac.glam.wcsclient.wcs100.CoverageOfferingType;
import uk.ac.glam.wcsclient.wcs100.DocumentRoot;
import uk.ac.glam.wcsclient.wcs100.DomainSetType;
import uk.ac.glam.wcsclient.wcs100.ServiceType;
import uk.ac.glam.wcsclient.wcs100.SpatialDomainType;
import uk.ac.glam.wcsclient.wcs100.WCSCapabilitiesType;
import uk.ac.glam.wcsclient.wcs100.gml.CodeListType;
import uk.ac.glam.wcsclient.wcs100.gml.EnvelopeType;
import uk.ac.glam.wcsclient.wcs100.gml.GridEnvelopeType;
import uk.ac.glam.wcsclient.wcs100.gml.GridType;
import uk.ac.glam.wcsclient.wcs100.gml.RectifiedGridType;
import uk.ac.glam.wcsclient.wcs100.gml.VectorType;
import uk.ac.glam.wcsclient.wcs100.util.Wcs100XMLProcessor;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WCS100Adapter {
    
    /**
     * Private constructor to prevent this utility class being instantiated.
     */
    private WCS100Adapter() {}
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	/**
	 * Parse the capabilities information into a serializable format.
	 * @param wcs the WCS information
	 * @return the capabilities data in a serializable WCSCapabilities class
	 */
	public static WCSCapabilities parseWCSCapabilities(WCS100 wcs) {
		WCSCapabilities wcsCapabilities = new WCSCapabilities();
		
		WCSCapabilitiesType capabilities = wcs.getCapabilities();
		
		// Adapt emf object to serializable object we can pass back to the browser
		
		// CoverageOfferings
		wcsCapabilities.setCoverageOfferings(contentMetadataAdapter(capabilities.getContentMetadata()));
		// Service
		wcsCapabilities.setService(serviceAdapter(capabilities.getService()));
		
		wcsCapabilities.setServiceURL(wcs.getServiceURL());
		
		return wcsCapabilities;
	}
	
	/**
	 * TODO: document
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static CoverageOffering parseDescribeCoverage(String url) throws IOException {
		
		Wcs100XMLProcessor processor = new Wcs100XMLProcessor();
		Resource r = processor.load(url, null);
		//EList<EObject> eList = r.getContents();
		DocumentRoot root = (DocumentRoot) r.getContents().get(0);
		//We've only requested one coverage description
		CoverageOfferingType coverageOfferingType = root.getCoverageDescription().getCoverageOffering().get(0);
		CoverageOffering coverageOffering = coverageOfferingAdapter(coverageOfferingType);
		coverageOffering.setServiceURL(url.split("\\?")[0]);
		
		return coverageOffering;
	}

	private static CoverageOffering coverageOfferingAdapter(
			CoverageOfferingType coverageOfferingType) {
		LOGGER.fine("In coverageOfferingAdapter");
		CoverageOffering coverageOffering = new CoverageOffering();
		coverageOffering.setDescription(coverageOfferingType.getDescription1());	// Description
		coverageOffering.setDomainSet(domainSetAdapter(coverageOfferingType.getDomainSet()));	// DomainSet
		coverageOffering.setLabel(coverageOfferingType.getLabel());		// Label
		coverageOffering.setLonLatEnvelope(envelopeAdapter(coverageOfferingType.getLonLatEnvelope()));	// LonLatEnvelope
		coverageOffering.setName(coverageOfferingType.getName1());		// Name
		// RangeSet TODO: unsupported
		// Supported CRS - don't bother makes a SupportedCRSs class
		ArrayList<String> requestCRSs = new ArrayList<String>();
		ArrayList<String> responseCRSs = new ArrayList<String>();
		if (coverageOfferingType.getSupportedCRSs().getRequestResponseCRSs() == null) {
			// should have seperate request and response lists
			// RequestCRSs
			List<CodeListType> requestCrsList = coverageOfferingType.getSupportedCRSs().getRequestCRSs();
            for (CodeListType codeList : requestCrsList) {
				requestCRSs.addAll(codeList.getValue());
			}
			// ReponseCRSs
			List<CodeListType> responseCrsList = coverageOfferingType.getSupportedCRSs().getResponseCRSs();
            for (CodeListType codeList : responseCrsList) {
				responseCRSs.addAll(codeList.getValue());
			}
		} else {
			// we have a RequestResponseCRS list
			List<CodeListType> requestResponseCrsList = coverageOfferingType.getSupportedCRSs().getRequestResponseCRSs();
            for (CodeListType codeList : requestResponseCrsList) {
				requestCRSs.addAll(codeList.getValue());
				responseCRSs.addAll(codeList.getValue());
			}
		}
		coverageOffering.setRequestCRSs(requestCRSs);
		coverageOffering.setResponseCRSs(responseCRSs);
		
		return coverageOffering;
	}

	private static DomainSet domainSetAdapter(DomainSetType domainSetType) {
		LOGGER.fine("In domainSetAdapter");
		DomainSet domainSet = new DomainSet();
		// Spatial Domain
		SpatialDomainType spatialDomainType = domainSetType.getSpatialDomain();
		domainSet.setSpatialDomain(spatialDomainAdapter(spatialDomainType));
		// TemporalDomain
		// TODO: unsupported
		//if (spatialDomainType == null) {
			// use getTomporalDomain1
		//	domainSet.setTomporalDomain(temporalDomainAdapter(domainSetType.getTemporalDomain1()));
		//} else {
			// use getTomporalDomain
		//}
		
		return domainSet;
	}

	private static SpatialDomain spatialDomainAdapter(SpatialDomainType spatialDomainType) {
		LOGGER.fine("In spatialDomainAdapter");
		SpatialDomain spatialDomain = new SpatialDomain();
		// Envelopes -- currently doesn't support EnvelopeWithTimePeriod
		List<EnvelopeType> envelopeTypes = spatialDomainType.getEnvelope();
		ArrayList<BoundsSerializable> envelopes = new ArrayList<BoundsSerializable>();
        for (EnvelopeType envelopeType : envelopeTypes) {
			envelopes.add(envelopeAdapter(envelopeType));
		}
		spatialDomain.setEnvelopes(envelopes);
		// Grids
		List<GridType> gridTypes = spatialDomainType.getGrid();
		ArrayList<Grid> grids = new ArrayList<Grid>();
        for (GridType gridType : gridTypes) {
			grids.add(gridAdapter(gridType));
		}
		spatialDomain.setGrids(grids);
		// Polygon
		//TODO: Implement polygon
		return spatialDomain;
	}

	private static Grid gridAdapter(GridType gridType) {
		LOGGER.fine("In gridAdapter");
		Grid grid;
		if (gridType instanceof RectifiedGridType) {
			grid = new RectifiedGrid();
			RectifiedGridType rectGrid = (RectifiedGridType)gridType;
			List<VectorType> vectorTypes = rectGrid.getOffsetVector();
			ArrayList<double[]> offsetVectors = new ArrayList<double[]>();
			//double[][] offsetVectors = new double[vectorTypes.size()][];
			for (int i = 0; i < vectorTypes.size(); i++) {
				VectorType vector = vectorTypes.get(i);
				double[] offsetVector = {vector.getValue().get(0), vector.getValue().get(1)};
				LOGGER.log(Level.INFO, "OFFSET VECTOR: {0},{1}", new Object[]{offsetVector[0], offsetVector[1]});
				offsetVectors.add(offsetVector);
			}
			grid.setOffsetVectors(offsetVectors);
			
		} else {
			grid = new Grid();
		}
		// Dimension
		grid.setDimension(gridType.getDimension().intValue());
		// Limits
		grid.setLimits(gridEnvelopeAdapter(gridType.getLimits().getGridEnvelope()));
		// Axis Names
		grid.setAxisNames(gridType.getAxisName());
		// SRS Name
		grid.setSRSName(gridType.getSrsName());
		
		return grid;
	}

	private static GridEnvelope gridEnvelopeAdapter(GridEnvelopeType gridEnvelopeType) {
		LOGGER.fine("In gridEnvelopeAdapter");
		GridEnvelope gridEnvelope = new GridEnvelope();
		// High
		List<BigInteger> highsOld = gridEnvelopeType.getHigh();
		int[] highs = new int[highsOld.size()];
		for (int i = 0; i < highs.length; i++) {
			highs[i] = highsOld.get(i).intValue();
		}
		gridEnvelope.setHigh(highs);
		// Low
		List<BigInteger> lowsOld = gridEnvelopeType.getLow();
		int[] lows = new int[lowsOld.size()];
		for (int i = 0; i < lows.length; i++) {
			lows[i] = lowsOld.get(i).intValue();
		}
		gridEnvelope.setLow(lows);
				
		return gridEnvelope;
	}

	private static Service serviceAdapter(ServiceType serviceType) {
		LOGGER.fine("In serviceAdapter");
		Service service = new Service();
		// Descripion
		service.setDescription(serviceType.getDescription1());
		return service;
	}

	private static ArrayList<CoverageOfferingBrief> contentMetadataAdapter(
			ContentMetadataType contentMetadata) {
		LOGGER.fine("In contentMetadataAdapter");
		ArrayList<CoverageOfferingBrief> coverageOfferingsFinal = new ArrayList<CoverageOfferingBrief>();
		List<CoverageOfferingBriefType> coverageOfferingsList = contentMetadata.getCoverageOfferingBrief();
        for (CoverageOfferingBriefType coverageOffering : coverageOfferingsList) {
			coverageOfferingsFinal.add(coverageOfferingBriefAdapter(coverageOffering));
		}
		return coverageOfferingsFinal;
	}

	private static CoverageOfferingBrief coverageOfferingBriefAdapter(
			CoverageOfferingBriefType coverageOffering) {
		LOGGER.fine("In coverageOfferingBriefAdapter");
		CoverageOfferingBrief coverageOfferingBriefFinal = new CoverageOfferingBrief();
		// Description
		coverageOfferingBriefFinal.setDescription(coverageOffering.getDescription1());
		// Keywords
		/*ArrayList<String> keywords = new ArrayList<String>();
		List<KeywordsType> keywordsTypes = coverageOffering.getKeywords();
        for (KeywordsType kw : keywordsTypes) {
			kw.get
			System.out.println(kw);
		}
		coverageOfferingBriefFinal.setKeyWords(keywords);*/
		// Label
		coverageOfferingBriefFinal.setLabel(coverageOffering.getLabel());
		// LonLatEnvelope
		coverageOfferingBriefFinal.setLonLatEnvelope(envelopeAdapter(coverageOffering.getLonLatEnvelope()));
		// Name
		coverageOfferingBriefFinal.setName(coverageOffering.getName1());
		LOGGER.log(Level.INFO, "Found coverage: {0}", coverageOfferingBriefFinal.getName());
		
		return coverageOfferingBriefFinal;
	}

	private static BoundsSerializable envelopeAdapter(
			EnvelopeType envelope) {
		LOGGER.fine("In envelopeAdapter");
		// Mins
		double minX = envelope.getPos().get(0).getValue().get(0);
		double minY = envelope.getPos().get(0).getValue().get(1);
		// Maxs
		double maxX = envelope.getPos().get(1).getValue().get(0);
		double maxY = envelope.getPos().get(1).getValue().get(1);
		//SRS
		String srs = envelope.getSrsName();
		
		return new BoundsSerializable(minX, minY, maxX, maxY, srs);
	}

	
}
