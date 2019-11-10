import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class ChatWindow extends JFrame {
    private JButton openSmilesButton;
    private JPanel panel;
    private JButton sendButton;
    private JTextField enterField;
    private JTextArea outputArea;
    private JButton chooseSort;

    private ChatListner listner;

    private Vector<String> chatsData;
    private JList<String> chatsList;

    private boolean ifSortedByDate = false;


    private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelSmiles = new JPanel();

    private Smile smiles;

    ChatWindow() {

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                listner.getChats();
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                listner.saveChats();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setTitle("Chat");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        lpane = new JLayeredPane();
        setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 600, 400);
        panel.setBounds(0, 0, 600, 400);
        panel.setOpaque(true);


        panelSmiles = new JPanel();
        panelSmiles.setBackground(Color.LIGHT_GRAY);
        panelSmiles.setBounds(300, 200, 200, 100);
        panelSmiles.setVisible(false);


        panelSmiles.setLayout(new GridLayout(4, 2));
        for (int i = 0; i < 8; ++i) {
            Smile smile = new Smile(i);
            JLabel label = new JLabel(smile.getAsString());
            label.setOpaque(false);
            label.addMouseListener(new MouseAdapter() {
                                       @Override
                                       public void mouseClicked(MouseEvent e) {
                                           enterField.setText(enterField.getText() + label.getText());
                                           panelSmiles.setVisible(false);
                                       }
                                   }
            );
            panelSmiles.addMouseListener(
                    new MouseAdapter() {
                        @Override
                        public void mouseExited(MouseEvent e) {
                            panelSmiles.setVisible(false);
                        }
                    }
            );
            panelSmiles.add(label);
        }


        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        enterField = new JTextField();
        c.weightx = 0.3;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(enterField, c);

        outputArea = new JTextArea("Choose chat", 20, 50);
        outputArea.setPreferredSize(new Dimension(100, 100));
        outputArea.setLineWrap(true);
        outputArea.setEditable(false);
        ;
        outputArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        c.weightx = 0.7;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(new JScrollPane(outputArea), c);

        sendButton = new JButton("Enter");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridx = 3;
        c.gridy = 1;
        panel.add(sendButton, c);
        sendButton.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent event) {
                                             String message = enterField.getText();
                                             listner.sendQuestion(message);
                                         }
                                     }
        );

        chooseSort = new JButton("A-Z");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(chooseSort, c);
        chooseSort.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent event) {
                                             if (chooseSort.getText().equals("A-Z")) {
                                                 chooseSort.setText("date");
                                                 ifSortedByDate = false;
                                             } else {
                                                 chooseSort.setText("A-Z");
                                                 ifSortedByDate = true;
                                             }
                                             updateChatsSorting();
                                         }
                                     }
        );

        openSmilesButton = new JButton("㋡");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(openSmilesButton, c);
        openSmilesButton.addMouseListener(new MouseListener() {
                                              @Override
                                              public void mouseClicked(MouseEvent e) {
                                              }

                                              @Override
                                              public void mousePressed(MouseEvent e) {

                                              }

                                              @Override
                                              public void mouseReleased(MouseEvent e) {

                                              }

                                              @Override
                                              public void mouseEntered(MouseEvent e) {
                                                  panelSmiles.setVisible(true);
                                              }

                                              @Override
                                              public void mouseExited(MouseEvent e) {

                                              }
                                          }
        );


        lpane.add(panel, JLayeredPane.DEFAULT_LAYER);
        lpane.add(panelSmiles, JLayeredPane.PALETTE_LAYER);
        pack();
    }

    public interface ChatListner {
        void sendQuestion(String s);

        void changeBot(String nameOfPerson);

        void saveChats();

        void getChats();

        Vector<String> getNamesSortedAlphabet();

        Vector<String> getNamesSortedDate();
    }

    void setListner(ChatListner inputListner) {
        listner = inputListner;
    }

    void printAnswer(String s) {
        outputArea.append(s + "\n");
    }

    void changeDialog(int numberOfBot, ArrayList<Message> historyOfChat) {
        outputArea.setText("");
        for (Message message : historyOfChat) {
            outputArea.append(message.getAsString() + "\n");
        }
    }

    void setChats() {
        GridBagConstraints c = new GridBagConstraints();
        chatsData = new Vector<>();
        chatsData = listner.getNamesSortedAlphabet();
        chatsList = new JList<>(chatsData);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(chatsList, c);
        chatsList.addMouseListener(new MouseAdapter() {
                                       public void mouseClicked(MouseEvent e) {
                                           listner.changeBot(chatsData.get(chatsList.locationToIndex(e.getPoint())));
                                       }
                                   }
        );
    }

    void updateChatsSorting() {
        if (ifSortedByDate) {
            chatsData = listner.getNamesSortedDate();
            chatsList.setListData(chatsData);
        } else {
            chatsData = listner.getNamesSortedAlphabet();
            chatsList.setListData(chatsData);
        }
    }

}
