package by.yavid.Repository.Workbase.Bazis;

import by.yavid.Entity.Workbase.Bazis.KDList;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface KDListRepository extends CrudRepository<KDList, Integer> {
    Optional<KDList> findByUser_Id(int idUser);
    Optional<KDList> findByNumberKD(String numberKD);
}
