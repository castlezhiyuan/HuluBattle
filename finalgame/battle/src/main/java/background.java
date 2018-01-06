import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import  java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Timer;
import java.util.TimerTask;

enum Situation
{
    START,FINAL,END
}
public class background extends JPanel{
    private ExecutorService exec;
    private Position pos[][];
    private static final int N = 12;
    private GoodCamp goodcamp = null;
    private BadCamp badcamp = null;
    private boolean quit = false;
    private Situation situation=Situation.START;
    private boolean flag=true;
    private HashMap<Name, Integer> map = new HashMap<Name,Integer>();

    {
        map.put(Name.HULUWA_1, new Integer(1));
        map.put(Name.HULUWA_2, new Integer(2));
        map.put(Name.HULUWA_3, new Integer(3));
        map.put(Name.HULUWA_4, new Integer(4));
        map.put(Name.HULUWA_5, new Integer(5));
        map.put(Name.HULUWA_6, new Integer(6));
        map.put(Name.HULUWA_7, new Integer(7));
        map.put(Name.MONSTOR, new Integer(8));
    }

    private List<String> list = null;
    private Vector<Creature> member = new Vector<Creature>();

    public Position[][] getallSpace() {
        return pos;
    }

    public background() {
        pos = new Position[12][12];
        int i, j;
        for (i = 0; i < 12; i++)
            for (j = 0; j < 12; j++) {
                pos[i][j] = new Position(i, j);
            }
        goodcamp = new GoodCamp();
        badcamp = new BadCamp();
    }

    public static List<String> getFileList(File file) {
        List<String> result = new ArrayList<String>();
        if (!file.isDirectory()) {
            //  System.out.println(file.getAbsolutePath());
            result.add(file.getAbsolutePath());
        } else {
            File[] directoryList = file.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().indexOf("txt") > -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            for (int i = 0; i < directoryList.length; i++) {
                result.add(directoryList[i].getPath());
            }
        }
        return result;
    }

    public String setfilename() {
        String filename = null;
        try {
            File directory = new File("");//参数为空 
            String File_in = directory.getCanonicalPath() + "\\src\\main\\resources\\Record";
            // System.out.println(File_in);
            // String File_in=Thread.currentThread().getContextClassLoader().getResource("");
            ;
            File f = new File(File_in);
            list = new ArrayList<String>();
            list = getFileList(f);

            String a = String.valueOf(list.size() + 1);
            filename = File_in + "\\record" + a + ".txt";
            list.add(filename);
            return filename;
        } catch (IOException E) {
            E.printStackTrace();
        }
        return filename;

    }

    public void record() {
        String s = setfilename();
        // System.out.println(s);
        Timer timer = new Timer();
        int[][] count = new int[12][12];
        timer.schedule(new recordtask(count, s), 0, 1000);
    }
    class recordtask extends TimerTask {
        private int[][] count;
        private String filename;

        public recordtask(int[][] count, String name) {
            this.count = count;
            this.filename = name;
        }

