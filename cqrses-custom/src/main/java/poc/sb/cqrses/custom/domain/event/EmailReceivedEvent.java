package poc.sb.cqrses.custom.domain.event;


import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailReceivedEvent {

    private final String from;
    private final String to;
    private final String cc;
    private final String subject;
    private final String content;

    public EmailReceivedEvent(@JsonProperty String from,
                              @JsonProperty String to,
                              @JsonProperty String cc,
                              @JsonProperty String subject,
                              @JsonProperty String content) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getCc() {
        return cc;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailReceivedEvent that = (EmailReceivedEvent) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        if (cc != null ? !cc.equals(that.cc) : that.cc != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (cc != null ? cc.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }


    public static final class Builder {
        private String from;
        private String to;
        private String cc;
        private String subject;
        private String content;

        private Builder() {
        }

        public static Builder anEmailReceivedEvent() {
            return new Builder();
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder cc(String cc) {
            this.cc = cc;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public EmailReceivedEvent build() {
            return new EmailReceivedEvent(from, to, cc, subject, content);
        }
    }
}