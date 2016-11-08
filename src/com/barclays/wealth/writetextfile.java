package com.barclays.wealth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writetextfile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			String content = "This is the content to write into file";

			File file = new File("./TestReport/filename.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			StringBuilder str = new StringBuilder("tutorials ");
			str.append("abc"+"\t");
			str.append("123"+"\t");
			str.append("\r\n");
			str.append("sef"+"\t");
			str.append("\r\n");
			bw.write(str.toString());
			System.out.println(str);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
