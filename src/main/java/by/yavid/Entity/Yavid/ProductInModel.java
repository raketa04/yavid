package by.yavid.Entity.Yavid;


import javax.persistence.*;

@Entity
@Table(name = "ARTICOLI")
public class ProductInModel {
    @Id
    @Column(name = "COD")
    private String codProduct;

    @Column(name = "DES")
    private String nameProduct;

    @ManyToOne
    @JoinColumn(name="neutro")
    private Product product;

    @ManyToOne
    @JoinColumn(name="Modello")
    private Model model;

    public ProductInModel() {
    }

    public String getCodProduct() {
        return codProduct;
    }

    public void setCodProduct(String codProduct) {
        this.codProduct = codProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
