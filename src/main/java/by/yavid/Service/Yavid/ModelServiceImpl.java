package by.yavid.Service.Yavid;

import by.yavid.Entity.Yavid.Model;
import by.yavid.Repository.Yavid.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class ModelServiceImpl implements ModelService  {

    private ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Transactional(transactionManager="yavidTransactionManager")
    @Override
    public List<Model> getModelsFromCods(List<String> codsModels) {
        return modelRepository.findByCodIsIn(codsModels);
    }

    @Transactional(transactionManager="yavidTransactionManager")
    @Override
    public Model getModelFromCod(String codModel) {
        return modelRepository.findFirstByCod(codModel);
    }
}
