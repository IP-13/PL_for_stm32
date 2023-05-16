#pragma once

#include <inttypes.h>
#include <stdlib.h>

#define DEFAULT// define your structs sizes (in bytes). They depend on memory size

#ifdef DEFAULT
#define DATA_STACK_SIZE 1024
#define RET_STACK_SIZE 1024
#define VAR_MAP_SIZE 1024
#define HEAP_SIZE 1024
#define NULL_PTR HEAP_SIZE
#endif


// bytecode commands
enum byte_code_commands {
    EXIT = 0,
    JMP = 1,
    JDEC = 2,
    JRET = 3,
    LOOP = 10,
    FINT = 11,
    FVAR = 12,
    JT = 20,
    CALL = 100,
    LIT = 110,
    VAR = 111,
    OFC = 112,
    RLIT = 120,
    RVAR = 121,
    ROFC = 122,
    BOOL = 200,
    INT = 201,
    FLT = 202,
    STR = 203,
    PTR = 204,
    VOID = 205,
    PRINT = 700,
    AGGING = 701,
    GET_DATA = 710,
    SET_DATA = 711,
    GET_ADDR = 712,
    MALLOC = 713,
    AND = 720,
    OR = 721,
    NOT = 722,
    CONCAT = 725,
    SUBSTR = 726,
    LIKE = 727,
    LENGTH = 728,
    ABS = 735,
    SIN = 736,
    COS = 737,
    INC = 738,
    DEC = 739,
    LOG = 755,
    POW = 756,
    SUM = 757,
    SUB = 758,
    MUL = 759,
    DIV = 760,
    MOD = 761,
    MIN = 762,
    MAX = 763,
    LESS = 764,
    GREATER = 765,
    EQUAL = 766,
    PI = 775,
    E = 776,
    RANDOM = 777,
    MAIN = 999
};


// data_stack
struct var {
    enum byte_code_commands type;
    void *value;
};


struct data_stack {
    int32_t size;
    struct var *data;
};


struct var data_stack_top(struct data_stack *stack);


struct var data_stack_pop(struct data_stack *stack);


void data_stack_push(struct data_stack *stack, int32_t top);


// ret_stack
struct ret_stack {
    int32_t size;
    int32_t *data;
};


int32_t ret_stack_top(struct ret_stack *stack);


int32_t ret_stack_pop(struct ret_stack *stack);


void ret_stack_push(struct ret_stack *stack, int32_t top);


// var map
struct var_map {
    int32_t size;
    struct var *data;
};


struct var_map_map {
    int8_t size;
    struct var_map *data;
};


// heap
struct heap_entry {
    enum byte_code_commands type;
    void *value; // null by default
    int8_t num_of_links;
};


struct heap {
    int32_t size; // in bytes
    struct heap_entry *data; // index - addr, value - heap_entry
    int32_t next_free_entry; // index of the next free addr in the data.
};


struct heap_entry get_by_addr(int32_t addr, struct heap *heap);


int32_t heap_insert(void *value, struct heap *heap); // return addr at which value was inserted


// interpreter
struct interpreter {
    struct data_stack *data_stack;
    struct ret_stack *ret_stack;
    struct var_map_map *var_map_map;
    struct heap *heap;
};

