class Interval implements Comparable<Interval>
{
    private int start;
    private int end;

    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
    }


    public int getStart()
    {
        return start;
    }
    public int getEnd()
    {
        return end;
    }

    public String toString()
    {
        return "[" + start + ", " + end + "]";
    }

    public int compareTo(Interval other)
    {
        return getEnd() - other.getEnd();
    }
}