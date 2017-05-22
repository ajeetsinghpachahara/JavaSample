package soap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;

import org.w3c.dom.Document;

public class SOAPClientSAAJ {

    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    public static void main(String args[]) {
    	
    	try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
           // String url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
            String url = "https://service.milestonesys.com/arcus/1.0/VendorService.svc";
            
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
       // String encoded = Base64.getEncoder().encodeToString(("doug.cheung@netgear.com"+":"+"1234").getBytes(StandardCharsets.UTF_8));  //Java 8
        //soapMessage.setProperty("Authorization", "Basic "+encoded);
        SOAPPart soapPart = soapMessage.getSOAPPart();

       // String serverURI = "http://ws.cdyne.com/";
        String serverURI1 = "http://milestone.dk/arcus/2013/11/";
        String serverURI2 = "http://milestone.dk/arcus/2013/06/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
       // envelope.addNamespaceDeclaration("example", serverURI);
        envelope.addNamespaceDeclaration("ns", serverURI1);
        envelope.addNamespaceDeclaration("ns1", serverURI2);
        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */
        
       /* <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://milestone.dk/arcus/2013/11/" xmlns:ns1="http://milestone.dk/arcus/2013/06/">
        <soapenv:Header/>
        <soapenv:Body>
           <ns:RegisterProvisions>
              <!--Optional:-->
              <ns:provisions>
                 <!--Zero or more repetitions:-->
                 <ns1:Provision>
                    <!--Optional:-->
                    <ns1:Amount>2</ns1:Amount>
                    <!--Optional:-->
                    <ns1:Comment></ns1:Comment>
                    <!--Optional:-->
                    <ns1:LicenseExpireDays>1095</ns1:LicenseExpireDays>
                    <!--Optional:-->
                    <ns1:LicenseType>ARCUSBL/10</ns1:LicenseType>
                    <!--Optional:-->
                    <ns1:SupExpireDays>0</ns1:SupExpireDays>
                    <!--Optional:-->
                    <ns1:UniqueHardwareId></ns1:UniqueHardwareId>
                 </ns1:Provision>
              </ns:provisions>
           </ns:RegisterProvisions>
        </soapenv:Body>
     </soapenv:Envelope>*/

     // Header may or may not exist yet
        SOAPHeader header = envelope.getHeader();
        if (header == null)
            header = envelope.addHeader();

        // Add WSS Usertoken Element Tree 
        final SOAPElement security = header.addChildElement("Security", "wsse",
                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        final SOAPElement userToken = security.addChildElement("UsernameToken", "wsse");
        userToken.addChildElement("Username", "wsse").addTextNode("doug.cheung@netgear.com");
        userToken.addChildElement("Password", "wsse").addTextNode("ntgr3481");
        
        
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        
       /* SOAPElement Header = soapBody.addBodyElement(new QName("Header"));

      //attribute                     
              SOAPElement Security= Header.addChildElement(new QName("Security"));
              SOAPElement UsernameToken= Security.addChildElement(new QName("UsernameToken"));
              SOAPElement Username= UsernameToken.addChildElement(new QName("Username"));
              SOAPElement Password= UsernameToken.addChildElement(new QName("Password"));

      //enter the username and password
      Username.addTextNode("username");
      Password.addTextNode("password");*/

        
        
        //SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail", "example");
        SOAPElement soapBodyElem = soapBody.addChildElement("RegisterProvisions", "ns");
        SOAPElement soapBodyElemProvisions = soapBodyElem.addChildElement("provisions", "ns");
        //SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email", "example");
        SOAPElement soapBodyElem1 = soapBodyElemProvisions.addChildElement("Provision", "ns1");
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("Amount", "ns1");
        soapBodyElem2.addTextNode("2");
        SOAPElement soapBodyElem3 = soapBodyElem1.addChildElement("Comment", "ns1");
        SOAPElement soapBodyElem4 = soapBodyElem1.addChildElement("LicenseExpireDays", "ns1");
        soapBodyElem4.addTextNode("0");
        SOAPElement soapBodyElem5 = soapBodyElem1.addChildElement("LicenseType", "ns1");
        soapBodyElem5.addTextNode("ARCUSBL/10");
        SOAPElement soapBodyElem6 = soapBodyElem1.addChildElement("SupExpireDays", "ns1");
        soapBodyElem6.addTextNode("0");
        SOAPElement soapBodyElem7 = soapBodyElem1.addChildElement("UniqueHardwareId", "ns1");
        soapBodyElem7.addTextNode("4XK16CE80002B");
        /*soapBodyElem1.addTextNode("mutantninja@gmail.com");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey", "example");
        soapBodyElem2.addTextNode("123");*/

        MimeHeaders headers = soapMessage.getMimeHeaders();
       // headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");
        
        headers.addHeader("SOAPAction", "http://milestone.dk/arcus/2013/11/IVendorService/RegisterProvisions");
       // headers.addHeader("Authorization", "Basic " + encoded);
        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        
        final StringWriter sw = new StringWriter();
        
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(sw);
        transformer.transform(sourceContent, result);
        System.out.print("\nResponse SOAP Message = "+sw.toString());
        String bodyContent = soapResponse.getSOAPBody().getTextContent();
        System.out.print("\nResponse SOAP Message = "+bodyContent);
        
       /* JAXBContext jc = JAXBContext.newInstance(MyPojo.class);
        Unmarshaller um = jc.createUnmarshaller();
        MyPojo output = (MyPojo)um.unmarshal(soapResponse.getSOAPBody().extractContentAsDocument());
        
        
        System.out.println(output.getFault().getFaultcode());*/
        parseResponse(sw.toString());
    }
    
    
    private static void parseResponse(String xmlString)
    {
    	try
    	{
    		/*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.parse( new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
        org.w3c.dom.Node getNumberResponseElt = d.getElementsByTagNameNS("http://schemas.xmlsoap.org/soap/envelope/", "Body").item(0);

        JAXBContext jc = JAXBContext.newInstance(MyPojo.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<MyPojo> je = unmarshaller.unmarshal(new DOMSource(getNumberResponseElt), MyPojo.class);
        System.out.println("parsed data");
        System.out.println(je.getName());
        System.out.println(je.getValue().getFault().faultcode);
        */
    		
    		JAXBContext jc = JAXBContext.newInstance(Response.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
           
            Response res = (Response) unmarshaller.unmarshal(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
            System.out.println(res.getStatus());
            System.out.println(res.getBody().getReason());
            System.out.println(res.getBody().getFault().getFaultcode());
            
            
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

}