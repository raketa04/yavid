package by.yavid.Service.Yavid;

import by.yavid.Entity.Yavid.Product;
import by.yavid.Entity.Yavid.ProductInModel;
import by.yavid.Repository.Yavid.ProductInModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {
    private ProductInModelRepository productInModelRepository;

    public ProductService(ProductInModelRepository productInModelRepository) {
        this.productInModelRepository = productInModelRepository;
    }

    @Transactional(transactionManager="yavidTransactionManager")
    public ProductInModel getProductByCodProduct(String codProduct){
        if(productInModelRepository.findByCodProduct(codProduct).isPresent()){
            return productInModelRepository.findByCodProduct(codProduct).get();
        }
        else {
            System.out.println(codProduct + " not found");
            return new ProductInModel();
        }
    }
}
