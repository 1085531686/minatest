package learn.test.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class MessageBodyConfig {

	
	private List<Object> values = new ArrayList<Object>();
	
	private List<String> messagebodycfg = new ArrayList<String>();
	/**
	 * time,orgcode,lane,vlp
	 * @return
	 */
	public List<String> getMessagebody() {
		messagebodycfg.add("19,String");
		messagebodycfg.add("3,String");
		messagebodycfg.add("2,String");
		messagebodycfg.add("10,String");
		return messagebodycfg;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}
	
	
	 
	 
	
}
