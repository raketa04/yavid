package by.yavid.Repository.Workbase.Bazis;

import by.yavid.Entity.Workbase.Bazis.GroupMC;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMCRepository extends CrudRepository<GroupMC, Integer> {
    Optional<List<GroupMC>> findBycMainGr(int cMainGr);
}

