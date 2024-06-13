package View;
import Model.ImageData;
import Model.SoundData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private ImageData imageData;

    public MainView() {
        this.imageData = new ImageData();

        SoundData soundData = new SoundData();
        Container mainContainer = getContentPane();
        mainContainer.setLayout(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageData.getMainPageImg(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set size to match the frame
        backgroundPanel.setBounds(0, 0, 1200, 1200);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start!");
        JButton exitButton = new JButton("Exit!");
        JPanel bgmController = soundData.bgmControll();

        setTitle("Enjoy_PuzzleGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        soundData.bgmPlay();

        buttonPanel.setLayout(new GridLayout(1, 2));

        startButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 25));
        exitButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 25));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                soundData.bgmStop();
                new SubView();
            }
        });


        //exit Program
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        buttonPanel.setSize(300, 100);
        buttonPanel.setLocation(450, 650);
        buttonPanel.setBackground(new Color(231, 160, 253, 255));

        bgmController.setLocation(0, 0);
        bgmController.setBackground(new Color(231, 160, 253, 255));
        bgmController.setSize(100, 50);

        mainContainer.add(buttonPanel);
        mainContainer.add(bgmController);
        mainContainer.add(backgroundPanel);

        setSize(1200, 1200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
