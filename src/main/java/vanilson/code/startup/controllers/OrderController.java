package vanilson.code.startup.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanilson.code.startup.dto.OrderDTO;
import vanilson.code.startup.persistence.model.Order;
import vanilson.code.startup.services.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @GetMapping(value = "/order")
    public ResponseEntity<List<Order>> listAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    @PostMapping(value = "/order/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewOrder(@RequestBody @Valid OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);
        return " Order Placed Successfully";

    }
}
