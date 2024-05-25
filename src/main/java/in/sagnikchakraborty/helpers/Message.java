package in.sagnikchakraborty.helpers;

public class Message {

    private MessageType type = MessageType.INFO;
    private MessageColor color = MessageColor.blue;
    private String content;

    public Message() {
    }

    public Message(Builder builder) {
        this.type = builder.type;
        this.color = builder.color;
        this.content = builder.content;
    }

    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public MessageColor getColor() {
        return color;
    }
    public void setColor(MessageColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", color=" + color +
                ", content='" + content + '\'' +
                '}';
    }

    public static class Builder {
        private MessageType type = MessageType.INFO;
        private MessageColor color = MessageColor.blue;
        private String content;

        public Builder type(MessageType type) {
            this.type = type;
            return this;
        }

        public Builder color(MessageColor color) {
            this.color = color;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
