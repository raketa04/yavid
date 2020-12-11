package by.yavid.Repository.BazisBaseMaterial;

import by.yavid.Entity.BazisBaseMaterial.Material;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MaterialRepository  extends CrudRepository<Material, Integer> {
    Optional<Material> findFirstByCodMaterial(String codMaterial);
}
