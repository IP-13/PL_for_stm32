#include <inttypes.h>
#include <stdio.h>
#include <math.h>

#define DEFAULT // define your structs sizes (in bytes). They depend on memory num_of_entries

#ifdef DEFAULT
#define DATA_STACK_SIZE 1024 // max num_of_entries - 65536
#define RET_STACK_SIZE 1024 // max num_of_entries - 65536
#define VAR_MAP_SIZE 1024 // max num_of_entries - 65536
#define HEAP_SIZE 1024 // max num_of_entries - 4294967296
#endif

#define NULL_BOOL_VALUE 0
#define NULL_INT_VALUE 0
#define NULL_FLT_VALUE 0
#define NULL_PTR_VALUE HEAP_SIZE
#define NULL_STR_VALUE (-1)
#define MAX_BYTE_CODE_SIZE 1000

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
    NOT_YET_DEFINED_TYPE = 206,
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
    INC = 736,
    DEC = 737,
    POW = 756,
    SUM = 757,
    SUB = 758,
    MUL = 759,
    DIV = 760,
    MOD = 761,
    LESS = 762,
    GREATER = 763,
    PI = 775,
    E = 776,
    RANDOM_INT = 777,
    RANDOM_STRING = 778,
    RANDOM_BOOL = 779,
    RANDOM_FLOAT = 780,
    MAIN = 999
};


// structs
struct var {
    int32_t value;
    enum byte_code_commands type;
};


struct data_stack {
    uint16_t num_of_entries;
    struct var *data;
};


struct ret_stack {
    uint16_t num_of_entries;
    uint32_t *data;
};


struct var_map {
    uint16_t num_of_entries;
    struct var *data;
};


struct var_map_map {
    uint8_t num_of_entries;
    struct var_map *data;
};


struct heap_entry {
    int32_t value; // null by default
    enum byte_code_commands type; // NOT_YET_DEFINED by default
    uint8_t num_of_links;
};


struct heap {
    uint32_t num_of_entries;
    struct heap_entry *data; // index - addr, value - heap_entry
    uint32_t next_free_entry; // index of the next free addr in the data.
};


struct interpreter {
    struct data_stack *data_stack;
    struct ret_stack *ret_stack;
    struct var_map_map *var_map_map;
    struct heap *heap;
};


// NULL VALUE STRUCTS
const struct var NULL_DATA_STACK_ENTRY = {.value = 0, .type = NOT_YET_DEFINED_TYPE};
const uint32_t NULL_RET_STACK_ENTRY = 0;
const struct var NULL_VAR_MAP_ENTRY = {.value = 0, .type = NOT_YET_DEFINED_TYPE};
const struct heap_entry NULL_HEAP_ENTRY = {.value = 0, .type = NOT_YET_DEFINED_TYPE, .num_of_links = 0};


// FUNCTIONS
struct var *data_stack_top(struct data_stack *stack) {
    if (stack->num_of_entries == 0) {
        // stack underflow
    }

    return &stack->data[stack->num_of_entries - 1];
}


struct var data_stack_pop(struct data_stack *stack) {
    if (stack->num_of_entries == 0) {
        // stack underflow
    }

    struct var top = stack->data[--stack->num_of_entries];
    stack->data[stack->num_of_entries] = NULL_DATA_STACK_ENTRY;
    return top;
}


void data_stack_push(int32_t value, enum byte_code_commands type, struct data_stack *stack) {
    if (stack->num_of_entries == DATA_STACK_SIZE) {
        // stack overflow
    }

    struct var var = {.value = value, .type = type};
    stack->data[stack->num_of_entries++] = var;
}


uint32_t *ret_stack_top(struct ret_stack *stack) {
    if (stack->num_of_entries == 0) {
        // stack underflow
    }

    return &stack->data[stack->num_of_entries - 1];
}


uint32_t ret_stack_pop(struct ret_stack *stack) {
    if (stack->num_of_entries == 0) {
        // stack underflow
    }

    uint32_t top = stack->data[--stack->num_of_entries];
    stack->data[stack->num_of_entries] = NULL_RET_STACK_ENTRY;
    return top;
}


