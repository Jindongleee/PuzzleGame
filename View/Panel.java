package View;
import Model.ImageData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {

    private ImageData imageData;
    private BufferedImage[] imgParts;
    private int[] numbers;
    private int num;
    private Timer timer;
    private boolean isTimeRunning;
    private long startTime;
    private long elapsedTime;
    private long seconds;
    private long minutes;
    private int clickCount;

    JLabel clickCountLabel;

    public Panel(ImageData imageData, String text) {
        this.imageData = imageData;
        initializePanel(text);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int partWidth = getWidth() / num;
        int partHeight = getHeight() / num;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (numbers[i * num + j] != -1) {
                    g.drawImage(imgParts[numbers[i * num + j] - 1], partWidth * j, partHeight * i, partWidth, partHeight, this);
                }
            }
        }
    }

    public boolean Complete() {
        for (int i = 0; i < (num*num)-1; i++) {
            if (numbers[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    private void startTimer() {
        isTimeRunning = true;
        startTime = System.currentTimeMillis();
        timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
            isTimeRunning = false;
        }
    }

    private void updateTime() {
        elapsedTime = System.currentTimeMillis() - startTime;
        seconds = elapsedTime / 1000;
        minutes = seconds / 60;
        seconds = seconds % 60;
        clickCountLabel.setText("Click Count: " + clickCount + " | Time: " + minutes + "Min" + seconds + "Sec");
    }

    private void initializePanel(String text) {
        this.num = Integer.parseInt(text);
        clickCount = 0;
        clickCountLabel = new JLabel();
        imgParts = imageData.splitImage(num);
        numbers = imageData.initialNums(num);
        isTimeRunning = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                super.mousePressed(e);
                Point clickPoint = e.getPoint();
                int width = getWidth() / num;
                int height = getHeight() / num;
                int clickedIndex = (clickPoint.y / height) * num + (clickPoint.x / width);

                if (numbers[clickedIndex] != -1) {
                    int row = clickedIndex / num;
                    int col = clickedIndex % num;

                    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                    for (int[] dir : directions) {
                        int newRow = row + dir[0];
                        int newCol = col + dir[1];
                        if (newRow >= 0 && newRow < num && newCol >= 0 && newCol < num) {

                            int neighborIndex = newRow * num + newCol;

                            if (numbers[neighborIndex] == -1) {
                                numbers[neighborIndex] = numbers[clickedIndex];
                                numbers[clickedIndex] = -1;
                                repaint();
                                clickCount++;
                                if (!isTimeRunning) {
                                    startTimer();
                                }
                                if (Complete()) {
                                    stopTimer();
                                    JOptionPane.showMessageDialog(Panel.this, "Click Count: " + clickCount+" Time: "+minutes +"Min" +seconds+"Sec");
                                    clickCount=0;
                                    elapsedTime=0;
                                }

                            }
                        }
                    }
                }
            }
        });
    }
}








