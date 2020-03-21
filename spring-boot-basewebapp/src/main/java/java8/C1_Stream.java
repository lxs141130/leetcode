package java8;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author franksun
 * 
 *         Mar 19, 2020
 * 
 */
public class C1_Stream {
	// 2. Stream Creation
	// 2.1. Empty Stream
	Stream<String> streamEmpty = Stream.empty();

	public Stream<String> streamOf(List<String> list) {
		return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}

	// 2.2. Stream of Collection
	Collection<String> collection = Arrays.asList("a", "b", "c");
	Stream<String> streamOfCollection = collection.stream();

	// 2.3. Stream of Array
	Stream<String> streamOfArray = Stream.of("a", "b", "c");
	String[] arr = new String[] { "a", "b", "c" };
	Stream<String> streamOfArrayFull = Arrays.stream(arr);
	Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

	// 2.4. Stream.builder()

	// When builder is used the desired type should be additionally specified in the
	// right part of the statement,
	// otherwise the build() method will create an instance of the Stream<Object>:
	Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

	// 2.5. Stream.generate()

	// The generate() method accepts a Supplier<T> for element generation.
	// As the resulting stream is infinite,
	// developer should specify the desired size or the generate() method will work
	// until it reaches the memory limit:
	Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

	// The code above creates a sequence of ten strings with the value – “element".

	// 2.6. Stream.iterate()
	Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

	// first 40, second 42, then keeping +2 till 20 times, need specify the maxSize
	// as well

	// 2.7. Stream of Primitives
	IntStream intStream = IntStream.range(1, 3);// not include end
	LongStream longStream = LongStream.rangeClosed(1, 3);// include end

	Random random = new Random();
	DoubleStream doubleStream = random.doubles(3);// 3 is stream size

	// 2.8. Stream of String
	IntStream streamOfChars = "abc".chars();// as there is no charStream, we use IntStream instead

	// The following example breaks a String into sub-strings according to specified
	// RegEx:
	Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

	// 2.9. Stream of File
	Path path = Paths.get("");
	// Stream<String> streamOfStrings = Files.lines(path);
	// Stream<String> streamWithCharset = Files.lines(path,
	// Charset.forName("UTF-8"));

	// 3. Referencing a Stream
	// it is very important to remember that Java 8 streams can't be reused.
	Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
	Optional<String> anyElement1 = stream.findAny();
	Optional<String> firstElement1 = stream.findFirst();// IllegalStateException throw in run time

	// correct way to use it
	List<String> elements = Stream.of("a", "b", "c").filter(element -> element.contains("b"))
			.collect(Collectors.toList());
	Optional<String> anyElement = elements.stream().findAny();
	Optional<String> firstElement = elements.stream().findFirst();

	// 4. Stream Pipeline

	// To perform a sequence of operations over the elements of the data source and
	// aggregate their results,
	// three parts are needed
	// – the source
	// - intermediate operation(s), Only one terminal operation can be used per
	// stream.
	// - a terminal operation.
	Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1);
	Stream<String> twiceModifiedStream = stream.skip(1).map(element -> element.substring(0, 3));

	// 5. Lazy Invocation

	// Intermediate operations are lazy. This means that they will be invoked only
	// if it is necessary for the terminal operation execution.
	List<String> list = Arrays.asList("abc1", "abc2", "abc3");
	Optional<String> stream1 = list.stream().filter(element -> {
		System.out.println("filter() was called");
		return element.contains("2");
	}).map(element -> {
		System.out.println("map() was called");
		return element.toUpperCase();
	}).findFirst();

	// Resulting log shows that the filter() method was called twice and the map()
	// method just once.

	// 6. Order of Execution
	// keep such methods as skip(), filter(), distinct() at the top of your stream
	// pipeline.
	private long counter;

	private void wasCalled() {
		counter++;
	}

	long size = list.stream().map(element -> {
		wasCalled();// called three times
		return element.substring(0, 3);
	}).skip(2).count();
	// intermediate operations which reduce the size of the stream should be placed
	// before operations which are applying to each element.
	long size2 = list.stream().skip(2).map(element -> {
		wasCalled();// only one time
		return element.substring(0, 3);
	}).count();

	// 7. Stream Reduction

	// The API has many terminal operations which aggregate a stream to a type or to
	// a primitive,
	// for example, count(), max(), min(), sum()

	// 7.1. The reduce() Method
	// three variations of this method
	// 1. identity
	// – the initial value for an accumulator or a default value if a
	// stream is empty and there is nothing to accumulate;

	// 2. accumulator
	// – a function which specifies a logic of aggregation of
	// elements. As accumulator creates a new value for every step of reducing, the
	// quantity of new values equals to the stream's size and only the last value is
	// useful. This is not very good for the performance.

	// 3. combiner
	// – a function which aggregates results of the accumulator. Combiner is called
	// only in a parallel mode to reduce results of accumulators from different
	// threads.
	OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b);// 6(1 + 2 + 3)
	int reducedTwoParams = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);// 16(10 + 1 +2 + 3)

	// 16(10 + 1 +2 + 3), and no system.out called
	int reducedParams = Stream.of(1, 2, 3).reduce(10, (a, b) -> a + b, (a, b) -> {
		System.out.println("combiner was called");
		return a + b;
	});
	// to make it called, use parallel mode
	// accumulator -> 10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;
	// combiner ->12 + 13 = 25; 25 + 11 = 36
	int reducedParallel = Arrays.asList(1, 2, 3).parallelStream().reduce(10, (a, b) -> a + b, (a, b) -> {
		System.out.println("combiner was called");
		return a + b;
	});

	// 7.2. The collect() Method
	class Product {
		int price;
		String name;

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Product(int price, String name) {
			super();
			this.price = price;
			this.name = name;
		}
	}

	List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
			new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));

	// Converting a stream to the Collection (Collection, List or Set):
	List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());

	// Reducing to String:
	String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));

	// Processing the average value of all numeric elements of the stream:
	double averagePrice = productList.stream().collect(Collectors.averagingInt(Product::getPrice));

	// Processing the sum of all numeric elements of the stream:
	int summingPrice = productList.stream().collect(Collectors.summingInt(Product::getPrice));

	// Grouping of stream’s elements according to the specified function:
	// In the example above the stream was reduced to the Map which groups all
	// products by their price.
	Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
			.collect(Collectors.groupingBy(Product::getPrice));

	// Dividing stream’s elements into groups according to some predicate:
	Map<Boolean, List<Product>> mapPartioned = productList.stream()
			.collect(Collectors.partitioningBy(product -> product.getPrice() > 15));

	// Pushing the collector to perform additional transformation:
	Set<Product> unmodifiableSet = productList.stream()
			.collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));

	// Custom collector:
	Collector<Product, ?, LinkedList<Product>> toLinkedList = 
		Collector.of(LinkedList::new, LinkedList::add,
			(first, second) -> {
				first.addAll(second);
				return first;
			});

	LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);
	
	
	//Parallel Streams
	
	Stream<Product> streamOfCollection1 = productList.parallelStream();
	boolean isParallel = streamOfCollection1.isParallel();
	boolean bigPrice = streamOfCollection1
	  .map(product -> product.getPrice() * 12)
	  .anyMatch(price -> price > 200);
}
