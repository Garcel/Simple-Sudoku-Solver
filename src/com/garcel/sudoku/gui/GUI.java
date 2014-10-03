package com.garcel.sudoku.gui;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

/**
 * Contains the Graphic User Interface
 *
 * @author Garcel
 */
public class GUI extends JFrame
{
	private static final Logger logger = Logger.getLogger("com.garcel.sudoku.gui.GUI");
	
    private Panel panel;
    
    public GUI (int width, int height)
    {
    	logger.info("Initializing GUI...");
        
        /*** Initializing variables ***/
        panel = new Panel (width, height);
        
        /*** Adding elements to JFrame ***/
        this.add(panel);
        
        /*** JFrame settings ***/
        this.setFocusable(true);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(width, height);
        this.setName("Simple Sudoku Solver");
		this.setTitle("Simple Sudoku Solver");
        this.setVisible (true);
        
        logger.info("GUI initialized...");
    }
}
