package securityservices.core.equipment.domain.model;

import securityservices.core.shared.products.Product;


public class Software extends Product {
    protected String version, os, medium;

    public Software() {
    }

    public Software(String code, String name, String type, String maker, String description, double price,
            String version, String os, String medium) {
        super(code, name, type, maker, description, price);
        this.version = version;
        this.os = os;
        this.medium = medium;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public String getDetails() {
        return "Version:" + this.version + ";OS:" + this.os + ";Medium:" + this.medium +
                ";Especifications:" + this.description;
    }   
}