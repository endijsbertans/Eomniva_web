package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.City;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.service.IDriverCRUDService;
import org.springframework.ui.Model;
import lv.venta.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/parcel")
public class ParcelController {
    @Autowired
    private IParcelService parcelService;
    @Autowired
    private IDriverCRUDService driverCRUDService;
    @GetMapping("/show/all")
    public String showAllParcels(Model model){
        try {
            model.addAttribute("myobjs", parcelService.selectAllParcels());
            model.addAttribute("driver", driverCRUDService.selectAllDrivers());
            model.addAttribute("title", "All parcels");
            return "show-all-parcels";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @GetMapping("/show/customer")//one?id=2
    public String showAllParcelsByCustomerId(@RequestParam("id") long id, Model model){
        try {
            model.addAttribute("myobjs", parcelService.selectAllParcelsByCustomerId(id));
            model.addAttribute("title", "All parcels by customer");
            model.addAttribute("driver",driverCRUDService.selectAllDrivers());;
            return "show-all-parcels";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @GetMapping("/show/driver")
    public String showAllParcelsByDriverId(@RequestParam("id") long id, Model model){
        try {
            model.addAttribute("myobjs", parcelService.selectAllParcelsDeliveredByDriverId(id));
            model.addAttribute("title", "All parcels by driver");
            return "show-all-parcels";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @GetMapping("/show/city")
    public String showAllParcelsDeliveredToCity(@RequestParam("city") String city, Model model){
        try {

            model.addAttribute("myobjs", parcelService.selectAllParcelsDeliveredToCity(City.valueOf(city)));
            model.addAttribute("title", "All parcels delivered to city");
            return "show-all-parcels";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @GetMapping("/show/price")
    public String showAllParcelsByPrice(@RequestParam("price") float price, Model model){
        try {
            model.addAttribute("myobjs", parcelService.selectAllParcelsPriceLessThan(price));
            model.addAttribute("title", "All parcels cheaper than " + price + "€");
            return "show-all-parcels";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @GetMapping("/add/{customerCode}/{driverId}")
    public String addParcel(@PathVariable("customerCode") String customerCode, @PathVariable("driverId") long driverId, Model model){
        try {
            model.addAttribute("parcel", new Parcel());
            model.addAttribute("customerCode", customerCode);
            model.addAttribute("driverId", driverId);
            model.addAttribute("title", "Parcel added");
            return "insert-parcel-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
    @PostMapping("/add/{customerCode}/{driverId}")
    public String postDriverAdd(@PathVariable("customerCode") String customerCode, @PathVariable("driverId") long driverId, Parcel parcel, BindingResult result, Model model){

            try {
                parcelService.insertNewParcelByCustomerCodeAndDriverId(parcel, customerCode, driverId);
                return "redirect:/parcel/show/driver?id=" + driverId;
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page";
            }

    }
    @GetMapping("/change/{parcelid}")
    public String changeParcelDriver(@PathVariable("parcelid") long parcelId, @RequestParam("driverId") long driverId, Model model){
        try {
            model.addAttribute("title", "Change driver");
            model.addAttribute("myobjs", parcelService.selectAllParcels());
            model.addAttribute("driverId", driverId);
            model.addAttribute("driver", driverCRUDService.selectAllDrivers());
            model.addAttribute("parcel", parcelService.changeParcelDriverByParcelIdAndDriverId(parcelId, driverId));
            return "redirect:/parcel/show/all";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page";
        }
    }
}
