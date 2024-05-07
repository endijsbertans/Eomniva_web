package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractCustomer {
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idc")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long idc;

    @NotNull
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Customer_code")
    protected String customerCode;
    public abstract void setCustomerCode();

    @OneToMany(
            mappedBy = "abstractCustomer",
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private Collection<Parcel> parcels;
    public void addParcel(Parcel parcel){
        if(parcels == null){
            parcels = new ArrayList<>();
        }
        parcels.add(parcel);
    }
    @NotNull
    @OneToOne
    @JoinColumn(name = "Address")
    private Address address;

    @NotNull
    @Column(name = "Phone_no")
    @Pattern(regexp = "2\\d{7}")
    private String phoneNo;



    public AbstractCustomer(Address address, String phoneNo) {
        setAddress(address);
        setPhoneNo(phoneNo);

    }
}