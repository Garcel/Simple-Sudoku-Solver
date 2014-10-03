package com.garcel.sudoku;

import javax.swing.JOptionPane;

import com.garcel.Utils.Randoms;

/**
 * Handles all the sudoku logic.
 *
 * @author Garcel
 */
public class Logic
{
    private int sol [][];//Stores the sudoku "matrix"
    
    /**
     * Constructor
     */
    public Logic ()
    {
        sol = new int [9][9];
      }
    
    /**
     * 
     */
    public void solve ()
    {
        boolean initialize [][] = new boolean [9][9];
        
        for (int i = 0; i < 9; i ++)
            for (int j = 0; j < 9; j ++)
                initialize [i][j] = (sol [i][j] != 0);
        
        sudoku_BT(0, 0, sol, initialize);
    }
    
    /**
     * 
     * @param i
     * @param j
     * @param sol
     * @param initialize
     */
    public void sudoku_BT (int i, int j, int sol [][], boolean initialize [][])
    {
        if (!initialize [i][j])
        {
            for (int k = 1; k < 10; k++)
            {
                sol [i][j] = k;
                if (isAchievable(i, j, sol))
                {
                    if ((i < 8) && (j == 8))
                        sudoku_BT (i + 1, 0, sol, initialize);
                    else if ((i <= 8) && (j < 8))
                        sudoku_BT (i , j + 1, sol, initialize);
                    else{
                    	//end
                    }
                }
                
                sol [i][j] = 0;
            }
        }
        else
        {
        	if ((i < 8) && (j == 8))
                sudoku_BT (i + 1, 0, sol, initialize);
            else if ((i <= 8) && (j < 8))
                sudoku_BT (i , j + 1, sol, initialize);
            else{
            	//end
            }
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
        int m = 0, l;
        
        while ((m <= 8) && valid)
        {
            if ((sol [i][j] == sol [m][j]) && (m!= i))
                valid = false;
            
            m ++;
        }
        
        l = 0;
        
        while ((l <= 8) & (valid))
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
    private void generate ()
    {
        int counter = 0, p, r, t, difficulty;
        
        difficulty = setDifficulty ();
                
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
    }
    
    /**
     * 
     * @return
     */
    private int setDifficulty()
    {
        final String options[]={"Easy", "Normal", "Hard", "Overkill"};
        String dif = null;
        dif = String.valueOf(JOptionPane.showInputDialog(null, "Please select a dofficulty ","Difficulty",
                           JOptionPane.INFORMATION_MESSAGE, null, options, options[0]));
        
        //If cancel button is pressed, program exits
        if (dif.length() == 4)
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
}