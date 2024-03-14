package primary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FindAllPermutationsOfArray {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 8, 4, 9));
        printAllDistinctSums(list);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the desired sum (or -1 to exit): ");
            int desiredSum = scanner.nextInt();
            if (desiredSum == -1) {
                break;
            }
            findAllPermutationsWithSum(list, desiredSum);
        }

        System.out.println("Program exited.");
        scanner.close();
    }

    private static void printAllDistinctSums(List<Integer> list) {
        List<List<Integer>> allPermutations = iteratePermutations(list, 0, new ArrayList<>());
        Set<Integer> distinctSums = new HashSet<>();

        for (List<Integer> permutation : allPermutations) {
            distinctSums.add(sum(permutation));
        }

        List<Integer> sortedSums = new ArrayList<>(distinctSums);
        Collections.sort(sortedSums);

        System.out.println("Distinct sums of permutations in ascending order:");
        for (int sum : sortedSums) {
            System.out.println(sum);
        }
    }

    private static void findAllPermutationsWithSum(List<Integer> list, int desiredSum) {
        Collections.sort(list);
        List<List<Integer>> allPermutations = iteratePermutations(list, 0, new ArrayList<>());

        // Print permutations with sum equal to desiredSum
        boolean found = false;
        for (List<Integer> permutation : allPermutations) {
            int sum = sum(permutation);
            if (sum == desiredSum) {
                System.out.println("Permutation with sum " + desiredSum + ": " + permutation);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No permutation found with sum " + desiredSum);
        }
    }

    private static List<List<Integer>> iteratePermutations(List<Integer> list, int start, ArrayList<Integer> current) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        allPermutations.add(new ArrayList<>(current));

        for (int i = start; i < list.size(); i++) {
            current.add(list.get(i));
            allPermutations.addAll(iteratePermutations(list, i + 1, current));
            current.remove(current.size() - 1);
        }

        return allPermutations;
    }

    private static int sum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
}
