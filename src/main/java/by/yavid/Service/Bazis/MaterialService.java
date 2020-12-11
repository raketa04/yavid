package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.Material;

import java.util.Optional;

public interface MaterialService {
    public boolean isPresentCodMaterial(String codMaterial);
    public Material saveMaterial(Material material);
    public Material getMaterialByCod(String codMaterial);
}
