package by.yavid.Repository.Yavid;

import by.yavid.Entity.Yavid.Variant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VariantRepository extends CrudRepository<Variant, Integer> {
    List<Variant> findByCodVariant_CodVar(String codVar);
}
