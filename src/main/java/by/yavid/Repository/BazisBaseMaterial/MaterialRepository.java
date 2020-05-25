package by.yavid.Repository.BazisBaseMaterial;

import by.yavid.Entity.BazisBaseMaterial.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialRepository  extends CrudRepository<Material, Integer> {
    public Material findFirstByCodMaterial(String codMaterial);
}
