package litvaksig.index.ingest.extractors;

import litvaksig.index.ingest.converters.ExcelGenericRecordConverter;
import litvaksig.index.ingest.records.ExcelBirthRecord;
import litvaksig.index.ingest.records.ExcelDeathRecord;
import litvaksig.index.ingest.converters.ExcelBirthRecordConverter;
import litvaksig.index.ingest.records.ExcelGenericRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtractorBeans {
    @Bean
    public ExcelRecordExtractor<ExcelBirthRecord> birthRecordExtractor() {
        return new ExcelRecordExtractor<>(ExcelBirthRecord.class,
                new ExcelBirthRecordConverter(),
                0, 1);
    }

    @Bean
    public ExcelRecordExtractor<ExcelDeathRecord> deathRecordExtractor() {
        return new ExcelRecordExtractor<>(ExcelDeathRecord.class,
                null,
                0, 2);
    }

    @Bean
    public ExcelRecordExtractor<ExcelGenericRecord> genericRecordExtractor() {
        return new ExcelRecordExtractor<>(ExcelGenericRecord.class,
                new ExcelGenericRecordConverter(),
                0, 2);
    }
}
