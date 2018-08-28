public class Test {
	public static void main(String[] arge) {
		int[][] arr = new int[10][];
		for(int i = 0; i < 10; i++) {
			arr[i] = new int[i+1];

			for(int j = 0; j <= i; j++) {
				if(j == 0) {//头是一
					arr[i][j] = 1;
				}else if(j == i) {//尾也是一
					arr[i][j] = 1;
				} else {
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j];		
				}
			}
		}


		for(int i = 0; i < arr.length; i++) {
			for(int z = i+1; z < 10; z++) {
				System.out.print(" ");
			}

			for(int j = 0; j <= i; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}