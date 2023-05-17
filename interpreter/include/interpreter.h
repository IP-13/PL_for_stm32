#pragma once

#include <inttypes.h>
#include <stdlib.h>

#define DEFAULT // define your structs sizes (in bytes). They depend on memory num_of_entries

#ifdef DEFAULT
#define DATA_STACK_SIZE 1024 // max num_of_entries - 65536
#define RET_STACK_SIZE 1024 // max num_of_entries - 65536
#define VAR_MAP_SIZE 1024 // max num_of_entries - 65536
#define HEAP_SIZE 1024 // max num_of_entries - 4294967296
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
    ASSIGN = 701,
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


struct var {
    enum byte_code_commands type;
    void *value;
};


// data_stack
struct data_stack {
    uint16_t num_of_entries;
    struct var *data;
};


struct var data_stack_top(struct data_stack *stack);


struct var data_stack_pop(struct data_stack *stack);


void data_stack_push(struct data_stack *stack, enum byte_code_commands type, void *value);


// ret_stack
struct ret_stack {
    uint16_t num_of_entries;
    uint32_t *data;
};


uint32_t ret_stack_top(struct ret_stack *stack);


uint32_t ret_stack_pop(struct ret_stack *stack);


void ret_stack_push(struct ret_stack *stack, uint32_t top);


// var map
struct var_map {
    uint16_t num_of_entries;
    struct var *data;
};


struct var_map_map {
    uint8_t num_of_entries;
    struct var_map *data;
};


struct var var_map_get(uint32_t var_index, uint32_t map_index, struct var_map_map *var_map_map);


void var_map_set(enum byte_code_commands type, void *value, uint32_t var_index, uint32_t map_index,
                 struct var_map_map *var_map_map);


void var_map_push(enum byte_code_commands type, void *value, struct var_map_map *var_map_map);


// heap
struct heap_entry {
    uint8_t num_of_links;
    enum byte_code_commands type;
    void *value; // null by default
};


struct heap {
    uint32_t num_of_entries;
    struct heap_entry *data; // index - addr, value - heap_entry
    uint32_t next_free_entry; // index of the next free addr in the data.
};


struct heap_entry heap_get_by_addr(uint32_t addr, struct heap *heap);


uint32_t heap_insert(void *value, enum byte_code_commands type, struct heap *heap); // return addr at which value was inserted


// interpreter
struct interpreter {
    struct data_stack *data_stack;
    struct ret_stack *ret_stack;
    struct var_map_map *var_map_map;
    struct heap *heap;
};


void interpret(struct interpreter interpreter, int32_t *byte_code, uint32_t start);


struct var NULL_DATA_STACK_ENTRY = {.value = NULL};
uint32_t NULL_RET_STACK_ENTRY = 0;
struct var NULL_VAR_MAP_ENTRY = {.value = NULL};
struct heap_entry NULL_HEAP_ENTRY = {.value = NULL, .num_of_links = 0};