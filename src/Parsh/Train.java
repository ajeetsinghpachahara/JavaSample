package Parsh;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="Train")
public class Train {

    private List<Passenger> passengers;
    
    private String Color;

    @XmlElementWrapper(name="Passengers")
    @XmlElement(name="Passenger")
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    @XmlElement(name="Color")
	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}
    
    

}
