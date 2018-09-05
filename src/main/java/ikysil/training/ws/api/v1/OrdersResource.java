package ikysil.training.ws.api.v1;

import ikysil.training.service.OrderService;
import ikysil.training.ws.api.v1.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO add mapper
// TODO add search API
// TODO add model validation
// TODO add fail path tests

@Component
@Path("/api/v1/orders/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class OrdersResource {
    private final OrderService orderService;

    @POST
    public Response createOrders(OrderDto order) {
        OrderDto createdOrder = orderService.createOrder(order);

        return Response.ok(createdOrder).build();
    }

    @GET
    @Path("{id}")
    public Response getOrder(@PathParam("id") String orderId) {
        OrderDto order = orderService.getOrder(orderId);

        return Response.ok(order).build();
    }

    @GET
    public Response getOrders() {
        List<OrderDto> orders = orderService.getOrders();

        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteOrder(@PathParam("id") String orderId) {
        orderService.deleteOrder(orderId);

        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response updateOrder(@PathParam("id") String orderId, OrderDto order) {
        OrderDto updatedOrder = orderService.updateOrder(orderId, order);

        return Response.ok(updatedOrder).build();
    }

}
