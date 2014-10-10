package com.garcel.sudoku;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.garcel.IO.ReadSudoku;

public class LogicTest {
	
	private static final Logger logger = Logger.getLogger("test.com.garcel.sudoku.LogicTest");
	/**
	 * Method which tests the correct creation of the class objects by running the constructor. Also tests getters.
	 */
	@Test
	public void  fillAllowedTest_3x3(){
		logger.info("Initizalizing fillAllowed test 3x3 Matrix...");
		
		//Initializing variables
		int [][] sol = new int [3][3];
		sol[0][0] = 1;
		sol[0][1] = 2;
		sol[0][2] = 0;
		sol[1][0] = 4;
		sol[1][1] = 5;
		sol[1][2] = 6;
		sol[2][0] = 7;
		sol[2][1] = 8;
		sol[2][2] = 9;
		
		boolean allowed [][][] = new boolean [3][3][9];
		for (int i = 0; i < allowed.length; i ++)
			for (int j = 0; j < allowed[0].length; j ++)
				for (int k = 0; k < 9; k ++)
					allowed[i][j][k] = false;
		
		boolean initialize [][] = new boolean [3][3];
		for (int i = 0; i < sol.length; i ++)
			for (int j = 0; j < sol[0].length; j ++)
				if (sol[i][j] != 0)
					initialize[i][j] = true;
		
		//Initializing logic
		Logic logic = new Logic ();
		logic.fillAllowed(sol, allowed, initialize);
		
		//Initializing new variables to compare the result in the assert
		int [][] solTrue = new int [3][3];
		solTrue[0][0] = 1;
		solTrue[0][1] = 2;
		solTrue[0][2] = 3;
		solTrue[1][0] = 4;
		solTrue[1][1] = 5;
		solTrue[1][2] = 6;
		solTrue[2][0] = 7;
		solTrue[2][1] = 8;
		solTrue[2][2] = 9;
		
		//Tests
		assertTrue("Checking if allowed matrix is correctly setted after filling process", allowed[0][2][2] == true);
		assertTrue("Checking if sol matrix is correctly setted after filling process", sol[0][2] == solTrue[0][2]);
	}
	
	/**
	 * Method which tests the correct creation of the class objects by running the constructor. Also tests getters.
	 * @throws Exception 
	 */
	@Test
	public void  fillAllowedTest_9x9() throws Exception{
		logger.info("Initizalizing fillAllowed test 9x9 Matrix...");
		
		//Loading Sudoku from file
		String [] sudoku = ReadSudoku.read("sudoku.txt");
		
		//Initializing variables
		int [][] sol = new int [9][9];
		for (int i = 0; i < sol.length; i ++)
			for (int j = 0; j < sol[0].length; j ++)
				sol[i][j] = Integer.parseInt(sudoku[i].substring(j, j + 1));
		
		boolean allowed [][][] = new boolean [9][9][9];
		for (int i = 0; i < allowed.length; i ++)
			for (int j = 0; j < allowed[0].length; j ++)
				for (int k = 0; k < 9; k ++)
					allowed[i][j][k] = false;
		
		boolean initialize [][] = new boolean [9][9];
		for (int i = 0; i < sol.length; i ++)
			for (int j = 0; j < sol[0].length; j ++)
				if (sol[i][j] != 0)
					initialize[i][j] = true;
		
		//Initializing logic
		Logic logic = new Logic ();
		logic.fillAllowed(sol, allowed, initialize);
		
		//Tests
		assertTrue("Checking if allowed matrix is correctly setted after filling process", allowed[0][2][8] == true);
		assertTrue("Checking if allowed matrix is correctly setted after filling process", allowed[0][2][2] == false);
		assertTrue("Checking if allowed matrix is correctly setted after filling process", allowed[2][2][8] == true);
		assertTrue("Checking if allowed matrix is correctly setted after filling process", allowed[6][6][8] == true);
	}
	
	@Test
	public void  SolveTest() throws Exception{
		logger.info("Initizalizing Solve test...");
		
		//Loading Sudoku from file
		String [] sudoku = ReadSudoku.read("sudoku.txt");
		
		//Initializing variables
		int [][] sol = new int [9][9];
		for (int i = 0; i < sol.length; i ++)
			for (int j = 0; j < sol[0].length; j ++)
				sol[i][j] = Integer.parseInt(sudoku[i].substring(j, j + 1));
		
		boolean allowed [][][] = new boolean [9][9][9];
		for (int i = 0; i < allowed.length; i ++)
			for (int j = 0; j < allowed[0].length; j ++)
				for (int k = 0; k < 9; k ++)
					allowed[i][j][k] = false;
		
		boolean initialize [][] = new boolean [9][9];
		for (int i = 0; i < sol.length; i ++)
			for (int j = 0; j < sol[0].length; j ++)
				if (sol[i][j] != 0)
					initialize[i][j] = true;
		
		//Initializing logic
		Logic logic = new Logic ();
		sol = logic.solve(sol);
		
		//Initializing new variables to compare the result in the assert
		//Loading Sudoku from file
		String [] expectedSudoku = ReadSudoku.read("expected.txt");
				
		//Initializing variables
		int [][] expected = new int [9][9];
		for (int i = 0; i < expected.length; i ++)
			for (int j = 0; j < expected[0].length; j ++)
				expected[i][j] = Integer.parseInt(expectedSudoku[i].substring(j, j + 1));
		
		//Tests
		for (int i = 0; i < expected.length; i ++)
			for (int j = 0; j < expected[0].length; j ++)
				assertTrue("Checking if position " + i + " (" + sol[i][j] + ") " + j + " (" + expected[i][j] + ") is correct", sol[i][j] == expected[i][j]);
	}
}
