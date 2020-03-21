package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
		
	}
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