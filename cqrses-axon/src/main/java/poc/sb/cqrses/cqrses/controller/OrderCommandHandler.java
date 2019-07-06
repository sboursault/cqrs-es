package poc.sb.cqrses.cqrses.controller;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.sb.cqrses.cqrses.domain.command.ConfirmOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.PlaceOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.ShipOrderCommand;
import poc.sb.cqrses.cqrses.domain.event.OrderConfirmedEvent;
import poc.sb.cqrses.cqrses.domain.event.OrderPlacedEvent;
import poc.sb.cqrses.cqrses.domain.event.OrderShippedEvent;
import poc.sb.cqrses.cqrses.domain.model.Order;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Service
public class OrderCommandHandler {

    @Autowired
    private EventSourcingRepository<Order> orderRepository;

    //@Autowired
    //private EventGateway eventGateway;

//REPARER PROJET PUIS TESTER l'envoi via event gateway
//- naturel pour les evenements externes
//-api sans throws Exception
//- peut être plus simple à comprendre

    @CommandHandler

    public void handle(PlaceOrderCommand cmd) throws Exception
/* -> beurk */
 {

        //eventGateway.publish(new OrderPlacedEvent(cmd.getOrderId(), cmd.getProduct()));

        String uuid = cmd.getOrderId();
        orderRepository.newInstance(
                () -> {
                    Order model = new Order();
                    // validate(order, cmd)
                    apply(new OrderPlacedEvent(cmd.getOrderId(), cmd.getProduct()));
                    return model;
                });
    }



/*
    @CommandHandler
    public void handle(ConfirmOrderCommand cmd) {
        String uuid = cmd.getOrderId();
        orderRepository.load(uuid).execute(
                order -> {
                    // validate(order, cmd)
                    apply(new OrderConfirmedEvent(uuid));
                });
    }

    @CommandHandler
    public void handle(ShipOrderCommand cmd) {
        String uuid = cmd.getOrderId();
        orderRepository.load(uuid).execute(
                order -> {
                    if (!order.isConfirmed()) {
                        throw new IllegalStateException("Cannot ship an order which has not been confirmed yet.");
                    }
                    apply(new OrderShippedEvent(uuid));
                }
        );
    }
*/
}
