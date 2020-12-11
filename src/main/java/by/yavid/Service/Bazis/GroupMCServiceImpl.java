package by.yavid.Service.Bazis;

import by.yavid.Exception.ThereIsNoSuchGroupMCException;
import by.yavid.Entity.Workbase.Bazis.GroupMC;
import by.yavid.Repository.Workbase.Bazis.GroupMCRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupMCServiceImpl implements GroupMCService {

    private GroupMCRepository groupMCRepository;

    public GroupMCServiceImpl(GroupMCRepository groupMCRepository) {
        this.groupMCRepository = groupMCRepository;
    }

    @Override
    public List<GroupMC> getTypeProduction() {
        return groupMCRepository.findBycMainGr(9).orElseThrow(()-> new ThereIsNoSuchGroupMCException());
    }
}
