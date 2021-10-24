package util;
/**
 *  查找数组中第k小的奇数并计算出时间复杂度
 */
public class FindKth {
	
	public static int findKth(int[] arr,int k) {
		
		//对数组进行排序
		if(arr != null) {
			for(int i = 0; i < arr.length - 1; i++) {              //   1 + 2 * arr.lenth + arr.length * [ 1 + 2 * (arr.length - i) +  (arr.length - i) *  )
				for(int j = 0; j < arr.length - 1 - i; j++) {
					if(arr[j] > arr[j+1]) {
						int temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = temp;
					}
				}
			}
		}
		//思路就是直接从排序的数组中找奇数
		//我们遍历数组，这个a就是第几个小的奇数，然后每次都与k比较，如果是就返回
		int a = 1;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]%2 == 1) {  //说明是奇数
				if(a == k) {   //如果第a小的数是想要的k，那么就可以返回了
					return arr[i];
				}
				a++;
			}
		}
		//返回结果
		return 0;
	}
	
	//测试方法
	public static void main(String[] args) {
		int[] arr = {11,9,5,13,7,3,6,1,16}; //定义一个数组
		System.out.println(findKth(arr, 4));
	}
}
