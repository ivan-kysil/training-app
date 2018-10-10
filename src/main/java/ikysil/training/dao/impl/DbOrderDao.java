package ikysil.training.dao.impl;


import ikysil.training.dao.OrderDao;
import ikysil.training.dao.repo.OrderRepo;
import ikysil.training.model.Order;
import ikysil.training.ws.api.v1.dto.OrderDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DbOrderDao implements OrderDao {

    private final OrderRepo orderRepo;

    @Override
    public OrderDto saveOrder(OrderDto order) {
        return orderRepo.save(order.toModel())
                .toDto();
    }

    @Override
    public OrderDto findOrder(String id) {
        return orderRepo.findOne(Long.parseLong(id))
                .toDto();
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepo.findAll().stream()
                .map(Order::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrder(String id, OrderDto order) {
        return orderRepo.save(order.toModel().setId(Long.parseLong(id)))
                .toDto();
    }

    @Override
    public OrderDto deleteOrder(String id) {
        OrderDto order = findOrder(id);
        orderRepo.delete(Long.parseLong(id));
        return order;
    }

    @Override
    public void deleteAll() {
        orderRepo.deleteAll();
    }
}
