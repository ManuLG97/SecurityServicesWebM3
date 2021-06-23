package securityservices.core.shared.products;

public interface Storable {
    public String getCode();
    public String getDimensions();
    public Double getVolum();
    public Double getWeight();
    public Boolean isFragile();
}
