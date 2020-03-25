package java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class C10_Map_VS_FlatMap {
	@Test
	public void test() {
		// 2. Map and Flatmap in Optionals
		// The map() method works well with Optional – if the function returns the exact
		// type we need:
		Optional<String> s = Optional.of("test");
		// The map() method works well with Optional – if the function returns the exact
		// type we need:
		assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));

		// However, in more complex cases we might be given a function that returns an
		// Optional too. In such cases using map() would lead to a nested structure, as
		// the map() implementation does an additional wrapping internally.
		assertEquals(Optional.of(Optional.of("STRING")), Optional.of("string").map(s1 -> Optional.of("STRING")));

		// As we can see, we end up with the nested structure Optional<Optional<String>>

		// That's exactly what flatMap() helps us to do:
		assertEquals(Optional.of("STRING"), Optional.of("string").flatMap(s2 -> Optional.of("STRING")));

		// 3. Map and Flatmap in Streams
		// Both methods work similarly for Optional.
		// The map() method wraps the underlying sequence in a Stream instance, whereas the flatMap() method allows avoiding nested Stream<Stream<R>> structure.
		List<String> myList = Stream.of("a", "b")
				  .map(String::toUpperCase)
				  .collect(Collectors.toList());
		assertEquals(Arrays.asList("A", "B"), myList);
		
		//	Let's see how it works:
		List<List<String>> list = Arrays.asList(
				  Arrays.asList("a"),
				  Arrays.asList("b"));
		
		//[[a], [b]]
		System.out.println(list);
		
		//	Now, let's use a flatMap():
		//	 [a, b]
		System.out.println(list
				  .stream()
				  .flatMap(Collection::stream)
				  .collect(Collectors.toList()));
		
	}
}
