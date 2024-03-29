package by.yavid.Service.Yavid;

import by.yavid.Entity.Yavid.Variant;
import by.yavid.Repository.Yavid.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {

    private VariantRepository variantRepository;

    public VariantServiceImpl(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    @Transactional(transactionManager="yavidTransactionManager")
    @Override
    public List<Variant> GetVariantsByCodVar(String codVar) {
        return variantRepository.findByCodVariant_CodVar(codVar);
    }

    public List<String> getCodsModelsOfVariants(List<Variant> variants){
        ArrayList<String> codsModels =  new ArrayList<>();
        for (Variant variant:variants) {
            if(!(variant.getParametrFromVariant(2).equals("100")
                    | variant.getParametrFromVariant(2).equals("500")
                    | codsModels.contains(variant.getParametrFromVariant(2)))){
                codsModels.add(variant.getParametrFromVariant(2));
            }
        }
        return codsModels;
    }
}