        public void run() {


            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 12; j++)
                    count[i][j] = 0;
            }
            for (Creature c : background.this.member) {
                Integer i = map.get(c.getName());
                // System.out.println(i);
                int x = c.getX();
                int y = c.getY();
                if (c.getState() == State.DEAD) {
                    count[x][y] -= 100 * i;
                } else {
                    count[x][y] += i;
                }

            }
            try {
                File file = new File(filename);
                FileWriter out = new FileWriter(file, true);
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 12; j++) {
                        //System.out.print(count[i][j]);
                        out.write(count[i][j] + "\t");
                        out.flush();
                    }
                    // System.out.println();
                    out.write("\r\n");
                    out.flush();

                }
                // System.out.println();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public Position getPosition(int x, int y) {
        return pos[x][y];
    }

    public void paint(Graphics g) {
        super.paint(g);
        Image bg = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/bg.jpg");
        g.drawImage(bg, 0, 0, 1500, 1200, this);

        if (situation==Situation.FINAL) {
            Image finalbg = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/finalBattleLogo.png");
            g.drawImage(finalbg, 400, 50, 200, 100, this);
            Image vs = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/vs.png");
            g.drawImage(vs, 700, 60, 200, 100, this);
            Image Hp1 = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/huluHp.png");
            g.drawImage(Hp1, 700, 20, 200, 100, this);
            Image Hp2 = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/minionHp.png");
            g.drawImage(Hp2, 700, 100, 200, 100, this);
        }
        if(situation==Situation.END)
        {
            Image end = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/gameover.png");
            g.drawImage(end, 400, 50, 200, 100, this);
            if(flag==true)
            {
                Image winner = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/huluwin.png");
                g.drawImage(winner, 600, 20, 400, 200, this);
            }
            else
            {
                Image winner = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/minionwin.png");
                g.drawImage(winner, 600, 20, 400, 200, this);
            }
        }
        int i, j;
        for (Creature c : member) {
            if (c.getState() == State.DEAD) {
                drawDead(c, g, c.getX(), c.getY());
            }
        }
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                drawPos(pos[i][j], i, j, g);


            }
        }
    }

    public void drawDead(Creature c, Graphics g, int i, int j) {
        if (c.getClass().equals(Huluwa.class)) {
            Image im = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/huluwadead.png");
            g.drawImage(im, i * 100, j * 100, 80, 80, this);
        } else {
            Image im = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/minion_dead.png");
            g.drawImage(im, i * 100, j * 100, 80, 80, this);
        }
    }

    public void drawPos(Position pos, int i, int j, Graphics g) {
        Image im = null;

        if (pos.occupy()) {
            if (pos.getHolder().getState() == State.BATTLE) {
                im = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/boom.png");
                g.drawImage(im, i * 100 + 50, j * 100, 80, 80, this);
                g.drawImage(im, i * 100 - 50, j * 100, 80, 80, this);
            }
            g.drawImage(pos.getHolder().getIm(), i * 100 + 50, j * 100, 80, 80, this);
        }

    }

    public void addGoodCamp() {
        goodcamp.addbattle(this);
        for (int i = 0; i < 7; i++) {
            //        System.out.println(goodcamp.addmember(i).getX());
            member.addElement(goodcamp.addmember(i));
        }
    }

    public void addBadCamp() {
        badcamp.addbattle(this);
        for (int i = 0; i < 7; i++) {
            //   System.out.println(goodcamp.addmember(i).getX());
            member.addElement(badcamp.addmember(i));
        }
    }

    public boolean setStop() {
        for (int i = 0; i < 7; i++) {
            for (Creature c : member) {
                if (c.getState() != State.DEAD && c.getState() != State.WAIT) {


                    return false;
                }
            }
        }

        return true;
    }

    public void startbattle() {
        record();
        exec = Executors.newCachedThreadPool();

        for (Creature e : member) {
            exec.execute(e);
        }

        exec.execute(goodcamp.addGrandpa());
        exec.execute(badcamp.addSnake());


    }

    // void refresh()
    @Override
    public void run() {
        startbattle();

        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.setStop())
            {
                situation=Situation.FINAL;
                this.repaint();
                break;
            }
            else {
                this.repaint();
            }
        }
        try {

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int goodPower = 20;
        int badPower = 25;
        for (Creature c : member) {
            if (c.getState() != State.DEAD) {
                if (c.getNature() == Nature.GOOD) {
                    goodPower += c.getPower();
                    c.leavePos();
                    c.changePos(3, c.getY());
                } else {
                    badPower += c.getPower();
                    c.leavePos();
                    c.changePos(9, c.getY());
                }
            }
        }
        if(goodPower<badPower)
        {
            flag=false;
        }
        //  System.out.println(goodPower);
        // System.out.println(badPower);

        for (Creature c : member) {
            if (c.getState() != State.DEAD) {
                if (c.getNature() == Nature.GOOD && goodPower < badPower) {
                    c.setState(State.DEAD);
                    c.leavePos();
                    // System.out.println("!" + c.getY());
                }
                if (c.getNature() == Nature.EVIL && goodPower > badPower) {
                    c.setState(State.DEAD);
                    c.leavePos();
                    // System.out.println("!" + c.getY());
                }
            }
        }
        situation=Situation.END;
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }

    }


}




