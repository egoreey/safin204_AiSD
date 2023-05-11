package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BStarTree bStarTree = new BStarTree();
        int[] array = new int[10000];
        int count = 0;
        fileWriter();
        Scanner scanner = new Scanner(new File("input.txt"));
        while(scanner.hasNext()){
            String[] str = scanner.nextLine().split(",");
            array[count] = Integer.parseInt(str[0]);
            count++;
        }



        ArrayList<OperationResult> insertResults = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            long startTime = System.nanoTime();
            bStarTree.insert(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            insertResults.add(new OperationResult(key, duration, operationCount));
        }


        ArrayList<OperationResult> searchResults = new ArrayList<>();
        int[] b = pick100(array);
        for (int i = 0; i < b.length; i++) {
            int key = array[i];
            long startTime = System.nanoTime();
            boolean found = bStarTree.search(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            searchResults.add(new OperationResult(key, duration, operationCount, found));
        }

        ArrayList<OperationResult> deleteResults = new ArrayList<>();
        int[] c = pick1000(array);
        for (int i = 0; i < c.length; i++) {
            int key = array[i];
            long startTime = System.nanoTime();
            bStarTree.delete(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            deleteResults.add(new OperationResult(key, duration, operationCount));
        }


        double averageInsertTime = calculateAverageTime(insertResults);
        double averageDeleteTime = calculateAverageTime(deleteResults);
        double averageSearchTime = calculateAverageTime(searchResults);
        double averageInsertOperations = calculateAverageOperations(insertResults);
        double averageDeleteOperations = calculateAverageOperations(deleteResults);
        double averageSearchOperations = calculateAverageOperations(searchResults);

        System.out.println("Среднее время добавления: " + averageInsertTime + " наносек");
        System.out.println("Среднее время удаления: " + averageDeleteTime + " наносек");
        System.out.println("Среднее время поиска: " + averageSearchTime + " наносек");
        System.out.println("Среднее количество операций добавления: " + averageInsertOperations);
        System.out.println("Среднее количество операций удаления: " + averageDeleteOperations);
        System.out.println("Среднее количество операций поиска: " + averageSearchOperations);
    }

    private static int[] pick100(int[] a) {
        int[] b = new int[100];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[(int)(Math.random() * 10000)];
        }
        return b;
    }
    private static int[] pick1000(int[] a) {
        int[] b = new int[1000];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[(int)(Math.random() * 10000)];
        }
        return b;
    }

    private static double calculateAverageTime(ArrayList<OperationResult> results) {
        long totalDuration = 0;
        for (OperationResult result : results) {
            totalDuration += result.getDuration();
        }
        return (double) totalDuration / results.size();
    }

    private static double calculateAverageOperations(ArrayList<OperationResult> results) {
        int totalOperations = 0;
        for (OperationResult result : results) {
            totalOperations += result.getOperationCount();
        }
        return (double) totalOperations / results.size();
    }
    private static void fileWriter() throws IOException {
        File file = new File("input.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter("input.txt");
        for (int i = 0; i < 10000; i++) {
            fileWriter.write((int) (Math.random()*Integer.MAX_VALUE)
                    + ",");
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}

