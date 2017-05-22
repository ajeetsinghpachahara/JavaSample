package soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace="http://schemas.xmlsoap.org/soap/envelope/")
public class MyPojo {
	
	private Fault Fault;
	
	private String reason;
	
	
	
	@XmlElement(name="Fault")
	public Fault getFault() {
		return Fault;
	}




	public void setFault(Fault fault) {
		Fault = fault;
	}



	@XmlElement(name="reason")
	public String getReason() {
		return reason;
	}




	public void setReason(String reason) {
		this.reason = reason;
	}

	
	


	

}
