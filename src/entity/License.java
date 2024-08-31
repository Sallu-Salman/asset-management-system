package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class License {
    private Long id;
    private Double cost;
    private LocalDate installedDate;
    private LocalDate expiryDate;
    private Software software;
    private Device device;

    private static Long idGenerator = 1L;

    public License() {
        this.id = idGenerator++;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", cost=" + cost +
                ", installedDate=" + installedDate +
                ", expiryDate=" + expiryDate +
                ", software=" + software +
                ", device=" + device +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return Objects.equals(id, license.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(LocalDate installedDate) {
        this.installedDate = installedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
