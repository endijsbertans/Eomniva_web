package lv.venta.controller;

import org.springframework.ui.Model;
import lv.venta.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
