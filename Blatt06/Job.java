class Job implements Comparable<Job>
{
    private int length;
    private int deadline;
    public Job(int length, int deadline)
    {
        this.length = length;
        this.deadline = deadline;
    }

    public int getLength()
    {
        return length;
    }
    public int getDeadline()
    {
        return deadline;
    }
    
    public String toString()
    {
        return "[" + length + ", " + deadline + "]";
    }

    public int compareTo(Job other)
    {
        return getDeadline() - other.getDeadline();
    }
}