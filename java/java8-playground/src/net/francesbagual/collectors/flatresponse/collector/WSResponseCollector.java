package net.francesbagual.collectors.flatresponse.collector;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.francesbagual.collectors.flatresponse.WSField;

public class WSResponseCollector<T> implements Collector<WSField, T, T> {
	
	private Map<String, BiConsumer<T, WSField>> fieldMapping = new HashMap<String, BiConsumer<T,WSField>>();
	private Supplier<T> supplier;
	
	public WSResponseCollector( Supplier<T> supplier, Map<String, BiConsumer<T, WSField>> fieldMapping){
		this.fieldMapping = fieldMapping;
		this.supplier = supplier;
	}

	@Override
	public Supplier<T> supplier() {
		return supplier;
	}

	@Override
	public BiConsumer<T, WSField> accumulator() {
		return (person, field) -> fieldMapping.get(field.getName()).accept(person, field);
	}

	@Override
	public BinaryOperator<T> combiner() {
		return null;
	}

	@Override
	public Function<T, T> finisher() {
		return null;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(
                Collector.Characteristics.UNORDERED,
                Collector.Characteristics.IDENTITY_FINISH));
	}

}
