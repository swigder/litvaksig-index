package litvaksig.index.excel.converter;

import litvaksig.index.data.Person;
import litvaksig.index.data.Place;
import litvaksig.index.ingest.converters.ExcelBirthRecordConverter;
import litvaksig.index.ingest.records.ExcelBirthRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ExcelBirthRecordConverterTest {
    private static final String GIVEN_NAME = "Anne";
    private static final String SURNAME = "Balshevis";
    private static final String FATHER = "Chaim";
    private static final String FATHERS_FATHER = "David";
    private static final String MOTHER = "Ester";
    private static final String MOTHERS_FATHER = "Favish";
    private static final String MOTHER_MAIDEN = "Gershowitz";
    private static final int DAY = 30;
    private static final int MONTH = 5;
    private static final int YEAR = 1879;
    private static final String TOWN = "Kaunas";
    private static final String GUBERNIYA = "Suwalki";

    private Person.PersonBuilder DEFAULT_FATHERS_FATHER_BUILDER = Person.builder()
            .givenName(FATHERS_FATHER)
            .surname(SURNAME);

    private Person.PersonBuilder DEFAULT_FATHER_BUILDER = Person.builder()
            .givenName(FATHER)
            .surname(SURNAME)
            .father(DEFAULT_FATHERS_FATHER_BUILDER.build());

    private Person.PersonBuilder DEFAULT_MOTHERS_FATHER_BUILDER = Person.builder()
            .givenName(MOTHERS_FATHER)
            .surname(MOTHER_MAIDEN);

    private Person.PersonBuilder DEFAULT_MOTHER_BUILDER = Person.builder()
            .givenName(MOTHER)
            .surname(SURNAME)
            .birthSurname(MOTHER_MAIDEN)
            .father(DEFAULT_MOTHERS_FATHER_BUILDER.build());

    private Place.PlaceBuilder DEFAULT_PLACE_BUILDER = Place.builder()
            .town(TOWN)
            .guberniya(GUBERNIYA);

    private Person.PersonBuilder DEFAULT_PERSON_BUILDER = Person.builder()
            .givenName(GIVEN_NAME)
            .surname(SURNAME)
            .mother(DEFAULT_MOTHER_BUILDER.build())
            .father(DEFAULT_FATHER_BUILDER.build())
            .birthDate(LocalDate.of(1879, 5, 30))
            .birthPlace(DEFAULT_PLACE_BUILDER.build());


    private ExcelBirthRecord generateDefaultRecord() {
        ExcelBirthRecord record = new ExcelBirthRecord();
        record.givenName = GIVEN_NAME;
        record.surname = SURNAME;
        record.father = FATHER;
        record.fathersFather = FATHERS_FATHER;
        record.mother = MOTHER;
        record.mothersFather = MOTHERS_FATHER;
        record.motherMaiden = MOTHER_MAIDEN;
        record.day = DAY;
        record.month = MONTH;
        record.year = YEAR;
        record.town = TOWN;
        record.guberniya = GUBERNIYA;
        return record;
    }

    private ExcelBirthRecordConverter converter = new ExcelBirthRecordConverter();

    @Test
    void convertsCorrectlyWhenEverythingIsSet() {
        ExcelBirthRecord inputRecord = generateDefaultRecord();

        assertThat(converter.apply(inputRecord), equalTo(DEFAULT_PERSON_BUILDER.build()));
    }

    @Test
    void convertsWithoutDateOfBirthWhenMonthIsInvalid() {
        ExcelBirthRecord inputRecord = generateDefaultRecord();
        inputRecord.month = 13;

        assertThat(converter.apply(inputRecord), equalTo(DEFAULT_PERSON_BUILDER.birthDate(null).build()));
    }

    @Test
    void convertsWhenMothersMaidenNameIsMissing() {
        ExcelBirthRecord inputRecord = generateDefaultRecord();
        inputRecord.motherMaiden = null;

        Person mothersFather = DEFAULT_MOTHERS_FATHER_BUILDER.surname(null).build();
        Person mother = DEFAULT_MOTHER_BUILDER.birthSurname(null).father(mothersFather).build();
        assertThat(converter.apply(inputRecord), equalTo(DEFAULT_PERSON_BUILDER.mother(mother).build()));
    }

    @Test
    void doesNotCreateGrandparentIfMissing() {
        ExcelBirthRecord inputRecord = generateDefaultRecord();
        inputRecord.mothersFather = null;

        Person mother = DEFAULT_MOTHER_BUILDER.father(null).build();
        assertThat(converter.apply(inputRecord), equalTo(DEFAULT_PERSON_BUILDER.mother(mother).build()));
    }
}