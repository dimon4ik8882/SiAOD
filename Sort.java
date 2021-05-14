import java.io. *;
import java.util.*;
import java.lang. *;

public class Sort{
	public static void main(String[] args){
		for(int i = 1; i <= 6; i++){

			System.out.println("" + func(i) + " sec");
		}
	}

	static double func(int n){
		int[][] matrix = new int[1000][1000];
		Random rand = new Random(System.currentTimeMillis());

		//генерируем матрицу
		for(int i = 0; i < matrix.length; i++){
				for(int j = 0; j < matrix[0].length; j++){
					matrix[i][j] = rand.nextInt(100);
				}
		}

		double start = System.currentTimeMillis();
		//сортируем
		switch(n){
				case 1:
					System.out.print("bubble: \n");
					matrix = bubble(matrix);
					break;
				case 2:
					System.out.print("selection: \n");
					matrix = selection(matrix);
					break;
				case 3:
					System.out.print("insertion: \n");
					matrix = insertion(matrix);
					break;
				case 4:
					System.out.print("shell: \n");
					matrix = shell(matrix);
					break;
				case 5:
					System.out.print("quick: \n");
					qsort(matrix);
					break;
				case 6:
					System.out.print("Standard: \n");
					for(int i = 0; i < matrix.length; i++){
						Arrays.sort(matrix[i]);
					}
					break;
				default:
					break;
			}
			double end = System.currentTimeMillis() - start;

		//преводим милисекунды в секунды и возвращаем значение	
		return end / 1000;
	}

	static int[][] bubble(int matrix[][]){
		int key;

			for(int i = 0; i < matrix.length; i++){
				for(int j = 0; j < matrix[0].length - 1; j++){
					for(int k = j + 1; k < matrix[0].length; k++){
						if(matrix[i][j] > matrix[i][k]){

							matrix[i][k] = swap(matrix[i][j], matrix[i][j] = matrix[i][k]);
						}
					}
				}
			}

		return matrix;
	}

	static int[][] selection(int matrix[][]){
		int key;

		for(int i = 0; i < matrix.length; i++){

			for(int j = 0; j < matrix[0].length; j++){
				int n = j;

				for(int k = j + 1; k < matrix[0].length; k++){
					if(matrix[i][k] < matrix[i][n]){
						n = k;
					}
				}
				matrix[i][n] = swap(matrix[i][j], matrix[i][j] = matrix[i][n]);

			}
		}

		return matrix;
	}

	static int[][] insertion(int matrix[][]){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 1; j < matrix.length; j++){
				int k = j;
				int n = matrix[i][j];
				while(k > 0 && matrix[i][k - 1] > n){
					matrix[i][k] = matrix[i][k - 1];
					k--;
				}
				matrix[i][k] = n;
			}
		}

		return matrix;
	}

	static int[][] shell(int matrix[][]){

		for(int i = 0; i < matrix.length; i++){
			int key = matrix.length / 2;

			while (key >= 1){
				for(int j = key; j < matrix.length; j++){
					int k = j;
					int n = matrix[i][j];
					while(k > 0 && matrix[i][k - 1] > n){
						matrix[i][k] = matrix[i][k - 1];
						k--;
					}
					matrix[i][k] = n;
				}
				key = key / 2;
			}
		}

		return matrix;
	}

	static void quick(int array[], int left, int right){
		int i = left, j = right;
		int key = (left + right) / 2;

		while(i <= j){
			while(array[i] < array[key]){
				i++;
			}
			while(array[j] > array[key]){
				j--;
			}

			if(i <= j){
			array[j] = swap(array[i], array[i = j]);
			i++;
			j--;
			}			
		}

		if(left < j){
			quick(array, left, j);
		}
		if(right > i){
			quick(array, i, right);
		}
	}

	static void qsort(int matrix[][]){
		for(int i = 0; i < matrix.length; i++){
			quick(matrix[i], 0, matrix[i].length - 1);
		}
	}

	static int swap(int b, int a){
		return b;
	}
}