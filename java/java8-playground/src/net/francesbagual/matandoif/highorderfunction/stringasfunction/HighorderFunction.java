package net.francesbagual.matandoif.highorderfunction.stringasfunction;

import net.francesbagual.matandoif.highorderfunction.stringasfunction.number.MyNumber;

public class HighorderFunction {
	
	private MyNumberOperation myNumberOperations = new MyNumberOperation();
	
	public MyNumber add(MyNumber a, MyNumber b) {
		return compute(a, b, "add");
	}

	public MyNumber multiply(MyNumber a, MyNumber b) {
		return compute(a, b, "mult");
	}

	private MyNumber compute(MyNumber a, MyNumber b, String operation) {
		return myNumberOperations.findOrThrow(operation).apply(a, b);
	}
	
	
	public static void main(String[] args) {
		HighorderFunction highorderFunction = new HighorderFunction();
		System.out.println(highorderFunction.add(new MyNumber(1), new MyNumber(1)));
		System.out.println(highorderFunction.multiply(new MyNumber(2), new MyNumber(2)));
	}

}




