package by.yavid.Repository.Ecadmaster;

import by.yavid.Entity.Ecadmaster.ItemOrder;
import by.yavid.Entity.Ecadmaster.ItemOrderId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, ItemOrderId> {
    List<ItemOrder> findByItemOrderId_CatalogAndItemOrderId_OrderAndParentPositionInOrder(String catalog,Integer numberOrder,Integer parentPositionInOrder);
}
