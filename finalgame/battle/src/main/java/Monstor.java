public class Monstor extends Creature {
    Monstor(int power)
    {
        super();
        this.name=Name.MONSTOR;
        this.setDirection(Direction.LEFT);
        this.setNature(Nature.EVIL);
        this.setState(State.MOVE);
        this.power=power;
        this.picName="src/main/resources/Images/minion.png";
        this.setIm();
    }

    @Override
    public void report() {
        System.out.print("啰啰");
    }
}
