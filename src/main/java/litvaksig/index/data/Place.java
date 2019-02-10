package litvaksig.index.data;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Place {
    private String town;
    private String guberniya;
}
