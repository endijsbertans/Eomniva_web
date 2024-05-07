package lv.venta.service.impl;

import lv.venta.model.*;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.service.ICustomerService;
import lv.venta.service.IDriverCRUDService;
import lv.venta.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service

public class ServiceImpl implements ICustomerService, IDriverCRUDService, IParcelService {
    @Autowired
    private ICustomerAsPersonRepo customerAsPersonRepo;
    @Autowired
    private ICustomerAsCompanyRepo customerAsCompanyRepo;
    @Autowired
    private IDriverRepo driverRepo;
    @Autowired
    private IParcelRepo parcelRepo;
    @Override
    public void insertNewCustomerAsPerson(CustomerAsPerson person) throws Exception {
        if(person == null) throw new Exception("Person is null");
        CustomerAsPerson customerFromDB = customerAsPersonRepo.findByPersonAndPhoneNo(person.getPerson(), person.getPhoneNo());
        if(customerFromDB != null) {
            throw new Exception("Customer already exists");
        } else {
            customerAsPersonRepo.save(person);
        }
    }

    @Override
    public void insertNewCustomerAsCompany(CustomerAsCompany company) throws Exception {
        if(company == null) throw new Exception("Company is null");
        CustomerAsCompany customerFromDB = customerAsCompanyRepo.findByPhoneNoAndTitleAndCompanyRegNo(company.getPhoneNo(), company.getTitle(), company.getCompanyRegNo());
        if(customerFromDB != null) {
            throw new Exception("Customer already exists");
        } else {
            customerAsCompanyRepo.save(company);
        }
    }

    @Override
    public void addAddressToCustomerByCustomerId(Address address, long customerId) throws Exception{
        if(address == null || customerId < 0) throw new IllegalArgumentException("Address or customerId is invalid");

        CustomerAsPerson personCustomer = customerAsPersonRepo.findById(customerId).orElse(null);
        CustomerAsCompany companyCustomer = customerAsCompanyRepo.findById(customerId).orElse(null);

        if (personCustomer != null) {
            personCustomer.setAddress(address);
        } else if (companyCustomer != null) {
            companyCustomer.setAddress(address);
        } else {
            throw new Exception("Customer not found");
        }
    }

    @Override
    public ArrayList<Driver> selectAllDrivers() {
        return (ArrayList<Driver>) driverRepo.findAll();
    }

    @Override
    public Driver selectDriverById(long id) throws Exception {
        if(id < 0) throw new Exception("Id should be positive");
        if(driverRepo.existsById(id))
        {
            return driverRepo.findById(id).get();
        }
        throw new Exception("Driver with " + id + " is not found");
    }

    @Override
    public Driver deleteDriverById(long id) throws Exception {
        Driver deleteProduct = selectDriverById(id);
        driverRepo.delete(deleteProduct);
        return deleteProduct;
    }

    @Override
    public Driver insertNewDriver(Driver driver) throws Exception {
        if(driver == null) throw new Exception("Company is null");
        Driver driverFromDB = driverRepo.findByNameAndSurnameAndPersonCodeAndExperienceInYearsAndLicenseNo(driver.getName(), driver.getSurname(), driver.getPersonCode(), driver.getExperienceInYears(), driver.getLicenseNo());
        if(driverFromDB != null) {
            throw new Exception("Customer already exists");
        } else {
            return driverRepo.save(driver);
        }
    }

