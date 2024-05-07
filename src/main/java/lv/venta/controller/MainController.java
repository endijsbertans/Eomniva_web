package lv.venta.controller;

import lv.venta.service.ICustomerService;
import lv.venta.service.IDriverCRUDService;
import lv.venta.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private IDriverCRUDService driverCRUDService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IParcelService parcelService;
    @GetMapping("/error") // localhost:8080/error
    public String getError() {
        System.out.println("Hello from Spring boot");
        return "error-page";
    }
}
