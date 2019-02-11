package litvaksig.index.ingest.extractors;

import litvaksig.index.excel.ExcelReader;
import litvaksig.index.ingest.converters.ExcelRecordConverter;
import litvaksig.index.ingest.records.ExcelRecord;

import java.util.List;

public class ExcelRecordExtractor<T extends ExcelRecord> {
    private final Class<T> klass;
    private final ExcelRecordConverter<T> converter;
    private final int sheetToExtract;
    private final int headerRows;

    public ExcelRecordExtractor(Class<T> klass, ExcelRecordConverter<T> converter, int sheetToExtract, int headerRows) {
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
