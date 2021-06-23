package securityservices.core.order.appservices;

import securityservices.infrastructure.filesystemadapters.FileManager;
import securityservices.shared.responses.ResultRequest;

public class UseCaseOrderController {
    private FileManager fileManager = new FileManager();
    
    public ResultRequest<String> getJsonResource(){        
        ResultRequest<String> dataOrderFile = fileManager.read("E:\\files\\order_02_03_2021_21_52_12.json");
        if (dataOrderFile.failed()==false) {
            return dataOrderFile;
        } 
        return ResultRequest.fails("Not Found");
    }
    
    public ResultRequest<String> getXmlResource(){
        ResultRequest<String> dataOrderFile = fileManager.read("E:\\files\\order_02_03_2021_21_52_27.xml");
        if (dataOrderFile.failed()==false) {
            return dataOrderFile;
        } 
        return ResultRequest.fails("Not Found");
    }  
}
