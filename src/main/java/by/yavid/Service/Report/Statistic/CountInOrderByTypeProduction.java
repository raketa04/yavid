package by.yavid.Service.Report.Statistic;


import by.yavid.DTO.Unf1C.ShippedOrder;
import by.yavid.Entity.Ecadmaster.ItemOrder;
import by.yavid.Entity.Yavid.ProductInModel;
import by.yavid.Service.Ecadmaster.OrderService;
import by.yavid.Service.Report.Statistic.TypeProduction.RangeTypeProduction;
import by.yavid.Service.Report.Statistic.TypeProduction.TypeProduction;
import by.yavid.Service.Yavid.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Service
public class CountInOrderByTypeProduction {

    private RateService rateService;
    private List<RangeTypeProduction> rangeTypeProductionList;
    private OrderService orderService;
    private ProductService productService;


    public CountInOrderByTypeProduction(RateService rateService, List<RangeTypeProduction> rangeTypeProductionList, OrderService orderService, ProductService productService) {
        this.rateService = rateService;
        this.rangeTypeProductionList = rangeTypeProductionList;
        this.orderService = orderService;
        this.productService = productService;
    }

    private double sumPriceDetailInItemOrder(ItemOrder itemOrder , List<ItemOrder> componentsItemOrder, ShippedOrder order, float rate){
        double sum = getPriceItemDependingOnTypePrice(itemOrder,order.getTypePrice(),order.getCurrency(),rate);
        for(ItemOrder componentItemOrder:componentsItemOrder){
            sum += getPriceItemDependingOnTypePrice(componentItemOrder,order.getTypePrice(),order.getCurrency(),rate);
        }
        return sum;
    }

    private double getPriceItemDependingOnTypePrice(ItemOrder itemOrder,Integer typePrice,int typeCurrency,float rate){
        itemOrder.setExtract(true);
        if (typePrice==1){
            if (typeCurrency == 643 || typeCurrency ==978) return Math.round(itemOrder.getPriceTrade())*rate;
            else return itemOrder.getPriceTrade();
        }
        else if (typePrice==2) {
            if (typeCurrency == 643 || typeCurrency ==978) return Math.round(itemOrder.getPriceRetail())*rate;
            else return itemOrder.getPriceRetail();
        }
        return 0;
    }

    public void getR(ShippedOrder order){
        TypeProduction typeProduction;
        ProductInModel product;
        Map<TypeProduction, Integer> countItemsByTypeProduction = new HashMap<>();
        Map<TypeProduction, Double> sumItemsByTypeProduction = new HashMap<>();
        float rate = (float) 1.0;
        if (order.getCurrency() != 933) rate = rateService.getCusrRateForBYN(order.getCurrency(), order.getDateShipment());
        List<ItemOrder> itemsOrder = orderService.getItemsOrderFromOrder("YAVID", order.getNumberOrder());
        if (itemsOrder.isEmpty()) System.out.println(order.getNumberOrder() + " not found");
        for (ItemOrder itemOrder : itemsOrder) {
            product = productService.getProductByCodProduct(itemOrder.getCodProduct());
            for (RangeTypeProduction rangeTypeProduction:rangeTypeProductionList) {
                if(rangeTypeProduction.isRangeTypeProduction(parseInt(product.getModel().getCod()),parseInt(product.getCodProduct()))) {
                    typeProduction = rangeTypeProduction.getTypeProdction();
                    List<ItemOrder> componentsItemOrder = orderService.getComponentItemOrderFromOrder("YAVID",order.getNumberOrder(),itemOrder.getItemOrderId().getPositionInOrder());
                    if(countItemsByTypeProduction.containsKey(typeProduction))
                        countItemsByTypeProduction.put(typeProduction,countItemsByTypeProduction.get(typeProduction) + itemOrder.getNumberProduct());
                    else
                        countItemsByTypeProduction.put(typeProduction,itemOrder.getNumberProduct());
                    if(sumItemsByTypeProduction.containsKey(typeProduction))
                        sumItemsByTypeProduction.put(typeProduction,sumItemsByTypeProduction.get(typeProduction) + sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate));
                    else
                        sumItemsByTypeProduction.put(typeProduction,sumPriceDetailInItemOrder(itemOrder,componentsItemOrder,order,rate));
                    break;
                }
            }
        }
    }
}
