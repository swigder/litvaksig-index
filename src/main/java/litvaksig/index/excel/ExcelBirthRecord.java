package litvaksig.index.excel;

import litvaksig.index.excel.annotation.ExcelColumn;
import litvaksig.index.excel.annotation.ExcelRow;

@ExcelRow
public class ExcelBirthRecord {
    @ExcelColumn(index = 7) String surname;
    @ExcelColumn(index = 8) String givenName;

    @Override
    public String toString() {
        return "ExcelBirthRecord{" +
                "surname='" + surname + '\'' +
                ", givenName='" + givenName + '\'' +
                '}';
    }
}
