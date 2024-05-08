package lv.venta.service;
//i. insertNewCustomerAsPerson - pievieno sistēmā jaunu pircēju kā personu, bet nepievienojot adresi. Neaizmirstam
//pārbaudīt, vai šāds pircējs jau neeksistē.
//        ii. insertNewCustomerAsCompany - pievieno sistēmā jaunu pircēju kā kompāniju, bet nepievienojot adresi. Neaizmirstam
//pārbaudīt, vai šāda kompānija jau neeksistē.
//        iii. addAddressToCustomerByCustomerId - izveido un pievieno konkrētajam pircējam adresi.


import lv.venta.model.Address;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;

import java.util.ArrayList;

public interface ICustomerService {
    public void insertNewCustomerAsPerson(CustomerAsPerson person) throws Exception;
    public void insertNewCustomerAsCompany(CustomerAsCompany company) throws Exception;
    public void addAddressToCustomerByCustomerId(Address address, long customerId) throws Exception;
    public ArrayList<String> retrieveAllCustomerCode();
}
