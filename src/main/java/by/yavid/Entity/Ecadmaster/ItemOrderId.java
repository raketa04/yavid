package by.yavid.Entity.Ecadmaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Embeddable
@Inheritance(strategy=InheritanceType.JOINED)
public class ItemOrderId implements Serializable {

    @Column(name = "PERCORSO")
    protected String catalog;

    @Column(name = "NUMERO")
    protected Integer order;

    @Column(name = "RIGA")
    protected Integer positionInOrder;

    public ItemOrderId() {
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getPositionInOrder() {
        return positionInOrder;
    }

    public void setPositionInOrder(Integer positionInOrder) {
        this.positionInOrder = positionInOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrderId that = (ItemOrderId) o;
        return Objects.equals(catalog, that.catalog) &&
                Objects.equals(order, that.order) &&
                Objects.equals(positionInOrder, that.positionInOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog, order, positionInOrder);
    }
}
