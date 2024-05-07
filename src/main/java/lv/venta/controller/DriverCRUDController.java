package lv.venta.controller;

import org.springframework.ui.Model;
import lv.venta.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driver")

public class DriverCRUDController {
    @Autowired
    private IDriverCRUDService driverCRUDService;

    @GetMapping("/show/all")
    public String showAllDrivers(Model model){
        try {
            model.addAttribute("myobjs", driverCRUDService.selectAllDrivers());
            System.out.println(driverCRUDService.selectAllDrivers().size());
            return "show-all-drivers";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }
}
