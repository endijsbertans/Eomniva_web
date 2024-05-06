//package lv.venta.model;
//
//import jakarta.persistence.Entity;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@NoArgsConstructor
//@Getter
//@Setter
//public abstract class AbstractCustomerAsPerson extends AbstractCustomer {
//    protected Person person;
//
//    AbstractCustomerAsPerson(String name, String surname, String personCode, Address address, String phoneNO){
//        super(address, phoneNO);
//        this.person = new Person(name, surname, personCode);
//    }
//
//    @Override
//    public String toString() {
//        return   super.toString() +
//
//                "AbstractCustomerAsPerson{" +
//                "person=" + person +
//                '}';
//    }
//}