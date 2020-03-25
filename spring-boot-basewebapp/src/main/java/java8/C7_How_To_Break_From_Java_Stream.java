package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class C7_How_To_Break_From_Java_Stream {

	// 2. Java 9's Stream.takeWhile()
	public static void main(String[] args) {
		// output, it is break while it counters "elephant"
//		cat
//		dog
		Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck").takeWhile(n -> n.length() % 2 != 0)
				.forEach(System.out::println);

		System.out.println("---------------------");
		// it is equals to
		List<String> list = Arrays.asList("cat", "dog", "elephant", "fox", "rabbit", "duck");
		for (int i = 0; i < list.size(); i++) {
			String item = list.get(i);
			if (item.length() % 2 == 0) {
				break;
			}
			System.out.println(item);
		}
	}
	
	
}
