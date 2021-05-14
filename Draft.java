import java.io. *;
import java.util.Random;
import java.lang. *;


public class Draft{
	public static void main(String[] args){
		Random rand = new Random(System.currentTimeMillis());
		int N = 1 + rand.nextInt(32);

		for(int i = 0; i < N; i++){
			int arr[] = new int[5];
			arr[0] = detect(150 + rand.nextInt(100), 190, 220);
			arr[1] = detect(150 + rand.nextInt(120), 200, 250);
			arr[2] = detect(5 + rand.nextInt(30), 10, 20);
			arr[3] = detect(rand.nextInt(10), 2, 6);
			arr[4] = detect(rand.nextInt(10), 3, 7);
			
			int three = 0, two = 0, one = 0, zero = 0;

			for(int j = 0; j < arr.length; j++){
				System.out.println(arr[j]);
					switch(arr[j]){
						case 0:
							zero++;
							break;
						case 1:
							one++;
							break;
						case 2:
							two++;
							break;
						case 3:
							three++;
							break;
						default:
							break;
					}
				}


			if(three >= 3){
				if(arr[0] == 3 || arr[1] == 3){
					System.out.println("The " + (i + 1) + " candidate is a unicorn!");
					continue;
				}else{
					System.out.println("The " + (i + 1) + " candidate suits 1st category");
					continue;
				}
			}
			else if(three >= 2 && two >= 1 || two >= 3 && zero == 0){
				System.out.println("The " + (i + 1) + " candidate suits 1st category");
				continue;
			}
			else if(three >= 1 && two >= 1 || two >= 3){
				System.out.println("The " + (i + 1) + " candidate suits 2nd category");
				continue;
			}
			else{
				System.out.println("The " + (i + 1) + " candidate suits 3rd category");
				continue;
			}
		}
	}

	static int detect(int param, int a, int b){
		if(param < a)
			param = 0;
		else if(param >= a && param < (a + b) / 2)
			param = 1;
		else if(param >= (a + b) / 2 && param <= b)
			param = 2;
		else
			param = 3;

		return param;
	}
}