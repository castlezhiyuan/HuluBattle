public class Snake extends Creature {
    Snake()
    {
        super();
        this.name=Name.SNAKE;
        this.setDirection(Direction.STILL);
        this.setNature(Nature.EVIL);
        this.setState(State.WAIT);
        this.power=20;
        this.picName="src/main/resources/Images/snake.png";
        this.setIm();

    }

    @Override
    public void report() {
        System.out.print("蛇精");
    }

    public void run() {
    }

}
