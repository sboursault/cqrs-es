package poc.sb.cqrses.custom.domain.model;

import poc.sb.cqrses.custom.domain.event.EmailReadEvent;
import poc.sb.cqrses.custom.domain.event.EmailReceivedEvent;
import poc.sb.cqrses.custom.domain.event.EmailStarredEvent;

import java.util.Objects;
import java.util.UUID;

public class Email {

    private String id;

    private String from;
    private String to;
    private String cc;
    private String subject;
    private String content;
    private boolean read = false;
    private boolean trashed = false;
    private boolean starred;

    public void on(EmailReceivedEvent event) {
        if (this.id == null)
            this.id = UUID.randomUUID().toString();  // not sure this works well on rhydratation
        this.from    = event.getFrom();
        this.to      = event.getTo();
        this.cc      = event.getCc();
        this.from    = event.getFrom();
        this.subject = event.getSubject();
        this.content = event.getContent();
    }

    public void on(EmailStarredEvent event) {
        this.starred = true;
    }

    public void on(EmailReadEvent event) {
        this.read = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}