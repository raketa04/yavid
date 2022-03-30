package by.yavid.Repository.Yavid;

import by.yavid.Entity.Yavid.ProductInModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductInModelRepository extends CrudRepository<ProductInModel, Integer> {
    public Optional<ProductInModel> findByCodProduct (String codProduct);
}
