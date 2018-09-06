package ikysil.training.dao;

import ikysil.training.ws.api.v1.dto.OrderDto;

import java.util.List;


public interface OrderDao {

    OrderDto saveOrder(OrderDto order);
    OrderDto findOrder(String id);
    List<OrderDto> findAll();
    OrderDto updateOrder(String id, OrderDto order);
    OrderDto deleteOrder(String id);
    void deleteAll();
}
