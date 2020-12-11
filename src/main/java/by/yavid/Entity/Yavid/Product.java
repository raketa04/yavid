package by.yavid.Entity.Yavid;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ARTNEUTRI")
public class Product {
    @Id
    @Column(name = "COD")
    private String codProduct;

    @Column(name = "TIP")
    private Integer typeProduct;

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval=true)
    private Set<ProductInModel> productsInModel = new HashSet<>();

    public Product() {
    }

    public String getCodProduct() {
        return codProduct;
    }

    public void setCodProduct(String codProduct) {
        this.codProduct = codProduct;
    }

    public Integer getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(Integer typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Set<ProductInModel> getProductsInModel() {
        return productsInModel;
    }

    public void setProductsInModel(Set<ProductInModel> productsInModel) {
        this.productsInModel = productsInModel;
    }
}
