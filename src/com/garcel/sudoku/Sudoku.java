package com.garcel.sudoku;

import com.garcel.sudoku.gui.GUI;

/**
 * Runs the project!
 *
 * @author Garcel
 */
public class Sudoku 
{
    private GUI gui;
    
    /**
     * Constructor
     */
    public Sudoku ()
    {
        gui = new GUI (400, 450);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Sudoku sudoku = new Sudoku ();
    }
}
