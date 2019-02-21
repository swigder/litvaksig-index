package litvaksig.index.ingest.records;

import litvaksig.index.excel.annotation.ExcelColumn;
import litvaksig.index.excel.annotation.ExcelRow;
import lombok.ToString;

@ExcelRow
@ToString
public class ExcelGenericRecord implements ExcelRecord {
    @ExcelColumn(fromHeader = "Surname") public String surname;
    @ExcelColumn(fromHeader = "Given Name") public String givenName;
    @ExcelColumn(fromHeader = "Father") public String father;
}
