import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

/**
 * Class "Main" shows program work
 *
 * @version 1.0 Reborn 02.11.2021
 * @author Igor Kolesov PIN-23
 */

public class App {
    //main
    public static void main(String[] args){
        FrameLab6 frame = new FrameLab6();
    }
}

class FrameLab6 extends JFrame{
    private int width = 600;
    private int height = 800;

    private static int keyButtonsCount = 0;
    private static long start;
    private static long last;
    private static long total;

    public FrameLab6()
    {
        this.setSize(this.width, this.width);
        this.setTitle("TextBox");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());

        JTextField TField = new JTextField(40);
        TField.setBackground(Color.black);
        TField.setFont(new Font("Serif", Font.BOLD, 20));
        TField.setForeground(Color.white);
        JTextField TimeField = new JTextField(10);
        TimeField.setBackground(Color.black);
        TimeField.setFont(new Font("Serif", Font.BOLD, 20));
        TimeField.setForeground(Color.white);

        this.add(TField,new GridBagConstraints(0,6,1,1,0.0,0.9,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
        this.add(TimeField,new GridBagConstraints(0,7,1,2,0.0,0.9,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 0,0));
        this.pack();


        class KeyBoardKeyListener implements KeyListener {
            @Override
            public void keyTyped(KeyEvent event) {
                if(keyButtonsCount==0) start = System.nanoTime();
                last = System.nanoTime();
                keyButtonsCount++;
            }

            @Override
            public void keyPressed(KeyEvent event) {
            }

            @Override
            public void keyReleased(KeyEvent event) {

            }
        }


        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        last -= System.nanoTime();
                        total = System.nanoTime() - start + last;
                        TimeField.setText(""+(int)(keyButtonsCount * 60/((double)total/1000000000)));
                        Thread.sleep(1000); //1000 - 1 сек
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        run.start(); // заводим
        TField.addKeyListener(new KeyBoardKeyListener());
    }
}

