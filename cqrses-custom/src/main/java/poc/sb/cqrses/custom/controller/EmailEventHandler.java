package poc.sb.cqrses.custom.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poc.sb.cqrses.custom.domain.event.EmailReceivedEvent;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.util.UUID;

@Component
public class EmailEventHandler {

    @Autowired
    private Jedis jedis;

    public void on(EmailReceivedEvent event) {
        storeEmailEvent(event);

    }

    private void storeEmailEvent(EmailReceivedEvent event) {

        String emailId = UUID.randomUUID().toString();
        Instant instant = Instant.now();
        double timestamp = Long.valueOf(instant.toEpochMilli()).doubleValue();
        String aggregateKey = "email" + ":" + emailId;

        String member = "" +
                "{" +
                "  \"eventType\": \"" + event.getClass().toString() + "\"," +  // un peu moyen de stocker le path complet
                "  \"timestamp\": \"" + timestamp + "\"," +                    // ce timestamp renforce l'unicité du member
                "  \"payload\": \"" + objectToJson(event) + "\"," +
                "}";

        jedis.zadd(aggregateKey, timestamp, member);
    }

    private String objectToJson(EmailReceivedEvent event) {
        try {
            return new ObjectMapper().writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
