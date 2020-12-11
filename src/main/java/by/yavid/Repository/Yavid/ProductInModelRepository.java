package by.yavid.Repository.Yavid;

import by.yavid.Entity.Yavid.ProductInModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductInModelRepository extends CrudRepository<ProductInModel, Integer> {
    public ProductInModel findByCodProduct (String codProduct);
}
