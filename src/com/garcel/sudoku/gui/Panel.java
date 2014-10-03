package com.garcel.sudoku.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private JButton solve, generate;
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
        
        resetBoxes();
        
        panelBottom.add(generate);
        panelBottom.add(solve);
        
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);
        
        /*** Adding action listeners to buttons ***/
        solve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //logic.solve();
            }
        });
        
        generate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //logic.generate():    
            }
        });
        
        logger.info("Panel initialized...");
	}
	
	private void resetBoxes (){
		boxes = new JTextField [9][9];
        for (int i = 0; i < 9; i ++)
           for (int j = 0; j < 9; j ++)
           {
               boxes [i][j] = new JTextField ("");
               boxes [i][j].setPreferredSize(box);
               boxes [i][j].setHorizontalAlignment(JTextField.CENTER);
               panelCenter.add (boxes [i][j]);
           }
	}
}