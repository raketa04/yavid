package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.TypeMaterial;
import by.yavid.Repository.BazisBaseMaterial.TypeMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeMaterialService {
    private TypeMaterialRepository typeMaterialRepository;

    public TypeMaterialService(TypeMaterialRepository typeMaterialRepository) {
        this.typeMaterialRepository = typeMaterialRepository;
    }

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    public TypeMaterial getTypeMaterialFromNameType(String nameType){
        return typeMaterialRepository.findFirstByNameType(nameType);
    }
}
