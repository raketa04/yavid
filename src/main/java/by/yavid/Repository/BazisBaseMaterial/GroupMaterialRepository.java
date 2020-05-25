package by.yavid.Repository.BazisBaseMaterial;

import by.yavid.Entity.BazisBaseMaterial.GroupMaterial;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMaterialRepository extends CrudRepository<GroupMaterial, Integer> {
    List<GroupMaterial> findByNameGroupIn(List<String> namesGroups);
    GroupMaterial findFirstByNameGroup(String nameGroup);
}
