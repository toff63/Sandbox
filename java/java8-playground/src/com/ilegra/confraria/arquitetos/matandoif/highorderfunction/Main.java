package com.ilegra.confraria.arquitetos.matandoif.highorderfunction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("\\w+");
		Matcher m  = p.matcher("greeting: Hello World");
		System.out.println(m.groupCount());
		System.out.println(m.find());
		System.out.println(m.group());
	}
}
