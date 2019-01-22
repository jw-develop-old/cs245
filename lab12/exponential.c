#include <stdlib.h>
#include <math.h>

#include "function.h"
#include "exponential.h"

float
exp_evaluate(struct function *super, float x)
{
	struct exponential *this = (struct exponential *) super;
	return this->coefficient * expf(x);
}

struct function *
exp_differentiate(struct function *super)
{
	struct exponential *exp = (struct exponential *) super;
	return exp_construct(exp->coefficient);
}

float
exp_integrate(struct function *super, float lower, float upper) 
{
	return exp_evaluate(super, upper) - exp_evaluate(super, lower);
}

void
exp_destroy(struct function *super)
{
	free(super);
}


struct function *
exp_construct(float coefficient)
{
	struct function * super = malloc(sizeof(struct exponential));
	super->evaluate = exp_evaluate;
	super->differentiate = exp_differentiate;
	super->integrate = exp_integrate;
	super->destroy = exp_destroy;

	struct exponential *exp = (struct exponential *) super;
	exp->coefficient = coefficient;
	
	return super;
}
