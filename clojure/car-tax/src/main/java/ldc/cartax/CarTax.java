package ldc.cartax;

import java.io.IOException;
import java.util.Arrays;

import clojure.lang.IPersistentMap;
import clojure.lang.ISeq;
import clojure.lang.LazySeq;
import clojure.lang.RT;
import clojure.lang.Var;

public class CarTax {

	public static void main(String[] args) throws IOException {
		RT.loadResourceScript("ldc/cartax/car_tax.clj");
		Var carTax = RT.var("ldc.cartax.car-tax", "car-tax");

		IPersistentMap carMap1 = RT.map(
				RT.keyword(null, "owner"), "Christophe", 
				RT.keyword(null, "speed"), 100, 
				RT.keyword(null, "type"), "Ferrari", 
				RT.keyword(null, "licence-plate"), "123456");
		IPersistentMap carMap2 = RT.map(
				RT.keyword(null, "owner"), "Bob",
				RT.keyword(null, "speed"), 250, 
				RT.keyword(null, "type"), "Porsche", 
				RT.keyword(null, "licence-plate"), "1234567");
		ISeq cars = RT.seq(Arrays.asList(carMap1, carMap2));
		LazySeq results = (LazySeq) carTax.invoke(cars);
		for(Object o : results){
			System.out.println(o);
		}
	}
}
