import jdk.vm.ci.meta.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Message {
    String text;
    String name;
    LocalDateTime time;
    private DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");

    Message(LocalDateTime time, String name, String text) {
        this.time = time;
        this.name = name;
        this.text = text;
    }
    String getAsString(){
        return "[" + time.getDayOfWeek() + " " + + time.getHour() + ":" + time.getMinute() + "] " + name + ": " + text;
    }
    String getAsStringForFile(){
        return time.format( dateTimeFormatter) + "\n" + name + "\n" + text;
    }
    LocalDateTime getTime() {
        return time;
    }
    String getName() {
        return name;
    }
    String getText() {
        return text;
    }
}
