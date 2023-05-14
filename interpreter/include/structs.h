#pragma once

#include <inttypes.h>
#include <stdlib.h>

#define MAX_DATA_STACK_SIZE 3
#define MAX_RET_STACK_SIZE 32768
#define MAX_HEAP_SIZE (1024 * 10)
#define MAX_SIZE_OF_GLOBAL_VARS_MAP (1024 * 10)
#define MAX_SIZE_OF_LOCAL_VARS_MAP (1024 * 10)


// variable
enum var_type {
    BOOL,
    INT,
    FLOAT,
    STRING,
    POINTER
};


// This language is targeted to embedded systems, so we can't use dynamic memory allocation.
// Everything will be created in the int main() and will be used by type conversion, that's why we need field var_type.
struct var {
    enum var_type type;
    void *value;
};




// stack
// This structure will contain co
struct stack_entry {
    enum var_type type;
    void *data;
};


struct stack {
    int32_t size;
    struct stack_entry *data;
};


int32_t top(struct stack *stack);


int32_t pop(struct stack *stack);


int32_t push(struct stack *stack, int32_t top);


void destroy_stack(struct stack *stack);




// heap
struct heap_entry {
    enum var_type type;
    void *value; // null by default
    int8_t is_free;
    int8_t num_of_links;
};


struct heap {
    int32_t size; // in bytes
    struct heap_entry *data; // index - addr, value - heap_entry
    int32_t next_free_entry; // index of the next free addr in the data.
};


struct heap_entry get_by_addr(int32_t addr, struct heap *heap);


int32_t heap_insert(void *value, struct heap *heap); // return addr at which value was inserted




// var map
struct var_map {
    int32_t size;
    struct var *data;
};


struct var_map_map {
    int8_t size;
    struct var_map *data;
};

