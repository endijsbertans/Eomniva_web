package lv.venta.controller;

import jakarta.validation.Valid;
import lv.venta.model.City;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
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
    @GetMapping("/show/customer")//one?id=2
    public String showAllParcelsByCustomerId(@RequestParam("id") long id, Model model){
        try {
            model.addAttribute("myobjs", parcelService.selectAllParcelsByCustomerId(id));
            model.addAttribute("title", "All parcels by customer");
            return "show-all-parcels";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page-parcels.html";
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
            return "error-page-parcels.html";
        }
    }
    @GetMapping("/show/price")
    public String showAllParcelsByPrice(@RequestParam("price") float price, Model model){
        try {
            model.addAttribute("myobjs", parcelService.selectAllParcelsPriceLessThan(price));
            model.addAttribute("title", "All parcels cheaper than");
            return "show-all-parcels";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("title", "Error Page");
            return "error-page-parcels.html";
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
            return "error-page-parcels.html";
        }
    }
    @PostMapping("/add/{customerCode}/{driverId}")
    public String postDriverAdd(@PathVariable("customerCode") String customerCode, @PathVariable("driverId") long driverId, @Valid Parcel parcel, BindingResult result, Model model){
//        if(result.hasErrors()){
//            model.addAttribute("customerCode", customerCode);
//            model.addAttribute("driverId", driverId);
//            return "insert-parcel-page";
//        }else{
            try {
                parcelService.insertNewParcelByCustomerCodeAndDriverId(parcel, customerCode, driverId);
                return "redirect:/parcel/show/driver?id=" + driverId;
            } catch (Exception e) {
                model.addAttribute("msg", e.getMessage());
                model.addAttribute("title", "Error Page");
                return "error-page-parcels.html";
            }
        //}
    }
}
