package by.yavid.DTO.ERP;

import java.util.List;

public class LoadingShipment {
    private int idLoadingShipment;
    private List<String> ordersInLoadingShipment;

    public int getIdLoadingShipment() {
        return idLoadingShipment;
    }

    public void setIdLoadingShipment(int idLoadingShipment) {
        this.idLoadingShipment = idLoadingShipment;
    }

    public List<String> getOrdersInLoadingShipment() {
        return ordersInLoadingShipment;
    }

    public void setOrdersInLoadingShipment(List<String> ordersInLoadingShipment) {
        this.ordersInLoadingShipment = ordersInLoadingShipment;
    }
}
