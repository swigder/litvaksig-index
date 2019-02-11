package litvaksig.index.excel.data.converters;

import litvaksig.index.data.Person;
import litvaksig.index.excel.data.ExcelRecord;

import java.util.function.Function;

public interface ExcelRecordConverter<T extends ExcelRecord> extends Function<T, Person> {

}
