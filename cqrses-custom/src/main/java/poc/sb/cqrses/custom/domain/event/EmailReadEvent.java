package poc.sb.cqrses.custom.domain.event;

public class EmailReadEvent {

    private final String uuid;

    public String getUuid() {
        return uuid;
    }

    public EmailReadEvent(String uuid) {
        this.uuid = uuid;
    }
}