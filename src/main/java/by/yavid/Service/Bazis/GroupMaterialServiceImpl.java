package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.GroupMaterial;
import by.yavid.Repository.BazisBaseMaterial.GroupMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupMaterialServiceImpl implements GroupMaterialService {

    private GroupMaterialRepository groupMaterialRepository;

    public GroupMaterialServiceImpl(GroupMaterialRepository groupMaterialRepository) {
        this.groupMaterialRepository = groupMaterialRepository;
    }

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    @Override
    public List<GroupMaterial> GetGroupsMaterialsInNames(List<String> namesModels) {
        return groupMaterialRepository.findByNameGroupIn(namesModels);
    }

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    @Override
    public GroupMaterial GetGroupMaterialByName(String nameGroup) {
        return groupMaterialRepository.findFirstByNameGroup(nameGroup);
    }

    @Override
    public GroupMaterial AddGroupMaterial(GroupMaterial groupMaterial) {
        return groupMaterialRepository.save(groupMaterial);
    }
}
