package ikysil.training.service.impl;

import ikysil.training.dao.OrderDao;
import ikysil.training.service.OrderService;
import ikysil.training.ws.api.v1.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

// TODO implement, use dao to CRUD orders
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderDao dao;

    @Override
    public OrderDto createOrder(OrderDto order) {
        return null;
    }

    @Override
    public OrderDto getOrder(String orderId) {
        return null;
    }

    @Override
    public List<OrderDto> getOrders() {
        return null;
    }

    @Override
    public OrderDto updateOrder(String orderId, OrderDto order) {
        return null;
    }

    @Override
    public void deleteOrder(String orderId) {

    }
}
