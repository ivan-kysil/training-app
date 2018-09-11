package ikysil.training.service.impl;

import ikysil.training.dao.OrderDao;
import ikysil.training.service.OrderService;
import ikysil.training.ws.api.v1.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao dao;

    @Override
    public OrderDto createOrder(OrderDto order) {
        return dao.saveOrder(order);
    }

    @Override
    public OrderDto getOrder(String orderId) {
        return dao.findOrder(orderId);
    }

    @Override
    public List<OrderDto> getOrders() {
        return dao.findAll();
    }

    @Override
    public OrderDto updateOrder(String orderId, OrderDto order) {
        return dao.updateOrder(orderId, order);
    }

    @Override
    public void deleteOrder(String orderId) {
        dao.deleteOrder(orderId);
    }
}
