package mx.edu.utez.util;

import lombok.NonNull;

public class Message {
    private @NonNull String title;
    private @NonNull String message;
    private @NonNull String type;
    private Object result;
    private int code;

    public @NonNull String getTitle() {
        return this.title;
    }

    public @NonNull String getMessage() {
        return this.message;
    }

    public @NonNull String getType() {
        return this.type;
    }

    public Object getResult() {
        return this.result;
    }

    public int getCode() {
        return this.code;
    }

    public void setTitle(final @NonNull String title) {
        if (title == null) {
            throw new NullPointerException("title is marked non-null but is null");
        } else {
            this.title = title;
        }
    }

    public void setMessage(final @NonNull String message) {
        if (message == null) {
            throw new NullPointerException("message is marked non-null but is null");
        } else {
            this.message = message;
        }
    }

    public void setType(final @NonNull String type) {
        if (type == null) {
            throw new NullPointerException("type is marked non-null but is null");
        } else {
            this.type = type;
        }
    }

    public void setResult(final Object result) {
        this.result = result;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Message)) {
            return false;
        } else {
            Message other = (Message) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                label61:
                {
                    Object this$title = this.getTitle();
                    Object other$title = other.getTitle();
                    if (this$title == null) {
                        if (other$title == null) {
                            break label61;
                        }
                    } else if (this$title.equals(other$title)) {
                        break label61;
                    }

                    return false;
                }

                label54:
                {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label54;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label54;
                    }

                    return false;
                }

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Message;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getCode();
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        return result;
    }

    public String toString() {
        return "Message(title=" + this.getTitle() + ", message=" + this.getMessage() + ", type=" + this.getType() + ", result=" + this.getResult() + ", code=" + this.getCode() + ")";
    }

    public Message(final @NonNull String title, final @NonNull String message, final @NonNull String type) {
        if (title == null) {
            throw new NullPointerException("title is marked non-null but is null");
        } else if (message == null) {
            throw new NullPointerException("message is marked non-null but is null");
        } else if (type == null) {
            throw new NullPointerException("type is marked non-null but is null");
        } else {
            this.title = title;
            this.message = message;
            this.type = type;
        }
    }

    public Message(final @NonNull String title, final @NonNull String message, final @NonNull String type, final Object result, final int code) {
        if (title == null) {
            throw new NullPointerException("title is marked non-null but is null");
        } else if (message == null) {
            throw new NullPointerException("message is marked non-null but is null");
        } else if (type == null) {
            throw new NullPointerException("type is marked non-null but is null");
        } else {
            this.title = title;
            this.message = message;
            this.type = type;
            this.result = result;
            this.code = code;
        }
    }

    public Message() {
    }
}