void ret_stack_push(uint32_t top, struct ret_stack *stack) {
    if (stack->num_of_entries == RET_STACK_SIZE) {
        // stack overflow
    }

    stack->data[stack->num_of_entries++] = top;
}


struct var var_map_get(uint32_t var_index, uint32_t map_index, struct var_map_map *var_map_map) {
    if (map_index >= var_map_map->num_of_entries) {
        // index out of bounds
    }

    if (var_index >= var_map_map->data[map_index].num_of_entries) {
        // index out of bounds
    }

    return var_map_map->data[map_index].data[var_index];
}


void var_map_set(int32_t value,
                 enum byte_code_commands type,
                 uint32_t var_index,
                 uint32_t map_index,
                 struct var_map_map *var_map_map) {
    if (map_index >= var_map_map->num_of_entries) {
        // index out of bounds
    }

    if (var_index >= var_map_map->data[map_index].num_of_entries) {
        // index out of bounds
    }

    struct var curr_var_in_var_index = var_map_map->data[map_index].data[var_index];

    if (type != curr_var_in_var_index.type) {
        // different types
    }

    var_map_map->data[map_index].data[var_index].value = value;
}


void var_map_push(int32_t value, enum byte_code_commands type, struct var_map_map *var_map_map) {
    if (var_map_map->data[var_map_map->num_of_entries - 1].num_of_entries == VAR_MAP_SIZE) {
        // stack overflow
    }

    int32_t index_to_push = var_map_map->data[var_map_map->num_of_entries - 1].num_of_entries;

    struct var var = {.value = value, .type = type};

    var_map_map->data[var_map_map->num_of_entries - 1].data[index_to_push] = var;

    var_map_map->data[var_map_map->num_of_entries - 1].num_of_entries++;
}


void heap_collect_garbage(struct heap *heap) {
    for (size_t i = 0; i < heap->num_of_entries; i++) {
        if (heap->data[i].num_of_links == 0) {
            heap->data[i] = NULL_HEAP_ENTRY;
            heap->num_of_entries--;
        }
    }

    for (size_t i = 0; i < HEAP_SIZE; i++) {
        if (heap->data[i].num_of_links == 0) {
            heap->next_free_entry = i;
            return;
        }
    }
}


struct heap_entry heap_get(uint32_t addr, struct heap *heap) {
    if (addr >= heap->num_of_entries) {
        // index out of bounds
    }

    return heap->data[addr];
}


void heap_set(uint32_t addr, int32_t value, enum byte_code_commands type, struct heap *heap) {
    if (heap->data[addr].type != NOT_YET_DEFINED_TYPE && type != heap->data[addr].type) {
        // different types
    }

    heap->data[addr].value = value;
}


uint32_t heap_malloc(struct heap *heap) { // return allocated addr
    if (heap->next_free_entry == HEAP_SIZE) {
        heap_collect_garbage(heap);
    }

    if (heap->next_free_entry == HEAP_SIZE) {
        // heap overflow
    }

    heap->num_of_entries++;

    uint32_t old_next_free_entry = heap->next_free_entry;

    heap->next_free_entry = HEAP_SIZE;

    for (size_t i = old_next_free_entry + 1; i < HEAP_SIZE; i++) {
        if (heap->data[i].num_of_links == 0) {
            heap->next_free_entry = i;
            break;
        }
    }

    return old_next_free_entry;
}


void heap_dec_num_of_links(uint32_t addr, struct heap *heap) {
    if (addr >> 31 != 0) {
        // reference to a non-heap addr
        // heap addr starts with 0
        // with 1 starts var_map addr
    }

    if (heap->data[addr].num_of_links == 0) {
        return; // memory is free already
    }

    heap->data[addr].num_of_links--;

    if (heap->data[addr].num_of_links == 0) {
        heap->data[addr] = NULL_HEAP_ENTRY;
        heap->num_of_entries--;
        if (heap->next_free_entry > addr) {
            heap->next_free_entry = addr;
        }
    }
}


void heap_inc_num_of_links(uint32_t addr, struct heap *heap) {
    if (addr >> 31 != 0) {
        // reference to a non-heap addr
        // heap addr starts with 0
        // with 1 starts var_map addr
    }

    heap->data[addr].num_of_links++;
}


