package java8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author franksun
 * 
 *         Mar 19, 2020
 * 
 */
public class test {
	public static void main(String[] args) {

		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));

		Collector<Product, ?, LinkedList<Product>> toLinkedList = Collector.of(LinkedList::new, LinkedList::add,
				(first, second) -> {
					first.addAll(second);
					return first;
				});

		LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);
		System.out.println(linkedListOfPersons);
		
		Stream<Product> streamOfCollection1 = productList.parallelStream();
		boolean isParallel = streamOfCollection1.isParallel();
		boolean bigPrice = streamOfCollection1
		  .map(product -> product.getPrice() * 12)
		  .anyMatch(price -> price > 200);
		
		System.out.println(isParallel);
		System.out.println(bigPrice);
	}

	
}

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
