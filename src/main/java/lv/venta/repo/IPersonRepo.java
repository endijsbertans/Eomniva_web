package lv.venta.repo;

import lv.venta.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface IPersonRepo extends CrudRepository<Person, Long> {
}
