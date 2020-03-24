// package java8;


// import static org.hamcrest.CoreMatchers.anyOf;
// import static org.hamcrest.CoreMatchers.is;
// import static org.junit.Assert.assertThat;
// import static org.junit.Assert.assertTrue;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import org.junit.Test;

// public class C2_Stream_Method {
	
// 	//2. Using the Stream.findAny()
// 	@Test
// 	public void createStream_whenFindAnyResultIsPresent_thenCorrect() {
// 	    List<String> list = Arrays.asList("A","B","C","D");
	 
// 	    Optional<String> result = list.stream().findAny();
	 
// 	    assertTrue(result.isPresent());
// 	    assertThat(result.get(), anyOf(is("A"), is("B"), is("C"), is("D")));
// 	}
	
// 	//In a non-parallel operation, it will most likely return the first element in the Stream but there is no guarantee for this.
	
// 	//3. Using the Stream.findFirst()
// 	@Test
// 	public void createStream_whenFindFirstResultIsPresent_thenCorrect() {
	 
// 	    List<String> list = Arrays.asList("A", "B", "C", "D");
	 
// 	    Optional<String> result = list.stream().findFirst();
	 
// 	    assertTrue(result.isPresent());
// 	    assertThat(result.get(), is("A"));
// 	}
		
// }
