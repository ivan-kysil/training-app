package ikysil.training.dao.impl;

import ikysil.training.dao.OrderDao;
import ikysil.training.ws.api.v1.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryOrderDao implements OrderDao {

    private Map<String, OrderDto> storage = new HashMap<>();

    @Override
    public OrderDto saveOrder(OrderDto order) {
        storage.put(order.getId(), order);
        return order;
    }

    @Override
    public OrderDto findOrder(String id) {
        return storage.get(id);
    }

    @Override
    public List<OrderDto> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public OrderDto updateOrder(String id, OrderDto order) {
        storage.computeIfPresent(id, (k, v) -> v = order);
        return order;
    }

    @Override
    public void deleteOrder(String id) {
        storage.remove(id);
    }
}
