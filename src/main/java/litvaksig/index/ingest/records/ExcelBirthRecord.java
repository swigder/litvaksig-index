package litvaksig.index.ingest.records;

import litvaksig.index.excel.annotation.ExcelColumn;
import litvaksig.index.excel.annotation.ExcelRow;
import lombok.ToString;

@ExcelRow
@ToString
public class ExcelBirthRecord implements ExcelRecord {
    @ExcelColumn(index = 7) public String surname;
    @ExcelColumn(index = 8) public String givenName;
    @ExcelColumn(index = 9) public String father;
    @ExcelColumn(index = 10) public String fathersFather;
    @ExcelColumn(index = 11) public String mother;
    @ExcelColumn(index = 12) public String mothersFather;
    @ExcelColumn(index = 13) public String motherMaiden;
    @ExcelColumn(index = 14) public Integer day;
    @ExcelColumn(index = 15) public Integer month;
    @ExcelColumn(index = 16) public Integer year;
    @ExcelColumn(index = 18) public String town;
    @ExcelColumn(index = 20) public String guberniya;
}
