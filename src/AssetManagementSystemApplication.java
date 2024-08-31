import entity.*;
import repository.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AssetManagementSystemApplication {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        server: while(true) {
            System.out.println("Asset Management System");
            System.out.println("-----------------------");
            System.out.println();

            System.out.println("1. New Employee");
            System.out.println("2. New Device");
            System.out.println("3. New Vendor");
            System.out.println("4. New Software");
            System.out.println("5. New License");
            System.out.println("6. No. of installations of a particular software");
            System.out.println("7. No. of software installed in a device");
            System.out.println("8. No. of software installed for an employee");
            System.out.println("9. Amount spend for a software");
            System.out.println("10. Amount spend for an employee");
            System.out.println("11. Amount spend on a vendor");
            System.out.println("12. No. of installations of software from a vendor");
            System.out.println("13. Devices that have an expired software");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    createNewEmployee();
                    break;
                case 2:
                    createNewDevice();
                    break;
                case 3:
                    createNewVendor();
                    break;
                case 4:
                    createNewSoftware();
                    break;
                case 5:
                    createNewLicense();
                    break;
                case 6:
                    getInstallationsForSoftware();
                    break;
                case 7:
                    getSoftwareInstalledInDevice();
                    break;
                case 8:
                    getSoftwareInstalledForEmployee();
                    break;
                case 9:
                    getAmountSpentForSoftware();
                    break;
                case 10:
                    getAmountSpentForEmployee();
                    break;
                case 11:
                    getAmountSpentForVendor();
                    break;
                case 12:
                    getSoftwareInstallationFromVendor();
                    break;
                case 13:
                    getDevicesWithExpiredLicense();
                    break;
                default:
                    break server;
            }
        }


    }

    private static void getDevicesWithExpiredLicense() {
        System.out.println("Devices with expired software");
        List<Device> devices = Repository.getDeviceWithExpiredSoftware();

        devices.forEach(System.out::println);
    }

    private static void getSoftwareInstallationFromVendor() {
        System.out.println("Software installation count from a vendor");
        System.out.println();
        System.out.print("Enter vendor ID: ");
        long id = readLong();
        Vendor vendor = Repository.getVendorById(id);

        long count = Repository.getSoftwareCountByVendor(vendor);
        System.out.println("ANSWER: " + count);
    }

    private static void getAmountSpentForVendor() {
        System.out.println("Amount spend for a vendor");
        System.out.println();
        System.out.print("Enter vendor ID: ");
        long id = readLong();
        Vendor vendor = Repository.getVendorById(id);

        double amount = Repository.getAmountByVendor(vendor);
        System.out.println("ANSWER: " + amount);
    }

    private static void getAmountSpentForEmployee() {
        System.out.println("Amount spend for an employee");
        System.out.println();
        System.out.print("Enter employee ID: ");
        long id = readLong();
        Employee employee = Repository.getEmployeeById(id);

        double amount = Repository.getAmountByEmployee(employee);
        System.out.println("ANSWER: " + amount);
    }

    private static void getAmountSpentForSoftware() {
        System.out.println("Amount spend on a software");
        System.out.println();
        System.out.print("Enter software ID: ");
        long id = readLong();
        Software software = Repository.getSoftwareById(id);

        double amount = Repository.getAmountBySoftware(software);
        System.out.println("ANSWER: " + amount);
    }

    private static void getSoftwareInstalledForEmployee() {
        System.out.println("Software installed count for an employee");
        System.out.println();
        System.out.print("Enter employee ID: ");
        long id = readLong();
        Employee employee = Repository.getEmployeeById(id);

        long count = Repository.getSoftwareCountByEmployee(employee);
        System.out.println("ANSWER: " + count);
    }

    private static void getSoftwareInstalledInDevice() {
        System.out.println("Software Installed count for a device");
        System.out.println();
        System.out.print("Enter device ID: ");
        long id = readLong();
        Device device = Repository.getDeviceById(id);

        long count = Repository.getSoftwareCountByDevice(device);
        System.out.println("ANSWER: " + count);
    }

    private static void getInstallationsForSoftware() {
        System.out.println("Installation count for a Software");
        System.out.println();
        System.out.print("Enter software ID: ");
        long id = readLong();
        Software software = Repository.getSoftwareById(id);
        long count = Repository.getLicenseCountBySoftware(software);

        System.out.println("ANSWER: " + count);
    }

    private static void createNewLicense() {
        System.out.println("New License");
        System.out.println();

        License license = new License();
        System.out.print("Enter software ID: ");
        license.setSoftware(Repository.getSoftwareById(readLong()));
        System.out.print("Enter device ID: ");
        license.setDevice(Repository.getDeviceById(readLong()));
        System.out.print("Enter license amount: ");
        license.setCost(readDouble());
        System.out.print("Enter license validity period (months): ");
        int months = readInt();
        license.setExpiryDate(LocalDate.now().plusMonths(months));
        license.setInstalledDate(LocalDate.now());

        Repository.saveLicense(license);
        System.out.println("SUCCESS: " + license);
    }

    private static void createNewSoftware() {
        System.out.println("New Software");
        System.out.println();

        Software software = new Software();
        System.out.print("Enter name: ");
        software.setName(readStr());
        System.out.print("Enter vendor ID: ");
        software.setVendor(Repository.getVendorById(readLong()));

        Repository.saveSoftware(software);
        System.out.println("SUCCESS: " + software);
    }

    private static void createNewVendor() {
        System.out.println("New Vendor");
        System.out.println();

        Vendor vendor = new Vendor();
        System.out.print("Enter name: ");
        vendor.setName(readStr());

        Repository.saveVendor(vendor);
        System.out.println("SUCCESS: " + vendor);
    }

    private static void createNewDevice() {
        System.out.println("New Device");
        System.out.println();

        Device device = new Device();
        System.out.print("Enter name: ");
        device.setName(readStr());
        System.out.print("Enter employee ID: ");
        device.setEmployee(Repository.getEmployeeById(readLong()));

        Repository.saveDevice(device);
        System.out.println("SUCCESS: " + device);
    }

    private static void createNewEmployee() {
        System.out.println("New Employee");
        System.out.println();

        Employee employee = new Employee();
        System.out.print("Enter name: ");
        employee.setName(readStr());

        Repository.saveEmployee(employee);
        System.out.println("SUCCESS: " + employee);
    }

    static int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    static double readDouble() {
        return Double.parseDouble(scanner.nextLine());
    }

    static long readLong() {
        return Long.parseLong(scanner.nextLine());
    }

    static String readStr() {
        return scanner.nextLine();
    }
}
