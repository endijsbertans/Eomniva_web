package lv.venta;

import lv.venta.model.*;
import lv.venta.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class EomnivaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EomnivaApplication.class, args);
    }
    @Bean
    public CommandLineRunner testDatabaseLayer(
            IAddressRepo addressRepo,
            ICustomerAsCompanyRepo customerAsCompanyRepo,
            ICustomerAsPersonRepo customerAsPersonRepo,
            IDriverRepo driverRepo,
            IPersonRepo personRepo,
            IParcelRepo parcelRepo) {
        return new CommandLineRunner() {


            @Override
            public void run(String... args) throws Exception {

                Person pers = new Person("Edijs","12345-12345", "Bertans");
                personRepo.save(pers);
               Driver d2 = new Driver(pers, 3, "AT12122");
               System.out.println(d2);
               driverRepo.save(d2);

               Person pers2 = new Person("Endijs", "123456-12345", "Kalnins");
               personRepo.save(pers2);
               Address addr1 = new Address(City.Ventspils, "Brivibas", 11);
                addressRepo.save(addr1);
               CustomerAsPerson cust3 = new CustomerAsPerson(pers2, addr1, "21792098");
               System.out.println(cust3);
               customerAsPersonRepo.save(cust3);

                Address addr2 = new Address(City.Liepaja, "Talsu", 14);
                addressRepo.save(addr2);
                CustomerAsCompany cust4 = new CustomerAsCompany( addr2, "29666117", "SIA Labais", "LV1234124" );
                customerAsCompanyRepo.save(cust4);
                System.out.println(cust4);

               // Parcel pckg2 = new Parcel(true, 11, Size.M, cust3);
               // parcelRepo.save(pckg2);
               // System.out.println(pckg2);

            }
        };
    }

}