package ikysil.training.service;

import ikysil.training.dao.OrderDao;
import ikysil.training.service.impl.OrderServiceImpl;
import ikysil.training.ws.api.v1.dto.OrderDto;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class OrderServiceImplTest {

    @Mock private OrderDao dao;
    @InjectMocks private OrderServiceImpl serv;

    private OrderDto order1 = new OrderDto("1144", "twenty one", "none", "3129", "firstuser@gmail.com");
    private OrderDto order2 = new OrderDto("3257", "twenty two", "none", "4024", "seconduser@gmail.com");

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void createOrderTest(){
        when(dao.saveOrder(order1)).thenReturn(order1);
        when(dao.saveOrder(order2)).thenReturn(order2);
        assertThat(order1).isEqualTo(serv.createOrder(order1));
        assertThat(order2).isEqualTo(serv.createOrder(order2));
        verify(dao, times(2)).saveOrder(any());
    }

    @Test
    public void getOrderTest(){
        when(dao.findOrder("1144")).thenReturn(order1);
        when(dao.findOrder("3257")).thenReturn(order2);
        OrderDto testOrder1 = serv.getOrder("1144");
        assertThat(order1).isEqualTo(testOrder1);
        OrderDto testOrder2 = serv.getOrder("3257");
        assertThat(order2).isEqualTo(testOrder2);
    }

    @Test
    public void getOrdersTest(){
        when(dao.findAll()).thenReturn(Arrays.asList(order1, order2)).thenReturn(new ArrayList<>());
        List<OrderDto> orders = serv.getOrders();
        assertThat(orders)
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(order1, order2);
        orders = serv.getOrders();
        assertThat(orders).isEmpty();
    }

    @Test
    public void updeteOrderTest(){
        OrderDto newOrder = new OrderDto("3257","thirty three", "noneone", "2440", "newuser@gmail.com");
        serv.updateOrder("3257", newOrder);
        serv.updateOrder("3257", newOrder);
        serv.updateOrder("3257", newOrder);
        verify(dao,times(3)).updateOrder(anyString(), any());
    }

    @Test
    public void deleteOrderTest(){
        doAnswer((Answer) invocation -> {
            Object[] objects = invocation.getArguments();
            assertThat(objects[0].equals("1144") || objects[0].equals("3257")).isTrue();
            return null;
        }).when(dao).deleteOrder(anyString());
        serv.deleteOrder("1144");
        serv.deleteOrder("3257");
    }
}
