BINS = functiondriver
SRCS = functiondriver.c polynomial.c step.c exponential.c
OBJS = $(patsubst %.c,%.o,$(SRCS))

CFLAGS += -g -std=c99 -Wall -Wextra -Wno-unused
# Uncomment this to help you not ignore warnings
#CFLAGS += -Werror
LDFLAGS += -lm

.PHONY: all clean

all: $(BINS)

clean:
	$(RM) Makefile.dep $(BINS) $(OBJS)

$(BINS): $(OBJS)

Makefile.dep:
	gcc -MM $(SRCS) > $@

include Makefile.dep
