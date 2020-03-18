package indeed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.example.code.TreeNode;

/**
 * @author franksun
 * 
 *         Feb 9, 2020
 * 
 */
public class LC_297_Serialize_and_Deserialize_Binary_Tree {
	// pre order traversal
	// root, left, right
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	public void buildString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("#,");
		} else {
			sb.append(node.val).append(",");
			buildString(node.left, sb);
			buildString(node.right, sb);
		}
	}

	// Decodes your encoded data to tree.
	// pre order, root, left, right
	public TreeNode deserialize(String data) {
		Queue<String> nodes = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(",")));
		return buildTree(nodes);
	}

	public TreeNode buildTree(Queue<String> nodes) {
		String val = nodes.poll();
		if (val.equals("#")) {
			return null;
		} else {
			TreeNode node = new TreeNode(Integer.parseInt(val));
			node.left = buildTree(nodes);
			node.right = buildTree(nodes);
			return node;
		}
	}

	public static void main(String[] args) {
		LC_297_Serialize_and_Deserialize_Binary_Tree sd = new LC_297_Serialize_and_Deserialize_Binary_Tree();
		TreeNode root = new TreeNode(5);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(2);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(3);
		TreeNode node6 = new TreeNode(1);

		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.left = node5;
		node2.right = node6;

		TreeNode node = sd.deserialize(sd.serialize(root));
		sd.inOrder(node);

//		TreeNode node1111 = sd.deserialize(sd.serialize(root));
//		sd.inOrder(node1111);
	}

	// Function to print tree nodes in InOrder fashion
	public void inOrder(TreeNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.val + " ");
			inOrder(root.right);
		}
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
