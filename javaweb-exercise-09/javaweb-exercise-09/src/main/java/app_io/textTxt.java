package app_io;

import java.io.BufferedReader;
import java.io.FileReader;

public class textTxt {
	public static void main(String[] args) {
		String file = "C:/Users/Tibame_T14/Documents/APP_THP101/JavaSE線上講義網址.txt";
		try(
		    FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		) {
			String line ;
			while ( (line = br.readLine()) != null ) {
				System.out.println(line);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
