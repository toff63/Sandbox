package net.francesbagual.collectors.flatresponse;

import java.util.List;

public class WSFlatResponseOutput {
	private List<WSField> fields;

	public WSFlatResponseOutput(List<WSField> fields) {
		this.fields = fields;
	}
	
	public List<WSField> getFields() {
		return fields;
	}
	

}
