package lv.venta.repo;

import lv.venta.model.CustomerAsPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson, Long>{
}
