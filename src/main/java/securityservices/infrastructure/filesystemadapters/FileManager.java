package securityservices.infrastructure.filesystemadapters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import securityservices.core.ports.infrastructure.FilePort;
import securityservices.shared.responses.ResultRequest;

public class FileManager {
    protected FilePort filePort = null;
    private final String defaultPathfile = "E:\\files\\";
    private final DateTimeFormatter DtFormatter = DateTimeFormatter.ofPattern("dd'_'MM'_'yyyy'_'HH'_'mm'_'ss");

    public FileManager() {
        this.filePort = new FileAdapter();
    }

    public FileManager(FilePort filePort) {
        this.filePort = filePort;
    }

    public ResultRequest write(String data, String filename, String extension) {
        String pathname = this.defaultPathfile + filename + "_" + 
                DtFormatter.format(LocalDateTime.now()) + "." + extension;
        return this.filePort.write(data, pathname);
    }

    public ResultRequest read(String dataSource) {
        return this.filePort.read(dataSource);
    }
}