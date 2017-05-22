package soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Envelope")
public class Response {
	
	private String status;
	private MyPojo Body;

	@XmlElement(name="Body")
	public MyPojo getBody() {
		return Body;
	}

	public void setBody(MyPojo body) {
		Body = body;
	}

	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
