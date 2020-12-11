package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.Material;
import by.yavid.Repository.BazisBaseMaterial.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    private MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    @Override
    public boolean isPresentCodMaterial(String codMaterial) {
        if(materialRepository.findFirstByCodMaterial(codMaterial).isPresent()) return true;
        else return false;
    }
    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    @Override
    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    public Material getMaterialByCod(String codMaterial) {
        return materialRepository.findFirstByCodMaterial(codMaterial).get();
    }
}
