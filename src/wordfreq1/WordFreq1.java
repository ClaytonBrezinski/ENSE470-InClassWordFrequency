/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordfreq1;
//imported libraries

import java.io.*;
import java.util.Map;
import java.util.Scanner;
//new library used for tree
import java.util.TreeMap;

public class WordFreq1
{

    public static void main(String[] args) throws IOException
    {
        //Variables
        //create TreeMap
        TreeMap<String, Integer> tree = new TreeMap<String, Integer>();
        String fileName = "sample.txt";
        String fileInput = "";

        //buffer input so we can read it in properly and read it in
        BufferedReader input;
        fileName = getInputFile(args);

        //records current time of run for use later in the code
        long startTime = System.currentTimeMillis();

        //prints name of file to make sure user typed in the correct file
        if (fileName != null)
        {
            System.err.println(fileName);
        }

        fileInput = scanInput(fileName, fileInput);

        String[] items = manipulateFileInput(fileInput);

        int[] itemValue = getItemValues(items);

        insertWord(items, tree, itemValue);

        Long endTime = printTree(tree);
        printTime(startTime, endTime);
    }

    private static String getInputFile(String[] args) throws IOException
    {
        BufferedReader input;
        String fileName;
        //prompt for selecting text file
        System.err.println("Please enter the txt file you would like to use: ");
        //checks for input from the command line so that it can run the program properly
        if (args.length != 1)
        {
            input = new BufferedReader(new InputStreamReader(System.in));
            fileName = input.readLine();
            input.close();
        }
        else
        {
            fileName = args[0];
        }
        return fileName;
    }

    private static String scanInput(String fileName, String fileInput) throws FileNotFoundException
    {
        //create scanner for use filtering and modifying input text
        Scanner sc = new Scanner(new File(fileName));
        //inputs text from scanner into a string variable for manipulation
        while (sc.hasNextLine())
        {
            fileInput += sc.nextLine();
            fileInput += "\n";
        }
        //used for testing to check if the scanner read the input properly

        //System.out.println(fileInput);
        //closes the scanner
        sc.close();
        return fileInput;
    }

    private static int[] getItemValues(String[] items)
    {
        int[] itemValue = new int[items.length];
        for (int i = 0; i < items.length; i++)
        {
            itemValue[i] = 0;
        }
        return itemValue;
    }

    private static String[] manipulateFileInput(String fileInput)
    {
        //this section manipulates the string to properly manipulate it to lower case
        fileInput = fileInput.toLowerCase();
        //replaces all number and special characters (excluding apostrophes) with spaces
        fileInput = fileInput.replaceAll("[0-9\\W&&[^']]", " ");
        //divides words up using split, which chooses words based on spaces in between them (doesn't matter how many)
        //also trims of the spaces of the start and end of the words.
        String[] items = fileInput.trim().split(" +");
        return items;
    }

    private static void printTime(long startTime, Long endTime)
    {
        try
        {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        //takes the two times measured, subtracts the last time from the first time then displays the difference to show the total runtime in ms
        System.err.println("Total execution time: " + (endTime - startTime) + " ms");
    }

    private static Long printTree(TreeMap<String, Integer> tree)
    {
        //calls the print function of the tree to print all of the sections in a in order traversal
        System.err.println("Here are the contents of your tree:");
        System.err.println();
        //tree.print();
        for (Map.Entry<String, Integer> entry : tree.entrySet())
        {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        //records the current runtime of the program and returns it
        return System.currentTimeMillis();
    }

    //calls insert function of the tree class to store the words from the string array into the nodes
    private static void insertWord(String[] items, TreeMap<String, Integer> tree, int[] itemValue)
    {

        for (int i = 0; i < items.length; i++)
        {
            //tree.insert(items[i]);
            if (tree.containsKey(items[i]))
            {
                tree.replace(items[i], tree.get(items[i]) + 1);
            }
            else
            {
                tree.put(items[i], itemValue[i] + 1);
            }
        }
    }
}
