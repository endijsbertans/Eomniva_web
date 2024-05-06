package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@Table(name = "CUSTOMER_AS_COMPANY_TABLE")
@Entity
@PrimaryKeyJoinColumn(name = "Idp")
public class CustomerAsCompany extends AbstractCustomer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idc;

//    @NotNull
//    @OneToOne
//    @JoinColumn(name = "address_id")
//    private Address address;


    // TODO make custome one
    @NotNull
    @Column(name = "Company_regNo")
    @Pattern(regexp = "LV\\d+")
    private String companyRegNo;

    @NotNull
    @Column(name = "Title")
    private String title;

//    @OneToMany(
//            mappedBy = "customerAsCompany",
//            cascade = CascadeType.ALL
//    )//ir otras klases mainīgā nosaukums
//    @ToString.Exclude
//    private Collection<Parcel> parcels;
    public CustomerAsCompany(Address address, String phoneNo, String title, String companyRegNo) {
        super(address, phoneNo);
        setTitle(title);
        setCompanyRegNo(companyRegNo);
        setCustomerCode();
    }

    @Override
    public void setCustomerCode() {
        super.customerCode  = getIdc() + "_company_" + companyRegNo;
    }
}