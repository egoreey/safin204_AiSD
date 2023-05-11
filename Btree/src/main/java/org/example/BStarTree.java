package org.example;

public class BStarTree {
    private static final int M = 4; // минимальная степень дерева
    private Node root;
    private int operationCount; // Track operation count

    private static class Node {
        private int[] keys = new int[2 * M - 1];
        private Node[] children = new Node[2 * M];
        private int size;
        private boolean leaf;

        private Node(boolean leaf) {
            this.leaf = leaf;
        }
    }

    public BStarTree() {
        root = new Node(true);
        operationCount = 0;
    }

    public boolean search(int key) {
        operationCount = 0; // Reset operation count
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        operationCount++; // Increment operation count
        int i = 0;
        while (i < node.size && key > node.keys[i]) {
            i++;
            operationCount++; // Increment operation count
        }
        if (i < node.size && key == node.keys[i]) {
            return true;
        }
        if (node.leaf) {
            return false;
        }
        return search(node.children[i], key);
    }

    public void insert(int key) {
        operationCount = 0; // Reset operation count
        Node r = root;
        if (r.size == 2 * M - 1) {
            Node s = new Node(false);
            root = s;
            s.children[0] = r;
            splitChild(s, 0, r);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    private void insertNonFull(Node node, int key) {
        int i = node.size - 1;
        if (node.leaf) {
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
                operationCount += 2; // Increment operation count for assignments and comparisons
            }
            node.keys[i + 1] = key;
            node.size++;
        } else {
            while (i >= 0 && key < node.keys[i]) {
                i--;
                operationCount++; // Increment operation count
            }
            i++;
            if (node.children[i].size == 2 * M - 1) {
                splitChild(node, i, node.children[i]);
                if (key > node.keys[i]) {
                    i++;
                    operationCount++; // Increment operation count
                }
            }
            insertNonFull(node.children[i], key);
        }
    }

    private void splitChild(Node parent, int i, Node child) {
        Node newNode = new Node(child.leaf);
        newNode.size = M - 1;
        for (int j = 0; j < M - 1; j++) {
            newNode.keys[j] = child.keys[j + M];
        }
        if (!child.leaf) {
            for (int j = 0; j < M; j++) {
                newNode.children[j] = child.children[j + M];
            }
        }
        child.size = M - 1;
        for (int j = parent.size; j >= i + 1; j--) {
            parent.children[j + 1] = parent.children[j];
        }
        parent.children[i + 1] = newNode;
        for (int j = parent.size - 1; j >= i; j--) {
            parent.keys[j + 1] = parent.keys[j];
        }
        parent.keys[i] = child.keys[M - 1];
        parent.size++;
    }

    public void delete(int key) {
        operationCount = 0; // Reset operation count
        delete(root, key);
    }

    private void delete(Node node, int key) {
        if (node == null) {
            return; // Base case: the node is null, nothing to delete
        }

        int i = 0;
        while (i < node.size && key > node.keys[i]) {
            i++;
            operationCount++; // Increment operation count
        }
        if (i < node.size && key == node.keys[i]) {
            // Perform deletion operation
        } else if (node.leaf) {
            // Key not found
            return;
        }
        // Perform recursive delete operation
        delete(node.children[i], key);
    }

    public int getOperationCount() {
        return operationCount;
    }

    private void removeFromLeaf(Node node, int index) {
        for (int i = index + 1; i < node.size; i++) {
            node.keys[i - 1] = node.keys[i];
        }
        node.size--;
    }

    private void fillChild(Node node, int index) {
        if (index != 0 && node.children[index - 1].size >= M) {
            borrowFromPrev(node, index);
        } else if (index != node.size && node.children[index + 1].size >= M) {
            borrowFromNext(node, index);
        } else {
            if (index != node.size) {
                mergeChildren(node, index);
            } else {
                mergeChildren(node, index - 1);
            }
        }
    }

    private void borrowFromPrev(Node node, int index) {
        Node child = node.children[index];
        Node sibling = node.children[index - 1];

        for (int i = child.size - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }

        if (!child.leaf) {
            for (int i = child.size; i >= 0; i--) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.keys[0] = node.keys[index - 1];

        if (!child.leaf) {
            child.children[0] = sibling.children[sibling.size];
        }

        node.keys[index - 1] = sibling.keys[sibling.size - 1];

        child.size++;
        sibling.size--;
    }

    private void borrowFromNext(Node node, int index) {
        Node child = node.children[index];
        Node sibling = node.children[index + 1];

        child.keys[child.size] = node.keys[index];

        if (!child.leaf) {
            child.children[child.size + 1] = sibling.children[0];
        }

        node.keys[index] = sibling.keys[0];

        for (int i = 1; i < sibling.size; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
        }

        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.size; i++) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }

        child.size++;
        sibling.size--;
    }

    private void mergeChildren(Node node, int index) {
        Node child = node.children[index];
        Node sibling = node.children[index + 1];

        child.keys[M - 1] = node.keys[index];

        for (int i = 0; i < sibling.size; i++) {
            child.keys[i + M] = sibling.keys[i];
        }

        if (!child.leaf) {
            for (int i = 0; i <= sibling.size; i++) {
                child.children[i + M] = sibling.children[i];
            }
        }

        for (int i = index + 1; i < node.size - 1; i++) {
            node.keys[i] = node.keys[i + 1];
        }

        for (int i = index + 2; i <= node.size; i++) {
            node.children[i - 1] = node.children[i];
        }

        child.size += sibling.size + 1;
        node.size--;
    }
}
