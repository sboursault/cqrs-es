package poc.sb.cqrses.custom.domain.event;

public class EmailStarredEvent {

    private final String uuid;

    public String getUuid() {
        return uuid;
    }

    public EmailStarredEvent(String uuid) {
        this.uuid = uuid;
    }
}