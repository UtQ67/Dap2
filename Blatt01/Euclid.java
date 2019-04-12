class Euclid{
    public static void main(String[] args)
    {
        System.out.println(euclid(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
    }

    public static int euclid(int a, int b)
    {
        if (b==0)
            return a;
        return euclid(b, a%b);
    }
}