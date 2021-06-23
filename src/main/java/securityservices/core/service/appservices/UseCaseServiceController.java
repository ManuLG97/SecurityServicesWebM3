package securityservices.core.service.appservices;

import securityservices.infrastructure.filesystemadapters.FileManager;
import securityservices.shared.responses.ResultRequest;

public class UseCaseServiceController {
    private FileManager fileManager = new FileManager();
    
    public ResultRequest<String> getJsonResource(){        
        ResultRequest<String> dataServiceFile = fileManager.read("E:\\files\\service_02_03_2021_21_52_54.json");
        if (dataServiceFile.failed()==false) {
            return dataServiceFile;
        } 
        return ResultRequest.fails("Not Found");
    }
    
    public ResultRequest<String> getXmlResource(){
        ResultRequest<String> dataServiceFile = fileManager.read("E:\\files\\service_02_03_2021_21_53_08.xml");
        if (dataServiceFile.failed()==false) {
            return dataServiceFile;
        } 
        return ResultRequest.fails("Not Found");
    }  
}
