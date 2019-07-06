package poc.sb.cqrses.cqrses;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import poc.sb.cqrses.cqrses.domain.event.OrderPlacedEvent;
import poc.sb.cqrses.cqrses.domain.command.ConfirmOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.PlaceOrderCommand;
import poc.sb.cqrses.cqrses.domain.command.ShipOrderCommand;
import poc.sb.cqrses.cqrses.domain.event.OrderConfirmedEvent;
import poc.sb.cqrses.cqrses.domain.event.OrderShippedEvent;
import poc.sb.cqrses.cqrses.domain.model.Order;

import java.util.UUID;

public class OrderAggregateUnitTest {

    private FixtureConfiguration<Order> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(Order.class);
    }

    @Test
    public void giveNoPriorActivity_whenPlaceOrderCommand_thenShouldPublishOrderPlacedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.givenNoPriorActivity()
               .when(new PlaceOrderCommand(orderId, product))
               .expectEvents(new OrderPlacedEvent(orderId, product));
    }

    @Test
    public void givenOrderPlacedEvent_whenConfirmOrderCommand_thenShouldPublishOrderConfirmedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
               .when(new ConfirmOrderCommand(orderId))
               .expectEvents(new OrderConfirmedEvent(orderId));
    }

    @Test
    public void givenOrderPlacedEvent_whenShipOrderCommand_thenShouldThrowIllegalStateException() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product))
               .when(new ShipOrderCommand(orderId))
               .expectException(IllegalStateException.class);
    }

    @Test
    public void givenOrderPlacedEventAndOrderConfirmedEvent_whenShipOrderCommand_thenShouldPublishOrderShippedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
               .when(new ShipOrderCommand(orderId))
               .expectEvents(new OrderShippedEvent(orderId));
    }

}