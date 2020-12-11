package by.yavid.DTO.Unf1C;

import java.util.Date;
import java.util.List;

public class ShippedOrder {
    private int numberOrder, typePrice;
    private int currency;
    private Date dateShipment;

    public ShippedOrder() {
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(int typePrice) {
        this.typePrice = typePrice;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public Date getDateShipment() {
        return dateShipment;
    }

    public void setDateShipment(Date dateShipment) {
        this.dateShipment = dateShipment;
    }
}
