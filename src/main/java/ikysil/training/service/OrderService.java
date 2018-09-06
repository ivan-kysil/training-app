package ikysil.training.service;

import ikysil.training.ws.api.v1.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto order);

    OrderDto getOrder(String orderId);

    List<OrderDto> getOrders();

    OrderDto updateOrder(String orderId, OrderDto order);

    OrderDto deleteOrder(String orderId);
}
