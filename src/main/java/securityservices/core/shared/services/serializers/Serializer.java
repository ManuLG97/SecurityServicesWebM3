package securityservices.core.shared.services.serializers;

import securityservices.shared.responses.ResultRequest;

public interface Serializer<T> {
    public ResultRequest <String> serialize( T o);
    public ResultRequest<T> unserialize( String data);
}