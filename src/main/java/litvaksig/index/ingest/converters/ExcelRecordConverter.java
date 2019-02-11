package litvaksig.index.ingest.converters;

import litvaksig.index.data.Person;
import litvaksig.index.ingest.records.ExcelRecord;

import java.util.function.Function;

public interface ExcelRecordConverter<T extends ExcelRecord> extends Function<T, Person> {

}
