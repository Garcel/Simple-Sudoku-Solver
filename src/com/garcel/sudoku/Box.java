
package com.garcel.sudoku;

/**
 * Represents the box containing the sudoku numbers.
 *
 * @author Garcel
 */
public class Box 
{
    private static int counter = 0;
    private int id;//Id for the box in the sudoku grid
    private int number;//Number contained in the box
    
    /**
     * Constructor
     */
    public Box ()
    {
        id = counter ++;
        number = 10;
    }
    
    /**
     * Returns the sudoku number contained in the box
     * 
     * @return the number contained in the box
     */
    public int getNumero ()
    {
        return number;
    }
    
    /**
     * Sets the number in the box
     * 
     * @param number
     */
    public void setNumero (int number)
    {
        this.number = number;
    }
    
    /**
     * Returns the box id
     * 
     * @return the box id
     */
    public int getId ()
    {
        return id;
    }
}
