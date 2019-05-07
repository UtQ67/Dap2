import java.security.InvalidParameterException;

public class ATM
{
    public static final String INFO_STRING = "Es werden 2 Parameter benoetigt.\n"+
                                                "\t1. Parameter: Welche Art der Währung verwendet werden sol (Euro, Alternativ)\n"+
                                                "\t2. Parameter: Welcher Wert gewechselt werden soll";
    
    public static void main(String[] args) {

        // Parameter die eingelesen werden müssen
        int[] w = null; // waehrung
        int b = 0;      // Wert der gewechselt werden soll

        try{
            // Man benötigt genau 2 Parameter
            if(args.length != 2)
                throw new InvalidParameterException();

            // Einlesen von dem Erstem Parameter (mögliche eingaben: "Wert", "Alternative")
            if(args[0].equals("Euro"))
                w = new int[]{200,100,50,20,10,5,2,1};
            else if(args[0].equals("Alternative"))
                w = new int[]{200,100,50,20,10,5,4,2,1};
            else
                throw new InvalidParameterException();
                
            // EInlesen vom 2. Parameter (Positiver integer wert)
            b = Integer.parseInt(args[1]);
            if(b < 0)
                throw new InvalidParameterException();
        }catch(Exception e)
        {
            System.out.println(INFO_STRING);
            System.exit(1);
        }


        int[] exchange = change(b, w);

        // Output the exchange
        System.out.print("{");
        for(int i = 0; i < exchange.length; i++)
        {
            if(i != 0)
                System.out.print(", ");
            System.out.print(exchange[i]);
        }
        System.out.println("}");
    }

    // Greedy-Algorithmus zum wechseln des Geldes
    // Der Algorithmus ist optimal
    public static int[] change(int b, int[] w)
    {
        int[] exchange = new int[w.length];
     
        for(int i = 0; i < w.length; i++)
        {
            while(w[i] <= b)
            {
                exchange[i]++;
                b-=w[i];
            }
        }

        return exchange;

    }
}