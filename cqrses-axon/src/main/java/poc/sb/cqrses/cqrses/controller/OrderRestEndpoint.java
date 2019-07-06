package poc.sb.cqrses.cqrses.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.sb.cqrses.cqrses.domain.command.ConfirmOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.PlaceOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.ShipOrderCommand;
import poc.sb.cqrses.cqrses.view.query.FindAllOrderedProductsQuery;
import poc.sb.cqrses.cqrses.view.query.OrderedProduct;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    //@Autowired
    //private OrderCommandHandler orderService;

    public OrderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/ship-order")
    public void shipOrder() {
        String uuid = UUID.randomUUID().toString(); // TODO uuid devrait pas être généré par le controleur, voir si il peut être retourné par la cmdGtw
        // orderService.handle(new PlaceOrderCommand(uuid, "Deluxe Chair"));
        commandGateway.send(new PlaceOrderCommand(uuid, "Deluxe Chair"));
        commandGateway.send(new ConfirmOrderCommand(uuid));
        //orderService.confirmOrder(uuid);
        commandGateway.send(new ShipOrderCommand(uuid));

        //orderService.shipOrder(uuid);
    }

    @GetMapping("/ship-unconfirmed-order")
    public void shipUnconfirmedOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        // This throws an exception, as an Order cannot be shipped if it has not been confirmed yet.
        commandGateway.send(new ShipOrderCommand(orderId));
    }

    @GetMapping("/all-orders")
    public List<OrderedProduct> findAllOrderedProducts() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(OrderedProduct.class))
                           .join();
    }

}