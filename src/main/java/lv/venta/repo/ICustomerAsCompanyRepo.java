package lv.venta.repo;

import lv.venta.model.CustomerAsCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Long> {
}
