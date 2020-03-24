package java8;

import java.util.function.Function;

@FunctionalInterface
interface Foo {
	String method(String string);// can only have one abstract method, can have multiple other method, but not
									// suggest to use

	default void defaultBaz() {
		System.out.println("");
	}

}

public class Functional_Interfaces {
	public static String add(String string, Foo foo) {
		return foo.method(string);
	}

	public static String add(String string, Function<String, String> fn) {
		return fn.apply(string);
	}

	public static void main(String[] args) {
		// first way to execute it
		Foo foo = parameter -> parameter + " from lambda";
		String result = Functional_Interfaces.add("Message", foo);
		System.out.println(result);

		// second way to execute it
		Function<String, String> fn = parameter -> parameter + " from lambda";
		String result1 = Functional_Interfaces.add("Message", fn);
		System.out.println(result1);
	}

}

//Just as with regular interfaces, extending different functional interfaces with the same default method can be problematic. 
//For example, assume that interfaces Bar and Baz both have a default method defaultCommon(). 
//In this case, you will get a compile-time error:
//interface Foo inherits unrelated defaults for defaultCommon() from types Baz and Bar...

// @FunctionalInterface
// interface FooExtended extends Baz, Bar {
// }

// @FunctionalInterface
// interface Baz {
// 	String method();

// 	default void defaultCommon() {
// 	}
// }

// @FunctionalInterface
// interface Bar {
// 	String method();

// 	default void defaultCommon() {
// 	}
// }

//To fix this.
//But be careful. Adding too many default methods to the interface is not a very good architectural decision. 

@FunctionalInterface
interface FooExtended1 extends Baz1, Bar1 {
	default void defaultCommon() {
		Baz1.super.defaultCommon();
	}
}

@FunctionalInterface
interface Baz1 {
	String method();

	default void defaultCommon() {
	}
}

@FunctionalInterface
interface Bar1 {
	String method();

	default void defaultCommon() {
	}
}
