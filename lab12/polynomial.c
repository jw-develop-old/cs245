#include <stdio.h>
#include <stdlib.h>

#include "function.h"
#include "polynomial.h"

float
poly_evaluate(struct function *super, float x) 
{
	float toReturn, currentPower;
	int i;
	struct polynomial *this = (struct polynomial *) super;

	toReturn = 0;
	currentPower = 1;

	for (i = 0; i <= this->degree; i++) 
	{
		toReturn += this->coefficients[i] * currentPower;
		currentPower *= x;
	}

	return toReturn;
}

struct function *
poly_differentiate(struct function *super) 
{
	struct polynomial *this = (struct polynomial *) super;
	float *diff_coef = (float *) calloc(sizeof(float), this->degree);
	int i;
	struct function *toReturn;

	for (i = 0; i < this->degree; i++)
		diff_coef[i] = this->coefficients[i + 1] * (i + 1);

	toReturn = poly_construct(this->degree-1, diff_coef);
	free(diff_coef);
	return toReturn;
}

float
poly_integrate(struct function *super, float lower, float upper) 
{
	struct polynomial *this = (struct polynomial *) super;
	float upperVal, lowerVal, cpLower, cpUpper;
	int i;
	upperVal = lowerVal = 0;
	cpLower = lower;
	cpUpper = upper;
	for (i = 0; i <= this->degree; i++)
	{
		upperVal += (this->coefficients[i] / (i + 1)) * cpUpper;
		lowerVal += (this->coefficients[i] / (i + 1)) * cpLower;
		cpUpper *= upper;
		cpLower *= lower;
	}
	return upperVal - lowerVal;
}

void
poly_destroy(struct function *super) 
{
	struct polynomial *poly = (struct polynomial *) super;
	free(poly->coefficients);
	free(super);
}

struct function *
poly_construct(int degree, float *coefficients)
{
	struct function * super = malloc(sizeof(struct polynomial));
	super->evaluate = poly_evaluate;
	super->differentiate = poly_differentiate;
	super->integrate = poly_integrate;
	super->destroy = poly_destroy;

	struct polynomial *poly = (struct polynomial *) super;

	poly->degree = degree;
	poly->coefficients = calloc(sizeof(float),degree + 1);
	for (int i = 0; i < degree + 1; i++)
		poly->coefficients[i] = coefficients[i];
	return super;
}