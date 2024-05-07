package lv.venta.service;

//. selectAllDriver - atgriež visus šoferus, kas ir saglabāti sistēmā
//ii. selectDriverById - atgriež vienu šoferi pēc tā id
//iii. deleteDriverById - dzēš šoferi pēc tā id
//iv. insertNewDriver - pievieno jaunu šoferi sistēmā
//v. updateDriverById - rediģē esošo šoferi

import lv.venta.model.Driver;

import java.util.ArrayList;

public interface IDriverCRUDService {
    public abstract ArrayList<Driver> selectAllDriver();
    public abstract Driver selectDriverById(long id) throws Exception;
    public abstract Driver deleteDriverById(long id) throws Exception;
    public abstract Driver insertNewDriver(Driver driver) throws Exception;
    public abstract Driver updateDriverById(long id, Driver driver) throws Exception;
}
