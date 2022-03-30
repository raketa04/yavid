package by.yavid.Service.Bazis;

import by.yavid.Entity.Workbase.Bazis.FilesBazisKD;
import by.yavid.Entity.Workbase.Bazis.KDList;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FilesBazisKDService {
    FilesBazisKD saveFileKDBazis(MultipartFile uploadedFileRef, KDList kdList);
    File openFileKDBazis(Integer idFileKDList);
    boolean saveFileSpecificationFor3Cad(String numberKD,String specification);
    String getSpecificationFor3Cad(String numberKD);
}
