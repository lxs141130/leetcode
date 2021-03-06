package indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author franksun
 * 
 *         Feb 9, 2020
 * 
 */
public class LC_380_Insert_Delete_GetRandom {
	Map<Integer, Integer> map;
	List<Integer> list;
	Random r;

	/** Initialize your data structure here. */
    public LC_380_Insert_Delete_GetRandom() {
        list = new ArrayList<>();
        map = new HashMap<>();
        r = new Random();
    }

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		}
		map.put(val, list.size());
		list.add(list.size(), val);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}

		int lastElement = list.get(list.size() - 1);
		int idx = map.get(val);
		//if this ele is already located at the last, just remove it
        //else update the indexToRemove with lastEle in list
        //update lastEle with indexToRemveo in map
		if (idx != list.size() - 1) {
			list.set(idx, lastElement);
			map.put(lastElement, idx);
		}
		// delete the last element
		list.remove(list.size() - 1);
		map.remove(val);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return list.get(r.nextInt(list.size()));
	}
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet(); boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val); int param_3 = obj.getRandom();
 */