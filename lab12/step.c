#include <stdlib.h>

#include "function.h"
#include "step.h"
#include "polynomial.h"

float
step_evaluate(struct function *super, float x) 
{
	struct step *this = (struct step *) super;
	if (x<this->stepPoint)
		return 0;
	else
		return this->stepLevel;
}

struct function *
step_differentiate(struct function *super) 
{
	struct step *this = (struct step *) super;
	float arr[] = {0};
	return poly_construct(0,arr);
}

float
step_integrate(struct function *super, float lower, float upper) 
{
	struct step *data = (struct step *) super;
	if (lower == upper)
		return 0;
	if (lower > upper)
		return -step_integrate(super, upper, lower);
	if (upper < data->stepPoint)
		return 0;
	if (lower > data->stepPoint)
		return data->stepLevel * (upper - lower);

	return data->stepLevel * (upper - data->stepPoint);
}

void
step_destroy(struct function *super)
{
	free(super);
}


struct function *
step_construct(float stepPoint, float stepLevel) 
{
	struct function * super = malloc(sizeof(struct step));
	super->evaluate = step_evaluate;
	super->differentiate = step_differentiate;
	super->integrate = step_integrate;
	super->destroy = step_destroy;

	struct step *this = (struct step *) super;

	this->stepPoint = stepPoint;
	this->stepLevel = stepLevel;
	return super;
}
