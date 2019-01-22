#ifndef POLYNOMIAL_H
#define POLYNOMIAL_H

#include "function.h"

struct polynomial {
	struct function super;
	int degree;
	float *coefficients;
};

typedef struct polynomial polynomial_t;

struct function *poly_construct(int degree, float *coefficients);

#endif
