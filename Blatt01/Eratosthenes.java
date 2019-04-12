class Eratosthenes {
    public static void main(String[] args) {
        boolean[] isPrim = eratosthenes(Integer.parseInt(args[0]));
        
        if(args.length > 1)
        {
            for(int i = 0; i < isPrim.length; i++)
            {
                if(isPrim[i])
                    System.out.println(i);
            }
        }
    }
    public static boolean[] eratosthenes(int length)
    {
        boolean[] isPrim = new boolean[length];
        for(int i = 2; i < isPrim.length; i++)
        {
            isPrim[i] = true;
        }
        for(int i = 2; i < isPrim.length; i++)
        {
            if(!isPrim[i])
                continue;
            for(int j = i+i; j < isPrim.length; j+=i)
            {
                isPrim[j] = false;
            }
        }

        return isPrim;
    }
}