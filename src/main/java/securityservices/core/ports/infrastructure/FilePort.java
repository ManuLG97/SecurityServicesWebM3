package securityservices.core.ports.infrastructure;

import securityservices.shared.responses.ResultRequest;

public interface FilePort {
    public ResultRequest<String> read(String filename);
    public ResultRequest<String> write(String data, String filename);    
}
