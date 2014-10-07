package com.garcel.sudoku.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.apache.log4j.Logger;

import com.garcel.sudoku.Logic;

/***
 * Contains the Sudoku boxes and the buttons.
 * 
 * @author Garcel
 *
 */
public class Panel extends JPanel{

	private static final Logger logger = Logger.getLogger("com.garcel.sudoku.gui.Panel");
	
	private int width, height;
	
	private Logic logic;
	
    private JTextField boxes [][];
    private JButton solve, generate, check;
    private JPanel panelCenter, panelBottom;
    private Dimension box;
    
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public Panel (int width, int height){
		logger.info("Initializing Panel...");
		
		/*** JPanel settings ***/
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setLayout(new BorderLayout());
		
		/*** Initializing variables ***/
		logic = new Logic ();
		box = new Dimension (40, 40);
        
		/*** Adding elements to the panel ***/
        panelCenter = new JPanel(new GridLayout(9, 9));
        panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        panelCenter.setSize(new Dimension(width, 400));
        panelCenter.setLocation(0, 10);
        panelBottom.setSize(new Dimension(width, 50));
        panelBottom.setLocation(0,  400);
        
        solve = new JButton("Solve");
        generate = new JButton("Generate");
        check = new JButton("Check");
        
        //Adding boxes
        boxes = new JTextField [9][9];
        Font font = new Font("SansSerif", Font.BOLD, 20);
        
        for (int i = 0; i < 9; i ++)
           for (int j = 0; j < 9; j ++)
           {
               boxes [i][j] = new JTextField ("");
               boxes [i][j].setPreferredSize(box);
               boxes [i][j].setHorizontalAlignment(JTextField.CENTER);
               boxes [i][j].setFont(font);
               
               //Setting the bottom border to draw the sudoku grid
               if (i == 2 || i == 5){
            	   Border oldBorder =  boxes[i][j].getBorder();
            	   Border border = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK);
            	   Border newBorder = BorderFactory.createCompoundBorder(border, oldBorder);
            	   boxes[i][j].setBorder(newBorder);
               }
               
               //Setting the right border to draw the sudoku grid
               if (j == 2 || j == 5){
            	   Border oldBorder =  boxes[i][j].getBorder();
            	   Border border = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK);
            	   Border newBorder = BorderFactory.createCompoundBorder(border, oldBorder);
            	   boxes[i][j].setBorder(newBorder);
               }
               
               panelCenter.add (boxes [i][j]);
           }
        
        panelBottom.add(generate);
        panelBottom.add(solve);
        panelBottom.add(check);
        
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);
        
        /*** Adding action listeners to buttons ***/
        solve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	logger.debug("Solve button pressed...");
            	
            	logic.solve(getBoxes());
        		setBoxes (logic.getSol(), false);
            }
        });
        
        generate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	logger.debug("Generate button pressed...");
            	
                logic.generate();
                resetBoxes();
                setBoxes (logic.getSol(), true);
            }
        });
        
        check.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	logger.debug("Check button pressed...");
            	
                if (logic.check(getBoxes())){
                	JOptionPane.showMessageDialog(null, "Solution is valid!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                	JOptionPane.showMessageDialog(null, "Solution is wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        logger.info("Panel initialized...");
	}
	
	private void setBoxes(int [][] sol, boolean changeColor){
		logger.info("Setting Sudoku values...");
		
		 for (int i = 0; i < 9; i ++)
	           for (int j = 0; j < 9; j ++)
	           {
	        	   if (sol[i][j] == 0){
	        		   boxes [i][j].setText("");
	        	   }
	        	   else{
	        		   boxes [i][j].setText(String.valueOf(sol[i][j]));
	        		   if(changeColor)
	        			   boxes[i][j].setBackground(Color.cyan);
	        	   }
	           }
	}
	
	private int [][] getBoxes(){
		logger.info("Getting Sudoku values...");
		
		int [][] sol = new int [9][9];
		
		 for (int i = 0; i < 9; i ++)
	           for (int j = 0; j < 9; j ++)
	           {
	        	  if (!boxes[i][j].getText().equals(""))
	        		  sol[i][j] = Integer.valueOf(boxes [i][j].getText());
	        	  else
	        		  sol[i][j] = 0;
	           }
		 
		 return sol;
	}
	
	private void resetBoxes(){
		for (int i = 0; i < 9; i ++)
	           for (int j = 0; j < 9; j ++)
	           {
	        	  boxes [i][j].setText("");
	        	  boxes[i][j].setBackground(Color.WHITE);
	           }
	}
}