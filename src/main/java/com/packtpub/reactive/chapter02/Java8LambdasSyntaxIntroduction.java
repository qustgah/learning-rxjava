package com.packtpub.reactive.chapter02;

import com.packtpub.reactive.common.Program;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Some of examples of using lambdas.
 * 
 * @author meddle
 */
public class Java8LambdasSyntaxIntroduction implements Program {

	/**
	 * Functional interface with method with one argument, that returns a value.
	 * 
	 * @author meddle
	 */
	interface Mapper<V, M> {
		M map(V value);
	}

	/**
	 * Functional interface with method with one argument, that doesn't return anything - just executes actions.
	 * 
	 * @author meddle
	 */
	interface Action<V> {
		void act(V value);
	}

	public static <V, M> List<M> map(List<V> list, Mapper<V, M> mapper) {
		List<M> mapped = new ArrayList<M>(list.size());
		for (V v : list) {
			mapped.add(mapper.map(v));
		}

		return mapped;
	}

	public static <V> void act(List<V> list, Action<V> action) {
		for (V v : list) {
			action.act(v);
		}
	}

	@Override
	public String name() {
		return "Introduction to the new syntax and semantics";
	}

	@Override
	public int chapter() {
		return 2;
	}

	@Override
	public void run() {
		// A list of numbers.
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// A list of numbers powered by 2, using the custom map function.
		List<Integer> mapped = map(numbers, new Mapper<Integer, Integer>() {
			@Override
			public Integer map(Integer value) {
				return value * value;
			}
		});

		// Reference to the System.out's print method.
		Consumer<String> print = System.out::println;
		print.andThen(print).accept("Here we go!"); // 2x Here we go!

		// Defining a new lambda that turns a value to string and concatenates '!' to it.
		Function<Integer, String> map = new Function<Integer, String>() {
			@Override
			public String apply(Integer value) {
				return (value + "!");
			}
		};
		print.accept(map.apply(5)); // 5!

		// Another map lambda
		Mapper<Integer, String> toMessage = (value) -> "We are happy to present to you : "
				+ (value) + "!";
				
		// Passing a lambda to a function, receiving interface with only one method.		
		List<String> messages = map(mapped, toMessage);

		// Predicate that tests if a number is odd as a lambda.
		Predicate<Integer> odd = (value) -> value % 2 != 0;
		print.accept(odd.test(5) + "");

		// Passing reference to a function, to method that receives functional interface.
		act(messages, System.out::println);

		// Using map and act with lambdas.
		List<String> lambdaMapped = map(numbers, v -> {
			return "The square of " + v + " is " + (v * v) + "!";
		});
		act(lambdaMapped, v -> System.out.println(v));
		testTime();
	}

	void testTime(){
		/*localDate*/
		//LocalDate
		LocalDate localDate = LocalDate.now(); //获取本地日期
		localDate = LocalDate.ofYearDay(2014, 200); // 获得 2014 年的第 200 天
		System.out.println(localDate.toString());//输出：2014-07-19
		localDate = LocalDate.of(2014, Month.SEPTEMBER, 10); //2014 年 9 月 10 日
		System.out.println(localDate.toString());//输出：2014-09-10
//LocalTime
		LocalTime localTime = LocalTime.now(); //获取当前时间
		System.out.println(localTime.toString());//输出当前时间
		localTime = LocalTime.of(10, 20, 50);//获得 10:20:50 的时间点
		System.out.println(localTime.toString());//输出: 10:20:50
//Clock 时钟
		Clock clock = Clock.systemDefaultZone();//获取系统默认时区 (当前瞬时时间 )
		long millis = clock.millis();//
	}


	
	public static void main(String[] args) {
		new Java8LambdasSyntaxIntroduction().run();
	}
}
