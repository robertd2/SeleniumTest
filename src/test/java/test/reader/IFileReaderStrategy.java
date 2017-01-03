package test.reader;

import java.util.List;

public interface IFileReaderStrategy {

	List<String[]> readTextFile(String filePath);

}
