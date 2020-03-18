package microsoft.Onsite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author franksun
 * 
 *         Feb 20, 2020
 * 
 */
public class LC_692_Top_K_Frequent_Words {
	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> countMap = new HashMap<>();
		for (String word : words) {
			countMap.put(word, countMap.getOrDefault(word, 0) + 1);
		}
		PriorityQueue<String> heap = new PriorityQueue<String>(
				(w1, w2) -> countMap.get(w1).equals(countMap.get(w2)) ? w2.compareTo(w1)
						: countMap.get(w1) - countMap.get(w2));

		for (String word : countMap.keySet()) {
			heap.offer(word);
			if (heap.size() > k)
				heap.poll();
		}

		List<String> ans = new ArrayList<>();
		while (!heap.isEmpty()) {
			ans.add(heap.poll());
		}
		Collections.reverse(ans);
		return ans;
	}
}
