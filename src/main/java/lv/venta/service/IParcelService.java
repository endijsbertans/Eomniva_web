package lv.venta.service;
//i. selectAllParcelsByCustomerId - atgriež visas paciņas konkrētam pircējam;
//ii. selectAllParcelsDeliveredByDriverId - atgriež visas paciņas, kuras ir piegādājis vai piegādās konkrēts šoferis;
//iii. selectAllParcelsPriceLessThan - atgriež visas paciņas, kuras cena ir mazāka par padoto sliekšņa vērtību (piemēram, 5
//                                                                                                              eur);
//iv. selectAllParcelsDeliveredToCity - atgriež visas paciņas, kuras ir jānogādā (vai ir jau nogādātas) uz konkrētu pilsētu;
//v. insertNewParcelByCustomerCodeAndDriverId - pievieno jaunu paciņu konkrētam pircējam un piesaistot to konkrētam
//šoferim pēc tā id;
//vi. changeParcelDriverByParcelIdAndDriverId - nomaina esošās paciņas kurjeru uz citu šoferi, funkcijā padodot gan šofera
//id, gan paciņas id;
//vii. calculateIncomeOfParcelsByCustomerId - aprēķina un atgriež kopējo paciņu summu konkrētajam pircējam, funkcijā
//padodot pircēja id;
//viii. calculateHowManyParcelsNeedToDeliverToday- atgriež paciņu kopskaitu, kuras jānogādā šodien.

import lv.venta.model.City;
import lv.venta.model.Parcel;

import java.util.ArrayList;

public interface IParcelService {
    public abstract ArrayList<Parcel> selectAllParcelsByCustomerId(long id) throws Exception;
    public abstract ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(long id) throws Exception;
    public abstract ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception;
    public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception;
    public Parcel insertNewParcelByCustomerCodeAndDriverId(Parcel parcel, String customerCode, long driverId) throws Exception;
    public abstract Parcel changeParcelDriverByParcelIdAndDriverId(long parcelId, long driverId) throws Exception;
    public abstract double calculateIncomeOfParcelsByCustomerId(long id);
    public abstract int calculateHowManyParcelsNeedToDeliverToday();
}
