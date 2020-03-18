package microsoft.Onsite;

/**
 * @author franksun
 * 
 * Feb 26, 2020
 * 
 */
public class Geeks_Josephus_Problem {
	DLList start;
	int len;
	
	//n people, kill every k interval
	//like if n = 5, k = 2. kill 2, 4, 1, 5, return 3
	//n = 7, k = 3. kill 3, 6, 2, 7, 5, 1, return 4
	
	public int josephus(int n, int k) {
		if (n == 1) {
			return 1;
		}
		if (k == 1) {
			return n;
		}
		buildCycle(n);
		DLList temp = start;
		while(len > 1) {
			if(k > len) {
				k%=len;
			}
			int i = k;
			while(i > 0) {
				temp = temp.next;
				i--;
			}
			deleteNode(temp.prev);
			len--;
		}
		return temp.val;
	}
	
	private void deleteNode(DLList node) {
		if (len == 2) {
			return;
		}
		DLList prev = node.prev;
		DLList next = node.next;
		prev.next = next;
		next.prev = prev;
		
	}

	private void buildCycle(int n) {
		len = n;
		start = new DLList(1);
		DLList temp = start;
		for (int i = 2; i <= n; i++) {
			DLList node = new DLList(i); 
			temp.next = node;
			node.prev = temp;
			temp = temp.next;
		}
		temp.next = start;
		start.prev = temp;		
	}
	
	public static void main(String[] args) {
		Geeks_Josephus_Problem jp = new Geeks_Josephus_Problem();
		System.out.println(jp.josephus(5, 2));
		
		System.out.println(jp.josephus(7, 3));
	}
	
	class DLList {
		DLList prev;
		DLList next;
		int val;
		
		public DLList() {}
		public DLList(int val) {
			this.val = val;
		}
	}
}
