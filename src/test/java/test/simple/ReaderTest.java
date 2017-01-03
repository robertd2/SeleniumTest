package test.simple;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.project.selenium.User;

import test.reader.FileReader;
import test.reader.strategy.CsvReaderStrategy;

public class ReaderTest {

	@Test
	public void readerTest() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		FileReader<User> reader = null;
		reader = new FileReader<User>("./target/exampledata.csv",
				new CsvReaderStrategy(";"),
				User.class);
		reader.setHeader(true);
		reader.loadFile();
		reader.listContent();
		reader.listObjects();
		assertTrue(true);

	}

}
