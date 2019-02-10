package litvaksig.index.excel;

import litvaksig.index.excel.annotation.ExcelColumn;
import litvaksig.index.excel.annotation.ExcelRow;

@ExcelRow
public class ExcelBirthRecord {
    @ExcelColumn(index = 7) String surname;
    @ExcelColumn(index = 8) String givenName;
    @ExcelColumn(index = 9) String father;
    @ExcelColumn(index = 10) String fathersFather;
    @ExcelColumn(index = 11) String mother;
    @ExcelColumn(index = 12) String mothersFather;
    @ExcelColumn(index = 13) String motherMaiden;
    @ExcelColumn(index = 14) Double day;
    @ExcelColumn(index = 15) Double month;
    @ExcelColumn(index = 16) Double year;

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
                '}';
    }
}
