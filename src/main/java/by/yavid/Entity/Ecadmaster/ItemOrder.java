package by.yavid.Entity.Ecadmaster;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RORDINE")
@Inheritance(strategy=InheritanceType.JOINED)
public class ItemOrder {
    @EmbeddedId
    private ItemOrderId itemOrderId;

    @Column(name="CODART")
    private String codProduct;

    @Column(name="PZ")
    private Integer numberProduct;

    @Column(name="PADRE")
    private Integer parentPositionInOrder;

    @Column(name="YPR3")
    private Double priceRetail;

    @Column(name="YPR1")
    private Double priceTrade;

    @Transient
    private boolean extract = false;



    public ItemOrder() {
    }

    public ItemOrderId getItemOrderId() {
        return itemOrderId;
    }

    public void setItemOrderId(ItemOrderId itemOrderId) {
        this.itemOrderId = itemOrderId;
    }

    public String getCodProduct() {
        return codProduct;
    }

    public void setCodProduct(String codProduct) {
        this.codProduct = codProduct;
    }

    public Integer getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(Integer numberProduct) {
        this.numberProduct = numberProduct;
    }

    public Double getPriceRetail() {
        return priceRetail;
    }

    public void setPriceRetail(Double priceRetail) {
        this.priceRetail = priceRetail;
    }

    public Double getPriceTrade() {
        return priceTrade;
    }

    public void setPriceTrade(Double priceTrade) {
        this.priceTrade = priceTrade;
    }

    public Integer getParentPositionInOrder() {
        return parentPositionInOrder;
    }

    public void setParentPositionInOrder(Integer parentPositionInOrder) {
        this.parentPositionInOrder = parentPositionInOrder;
    }
    public boolean isExtract() {
        return extract;
    }

    public void setExtract(boolean extract) {
        this.extract = extract;
    }
}

