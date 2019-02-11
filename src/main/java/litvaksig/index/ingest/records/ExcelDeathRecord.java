package litvaksig.index.ingest.records;

import litvaksig.index.excel.annotation.ExcelColumn;

public class ExcelDeathRecord implements ExcelRecord {
    @ExcelColumn(index = 7) public String surname;
    @ExcelColumn(index = 8) public String givenName;
    @ExcelColumn(index = 9) public String father;
    @ExcelColumn(index = 10) public String mother;
    @ExcelColumn(index = 11) public String motherMaiden;
    @ExcelColumn(index = 12) public String spouseGiven;
    @ExcelColumn(index = 13) public String spouseMaiden;
    @ExcelColumn(index = 15, checkType = true) public String age;
    @ExcelColumn(index = 16) public Integer day;
    @ExcelColumn(index = 17) public Integer month;
    @ExcelColumn(index = 18) public Integer year;
    @ExcelColumn(index = 20) public String town;
    @ExcelColumn(index = 22) public String guberniya;

    @Override
    public String toString() {
        return "ExcelDeathRecord{" +
                "surname='" + surname + '\'' +
                ", givenName='" + givenName + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", motherMaiden='" + motherMaiden + '\'' +
                ", spouseGiven='" + spouseGiven + '\'' +
                ", spouseMaiden='" + spouseMaiden + '\'' +
                ", age='" + age + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", town='" + town + '\'' +
                ", guberniya='" + guberniya + '\'' +
                '}';
    }
}
