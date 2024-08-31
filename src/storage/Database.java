package storage;

import entity.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Database {
    public static Map<Long, Device> deviceTable = new LinkedHashMap<>();
    public static Map<Long, Employee> employeeTable = new LinkedHashMap<>();
    public static Map<Long, License> licenseTable = new LinkedHashMap<>();
    public static Map<Long, Software> softwareTable = new LinkedHashMap<>();
    public static Map<Long, Vendor> vendorTable = new LinkedHashMap<>();
}
