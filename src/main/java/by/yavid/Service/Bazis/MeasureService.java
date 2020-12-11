package by.yavid.Service.Bazis;


import by.yavid.Entity.BazisBaseMaterial.Measure;
import by.yavid.Repository.BazisBaseMaterial.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeasureService {
    @Autowired
    MeasureRepository measureRepository;

    @Transactional(transactionManager="BazisBaseMaterialTransactionManager")
    public Measure getMeasureFromNameMeasure(String nameMeasure){
        return measureRepository.findFirstByNameMeasure(nameMeasure);
    }
}
