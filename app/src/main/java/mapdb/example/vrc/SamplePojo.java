package mapdb.example.vrc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SamplePojo implements Serializable {
    int id;
    String str1;
    String str2;
    float float1;
    double double1;
    BigDecimal bigDecimal;
}
