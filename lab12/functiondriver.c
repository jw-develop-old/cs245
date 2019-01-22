#include <stdio.h>

#include "function.h"
#include "polynomial.h"

#include "step.h"
#include "exponential.h"


int
main(void)
{
	struct function *test, *derivative;
	int choice;
	float value, lowerBound, upperBound;
	float array[] = {1, 2, 3};

	printf("1=Polynomial, 2=Step, 3=Exponential; your choice--> ");
	scanf("%d", &choice);

	switch (choice) 
	{
		case 1:
			test = poly_construct(2, array);
			break;
		case 2:
			test = step_construct(4, 7);
			break;
		case 3:
			test = exp_construct(3);
			break;
		default:
			printf("No such choice, %d. Expect unexpected results.\n", choice);
	}

	printf("Test value--> ");
	scanf("%f", &value);

	derivative = func_differentiate(test);

	printf("Value of function: %f\nValue of derivative: %f\n",
			func_evaluate(test, value),
			func_evaluate(derivative, value));

	func_destroy(derivative);

	printf("Lower bound--> ");
	scanf("%f", &lowerBound);
	printf("Upper bound--> ");
	scanf("%f", &upperBound);
	printf("definite integral: %f\n", func_integrate(test, lowerBound, upperBound));

	func_destroy(test);

	return 0;
}