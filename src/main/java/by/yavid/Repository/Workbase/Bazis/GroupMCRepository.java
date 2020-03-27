package by.yavid.Repository.Workbase.Bazis;

import by.yavid.Entity.Workbase.Bazis.GroupMC;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMCRepository extends CrudRepository<GroupMC, Integer> {
    List<GroupMC> findBycMainGr(int cMainGr);
}

