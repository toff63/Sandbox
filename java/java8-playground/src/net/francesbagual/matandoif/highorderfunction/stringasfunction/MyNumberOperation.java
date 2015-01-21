package net.francesbagual.matandoif.highorderfunction.stringasfunction;

import net.francesbagual.matandoif.highorderfunction.stringasfunction.lib.Operations;
import net.francesbagual.matandoif.highorderfunction.stringasfunction.number.MyNumber;
import net.francesbagual.matandoif.highorderfunction.stringasfunction.number.MyNumberOperator;

public class MyNumberOperation extends Operations<MyNumberOperator>{

	@Override
	protected Class<MyNumberOperator> getOperationClass() {
		return MyNumberOperator.class;
	}

	private MyNumberOperator add = (MyNumber a, MyNumber b) -> new MyNumber(a.value + b.value);
	private MyNumberOperator mult =  (MyNumber a, MyNumber b) -> new MyNumber(a.value * b.value);
	private MyNumberOperator divide =  (MyNumber a, MyNumber b) -> new MyNumber(a.value / b.value);


}