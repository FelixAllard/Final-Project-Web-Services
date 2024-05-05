package org.champqcsoft.productservice.dataaccesslayer;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Date {
    private int day;
    private int month;
    private int year;

}
