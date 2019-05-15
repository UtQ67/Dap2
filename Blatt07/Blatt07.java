import java.util.*;
import java.security.InvalidParameterException;

public class Blatt07
{

	public static final String INFO ="Es wird ein Parameter benötigt.";
	public static void main(String... args)
	{
		// Länge der zu vergleichenden Strings
		int n = 0;
		try{
			// Parameter einlesen
			if(args.length != 1)
				throw new InvalidParameterException();
			n = Integer.parseInt(args[0]);
		}catch(Exception e)
 		{
			System.out.println(INFO);
			System.exit(0);
		}

		// Strings generieren
		Random rand = new Random();
		String a = randStr(n, rand);
		String b = randStr(n, rand);

		System.out.println("generated Strings:\n");
		System.out.println(a);
		System.out.println(b + "\n");

		int[][] array = generateArray(a,b);

		System.out.println("Size of longest common sequence: " + array[n][n] + "\n");

		System.out.println("Longest common subsequence: ");
		
		System.out.println(generateOutput(array, a, b, n, n) + "\n");

	}

	// Vorgegeben in aufgabe
	public static String randStr(int n, Random r) {
    	String alphabet ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	StringBuilder res =new StringBuilder(n);
   	 	while (--n >= 0) {
        	res.append(alphabet.charAt(r.nextInt(alphabet.length())));
    	}
    	return res.toString();
	}

	// Implementierung des Paseudocode aus den Folien
	public static int[][] generateArray(String a, String b)
	{
		int n = a.length();

		char[] ar = a.toCharArray();
		char[] br = b.toCharArray();
		
		int[][] array = new int[n+1][n+1];
		for(int i = 1; i < n+1; i++)
		{
			for(int j = 1; j < n+1; j++)
			{
				if(ar[i-1] == br[j-1])
					array[i][j] = array[i-1][j-1]+1;
				else
				{
					if(array[i-1][j] >= array[i][j-1])
						array[i][j] = array[i-1][j];
					else
						array[i][j] = array[i][j-1];
				}
			}
		}

		return array;
	}

	// Rekursiv das array abarbeiten mit der Rekursionsgleichung die in der Vorlesung behandelt wurde
	public static String generateOutput(int[][] array, String a, String b, int i, int j)
	{
		int n = a.length();

		char[] ar = a.toCharArray();
		char[] br = b.toCharArray();

		// Anfang vom array => keine Zeichen werden verglichen
		if(i == 0 || j == 0)
			return "";
		if(array[i][j]== array[i][j-1])
			return generateOutput(array, a, b, i, j-1);
		// Zeichen wurde gefunden
		if(ar[i-1] == br[j-1])
			return generateOutput(array, a, b, i-1, j-1) + ar[i-1];
		if(array[i-1][j] > array[i][j-1])
			return generateOutput(array, a, b, i-1, j);
		
		return generateOutput(array, a, b, i, j-1);
	
	}
}
