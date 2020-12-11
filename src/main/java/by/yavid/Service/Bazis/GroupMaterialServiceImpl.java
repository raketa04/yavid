package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.GroupMaterial;
import by.yavid.Entity.BazisBaseMaterial.TypeMaterial;
import by.yavid.Repository.BazisBaseMaterial.GroupMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupMaterialServiceImpl implements GroupMaterialService {

    private GroupMaterialRepository groupMaterialRepository;
    private TypeMaterialService typeMaterialService;

    public GroupMaterialServiceImpl(GroupMaterialRepository groupMaterialRepository,TypeMaterialService typeMaterialService) {
        this.groupMaterialRepository = groupMaterialRepository;
        this.typeMaterialService = typeMaterialService;
    }

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    @Override
    public List<GroupMaterial> getGroupsMaterialsInNames(List<String> namesModels) {
        return groupMaterialRepository.findByNameGroupIn(namesModels);
    }

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    @Override
    public GroupMaterial getGroupMaterialByName(String nameGroup) {
        return groupMaterialRepository.findFirstByNameGroup(nameGroup);
    }

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    @Override
    public GroupMaterial addGroupMaterial(GroupMaterial groupMaterial) {
        return groupMaterialRepository.save(groupMaterial);
    }

    @Override
    public void addGroupsMaterialForFasad(List<String> namesGroup) {
        List<GroupMaterial> groupsMaterials = getGroupsMaterialsInNames(namesGroup);
        GroupMaterial groupMaterialFasad = getGroupMaterialByName("08.Фасады");
        for(GroupMaterial groupMaterial:groupsMaterials){
            if(namesGroup.contains(groupMaterial.getNameGroup())){
                namesGroup.remove(groupMaterial.getNameGroup());
            }
        }
        TypeMaterial typeMaterial = typeMaterialService.getTypeMaterialFromNameType("Листовой и погонный материалы");
        for(String nameModel:namesGroup){
            GroupMaterial groupMaterial = new GroupMaterial(nameModel,typeMaterial,groupMaterialFasad);
            addGroupMaterial(groupMaterial);
        }

    }


}
