//Author: Alejandro Diaz
//Assignment: CS2302 Lab 1 Sudoku
//Instructor: Diego Aguirre
//T.A.: Manoj Saha
//Last Modification: 09/08/2017

/*
Creates a program that calculates a user selected fibonacci number using 
recursion, a loop, and a formula and reports the time it takes to run each algorithm
*/

import java.util.*;
import java.io.*;

public class Lab1Fib{
	public static void main(String[] args) {
		try{
		//create a scanner to prompt the user for which fibonacci number it wants to find
		Scanner console = new Scanner(System.in);

		//asks user to select the fibonacci number they want to find and checks to make sure it is a valid number
		boolean validChoice = false;
		int n=1; 
		while(!validChoice){
			System.out.println("Enter the Fibonacci Number To Find");
			n = console.nextInt();
			if(n>0)
				validChoice = true;
			else
				System.out.println("\nPlease pick a positive number\n");
		}

		//Calculates the time it takes to run the recursive fibonacci algorithm and reports the number
		double startTime = System.nanoTime();
		System.out.println("Recursive O(n^2): " + recursiveFib(n));
		System.out.println("Time: " + (System.nanoTime() - startTime) + "\n");

		//Calculates the time it takes to run the loop fibanacci algorithm and reports the number
		startTime = System.nanoTime();
		System.out.println("Loop O(n): " + loopFib(n));
		System.out.println("Time: " + (System.nanoTime() - startTime) + "\n");
		
		//Calculates the time it takes to run the constant time fibonacci algorithm and reports the number
		startTime = System.nanoTime();
		System.out.println("Constant O(1): " + constantFib(n));
		System.out.println("Time: " + (System.nanoTime() - startTime) + "\n");
	}
		catch(NumberFormatException e3){
	       System.err.println("Invalid Data Entry");
	     }
	     //handles when it cannot find an element
	     catch(NoSuchElementException e5){
	       System.err.println("Invalid Data Entry");
	     }
	}

	//Uses a recursive call to calculate the previous two fibanacci numbers in order to calculate the next one. runs in O(n^2) time
	public static int recursiveFib(int n){
		if(n<2)
			return n;
		return recursiveFib(n-1)+recursiveFib(n-2);
	}

	//instead of performing duplicate calculations, uses a for loop to achieve O(n) run time
	public static int loopFib(int n){
		if(n<2)
			return n;
		int f1 = 0;
		int f2 = 1;
		int fn = 0;	
		for(int i = 2; i<=n; i++) //first we calculate the second fib
		{
			fn = f1+f2; //calculates fib number i
			f1 = f2;
			f2 = fn;
		}

		return(fn);

	}

	//Uses the equation for fibonacci numbers to achieve O(1) run time
	public static int constantFib(int n){
		return (int)((Math.pow((1+Math.sqrt(5))/2, n) - Math.pow((1-Math.sqrt(5))/2, n))/Math.sqrt(5));
	}
}

	
