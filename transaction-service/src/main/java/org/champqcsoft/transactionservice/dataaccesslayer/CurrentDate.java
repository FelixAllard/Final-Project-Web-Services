package org.champqcsoft.transactionservice.dataaccesslayer;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentDate {
    private int transactionHour;
    @Embedded
    private Date date;

}
