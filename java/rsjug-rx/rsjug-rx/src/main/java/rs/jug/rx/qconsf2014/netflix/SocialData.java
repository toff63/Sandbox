package rs.jug.rx.qconsf2014.netflix;

import java.util.HashMap;
import java.util.Map;

public class SocialData {
	public Map<String,Object> getDataAsMap() {
		Map<String, Object> result = new HashMap<>();
		result.put("friends", "rsjug");
		return result;
	}
}
