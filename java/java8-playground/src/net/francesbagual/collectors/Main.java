package net.francesbagual.collectors;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.francesbagual.collectors.domain.Person;
import net.francesbagual.collectors.flatresponse.WSField;
import net.francesbagual.collectors.flatresponse.WSFlatResponseOutput;
import net.francesbagual.collectors.flatresponse.collector.WSResponseCollector;

public class Main {
	
	public static void main(String[] args) {
		WSFlatResponseOutput flatResponse = new WSFlatResponseOutput(Stream.of(new WSField("name", "John"), new WSField("age", "25")).collect(Collectors.toList()));
		Person p = flatResponse.getFields().stream().collect(new WSResponseCollector<Person>(() -> new Person(), personFieldsMapping()));
		System.out.println(p);
	}
	
	private static Map<String, BiConsumer<Person, WSField>> personFieldsMapping(){
		 Map<String, BiConsumer<Person, WSField>> fieldMappings = new HashMap<String, BiConsumer<Person, WSField>>();
		 fieldMappings.put("name", (person, wsfield) -> person.setName(wsfield.getValue()));
		 fieldMappings.put("age", (person, wsfield) -> person.setAge(Integer.valueOf(wsfield.getValue())));
		 return fieldMappings;
	}

}
