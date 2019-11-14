
public class Grandpa extends Creature {

    Grandpa()
    {
        super();
        this.name=Name.GRANDPA;
        this.state=State.WAIT;
        this.setNature(Nature.GOOD);
        this.direction=Direction.STILL;
        this.picName="src/main/resources/Images/grandpa.png";
        this.power=20;
        this.setIm();
    }
    public void report() {
        System.out.print("老爷爷");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.getCampNum() <= 4)
                break;
        }

        while (true) {

        }
    }
}
