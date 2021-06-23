package securityservices.core.shared.operation;

public interface Billable {
    public String getCode();
    public String getType();
    public int getClient(); 
    public String getBeginDate();
    public String getFinishDate();
    public double getTotalValue();
}
