import java.io. *;
import java.util.*;
import java.lang. *;

public class Search_2{
	public static void main(String[] args){
		hashTable table1 = new hashTable(300000);
		hashTable  table2 = new hashTable(300000);
		Random rand = new Random(System.currentTimeMillis());
		
		final int ARRSIZE = 100000;

		ArrayList<Integer> array = new ArrayList<Integer>();

		Tree tree = null;
		//что за l ? непонел
		ArrayList<Integer> l = new ArrayList<>(9);
		//заполняю массив элементами
		for (int i = 0; i < ARRSIZE; i++){
			int number = rand.nextInt(10 * ARRSIZE);

			array.add(number);
			if(tree == null){
				tree = new Tree(number);
			}
			tree.add(number);
		}
		//сортирую массив
		Collections.sort(array);

		System.out.println("\nInsertion\n");

		for(int i = 0; i < 4; i++){
			long start = System.currentTimeMillis();
			switch(i){
				case 0:
					for(int j = 0; j < ARRSIZE; j++){
						int num = rand.nextInt(table1.size()*10);
						table1.insert_simple(num);
					}
					System.out.println("Simple hash: " + (System.currentTimeMillis() - start));
					break;
				case 1:
					for(int j = 0; j < ARRSIZE; j++){
						int num = rand.nextInt(table2.size()*10);
						table2.insert_pseudo(num);
					}
					System.out.println("Pseudo hash: " + (System.currentTimeMillis() - start));
					break;
				case 2:
					for(int j = 0; j < ARRSIZE; j++){
						int num = rand.nextInt(table1.size()*10);
						table1.insert_chain(num);
					}
					System.out.println("Chain hash: " + (System.currentTimeMillis() - start));
					break;
				case 3:
					for (int j = 0; j < ARRSIZE; j++){
						int number = rand.nextInt(10 * ARRSIZE);
						if(tree == null){
							tree = new Tree(number);
						}
						tree.add(number);
					}
					System.out.println("Binary " + (System.currentTimeMillis() - start));
					break;
				default:
					break;
				}
		}

		System.out.println("\nSearch\n");

		for(int i = 0; i < 4; i++){
			int num = rand.nextInt(100);
			long start = System.currentTimeMillis();
			switch(i){
				case 0:
					for(int j = 0; j < ARRSIZE; j++){
						table1.search_simple(num);
					}
					System.out.println("Simple hash: " + (System.currentTimeMillis() - start));
					break;
				case 1:
					for(int j = 0; j < ARRSIZE; j++){
						table2.search_pseudo(num);
					}
					System.out.println("Pseudo hash: " + (System.currentTimeMillis() - start));
					break;
				case 2:
					for(int j = 0; j < ARRSIZE; j++){
						table1.search_chain(num);
					}
					System.out.println("Chain hash: " + (System.currentTimeMillis() - start));
					break;
				case 3:
					break;
				default:
					break;
				}
		}
		double start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			tree.B_search(i);
		}
		double end = System.currentTimeMillis();
		System.out.println("\nBinary tree search: " + (end - start));

		start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			binary_search(array, i, 0, array.size() - 1);
		}
		end = System.currentTimeMillis();
		System.out.println("Binary search: " + (end - start));

		start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			interpolation_search(array, i);
		}
		end = System.currentTimeMillis();
		System.out.println("Interpolation search: " + (end - start));

		start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			fibonacci_search(array, i);
		}
		end = System.currentTimeMillis();
		System.out.println("Fibonacci search: " + (end - start));



		System.out.println("\nRemoving\n");

		for(int i = 0; i < 4; i++){
			int num = rand.nextInt(100);
			start = System.currentTimeMillis();
			switch(i){
				case 0:
					for(int j = 0; j < ARRSIZE; j++){
						table1.delete_simple(num);
					}
					System.out.println("Simple hash: " + (System.currentTimeMillis() - start));
					break;
				case 1:
					for(int j = 0; j < ARRSIZE; j++){
						table2.delete_pseudo(num);
					}
					System.out.println("Pseudo hash: " + (System.currentTimeMillis() - start));
					break;
				case 2:
					for(int j = 0; j < ARRSIZE; j++){
						table1.delete_chain(num);
					}
					System.out.println("Chain hash: " + (System.currentTimeMillis() - start));
					break;
				case 3:
					for(int j = 0; j < ARRSIZE; j++){
						tree.remove(num);
					}
					System.out.println("Binary tree: " + (System.currentTimeMillis() - start));
					break;
				default:
					break;
				}
		}
	}

	static int binary_search(ArrayList<Integer> array, int num, int left, int right){

		if(left > right){
			return -1;
		}

		int key = (right + left) / 2;

		if(num == array.get(key)){
			return key;
		}
		else if(num < array.get(key)){
			key = binary_search(array, num, left, key - 1);
		}
		else if(num > array.get(key)){
			key = binary_search(array, num, key + 1, right);
		}

		return key;
	}

	static int fibonacci_search(ArrayList<Integer> array, int num){
		int k = 1, M, i, p = 1, q = 1;
		while(array.size() + 1 >  p){
			p += q;
			q = p - q;
			k++;
		}

		M = F(k + 1) - array.size() - 1;
		i = F(k) - M;
		p = F(k - 1);
		q = F(k - 2);

		while(true){
			if(i < 0){
				if(q == 0)	return -1;
					i -= q;
					q = swap(p - q, p = q);
			}
			else if(i >= array.size()){
				if(p == 1)	return -1;
				i += q;
				p -= q;
				q -= p;
			}
			else{
				if(num < array.get(i)){
					if(q == 0)	return -1;
					i -= q;
					q = swap(p - q, p = q);
				}
				else if(num > array.get(i)){
					if(p == 1)	return -1;
					i += q;
					p -= q;
					q -= p;
				}
				else{
					return i;
				
				}
			}
		}
		
	}
	static int F(int a){
		if(a <= 1)
			return a;
		
		return F(a - 1) + F(a - 2);
	}

	static int interpolation_search(ArrayList<Integer> array, int num){
		int mid, left = 0, right = array.size() - 1;

		while(array.get(left) <= num && array.get(right) >= num){
			mid = left + ((num - array.get(left)) * (right - left)) / (array.get(right) - array.get(left));
			if (array.get(mid) < num)	left = mid + 1;
			else if (array.get(mid) > num)	right = mid - 1;
			else return mid;
		}

		if((array.get(left) == num))	return left;
		return -1;
	}

	static int swap(int a, int b){
		return a;
	}
}