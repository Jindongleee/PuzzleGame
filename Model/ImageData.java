package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ImageData {


    private ImageIcon puzzleImage;

    private Image puzzleImg;

    public Image getPuzzleImg() {
        return puzzleImg;
    }

    private ImageIcon mainPageImage;

    private Image mainPageImg;

    public Image getMainPageImg() {
        return mainPageImg;
    }

    private ArrayList<Integer> numberList;
    private int num;

    public ImageData() {
        Random random = new Random();
        int randomInt=random.nextInt(4)+1;
        String imageNum = Integer.toString(randomInt);
        this.puzzleImage = new ImageIcon(filename);
        this.puzzleImg = puzzleImage.getImage();
        this.mainPageImage = new ImageIcon(filename);
        this.mainPageImg = mainPageImage.getImage();
    }


    public BufferedImage toBufferedImage() {
        BufferedImage bimage = new BufferedImage(puzzleImg.getWidth(null), puzzleImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bgr = bimage.createGraphics();
        bgr.drawImage(puzzleImg, 0, 0, null);
        bgr.dispose();
        return bimage;
    }

    public BufferedImage[] splitImage(int num) {
        BufferedImage[] imgParts = new BufferedImage[num*num];
        BufferedImage bufferedImage = toBufferedImage();
        int partWidth = bufferedImage.getWidth() / num;
        int parHeight = bufferedImage.getHeight() / num;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                imgParts[i * num + j] = bufferedImage.getSubimage(partWidth * j, parHeight * i, partWidth, parHeight);
            }
        }
        return imgParts;
    }

    public int[] initialNums(int num) {
        this.num=num;
        int[] numbers = new int[num * num];

        numberList = new ArrayList<>();
        for (int i = 1; i <= (num * num) - 1; i++) {
            numberList.add(i);
        }
        numberList.add(-1);

        while(true){
            int count =0;
            int blankLocation=0;
            Collections.shuffle(numberList);
            for(int i=0;i<numberList.size()-1;i++){
                if(numberList.get(i)==-1)
                    continue;
                for(int j=1+i;j<numberList.size();j++){
                    if(numberList.get(j)==-1)
                        continue;
                    if(numberList.get(i)>numberList.get(j)){
                        count++;
                    }
                }
            }
            for(int i=numberList.size()-1;i>=0;i--){
                if(numberList.get(i)==-1){
                    int row = i/num;
                    blankLocation=num-row;
                    break;
                }
            }
            //Odd
            if(num%2!=0 && count%2==0){
                break;
            }
            //Even
            else if(num%2==0){
                if(blankLocation%2==0 && count%2!=0 || blankLocation%2!=0 && count%2==0)
                    break;
            }
        }

        for (int i = 0; i < num * num; i++) {
            numbers[i] = numberList.get(i);
        }
        return numbers;
    }

}

