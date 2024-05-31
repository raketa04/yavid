package by.yavid.Service.CncMachine;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface FileDefaultDrawingService {
    File getFileDefaultDrawingService(String nameFile);
    Map<String,Set<String>> getAllFileDefaultDrawingService();

}
