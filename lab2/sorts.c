/* sorts.c */

#include <stdio.h>
#include <stdlib.h>

#include "sorts.h"
#include "array_util.h"

int
selectionSort(int array[], int n)
{
	int compars = 0;	/* number of comparisons */
	int i, j;

	for (i = 0; i < n; i++) {
		int min = array[i];
		int minPos = i;

		for (j = i + 1; j < n; j++) {
			compars++;
			if (min > array[j]) {
				min = array[j];
				minPos = j;
			}
		}

		array[minPos] = array[i];
		array[i] = min;
	}
	return compars;
}

int
bubbleSort(int array[], int n)
{
	int compars = 0;
	int i;

	int changed = 1;
	while (changed) {
		changed = 0;
		for (i = 1; i < n; i++) {
			compars++;
			if (array[i] < array[i - 1]) {
				changed = 1;
				int temp = array[i];
				array[i] = array[i - 1];
				array[i - 1] = temp;
			}
		}
	}

	return compars;
}

/* --------- Shell sort stuff starts here --------------- */

int
shellSort(int array[], int n)
{
	int compars = 0;

	return compars;
}

/* -------------- Merge sort stuff starts here -------------- */

// Merge two sorted halves of a section of an array into
// a helper array.
// Precondition: The range from start to stop in array is
// such that the bottom half (from start inclusive to the midpoint
// exclusive) and the top half (from the midpoint inclusive to
// stop exclusive) are each sorted.
// Postcondition: the values in [start, stop) from array have
// been colated into the range [0, stop-start) in helpder.
// Return: the number of comparisons between elements

int
merge(int array[], int helper[], int start, int stop)
{
	int compars = 0;	// number of comparisons

	int mid = (start + stop) / 2;	// index indicating the midpoint

	int i = start;		// index into the first sorted segment of array

	int j = mid;		// index into the second sorted segment of array

	int k;			// index into helper, the next index where to write

	// loop invariant: the range [0, k) in helper contains
	// is sorted and contains the values in [start, i) and [mid, j)
	// in array, which are the k smallest elements in [start, stop)
	// in array
	for (k = 0; k < stop - start; k++) {

		// fill-in the rest

	}
	return compars;
}

// sort the subarray from start (inclusive) to stop (exclusive)
// return the number of comparisons
int
mergeSortR(int array[], int helper[], int start, int stop)
{

	if (start + 1 >= stop)
		return 0;
	else {
		int counter = 0;	// the number of comparisons
		int midpoint = (start + stop) / 2;	// index to the middle of the range
		int k, n;

		n = stop - start;

		counter += mergeSortR(array, helper, start, midpoint);

		counter += mergeSortR(array, helper, midpoint, stop);

		counter += merge(array, helper, start, stop);

		for (k = 0; k < n; k++)
			array[start + k] = helper[k];
		return counter;
	}

}

// you will not need to modify this function
int
mergeSort(int array[], int n)
{
	int comparisons;

	// You'll understand this line about halfway through the semester.
	// It allocates an auxilliary array of size n dynamically.
	int *helper = (int *) calloc(sizeof(int), n);

	comparisons = mergeSortR(array, helper, 0, n);

	// This also is something we won't cover until later in the semester.
	// It deallocates the array that was allocated in the "calloc" line.
	free(helper);

	return comparisons;
}

/* -------------- Insertion sort stuff starts here -------------- */

int
insertionSort(int array[], int n)
{

	// if you do this part of the lab, delete the next line
	return 0;

	int compars = 0;
	int i, j;

	// invariant: The range [0, i) is sorted
	for (i = 1; i < n; i++) {
		compars++;

		// invariant:
		//   - The range [0, j) is sorted
		//   - The range [j, i) is sorted
		for (j = i; /* ?? */ ; /* ?? */ ) {

			// ??

			compars++;
		}
	}

	return compars;
}
