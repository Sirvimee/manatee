package ee.cyber.manatee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String interviewer;

    @NotNull
    private OffsetDateTime interviewTime;
}
