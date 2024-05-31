package by.yavid.Service.CncMachine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class FileDefaultDrawingServiceImpl implements FileDefaultDrawingService {

    @Value("${file.pahtDirectoryDefaultDrawing}")
    private String pahtDirectoryDefaultDrawing;

    @Value("${file.formatDefaultDrawing}")
    private String formatDefaultDrawing;

    @Override
    public File getFileDefaultDrawingService(String nameFile) {
        File defaultDrawing = new File(pahtDirectoryDefaultDrawing+nameFile+"."+formatDefaultDrawing);
        if(defaultDrawing.exists()){
            return defaultDrawing;
        }
        else {
            return null;
        }

    }

    @Override
    public Map<String,Set<String>>  getAllFileDefaultDrawingService() {
        Map<String,Set<String>> nodeDrawingMap = new HashMap();
        FilenameFilter filter = (dir, name) -> name.endsWith("."+formatDefaultDrawing);
        List<String> listFile =  Arrays.asList(new File(pahtDirectoryDefaultDrawing).list(filter)).stream()
                .map(s -> s.replaceAll("\\.\\w+$", ""))
                .collect(Collectors.toList());
        for(String nameFile:listFile){
            if (!nodeDrawingMap.containsKey(nameFile.substring(0,nameFile.lastIndexOf("-")))){
                nodeDrawingMap.put(nameFile.substring(0, nameFile.lastIndexOf("-")),new HashSet<>());
            }
            nodeDrawingMap.get(nameFile.substring(0, nameFile.lastIndexOf("-"))).add(nameFile);
        }

        return nodeDrawingMap;

    }
}
