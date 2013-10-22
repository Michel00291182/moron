package nl.hanze.moron.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import nl.hanze.moron.MAPType;
import nl.hanze.moron.ObjectFactory;

public class JAXBExample 
{
	public static void main(String[] args) {
		 
		 try {
	 
			File file = new File("/Users/jongsml/Workspaces/Eclipse_Hanze_Project/moron/src/main/resources/MapMetSonarTest.xml");

			
			JAXBContext jaxbContext = JAXBContext.newInstance("nl.hanze.moron");
	 
			JAXBElement<MAPType> m = (JAXBElement<MAPType>)jaxbContext.createUnmarshaller().unmarshal(file);
			MAPType mapType =  m.getValue();
			
			
			//JAXBElement<MAPType> myJAXBObject = (JAXBElement<MAPType>) jaxbUnmarshaller.unmarshal(file);
			
			// Booking b = (Booking) jaxbCtx.createUnmarshaller().unmarshal(new StringReader(xmlWriter.toString()));

			
			//MyJAXBObject myJAXBObject = (MyJAXBObject) um.unmarshal(new java.io.FileInputStream( "myDoc.xml" ))
			//ObjectFactory objectFactory = (ObjectFactory) jaxbUnmarshaller.unmarshal(file);
			System.out.println(mapType.toString());
			
			
	 
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
	 
		}
	

}
