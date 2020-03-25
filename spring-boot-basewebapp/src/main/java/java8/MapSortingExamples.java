package java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author franksun
 * 
 * Mar 10, 2020
 * 
 */
public class MapSortingExamples {
	 
    public static void main(String[] args) {
        System.out.println("\nSorting using Java8 streams\n");
         
        sortByValueJava8Stream();
    }
 
    private static void sortByValueJava8Stream() 
    {
        Map<String, Integer> unSortedMap = getUnSortedMap();
         
        System.out.println("Unsorted Map : " + unSortedMap);
 
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
         
        System.out.println("Sorted Map   : " + sortedMap);
         
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
         
        System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
        
        LinkedHashMap<String, Integer> SortedByKeyMap = new LinkedHashMap<>();
        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> SortedByKeyMap.put(x.getKey(), x.getValue()));
         
        System.out.println("Sorted By Key Map   : " + SortedByKeyMap);
        
        LinkedHashMap<String, Integer> SortedByKeyReverseMap = new LinkedHashMap<>();
        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(x -> SortedByKeyReverseMap.put(x.getKey(), x.getValue()));
         
        System.out.println("Sorted By Key Reverse Map   : " + SortedByKeyReverseMap);
        
        method1();
    }
    
    public static void method1(){
    	 
        // a Map with string keys and integer values
        Map<String, Integer> budget = new HashMap<>();
        budget.put("clothes", 120);
        budget.put("grocery", 150);
        budget.put("transportation", 100);
        budget.put("utility", 130);
        budget.put("rent", 1150);
        budget.put("miscellneous", 90);
     
        System.out.println("map before sorting: " + budget);
     
        // let's sort this map by values first
        Map<String, Integer> sorted = budget
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                    LinkedHashMap::new));
     
        System.out.println("map after sorting by values: " + sorted);
     
        // above code can be cleaned a bit by using method reference
        sorted = budget
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));
     
        // now let's sort the map in decreasing order of value
        sorted = budget
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));
     
        System.out.println("map after sorting by values in descending order: "
            + sorted);
      }
 
    private static Map<String, Integer> getUnSortedMap() 
    {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("alex", 1);
        unsortMap.put("david", 2);
        unsortMap.put("elle", 3);
        unsortMap.put("charles", 4);
        unsortMap.put("brian", 5);
        return unsortMap;
    }
}
