package by.yavid.Service.Bazis;

import by.yavid.Entity.BazisBaseMaterial.MaterialAdvance;
import by.yavid.Repository.BazisBaseMaterial.MaterialAdvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaterialAdvanceService {

    @Autowired
    MaterialAdvanceRepository materialAdvanceRepository;

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    public MaterialAdvance AddMaterialAdvance(MaterialAdvance materialAdvance){
        return materialAdvanceRepository.save(materialAdvance);
    }
}
