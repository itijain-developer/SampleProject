package bean;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dataIntegrityValidation")
public class DataIntegrityValidation {

	public DataIntegrityValidation(Map<String, String> response) {
		super();
		this.response = response;
	}

	Map<String, String> response;

	public Map<String, String> getResponse() {
		return response;
	}

	public void setResponse(Map<String, String> response) {
		this.response = response;
	}

}