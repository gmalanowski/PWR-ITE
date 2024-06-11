import java.util.Scanner;

public class Fib {
	public static int fibbI(int n)
	{
		int x = 0;
		int y = 1;
		if(n == 0) return x;
		else if(n == 1) return y;
		else
		{
			int result = 0;
			for(int i = 2; i <= n; i++)
			{
				result = x + y;
				x = y;
				y = result;
			}
			return result;
		}
	}
	
	public static int fibbR(int n)
	{
		if(n == 0)
		{
			return 0;
		}
		else if(n == 1)
		{
			return 1;
		}
		else
		{
			return fibbR(n-1) + fibbR(n-2);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj, n-ty wyraz do policzenia: ");
		int n = scan.nextInt();
		
		System.out.println(fibbI(n));
		System.out.println(fibbR(n));
		
		scan.close();
	}
}