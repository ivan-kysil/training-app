package ikysil.training.dao;

import ikysil.training.dao.impl.InMemoryOrderDao;
import ikysil.training.ws.api.v1.dto.OrderDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InMemoryOrderDaoTest {

    private OrderDao dao = new InMemoryOrderDao();

    private OrderDto order1 = new OrderDto("1144", "twenty one", "none", "3129", "firstuser@gmail.com");
    private OrderDto order2 = new OrderDto("3257", "twenty two", "none", "4024", "seconduser@gmail.com");

    @Before
    public void insertOrders(){
        dao.saveOrder(order1);
        dao.saveOrder(order2);
    }

    @Test
    public void findOrderTest(){
        OrderDto testOrder1 = dao.findOrder("1144");
        assertThat(order1).isEqualTo(testOrder1);

        OrderDto testOrder2 = dao.findOrder("3257");
        assertThat(order2).isEqualTo(testOrder2);
    }

    @Test
    public void updateOrderTest() {
        OrderDto newOrder = new OrderDto("3257","thirty three", "noneone", "2440", "newuser@gmail.com");
        dao.updateOrder("3257", newOrder);
        assertThat(dao.findOrder("3257")).isEqualTo(newOrder);
    }

    @Test
    public void findAllTest(){
        List<OrderDto> orders = dao.findAll();
        assertThat(orders).hasSize(2);
        removeOrders();
        orders = dao.findAll();
        assertThat(orders).isEmpty();
        insertOrders();
    }

    @Test(expected = NullPointerException.class)
    public void insertNullTest(){
        dao.saveOrder(null);
    }

    @Test
    public void dublicatesTest(){
        dao.saveOrder(order1);
        dao.saveOrder(order1);
        dao.saveOrder(order2);
        dao.saveOrder(order2);
        assertThat(dao.findAll()).contains(order1, order2);
    }

    @After
    public void removeOrders(){
        dao.deleteOrder("1144");
        dao.deleteOrder("3257");
    }


}
