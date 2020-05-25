package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.Material;

public interface MaterialService {
    public boolean isPresentCodMaterial(String codMaterial);
    public Material AddMaterial(Material material);
}