void remove_var_map(struct interpreter *interpreter) { // removes (nullifies) top var_map
    struct var_map_map *var_map_map = interpreter->var_map_map;
    var_map_map->num_of_entries--;
    for (size_t i = 0; i < var_map_map->data[var_map_map->num_of_entries].num_of_entries; i++) {
        struct var var = var_map_map->data[var_map_map->num_of_entries].data[i];

        if (var.type == PTR) {
            heap_dec_num_of_links(var.value, interpreter->heap);
        }

        var_map_map->data[var_map_map->num_of_entries].data[i] = NULL_VAR_MAP_ENTRY;
    }

    var_map_map->data[var_map_map->num_of_entries].num_of_entries = 0;
}


int32_t get_from_cycle_value(uint32_t *curr_command_addr, const int32_t *byte_code, struct interpreter *interpreter) {
    int32_t value;

    if (byte_code[++(*curr_command_addr)] == FINT) {
        value = byte_code[++(*curr_command_addr)];
    } else {
        uint32_t var_index = byte_code[++(*curr_command_addr)];
        uint32_t map_index = interpreter->var_map_map->num_of_entries - 1;
        value = var_map_get(var_index, map_index, interpreter->var_map_map).value;
    }

    return value;
}


uint32_t get_int_bits(uint32_t bits, int32_t from, int32_t to) { // from and to include
    return bits << (31 - from) >> (to + 31 - from);
}


struct var get_by_addr(uint32_t addr, struct heap *heap, struct var_map_map *var_map_map) {
    uint32_t is_in_var_map = get_int_bits(addr, 31, 31);

    if (!is_in_var_map) {
        struct heap_entry heap_entry = heap_get(addr, heap);
        return (struct var) {.type = heap_entry.type, .value = heap_entry.value};
    } else {
        uint32_t map_index = get_int_bits(addr, 30, 16);
        uint32_t var_index = get_int_bits(addr, 15, 0);
        return var_map_get(var_index, map_index, var_map_map);
    }
}


void set_by_addr(uint32_t addr,
                 int32_t value,
                 enum byte_code_commands type,
                 struct heap *heap,
                 struct var_map_map *var_map_map) {
    uint32_t is_in_var_map = get_int_bits(addr, 31, 31);

    if (!is_in_var_map) {
        heap_set(addr, value, type, heap);
    } else {
        uint32_t map_index = get_int_bits(addr, 30, 16);
        uint32_t var_index = get_int_bits(addr, 15, 0);
        var_map_set(type, value, var_index, map_index, var_map_map);
    }
}


struct var get_data_from_data_stack_top(struct data_stack *stack, struct var_map_map *var_map_map) {
    struct var var = data_stack_pop(stack);

    if (var.type == VAR) { // if var.type == VAR, then var.value = var index in var_map
        var = var_map_get(var.value, var_map_map->num_of_entries, var_map_map);
    }

    return var;
}


float int_to_float(int32_t num) {
    void *p = &num;
    float f = *(float *) p;
    return f;
}


int32_t float_to_int(float f) {
    void *p = &f;
    int32_t num = *(int32_t *) p;
    return num;
}


