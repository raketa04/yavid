package by.yavid.Repository.Workbase.Bazis;

import by.yavid.Entity.Workbase.Bazis.FilesBazisKD;
import by.yavid.Entity.Workbase.Bazis.KDList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FilesBazisKDRepository extends CrudRepository<FilesBazisKD, Integer> {
    @Query("SELECT max(f.revision) FROM FilesBazisKD f WHERE f.kdList = ?1")
    Optional<Integer> getMaxRevision(KDList kdList);
    @Query("SELECT f.revision FROM FilesBazisKD f WHERE f.kdList = ?1")
    List<Integer> getAllRevisionsKDList(Integer idKDList);
}
