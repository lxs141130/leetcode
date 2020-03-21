package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

public class C3_Collectors {
	List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");

	// 3.1. Collectors.toList()
	List<String> resultList = givenList.stream().collect(Collectors.toList());

	// 3.2. Collectors.toSet()
	Set<String> resultSet = givenList.stream().collect(Collectors.toSet());

	// 3.3. Collectors.toCollection(), only for not immutable class
	List<String> resultCollection = givenList.stream().collect(Collectors.toCollection(LinkedList::new));

	// 3.4. Collectors.toMap()
	Map<String, Integer> resultMap = givenList.stream().collect(Collectors.toMap(Function.identity(), String::length));

	// if there is duplicate with key, it immediately throws an
	// IllegalStateException.
	List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
	Map<String, Integer> errorMap = listWithDuplicates.stream()
			.collect(Collectors.toMap(Function.identity(), String::length));

	// In such cases with key collision, we should use toMap with another signature:
	Map<String, Integer> resultMapWithDup = givenList.stream()
			.collect(Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item));

	// 3.5. Collectors.collectingAndThen()
	// CollectingAndThen is a special collector that allows performing another
	// action on a result straight after collecting ends.
	// Let's collect Stream elements to a List instance and then convert the result
	// into an ImmutableList instance:
	List<String> result1 = givenList.stream()
			.collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));

	// 3.6. Collectors.joining()
	String resultStr1 = givenList.stream().collect(Collectors.joining());
	// "abbcccdd"

	String result = givenList.stream().collect(Collectors.joining(" "));
	// "a bb ccc dd"

	String resultStr2 = givenList.stream().collect(Collectors.joining(" ", "PRE-", "-POST"));
	// "PRE-a bb ccc dd-POST"

	// 3.7. Collectors.counting()
	Long resultCounting = givenList.stream().collect(Collectors.counting());// 4

	// 3.8. Collectors.summarizingDouble/Long/Int()
	DoubleSummaryStatistics result2 = givenList.stream().collect(Collectors.summarizingDouble(String::length));

	// in this case
//	assertThat(result2.getAverage()).isEqualTo(2);
//	assertThat(result2.getCount()).isEqualTo(4);
//	assertThat(result2.getMax()).isEqualTo(3);
//	assertThat(result2.getMin()).isEqualTo(1);
//	assertThat(result2.getSum()).isEqualTo(8);

	// 3.9. Collectors.averagingDouble/Long/Int()
	// We can get average string length by doing:
	Double result3 = givenList.stream().collect(Collectors.averagingDouble(String::length));

	// 3.10. Collectors.summingDouble/Long/Int()
	// We can get a sum of all string lengths by doing:
	Double result4 = givenList.stream().collect(Collectors.summingDouble(String::length));

	// 3.11. Collectors.maxBy()/minBy()
	// We can pick the biggest element by doing:
	Optional<String> result5 = givenList.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));

	// 3.12. Collectors.groupingBy()

	// We can group them by string length and store grouping results in Set
	// instances:
	Map<Integer, Set<String>> result6 = givenList.stream()
			.collect(Collectors.groupingBy(String::length, Collectors.toSet()));

	// 3.13. Collectors.partitioningBy()
	Map<Boolean, List<String>> result7 = givenList.stream().collect(Collectors.partitioningBy(s -> s.length() > 2));
	// output: {false=["a", "bb", "dd"], true=["ccc"]}

	// 3.14. Collectors.teeing()
	List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
	Optional<Integer> min = numbers.stream().collect(Collectors.minBy(Integer::compareTo));
	Optional<Integer> max = numbers.stream().collect(Collectors.maxBy(Integer::compareTo));
	// do something useful with min and max

	// with java12

//	numbers.stream().collect(Collectors.teeing(
//			Collectors.minBy(Integer::compareTo), // The first collector
//			Collectors.maxBy(Integer::compareTo), // The second collector
//			  (min, max) -> {}// Receives the result from those collectors and combines them
//			));

	// 4. Custom Collectors
}
