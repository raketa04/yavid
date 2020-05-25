package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.GroupMaterial;

import java.util.List;

public interface GroupMaterialService {
    List<GroupMaterial> GetGroupsMaterialsInNames(List<String> namesModels);
    GroupMaterial GetGroupMaterialByName(String nameGroup);
    GroupMaterial AddGroupMaterial(GroupMaterial groupMaterial);
}
