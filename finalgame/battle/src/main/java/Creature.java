import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

enum Name
{HULUWA_1,HULUWA_2,HULUWA_3,HULUWA_4,HULUWA_5,HULUWA_6,HULUWA_7,GRANDPA,SNAKE,SCORPTION,MONSTOR}
enum Direction
{
    UP,DOWN,RIGHT,LEFT,STILL
}
enum Nature
{
    GOOD,EVIL
}
enum State
{
    MOVE,BATTLE,DEAD,WAIT,PROTECT
}
//每一个生物有如下属性：
// 姓名，位置，行进速度，行进方向
public  class Creature implements Runnable{
    protected int x;
    protected int y;
    protected Name name;
    protected Position pos[][];
    protected  Camp camp;
    protected int power;
    protected Nature nature;
    protected Direction direction;
    protected State state;
    protected Creature enermy=null;
    protected String picName=null;
    protected Image im=null;
    // protected boolean ifBattleStop=false;

    //  public void setStop(back)

    public String getPicName(){
        return picName;
    }
    public void setIm()
    {

      //  picName = "src/main/resources/Images/boom.png";
        im=Toolkit.getDefaultToolkit().getImage(picName);
        //  return im;
    }
    public Image getIm()
    {
        return im;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }
    public int  getCampNum(){
        return this.camp.getSum();
    }

    public    void setState(State s)
    {
        state=s;
    }

    public State getState()
    {
        return state;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Nature getNature() {
        return nature;
    }

    public void setDirection(Direction d)
    {
        this.direction=d;
    }
    public Direction getDirection()
    {
        return this.direction;
    }
    public Name getName()
    {
        return name;
    }
    public void setallSpace(Position[][] p)
    {
        pos=p;
    }
    Creature() {

    }

    public  void leavePos()
    {
        pos[x][y].CreatureOut();
    }
    public   void changePos(int new_X,int new_Y)
    {
        x=new_X;
        y=new_Y;
        pos[x][y].setHolder(this);
    }
    public void setPos(int new_X,int new_Y) {
        x=new_X;
        y=new_Y;
    }

    public int getPower()
    {
        return this.power;
    }
    public void report(){}

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    @Override
    public void run() {

        while(x<=9&&x>=3&&this.getState()!=State.DEAD ) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.getState()==State.PROTECT)
            {
                break;
            }
            ifmeetOtherCamp();
            if (this.getState() == State.MOVE) {
                move();
            } else {
                long time = System.currentTimeMillis();
                // 使用while循环模拟 sleep
                while ((System.currentTimeMillis() - time )< 2000){}
                battle();
            }
            // this.leavePos();
            //this.changePos(x+1,y);
//               if(x==9)
//               {
//                   this.setDirection(Direction.LEFT);
//
//               }
//               if(x==3)
//               {
//                   this.setDirection(Direction.RIGHT);
//               }
        }
        if(this.getState()==State.MOVE)
        {
            this.setState(State.WAIT);
        }
//           while(this.getState()!=State.PROTECT) {
//           }
//           if(x<3)
//           {
//
//               //this.setState(State.WAIT);
//               this.leavePos();
//               this.changePos(9,y);
//
//           }
//           if(x>9)
//           {
//               this.leavePos();
//               this.changePos(3,y);
//           }

    }

    public void battle()
    {

        if(this.power>enermy.getPower())
        {
            setState(State.MOVE);
            // pos[enermy.getX()][enermy.getY()].setDeadpicture();
            enermy.setState(State.DEAD);
            enermy.leavePos();

        }
        else
        {
            //  pos[getX()][getY()].setDeadpicture();
            setState(State.DEAD);
            //  System.out.println(Thread.currentThread().toString());

            enermy.setState(State.MOVE);
            leavePos();
        }


    }
    public boolean ifmeetOtherCamp()
    {
        if(getNature()==Nature.GOOD)
        {
            if((!pos[this.getX()+1][this.getY()].occupy())&&pos[this.getX()+2][this.getY()].occupy()) {

                this.setState(State.BATTLE);
                pos[this.getX() + 2][this.getY()].getHolder().setState(State.BATTLE);
                this.enermy=pos[this.getX() + 2][this.getY()].getHolder();
                return true;
            }
            else if(pos[this.getX()+1][this.getY()].occupy()) {
                this.setState(State.BATTLE);
                pos[this.getX() + 1][this.getY()].getHolder().setState(State.BATTLE);
                this.enermy=pos[this.getX() + 1][this.getY()].getHolder();
                return true;
            }
            else
                return false;
        }
        else
        {
            if((!pos[this.getX()-1][this.getY()].occupy())&&pos[this.getX()-2][this.getY()].occupy()){

                this.setState(State.BATTLE);
                pos[this.getX() -2][this.getY()].getHolder().setState(State.BATTLE);
                this.enermy=pos[this.getX() - 2][this.getY()].getHolder();
                return true;
            }
            else if(pos[this.getX()-1][this.getY()].occupy()) {
                this.setState(State.BATTLE);
                pos[this.getX() - 1][this.getY()].getHolder().setState(State.BATTLE);
                this.enermy=pos[this.getX() -1][this.getY()].getHolder();
                return true;
            }
            else
                return false;

        }
    }
    public void move()
    {
        this.leavePos();
        if(this.getDirection()==Direction.RIGHT)
        {
            this.changePos(x+1,y);
        }
        else
        {
            this.changePos(x-1,y);

        }
    }
}