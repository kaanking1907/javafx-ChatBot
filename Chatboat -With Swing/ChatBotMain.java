import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class ChatBotMain extends JFrame 
{

    private JPanel contentPane;
    private JTextField textField;
    private JButton btnSend;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private String userName = "";

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    ChatBotMain frame = new ChatBotMain();
                    frame.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChatBotMain() 
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 850, 657);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        textArea = new JTextArea();
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 23));
        textField.setColumns(10);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    action();
                }
            }
        });
        inputPanel.add(textField);

        btnSend = new JButton("Gönder");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
        inputPanel.add(btnSend);

        inputPanel.setPreferredSize(new Dimension(getWidth(), 60));

        scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight() - inputPanel.getPreferredSize().height));

        textField.requestFocus();

        showSignInMenu();
    }

    void action() {
        String query = textField.getText();
        textArea.append(" " + userName + "-  ͡⚈ ͜ʖ ͡⚈-> " + query + "\n");
        query = query.toLowerCase();

        if (query.contains("clr")) 
        {
            textArea.setText("");
            textField.setText("");
        } 
        else if (query.contains("cmd"))
        {
            ai("Komut Listesi\n\tclr = Ekranı temizler");
        } 
        else 
        {
            ai("Ben aptal bir chatbot'um, sorunuza cevap veremem. Komutlar için cmd yazın.");
        }
    }

    void ai(String s) 
    {
        textArea.append(" AI-✍( ⚈ ͜ʖ ⚈ ))->" + s + "\n\n");
        textField.setText("");
    }

    void showSignInMenu() 
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets.bottom = 5;

        JLabel lblUsername = new JLabel("Kullanıcı Adı:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setForeground(Color.WHITE);
        panel.add(lblUsername, gbc);

        JTextField txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Şifre:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setForeground(Color.WHITE);
        panel.add(lblPassword, gbc);

        JPasswordField txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(txtPassword, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton btnSignIn = new JButton("Giriş Yap");
        btnSignIn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (username.trim().isEmpty() || password.trim().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, "Lütfen kullanıcı adınızı ve şifrenizi girin.", "Giriş Başarısız.",
                            JOptionPane.ERROR_MESSAGE);
                } 
                else 
                {
                    boolean isValid = true;

                    if (isValid) 
                    {
                        userName = username;
                        setTitle("ChatBot - " + userName + " olarak giriş yapıldı");
                        JOptionPane.getRootFrame().dispose();
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Geçersiz kullanıcı adı veya şifre. Lütfen tekrar deneyin",
                                "Giriş Başarısız.", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(btnSignIn);

        JButton btnCancel = new JButton("Çıkış");
        btnCancel.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        buttonPanel.add(btnCancel);

        panel.setBackground(Color.DARK_GRAY);
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.DARK_GRAY);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        containerPanel.setBackground(Color.DARK_GRAY);
        containerPanel.add(panel, BorderLayout.CENTER);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        JOptionPane.showOptionDialog(null, containerPanel, "Giriş Yap", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);
    }
}
