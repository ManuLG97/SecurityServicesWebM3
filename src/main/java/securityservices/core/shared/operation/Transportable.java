package securityservices.core.shared.operation;

import securityservices.core.shared.products.Storable;

public interface Transportable extends Storable {
    public String getDeliveryAddress();
    public String getReceiverName();
    public String getTransporter();
}
