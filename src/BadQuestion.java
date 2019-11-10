public class BadQuestion extends Exception {
    String message;

    BadQuestion(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
