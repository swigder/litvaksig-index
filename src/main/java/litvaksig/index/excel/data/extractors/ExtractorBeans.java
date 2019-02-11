package litvaksig.index.excel.data.extractors;

import litvaksig.index.excel.data.ExcelBirthRecord;
import litvaksig.index.excel.data.ExcelDeathRecord;
import litvaksig.index.excel.data.converters.ExcelBirthRecordConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtractorBeans {
    @Bean
    public RecordExtractor<ExcelBirthRecord> birthRecordExtractor() {
        return new RecordExtractor<>(ExcelBirthRecord.class,
                new ExcelBirthRecordConverter(),
                0, 1);
    }

    @Bean
    public RecordExtractor<ExcelDeathRecord> deathRecordExtractor() {
        return new RecordExtractor<>(ExcelDeathRecord.class,
                null,
                0, 2);
    }
}
