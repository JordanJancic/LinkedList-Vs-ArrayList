/*
@author Jordan Jancic, 000269500
Date Completed: November 13, 2019
*/
package linkedListVsArrayList;

/*
Linked List Discussion.

RESULTS:

    The time required to add 18756 String elements to a Linked List = 1507142 us
    The time required to remove 9378 String elements from a Linked List = 263418 us
    The time required to add 18756 Integer elements to a Linked List = 2496308 us
    The time required to remove 9378 Integer elements from a Linked List = 386963 us
    The time required to add 18756 elements to an (Built-in) Array List = 2404555 us
    The time required to remove 9378 elements from an (Built-in) Array List = 6511 us

*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class LinkedListVsArrayList {

    public static void main(String[] args) {

        final int NUMBER_OF_NAMES = 18756;                                      //The amount of names in the bnames file.
        final String filename = "resources/bnames.txt";                         //Tracks the bnames file locaton.
        final String[] names = new String[NUMBER_OF_NAMES];                     //Creates a String array to hold the names from the file.

        // May be useful for selecting random words to remove
        Random random = new Random();                                           //Generates a random number.
        int r = random.nextInt((NUMBER_OF_NAMES - 1) + 1) + 1;                  //Stores random number in variable r.

        // Read in all of the names 
        try {
            Scanner fin = new Scanner(new File(filename));                      //Generates new Scanner object to prepare intake of file elements.
            
            /*
            Iterates over file elements and stores
            them in names array.
            */
            for (int i=0; i<NUMBER_OF_NAMES; i++) {                             
                names[i] = fin.next(); 
        }
        fin.close();                                                            //Closes scanner;
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());          //Catches file intake error and outputs error message.
            System.exit(-1);
        }

        // Use the system to build the linked List

        // 1. Create the linkedList and add all items in Array
        SortedLinkedList<Comparable> linkedList_String = new SortedLinkedList<>();  //Generates new Linked List based on SortedLinkedList object.
        
        long start = System.nanoTime();                                         //Stores start time.
        
        /*
        Iterates over the names array and adds
        each name element to the newly made
        String LinkedList.
        */
        for (int i=0; i<NUMBER_OF_NAMES;i++) {          
            linkedList_String.add(names[i]);                                    
        }

        long stop = System.nanoTime();                                          //Tracks stop time.


        // 2. Remove half the items and time the code.
        int half = NUMBER_OF_NAMES / 2;                                         //Takes the amount of names from the file and divides it by two.
        
        long start2 = System.nanoTime();                                        //Tracks start time.
        
        /*
        Iterates over the String Linked List and
        removes each Node associated in the element
        from the names array.
        */
        for (int i = 0; i < half; i++) {
            linkedList_String.remove(names[i]);
        }
        
        long stop2 = System.nanoTime();                                         //Tracks stop time.

        
        /*
        The Demo of the SortedLinkedList class
        working with Integer datatype.
        */
        System.out.println("Demo of LinkedList with Integer: ");                
        SortedLinkedList<Comparable> linkedList_IntDemo = new SortedLinkedList<>(); //Creates a LinkedList of Integer elements.
        
        //An int array that stores a short amount of 31 integers.
        int[] integersDemoSet = {345, 7, 2, 43, 7657, 67, 5, 5, 4, 67, 89, 6, 5, 23, 12, 2, 3, 56, 78, 9, 90, 675, 54, 4, 54, 546, 89, 0, 43, 324, 4};

        /*
        Iterates over int array and demonstrates that 
        it is currently not in sorted order.
        */
        for (int i = 0; i < integersDemoSet.length; i++) {
            System.out.print(integersDemoSet[i] + " ");
        }
        System.out.println("");
        
        /*
        Iterates over the new Integer LinkedList and 
        adds the elements to it from the integer array.
        */
        for (int i = 0; i < integersDemoSet.length; i++) {
            linkedList_IntDemo.add(integersDemoSet[i]);
        }
        
        System.out.println(linkedList_IntDemo.toString());                      //Outputs the results of the sorted adding process.

        
        //add integers to linked list
        int[] integers = new int[18756];                                        //An int array that will store 18756 int elements.
        
        /*
        Generates 18756 random integers from 1 - 5000
        and adds them to the newly made integer array 
        of 18756 elements.
        */
        for (int i = 0; i < integers.length; i++) {
            Random randomInt = new Random();
            integers[i] = randomInt.nextInt(5000 - 1) + 1;
        }
        System.out.println("");
        
        SortedLinkedList<Comparable> linkedList_Int = new SortedLinkedList<>(); //Generates new Integer Linked List.
        
        long start3 = System.nanoTime();                                        //Tracks start time.
        
        /*
        Adds the newly made large set of integers 
        and adds them to the newly made Integer
        Linked List.
        */
        for (int i=0; i < integers.length ;i++) {
            linkedList_Int.add(integers[i]);
        }
        
        long stop3 = System.nanoTime();                                         //Tracks stop time.

        //remove integers from linked list
        int halfIntegers = integers.length / 2;                                 //Stores the number of elements in the integer array and divides is by two.
        
        long start4 = System.nanoTime();                                        //Tracks start time.
        
        /*
        Iterates over the Integer Linked List and removes 
        half of the items inside.
        */
        for (int i = 0; i < half; i++) {
            linkedList_String.remove(names[i]);
        }
        
        long stop4 = System.nanoTime();                                         //Tracked stop time.

        // 4. Build a standard ArrayList of String - Remember to sort list after each element is added
        //    Time this code.
        //    Use this timing data to compare add against SortedLinkedList in discussion
        //    Remove the half the elements and time again.  
        //    Use this timing data to compare remove against SortedLinkedList in discussion
        
        //Add to arrayList
        ArrayList<String> arrayList_String = new ArrayList<>();                 //Creates a new String Array List.
        
        long start5 = System.nanoTime();                                        //Tracks start time.
        
        /*
        Iterates over the names array and adds each
        name element to the newly made String
        Array List.
        */
        for (int i=0; i<NUMBER_OF_NAMES; i++) {
            arrayList_String.add(names[i]);
            Collections.sort(arrayList_String);
        }
        
        long stop5 = System.nanoTime();                                         //Tracks stop time.

        
        int arrayListHalf = arrayList_String.size() / 2;                        //Takes the size of the String array list and divides it by two.                   
        
        long start6 = System.nanoTime();                                        //Tracks start time.
        
        /*
        Iterates over the Array List and removes
        half of the elements from within.
        */
        for (int i = 0; i < arrayListHalf; i++) {
            arrayList_String.remove(i);
        }
        
        long stop6 = System.nanoTime();                                         //Tracks stop time.
        
        /*
        Several print statements showing the amount of time
        taken for each operation to execute based on the 
        start and stop times documented earlier.
        */
        System.out.printf("The time required to add %d String elements to a Linked List = %d us\n", NUMBER_OF_NAMES, (stop - start) / 1000);

        System.out.printf("The time required to remove %d String elements from a Linked List = %d us\n", half, (stop2 - start2) / 1000);

        System.out.printf("The time required to add %d Integer elements to a Linked List = %d us\n", integers.length, (stop3 - start3) / 1000);

        System.out.printf("The time required to remove %d Integer elements from a Linked List = %d us\n", halfIntegers, (stop4 - start4) / 1000);

        System.out.printf("The time required to add %d elements to an (Built-in) Array List = %d us\n", NUMBER_OF_NAMES, (stop5 - start5) / 1000);

        System.out.printf("The time required to remove %d elements from an (Built-in) Array List = %d us\n", arrayListHalf, (stop6 - start6) / 1000);
    }   
}