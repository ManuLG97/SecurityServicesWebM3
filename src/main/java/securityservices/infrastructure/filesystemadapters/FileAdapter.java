package securityservices.infrastructure.filesystemadapters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import securityservices.core.ports.infrastructure.FilePort;
import securityservices.shared.responses.ResultRequest;

public class FileAdapter implements FilePort{

    public FileAdapter() {
    }

    @Override
    public ResultRequest<String> read(String filename) {
        if (filename == null || filename.trim().equals("")){
            return ResultRequest.fails("{\"Error\":\"Invalid filename\"}");
        }
            
        //LECTURA BASADA EN BUFFEREDREADER E INPUTSTREAM    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            String strBuffer = "", line;
            while ((line = reader.readLine()) != null) {
                strBuffer += line;
            }
            /*//ALTERNATIVA CON FILEREADER 
            FileReader reader = new FileReader(new File(source.toString()));
            char charBuffer[] = new char [2048];
            reader.read(charBuffer);
            String strBuffer = new String(charBuffer);
             */
            return ResultRequest.done(strBuffer);
        } catch (FileNotFoundException ex) {
            return ResultRequest.fails("{\"Error\":\"" + ex.toString() + "\"}");
        } catch (IOException ex) {
            return ResultRequest.fails("{\"Error\":\"" + ex.toString() + "\"}");
        }
    }

    @Override
    public ResultRequest<String> write(String data, String filename) {
        if (filename == null || filename.trim().equals("")){
            return ResultRequest.fails("{\"Error\":\"Invalid filename\"}");
        }
        //ESCRITURA BASADA EN FILEWRITER
        try (FileWriter writer = new FileWriter(new File(filename))) {
            writer.write(data);
            writer.close();
            return ResultRequest.done("{\"Result\":\"Done\"}");
        } catch (IOException ex) {
            return ResultRequest.fails("{\"Error\":\"" + ex.toString() + "\"}");
        }
    }
}