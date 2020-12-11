package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.GroupMaterial;

import java.util.List;

public interface GroupMaterialService {
    List<GroupMaterial> getGroupsMaterialsInNames(List<String> namesModels);
    GroupMaterial getGroupMaterialByName(String nameGroup);
    GroupMaterial addGroupMaterial(GroupMaterial groupMaterial);
    void addGroupsMaterialForFasad(List<String> namesGroup);
}
