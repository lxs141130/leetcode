package java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class C6_ToMap {

	static List<Book> bookList = Arrays.asList(new Book("The Fellowship of the Ring", 1954, "0395489318"),
			new Book("The Two Towers", 1954, "0345339711"), new Book("The Return of the King", 1955, "0618129111"));

	// For this scenario we'll use the following overload of the toMap() method:

	// Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
	// Function<? super T, ? extends U> valueMapper)

	// 2. List to Map
	public static Map<String, String> listToMap(List<Book> books) {
		return books.stream().collect(Collectors.toMap(Book::getIsbn, Book::getName));
	}

	@Test
	public void whenConvertFromListToMap() {
		assertTrue(listToMap(bookList).size() == 3);
	}

	// 3. Solving Key Conflicts
	public Map<Integer, Book> listToMapWithDupKeyError(List<Book> books) {
		return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity()));
	}

	@Test(expected = IllegalStateException.class)
	public void whenMapHasDuplicateKey_without_merge_function_then_runtime_exception() {
		listToMapWithDupKeyError(bookList);
	}

	// To resolve it, we need to use a different method with an additional
	// parameter, the mergeFunction:

//	Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
//			  Function<? super T, ? extends U> valueMapper,
//			  BinaryOperator<U> mergeFunction)

	// in the case of a collision, we keep the existing entry:
	public Map<Integer, Book> listToMapWithDupKey(List<Book> books) {
		return books.stream().collect(
				Collectors.toMap(Book::getReleaseYear, Function.identity(), (existing, replacement) -> existing));
	}

	// Or, in other words, we get first-win behavior:
	@Test
	public void whenMapHasDuplicateKeyThenMergeFunctionHandlesCollision() {
		Map<Integer, Book> booksByYear = listToMapWithDupKey(bookList);
		assertEquals(2, booksByYear.size());
		assertEquals("0395489318", booksByYear.get(1954).getIsbn());
	}

	// 4. Other Map Types
	// By default, a toMap() method will return a HashMap.
	// But can we return different Map implementations? The answer is yes:

//	Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
//			  Function<? super T, ? extends U> valueMapper,
//			  BinaryOperator<U> mergeFunction,
//			  Supplier<M> mapSupplier)

	// 4.1. List to ConcurrentMap
	public Map<Integer, Book> listToConcurrentMap(List<Book> books) {
		return books.stream().collect(
				Collectors.toMap(Book::getReleaseYear, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
	}

	// Let's go on and test our code:

	@Test
	public void whenCreateConcurrentHashMap() {
		assertTrue(listToConcurrentMap(bookList) instanceof ConcurrentHashMap);
	}

	// 4.2. Sorted Map
	public TreeMap<String, Book> listToSortedMap(List<Book> books) {
		return books.stream()
				.collect(Collectors.toMap(Book::getName, Function.identity(), (o1, o2) -> o1, TreeMap::new));
	}

	@Test
	public void whenMapisSorted() {
		assertTrue(listToSortedMap(bookList).firstKey().equals("The Fellowship of the Ring"));
	}
}

class Book {
	private String name;
	private int releaseYear;
	private String isbn;

	public Book(String name, int releaseYear, String isbn) {
		super();
		this.name = name;
		this.releaseYear = releaseYear;
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}