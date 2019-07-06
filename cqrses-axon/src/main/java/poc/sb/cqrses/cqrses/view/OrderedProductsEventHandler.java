package poc.sb.cqrses.cqrses.view;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.sb.cqrses.cqrses.domain.event.OrderConfirmedEvent;
import poc.sb.cqrses.cqrses.domain.event.OrderPlacedEvent;
import poc.sb.cqrses.cqrses.domain.event.OrderShippedEvent;
import poc.sb.cqrses.cqrses.domain.model.Order;
import poc.sb.cqrses.cqrses.view.query.FindAllOrderedProductsQuery;
import poc.sb.cqrses.cqrses.view.query.OrderedProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderedProductsEventHandler {

    private final Map<String, OrderedProduct> orderedProducts = new HashMap<>();

    @Autowired
    private EventStore eventStore;

    @Autowired
    private org.axonframework.modelling.command.Repository<Order> orderRepository;

    @EventHandler
    public void on(OrderPlacedEvent event) {
        String orderId = event.getOrderId();

        List<?> events   = eventStore.readEvents(event.getOrderId()).asStream().map(s -> s.getPayload()).collect(Collectors.toList());

        /*Order order = (Order) */

        Aggregate<Order> orderAggregate = orderRepository.load(orderId);
        Order order = orderAggregate.invoke(Function.identity());
        // eventStore.readEvents(orderId).peek().toString()
        // Order order = new Order();
        // VOIR COMMENT CA SE COMPORTE QD ON A PLUSIEURS EVENTS
        //        PEUT ETRE QUON PEUT OBTENIR LORDER VIA UNE QUERY ??
        //        VOIR AUSSI CE QUE JAI NOTé SUR le NOTEBOOK
        orderedProducts.put(orderId, new OrderedProduct(orderId, event.getProduct()));
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        orderedProducts.computeIfPresent(event.getOrderId(), (orderId, orderedProduct) -> {
            orderedProduct.setOrderConfirmed();
            return orderedProduct;
        });
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        orderedProducts.computeIfPresent(event.getOrderId(), (orderId, orderedProduct) -> {
            orderedProduct.setOrderShipped();
            return orderedProduct;
        });
    }

    @QueryHandler
    public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orderedProducts.values());
    }

}