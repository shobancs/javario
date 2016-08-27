package org.shoban.jaxp;

import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParserDemo {

	  public static void main(String[] args) throws Exception {
	    //Get the DOM Builder Factory
	    DocumentBuilderFactory factory = 
	        DocumentBuilderFactory.newInstance();

	    //Get the DOM Builder
	    DocumentBuilder builder = factory.newDocumentBuilder();
	   // Document document1 = builder.parse(new FileInputStream("emp.xml"));

	    //Load and Parse the XML document
	    //document contains the complete XML as a Tree.
	    Document document = 
	      builder.parse(
	        ClassLoader.getSystemResourceAsStream("artidr1_configdescriptor.xml"));

	    //List<Employee> empList = new ArrayList<>();

	    //Iterating through the nodes and extracting the data.
	    Set<String> urls = new HashSet<>();
	    //removeTextNodes(document);
	    NodeList nodeList = document.getDocumentElement().getChildNodes();

	    for (int i = 0; i < nodeList.getLength(); i++) {

	      
	      Node node = nodeList.item(i);
	      if (node instanceof Element && node.getNodeName().equals("localReplications")) {
	    	  
	    	  NodeList replicationList = node.getChildNodes();
	    	  for (int j = 0; j < replicationList.getLength(); i++) { 
	    		  Node nodeReplication = nodeList.item(j);System.out.println(nodeReplication.getNodeName());
	    		  if (nodeReplication instanceof Element && nodeReplication.getNodeName().equals("url")){
	    			  urls.add(nodeReplication.getNodeValue());
	    			  System.out.println(nodeReplication.getNodeValue());
	    		  }
				
			}
	      }

	    }
	    System.out.println(urls);

	   
	  }
	  static void removeTextNodes(Document document) throws XPathExpressionException{
		  XPathFactory xpathFactory = XPathFactory.newInstance();
		// XPath to find empty text nodes.
		XPathExpression xpathExp = xpathFactory.newXPath().compile(
		    "//text()[normalize-space(.) = '']");  
		NodeList emptyTextNodes = (NodeList) 
		    xpathExp.evaluate(document, XPathConstants.NODESET);
		// Remove each empty text node from document.
		for (int i = 0; i < emptyTextNodes.getLength(); i++) {
		  Node emptyTextNode = emptyTextNodes.item(i);
		emptyTextNode.getParentNode().removeChild(emptyTextNode);
		}
	  }
	}

	