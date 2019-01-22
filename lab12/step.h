#ifndef STEP_H
#define STEP_H

#include "function.h"

struct step {
	struct function super;
	float stepPoint;
	float stepLevel;
};

typedef struct step step_t;

struct function *step_construct(float stepPoint, float stepLevel);

#endif
