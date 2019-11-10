import java.util.ArrayList;

class Baby  extends  AbstractPerson  {

    private Answer<String> textAnswer = new Answer<>();
    private Answer<Emoji> emojiAnswer = new Answer<>();

    Baby(String newName) {
        name = newName;
        chatHistory = new ArrayList<>();
        textAnswer.set("очечек");
        emojiAnswer.set(new Emoji("◉_◉"));

    }

    @Override
    public String answer(String s) throws BadQuestion {
        if (s.contains("школ")) {
            throw new BadQuestion(emojiAnswer.get().textInterpretation());
        }
        Message enterMessage = new Message(localDateTime, "You", s);
        chatHistory.add(enterMessage);
        Message answerMessage = new Message(localDateTime, name, s + textAnswer.get());
        chatHistory.add(answerMessage);
        return enterMessage.getAsString() + "\n" + answerMessage.getAsString();
    }
}

