package uk.ac.glam.smartwps.wps.server;

import java.io.IOException;
import java.io.StringReader;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Contains utility methods for handling XML parsing.
 * 
 * @author Jon Britton
 */
public class XMLUtils {
	
	
	/**
	 * Creates a WFS output objects from the given DOM node. This is used
	 * to parse WFS outputs from WPS processes.
	 * @param rootNode the root node of the DOM representing the output
	 * @return the parsed WFS output
	 * @throws SAXException 
	 * @throws IOException 
	 */
	public static WFSOutputData parseWFSOutput(Node rootNode) throws SAXException, IOException {
		// For some reason the output is included as text, not part of the DOM so we have to parse it ourselves.
		// TODO: should check for when this is not the case and act accordingly
		String value = rootNode.getFirstChild().getNodeValue();
		DOMParser parser = new DOMParser();
		parser.parse(new InputSource(new StringReader(value)));
		Document doc = parser.getDocument();
		
		NodeList root = doc.getChildNodes();
		
		Node response = getNode("OWSResponse", root);
		NodeList nodes = response.getChildNodes();
		String resourceId = getNodeValue("ResourceID", nodes);
		String getCapsLink = getNodeValue("GetCapabilitiesLink", nodes);
		return new WFSOutputData(resourceId, getCapsLink);
	}

	/**
	 * Retrieves the node with given name from a NodeList
	 * @param tagName the name of the node
	 * @param nodes the NodeList to search
	 * @return the found node, or null if not found
	 */
	protected static Node getNode(String tagName, NodeList nodes) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            return node;
	        }
	    }
	    return null;
	}
	 
	/**
	 * Returns the values of the given node.
	 * @param node the node
	 * @return the node's value. Never null.
	 */
	protected static String getNodeValue( Node node ) {
	    NodeList childNodes = node.getChildNodes();
	    for (int x = 0; x < childNodes.getLength(); x++ ) {
	        Node data = childNodes.item(x);
	        if ( data.getNodeType() == Node.TEXT_NODE )
	            return data.getNodeValue();
	    }
	    return "";
	}
	 
	/**
	 * Gets the value of the node with the given name from a NodeList.
	 * @param tagName the node name to search for
	 * @param nodes the NodeList to search
	 * @return the node value. Never null.
	 */
	protected static String getNodeValue(String tagName, NodeList nodes ) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            NodeList childNodes = node.getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++ ) {
	                Node data = childNodes.item(y);
	                if ( data.getNodeType() == Node.TEXT_NODE )
	                    return data.getNodeValue();
	            }
	        }
	    }
	    return "";
	}
	 
	/**
	 * Gets the value of the given attribute from a Node.
	 * @param attrName the name of the attribute
	 * @param node the Node to search
	 * @return the value of the attribute. Never null.
	 */
	protected static String getNodeAttr(String attrName, Node node ) {
	    NamedNodeMap attrs = node.getAttributes();
	    for (int y = 0; y < attrs.getLength(); y++ ) {
	        Node attr = attrs.item(y);
	        if (attr.getNodeName().equalsIgnoreCase(attrName)) {
	            return attr.getNodeValue();
	        }
	    }
	    return "";
	}
	 
	/**
	 * Gets the value for the given attribute from the node with the given name from a
	 * NodeList.
	 * @param tagName the node name
	 * @param attrName the attribute name
	 * @param nodes the NodeList to search
	 * @return the attribute value of the node. Never null.
	 */
	protected static String getNodeAttr(String tagName, String attrName, NodeList nodes ) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            NodeList childNodes = node.getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++ ) {
	                Node data = childNodes.item(y);
	                if ( data.getNodeType() == Node.ATTRIBUTE_NODE ) {
	                    if ( data.getNodeName().equalsIgnoreCase(attrName) )
	                        return data.getNodeValue();
	                }
	            }
	        }
	    }
	    return "";
	}
}
