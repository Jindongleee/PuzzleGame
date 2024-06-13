package View;

import Model.ImageData;
import Model.SoundData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubView extends JFrame {

    private SoundData soundData;
    private ImageData imageData;
    private Panel panel;
    private Container allContain;
    private JPanel puzzlePanel;
    private String inputText;
    private JLabel original;
    private JPanel scorePanel;
    private JLabel clickJL;

    private void initializePanels(String text) {
        imageData = new ImageData();
        panel = new Panel(imageData,text);
    }

    private void switchToPanel(JPanel newPanel, JLabel newLabel) {
        allContain.remove(puzzlePanel);
        scorePanel.remove(clickJL);
        puzzlePanel = newPanel;
        puzzlePanel.setBackground(new Color(30, 27, 30));

        clickJL = newLabel;
        clickJL.setFont(new Font("Arial", Font.ITALIC, 30));
        clickJL.setForeground(new Color(255, 255, 255));
        scorePanel.add(clickJL);

        allContain.add(puzzlePanel, BorderLayout.CENTER);
        allContain.revalidate();
        allContain.repaint();
    }

    public SubView() {
        soundData = new SoundData();
        soundData.bgmPlay();
        initializePanels("3");
        setPreferredSize(new Dimension(1200, 1200));
        setTitle("Enjoy_PuzzleGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        allContain = getContentPane();
        allContain.setLayout(new BorderLayout(10, 10));

        JPanel headJp = new JPanel();
        headJp.setSize(100, 100);
        headJp.setLayout(new BorderLayout());

        JPanel titleJp = new JPanel();
        JLabel title = new JLabel("Welcome to PuzzleGame!");
        titleJp.setLayout(new FlowLayout(FlowLayout.CENTER));
        title.setFont(new Font("Arial", Font.ITALIC, 90));
        title.setForeground(Color.WHITE);
        titleJp.setBackground(new Color(70, 66, 70));
        titleJp.add(title);

        JPanel bgmController = soundData.bgmControll();
        bgmController.setLayout(new FlowLayout());
        bgmController.setBackground(new Color(70, 66, 70));

        JPanel numButtonJp = new JPanel();
        numButtonJp.setLayout(new FlowLayout(FlowLayout.CENTER));
        numButtonJp.setBackground(new Color(70, 66, 70));

        JButton numButton1 = new JButton("Easy");
        JButton numButton2 = new JButton("Normal");
        JButton numButton3 = new JButton("Hard");
        JTextField numButton4 = new JTextField("숫자입력", 15);

        JPanel originalGrid = new JPanel();
        originalGrid.setLayout(new GridLayout(3, 1));
        originalGrid.setPreferredSize(new Dimension(200, 200));

        JLabel blank1 = new JLabel("");

        numButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = numButton1.getText();
                if(text.equals("Easy")){
                    initializePanels("3");
                }
                switchToPanel(panel, panel.clickCountLabel);
            }
        });

        numButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = numButton2.getText();
                if(text.equals("Normal")){
                    initializePanels("4");
                }
                switchToPanel(panel, panel.clickCountLabel);
            }
        });

        numButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = numButton3.getText();
                if(text.equals("Hard")){
                    initializePanels("5");
                }
                switchToPanel(panel, panel.clickCountLabel);
            }
        });

        numButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inputText = numButton4.getText();
                    if (Integer.parseInt(inputText) > 10) {
                        JOptionPane.showMessageDialog(panel, "너무 큰 수를 입력하면 컴퓨터가 힘들어해요");
                    }
                    // Success conversion to String
                    initializePanels(inputText);
                    switchToPanel(panel, panel.clickCountLabel);
                } catch (NumberFormatException ex) {
                    // Failed
                    JOptionPane.showMessageDialog(panel, "숫자를 입력하세요");
                }
            }
        });

        //Difficulty button
        numButtonJp.add(numButton1);
        numButtonJp.add(numButton2);
        numButtonJp.add(numButton3);
        numButtonJp.add(numButton4);

        //Title panel
        headJp.add(bgmController, BorderLayout.NORTH);
        headJp.add(titleJp, BorderLayout.CENTER);
        headJp.add(numButtonJp, BorderLayout.SOUTH);


        puzzlePanel = panel;
        puzzlePanel.setBackground(new Color(30,27,30));

        original = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageData.getPuzzleImg(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        originalGrid.add(original);
        originalGrid.add(blank1);
        originalGrid.setBackground(new Color(43, 43, 45));

        clickJL = panel.clickCountLabel;
        clickJL.setFont(new Font("Arial", Font.ITALIC, 30));
        clickJL.setForeground(new Color(255, 255, 255));

        scorePanel = new JPanel();
        scorePanel.setPreferredSize(new Dimension(100, 50));
        scorePanel.setBackground(new Color(70, 66, 70));
        scorePanel.add(clickJL);

        allContain.add(headJp, BorderLayout.NORTH);
        allContain.add(puzzlePanel, BorderLayout.CENTER);
        allContain.add(originalGrid, BorderLayout.EAST);
        allContain.add(scorePanel, BorderLayout.SOUTH);
        allContain.setBackground(new Color(30, 27, 30));

        pack();
        setVisible(true);
    }
    public static void main(String[] args){
        new SubView();
    }

}


