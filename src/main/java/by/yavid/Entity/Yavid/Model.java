package by.yavid.Entity.Yavid;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MODELLI")
public class Model {

    @Id
    @Column(name="COD")
    private String cod;

    @Column(name="DES")
    private String nameModel;

    @OneToMany(mappedBy = "model", cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true)
    private Set<ProductInModel> productsInModel = new HashSet<>();

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public Set<ProductInModel> getProductsInModel() {
        return productsInModel;
    }

    public void setProductsInModel(Set<ProductInModel> productsInModel) {
        this.productsInModel = productsInModel;
    }
}
