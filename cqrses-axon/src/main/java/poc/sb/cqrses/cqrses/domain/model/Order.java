package poc.sb.cqrses.cqrses.domain.model;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import poc.sb.cqrses.cqrses.domain.command.ConfirmOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.PlaceOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.ShipOrderCommand;
import poc.sb.cqrses.cqrses.domain.event.OrderConfirmedEvent;
import poc.sb.cqrses.cqrses.domain.event.OrderPlacedEvent;
import poc.sb.cqrses.cqrses.domain.event.OrderShippedEvent;

@Aggregate
public class Order {

    @AggregateIdentifier
    private String orderId;
    private boolean confirmed;

    /*@CommandHandler
    public Order(PlaceOrderCommand command) {
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }
*/
    /*@CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }
*/
    //@CommandHandler
    //public void handle(ShipOrderCommand command) {
    //    if (!confirmed) {
    //        throw new IllegalStateException("Cannot ship an order which has not been confirmed yet.");
    //    }
    //    apply(new OrderShippedEvent(orderId));
    //}

    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
        confirmed = false;
        //if (isLive())
        //    updateViewModel(this);
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        confirmed = true;
    }

    public /*protected*/ Order() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}