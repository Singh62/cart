package com.shopping.cart.controller;

import com.shopping.cart.dto.OrderDTO;
import com.shopping.cart.dto.ResponseOrderDTO;
import com.shopping.cart.entity.Customer;
import com.shopping.cart.entity.Order;
import com.shopping.cart.entity.Product;
import com.shopping.cart.service.CustomerService;
import com.shopping.cart.service.OrderService;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ShoppingCartRestController {

    private OrderService orderService;
    private ProductService productService;
    private CustomerService customerService;


    public ShoppingCartRestController(OrderService orderService, ProductService productService, CustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }

    private Logger logger = LoggerFactory.getLogger(ShoppingCartRestController.class);

    @RequestMapping(value="/home",method=RequestMethod.GET)
    public String home() {
        return "home";
    }
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }
    @PostMapping(value = "/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        Product productResponse = this.productService.addProduct(product);

        return ResponseEntity.ok(productResponse);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/deleteProduct/{id}")
    public String deleteProduct(@PathVariable String id) {

        this.productService.deleteProduct(Integer.getInteger(id));

//        return ResponseEntity.ok();
        return "Done";
    }


    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }


    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = orderService.getCartAmount(orderDTO.getCartItems());

        Customer customer = new Customer(orderDTO.getCustomerName(), orderDTO.getCustomerEmail());
        Integer customerIdFromDb = customerService.isCustomerPresent(customer);
        if (customerIdFromDb != null) {
            customer.setId(customerIdFromDb);
            logger.info("Customer already present in db with id : " + customerIdFromDb);
        }else{
            customer = customerService.saveCustomer(customer);
            logger.info("Customer saved.. with id : " + customer.getId());
        }
        Order order = new Order(orderDTO.getOrderDescription(), customer, orderDTO.getCartItems());
        order = orderService.saveOrder(order);
        logger.info("Order processed successfully..");

        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
        responseOrderDTO.setOrderId(order.getId());
        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

        logger.info("test push..");

        return ResponseEntity.ok(responseOrderDTO);
    }

}