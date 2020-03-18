package microsoft.Onsite;

import java.util.Stack;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_42_Trapping_Water {
	public int trap(int[] height) {
		Stack<Integer> stack = new Stack<>();
		int area = 0;
		int i = 0;
		while (i < height.length) {
			// to calculate, need to have a V look structure,
			// which mean inside stack, it is decreased(when to push)
			// anytime counter a increase case, we do pop and calculate area
			if (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
				int top = stack.pop();
				int width = !stack.isEmpty() ? i - stack.peek() - 1 : i;
				int h = 0;
				if (!stack.isEmpty()) {
					h = Math.min(height[i], height[stack.peek()]) - height[top];
				}
				area += width * h;
			} else {
				stack.push(i++);
			}
		}
		return area;
	}

	public int trap1(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;
		int res = 0;
		while (i <= j) {
			leftMax = Math.max(height[i], leftMax);
			rightMax = Math.max(height[j], rightMax);
			if (leftMax > rightMax) {
				res += rightMax - height[j--];
			} else {
				res += leftMax - height[i++];
			}
		}
		return res;
	}
}
