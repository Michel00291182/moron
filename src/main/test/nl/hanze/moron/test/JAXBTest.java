package nl.hanze.moron.test;

import java.util.Collection;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import nl.hanze.moron.MAPType;
import nl.hanze.moron.OBSTACLEType;
import nl.hanze.moron.ObjectFactory;
import nl.hanze.moron.POINTType;
import junit.framework.TestCase;

public class JAXBTest extends TestCase 
{
	
	
	public Collection<POINTType> createPoints(ObjectFactory factory)
	{
		int min = 200;
		int max = 300;
		Random ran = new Random();
		Collection<POINTType> pointCollection = factory.createOBSTACLEType().getPOINT();
		for (int i=0;i<5 ;i++ )
		{
			POINTType pointType = factory.createPOINTType();
			pointType.setX(new Short((short) (ran.nextInt(max - min + 1) + min)));
			pointType.setY(new Short((short) (ran.nextInt(max - min + 1) + min)));
			pointCollection.add(pointType);
		} 
		return pointCollection;
	}
	
public void testMarshall() throws JAXBException 
{
	JAXBContext jc = JAXBContext.newInstance("nl.hanze.moron");
	ObjectFactory factory = new ObjectFactory();
	MAPType mapType = factory.createMAPType();
	OBSTACLEType createOBSTACLEType = factory.createOBSTACLEType();
	createOBSTACLEType.setNAME("WandBoven");
	createOBSTACLEType.setOPAQUE("false");
	createOBSTACLEType.getPOINT().addAll(createPoints(factory));
	mapType.getOBSTACLE().add(createOBSTACLEType);
	JAXBElement<MAPType> element = factory.createMAP(mapType);
	Marshaller m = jc.createMarshaller();
	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	m.marshal(element, System.out);
}
}