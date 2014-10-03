package com.garcel.sudoku;

import org.apache.log4j.Logger;

import com.garcel.sudoku.gui.GUI;

/**
 * Runs the project!
 *
 * @author Garcel
 */
public class Sudoku 
{
	private static final Logger logger = Logger.getLogger("com.garcel.sudoku.Sudoku");
	
    private GUI gui;
    
    /**
     * Constructor
     */
    public Sudoku ()
    {
    	logger.info("Initializing Sudoku...");
    	
        gui = new GUI (400, 450);
        
        logger.info("Sudoku initialized...");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	logger.info("Starting program...");
    	
        Sudoku sudoku = new Sudoku ();
    }
}
