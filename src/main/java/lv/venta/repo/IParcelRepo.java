package lv.venta.repo;

import lv.venta.model.Parcel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParcelRepo extends CrudRepository<Parcel, Long>{
}
