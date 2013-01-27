package uk.ac.glam.smartwps.base.server;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import uk.ac.glam.smartwps.base.server.logging.ServerToClientHandler;
import uk.ac.glam.smartwps.base.shared.ows.OWSRequestService;
import uk.ac.glam.smartwps.base.shared.response.RetrieveServerLogsResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Contains a number of GWT RPC methods for accessing Open Web Services.
 * 
 * @author Jon Britton
 */
public class OWSRequestServiceImpl extends RemoteServiceServlet implements
		OWSRequestService {

	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	private ServerToClientHandler stcHandler;
	private WPSProperties properties;

	@Override
	public void init() throws ServletException {
		super.init();

		WPSProperties.initialiseProperties(this);
		stcHandler = new ServerToClientHandler(LOGGER);
		properties = WPSProperties.getProperties();
	}

	/**
	 * TODO: document
	 * @return
	 */
	public WPSProperties getProperties() {
		return properties;
	}

	@Override
	public RetrieveServerLogsResponse retrieveServerLogs() {
		RetrieveServerLogsResponse response = new RetrieveServerLogsResponse();
		response.setLogRecords(stcHandler.getLogsAndClear());
		return response;
	}
		
	@Override
	public void testSomething() {
		//gmlToShapefile("http://localhost:8080/wps/RetrieveResultServlet?id=6e54ad97-9c57-44e3-b9aa-f58a8702da0cSIMPLIFIED_FEATURESresult-9ac22d25-8485-40c4-89e8-f880cead17ac");
	}

	@Override
	public void setModuleBaseURL(String moduleBaseURL) {
		properties.setModuleBaseURL(moduleBaseURL);
	}
}