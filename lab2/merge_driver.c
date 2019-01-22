#include "array_util.h"
#include "sorts.h"
#include <stdlib.h>
#include <stdio.h>

int samples[][20] = {
	{},
	{5},
	{1, 2},
	{1, 2, 3},
	{3, 1, 2},
	{2, 1, 3},
	{4, 5, 7, 8},
	{7, 8, 4, 5},
	{4, 8, 5, 7},
	{5, 7, 4, 5, 8},
	{7, 8, 1, 2, 3},
	{8, 9, 11, 3, 5, 10},
	{8, 9, 11, 3, 5, 10, 12},
	{-1, 1, 4},
	{-1, 5, 8, 1, 7},
	{-1, -1, -1, -1, -1, -1, 2, 5, 7, 9, 10, 1, 1, 7, 8, 12}
};

int start_stop[][2] = {
	{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 3}, {0, 3}, {0, 4}, {0, 4}, {0, 4},
	{0, 5}, {0, 5}, {0, 6}, {0, 7}, {1, 3}, {1, 5}, {6, 16}
};

int num_tests = 16;

int
main()
{
	int i, j;
	int temp[20];
	int failure = 0;

	for (i = 0; i < num_tests; i++) {
		int preserves_contents, sorted;
		int start, stop;
		start = start_stop[i][0];
		stop = start_stop[i][1];

		for (j = 0; j < stop - start; j++)
			temp[j] = 0;
		for (; j < 20; j++)
			temp[j] = 100;

		merge(samples[i], temp, start, stop);

		preserves_contents =
				same_contents(samples[i] + start, temp,
				stop - start);
		sorted = is_sorted(temp, 20);
		if (!(preserves_contents && sorted)) {
			failure = 1;
			printf("Failure:\n");
			display_array(samples[i] + start, stop - start);
			display_array(temp, stop - start);
		}

	}

	if (!failure)
		printf("All tests pass.\n");
	return failure;
}
