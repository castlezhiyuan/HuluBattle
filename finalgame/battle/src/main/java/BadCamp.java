public class BadCamp extends Camp{
    private Snake leader;
    public BadCamp()
    {
        this.sum=7;
        creature=new Monstor[7];
        for(int i=0;i<7;i++) {
            creature[i] = new Monstor(2 * i + 1);
        }
        leader=new Snake();
    }
    public void addbattle(background b)
    {
        Position m=null;
        m=b.getPosition(11,4);
        leader.setallSpace(b.getallSpace());
        m.setHolder(leader);
        leader.setPos(10,4);

        int[] order = new int[7];
        int num = (int) (Math.random() * 7);
        int size = 1;
        order[0] = num;
        while (true) {
            num = (int) (Math.random() * 7);
            boolean isHave = false;
            for (int j = 0; j < size; j++) {
                if (num == order[j]) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                order[size] = num;
                size++;
            }
            if (size == 7) {
                break;
            }
        }
        m=b.getPosition(6,5);
        creature[order[0]].setallSpace(b.getallSpace());
        m.setHolder(creature[order[0]]);
        creature[order[0]].setPos(6,5);

        m=b.getPosition(7,4);
        creature[order[1]].setallSpace(b.getallSpace());
        m.setHolder(creature[order[1]]);
        creature[order[1]].setPos(7,4);

        m=b.getPosition(8,3);
        creature[order[2]].setallSpace(b.getallSpace());
        m.setHolder(creature[order[2]]);
        creature[order[2]].setPos(8,3);

        m=b.getPosition(9,2);
        creature[order[3]].setallSpace(b.getallSpace());
        m.setHolder(creature[order[3]]);
        creature[order[3]].setPos(9,2);

        m=b.getPosition(7,6);
        creature[order[4]].setallSpace(b.getallSpace());
        m.setHolder(creature[order[4]]);
        creature[order[4]].setPos(7,6);

        m=b.getPosition(8,7);
        creature[order[5]].setallSpace(b.getallSpace());
        m.setHolder(creature[order[5]]);
        creature[order[5]].setPos(8,7);

        m=b.getPosition(9,8);
        creature[order[6]].setallSpace(b.getallSpace());
        m.setHolder(creature[order[6]]);
        creature[order[6]].setPos(9,8);
    }
    public Snake addSnake()
    {
        return leader;
    }

}
