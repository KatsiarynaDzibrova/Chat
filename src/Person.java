import java.time.LocalDateTime;
import java.util.ArrayList;

interface Person extends Comparable<Person> {

    String answer(String s) throws BadQuestion;
    ArrayList<Message> GetChatHistory();
    void saveChat();
    String getName();
    void getChat();
    int compareTo(Person o);
    LocalDateTime getDate();
}
