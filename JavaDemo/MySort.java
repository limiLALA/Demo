import java.util.*;


public class MySort{
/* 测试案例
20
4 6 7 9 33 2 5 0 7 1234 4 76 7 9 34 2 5 0 7 56
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++)	arr[i] = sc.nextInt();
		// bucketSort(arr, 0, n - 1, (int)Math.sqrt(n));
		radixSort(arr, 0, n - 1);
		for(int i : arr)	System.out.print(i + " ");
	}

	/*冒泡排序
		时间复杂度O(n*n)
		空间复杂度O(1)
	*/
	static void bubbleSort(int[] arr, int l, int r){
		for(int i = r; i > l; i--)
			for(int j = l; j < i; j++)
				if(arr[j] > arr[j + 1])
					swap(arr, j, j + 1);
	}

	/*快速排序
		时间复杂度O(nlog(n))
		空间复杂度O(nlog(n))：最优O(logn)，最差O(n)
	*/
	static void quickSort(int[] arr, int l, int r){
		if(l >= r)    return;
        int x = arr[(l + r) / 2], i = l - 1, j = r + 1;
        while(i < j){
            do i++; while(arr[i] < x);
            do j--; while(arr[j] > x);
            if(i < j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        quickSort(arr, l, j);
        quickSort(arr, j + 1, r);
	}

	/*计数排序
		时间复杂度O(n+k)
		空间复杂度O(k)
	*/
	static void countingSort(int[] arr, int l, int r){
		int n = r - l + 1;
		int max = arr[l];
		for(int i : arr)
			max = Math.max(max, i);
		int[] bucket = new int[max + 1];
		for(int i = l; i < l + n; i++)
			bucket[arr[i]]++;
		for(int i = l, j = 0; j <= max; j++)
			while(bucket[j]-- > 0)
				arr[i++] = j;
	}

	/*桶排序
		时间复杂度O(n+k)
		空间复杂度O(n+k)
	*/
	static void bucketSort(int[] arr, int l, int r, int bucketSize){
		int n = r - l + 1;
		int min, max;
		min = max = arr[l];
		for(int i = l; i <= r; i++){
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		int bucketCount = (max - min) / bucketSize + 1;
		ArrayList<Integer>[] buckets = new ArrayList[bucketCount];
		for(int i = l; i <= r; i++){
			int bucketIdx = (arr[i] - min) / bucketSize;
			if(buckets[bucketIdx] == null)
				buckets[bucketIdx] = new ArrayList<Integer>();
			buckets[bucketIdx].add(arr[i]);
		}
		int i = l;
		for(ArrayList<Integer> bucket : buckets){
			if(bucket == null)	continue;
			Collections.sort(bucket);
			for(Integer integer : bucket)
				arr[i++] = integer;
		}
	}

	/*基数排序
		时间复杂度O(n*k)	//k为最大值的位数
		空间复杂度O(n)
	*/
	static void radixSort(int[] arr, int l, int r){
		int max = arr[l];
		for(int i = l; i <= r; i++)
			max = Math.max(max, arr[i]);
		int radix = 0;
		int tmp = max;
		while(tmp > 0){
			radix++;
			tmp /= 10;
		}
		int help = 0;
		while(help < radix){
			ArrayList<Integer>[] counts = new ArrayList[10];
			for(int i = l; i <= r; i++){
				int idx = arr[i] / (int)Math.pow(10, help) % 10;
				if(counts[idx] == null)
					counts[idx] = new ArrayList<Integer>();
				counts[idx].add(arr[i]);
			}
			int i = l;
			for(ArrayList<Integer> count : counts){
				if(count == null)	continue;
				for(Integer integer : count)
					arr[i++] = integer;
			}
			help++;
		}
	}

	static void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}