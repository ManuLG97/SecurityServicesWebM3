package securityservices.core.equipment.appservices;

import securityservices.infrastructure.filesystemadapters.FileManager;
import securityservices.shared.responses.ResultRequest;

public class UseCaseEquipmentController {
    private FileManager fileManager = new FileManager();
    
    public ResultRequest<String> getJsonResource(){        
        ResultRequest<String> dataEquipmentFile = fileManager.read("E:\\files\\equipment_02_03_2021_21_51_18.json");
        if (dataEquipmentFile.failed()==false) {
            return dataEquipmentFile;
        } 
        return ResultRequest.fails("Not Found");
    }
    
    public ResultRequest<String> getXmlResource(){
        ResultRequest<String> dataEquipmentFile = fileManager.read("E:\\files\\equipment_02_03_2021_21_51_33.xml");
        if (dataEquipmentFile.failed()==false) {
            return dataEquipmentFile;
        } 
        return ResultRequest.fails("Not Found");
    }  
}
