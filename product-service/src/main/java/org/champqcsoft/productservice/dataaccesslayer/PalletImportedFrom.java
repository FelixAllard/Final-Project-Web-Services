package org.champqcsoft.productservice.dataaccesslayer;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PalletImportedFrom {
    private int palletId;
    private String manufacturer;
    @Embedded
    private Date arrivalDate;
}
