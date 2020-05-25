package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.Material;
import by.yavid.Repository.BazisBaseMaterial.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Override
    public boolean isPresentCodMaterial(String codMaterial) {
        if(materialRepository.findFirstByCodMaterial(codMaterial) == null){
            return  false;
        }
        else {
            return true;
        }
    }

    @Override
    public Material AddMaterial(Material material) {
        return materialRepository.save(material);
    }
}
