import java.util.*;

class Chat implements ChatWindow.ChatListner {
    ChatWindow window;
    private Vector<String> namesByAlphabet;
    private List<Person> list;
    private int number = 0;

    Chat() {
        list = new ArrayList<>();
        list.add(new Baby("John"));
        list.add(new Femenist("Angela"));
        list.add(new Femenist("Stefany"));

        namesByAlphabet = new Vector<>();
        for (Person it : list) {
            namesByAlphabet.add(it.getName());
        }
        Collections.sort(namesByAlphabet);

        window = new ChatWindow();
        window.setListner(this);
        window.setChats();
        window.setVisible(true);
    }

    public void sendQuestion(String s) {
        try {
            window.printAnswer(list.get(number).answer(s));
        } catch (BadQuestion ex) {
            window.printAnswer(ex.getMessage());
        }
    }

    public void saveChats() {
        for ( Person it : list) {
            it.saveChat();
        }
    }

    public void changeBot(String nameOfPerson) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(nameOfPerson)) {
                number = i;
                window.changeDialog(number, list.get(number).GetChatHistory());
                return;
            }
        }
    }

    public Vector<String> getNamesSortedAlphabet() {
        List<Person> personByAlphabet = new ArrayList<>(list);
        personByAlphabet.sort(Comparator.comparing(Person::getName));
        Vector<String> namesByAlphabet = new Vector<String>();
        for (Person it : personByAlphabet) {
            namesByAlphabet.add(it.getName());
        }
        return namesByAlphabet;
    }

    public Vector<String> getNamesSortedDate() {
        List<Person> personByDate = new ArrayList<>(list);
        personByDate.sort(Comparator.comparing(Person::getDate));
        Vector<String> namesByDate = new Vector<String>();
        for (Person it : personByDate) {
            namesByDate.add(it.getName());
        }
        return namesByDate;
    }

    public void getChats() {
        for (Person it : list) {
            it.getChat();
        }
    }

}


