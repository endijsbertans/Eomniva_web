package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class CustomerAsPerson extends AbstractCustomer{
// IDC, Custoemr code, Phone no, address, person code, idp

    @OneToOne
    @JoinColumn(name = "Idp")
    private Person person;

    @Column(name = "Person_code")
    private String personCode;

//    @Transient
//    public String getPersonCode() {
//        return person != null ? person.getPersonCode() : null;
//    }
    public CustomerAsPerson(String name, String surname, String personCode, Address address, String phoneNo) {
        super(address, phoneNo);
    }
    public CustomerAsPerson(Person person, Address address, String phoneNo) {
        super(address, phoneNo);
        setPerson(person);
        setPersonCode(person.getPersonCode());
        setCustomerCode();
    }
    @Override
    public void setCustomerCode() {
        super.customerCode  = getIdc() + "_person_" + getPersonCode();
    }
}

