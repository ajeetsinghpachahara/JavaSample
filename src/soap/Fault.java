package soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="http://schemas.xmlsoap.org/soap/envelope/")
public  class Fault
{
	private String  faultcode;
	private String  faultstring;
	
	
	@XmlElement(namespace="",name="faultcode")
	public String getFaultcode() {
		return faultcode;
	}
	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}
	@XmlElement(name="faultstring")
	public String getFaultstring() {
		return faultstring;
	}
	public void setFaultstring(String faultstring) {
		this.faultstring = faultstring;
	}
	
	
	
}