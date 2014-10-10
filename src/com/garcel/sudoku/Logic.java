package com.garcel.sudoku;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.garcel.Utils.Randoms;

/**
 * Handles all the sudoku logic.
 *
 * @author Garcel
 */
public class Logic
{
	private static final Logger logger = Logger.getLogger("com.garcel.sudoku.Logic");
	
    private int sol [][];//Stores the sudoku "matrix"
    private boolean initialize [][];
    private boolean [] allowed [][];
    /**
     * Constructor
     */
    public Logic ()
    {
    	logger.info("Initializing Logic...");
    	
        sol = new int [9][9];
        initialize = new boolean [9][9];
        allowed = new boolean [9][9][9];
        
        logger.info("Logic initialized...");
    }
    
    /**
     * 
     */
    public int [][] solve (int [][] sol)
    {
    	logger.info("Solving sudoku...");
    	
        for (int i = 0; i < 9; i ++)
            for (int j = 0; j < 9; j ++)
                initialize [i][j] = (sol [i][j] != 0);
        
        fillAllowed(sol, allowed, initialize);
        sudoku_BT(0, 0, sol, initialize, false);
        sol = this.sol;
        return sol;
    }
    
    /**
     * 
     * @param i
     * @param j
     * @param sol
     * @param initialize
     */
    public void sudoku_BT (int i, int j, int sol [][], boolean initialize [][], boolean end)
    {
    	//logger.debug("Running BT algorithm...");
    	if (!end && !initialize [i][j])
        {
            for (int k = 1; k < 10; k++)
            {
            	if(allowed[i][j][k - 1]){
	                sol [i][j] = k;
	                if (isAchievable (i, j, sol))
	                {
	                    if ((i == 8) && (j == 8))
	                    	sudoku_BT (0, 0, sol, initialize, true);
	                    else if ((i < 8) && (j == 8))
	                        sudoku_BT (i + 1, 0, sol, initialize, false);
	                    else if ((i <= 8) && (j < 8))
	                        sudoku_BT (i , j + 1, sol, initialize, false);
	                }
	                
	                sol [i][j] = 0;
            	}
            }
        }
        else if (!end && initialize [i][j])
        {
            if ((i == 8) && (j == 8))
            	sudoku_BT (0, 0, sol, initialize, true);
            else if ((i < 8) && (j == 8))
                sudoku_BT (i + 1, 0, sol, initialize, false);
            else if ((i <= 8) && (j < 8))
                sudoku_BT (i , j + 1, sol, initialize, false);
        }
        else if (end){
        	for (int a = 0; a < 9; a ++)
                for (int b = 0; b < 9; b ++)
                    this.sol[a][b] = sol[a][b];
        }
    }
    
    /**
     * 
     * @param i
     * @param j
     * @param sol
     * @return
     */
    private boolean isAchievable(int i, int j, int sol [][])
    {
        boolean valid = true;
        int m = 0, l = 0;
        
        while ((m <= 8) && valid)
        {
            if ((sol [i][j] == sol [m][j]) && (m!= i))
                valid = false;
            
            m ++;
        }
        
        while ((l <= 8) && (valid))
        {
            if ((sol [i][j] == sol [i][l]) && (l != j))
                valid = false;
            
            l ++;
        }
        
        m = corresp3x3 (i);
        l = corresp3x3 (j);
        
        while ((m < corresp3x3 (i) + 3) && valid)
        {
            while ((l < corresp3x3 (j) + 3) && valid)
            {
                if ((sol [i][j] == sol [m][l]) && (i != m) && (j != l))
                    valid = false;
                
                l ++;
            }
            
            m ++;
            l = corresp3x3 (j);
        }
        return valid;
    }
    
    /**
     * Returns the row correspondence to the given position
     * 
     * @param i the row correspondence to the given position
     * @return
     */
    private int corresp3x3 (int i)
    {
        int k = 0;
        int result = 0;
        
        if ((i + 1) % 3 == 0)
            k = (i + 1) / 3;
        else
            k = ((i + 1) / 3) + 1;
        
        switch (k)
        {
            case 1:
                result = 0;
                break;
            case 2:
                result = 3;
                break;
            case 3:
                result = 6;
                break;
        }
        
        return result;
    }
    
    /**
     * 
     */
    public void generate ()
    {
    	logger.debug("Generating Sudoku...");
    	
    	boolean valid = false;
    	
    	int difficulty = setDifficulty ();
    	
    	while (!valid){
	        int counter = 0, p, r, t;
	        
	        reset();
	                
	        while (counter < difficulty)
	        {
	        	p = Randoms.randomNumber(0, 8);
	            r = Randoms.randomNumber(0, 8);
	            t = Randoms.randomNumber(1, 9);
	                    
	            if (sol [p][r] == 0) 
	            {
	            	sol [p][r] = t;
	                        
	                if (isAchievable (p, r, sol))
	                	counter ++;
	                else
	                	sol [p][r] = 0;
	             }
	        }
	        
	        //if(solve (sol))
	        	valid = true;
    	}
    }
    
