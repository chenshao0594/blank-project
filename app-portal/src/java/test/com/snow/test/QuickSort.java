package com.snow.test;

public class QuickSort {

	public void sort(int[] array, int low, int high) {
		int middle = this.subSort(array, low, high);

	}

	public int subSort(int[] array, int low, int high) {
		if (low >= high) {
			return low;
		}
		int pivot = array[high];
		while (low < high) {
			while (low < high && array[high] >= pivot) {
				high = high - 1;
			}
			while (low < high && array[low] < pivot) {
				low = low + 1;
			}
			exchange(array, low, high);
			low = low + 1;
			high = high - 1;

		}
		return low;
	}

	public void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {

		QuickSort s = new QuickSort();
		int[] a = new int[] { 2345, 23, 24, 2345, 2345, 100, 9 };
		s.sort(a, 0, a.length - 1);
		System.out.println(a);
		for (int each : a) {
			System.out.print(each + "->");
		}

	}
}
