package util;
/**
 *  ���������е�kС�������������ʱ�临�Ӷ�
 */
public class FindKth {
	
	public static int findKth(int[] arr,int k) {
		
		//�������������
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
		//˼·����ֱ�Ӵ������������������
		//���Ǳ������飬���a���ǵڼ���С��������Ȼ��ÿ�ζ���k�Ƚϣ�����Ǿͷ���
		int a = 1;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]%2 == 1) {  //˵��������
				if(a == k) {   //�����aС��������Ҫ��k����ô�Ϳ��Է�����
					return arr[i];
				}
				a++;
			}
		}
		//���ؽ��
		return 0;
	}
	
	//���Է���
	public static void main(String[] args) {
		int[] arr = {11,9,5,13,7,3,6,1,16}; //����һ������
		System.out.println(findKth(arr, 4));
	}
}