    /**
     * 
     * @return
     */
    private int setDifficulty()
    {
    	logger.debug("Setting difficulty...");
    	
        final String options[]={"Easy", "Normal", "Hard", "Overkill"};
        String dif = null;
        dif = String.valueOf(JOptionPane.showInputDialog(null, "Please select a difficulty ","Difficulty",
                           JOptionPane.INFORMATION_MESSAGE, null, options, options[0]));
        
        //If cancel button is pressed, program exits
        if (dif == null)
            System.exit(0);
        
        try
        {
            if (dif == "Easy")
                return 28;
            else if (dif == "Normal")
                return 24;
            else if (dif == "Hard")
                return 20;  
        }
        catch (IllegalArgumentException e)
        {
            
        }
        
        return 18;
    }
    
    /**
     * 
     */
    private void reset (){
    	for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
            {
        		sol [i][j] = 0;
            }
    }
    
    public int [][] getSol(){
    	logger.info("Getting solution matrix...");
    	
    	return sol;
    }
    
    public void fillAllowed (int [][] sol, boolean [][][] allowed, boolean [][] initialize){
    	logger.info("Filling allowed numbers matrix...");
    	
    	boolean recheck = true;
    	
    	while (recheck){
    		recheck = false;
    		
	    	for (int i = 0; i < sol.length; i ++){
	    		for (int j = 0; j < sol[0].length; j ++){
	    			if(!initialize[i][j]){
		    			for (int k = 0; k < 9; k ++){
		    				logger.debug("Candidates for position " + i + " " + j + " and number " + (k+1));
		    				if (allowedRow(i, j, k + 1, sol) && allowedColumn(i,j, k + 1, sol) && allowedSubMatrix(i, j, k + 1, sol)){
		    					logger.debug("******Number " + (k + 1) + " is fully allowed******");
		    					allowed[i][j][k] = true;
		    				}
		    				
		    				if (k == 8){
		    					boolean unic = true;
		    					int value = 0;
		    					
		    					logger.debug("Checking if solution for position " + i + " " + j + " is unic");
		    					for (int x = 0; x < 9; x ++){
		    						if (allowed[i][j][x]){
		    							if (value == 0)
		    								value = x + 1;
		    							else{
		    								unic = false;
		    								break;
		    							}
		    						}
		    					}
		    					
		    					if (unic){
		    						logger.debug("Candidate for position " + i + " " + j + " is unic. Setting value " + value);
		    						sol[i][j] = value;
		    						initialize[i][j] = true;
		    						recheck = true;
		    					}
		    				}
		    			}
		    		}
	    		}
	    	}
    	}
    }
    
    private boolean allowedRow (int row, int column, int number, int [][] sol){
    	logger.debug("Looking for candidate numbers in row " + row + "...");
    	
    	for (int i = 0; i < sol.length; i ++){
    		logger.debug("Checking position " + row + " " + column);
    		if (sol[row][i] == number){
    			logger.debug("Is invalid!!!!");
    			return false;
    		}
    	}
    	
    	logger.debug("Is valid!");
    	
    	return true;
    }
    
    private boolean allowedColumn (int row, int column, int number, int [][] sol){
    	logger.debug("Looking for candidate numbers in column " + column + "...");
    	
    	for (int i = 0; i < sol.length; i ++){
    		logger.debug("Checking position " + row + " " + column);
    		if (sol[i][column] == number){
    			logger.debug("Is invalid!!!!");
    			return false;
    		}
    	}
    	
    	logger.debug("Is valid!");
    	
    	return true;
    }
    
    private boolean allowedSubMatrix (int row, int column, int number, int [][] sol){
    	logger.debug("Looking for candidate numbers in submatrix...");
    	
    	int m = corresp3x3 (row);
        int l = corresp3x3 (column);
         
        while ((m < corresp3x3 (row) + 3))
        {
            while ((l < corresp3x3 (column) + 3))
            {
            	if (sol[m][l] == number){
            		logger.debug("Is invalid!!!!");
        			return false;
        		}
                 
                l ++;
            }
             
            m ++;
            l = corresp3x3 (column);
         }
    	
        logger.debug("Is valid!");
        
        return true;
    }
    
    public boolean check(int [][] sol){
    	for (int i = 0; i < sol.length; i ++)
    		for (int j = 0; j < sol[0].length; j ++)
    			if (!isAchievable (i, j, sol))
    				return false;
    			
    	return true;
    }
    
    private ArrayList<Integer> fillList (ArrayList <Integer> list){
    	list.add (1);
    	list.add (2);
    	list.add (3);
    	list.add (4);
    	list.add (5);
    	list.add (6);
    	list.add (7);
    	list.add (8);
    	list.add (9);
    	
    	return list;
    }
    
    public void printSudoku (int [][] sol)
    {
    	for (int i = 0; i < sol.length; i ++){
    		for (int j = 0; j < sol[0].length; j ++)
    			System.out.print(sol[i][j] + " ");
    		
    		System.out.println(" ");
    	}
    }
}