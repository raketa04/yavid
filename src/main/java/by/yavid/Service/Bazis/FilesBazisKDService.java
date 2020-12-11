package by.yavid.Service.Bazis;

import by.yavid.Entity.Workbase.Bazis.FilesBazisKD;
import by.yavid.Entity.Workbase.Bazis.KDList;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FilesBazisKDService {
    public FilesBazisKD saveFileKDBazis(MultipartFile uploadedFileRef, KDList kdList);
    public File openFileKDBazis(Integer idFileKDList);
    public boolean saveFileSpecificationFor3Cad(String numberKD,String specification);
    public String getSpecificationFor3Cad(String numberKD);
}
