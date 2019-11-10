import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

class Femenist extends  AbstractPerson implements Person  {

    private FileWriter fileWriter;
//    public LocalDateTime localDateTime = LocalDateTime.now();

    Femenist(String newName) {
        name = newName;
        chatHistory = new ArrayList<>();
    }

    @Override
    public String answer(String s) throws BadQuestion {
        if (s.contains("кухн")) {
            throw new BadQuestion("сам ты " + s + "!!! я с тобой не разговариваю!!!");
        }
        Message enterMessage = new Message(localDateTime, "You", s);
        chatHistory.add(enterMessage);
        Message answerMessage = new Message(localDateTime, name, s + "ка");
        chatHistory.add(answerMessage);
        return enterMessage.getAsString() + "\n" + answerMessage.getAsString();
    }
}