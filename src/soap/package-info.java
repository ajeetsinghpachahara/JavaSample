@XmlSchema(
    namespace="http://schemas.xmlsoap.org/soap/envelope/",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns={
       @XmlNs(prefix="s", namespaceURI="http://schemas.xmlsoap.org/soap/envelope/"), 
       @XmlNs(prefix="u", namespaceURI="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
   }
)

package soap;

import javax.xml.bind.annotation.*;