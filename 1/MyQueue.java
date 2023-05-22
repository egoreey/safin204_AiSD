package hw1;

public class MyQueue {
    public MyStack stack1;
    public MyStack stack2;

    public MyQueue(int size) {
        this.stack1 = new MyStack(size);
        this.stack2 = new MyStack(size);
    }

    public boolean add(int elem) {
        return stack1.add(elem);
    }

    public int getFirst() {
        replace(stack1, stack2);
        return stack2.getFirst();
    }

    public int removeFirst() {
        replace(stack1, stack2);
        int save = stack2.getFirst();
        stack2.removeFirst();
        return save;
    }

    public static void replace(MyStack s1, MyStack s2) {
        if (s2.isEmpty()) {
            s2.addAll(s1);
            s1.clear();
        }
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public String toString() {
        return "stack1=" + stack1 + "\nstack2=" + stack2;
    }
}
