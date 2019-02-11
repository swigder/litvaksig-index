package litvaksig.index;

import litvaksig.index.excel.data.ExcelBirthRecord;
import litvaksig.index.excel.data.ExcelDeathRecord;
import litvaksig.index.excel.data.extractors.RecordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            @Autowired RecordExtractor<ExcelBirthRecord> birthRecordExtractor,
            @Autowired RecordExtractor<ExcelDeathRecord> deathRecordExtractor) {
        return args -> {
            String directory = "/Users/xx/Documents/genealogy/litvaksig/";

            String birthFile = "KUD-KID-1815-1845-B.xls";
            birthRecordExtractor.extractRecords(directory + birthFile);

            String deathFile = "KAL-KAL-1922_1939-D.xls";
            deathRecordExtractor.extractRecords(directory + deathFile);
        };
    }

}
