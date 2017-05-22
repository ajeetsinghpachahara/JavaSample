package Parsh;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ParshXML {
	
	
	public static void main(String args[])
	{
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Train.class);
		

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("/home/linchpin/Desktop/response.xml");
        Train train = (Train) unmarshaller.unmarshal(xml);
        
        System.out.println(train.getPassengers().get(0).getTicketNumber());
        
        System.out.println(train.getColor());
        
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
