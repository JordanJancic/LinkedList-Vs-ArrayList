/*
@author Jordan Jancic, 000269500
Date Completed: November 13, 2019
*/
package linkedListVsArrayList;

import java.util.Arrays;

/**
 * Generic Linked List class that always keeps the elements in order 
 * @author mark.yendt
 */
public class SortedLinkedList<T extends Comparable> {

  /**
   * The Node class stores a list element and a reference to the next node.
   */
    private final class Node<T extends Comparable> {
  
        T value;
        Node next;

        /**
         * Constructor.
         * @param val The element to store in the node.
         * @param n The reference to the successor node.
         */
        Node(T val, Node n)
        {
            value = val;
            next = n;
        }

        /**
         * Constructor.
         *
         * @param val The element to store in the node.
         */
        Node(T val)
        {
            // Call the other (sister) constructor.
            this(val, null);
        }
  }
  
  private Node first;  // list head

  /**
   * Constructor.
   */
    public SortedLinkedList()
    {
        first = null;
    }

  /**
   * The isEmpty method checks to see if the list is empty.
   *
   * @return true if list is empty, false otherwise.
   */
    public boolean isEmpty()
    {
        return first == null;
    }

  /**
   * The size method returns the length of the list.
   * @return The number of elements in the list.
   */
    public int size()
    {
          int count = 0;
          Node p = first;
          while (p != null) {
               // There is an element at p
              count++;
              p = p.next;
          }
          return count;
    }
    
    /*
    This was a get() method that I made, 
    however I did not end up using it.
    -Jordan Jancic
    */
//  public Node get(int i) {
//      int count = 0;
//      Node j = first;
//      while(j.next != null && count < i) {
//          j = j.next;
//          count++;
//      }
//      return j;
//  }

  /**
   * The add method adds an element at a position.
   * @param element The element to add to the list in sorted order.
   */
    private boolean runOnce = false;
    private boolean fixEmma = false;
    
    public void add(T element) {
      
        Node newNode = new Node(element);                                       //Creates a new Node object for the incoming element.                                      
      
        /*
        This if statement is used to ensure that the first
        element is added and set to first properly since
        the upcoming while loop can only run while the
        element's next element is not null.
        */
        if(!runOnce) {
            
            first = newNode; 
            runOnce = true;
            
        }
        /*
        If the first element was added, then the
        rest of the elements can now be added as well
        since the while loop will now execute normally.
        */
        else if(runOnce) {

            Node previousFirst = first;                                         //Tracks the previous first element.
            newNode.next = first;                                               //Sets the new node's next node to be the previous Node.
            first = newNode;                                                    //Sets the new node to be first Node.
            Node d = first;                                                     //Creates another node based on new node to prepare for iteration.
            
            /*
            I ran into an issue where the first two elements
            would not sort properly using my regular sorting
            algorith below, therefore, I created this code
            that addressed the issue and forces it to work.
            */
            if(!fixEmma && size() == 2) {

                if(first.value.compareTo(d.next.value) > 0) {                   //Checks if the first element is higher than the next.
                    Node temp = first;                                          //Stores value of first in a temporary node.
                    first = first.next;                                         //First becomes previous next.
                    first.next = temp;                                          //First's next becomes previous first.
                    temp.next = null;                                           //Sets next element's next to be null as it is now in the last position.
                    fixEmma = true;
                }

            }

            else {
                
                /*
                A while loop that iterates over all of the 
                Linked List elements. It only loops while
                the element.next is not null, therefore, this
                loop will avoid causing a NullPointerException.
                */
                while (d.next != null) {                                        

                    d = d.next;                                                 //Moves to next element by changing tempoary d node.

                    /*
                    Once again ensuring that the loop
                    will not encounter a NnullPointerException.
                    */
                    if(d.next != null) {

                        /*
                        Checks if the newly added Node is bigger than the 
                        current Node, but simultaneously also smaller than
                        the next Node, thus indicating that the new Node 
                        belongs in between both of the Nodes. This also
                        works with sorting an element that happens to be 
                        the same value as the previous first element in the Node list.
                        */
                        if(first.value.compareTo(d.value) > 0 && first.value.compareTo(d.next.value) <= 0) {

                            Node tempNode = d.next;                             //Stores temp node to track current next.
                            d.next = first;                                     //Sets current next to the new node.
                            d.next.next = tempNode;                             //Sets new node's next to previous remainder of list.
                            first = previousFirst;                              //Because the new value moved from first to another spot in the list, we simply assign the previous first to be the first again.

                      }
                        
                        /*
                        Checks if the new node is actually bigger than
                        the final element in the list. If it is, then it
                        is sent straight to the back and is assigned its 
                        next to be null.
                        */
                        else if(first.value.compareTo(d.next.value) > 0 && d.next.next == null) {
                            
                            d.next.next = first;                                //Assigns original null value of last Node to be first value.
                            d.next.next.next = null;                            //Assigns newly placed Node at back to have next of null.
                            first = previousFirst;                              //Assigns previous first to be current first element.
                            
                        }
                    }
                }
            }
        }
    }

  /**
   * The toString method computes the string representation of the list.
   * @return The string form of the list.
   */
    public String toString() {
 
        String listOfItems = "[";
        int counter = 0;                                                        //Tracks number of iterations for formatting purposes.
        Node p = first;                                                         //Copies first Node for iteration.

        /*
        Iterates over linked list and displays all the Node values.
        */
        while(p.next != null) {

            counter++;                                                          //Increments count by one.
            listOfItems += p.value + ",";                                       //Separates values by comma.
            p = p.next;                                                         //Moves to next Node.

            /*
            As long as the Node's next value
            is not equal to null, the element
            value will be added to the listOfItems
            String.
            */
            if(p.next == null) {
                listOfItems += p.value;
            }  
            
            /*
            Ensures that only 31 names are displayed
            per line.
            */
            if(counter % 31 == 0) {
                listOfItems += "\n";
            }
        }
        listOfItems += "]";

        return listOfItems;
    
    }
  /**
   * The remove method removes an element.
   * @param element The element to remove.
   * @return true if the remove succeeded, false otherwise.
   */
    public boolean remove(T element) {
  
        /*
        Checks if the first element happens to be
        the Node requiring deletion. If it is, then
        first simply becomes the next element and 
        the previos Node vanishes. Returns true if
        the element is found.
        */
        if(first.value.compareTo(element) == 0) {
            
            first = first.next;
            return true;
            
        }
        
        /*
        Otherwise, we iterate over the entire linked
        list and look for the matching Node. If it 
        is found, it is removed, and we return true.
        */
        else {
          
            Node p = first;                                                     //Assigns temporary node to p.

            /*
            Iterates over entire linked list.
            */
            while(p.next != null) {
                
                /*
                Checks if current Node's next value is the same 
                as the element in question. If it is, then 
                we assign the new next to be the element two
                next positions over, then the element in question
                ceases to exist.
                */
                if(p.next.value.compareTo(element) == 0) {
                    
                    p.next = p.next.next;
                    return true;
                }
                p = p.next;
            }
        }
        //Returns false if either above statements are not true
        //and nothing will get deleted.
        return false;
    
    }
}