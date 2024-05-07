package lv.venta.repo;

import lv.venta.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson, Long>{
    CustomerAsPerson findByPersonAndPhoneNo(Person person, String phoneNo);

    Optional<Object> findByCustomerCode(String customerCode);

    Collection<? extends Parcel> findByAddressCity(City city);
}
