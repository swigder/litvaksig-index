package litvaksig.index.ingest.converters;

import litvaksig.index.data.Person;
import litvaksig.index.data.Place;
import litvaksig.index.ingest.records.ExcelBirthRecord;

import java.time.DateTimeException;
import java.time.LocalDate;

public class ExcelBirthRecordConverter implements ExcelRecordConverter<ExcelBirthRecord> {
    @Override
    public Person apply(ExcelBirthRecord excelBirthRecord) {
        Person maternalGrandfather = null;
        if (excelBirthRecord.mothersFather != null) {
            maternalGrandfather = Person.builder()
                    .givenName(excelBirthRecord.mothersFather)
                    .surname(excelBirthRecord.motherMaiden)
                    .build();
        }

        Person mother = Person.builder()
                .givenName(excelBirthRecord.mother)
                .surname(excelBirthRecord.surname)
                .birthSurname(excelBirthRecord.motherMaiden)
                .father(maternalGrandfather)
                .build();

        Person paternalGrandfather = null;
        if (excelBirthRecord.fathersFather != null) {
            paternalGrandfather = Person.builder()
                    .givenName(excelBirthRecord.fathersFather)
                    .surname(excelBirthRecord.surname)
                    .build();
        }

        Person father = Person.builder()
                .givenName(excelBirthRecord.father)
                .surname(excelBirthRecord.surname)
                .father(paternalGrandfather)
                .build();

        LocalDate birthDate = null;
        try {
            birthDate = LocalDate.of(
                    excelBirthRecord.year,
                    excelBirthRecord.month,
                    excelBirthRecord.day);
        } catch (NullPointerException | DateTimeException e) {
            e.printStackTrace();
        }

        Place birthPlace = Place.builder()
                .town(excelBirthRecord.town)
                .guberniya(excelBirthRecord.guberniya)
                .build();

        return Person.builder()
                .givenName(excelBirthRecord.givenName)
                .surname(excelBirthRecord.surname)
                .father(father)
                .mother(mother)
                .birthDate(birthDate)
                .birthPlace(birthPlace)
                .build();
    }
}
