/* sorts.c */

#include "sorts.h"
#include "array_util.h"
#include <stdio.h>
#include <stdlib.h>

int
selectionSort(int array[], int n)
{
  int compars = 0;	/* comparisons */
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
insertionSort(int array[], int n)
{
	int compars = 0;
	int i, j;

	for (i = 1; i < n; i++) {
		compars++;
		for (j = i; j > 0 && array[j] < array[j - 1]; j--) {
			int temp = array[j];
			array[j] = array[j - 1];
			array[j - 1] = temp;
			compars++;
		}
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

int
quickSortR(int array[], int start, int stop)
{
int compars = 0;
int temp,j;
int j;
int piv = array[stop-1];
int i = start-1;
  if (start + 1 >= stop)
    return 0;
  else {
    for (j=start;j<=stop;j++) { 
      compars++;
      if (piv>array[j]) {
		i++;
		temp = array[i];
		array[i] = array[j];
		array [j] = temp;
      }
    }
    temp = array[stop-1];
    array[stop-1] = array[i+1];
    array[i+1] = temp;
    int midpoint = i+1;
    compars += quickSortR(array, start, midpoint);
    compars += quickSortR(array, midpoint+1, stop);
  	return compars;
	}
}
int
quickSort(int array[], int n)
{
  return quickSortR(array, 0, n);
}

int
shellSort(int array[], int n)
{
  int compars = 0;
  int gap, i, j, k;

  for (gap = n / 3;; gap = gap / 13 + 1) {
    //printf("(%d)\n", gap);
    for (k = 0; k < gap; k++)
      for (i = k + gap; i < n; i += gap) {
	compars++;
	for (j = i; j >= gap
	       && array[j] < array[j - gap];
	     j -= gap) {
	  int temp = array[j];
	  array[j] = array[j - gap];
	  array[j - gap] = temp;
	  compars++;
	}
      }
    if (gap == 1)
      break;
  }


  return compars;
}



int
merge(int array[], int helper[], int start, int stop)
{
	int i, j, k;
	int n = stop - start;
	int midpoint = (start + stop) / 2;	// index to the middle of the range
	int compars = 0;


	//the subarrays
	i = start;		// index into the first subarray
	j = midpoint;		// index into the second subarray
	for (k = 0; k < n; k++) {	// (k is index into helper)

		if (i >= midpoint) {

			helper[k] = array[j];
			j++;
		} else if (j >= stop) {

			helper[k] = array[i];
			i++;
		} else if (array[i] < array[j]) {

			compars++;
			helper[k] = array[i];
			i++;
		} else {
			compars++;
			helper[k] = array[j];
			j++;
		}

	}

	return compars;
}


int
mergeSortR(int array[], int helper[], int start, int stop)
{

	if (start + 1 >= stop)
		return 0;
	else {
		int compars = 0;	// the number of comparisons
		int midpoint = (start + stop) / 2;	// index to the middle of the range
		int k, n;

		n = stop - start;

		compars += mergeSortR(array, helper, start, midpoint);

		compars += mergeSortR(array, helper, midpoint, stop);

		compars += merge(array, helper, start, stop);

		for (k = 0; k < n; k++)
			array[start + k] = helper[k];
		return compars;
	}
}


// you will not need to modify this function
int
mergeSort(int array[], int n)
{
	int compars;

	// You'll understand this line about halfway through the semester.
	// It allocates an auxilliary array of size n dynamically.
	int *helper = (int *) calloc(sizeof(int), n);

	compars = mergeSortR(array, helper, 0, n);


	// This also is something we won't cover until later in the semester.
	// It deallocates the array that was allocated in the "calloc" line.
	free(helper);

	return compars;
}