void interpret(struct interpreter *interpreter, int32_t *byte_code, uint32_t start, uint32_t *byte_code_size_ptr) {
    uint32_t byte_code_size = *byte_code_size_ptr;
    uint32_t curr_command_addr = start;

    while (byte_code[curr_command_addr] != EXIT) {
        switch (byte_code[curr_command_addr]) {
            case EXIT: {
                return;
            }
            case JMP: {
                curr_command_addr = byte_code[curr_command_addr + 1];
                break;
            }
            case JDEC: {
                curr_command_addr++; // now curr_command_addr points to offset relative to current addr
                uint32_t temp = curr_command_addr;
                curr_command_addr += byte_code[curr_command_addr];
                uint32_t counter = --byte_code[curr_command_addr];
                if (!counter) {
                    curr_command_addr = temp;
                }
                break;
            }
            case JRET: {
                curr_command_addr = ret_stack_pop(interpreter->ret_stack);
                remove_var_map(interpreter);
                break;
            }
            case LOOP: {
                int32_t lower_bound = get_from_cycle_value(&curr_command_addr, byte_code, interpreter);
                int32_t upper_bound = get_from_cycle_value(&curr_command_addr, byte_code, interpreter);
                int32_t step = get_from_cycle_value(&curr_command_addr, byte_code, interpreter);
                int32_t counter = (upper_bound - lower_bound) / step;
                byte_code[++curr_command_addr] = counter;
                break;
            }
            case FINT: {
                // can't meet
                break;
            }
            case FVAR: {
                // can't meet
                break;
            }
            case JT: {
                struct var var = data_stack_pop(interpreter->data_stack);
                if (var.type != BOOL) {
                    // in condition was call of func that returns not bool value
                }

                if (var.value == 0) {
                    curr_command_addr += (byte_code[curr_command_addr + 1] - 1);
                } else {
                    curr_command_addr++;
                }

                break;
            }
            case CALL: {
                ret_stack_push(curr_command_addr + 2, interpreter->ret_stack);
                curr_command_addr = byte_code[curr_command_addr + 1];

                interpreter->var_map_map->num_of_entries++; // var_map for func created

                uint8_t num_of_args = byte_code[++curr_command_addr];
                struct var vars[num_of_args];

                for (uint32_t i = num_of_args; i > 0; i--) {
                    enum byte_code_commands req_type = byte_code[curr_command_addr + i];
                    struct var var = data_stack_pop(interpreter->data_stack);
                    if (req_type != var.type) {
                        // different types
                    }
                    vars[i - 1] = var;
                }

                for (uint32_t i = 0; i < num_of_args; i++) {
                    var_map_push(vars[i].value, vars[i].type, interpreter->var_map_map);
                }

                break;
            }
            case LIT: {
                enum byte_code_commands type = byte_code[++curr_command_addr];
                int32_t value = byte_code[++curr_command_addr];
                data_stack_push(value, type, interpreter->data_stack);
                break;
            }
            case VAR: { // in this case value is the index of var
                data_stack_push(byte_code[++curr_command_addr], VAR, interpreter->data_stack);
                break;
            }
            case OFC: {
                break;
            }
            case RLIT: {
                enum byte_code_commands type = byte_code[++curr_command_addr];
                int32_t value = byte_code[++curr_command_addr];
                data_stack_push(value, type, interpreter->data_stack);
                break;
            }
            case RVAR: {
                struct var var = var_map_get(byte_code[++curr_command_addr],
                                             interpreter->var_map_map->num_of_entries,
                                             interpreter->var_map_map);
                data_stack_push(var.value, var.type, interpreter->data_stack);
                break;
            }
            case ROFC: {
                break;
            }
            case BOOL: {
                var_map_push(BOOL, NULL_BOOL_VALUE, interpreter->var_map_map);
                break;
            }
            case INT: {
                var_map_push(INT, NULL_INT_VALUE, interpreter->var_map_map);
                break;
            }
            case FLT: {
                var_map_push(FLT, NULL_FLT_VALUE, interpreter->var_map_map);
                break;
            }
            case STR: {
                var_map_push(STR, NULL_STR_VALUE, interpreter->var_map_map);
                break;
            }
            case PTR: {
                var_map_push(PTR, NULL_PTR_VALUE, interpreter->var_map_map);
                break;
            }
            case VOID: {
                // can't meet
                break;
            }
                // itmova core
            case PRINT: {

            }
            case ASSIGN: {
                struct var new_data = data_stack_pop(interpreter->data_stack); // assigning value
                struct var var = data_stack_pop(interpreter->data_stack); // var to assign value to

                if (var.type != VAR) { // if passing arg through var, then its type = VAR
                    // wrong argument
                    // here should have been name of var
                }

                struct var old_data = var_map_get(var.value, // var.value = var index
                                                  interpreter->var_map_map->num_of_entries,
                                                  interpreter->var_map_map);

                if (old_data.type != new_data.type) {
                    // different types
                }

                if (old_data.type == PTR) {
                    heap_dec_num_of_links(old_data.value, interpreter->heap);
                    heap_inc_num_of_links(new_data.value, interpreter->heap);
                }

                var_map_set(new_data.value,
                            new_data.type,
                            var.value,
                            interpreter->var_map_map->num_of_entries,
                            interpreter->var_map_map);

                break;
            }
            case GET_DATA: {
                struct var ptr = data_stack_pop(interpreter->data_stack); // ptr.value = ptr index in var_map

                if (ptr.type != VAR) {
                    // wrong argument
                    // here should have been name of ptr
                }

                int32_t data_addr = var_map_get(ptr.value,
                                                interpreter->var_map_map->num_of_entries,
                                                interpreter->var_map_map).value;

                struct var data = get_by_addr(data_addr, interpreter->heap, interpreter->var_map_map);

                data_stack_push(data.value, data.type, interpreter->data_stack);

                break;
            }
            case SET_DATA: {
                struct var data = data_stack_pop(interpreter->data_stack);
                struct var ptr = data_stack_pop(interpreter->data_stack);

                if (ptr.type != PTR) {
                    // not a ptr
                }

                struct var var = get_by_addr(ptr.value, interpreter->heap, interpreter->var_map_map);

                if (var.type != data.type) {
                    // different types
                }

                set_by_addr(ptr.value, data.value, data.type, interpreter->heap, interpreter->var_map_map);

                break;
            }
            case GET_ADDR: {
                struct var var = data_stack_pop(interpreter->data_stack); // var.value = var index in var_map

                if (var.type != VAR) {
                    // wrong argument
                    // here should have been name of var
                }

                int32_t addr = 1 << 15;
                addr += (interpreter->var_map_map->num_of_entries - 1);
                addr = addr << 16;
                addr += var.value;

                int32_t var_index = var.value;

                enum byte_code_commands type = var_map_get(var_index,
                                                           interpreter->var_map_map->num_of_entries - 1,
                                                           interpreter->var_map_map).type;

                data_stack_push(addr, type, interpreter->data_stack);

                break;
            }
            case MALLOC: {
                data_stack_push(interpreter->heap->next_free_entry, PTR, interpreter->data_stack);
            }
            case AND: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != BOOL || arg1.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                if (arg1.value == 1 && arg2.value == 1) {
                    data_stack_push(1, BOOL, interpreter->data_stack);
                } else {
                    data_stack_push(0, BOOL, interpreter->data_stack);
                }

                break;
            }
            case OR: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != BOOL || arg1.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                if (arg1.value == 1 || arg2.value == 1) {
                    data_stack_push(1, BOOL, interpreter->data_stack);
                } else {
                    data_stack_push(0, BOOL, interpreter->data_stack);
                }

                break;
            }
            case NOT: {
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg1.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                if (arg1.value == 0) {
                    data_stack_push(1, BOOL, interpreter->data_stack);
                } else {
                    data_stack_push(0, BOOL, interpreter->data_stack);
                }

                break;
            }
            case CONCAT: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != STR || arg1.type != STR) {
                    // wrong argument, expected STR
                }

                int32_t second_str_size = byte_code[arg2.value];
                int32_t first_str_size = byte_code[arg1.value];
                int32_t concat_str_size = second_str_size + first_str_size;

                uint32_t concat_str_start_addr = byte_code_size;

                byte_code[byte_code_size++] = concat_str_size;

                for (size_t i = 0; i < first_str_size; i++) {
                    byte_code[byte_code_size++] = byte_code[arg1.value + i];
                }

                for (size_t i = 0; i < second_str_size; i++) {
                    byte_code[byte_code_size++] = byte_code[arg1.value + i];
                }

                data_stack_push(concat_str_start_addr, STR, interpreter->data_stack);

                break;
            }
            case SUBSTR: {
                struct var arg3 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg3.type != INT || arg2.type != INT || arg1.type != STR) {
                    // wrong argument
                }

                int32_t str_size = byte_code[arg1.value];
                int32_t str_start = arg1.value + 1;
                int32_t new_str_size = arg3.value;
                uint32_t new_str_start_addr = byte_code_size;
                int32_t substr_pos_start = arg2.value;

                if (new_str_size <= 0) {
                    // size less or equal than 0
                    break;
                }

                byte_code[byte_code_size++] = new_str_size;

                for (size_t i = 0; i < new_str_size; i++) {
                    byte_code[byte_code_size++] = byte_code[arg1.value + arg2.value + i];
                }

                data_stack_push(new_str_start_addr, STR, interpreter->data_stack);

                break;
            }
            case LIKE: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != STR || arg1.type != STR) {
                    // wrong argument, expected STR
                }

                int32_t second_str_size = byte_code[arg2.value];
                int32_t first_str_size = byte_code[arg1.value];

                int32_t second_str_start = arg2.value + 1;
                int32_t first_str_start = arg1.value + 1;

                int32_t curr_pos_in_second_str = 0;

                int32_t entry_like = -1;

                while (curr_pos_in_second_str < second_str_size) {
                    if (byte_code[second_str_start + curr_pos_in_second_str] == byte_code[first_str_start]) {
                        for (size_t i = 0; i < first_str_size; i++) {
                            int32_t curr_str2_symbol = byte_code[second_str_start + curr_pos_in_second_str + i];
                            int32_t curr_str1_symbol = byte_code[first_str_start + i];

                            if (curr_str1_symbol == curr_str2_symbol) {
                                if (i == first_str_size - 1) {
                                    entry_like = i;
                                }
                            } else {
                                break;
                            }
                        }
                    }

                    curr_pos_in_second_str++;
                }

                data_stack_push(entry_like, INT, interpreter->data_stack);

                break;
            }
            case LENGTH: {
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg1.type != STR) {
                    // wrong argument, expected STR
                }

                data_stack_push(byte_code[arg1.value], INT, interpreter->data_stack);

                break;
            }
            case ABS: {
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                if (arg1.type == INT) {
                    int32_t num = arg1.value;
                    if (num < 0) {
                        num = -num;
                    }
                    data_stack_push(num, INT, interpreter->data_stack);
                }

                if (arg1.type == FLT) {
                    float num = int_to_float(arg1.value);
                    if (num < 0) {
                        num = -num;
                    }
                    data_stack_push(float_to_int(num), FLT, interpreter->data_stack);
                }

                break;
            }
            case INC: {
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                if (arg1.type == INT) {
                    data_stack_push(arg1.value + 1, INT, interpreter->data_stack);
                }

                if (arg1.type == FLT) {
                    float f = int_to_float(arg1.value);

                    f++;

                    data_stack_push(float_to_int(f), FLT, interpreter->data_stack);
                }

                break;
            }
            case DEC: {
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                if (arg1.type == INT) {
                    data_stack_push(arg1.value - 1, INT, interpreter->data_stack);
                }

                if (arg1.type == FLT) {
                    float f = int_to_float(arg1.value);

                    f--;

                    data_stack_push(float_to_int(f), FLT, interpreter->data_stack);
                }

                break;
            }
            case POW: {
                struct var arg_power = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg_num = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg_power.type != INT && arg_power.type != FLT) {
                    // wrong argument
                }

                if (arg_num.type != INT && arg_power.type != FLT) {
                    // wrong argument
                }

                float power = int_to_float(arg_power.value);
                float num = int_to_float(arg_num.value);

                if (power < 1 && power > 0 && num < 0) {
                    // arithmetic exception
                }

                float res = powf(num, power);

                data_stack_push(float_to_int(res), FLT, interpreter->data_stack);

                break;
            }
            case SUM: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float sum = int_to_float(arg1_value) + int_to_float(arg2_value);
                    data_stack_push(float_to_int(sum), FLT, interpreter->data_stack);
                } else if (arg1.type == FLT) {
                    float sum = int_to_float(arg1_value) + (float) arg2_value;
                    data_stack_push(float_to_int(sum), FLT, interpreter->data_stack);
                } else if (arg2.type == FLT) {
                    float sum = (float) arg1_value + int_to_float(arg2_value);
                    data_stack_push(float_to_int(sum), FLT, interpreter->data_stack);
                } else {
                    int32_t sum = arg2_value + arg1_value;
                    data_stack_push(sum, INT, interpreter->data_stack);
                }

                break;
            }
            case SUB: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float sub = int_to_float(arg1_value) - int_to_float(arg2_value);
                    data_stack_push(float_to_int(sub), FLT, interpreter->data_stack);
                } else if (arg1.type == FLT) {
                    float sub = int_to_float(arg1_value) - (float) arg2_value;
                    data_stack_push(float_to_int(sub), FLT, interpreter->data_stack);
                } else if (arg2.type == FLT) {
                    float sub = (float) arg1_value - int_to_float(arg2_value);
                    data_stack_push(float_to_int(sub), FLT, interpreter->data_stack);
                } else {
                    int32_t sub = arg2_value - arg1_value;
                    data_stack_push(sub, INT, interpreter->data_stack);
                }

                break;
            }
            case MUL: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float mul = int_to_float(arg1_value) * int_to_float(arg2_value);
                    data_stack_push(float_to_int(mul), FLT, interpreter->data_stack);
                } else if (arg1.type == FLT) {
                    float mul = int_to_float(arg1_value) * (float) arg2_value;
                    data_stack_push(float_to_int(mul), FLT, interpreter->data_stack);
                } else if (arg2.type == FLT) {
                    float mul = (float) arg1_value * int_to_float(arg2_value);
                    data_stack_push(float_to_int(mul), FLT, interpreter->data_stack);
                } else {
                    int32_t mul = arg2_value * arg1_value;
                    data_stack_push(mul, INT, interpreter->data_stack);
                }

                break;
            }
            case DIV: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float numerator = int_to_float(arg1_value);
                    float denominator = int_to_float(arg2_value);

                    if (denominator == 0) {
                        // division by 0
                    }

                    float div = numerator / denominator;

                    data_stack_push(float_to_int(div), FLT, interpreter->data_stack);
                } else if (arg1.type == FLT) {
                    float numerator = int_to_float(arg1_value);
                    float denominator = (float) arg2_value;

                    if (denominator == 0) {
                        // division by 0
                    }

                    float div = numerator / denominator;

                    data_stack_push(float_to_int(div), FLT, interpreter->data_stack);
                } else if (arg2.type == FLT) {
                    float numerator = (float) arg1_value;
                    float denominator = int_to_float(arg2_value);

                    if (denominator == 0) {
                        // division by 0
                    }

                    float div = numerator / denominator;

                    data_stack_push(float_to_int(div), FLT, interpreter->data_stack);
                } else {
                    int numerator = arg1_value;
                    int denominator = arg2_value;

                    if (denominator == 0) {
                        // division by 0
                    }

                    int32_t div = numerator / denominator;

                    data_stack_push(div, INT, interpreter->data_stack);
                }

                break;
            }
            case MOD: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float numerator = int_to_float(arg1_value);
                    float denominator = int_to_float(arg2_value);

                    if (denominator == 0) {
                        // division by 0
                    }

                    float mod = fmodf(numerator, denominator);

                    data_stack_push(float_to_int(mod), FLT, interpreter->data_stack);
                } else if (arg1.type == FLT) {
                    float numerator = int_to_float(arg1_value);
                    float denominator = (float) arg2_value;

                    if (denominator == 0) {
                        // division by 0
                    }

                    float mod = fmodf(numerator, denominator);

                    data_stack_push(float_to_int(mod), FLT, interpreter->data_stack);
                } else if (arg2.type == FLT) {
                    float numerator = (float) arg1_value;
                    float denominator = int_to_float(arg2_value);

                    if (denominator == 0) {
                        // division by 0
                    }

                    float mod = fmodf(numerator, denominator);

                    data_stack_push(float_to_int(mod), FLT, interpreter->data_stack);
                } else {
                    int32_t numerator = arg1_value;
                    int32_t denominator = arg2_value;

                    if (denominator == 0) {
                        // division by 0
                    }

                    int32_t mod = numerator % denominator;

                    data_stack_push(mod, INT, interpreter->data_stack);
                }

                break;
            }
            case LESS: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    int32_t less = int_to_float(arg1_value) < int_to_float(arg2_value);
                    data_stack_push(less, FLT, interpreter->data_stack);
                } else if (arg1.type == FLT) {
                    int32_t less = int_to_float(arg1_value) < (float) arg2_value;
                    data_stack_push(less, FLT, interpreter->data_stack);
                } else if (arg2.type == FLT) {
                    int32_t less = (float) arg1_value < int_to_float(arg2_value);
                    data_stack_push(less, FLT, interpreter->data_stack);
                } else {
                    int32_t less = arg2_value < arg1_value;
                    data_stack_push(less, INT, interpreter->data_stack);
                }

                break;
            }
            case GREATER: {
                struct var arg2 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);
                struct var arg1 = get_data_from_data_stack_top(interpreter->data_stack, interpreter->var_map_map);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    int32_t greater = int_to_float(arg1_value) > int_to_float(arg2_value);
                    data_stack_push(greater, FLT, interpreter->data_stack);
                } else if (arg1.type == FLT) {
                    int32_t greater = int_to_float(arg1_value) > (float) arg2_value;
                    data_stack_push(greater, FLT, interpreter->data_stack);
                } else if (arg2.type == FLT) {
                    int32_t greater = (float) arg1_value > int_to_float(arg2_value);
                    data_stack_push(greater, FLT, interpreter->data_stack);
                } else {
                    int32_t greater = arg2_value > arg1_value;
                    data_stack_push(greater, INT, interpreter->data_stack);
                }

                break;
            }
            case PI: {

            }
            case E: {

            }
            case RANDOM_STRING: {

            }
            case RANDOM_BOOL : {

            }
            case RANDOM_FLOAT: {

            }
            case RANDOM_INT : {

            }
            case MAIN: {
                // TODO create var map for main func
                break;
            }
            default: {
                // error
            }
        }

        curr_command_addr++;
    }

    *byte_code_size_ptr = byte_code_size;
}


