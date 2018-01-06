import javax.print.attribute.standard.MediaSize;

public class GoodCamp extends Camp {
    private Grandpa leader;
    GoodCamp()
    {
        this.sum=7;
        System.out.print("!");
        creature=new Huluwa[7];
        creature[0]=new Huluwa(COLOR.赤,Name.HULUWA_1,SENIORITY.一,14,"src/main/resources/Images/huluwa1.png");
        //creature[0].setPos(new Position());
        creature[1]=new Huluwa(COLOR.橙,Name.HULUWA_2,SENIORITY.二,12,"src/main/resources/Images/huluwa2.png");
        creature[2]=new Huluwa(COLOR.黄, Name.HULUWA_3,SENIORITY.三,10,"src/main/resources/Images/huluwa3.png");
        creature[3]=new Huluwa(COLOR.绿,Name.HULUWA_4,SENIORITY.四,8,"src/main/resources/Images/huluwa4.png");
        creature[4]=new Huluwa(COLOR.青,Name.HULUWA_5,SENIORITY.五,6,"src/main/resources/Images/huluwa5.png");
        creature[5]=new Huluwa(COLOR.蓝,Name.HULUWA_6,SENIORITY.六,4,"src/main/resources/Images/huluwa6.png");
        creature[6]=new Huluwa(COLOR.紫,Name.HULUWA_7,SENIORITY.七,2,"src/main/resources/Images/huluwa7.png");
//        creature[7]=new Grandpa();
        leader=new Grandpa();
        leader.setCamp(this);
        for(int i=0;i<7;i++) {
            creature[i].setCamp(this);
            //  leader.setProtectors(creature[i]);
        }


    }
    public Grandpa addGrandpa()
    {
        return leader;
    }
    public void addbattle(background b) {
        Position m = null;
        for (int i = 0; i < 7; i++) {
            m = b.getPosition(3, 2+i);
            creature[i].setallSpace(b.getallSpace());
            creature[i].setPos(3,2+i);
            m.setHolder(creature[i]);
        }
        m = b.getPosition(1,4);
        leader.setallSpace(b.getallSpace());
        leader.setPos(1,4);
        m.setHolder(leader);
    }


}
