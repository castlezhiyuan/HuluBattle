import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import  java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class God {
    void initGame()
    {
        JFrame my=new JFrame();
        my.setSize(1200,1200);
        my.setVisible(true);
        my.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        beginPanel bg=new beginPanel();
        my.add(bg);
        my.addKeyListener(bg);

    }


}
class helpPanel extends JPanel
{
    public void paint(Graphics g)
    {
        super.paint(g);
        Image helpbg = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/bg.jpg");
        g.drawImage(helpbg, 0, 0, 550, 500, this);
        Image say = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/say.png");
        g.drawImage(say, 0, 50, 500, 400, this);

    }
}
class beginPanel extends JPanel implements KeyListener
{
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //  //System.out.println("?");
            MyFrame m=new MyFrame();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            //System.out.println("!");
            RecordMenu R=new RecordMenu();
        }
        else if (e.getKeyCode() == KeyEvent.VK_H) {
            JFrame helpFrame=new JFrame();
            helpFrame.setLocation(400,300);
            helpFrame.setSize(550,500);
            helpFrame.setVisible(true);
            helpPanel p=new helpPanel();
            helpFrame.add(p);
        }
    }
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void paint(Graphics g)
    {
        super.paint(g);
        Image bg = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/bg.jpg");
        g.drawImage(bg, 0, 0, 1200, 1200, this);
        Image a = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/buttonStart.png");
        g.drawImage(a, 500, 400, 200, 100, this);
        Image b = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/buttonRecord.png");
        g.drawImage(b, 500, 500, 200, 100, this);
        Image c = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/logo.png");
        g.drawImage(c, 420, 100, 400, 200, this);
        Image d = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/help.png");
        g.drawImage(d, 500, 600, 200, 100, this);

    }

}
class RecordMenu implements ActionListener
{
    JFrame f=null;
    public RecordMenu()
    {
        f = new JFrame("RecordOptionPane");
        f.setSize(500,400);
        f.setLocation(300,300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container cp = f.getContentPane();
        cp.setLayout(new GridLayout(3,3));
        String s="record";
        JButton bt=null;
        for(int i=0;i<9;i++)
        {
            String text=s+String.valueOf(i+1)+".txt";
            bt=new JButton(text);
            bt.addActionListener(this);
            cp.add(bt);

        }

        //f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter()
        {
//            public void windowClosing(WindowEvent e)
//            {
//                System.exit(0);
//            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String filename="src/main/resources/Record/"+cmd;
        File file=new File(filename);
        if(file.exists())
        {
            RecordFrame re=new RecordFrame(filename);
        }
        else
        {
            JOptionPane.showMessageDialog(f, "FILE NOT EXIST","Message Dialog", JOptionPane.ERROR_MESSAGE);
        }


    }
}
class RecordFrame extends JFrame
{
    private RecordPanel replayScene;


    public RecordFrame(String filename)
    {
        replayScene=new RecordPanel(filename);
        this.add(replayScene);
        this.setSize(1200,1200);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Thread t=new Thread(replayScene);
        t.start();
    }


}
class RecordPanel extends JPanel implements Runnable
{
    private int[][]result=new int[12][12];
    private String filename;
    public RecordPanel(String name)
    {
        filename=name;
    }

    public void paint(Graphics g)
    {  //已经读好设置好了
        super.paint(g);
        Image bg = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/bg.jpg");
        g.drawImage(bg, 0, 0, 1200, 1200, this);
        for(int i=0;i<12;i++) {
            for (int j = 0; j < 12; j++)
            {
                if(result[i][j]>0)
                {
                    drawLiveCreature(result[i][j],i,j,g);
                }
                if(result[i][j]<0)
                {
                    int num=0-result[i][j];
                    int livenum=100-num%100;
                    int deadnum=num/100+1;
                    drawDeadCreture(deadnum,i,j,g);
                    drawLiveCreature(livenum,i,j,g);
                }
            }
        }
    }
    public void drawLiveCreature(int num,int i,int j,Graphics g)
    {
        Image im=null;
        if(num<=7)
        {
            String s="src/main/resources/Images/huluwa";
            String picname=s+String.valueOf(num)+".png";
            im=Toolkit.getDefaultToolkit().getImage(picname);
            g.drawImage(im, i * 100, j * 100, 80, 80, this);
        }
        if(num==8)
        {
            im=Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/minion.png");
            g.drawImage(im, i * 100, j * 100, 80, 80, this);
        }
    }
    public void drawDeadCreture(int num,int i,int j,Graphics g)
    {
        Image im=null;
        if(num<=7)
        {
            im=Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/huluwadead.png");
            g.drawImage(im, i * 100, j * 100, 80, 80, this);
        }
        if(num==8)
        {
            im=Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/minion_dead.png");
            g.drawImage(im, i * 100, j * 100, 80, 80, this);
        }
    }
    public void run()
    {
        try {
            File file = new File(filename);
            BufferedReader in = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String line;//一行数据  
                int row = 0;
                try {
                    while (row < 12) {
                        line = in.readLine();
                        if(line==null)
                        {
                            break;
                        }
                        ////System.out.println(line);
                        String[] temp = line.split("\t");
                        for (int j = 0; j < temp.length; j++) {
                            result[row][j] = Integer.parseInt(temp[j]);
                        }
                        row++;

                    }
                    // //System.out.println();
                    ////System.out.println();
                    this.repaint();
                } catch (IOException E) {
                    E.printStackTrace();
                    break;
                }
            }
        }catch(IOException E)
        {
            E.printStackTrace();
        }
    }
}
class MyFrame extends JFrame {
    private background mypanel;
    //JButton jb;
    public MyFrame()
    {
        //  //System.out.println("!");
        mypanel=new background();
        mypanel.addGoodCamp();
        mypanel.addBadCamp();

        this.add(mypanel);
        this.addKeyListener(mypanel);
        this.setSize(1500,1200);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Thread t=new Thread(mypanel);
        t.start();
    }


}

