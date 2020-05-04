package by.yavid.Service.Bazis;


import by.yavid.Entity.Workbase.Bazis.KDList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KDListService {
    public Integer getNewKD();

    public KDList saveKDList(KDList kdList);

    public KDList getKdList(String numberKD);
    public KDList getKdList(Integer id);
    public List<KDList> getAllKdList();
    public KDList getKdListByUser(int idUser);

}
