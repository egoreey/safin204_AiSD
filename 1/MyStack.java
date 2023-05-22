package hw1;
import java.util.Arrays;

public class MyStack {
    public int[] array;
    public int current;

    public MyStack(int size) {
        this.array = new int[size];
        current = -1;
    }

    public boolean add(int elem) {
        if (current == array.length - 1) {
            return false;
        }
        array[current + 1] = elem;
        current++;
        return true;
    }

    public boolean addAll(MyStack stack) {
        if (current == array.length - 1) {
            return false;
        }
        for (int i = stack.current; i >= 0; i--) {
            add(stack.array[i]);
        }
        return true;
    }

    public int getFirst() {
        if (current > -1) {
            return array[current];
        }
        return -1;
    }

    public int removeFirst() {
        int elem = -1;
        if (current != -1) {
            elem = getFirst();
            array[current] = 0;
            current--;
        }
        return elem;
    }

    public void clear() {
        current = -1;
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }

    public boolean isEmpty() {
        return current == -1 && getFirst() == -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
