package poc.sb.cqrses.custom.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poc.sb.cqrses.custom.domain.event.EmailReceivedEvent;
import poc.sb.cqrses.custom.view.EmailView;
import poc.sb.cqrses.custom.view.repository.EmailViewRepository;

@Api(tags = "Email queries", description = " ")
@RestController
@RequestMapping("/email-api/v1")
public class EmailViewController {

    @Autowired
    private EmailViewRepository emailViewRepository;

    @GetMapping("/emails")
    public Iterable<EmailView> findAll() {
        return emailViewRepository.findAll();
    }

}