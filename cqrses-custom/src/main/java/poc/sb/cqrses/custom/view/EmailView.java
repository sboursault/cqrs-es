package poc.sb.cqrses.custom.view;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "emails"/*, type = "email"*/)
public class EmailView {

    private String id;

    private String from;
    private String to;
    private String cc;
    private String subject;
    private String content;
    private boolean read = false;
    private boolean trashed = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public void setTrashed(boolean trashed) {
        this.trashed = trashed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailView emailView = (EmailView) o;

        return id != null ? id.equals(emailView.id) : emailView.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public static final class Builder {
        private EmailView emailView;

        private Builder() {
            emailView = new EmailView();
        }

        public static Builder anEmailView() {
            return new Builder();
        }

        public Builder id(String id) {
            emailView.setId(id);
            return this;
        }

        public Builder from(String from) {
            emailView.setFrom(from);
            return this;
        }

        public Builder to(String to) {
            emailView.setTo(to);
            return this;
        }

        public Builder cc(String cc) {
            emailView.setCc(cc);
            return this;
        }

        public Builder subject(String subject) {
            emailView.setSubject(subject);
            return this;
        }

        public Builder content(String content) {
            emailView.setContent(content);
            return this;
        }

        public Builder read(boolean read) {
            emailView.setRead(read);
            return this;
        }

        public Builder trashed(boolean trashed) {
            emailView.setTrashed(trashed);
            return this;
        }

        public EmailView build() {
            return emailView;
        }
    }
}
