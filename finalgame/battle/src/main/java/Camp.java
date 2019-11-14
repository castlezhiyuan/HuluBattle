public class Camp {
    protected  Creature[] creature;
    protected int sum;
    public Creature addmember(int i)
    {
        return creature[i];
    }
    public synchronized void dec_sum()
    {
        sum=sum-1;
    }
    public int getSum()
    {
        return sum;
    }

}
