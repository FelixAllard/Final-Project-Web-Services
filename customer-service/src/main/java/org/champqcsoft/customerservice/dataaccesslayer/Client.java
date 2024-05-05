package org.champqcsoft.customerservice.dataaccesslayer;
import jakarta.persistence.*;
import org.champqcsoft.customerservice.commons.identifiers.ClientIdentifier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client {
    //Just always have this I guess
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    @Embedded
    private Contact contact;

    @Embedded
    private ClientIdentifier clientIdentifier;

    @Embedded
    private Address address;

    @Embedded
    private Membership membership;
    public Client(@NotNull Contact contact,
                  @NotNull ClientIdentifier clientIdentifier,
                  @NotNull Address address,
                  @NotNull Membership membership
    ){
        this.contact = contact;
        this.clientIdentifier = clientIdentifier;
        this.address = address;
        this.membership = membership;
    }
}
