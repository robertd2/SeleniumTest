package test.reader.strategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import test.reader.IFileReaderStrategy;

public class CsvReaderStrategy implements IFileReaderStrategy {
	private String separator=";";
	
	public CsvReaderStrategy(String str){
		separator = str;
	}

	public CsvReaderStrategy() {
		
	}

	public List<String[]> readTextFile(String filePath)   {
		List<String[]> result = new ArrayList<String []>();
		BufferedReader bufRdr = null;
		try {   
			
			String record;
			bufRdr = new BufferedReader(new FileReader(filePath));
			while ((record = bufRdr.readLine()) != null)
			{
			     String fields[] = record.split(separator);
			     result.add(fields);
			}
		} catch (IOException e) {
			System.out.println("Ups, cos poczło nie tak...");
			e.printStackTrace();
		} finally {
            try {
                if (bufRdr != null)bufRdr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return result;
	}
}
