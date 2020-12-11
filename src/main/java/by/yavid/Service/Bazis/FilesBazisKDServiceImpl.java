package by.yavid.Service.Bazis;

import by.yavid.Entity.Workbase.Bazis.FilesBazisKD;
import by.yavid.Entity.Workbase.Bazis.KDList;
import by.yavid.Repository.Workbase.Bazis.FilesBazisKDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FilesBazisKDServiceImpl implements FilesBazisKDService {

    private FilesBazisKDRepository filesBazisKDRepository;
    private KDListService kdListService;
    private Environment env;

    public FilesBazisKDServiceImpl(FilesBazisKDRepository filesBazisKDRepository, KDListService kdListService, Environment env) {
        this.filesBazisKDRepository = filesBazisKDRepository;
        this.kdListService = kdListService;
        this.env = env;
    }

    @Override
    public FilesBazisKD saveFileKDBazis(MultipartFile uploadedFileRef, KDList kdList) {
        Integer revision = filesBazisKDRepository.getMaxRevision(kdList).orElse(1);
        String path = env.getProperty("directory.bazis.kd") +  kdList.getNumberKD().trim() + "\\";
        if( revision == 1){
            new File(path).mkdir();
        }
        else{
            revision++;
        }
        try {
            StringBuffer fileName = new StringBuffer(uploadedFileRef.getOriginalFilename()).insert(uploadedFileRef.getOriginalFilename().lastIndexOf("."),"_rev"+revision);
            byte[] bytes = uploadedFileRef.getBytes() ;
            File file = new File(path+fileName);
            file.createNewFile();
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
            writer.write(bytes);
            writer.flush();
            writer.close();
            return filesBazisKDRepository.save(new FilesBazisKD(revision,path+fileName,kdList));
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public File openFileKDBazis(Integer idFileKDList) {
        String path = filesBazisKDRepository.findById(idFileKDList).get().getFilePath();
        return new File(path);
    }

    @Override
    public boolean saveFileSpecificationFor3Cad(String numberKD, String specification) {
        String path = env.getProperty("directory.3cad.specification");
        File file = new File(path+numberKD+".txt");
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "windows-1251"));
            writer.write(specification);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getSpecificationFor3Cad(String numberKD) {
        if(kdListService.getKdList(numberKD) != null){
            String path = env.getProperty("directory.3cad.specification");
            File file = new File(path+numberKD+".txt");
            String specification = new String();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "windows-1251"));
                String line;
                while ((line = reader.readLine()) != null) {
                    specification += line + "\n";
                }
                return specification;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        else{
            return "";
        }
    }


}
