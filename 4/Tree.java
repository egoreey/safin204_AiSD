package hw4;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree {
    Node root;

    public Tree() {
        this.root = null;
    }

    private static class Node {

        int value;
        Node left, right;

        public Node(int value) {
            this.value=value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public void addWhile(int value) {
        Node node = root;
        while (node != null) {
            if (node.value > value) node = node.left;
            else node = node.right;
        }
    }

    private Node addRec(Node node, int n) {
        if (node == null) {
            return new Node(n);
        }
        if (node.value > n) node.left = addRec(node.left, n);
        if (node.value < n) node.right = addRec(node.right, n);
        return node;
    }

    public void add(int value) {
        root = addRec(root, value);
    }

    public boolean find(int value) {
        return contains(root, value);
    }

    public boolean contains(Node node, int n) {
        if (node == null) return false;
        if (node.value < n) return contains(node.right, n);
        if (node.value > n) return contains(node.left, n);
        return true;
    }

    public void traversDeep(Node root) {
        if (root != null) {
            traversDeep(root.left);
            traversDeep(root.right);
            System.out.println(root.value);
        }
    }

    public void traversWide(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.value + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
