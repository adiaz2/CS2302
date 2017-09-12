//Author: Alejandro Diaz
//Assignment: CS2302 Lab 1 Sudoku
//Instructor: Diego Aguirre
//T.A.: Manoj Saha
//Last Modification: 09/08/2017

/*
Creates a program that prompts the user to select the size of a sudoku puzzle and solves it with the algorithm of their choice
the program also reports the time it took to run the algorithm in nanoseconds

*/

import java.util.*;
import java.io.*;

public class lab1Sudoku{
	public static void main(String[] args) {
		try{
		Scanner console = new Scanner(System.in);
		
		//asks user to select the size of the sudoku puzzle and checks to make sure it is a valid number
		boolean validChoice = false;
		int n=1; 
		while(!validChoice){
			System.out.println("Enter the size of the Sudoku puzzle:");
			n = console.nextInt();
			if(n>0)
				validChoice = true;
			else
				System.out.println("\nPlease pick a positive number\n");
		}
		
		//Asks user to select an Algorithm and checks if the user's input is valid
		validChoice = false;
		int choice = 0;
		while(!validChoice) {
			System.out.println("Which Algorithm would you like to use? Enter:\n"
								+ "1 for Naive Algorithm\n"
								+ "2 for Not So Naive Algorithm\n"
								+ "3 for Not Naive Algorithm");
			choice = console.nextInt();
			if(choice>0 && choice<=3)
				validChoice = true;
			else
				System.out.println("\nPlease pick a number between 1 and 3");
			}

		int S[][] = new int[n][n];
		int startNum;
		for(int r=0; r<n-1; r++){
			startNum = (int)(Math.sqrt(n)*(r%Math.sqrt(n)) + (double)(r/Math.sqrt(n)));
			for(int c=0; c<n-1;c++)
				S[r][c] = ((startNum + c) % n) + 1;
		}
		double startTime = 0;
		if(choice==1){
			//Runs Naive_Algorithm and measures the time it takes to run
			startTime = System.nanoTime();
			Naive_Algorithm(S);
			double naiveAlgTime = System.nanoTime()-startTime;
			System.out.println("Naive Algorithm took " + naiveAlgTime + " nanoseconds");
		}
		else if(choice==2){
			//Runs Not_So_Naive_Algorithm and measures the time it takes to run
			startTime = System.nanoTime();
			Not_So_Naive_Algorithm(S);
			double notSoNaiveAlgTime = System.nanoTime()-startTime;
			System.out.println("Not So Naive Algorithm took " + notSoNaiveAlgTime + " nanoseconds");
		}
		else if(choice==3){
			//Runs Not_Naive_Algorithm and measures the time it takes to run
			startTime = System.nanoTime();
			Not_Naive_Algorithm(S);
			double notNaiveAlgTime = System.nanoTime()-startTime;
			System.out.println("Not Naive Algorithm took " + notNaiveAlgTime + " nanoseconds");		
		}
		else
			System.out.println("Invalid Data Entry");
	     //handles when there is an invalid data type
	}
	     catch(NumberFormatException e3){
	       System.err.println("Invalid Data Entry");
	     }
	     //handles when it cannot find an element
	     catch(NoSuchElementException e5){
	       System.err.println("Invalid Data Entry");
	     }
	}

	//This algorith is the slowest of the three and runs in O(n^3) time
	public static void Naive_Algorithm(int[][] S){
		boolean isNumPresent; //used to determine if a number is in the current line of the Sudoku puzzle
		
		//nested for loop to traverse the 2-D Array
		for(int r=0; r<S.length; r++) {
			for(int num=1; num<S.length+1; num++) {
				isNumPresent = false;
				for(int i=0; i<S.length; i++){
					if(S[r][i] == num){
						isNumPresent = true;
						break;
					}
				}
				//If a number was not present in the row, it must be the missing one in this row, so we print that out
				if(!isNumPresent)
					System.out.println("The missing number in row: " + (r+1) + " is " + num);
			}
		}

	}

	//This algorithm is faster than the Naive_Algorithm and runs in O(n^2) time
	public static void Not_So_Naive_Algorithm(int[][] S){
		boolean[] isNumPresent = new boolean[S.length]; //stores which numbers were present in the current row of the sudoku puzzle
		
		//nested loop to traverse the 2-D Array
		for(int r=0; r<S.length; r++) {
			for(int num = 1; num<S.length; num++)
				isNumPresent[num] = false;
			for(int i=0; i<S.length; i++) {
				if(S[r][i]!=0)
					isNumPresent[S[r][i]-1] = true;
			}
			for(int i=1; i<S.length; i++){
				//looks for the number that was missing in this row and prints it out
				if(!isNumPresent[i])
					System.out.println("The missing number in row " + (r+1) + " is " + (i+1));
			}
		}
	}

	public static void Not_Naive_Algorithm(int[][] S){
		int sum = S.length * (S.length+1)/2; //calculate the sum of 1 through n
		int missingNum;
		for(int r=0;r<S.length; r++){
			missingNum = sum;
			for(int i = 0; i<S.length; i++){
				missingNum -= S[r][i]; //the missing number is the sum of 1 through n minus the sum of all the numbers in the row 
			}
			System.out.println("The missing number in row " + (r+1) + " is " + missingNum);
		}

	}
}
