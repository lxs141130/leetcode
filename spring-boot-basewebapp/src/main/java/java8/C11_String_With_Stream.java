package java8;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class C11_String_With_Stream {

	// 2. Joining Strings With the Stream API
	public static String join(String[] arrayOfString) {
		return Arrays.asList(arrayOfString).stream()
				// .map(...)
				.collect(Collectors.joining(","));
	}

	public static String joinWithPrefixPostfix(String[] arrayOfString) {
		return Arrays.asList(arrayOfString).stream()
				// .map(...)
				.collect(Collectors.joining(",", "[", "]"));
	}

	@Test
	public void givenArray_transformedToStream_convertToString() {
		String[] programmingLanguages = { "java", "python", "nodejs", "ruby" };
		String expectation = "java,python,nodejs,ruby";

		String result = join(programmingLanguages);
		assertEquals(result, expectation);
	}

	// 3. Splitting Strings With Stream API
	public static List<String> split(String str) {
		return Stream.of(str.split(",")).map(elem -> new String(elem)).collect(Collectors.toList());
	}

	@Test
	public void givenString_transformedToStream_convertToList() {
		String programmingLanguages = "java,python,nodejs,ruby";

		List<String> expectation = new ArrayList<>();
		expectation.add("java");
		expectation.add("python");
		expectation.add("nodejs");
		expectation.add("ruby");

		List<String> result = split(programmingLanguages);

		assertEquals(result, expectation);
	}

	public static List<Character> splitToListOfChar(String str) {
		return str.chars().mapToObj(item -> (char) item).collect(Collectors.toList());
	}

	// 4. String array to Map with Stream API
	public static Map<String, String> arrayToMap(String[] arrayOfString) {
		return Arrays.asList(arrayOfString).stream().map(str -> str.split(":"))
				.collect(Collectors.toMap(str -> str[0], str -> str[1]));
	}

	@Test
	public void givenStringArray_transformedToStream_convertToMap() {

		String[] programming_languages = new String[] { "language:java", "os:linux", "editor:emacs" };

		Map<String, String> expectation = new HashMap<>();
		expectation.put("language", "java");
		expectation.put("os", "linux");
		expectation.put("editor", "emacs");

		Map<String, String> result = arrayToMap(programming_languages);
		assertEquals(result, expectation);

	}

	public static void main(String[] args) {
		String[] arrayOfString = { "i", "love", "code" };
		// i,love,code
		System.out.println(join(arrayOfString));
		// [i,love,code]
		System.out.println(joinWithPrefixPostfix(arrayOfString));
		// [i, love, code]
		System.out.println(split("i,love,code"));
		// [i, ,, l, o, v, e, ,, c, o, d, e]
		System.out.println(splitToListOfChar("i,love,code"));

	}
}
