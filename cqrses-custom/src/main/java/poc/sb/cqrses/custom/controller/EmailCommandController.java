package poc.sb.cqrses.custom.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.sb.cqrses.custom.domain.event.EmailReceivedEvent;

@Api(tags = "Email commands", description = " ")
@RestController
@RequestMapping("/email-api/v1")
public class EmailCommandController {

    @Autowired
    private EmailEventHandler handler;

    @PostMapping("/email-received")
    public void received(EmailReceivedEvent event) {
        handler.on(event);
    }

}