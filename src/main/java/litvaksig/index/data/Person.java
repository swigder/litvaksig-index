package litvaksig.index.data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@EqualsAndHashCode
public class Person {
    private String givenName;
    private String surname;
    private String birthSurname;

    private LocalDate birthDate;
    private Place birthPlace;

    private Person father;
    private Person mother;

    @Override
    public String toString() {
        return "Person{" +
                "givenName='" + givenName + '\'' +
                (surname != null ? ", surname='" + surname + '\'' : "") +
                (birthSurname != null ? ", birthSurname='" + birthSurname + '\'' : "") +
                (birthDate != null ? ", birthDate='" + birthDate + '\'' : "") +
                (birthPlace != null ? ", birthPlace='" + birthPlace + '\'' : "") +
                (father != null ? ", father='" + father + '\'' : "") +
                (mother != null ? ", mother='" + mother + '\'' : "") +
                '}';
    }
}
