package litvaksig.index;

import litvaksig.index.ingest.records.ExcelBirthRecord;
import litvaksig.index.ingest.records.ExcelDeathRecord;
import litvaksig.index.ingest.extractors.ExcelRecordExtractor;
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
            @Autowired ExcelRecordExtractor<ExcelBirthRecord> birthRecordExtractor,
            @Autowired ExcelRecordExtractor<ExcelDeathRecord> deathRecordExtractor) {
        return args -> {
            String directory = "/Users/xx/Documents/genealogy/litvaksig/";

            String birthFile = "KUD-KID-1815-1845-B.xls";
            birthRecordExtractor.extractRecords(directory + birthFile);

            String deathFile = "KAL-KAL-1922_1939-D.xls";
            deathRecordExtractor.extractRecords(directory + deathFile);
        };
    }

}
