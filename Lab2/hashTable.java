import java.io. *;
import java.util.Random;
import java.util.LinkedList;
import java.lang. *;

public class hashTable{
	private Integer[] table;
	private LinkedList<Integer>[] tbl;
	private int size;

	public hashTable(int size){
		this.size = size;
		table = new Integer[size];
		tbl = new LinkedList[size];
	}

	private int hash(int key){
		return key % size;
	}

	private int simple_rehash(int hash){
		return (hash + 1) % size;
	}
	
	private int pseudo_rehash(int hash, Random rand){
		return (hash + Math.abs(rand.nextInt(size))) % size;
	}

	public int size(){
		return size;
	}

	public Integer get(int i){
		return table[i];
	}

	public LinkedList<Integer> take(int i){
		return tbl[i];
	}

	public void insert_chain(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		if(tbl[hash] == null){
			tbl[hash] = new LinkedList<Integer>();
		}
		tbl[hash].add(item);
	}

	public void insert_simple(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		while(table[hash] != null){
			hash = simple_rehash(hash);
		}
		table[hash] = item;
	}

	public void insert_pseudo(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		while(table[hash] != null){
			hash = pseudo_rehash(hash, rand);
		}
		table[hash] = item;
	}

	public boolean search_pseudo(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		while(table[hash] != null && table[hash] != key){
			hash = pseudo_rehash(hash, rand);
		}
		if(table[hash] == null){
			return false;
		}
		return true;
	}

	public boolean search_simple(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		while(table[hash] != null && table[hash] != key){
			hash = simple_rehash(hash);
		}
		if(table[hash] == null){
			return false;
		}
		return true;
	}

	public boolean search_chain(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		if(tbl[hash] == null){
			return false;
		}
		else{
			if(tbl[hash].contains(key)){
				return true;
			}else{
				return false;
			}
		}
	}

	public void delete_chain(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		if(tbl[hash] != null){
			if(tbl[hash].contains(key)){
				tbl[hash].remove(key);
			}
		}
	}

	public void delete_simple(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		while(table[hash] != null && table[hash] != key){
			hash = simple_rehash(hash);
		}
		table[hash] = null;
	}

	public void delete_pseudo(int key){
		Integer item = new Integer(key);
		int hash = hash(key);
		Random rand = new Random(hash);

		while(table[hash] != null && table[hash] != key){
			hash = pseudo_rehash(hash, rand);
		}
		table[hash] = null;
	}
}