    @Override
    public Driver updateDriverById(long id, Driver driver) throws Exception {
         Driver updateDriver = selectDriverById(id);

        updateDriver.setName(driver.getName());
        updateDriver.setSurname(driver.getSurname());
        updateDriver.setPersonCode(driver.getPersonCode());
        updateDriver.setExperienceInYears(driver.getExperienceInYears());
        updateDriver.setLicenseNo(driver.getLicenseNo());
        return driverRepo.save(updateDriver);//notiek Update arī DB līmenī
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsByCustomerId(long id) throws Exception {
        if(id <= 0) throw new Exception("Id should be positive");
        System.out.println(customerAsPersonRepo.findById(id).orElse(null) == null);
        System.out.println(customerAsCompanyRepo.findById(id).orElse(null) == null);
        if(customerAsPersonRepo.findById(id).orElse(null) == null && customerAsCompanyRepo.findById(id).orElse(null) == null)  throw new Exception("Customer with id (" + id + ") doesn't exist");

        ArrayList<Parcel> result = parcelRepo.findByAbstractCustomerIdc(id);
        if (result.isEmpty()) throw new Exception("There are no parcels for this customer");
        return result;
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(long id) throws Exception {
        if(id <= 0) throw new Exception("Id should be positive");
        if(!driverRepo.existsById(id)) throw new Exception("Driver with id (" + id + ") doesn't exist");

        ArrayList<Parcel> result =  parcelRepo.findByIdpa(id);
        if(result.isEmpty()) throw new Exception("There are no parcels for this driver");

        return result;
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsPriceLessThan(double price) throws Exception {
        if(price <= 0) throw new Exception("Price should be positive");

        ArrayList<Parcel> result = parcelRepo.findByPriceLessThan(price);

        if(result.isEmpty()) throw new Exception("there are no parcels with price less than " + price + "€");

        return result;
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception {
        if(city == null) throw new Exception("City is null");

        ArrayList<Parcel> result = customerAsCompanyRepo.findByAddressCity(city);
        result.addAll(customerAsPersonRepo.findByAddressCity(city));
        if(result.isEmpty()) throw new Exception("There are no parcels for this city");

        return result;
    }

    @Override
    public Parcel insertNewParcelByCustomerCodeAndDriverId(Parcel parcel, String customerCode, long driverId) throws Exception {
        if(customerCode == null) throw new Exception("CustomerCode null");
        if(driverId <= 0) throw new Exception("Id should be positive");

        if(!driverRepo.existsById(driverId)) throw new Exception("Driver with id (" + driverId + ") doesn't exist");
        if(     customerAsPersonRepo.findByCustomerCode(customerCode).orElse(null) == null ||
                customerAsCompanyRepo.findByCustomerCode(customerCode).orElse(null) == null)
            throw new Exception("Customer with id (" + customerCode + ") doesn't exist");

        CustomerAsPerson personCustomer = (CustomerAsPerson) customerAsPersonRepo.findByCustomerCode(customerCode).orElse(null);
        CustomerAsCompany companyCustomer = (CustomerAsCompany) customerAsCompanyRepo.findByCustomerCode(customerCode).orElse(null);

        if (personCustomer != null) {
            parcel.setDriver(driverRepo.findById(driverId).get());
            personCustomer.addParcel(parcel);
        } else if (companyCustomer != null) {
            parcel.setDriver(driverRepo.findById(driverId).get());
            personCustomer.addParcel(parcel);
        } else {
            throw new Exception("Customer not found");
        }
        return parcel;
    }

    @Override
    public Parcel changeParcelDriverByParcelIdAndDriverId(long parcelId, long driverId) throws Exception {
        Driver driver = selectDriverById(driverId);
        Parcel parcel = parcelRepo.findById(parcelId).orElse(null);

        if(parcel == null && driver == null) throw new Exception("Parcel or driver not found");
        parcel.setDriver(driver);
        return parcelRepo.save(parcel);
    }

    @Override
    public double calculateIncomeOfParcelsByCustomerId(long id) {
        double totalIncome = 0.0;
        ArrayList<Parcel> parcels = parcelRepo.findByAbstractCustomerIdc(id);
        for (Parcel parcel : parcels) {
            totalIncome += parcel.getPrice();
        }
        return totalIncome;
    }

    @Override
    public int calculateHowManyParcelsNeedToDeliverToday() {
        int count = 0;

        ArrayList<Parcel> allParcels = (ArrayList<Parcel>) parcelRepo.findAll();
        for (Parcel parcel : allParcels) {
            if (parcel.getPlannedDelivery().equals(LocalDateTime.now())) {
                count++;
            }
        }
        return count;
    }
}
