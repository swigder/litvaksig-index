package litvaksig.index.data;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class Person {
    private String givenName;
    private String surname;
    private String birthSurname;

    private LocalDate birthDate;
    private Place birthPlace;

    private Person father;
    private Person mother;
}
