import java.util.Scanner;

public class Printer {
	public static void print_even(int N)
	{
		for(int i = 0; i <= N; i = i+2)
		{
			System.out.println(i);
		}
	}
	public static void main(String[] args)
	{
		System.out.println("Podaj N: ");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		print_even(n);
		
		scan.close();
	}
}
