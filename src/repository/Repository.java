package repository;

import entity.*;
import storage.Database;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Repository {
    public static Device getDeviceById(Long id) {
        return Database.deviceTable.get(id);
    }

    public static Employee getEmployeeById(Long id) {
        return Database.employeeTable.get(id);
    }

    public static License getLicenseById(Long id) {
        return Database.licenseTable.get(id);
    }

    public static Software getSoftwareById(Long id) {
        return Database.softwareTable.get(id);
    }

    public static Vendor getVendorById(Long id) {
        return Database.vendorTable.get(id);
    }

    public static void saveDevice(Device device) {
        Database.deviceTable.put(device.getId(), device);
    }

    public static void saveEmployee(Employee emplyee) {
        Database.employeeTable.put(emplyee.getId(), emplyee);
    }

    public static void saveLicense(License license) {
        Database.licenseTable.put(license.getId(), license);
    }

    public static void saveSoftware(Software software) {
        Database.softwareTable.put(software.getId(), software);
    }

    public static void saveVendor(Vendor vendor) {
        Database.vendorTable.put(vendor.getId(), vendor);
    }


    public static long getLicenseCountBySoftware(Software software) {
        return Database.licenseTable.values()
                .stream()
                .filter(li -> li.getSoftware().equals(software))
                .count();
    }

    public static long getSoftwareCountByDevice(Device device) {
        Set<Long> software = new HashSet<>();
        Database.licenseTable.values()
                .stream()
                .filter(li -> li.getDevice().equals(device))
                .forEach(li -> software.add(li.getSoftware().getId()));

        return software.size();
    }

    public static long getSoftwareCountByEmployee(Employee employee) {
        Set<Long> software = new HashSet<>();

        Database.licenseTable.values()
                .stream()
                .filter(li -> li.getDevice().getEmployee().equals(employee))
                .forEach(li -> software.add(li.getSoftware().getId()));

        return software.size();
    }

    public static double getAmountBySoftware(Software software) {
        double amount =
            Database.licenseTable.values()
                .stream()
                .filter(li -> li.getSoftware().equals(software))
                .mapToDouble(License::getCost).sum();
        return amount;
    }

    public static double getAmountByEmployee(Employee employee) {
        return
                Database.licenseTable.values()
                        .stream()
                        .filter(li -> li.getDevice().getEmployee().equals(employee))
                        .mapToDouble(License::getCost)
                        .sum();
    }

    public static double getAmountByVendor(Vendor vendor) {
        return
                Database.licenseTable.values()
                        .stream()
                        .filter(li -> li.getSoftware().getVendor().equals(vendor))
                        .mapToDouble(License::getCost)
                        .sum();
    }

    public static long getSoftwareCountByVendor(Vendor vendor) {
        return
                Database.licenseTable.values()
                        .stream()
                        .filter(li -> li.getSoftware().getVendor().equals(vendor))
                        .count();
    }

    public static List<Device> getDeviceWithExpiredSoftware() {
        return
        Database.licenseTable.values()
                .stream()
                .filter(li -> li.getExpiryDate().isBefore(LocalDate.now()))
                .map(License::getDevice)
                .toList();

    }

}
















