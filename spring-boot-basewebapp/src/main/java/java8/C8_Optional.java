package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class C8_Optional {
	public static void main(String[] args) {
		List<Optional<String>> listOfOptionals = Arrays.asList(Optional.empty(), Optional.of("foo"), Optional.empty(),
				Optional.of("bar"));

		// 2. Using filter()
		// One of the options in Java 8 is to filter out the values with
		// Optional::isPresent and then perform mapping with the Optional::get function
		// to extract values:
		List<String> filteredList = listOfOptionals.stream().filter(Optional::isPresent).map(Optional::get)
				.collect(Collectors.toList());

		// [foo, bar]
		System.out.println(filteredList);

		// 3. Using flatMap()
		List<String> filteredList1 = listOfOptionals.stream()
				.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty()).collect(Collectors.toList());

		// [foo, bar]
		System.out.println(filteredList1);

		// Alternatively, you could apply the same approach using a different way of
		// converting an Optional to Stream:
		List<String> filteredList2 = listOfOptionals.stream().flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
				.collect(Collectors.toList());

		// [foo, bar]
		System.out.println(filteredList2);

		// 4. Java 9's Optional::stream
		List<String> filteredList3 = listOfOptionals.stream().flatMap(Optional::stream).collect(Collectors.toList());
		// [foo, bar]
		System.out.println(filteredList3);
	}
}
