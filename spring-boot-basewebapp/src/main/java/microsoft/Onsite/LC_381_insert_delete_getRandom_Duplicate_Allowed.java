package microsoft.Onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_381_insert_delete_getRandom_Duplicate_Allowed {
	Map<Integer, Set<Integer>> dict;
	List<Integer> list;
	Random rand;

	/** Initialize your data structure here. */
	public LC_381_insert_delete_getRandom_Duplicate_Allowed() {
		dict = new HashMap<>();
		list = new ArrayList<>();
		rand = new Random();
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */

	// insert 1,1,2
	// dict
	// 1 - 1
	// 2 - 0
	// list
	// 0 1
	// 2 1
	public boolean insert(int val) {
		if (!dict.containsKey(val)) {
			Set<Integer> set = new HashSet<>();
			set.add(list.size());
			list.add(list.size(), val);
			dict.put(val, set);
			return true;
		} else {
			Set<Integer> set = dict.get(val);
			set.add(list.size());
			list.add(list.size(), val);
			dict.put(val, set);
			return false;
		}
	}

	/**
	 * Removes a value from the collection. Returns true if the collection contained
	 * the specified element.
	 */
	public boolean remove(int val) {
		if (!dict.containsKey(val)) {
			return false;
		} else {
			//firstly, we need to remove this val from the original set anyway
			Set<Integer> set = dict.get(val);
			int indexToRemove = set.iterator().next();
			set.remove(indexToRemove);
			if (set.size() == 0) {
				dict.remove(val);
			}
			//if the element is already the last element, just remove from list will be okay
            //else
            //  we need to 
            //    1.remove last index from tail set
            //    2. add indexToRemove to tail set as lastEle is put to indexToRemove now
            //    3. update indexToRemove with lastEle in list
			if (indexToRemove < list.size() - 1) {
				int lastEle = list.get(list.size() - 1);
				Set<Integer> tailSet = dict.get(lastEle);
				// as we are gonna remove last element, we remove the index for it
				tailSet.remove(list.size() - 1);
				tailSet.add(indexToRemove);
				// update the removeIndex with lastEle, then we can just delete last ele
				list.set(indexToRemove, lastEle);
			}
			list.remove(list.size() - 1);
			return true;
		}
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		int index = rand.nextInt(list.size());
		return list.get(index);
	}
}
