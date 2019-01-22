# You can set up variables in Makefiles, then used them with $(...)

BINS = sort_driver merge_driver experiment
OBJS = $(addsuffix .o,$(BINS))
OBJS += sorts.o array_util.o


# Default flags to use for gcc when compiling (CFLAGS) and linking (LDFLAGS).
# -Wall: turn on warnings
# -g: allow debugging
# -std=c99: use the updated C standard from 1999
# -Wno-unused-xxxx: turn off warnings from not-yet-filled-in code

CFLAGS = -Wall -g -std=c99 -Wno-unused-variable -Wno-unused-but-set-variable
LDFLAGS = -g -lm


# Phony targets: "all" and "clean" aren't actually files

.PHONY: all clean

all: $(BINS)

clean:
	$(RM) $(BINS) $(OBJS)


# Make knows many standard recipes; in many cases you only need to specify the
# targets and dependencies, and make can handle the rest.

$(BINS): sorts.o array_util.o

$(BINS): %: %.o
