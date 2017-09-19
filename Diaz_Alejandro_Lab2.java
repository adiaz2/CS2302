//Author: Alejandro Diaz
//Assignment: CS2302 Lab 2
//Instructor: Diego Aguirre
//T.A.: Ismael Villanueva
//Last Modification: 09/18/2017

/*
Creates a program that creates a linked list and gives the
user the option to run the following algorithms on it:

* Bubble Sort
* Merge Sort
* Duplicate Check
* Element Comparison

The program then reports the runtimes of each algorithm as well as the results so they can be verified
*/


import java.util.Random;
import java.util.*;
import java.io.*;

public class Diaz_Alejandro_Lab2{
	public static void main(String[] args){
		try{
			//create a scanner to prompt the user for the size of 
			//the linked list and the range of numbers being used
			Scanner console = new Scanner(System.in);
			int n = 1;
			int m = 1;
			boolean validChoice = false;
			while(!validChoice){
				System.out.println("Input the length of the linked list");
				n = console.nextInt();
				System.out.println("Input the max value for random numbers");
				m = console.nextInt();
				if(n>0 && m>0)
					validChoice = true;
				else
					System.out.println("\nPlease pick positive numbers\n");
			}

			//builds a list of size and range specified by user
			iNode node = buildList(n, m);
			
			validChoice = false;
			double startTime;
			//Allow user to select which algorithm to perform on the node
			while(!validChoice){
				//present the user with options for which algorithm to run
				System.out.println("Which algorithm would you like to use to sort the list?" +
									"\n1) Bubble Sort\n2)Merge Sort\n3)Don't Sort, just search for duplicates" + 
									"\n4)Don't Sort, just compare the items in the list");
			
				int algorithmChoice = console.nextInt();//get user input
			
				if(algorithmChoice == 1){
					//runs bubble sort algorithm and checks for duplicates and calculates both runtimes
					startTime = System.nanoTime();
					node = bubbleSortList(node);
					System.out.println("Bubble Sort Runtime: " + (System.nanoTime()-startTime));
					startTime = System.nanoTime();
					checkForDuplicatesSorted(node);
					System.out.println("Duplicate Check Runtime: " + (System.nanoTime()-startTime));
					validChoice = true;
				}
				else if(algorithmChoice == 2){
					//runs merge sort algorithm and checks for duplicates and calculates both runtimes
					startTime = System.nanoTime();
					node = mergeSortList(node);
					System.out.println("Merge Sort Runtime: " + (System.nanoTime()-startTime));
					startTime = System.nanoTime();
					checkForDuplicatesSorted(node);
					System.out.println("Duplicate Check Runtime: " + (System.nanoTime()-startTime));
					validChoice = true;
				}
				else if(algorithmChoice == 3){
					//checks for duplicates on an unsorted list and calculates the runtime
					startTime = System.nanoTime();
					checkForDuplicates(node, m);
					System.out.println("Unsorted Duplicate Check Runtime: " + (System.nanoTime()-startTime));
					validChoice = true;
				}
				else if(algorithmChoice == 4){
					//compares every item on the list with every other item on the list and calculates the run time
					startTime = System.nanoTime();
					compareList(node);
					System.out.println("List Comparison Runtime: " + (System.nanoTime()-startTime));
					validChoice = true;
				}
				else
					System.out.println("\nPlease pick a valid choice.\n");
			}
			//iterates over the linked list and prints out the numbers to make validating results faster
			iNode iterator = node;
			while(iterator != null){
				System.out.print(iterator.item + " ");
				iterator = iterator.next;
			}
		}
		catch(NumberFormatException e3){
	       System.err.println("Invalid Data Entry");
	     }
	     catch(NoSuchElementException e5){
	       System.err.println("Invalid Data Entry");
	     }


	}

	//creates a linked list with random elements
	public static iNode buildList(int n, int m){
		Random rand = new Random();
		iNode first = new iNode(rand.nextInt(m));
		for(int i=1;i<n;i++){
			first = new iNode(rand.nextInt(m), first);		
		}
		return first;
	}
	
