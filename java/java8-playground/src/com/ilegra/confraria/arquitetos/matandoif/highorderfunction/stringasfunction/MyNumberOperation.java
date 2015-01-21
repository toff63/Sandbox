package com.ilegra.confraria.arquitetos.matandoif.highorderfunction.stringasfunction;

import com.ilegra.confraria.arquitetos.matandoif.highorderfunction.stringasfunction.lib.Operations;
import com.ilegra.confraria.arquitetos.matandoif.highorderfunction.stringasfunction.number.MyNumber;
import com.ilegra.confraria.arquitetos.matandoif.highorderfunction.stringasfunction.number.MyNumberOperator;

public class MyNumberOperation extends Operations<MyNumberOperator>{

	@Override
	protected Class<MyNumberOperator> getOperationClass() {
		return MyNumberOperator.class;
	}

	private MyNumberOperator add = (MyNumber a, MyNumber b) -> new MyNumber(a.value + b.value);
	private MyNumberOperator mult =  (MyNumber a, MyNumber b) -> new MyNumber(a.value * b.value);
	private MyNumberOperator divide =  (MyNumber a, MyNumber b) -> new MyNumber(a.value / b.value);


}