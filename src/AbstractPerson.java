import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

class AbstractPerson implements Comparable<Person>, Person {
    ArrayList<Message> chatHistory;
    String name;
    public LocalDateTime localDateTime = LocalDateTime.now();
    private DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");

    public String getName(){
        return name;
    }
    public void saveChat() {
        try(FileWriter fileWriter = new FileWriter(name +".txt")) {
            for (Message it : chatHistory) {
                String stringToSave = it.getAsStringForFile() + "\n";
                fileWriter.write(stringToSave);
            }
        } catch (IOException e) {
            System.out.println("Problem2!\n");
        }
    }

    @Override
    public String answer(String s) throws BadQuestion {
        return null;
    }

    public ArrayList<Message> GetChatHistory(){
        return chatHistory;
    }
    public void getChat() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(name + ".txt"))) {
            String date = bufferedReader.readLine();
            while(date != null) {
                String name = bufferedReader.readLine();
                String text = bufferedReader.readLine();
                chatHistory.add(new Message(LocalDateTime.parse(date, dateTimeFormatter), name, text));
                date = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }
    }


    public LocalDateTime getDate(){
        if (chatHistory.size() == 0) return LocalDateTime.of(0,1,1,0,0);
        return chatHistory.get(chatHistory.size() - 1).getTime();
    }

    @Override
    public int compareTo(Person o) {
        System.out.println(getDate().toString() + "\n" + o.getDate().toString());
        System.out.println(getName() + getDate().compareTo(o.getDate()) + o.getName());
        return getDate().compareTo(o.getDate());
    }
}
