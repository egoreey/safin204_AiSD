package org.example;

public class OperationResult {
    private int key;
    private long duration;
    private int operationCount;
    private boolean found;

    public OperationResult(int key, long duration, int operationCount) {
        this.key = key;
        this.duration = duration;
        this.operationCount = operationCount;
    }

    public OperationResult(int key, long duration, int operationCount, boolean found) {
        this.key = key;
        this.duration = duration;
        this.operationCount = operationCount;
        this.found = found;
    }

    public int getKey() {
        return key;
    }

    public long getDuration() {
        return duration;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public boolean isFound() {
        return found;
    }
}
