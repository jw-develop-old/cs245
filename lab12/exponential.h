#ifndef EXPONENTIAL_H
#define EXPONENTIAL_H

#include "function.h"

struct exponential {
	struct function super;
	float coefficient;
};

typedef struct exponential exponential_t;

struct function *exp_construct(float coefficient);

#endif
