package by.yavid.Repository.BazisBaseMaterial;

import by.yavid.Entity.BazisBaseMaterial.TypeMaterial;
import org.springframework.data.repository.CrudRepository;

public interface TypeMaterialRepository extends CrudRepository<TypeMaterial, Integer> {
    public TypeMaterial findFirstByNameType(String nameType);
}
