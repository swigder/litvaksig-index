package litvaksig.index.excel;

import litvaksig.index.data.Person;
import litvaksig.index.excel.data.ExcelBirthRecord;
import litvaksig.index.excel.converter.ExcelBirthRecordConverter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BirthRecordsExtractor {
    public static void extractRecords(String filename) {
        List<ExcelBirthRecord> records = ExcelReader.extractDataFromFile(filename, 0, 1, ExcelBirthRecord.class);
        List<Person> people =
                records.parallelStream()
                        .map(new ExcelBirthRecordConverter())
                        .collect(toList());
        people.forEach(System.out::println);
    }
}
