package com.ilegra.confraria.arquitetos.matandoif.highorderfunction.stringasfunction.lib;

import java.util.Map;
import java.util.Optional;

import com.ilegra.confraria.arquitetos.matandoif.highorderfunction.stringasfunction.MyNumberOperation;

public abstract class Operations<T> {
	
	protected abstract Class<T> getOperationClass();

	private Map<String, T> operations = null;

	private void init(){
		operations = new NameToFunction().convert(new MyNumberOperation(), getOperationClass());
	}
	
	public T findOrThrow(String operation) {
		return Optional.ofNullable(findOrNull(operation))
				       .orElseThrow(OperationNotFoundException::new);
	}
	
	public T findOrNull(String operation) {
		if(operations == null) init();
		return Optional.ofNullable(operations.get(operation))
				       .orElse(null);
	}
}
