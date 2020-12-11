package by.yavid.Repository.Yavid;

import by.yavid.Entity.Yavid.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Product findByCodProduct (String codProduct);
}
