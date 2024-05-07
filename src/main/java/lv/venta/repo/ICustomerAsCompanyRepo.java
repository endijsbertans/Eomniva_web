package lv.venta.repo;

import lv.venta.model.Address;
import lv.venta.model.CustomerAsCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Long> {
    CustomerAsCompany findByPhoneNoAndTitleAndCompanyRegNo(String phoneNo, String title, String companyRegNo);

    Optional<Object> findByCustomerCode(String customerCode);
}
