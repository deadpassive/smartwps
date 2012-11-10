package uk.ac.glam.smartwps.server.wcs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;
import uk.ac.glam.smartwps.shared.wcs111.CoverageDescription;
import uk.ac.glam.smartwps.shared.wcs111.CoverageSummary;
import uk.ac.glam.smartwps.shared.wcs111.Domain;
import uk.ac.glam.smartwps.shared.wcs111.SpatialDomain;
import uk.ac.glam.smartwps.shared.wcs111.WCSCapabilities111;
import uk.ac.glam.wcsclient.WCS111;
import uk.ac.glam.wcsclient.ows110.BoundingBoxType;
import uk.ac.glam.wcsclient.ows110.LanguageStringType;
import uk.ac.glam.wcsclient.ows110.WGS84BoundingBoxType;
import uk.ac.glam.wcsclient.wcs111.CapabilitiesType;
import uk.ac.glam.wcsclient.wcs111.ContentsType;
import uk.ac.glam.wcsclient.wcs111.CoverageDescriptionType;
import uk.ac.glam.wcsclient.wcs111.CoverageDomainType;
import uk.ac.glam.wcsclient.wcs111.CoverageSummaryType;
import uk.ac.glam.wcsclient.wcs111.SpatialDomainType;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WCS111Adapter {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	/**
	 * TODO: document
	 * @param wcs
	 * @return
	 */
	public static WCSCapabilities111 parseWCSCapabilities(WCS111 wcs) {
		WCSCapabilities111 wcsCapabilities = new WCSCapabilities111();
		CapabilitiesType capabilities = wcs.getCapabilities();
		// Adapt emf object to serializable object we can pass back to the browser
		
		// Contents
		wcsCapabilities.setContents(contentsAdapter(capabilities.getContents()));
		
		// TODO: ServiceIdentification
		// TODO: ServiceProvider
		// TODO: OperationsMetadata
		
		wcsCapabilities.setServiceURL(wcs.getServiceURL());
		
		return wcsCapabilities;
	}

	private static ArrayList<CoverageSummary> contentsAdapter(
			ContentsType contents) {
		ArrayList<CoverageSummary> coverageOfferingsFinal = new ArrayList<CoverageSummary>();
		List<CoverageSummaryType> coverageSummaryList = contents.getCoverageSummary();
		for (Iterator<CoverageSummaryType> iterator = coverageSummaryList.iterator(); iterator
				.hasNext();) {
			CoverageSummaryType coverageSummary = iterator.next();
			coverageOfferingsFinal.add(coverageSummaryAdapter(coverageSummary));
		}

		return coverageOfferingsFinal;
	}

	private static CoverageSummary coverageSummaryAdapter(
			CoverageSummaryType coverageSummary) {
		CoverageSummary coverageSummaryFinal = new CoverageSummary();
		// Title
		coverageSummaryFinal.setTitle(elistStringAdapter(coverageSummary.getTitle()));
		// Abstract
		coverageSummaryFinal.setAbstract(elistStringAdapter(coverageSummary.getAbstract()));
		
		// TODO: Keywords
		// TODO: WGS84BoundingBox
		coverageSummaryFinal.setWGS84BoundingBox(boundingBoxAdapter(coverageSummary.getWGS84BoundingBox().get(0)));	// assuming only one?
		
		// Identifier
		coverageSummaryFinal.setIdentifier(coverageSummary.getIdentifier());
		
		LOGGER.info("Found coverage: " + coverageSummaryFinal.getIdentifier());
		
		return coverageSummaryFinal;
	}

	private static BoundsSerializable boundingBoxAdapter(
			BoundingBoxType bboxType) {
		BoundsSerializable bbox = new BoundsSerializable();
		
		bbox.setMinX(bboxType.getLowerCorner().get(0));
		bbox.setMinY(bboxType.getLowerCorner().get(1));
		bbox.setMaxX(bboxType.getUpperCorner().get(0));
		bbox.setMaxY(bboxType.getUpperCorner().get(1));
		
		if ((bboxType.getCrs() != null) && (!bboxType.getCrs().equals("")))
			bbox.setProjection(bboxType.getCrs());
		else if (bboxType instanceof WGS84BoundingBoxType)
			bbox.setProjection("urn:ogc:def:crs:EPSG::4326");
		
		return bbox;
	}

	private static String elistStringAdapter(EList<LanguageStringType> elist) {
		String s = "";
		for (Iterator<LanguageStringType> iterator = elist.iterator(); iterator.hasNext();) {
			LanguageStringType languageStringType = iterator.next();
			s += languageStringType.getValue();
		}
		return s;
	}
	
	private static ArrayList<String> elistAdapter(EList<String> elist) {
		ArrayList<String> newList = new ArrayList<String>();
		for (Iterator<String> iterator = elist.iterator(); iterator.hasNext();) {
			newList.add(iterator.next());
		}
		return newList;
	}

	/**
	 * TODO: document
	 * @param wcs
	 * @param identifier
	 * @return
	 * @throws IOException
	 */
	public static CoverageDescription parseDescribeCoverage(WCS111 wcs, String identifier) throws IOException {
		CoverageDescription coverageDescription = new CoverageDescription();
		CoverageDescriptionType cdt = wcs.describeCoverage(identifier);
		
		// Title
		coverageDescription.setTitle(elistStringAdapter(cdt.getTitle()));
		// Abstract
		coverageDescription.setAbstract(elistStringAdapter(cdt.getAbstract()));
		// Identifier
		coverageDescription.setIdentifier(cdt.getIdentifier());
		// SupportedCRS
		coverageDescription.setSupportedCRSs(elistAdapter(cdt.getSupportedCRS()));
		// Domain
		coverageDescription.setDomain(domainAdapter(cdt.getDomain()));
		
		// TODO: Keywords
		// TODO: Range
		// TODO: SupportedFormat
		
		
		coverageDescription.setServiceURL(wcs.getServiceURL());
		coverageDescription.setCoverageSummary(coverageSummaryAdapter(wcs.getCoverageSummary(identifier)));
		
		return coverageDescription;
	}

	private static Domain domainAdapter(CoverageDomainType domainType) {
		Domain domain = new Domain();
		
		// SpatialDomain
		domain.setSpatialDomain(spatialDomainAdapter(domainType.getSpatialDomain()));
		
		// TODO: TemporalDomain
		
		return domain;
	}

	private static SpatialDomain spatialDomainAdapter(SpatialDomainType spatialDomainType) {
		SpatialDomain spatialDomain = new SpatialDomain();
		
		// BoundingBox
		List<BoundingBoxType> bboxs = spatialDomainType.getBoundingBox();
		for (Iterator<BoundingBoxType> iterator = bboxs.iterator(); iterator.hasNext();) {
			BoundingBoxType boundingBoxType = iterator.next();
			spatialDomain.addBoundingBox(boundingBoxAdapter(boundingBoxType));
		}
		
		// GridCRS
		if (spatialDomainType.getGridCRS() != null) {
			spatialDomain.setGridBaseCRS(spatialDomainType.getGridCRS().getGridBaseCRS());
			// TODO: GridType
			// TODO: GridOrigin
			// TODO: GridOffsets
			// TODO: GridCS
		}
		
		return spatialDomain;
	}
}
