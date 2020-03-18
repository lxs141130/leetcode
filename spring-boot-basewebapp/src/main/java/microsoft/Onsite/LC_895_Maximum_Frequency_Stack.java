package microsoft.Onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author franksun
 * 
 * Feb 18, 2020
 * 
 */
public class LC_895_Maximum_Frequency_Stack {
	DLinkedNode head;
    DLinkedNode tail;
    int mostFreq;
    Map<Integer, List<Integer>> freqStack;
    Map<Integer, Integer> times;
    
    public LC_895_Maximum_Frequency_Stack() {
        freqStack = new HashMap<>();
        times = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        mostFreq = 0;
        head.next = tail;
        tail.prev = head;
    }
    
//     head  5 4 7 5 tail
// 4, 1  || 1 ->[4, 5, 7]
// 7, 1  || 
// 5, 2  || 
    
    public void push(int x) {
        DLinkedNode node = new DLinkedNode(x, x);
        head.next.prev = node;
        node.prev = head;
        node.next = head.next;
        head.next = node;
         
        int time = times.getOrDefault(x, 0);
        times.put(x, time + 1); 
        
        if (time > 0) {
        	List<Integer> list = freqStack.get(time);
            list.remove((Integer)node.key);
            freqStack.put(time, list);
        }
        time++;
        List<Integer> list = freqStack.getOrDefault(time, new ArrayList<>());
        list.add(node.key);
        freqStack.put(time, list);
        
        mostFreq = Math.max(mostFreq, time);
    }
    
    public int pop() {
        DLinkedNode node = head.next;
        if (node == tail) {
            return -1;
        }
        int time = times.get(node.key);
        while (time != mostFreq) {
            node = node.next;
            time = times.get(node.key);
        }
        
        //remove node from DLinkedNode
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        time--;
        times.put(node.key, time);

        List<Integer> list = freqStack.get(mostFreq);
        list.remove((Integer)node.key);
        freqStack.put(mostFreq, list);

        if (list.size() == 0) {
            freqStack.remove(mostFreq);
            mostFreq--;  
            list = freqStack.getOrDefault(mostFreq, new ArrayList<>());
            list.add(node.key);
            freqStack.put(mostFreq, list); 
        } else {
            list = freqStack.getOrDefault(mostFreq - 1, new ArrayList<>());
            list.add(node.key);
            freqStack.put(mostFreq - 1, list); 
        }
        
        
        return node.val;
    }
    
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;
        
        DLinkedNode(){};
        DLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    public static void main(String[] args) {
    	LC_895_Maximum_Frequency_Stack stack = new LC_895_Maximum_Frequency_Stack();
    	stack.push(5);
    	stack.push(7);
    	stack.push(5);
    	stack.push(7);
    	stack.push(4);
    	stack.push(5);
    	
    	//5
    	System.out.println(stack.pop());
    	//7
    	System.out.println(stack.pop());
    	//5
    	System.out.println(stack.pop());
    	//4
    	System.out.println(stack.pop());
	}
}

