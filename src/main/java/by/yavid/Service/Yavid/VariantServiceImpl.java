package by.yavid.Service.Yavid;

import by.yavid.Entity.Yavid.Variant;
import by.yavid.Repository.Yavid.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {

    @Autowired
    VariantRepository variantRepository;

    @Transactional(transactionManager="yavidTransactionManager")
    @Override
    public List<Variant> GetVariantsByCodVar(String codVar) {
        return variantRepository.findByCodVariant_CodVar(codVar);
    }
}
