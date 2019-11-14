enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}
public class Huluwa extends Creature {

    private COLOR color;
    private SENIORITY seniority;

    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }



    Huluwa(COLOR color, Name name,SENIORITY seiority,int power,String picname) {
        this.name=name;
        this.color = color;
        this.seniority = seiority;
        this.setDirection(Direction.RIGHT);
        this.setNature(Nature.GOOD);
        this.setState(State.MOVE);
        this.power=power;
        this.picName=picname;
        System.out.println(this.picName);
        this.setIm();
    }


    public void report() {
        System.out.print(this.toString());
    }


    public String toString(){
        return this.seniority.toString();
    }




}
