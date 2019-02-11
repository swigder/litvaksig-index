package litvaksig.index.excel.data;

import litvaksig.index.excel.reader.annotation.ExcelColumn;
import litvaksig.index.excel.reader.annotation.ExcelRow;

@ExcelRow
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

    @Override
    public String toString() {
        return "ExcelBirthRecord{" +
                "surname='" + surname + '\'' +
                ", givenName='" + givenName + '\'' +
                ", father='" + father + '\'' +
                ", fathersFather='" + fathersFather + '\'' +
                ", mother='" + mother + '\'' +
                ", mothersFather='" + mothersFather + '\'' +
                ", motherMaiden='" + motherMaiden + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", town='" + town + '\'' +
                ", guberniya=" + guberniya +
                '}';
    }
}
