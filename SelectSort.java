import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program reads arrays of integers from an input file,
 * sorts each array using selection sort,
 * and writes the sorted arrays to a file named "output.txt".
 *
 * @author Johnnatan Yasin Medina
 * @version 1.0
 * @since 2025-05-09
 */
final class SelectSort {

    /**
     * This is to satisfy the style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private SelectSort() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Main method.
     *
     * @param args Unused.
     */
    public static void main(final String[] args) {
        // Make sure the user provides the input file name
        if (args.length < 1) {
            System.out.println("Usage: java SelectionSort <inputFileName>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = "output.txt";

        // List to store arrays read and sorted from the file
        ArrayList<int[]> arrayList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(inputFileName))) {

            // Read each line of the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.trim().split("\\s+");

                int[] numbers = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    numbers[i] = Integer.parseInt(tokens[i]);
                }

                sortEm(numbers);
                arrayList.add(numbers);
            }

            // Write the sorted arrays to the output file
            try (FileWriter writer = new FileWriter(outputFileName)) {
                for (int[] arr : arrayList) {
                    for (int num : arr) {
                        writer.write(num + " ");
                    }
                    writer.write("\n");
                }
            }

            // Confirmation message
            System.out.println("Sorting done. Output displayed in output.txt.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: The file \""
             + inputFileName + "\" was not found.");
        } catch (IOException e) {
            System.out.println("Error writing to output file.");
        }
    }

    /**
     * Sorts an array using selection sort algorithm.
     *
     * @param ar The array to sort.
     */
    private static void sortEm(final int[] ar) {
        for (int i = 0; i < ar.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < ar.length; j++) {
                if (ar[j] < ar[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = ar[minIndex];
            ar[minIndex] = ar[i];
            ar[i] = temp;
        }
    }
}
