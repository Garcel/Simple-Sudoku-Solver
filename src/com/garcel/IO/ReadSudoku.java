package com.garcel.IO;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadSudoku {

	public static String [] read (String filename) throws Exception{
		FileReader fr = new FileReader("C:\\Users\\IMENTEC\\Desktop\\" + filename);
		BufferedReader br1 = new BufferedReader(fr);
		String buffer = "";
		String [] data = new String [0];
		int i = 0;
		
		while((buffer=br1.readLine()) != null){ // continue reading until EOF
			if (i == 0)
				data = new String [buffer.length()];
			
			data[i] = buffer;
			i ++;
		}
		
		return data;
	}
}
