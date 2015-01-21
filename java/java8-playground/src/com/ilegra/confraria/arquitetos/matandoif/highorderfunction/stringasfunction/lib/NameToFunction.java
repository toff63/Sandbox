package com.ilegra.confraria.arquitetos.matandoif.highorderfunction.stringasfunction.lib;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NameToFunction {
	
	public <T, U> Map<String, U> convert(T instance, Class<U> filter){
		List<Field> fields = Arrays.<Field> asList(instance.getClass().getDeclaredFields());
		return fields.stream()
				.filter(field -> field.getType().equals(filter))
				.collect(Collectors.<Field,String,U>toMap(f -> f.getName(), f -> this.getField(instance, f)));

	}

	private <T,U> U getField(T instance, Field f){
		try {
			f.setAccessible(true);
			return (U)  f.get(instance);
		} catch (Exception e) {
			return null;
		}
	}
}
