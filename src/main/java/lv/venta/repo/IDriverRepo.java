package lv.venta.repo;

import lv.venta.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDriverRepo extends IPersonRepo {
    //CrudRepository<Driver, Long>
}
