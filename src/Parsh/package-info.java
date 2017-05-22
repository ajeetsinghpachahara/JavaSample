@XmlSchema(
    namespace="http://mycompany/train",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns={
       @XmlNs(prefix="train", namespaceURI="http://mycompany/train"), 
       @XmlNs(prefix="passenger", namespaceURI="http://mycompany/passenger")
   }
)
package Parsh;

import javax.xml.bind.annotation.*;