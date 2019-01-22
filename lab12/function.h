#ifndef FUNCTION_H
#define FUNCTION_H

struct function {
	float             (*evaluate)(struct function *this, float x);
	struct function * (*differentiate)(struct function *this);
	float             (*integrate)(struct function *this, float lower, float upper);
	void              (*destroy)(struct function *this);
};

static inline
float
func_evaluate(struct function *this, float x)
{
	return this->evaluate(this, x);
}

static inline
struct function *
func_differentiate(struct function *this)
{
	return this->differentiate(this);
}

static inline
float
func_integrate(struct function *this, float lower, float upper)
{
	return this->integrate(this, lower, upper);
}

static inline
void
func_destroy(struct function *this)
{
	return this->destroy(this);
}

typedef struct function function_t;

#endif
