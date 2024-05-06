package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@Table(name = "DRIVER_TABLE")
@Entity
//@PrimaryKeyJoinColumn(name = "Idp")
public class Driver extends Person{

//    @Setter(value = AccessLevel.NONE)
//    @Column(name = "Ida")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    private long ida;

//    @NotNull
//    @OneToOne(cascade = CascadeType.ALL)
//   @JoinColumn(name = "Idp")
//    @MapsId
//
//    private Person person;

    @NotNull
    @Column(name = "Experience_in_years")
    @Min(0)
    private float experienceInYears;

    @NotNull
    @Column(name = "License_no")
    @Pattern(regexp = "[A]{1}[T]{1}\\d{5,7}")
    private String licenseNo;

//    @NotNull
//    @OneToOne
//    @JoinColumn(name = "Person_code")
//    private Person person2;
    public Driver(Person person, float experienceInYears, String licenseNo){
        //super(person.getName(), person.getPersonCode(), person.getSurname(), person.getPhoneNo());
        super(person);
        //this.person = person;
        setExperienceInYears(experienceInYears);
        setLicenseNo(licenseNo);
    }
}
