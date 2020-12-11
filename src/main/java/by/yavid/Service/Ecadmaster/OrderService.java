package by.yavid.Service.Ecadmaster;

import by.yavid.Entity.Ecadmaster.ItemOrder;
import by.yavid.Repository.Ecadmaster.ItemOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private ItemOrderRepository orderRepository;

    public OrderService(ItemOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(transactionManager="ecadmasterTransactionManager")
    public List<ItemOrder> getItemsOrderFromOrder(String catalog, Integer numberOrder){
        return orderRepository.findByItemOrderId_CatalogAndItemOrderId_OrderAndParentPositionInOrder(catalog,numberOrder,0);
    }

    public List<ItemOrder> getComponentItemOrderFromOrder(String catalog, Integer numberOrder,Integer positionInOrder){
        return orderRepository.findByItemOrderId_CatalogAndItemOrderId_OrderAndParentPositionInOrder(catalog,numberOrder,positionInOrder);
    }

}
