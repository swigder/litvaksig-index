package litvaksig.index.excel.data.extractors;

import litvaksig.index.excel.data.ExcelRecord;
import litvaksig.index.excel.data.converters.ExcelRecordConverter;
import litvaksig.index.excel.reader.ExcelReader;

import java.util.List;

public class RecordExtractor<T extends ExcelRecord> {
    private final Class<T> klass;
    private final ExcelRecordConverter<T> converter;
    private final int sheetToExtract;
    private final int headerRows;

    public RecordExtractor(Class<T> klass, ExcelRecordConverter<T> converter, int sheetToExtract, int headerRows) {
        this.klass = klass;
        this.converter = converter;
        this.sheetToExtract = sheetToExtract;
        this.headerRows = headerRows;
    }

    public void extractRecords(String filename) {
        List<T> records = ExcelReader.extractDataFromFile(filename, sheetToExtract, headerRows, klass);
        if (converter == null) {
            records.forEach(System.out::println);
        } else {
            records.parallelStream().map(converter).forEach(System.out::println);
        }
    }
}
