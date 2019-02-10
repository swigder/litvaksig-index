package litvaksig.index.excel;

import java.util.List;

public class BirthRecordsExtractor {
    public static void extractRecords(String filename) {
        List<ExcelBirthRecord> records = ExcelReader.extractDataFromFile(filename, 0, 1, ExcelBirthRecord.class);
        records.forEach(System.out::println);
    }
}
