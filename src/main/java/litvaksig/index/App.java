/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package litvaksig.index;

import litvaksig.index.excel.BirthRecordsExtractor;

public class App {
    public static void main(String[] args) {
        String file = "KUD-KID-1815-1845-B.xls";
        BirthRecordsExtractor.extractRecords(file);
    }
}
