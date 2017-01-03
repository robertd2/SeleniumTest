package test.reader;

import java.util.List;

public interface IConvertStrategy<T> {

	List<T> createObjFromInput(List<String[]> stringList);
}
