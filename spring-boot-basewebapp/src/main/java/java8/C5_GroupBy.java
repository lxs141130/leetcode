package java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class C5_GroupBy {
	public static void main(String[] args) {
		List<BlogPost> posts = Arrays.asList(new BlogPost("a", "frank", BlogPostType.NEWS, 6),
				new BlogPost("aaa", "frank", BlogPostType.NEWS, 10),
				new BlogPost("bbb", "frank", BlogPostType.GUIDE, 1),
				new BlogPost("ccc", "frank", BlogPostType.NEWS, 22), new BlogPost("ddd", "anna", BlogPostType.NEWS, 32),
				new BlogPost("eee", "anna", BlogPostType.REVIEW, 11),
				new BlogPost("eee", "kikia", BlogPostType.GUIDE, 5),
				new BlogPost("eee", "anna", BlogPostType.REVIEW, 101),
				new BlogPost("eee", "kikia", BlogPostType.NEWS, 124));

		// 2.2. Simple Grouping by a Single Column
		// To group the blog posts in the blog post list by their type:
		Map<BlogPostType, List<BlogPost>> postPerType = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType));

		// {"NEWS":[],"GUIDE":[],"REVIEW":[]}

		// {REVIEW=[java8.BlogPost@3b0143d3, java8.BlogPost@5a8e6209],
		// NEWS=[java8.BlogPost@4b4523f8, java8.BlogPost@731a74c,
		// java8.BlogPost@369f73a2, java8.BlogPost@1f28c152, java8.BlogPost@7d907bac],
		// GUIDE=[java8.BlogPost@7791a895, java8.BlogPost@3a5ed7a6]}
		System.out.println(postPerType);

		// 2.3. Grouping by With a Complex Map Key Type
		// To group by the blog posts in the list by the type and author combined in a
		// Tuple instance:
		Map<Tuple, List<BlogPost>> postPerTypeAndAuthor = posts.stream()
				.collect(Collectors.groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));

		// {"tuple1":[],"tuple2":[],"tuple3":[]}

		// {java8.Tuple@65e2dbf3=[java8.BlogPost@7791a895],
		// java8.Tuple@5b87ed94=[java8.BlogPost@7d907bac],
		// java8.Tuple@67b92f0a=[java8.BlogPost@4b4523f8],
		// java8.Tuple@4f970963=[java8.BlogPost@369f73a2],
		// java8.Tuple@7fac631b=[java8.BlogPost@5a8e6209],
		// java8.Tuple@7b49cea0=[java8.BlogPost@3b0143d3],
		// java8.Tuple@2b9627bc=[java8.BlogPost@731a74c],
		// java8.Tuple@61f8bee4=[java8.BlogPost@1f28c152],
		// java8.Tuple@887af79=[java8.BlogPost@3a5ed7a6]}
		System.out.println(postPerTypeAndAuthor);

		// 2.4. Modifying the Returned Map Value Type
		// Let's use the toSet() collector as the downstream collector and get a Set of
		// blog posts (instead of a List):
		Map<BlogPostType, Set<BlogPost>> postsPerType = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType, Collectors.toSet()));

		// {REVIEW=[java8.BlogPost@3b0143d3, java8.BlogPost@5a8e6209],
		// NEWS=[java8.BlogPost@1f28c152, java8.BlogPost@7d907bac,
		// java8.BlogPost@4b4523f8, java8.BlogPost@731a74c, java8.BlogPost@369f73a2],
		// GUIDE=[java8.BlogPost@7791a895, java8.BlogPost@3a5ed7a6]}
		System.out.println(postsPerType);

		// 2.5. Grouping by Multiple Fields
		// To group the List of BlogPosts first by author and then by type:
		Map<String, Map<BlogPostType, List<BlogPost>>> map = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getAuthor, Collectors.groupingBy(BlogPost::getType)));

		// {kikia={NEWS=[java8.BlogPost@7d907bac], GUIDE=[java8.BlogPost@3a5ed7a6]},
		// frank={NEWS=[java8.BlogPost@4b4523f8, java8.BlogPost@731a74c,
		// java8.BlogPost@369f73a2], GUIDE=[java8.BlogPost@7791a895]},
		// anna={REVIEW=[java8.BlogPost@3b0143d3, java8.BlogPost@5a8e6209],
		// NEWS=[java8.BlogPost@1f28c152]}}
		System.out.println(map);

		// 2.6. Getting the Average from Grouped Results
		// To find the average number of likes for each blog post type:
		Map<BlogPostType, Double> averageLikesPerType = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType, Collectors.averagingInt(BlogPost::getLikes)));

		// {NEWS=38.8, GUIDE=3.0, REVIEW=56.0}
		System.out.println(averageLikesPerType);

		// 2.7. Getting the Sum from Grouped Results
		// To calculate the total sum of likes for each type:
		Map<BlogPostType, Double> sumLikesPerType = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType, Collectors.summingDouble(BlogPost::getLikes)));

		// {NEWS=194.0, GUIDE=6.0, REVIEW=112.0}
		System.out.println(sumLikesPerType);

		// 2.8. Getting the Maximum or Minimum from Grouped Results
		// Another aggregation that we can perform is to get the blog post with the
		// maximum number of likes:
		Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType = posts.stream().collect(Collectors
				.groupingBy(BlogPost::getType, Collectors.maxBy(Comparator.comparingInt(BlogPost::getLikes))));

		// {NEWS=Optional[java8.BlogPost@2d98a335],
		// GUIDE=Optional[java8.BlogPost@7ef20235],
		// REVIEW=Optional[java8.BlogPost@4f3f5b24]}
		System.out.println(maxLikesPerPostType);

		// minimum number of likes:
		Map<BlogPostType, Optional<BlogPost>> minLikesPerPostType = posts.stream().collect(Collectors
				.groupingBy(BlogPost::getType, Collectors.minBy(Comparator.comparingInt(BlogPost::getLikes))));

		// {NEWS=Optional[java8.BlogPost@6d03e736],
		// GUIDE=Optional[java8.BlogPost@16b98e56],
		// REVIEW=Optional[java8.BlogPost@27d6c5e0]}
		System.out.println(minLikesPerPostType);

		// 2.9. Getting a Summary for an Attribute of Grouped Results
		// Let's calculate a summary for the likes attribute of the blog posts for each
		// different type:
		Map<BlogPostType, IntSummaryStatistics> likeStatisticsPerType = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType, Collectors.summarizingInt(BlogPost::getLikes)));

		// The IntSummaryStatistics object for each type contains the count, sum,
		// average, min and max values for the likes attribute. Additional summary
		// objects exist for double and long values.

		likeStatisticsPerType.values().stream().forEach(intSummaryStatistics -> {
			System.out.println("Average: " + intSummaryStatistics.getAverage());
			System.out.println("Max: " + intSummaryStatistics.getMax());
			System.out.println("Min: " + intSummaryStatistics.getMin());
			System.out.println("Sum: " + intSummaryStatistics.getSum());
			System.out.println("Count: " + intSummaryStatistics.getCount());
			System.out.println("--------------------------");
		});

		// 2.10. Mapping Grouped Results to a Different Type
		// Let's get a concatenation of the titles of the posts for each blog post type:
		Map<BlogPostType, String> postsPerType1 = posts.stream().collect(Collectors.groupingBy(BlogPost::getType,
				Collectors.mapping(BlogPost::getTitle, Collectors.joining(", ", "Post Title: [", "]"))));

		// {NEWS=Post Title: [a, aaa, ccc, ddd, eee], GUIDE=Post Title: [bbb, eee],
		// REVIEW=Post Title: [eee, eee]}
		System.out.println(postsPerType1);

		// 2.11. Modifying the Return Map Type
		EnumMap<BlogPostType, List<BlogPost>> postsPerType2 = posts.stream().collect(
				Collectors.groupingBy(BlogPost::getType, () -> new EnumMap<>(BlogPostType.class), Collectors.toList()));

		// {NEWS=[java8.BlogPost@6d03e736, java8.BlogPost@568db2f2,
		// java8.BlogPost@378bf509, java8.BlogPost@5fd0d5ae, java8.BlogPost@2d98a335],
		// REVIEW=[java8.BlogPost@27d6c5e0, java8.BlogPost@4f3f5b24],
		// GUIDE=[java8.BlogPost@16b98e56, java8.BlogPost@7ef20235]}
		System.out.println(postsPerType2);

		// 3. Concurrent Grouping by Collector
		ConcurrentMap<BlogPostType, List<BlogPost>> postsPerType3 = posts.parallelStream()
				.collect(Collectors.groupingByConcurrent(BlogPost::getType));

		// {NEWS=[java8.BlogPost@6d03e736, java8.BlogPost@5fd0d5ae,
		// java8.BlogPost@378bf509, java8.BlogPost@2d98a335, java8.BlogPost@568db2f2],
		// GUIDE=[java8.BlogPost@16b98e56, java8.BlogPost@7ef20235],
		// REVIEW=[java8.BlogPost@4f3f5b24, java8.BlogPost@27d6c5e0]}
		System.out.println(postsPerType3);
	}

	@Test
	public void givenList_whenSatifyPredicate_thenMapValueWithOccurences() {
		List<Integer> numbers = List.of(1, 2, 3, 5, 5);

		Map<Integer, Long> result = numbers.stream().filter(val -> val > 3)
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		// {5=2}
		assertEquals(1, result.size());

		result = numbers.stream()
				.collect(Collectors.groupingBy(i -> i, Collectors.filtering(val -> val > 3, Collectors.counting())));
		// {1=0, 2=0, 3=0, 5=2}a
		assertEquals(4, result.size());
	}
	
	@Test
	public void givenListOfBlogs_whenAuthorName_thenMapAuthorWithComments() {
	    Blog blog1 = new Blog("1", Arrays.asList("Nice", "Very Nice"));
	    Blog blog2 = new Blog("2", Arrays.asList("Disappointing", "Ok", "Could be better"));
	    List<Blog> blogs = List.of(blog1, blog2);
	         
	    Map<String,  List<List<String>>> authorComments1 = blogs.stream()
	     .collect(Collectors.groupingBy(Blog::getAuthorName, 
	       Collectors.mapping(Blog::getComments, Collectors.toList())));
	        
	    //{1=[[Nice, Very Nice]], 2=[[Disappointing, Ok, Could be better]]}
	    assertEquals(2, authorComments1.size());
	    assertEquals(2, authorComments1.get("1").get(0).size());
	    assertEquals(3, authorComments1.get("2").get(0).size());
	 
	    Map<String, List<String>> authorComments2 = blogs.stream()
	      .collect(Collectors.groupingBy(Blog::getAuthorName, 
	        Collectors.flatMapping(blog -> blog.getComments().stream(), 
	        Collectors.toList())));
	 
	    //{1=[Nice, Very Nice], 2=[Disappointing, Ok, Could be better]}
	    assertEquals(2, authorComments2.size());
	    assertEquals(2, authorComments2.get("1").size());
	    assertEquals(3, authorComments2.get("2").size());
	}
}

class Blog {
	private String authorName;
	private List<String> comments;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public Blog(String authorName, List<String> comments) {
		super();
		this.authorName = authorName;
		this.comments = comments;
	}

	// constructor and getters

}

class BlogPost {
	String title;
	String author;
	BlogPostType type;
	int likes;

	public BlogPost(String title, String author, BlogPostType type, int likes) {
		super();
		this.title = title;
		this.author = author;
		this.type = type;
		this.likes = likes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BlogPostType getType() {
		return type;
	}

	public void setType(BlogPostType type) {
		this.type = type;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}

enum BlogPostType {
	NEWS, REVIEW, GUIDE
}

class Tuple {
	BlogPostType type;
	String author;

	public Tuple(BlogPostType type, String author) {
		super();
		this.type = type;
		this.author = author;
	}

}