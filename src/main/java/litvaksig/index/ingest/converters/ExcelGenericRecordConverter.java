package litvaksig.index.ingest.converters;

import litvaksig.index.data.Person;
import litvaksig.index.ingest.records.ExcelGenericRecord;

public class ExcelGenericRecordConverter implements ExcelRecordConverter<ExcelGenericRecord> {
    @Override
    public Person apply(ExcelGenericRecord excelGenericRecord) {
        Person father = null;
        if (excelGenericRecord.father != null && !"".equals(excelGenericRecord.father)) {
            father = Person.builder()
                    .givenName(excelGenericRecord.father)
                    .surname(excelGenericRecord.surname)
                    .build();
        }

        return Person.builder()
                .givenName(excelGenericRecord.givenName)
                .surname(excelGenericRecord.surname)
                .father(father)
                .build();
    }
}
