package securityservices.shared.responses;

public class ResultRequest<T> {
    private Boolean fail;
    private String error;
    private T value;

    private ResultRequest(T value) {
        this.fail = false;
        this.value = value;
        this.error = "";
    }

    private ResultRequest(String error) {
        this.fail = true;
        this.error = error;
        this.value = null;
    }

    public Boolean failed() {
        return this.fail;
    }

    public static ResultRequest fails(String error) {
        return new ResultRequest(error);
    }

    public static <T> ResultRequest<T> done(T value) {
        return new ResultRequest(value);
    }

    public T getValue() {
        return this.value;
    }

    public String getError() {
        return this.error;
    }
}
