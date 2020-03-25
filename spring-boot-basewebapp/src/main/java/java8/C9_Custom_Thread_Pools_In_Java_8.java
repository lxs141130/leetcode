package java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

public class C9_Custom_Thread_Pools_In_Java_8 {

	// 2. Parallel Stream
	// Let's start with a simple example – calling the parallelStream method on any
	// of the Collection types – which will return a possibly parallel Stream:
	@Test
	public void givenList_whenCallingParallelStream_shouldBeParallelStream() {
		List<Long> aList = new ArrayList<>();
		Stream<Long> parallelStream = aList.parallelStream();

		assertTrue(parallelStream.isParallel());
	}

	// The default processing that occurs in such a Stream uses the
	// ForkJoinPool.commonPool(), a Thread Pool shared by the entire application.

	// 3. Custom Thread Pool
	//	We can actually pass a custom ThreadPool when processing the stream.
	//	The following example lets have a parallel Stream use a custom Thread Pool to calculate the sum of long values from 1 to 1,000,000, inclusive:
	@Test
	public void giveRangeOfLongs_whenSummedInParallel_shouldBeEqualToExpectedTotal() 
	  throws InterruptedException, ExecutionException {
	     
	    long firstNum = 1;
	    long lastNum = 1_000_000;
	 
	    List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
	      .collect(Collectors.toList());
	 
	    ForkJoinPool customThreadPool = new ForkJoinPool(4);
	    long actualTotal = customThreadPool.submit(
	      () -> aList.parallelStream().reduce(0L, Long::sum)).get();
	  
	    assertEquals((lastNum + firstNum) * lastNum / 2, actualTotal);
	}
}
