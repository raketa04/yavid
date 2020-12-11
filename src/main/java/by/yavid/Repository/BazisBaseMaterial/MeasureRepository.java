package by.yavid.Repository.BazisBaseMaterial;

import by.yavid.Entity.BazisBaseMaterial.Measure;
import org.springframework.data.repository.CrudRepository;

public interface MeasureRepository extends CrudRepository<Measure, Integer> {
    Measure findFirstByNameMeasure (String nameMeasure);
}
