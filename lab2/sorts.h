#ifndef SORTS_H
#define SORTS_H

/* sorts.h */

/*
 * Each function sorts the given array with
 * a different sorting algorithm. Each sort is
 * either in-place or has the appearance of
 * in-place. Each function returns the number
 * of comparisons.
 */

int selectionSort(int array[], int n);

int insertionSort(int array[], int n);

int bubbleSort(int array[], int n);

int mergeSort(int array[], int n);

int merge(int array[], int aux[], int start, int stop);

int shellSort(int array[], int n);

#endif
