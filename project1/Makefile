BINS = sort_driver xperiment
OBJS = sort_driver.o sorts.o array_util.o

CFLAGS = -g -std=c99 -Wall
LDFLAGS = -g -lm

.PHONY: all clean

all: $(BINS)

clean:
	$(RM) $(BINS) $(OBJS)

sort_driver: sorts.o array_util.o

sort_driver.o: sorts.h array_util.h

xperiment: sorts.o array_util.o