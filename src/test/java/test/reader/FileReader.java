package test.reader;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.project.selenium.User;

public class FileReader<T>{

	private Class<T> clazz;
	IFileReaderStrategy fileReaderStrategy;
	
	String filePath;
	List<String[]> fileContent;
	boolean header=false;
	
	
	
	private FileReader(){
		clazz=null;
	}
	
	public FileReader(String filePath,IFileReaderStrategy fileReaderStrategy, Class<T> clazz ){
		this.fileReaderStrategy = fileReaderStrategy;
		this.filePath = filePath;
		this.fileContent=null;
		this.clazz=clazz;
	}
	


	public void loadFile(){
		fileContent=readFile(filePath);
	}

	List<String[]> readFile(String filePath ){
		List<String[]> result;
		result = fileReaderStrategy.readTextFile(filePath);
		return result;
		
	}
	
	
	public void listContent(){
		System.out.println("Content of the file: "+filePath);
		for(String[] elem : fileContent){
			System.out.println(Arrays.toString(elem));
		}
	}
	
	public void listObjects() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		System.out.println("Objects of the file: "+filePath);
		for(T elem : convertDataIntoListOfObjects()){
			System.out.println(elem);
		}		
	}
	
	public List<T> convertDataIntoListOfObjects() {
		
		List<T> result = new ArrayList<T>();
        
		if(fileContent != null){
			if(hasHeader()){
				fileContent.remove(0);
			}
		for(String[] elem : fileContent){
			try {
				Object[] ot = new Object[] {elem};
				result.add(clazz.getConstructor(String[].class).newInstance(ot));		

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println("Problem with/no String[] constructor in a class :"+clazz.getName());
				e.printStackTrace();
			}
		}
		}else{
			System.out.println("Empty file or problem during reading");
		}
		return result;
		
	}
	
	public void setFileReaderStrategy(IFileReaderStrategy fileReaderStrategy) {
		this.fileReaderStrategy = fileReaderStrategy;
	}

	public boolean hasHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}
	

}
