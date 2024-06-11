import java.util.Scanner;

public class Triangle {
	public static void print_triangle(int N) {
		int numberOfColumns;
		
		if(N % 2 == 0) {
			numberOfColumns = 2*N;
		} else {
			numberOfColumns = 2*N+2;
		}
		
		int[][] tab = new int[N][numberOfColumns];
		tab[0][numberOfColumns/2-1] = 1;
		
		for(int row = 1; row < N; row++) {
			for(int col = 1; col < numberOfColumns-1; col++) {
				tab[row][col] = tab[row-1][col-1] + tab[row-1][col+1];
			}
		}
		
		tab[N-1][0] = tab[N-2][1];
		tab[N-1][numberOfColumns-1] = tab[N-2][numberOfColumns-2];
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < numberOfColumns-1; col++) {
				System.out.print(tab[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Wprowadz N: ");
		int N = scan.nextInt();
		
		print_triangle(N);
	}
}