	//compares every item in the linked list with every other item and prints out the results. Runtime of O(n^2)
	public static void compareList(iNode node) {
		//we are starting on the first node of the list
		int currentNodeNumber = 1; //keeps track of which node we are on
		iNode currentNode = node;
		
		//we will begin comparing the node to the node directly after it
		int iteratorNumber = currentNodeNumber+1;
		iNode iterator = currentNode.next;

		//first loop goes over every item in the list
		//second loop compares the current item in the first loop with every other number in the list
		while(currentNode.next != null){
			System.out.println("\nCurrent Node number: " + currentNodeNumber);
			System.out.println("Current Node item: " + currentNode.item);
			while(iterator != null){
				if(currentNode.item > iterator.item)
					System.out.println("Current Node item " + currentNode.item + " is greater than node " + iteratorNumber + "\'s item " + iterator.item);
				else if(currentNode.item == iterator.item)
					System.out.println("Current Node item " + currentNode.item + " is equal to node " + iteratorNumber + "\'s item " + iterator.item);
				else
					System.out.println("Current Node item " + currentNode.item + " is less than node " + iteratorNumber + "\'s item " + iterator.item);
				iterator = iterator.next;
				iteratorNumber++;
			}
			currentNode = currentNode.next;
			iterator = currentNode.next;
			currentNodeNumber++;
			iteratorNumber = currentNodeNumber+1;
		}
	}

	//Checks for duplicates in a sorted list. Runtime of O(n)
	public static void checkForDuplicatesSorted(iNode node){
		iNode iterator = node;
		while(iterator.next != null){
			if(iterator.next.item == iterator.item)
				System.out.println("The number " + iterator.item + " has a duplicate");
			iterator = iterator.next;
		}
	}

	//runs a bubble sort algorithm to sort items in the list. Run time of O(n^2)
	public static iNode bubbleSortList(iNode node){
		iNode iterator = node;
		int temp;
		boolean didSwap = true;
		while(didSwap){
			didSwap = false;
			while(iterator.next != null){
				if(iterator.item > iterator.next.item){
					temp = iterator.item;
					iterator.item = iterator.next.item;
					iterator.next.item = temp;
					didSwap = true;
				}
				iterator = iterator.next;
			}
			iterator = node;
		}
		return node;
	}

	//runs Merge Sort algorithm to organize the list. runtime of O(nlog(n))
	public static iNode mergeSortList(iNode node){
		//if the list contains one item, then it is sorted, so return it
		if(node == null || node.next == null)
			return node;
		iNode iterator = node; 
		iNode head1 = node;

		//used to determine the size of the list so it can be split up
		int listSize = 0;
		while(iterator != null)
		{
			listSize++;
			iterator = iterator.next;
		}
		//resets node back to the head node
		iterator = node;

		//puts iterator at the node in the middle
		for(int i=0;i<(listSize/2)-1; i++){
			iterator = iterator.next;
		}

		//The split the list at the next node, first set the next node as the second head and 
		//make the first half of the list end at the current node
		iNode head2 = iterator.next;
		iNode iteratorHead2 = head2;
		iterator.next = null;

		//send the two halves back into the method to be sorted
		head1 = mergeSortList(head1);
		head2 = mergeSortList(head2);

		//Decides what the new head will be and stores it in a seperate variable so it can be returned later
		iNode newHead;
		if(head1.item<head2.item){
			newHead = head1;	
			head1 = head1.next;
		}
		else{
			newHead = head2;
			head2 = head2.next;
		}
		iterator = newHead;
		//combines every element in both lists into a new sorted list
		while(head1 != null || head2 != null){
			if(head1 == null){
				iterator.next = head2;
				head2 = head2.next;
			}
			else if(head2 == null || head1.item<head2.item){
				iterator.next = head1;		
				head1 = head1.next;
			}
			else{
				iterator.next = head2;
				head2 = head2.next;	
			}
			iterator = iterator.next;
		}
		return newHead;
	}

	//Checks for duplicates on an unsorted list. runtime of O(n^2)
	public static void checkForDuplicates(iNode node, int m){
		//an array used to traverse the list
		iNode iterator = node;

		//create an array that keeps track of which numbers have appeared and sets every spot equal to false
		boolean[] S = new boolean[m];
		for(int s=0; s<S.length; s++)
			S[s] = false;

		//for every item in the linked list, it checks every possible number that it could contain
		//and makes note that it has been found and reports duplicates
		while(iterator != null){
			for(int i=0;i<m;i++){
				if(iterator.item == i){
					if(S[i])
						System.out.println("The number " + i + " has a duplicate.");
					else
						S[i] = true;
				}
			}
			iterator = iterator.next;
		}
	}
}