int main() {
    struct var data_stack_data[DATA_STACK_SIZE];
    uint32_t ret_stack_data[RET_STACK_SIZE];
    struct var var_map_data[VAR_MAP_SIZE];
    struct var_map var_map_map_data[RET_STACK_SIZE];
    struct heap_entry heap_data[HEAP_SIZE];


    for (uint32_t i = 0; i < DATA_STACK_SIZE; i++) {
        data_stack_data[i] = NULL_DATA_STACK_ENTRY;
    }

    for (uint32_t i = 0; i < RET_STACK_SIZE; i++) {
        ret_stack_data[i] = NULL_RET_STACK_ENTRY;
    }

    for (uint32_t i = 0; i < VAR_MAP_SIZE; i++) {
        var_map_data[i] = NULL_VAR_MAP_ENTRY;
    }

    for (uint32_t i = 0; i < RET_STACK_SIZE; i++) {
        var_map_map_data[i].data = var_map_data;
    }

    for (uint32_t i = 0; i < HEAP_SIZE; i++) {
        heap_data[i] = NULL_HEAP_ENTRY;
    }


    struct data_stack my_data_stack = {.num_of_entries = 0, .data = data_stack_data};
    struct ret_stack my_ret_stack = {.num_of_entries = 0, .data = ret_stack_data};
    struct var_map_map my_var_map_map = {.num_of_entries = 1, .data = var_map_map_data};
    struct heap my_heap = {.num_of_entries = 0, .data = heap_data, .next_free_entry = 0};
    struct interpreter my_interpreter = {
            .data_stack = &my_data_stack,
            .ret_stack = &my_ret_stack,
            .var_map_map = &my_var_map_map,
            .heap = &my_heap
    };

    uint32_t main_program_start = 21;
    uint32_t byte_code_size = 42;

    int32_t byte_code[MAX_BYTE_CODE_SIZE] = {
            201,
            2,
            203,
            203,
            10,
            11,
            1,
            11,
            10,
            11,
            1,
            0,
            110,
            201,
            10,
            700,
            2,
            -6,
            3,
            999,
            110,
            203,
            31,
            110,
            203,
            38,
            100,
            0,
            112,
            700,
            0,
            6,
            113,
            119,
            101,
            114,
            116,
            121,
            3,
            1,
            2,
            3
    };

//    interpret(&my_interpreter, byte_code, main_program_start);

    return 0;
}
