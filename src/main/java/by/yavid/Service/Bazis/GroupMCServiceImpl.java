package by.yavid.Service.Bazis;

import by.yavid.Entity.Workbase.Bazis.GroupMC;
import by.yavid.Repository.Workbase.Bazis.GroupMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupMCServiceImpl implements GroupMCService {

    @Autowired
    GroupMCRepository groupMCRepository;

    @Override
    public List<GroupMC> getTypeProduction() {
        return groupMCRepository.findBycMainGr(9);
    }
}
