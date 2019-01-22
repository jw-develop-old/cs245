#include <stdio.h>
#include "array_util.h"
#include "sorts.h"

int
main()
{
	int i;
	long fore, aft;
	int comparisons;

	int master[500000];
	int copy[500000];

	int sizes[] = { 10, 50, 100, 150, 500, 10000, 15000, 20000, 25000,
		30000, 35000, 40000, 45000, 50000
	};

	printf("\tbubble\tselection\tinsertion\tshell\tmerge\n");
	printf("comparisons\n");
	for (i = 0; i < 4; i++) {
		printf("%d:", sizes[i]);
		random_array(master, sizes[i]);

		copy_array(master, copy, sizes[i]);
		comparisons = bubbleSort(copy, sizes[i]);

		printf("\t%d", comparisons);

		copy_array(master, copy, sizes[i]);
		comparisons = selectionSort(copy, sizes[i]);

		printf("\t%d", comparisons);

		copy_array(master, copy, sizes[i]);
		comparisons = insertionSort(copy, sizes[i]);

		printf("\t\t%d", comparisons);

		copy_array(master, copy, sizes[i]);
		comparisons = shellSort(copy, sizes[i]);

		printf("\t\t%d", comparisons);

		copy_array(master, copy, sizes[i]);
		comparisons = mergeSort(copy, sizes[i]);

		printf("\t%d\n", comparisons);
	}

	printf("running times\n");
	for (i = 4; i < 14; i++) {
		random_array(master, sizes[i]);
		printf("%d:", sizes[i]);

		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		bubbleSort(copy, sizes[i]);
		aft = get_time_millis();

		printf("\t%ld", aft - fore);

		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		selectionSort(copy, sizes[i]);
		aft = get_time_millis();

		printf("\t%ld", aft - fore);

		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		insertionSort(copy, sizes[i]);
		aft = get_time_millis();

		printf("\t\t%ld", aft - fore);

		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		shellSort(copy, sizes[i]);
		aft = get_time_millis();

		printf("\t\t%ld", aft - fore);

		copy_array(master, copy, sizes[i]);
		fore = get_time_millis();
		mergeSort(copy, sizes[i]);
		aft = get_time_millis();

		printf("\t%ld\n", aft - fore);

	}

	return 0;
}
