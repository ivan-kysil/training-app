package ikysil.training.dao.impl;

import ikysil.training.dao.OrderDao;
import ikysil.training.ws.api.v1.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// TODO implement, use storage
@Component
public class InMemoryOrderDao implements OrderDao {

    private Map<String, OrderDao> storage = new HashMap<>();

    @Override
    public OrderDto saveOrder(OrderDto order) {
        return null;
    }

    @Override
    public OrderDto findOrder(String id) {
        return null;
    }

    @Override
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public OrderDto updateOrder(String id, OrderDto order) {
        return null;
    }

    @Override
    public void deleteOrder(String id) {

    }
}
