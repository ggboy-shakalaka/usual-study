package ggboy.study.java.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortMethods {

	public static void main(String[] args) {
		Random r = new Random();
		int[] data = new int[100000];
		for (int i = 0; i < data.length; i++) {
			data[i] = r.nextInt(100000);
		}

		List<int[]> resultList = new ArrayList<>(8);
		int[] tempData;
		long start;

		System.out.println("直接插入排序");
		tempData = Arrays.copyOf(data, data.length);
		start = System.currentTimeMillis();
		tempData = method1(tempData);
		System.out.println(System.currentTimeMillis() - start);
		resultList.add(tempData);

		System.out.println("希尔排序");
		tempData = Arrays.copyOf(data, data.length);
		start = System.currentTimeMillis();
		tempData = method2(tempData);
		System.out.println(System.currentTimeMillis() - start);
		resultList.add(tempData);

		System.out.println("简单选择排序");
		tempData = Arrays.copyOf(data, data.length);
		start = System.currentTimeMillis();
		tempData = method3(tempData);
		System.out.println(System.currentTimeMillis() - start);
		resultList.add(tempData);

		System.out.println("堆排序");
		tempData = Arrays.copyOf(data, data.length);
		start = System.currentTimeMillis();
		tempData = method4(tempData);
		System.out.println(System.currentTimeMillis() - start);
		resultList.add(tempData);

		System.out.println("冒泡排序");
		tempData = Arrays.copyOf(data, data.length);
		start = System.currentTimeMillis();
		tempData = method5(tempData);
		System.out.println(System.currentTimeMillis() - start);
		resultList.add(tempData);

		System.out.println("快速排序");
		tempData = Arrays.copyOf(data, data.length);
		start = System.currentTimeMillis();
		tempData = method6(tempData);
		System.out.println(System.currentTimeMillis() - start);
		resultList.add(tempData);

		// System.out.println("直接插入排序");
		// tempData = Arrays.copyOf(data, data.length);
		// start = System.currentTimeMillis();
		// tempData = method7(tempData);
		// System.out.println(System.currentTimeMillis() - start);
		// resultList.add(tempData);

		// System.out.println("直接插入排序");
		// tempData = Arrays.copyOf(data, data.length);
		// start = System.currentTimeMillis();
		// tempData = method8(tempData);
		// System.out.println(System.currentTimeMillis() - start);
		// resultList.add(tempData);

		for (int[] result : resultList) {
			int temp = 0;
			boolean flag = true;
			for (int i : result) {
				// System.out.print(i + ",");
				// System.out.println();
				if (i < temp) {
					flag = !flag;
					break;
				}
				temp = i;
			}
			System.out.println(flag);
		}
	}

	/**
	 * 直接插入排序 <br />
	 * 在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
	 * 也是排好顺序的。如此反复循环，直到全部排好顺序。
	 */
	public static int[] method1(int[] data) {
		int i, j, temp;
		for (i = 1; i < data.length; i++) {
			temp = data[i];
			for (j = i; j > 0 && temp < data[j - 1]; j--) {
				data[j] = data[j - 1];
			}
			data[j] = temp;
		}
		return data;
	}

	/**
	 * 希尔排序 <br />
	 * 算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组， 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
	 * 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。 当增量减到1时，进行直接插入排序后，排序完成。
	 */
	public static int[] method2(int[] data) {
		int zld = data.length;
		while (zld > 1) {
			zld = (int) Math.ceil(zld / 2);
			for (int k = 0; k < zld; k++) {
				int i, j, temp;
				for (i = k + zld; i < data.length; i += zld) {
					temp = data[i];
					for (j = i; j > zld - 1 && temp < data[j - zld]; j -= zld) {
						data[j] = data[j - zld];
					}
					data[j] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * 简单选择排序 <br />
	 * 在要排序的一组数中，选出最小的一个数与第一个位置的数交换； 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
	 */
	public static int[] method3(int[] data) {
		int i, j, temp, index;
		for (i = 0; i < data.length - 1; i++) {
			for (index = i, j = i + 1; j < data.length; j++) {
				if (data[index] > data[j]) {
					index = j;
				}
			}
			if (index > i) {
				temp = data[i];
				data[i] = data[index];
				data[index] = temp;
			}
		}
		return data;
	}

	/**
	 * 堆排序 <br />
	 * 堆排序是一树形选择排序，在排序过程中，将R[1..N]看成是一颗完全二叉树的顺序存储结构，
	 * 利用完全二叉树中双亲结点和孩子结点之间的内在关系来选择最小的元素。
	 */
	public static int[] method4(int[] data) {
		return data;
	}

	/**
	 * 冒泡排序 <br />
	 * 在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：
	 * 每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
	 */
	public static int[] method5(int[] data) {
		int i, j, temp;
		for (i = 0; i < data.length; i++) {
			for (j = data.length - 1; j > i; j--) {
				if (data[j] < data[j - 1]) {
					temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * 快速排序 <br />
	 * 选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
	 * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
	 */
	public static int[] method6(int[] data) {
		quickSort(data, 0, data.length - 1);
		return data;
	}

	private final static void quickSort(int[] data, int start, int end) {
		int leftIndex = start, rightIndex = end, temp = data[start], index = leftIndex;
		while (leftIndex < rightIndex) {
			while (data[rightIndex] >= temp && rightIndex > leftIndex) {
				rightIndex--;
			}
			data[leftIndex] = data[rightIndex];
			index = rightIndex;

			while (data[leftIndex] <= temp && leftIndex < rightIndex) {
				leftIndex++;
			}
			data[rightIndex] = data[leftIndex];
			index = leftIndex;
		}

		data[index] = temp;

		if (index - start > 1) {
			quickSort(data, start, index - 1);
		}
		if (end - index > 1) {
			quickSort(data, index + 1, end);
		}

	}

	/**
	 * 归并排序 <br />
	 * 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
	 */
	public static int[] method7(int[] data) {
		return data;
	}

	/**
	 * 基数排序 <br />
	 * 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
	 */
	public static int[] method8(int[] data) {
		return data;
	}
}