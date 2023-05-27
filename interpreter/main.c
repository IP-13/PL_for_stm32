#include <inttypes.h>
#include <stdio.h>
#include <stdlib.h>

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
enum byte_code {
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
    SUM = 757,
    SUB = 758,
    MUL = 759,
    DIV = 760,
    LESS = 762,
    GREATER = 763,
    MAIN = 999
};


// structs
struct var {
    int32_t value;
    enum byte_code type;
};


struct data_stack {
    int32_t num_of_entries;
    struct var *data;
};


struct ret_stack {
    int32_t num_of_entries;
    int32_t *data;
};


struct var_map {
    int32_t num_of_entries;
    struct var *data;
};


struct var_map_map {
    int32_t num_of_entries;
    struct var_map *data;
};


struct heap_entry {
    int32_t value; // null by default
    enum byte_code type; // NOT_YET_DEFINED by default
    int32_t num_of_links;
};


struct heap {
    int32_t num_of_entries;
    struct heap_entry *data; // index - addr, value - heap_entry
    int32_t next_free_entry; // index of the next free addr in the data.
};


struct interpreter {
    struct data_stack *data_stack;
    struct ret_stack *ret_stack;
    struct var_map_map *vmm;
    struct heap *heap;
};


// NULL VALUE STRUCTS
const struct var NULL_DATA_STACK_ENTRY = {0, NOT_YET_DEFINED_TYPE};
const int32_t NULL_RET_STACK_ENTRY = 0;
const struct var NULL_VAR_MAP_ENTRY = {0, NOT_YET_DEFINED_TYPE};
const struct heap_entry NULL_HEAP_ENTRY = {0, NOT_YET_DEFINED_TYPE, 0};


// FUNCTIONS
void print_error_and_exit(char *message, int32_t line) {
    printf("%s at line %"PRId32"", message, line);
    exit(1);
}


struct var data_stack_pop(struct data_stack *data_stack) {
    if (data_stack->num_of_entries == 0) {
        // data_stack underflow
    }

    struct var top = data_stack->data[--data_stack->num_of_entries];
    data_stack->data[data_stack->num_of_entries] = NULL_DATA_STACK_ENTRY;
    return top;
}


void data_stack_push(struct var var, struct data_stack *data_stack) {
    if (data_stack->num_of_entries == DATA_STACK_SIZE) {
        // data_stack overflow
    }

    data_stack->data[data_stack->num_of_entries++] = var;
}


int32_t ret_stack_pop(struct ret_stack *ret_stack) {
    if (ret_stack->num_of_entries == 0) {
        // ret_stack underflow
    }

    int32_t top = ret_stack->data[--ret_stack->num_of_entries];
    ret_stack->data[ret_stack->num_of_entries] = NULL_RET_STACK_ENTRY;
    return top;
}


void ret_stack_push(int32_t top, struct ret_stack *ret_stack) {
    if (ret_stack->num_of_entries == RET_STACK_SIZE) {
        // ret_stack overflow
    }

    ret_stack->data[ret_stack->num_of_entries++] = top;
}


struct var var_map_get(int32_t var_index, int32_t map_index, struct var_map_map *vmm) {
    if (map_index >= vmm->num_of_entries) {
        printf("Map index out of bounds\n");
        exit(1);
        // index out of bounds
    }

    if (var_index >= vmm->data[map_index].num_of_entries) {
        printf("Var index out of bounds\n");
        exit(1);
        // index out of bounds
    }

    return vmm->data[map_index].data[var_index];
}


void var_map_set(struct var var, int32_t var_index, int32_t map_index, struct var_map_map *vmm) {
    if (map_index >= vmm->num_of_entries) {
        printf("Map index out of bounds\n");
        exit(1);
    }

    if (var_index >= vmm->data[map_index].num_of_entries) {
        printf("Var index out of bounds\n");
        exit(1);
    }

    struct var curr_data_at_var_index = vmm->data[map_index].data[var_index];

    if (var.type != curr_data_at_var_index.type) {
        printf("Different types\n");
        exit(1);
    }

    vmm->data[map_index].data[var_index].value = var.value;
}


void var_map_push(struct var var, struct var_map_map *vmm) {
    int32_t index_to_push = vmm->data[vmm->num_of_entries - 1].num_of_entries;

    if (index_to_push == VAR_MAP_SIZE) {
        // stack overflow
    }

    vmm->data[vmm->num_of_entries - 1].data[index_to_push] = var;

    vmm->data[vmm->num_of_entries - 1].num_of_entries++;
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


struct heap_entry heap_get(int32_t addr, struct heap *heap) {
    if (addr >= heap->num_of_entries) {
        // index out of bounds
    }

    return heap->data[addr];
}


void heap_set(int32_t addr, struct var var, struct heap *heap) {
    if (heap->data[addr].type != NOT_YET_DEFINED_TYPE && var.type != heap->data[addr].type) {
        // different types
    }

    heap->data[addr].value = var.value;
}


int32_t heap_malloc(struct heap *heap) { // return allocated addr
    if (heap->next_free_entry == HEAP_SIZE) {
        heap_collect_garbage(heap);
    }

    if (heap->next_free_entry == HEAP_SIZE) {
        // heap overflow
    }

    heap->num_of_entries++;

    int32_t old_next_free_entry = heap->next_free_entry;

    heap->next_free_entry = HEAP_SIZE;

    for (size_t i = old_next_free_entry + 1; i < HEAP_SIZE; i++) {
        if (heap->data[i].num_of_links == 0) {
            heap->next_free_entry = i;
            break;
        }
    }

    return old_next_free_entry;
}


void heap_dec_num_of_links(int32_t addr, struct heap *heap) {
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


void heap_inc_num_of_links(int32_t addr, struct heap *heap) {
    if (addr >> 31 != 0) {
        // reference to a non-heap addr
        // heap addr starts with 0
        // with 1 starts var_map addr
    }

    heap->data[addr].num_of_links++;
}


void remove_var_map(struct var_map_map *vmm, struct heap *heap) { // removes (nullifies) top var_map
    vmm->num_of_entries--;

    for (size_t i = 0; i < vmm->data[vmm->num_of_entries].num_of_entries; i++) {
        struct var var = vmm->data[vmm->num_of_entries].data[i];

        if (var.type == PTR) {
            heap_dec_num_of_links(var.value, heap);
        }

        vmm->data[vmm->num_of_entries].data[i] = NULL_VAR_MAP_ENTRY;
    }

    vmm->data[vmm->num_of_entries].num_of_entries = 0;
}


int32_t get_from_cycle_value(int32_t *curr_command_addr, const int32_t *byte_code, struct var_map_map *vmm) {
    int32_t value;

    if (byte_code[++(*curr_command_addr)] == FINT) {
        value = byte_code[++(*curr_command_addr)];
    } else {
        int32_t var_index = byte_code[++(*curr_command_addr)];
        int32_t map_index = vmm->num_of_entries - 1;
        value = var_map_get(var_index, map_index, vmm).value;
    }

    return value;
}


int32_t get_int_bits(int32_t bits, int32_t from, int32_t to) { // 'from' and 'to' include
    return bits << (31 - from) >> (to + 31 - from);
}


struct var get_by_addr(int32_t addr, struct heap *heap, struct var_map_map *vmm) {
    int32_t is_in_var_map = get_int_bits(addr, 31, 31);

    if (!is_in_var_map) {
        struct heap_entry heap_entry = heap_get(addr, heap);
        return (struct var) {heap_entry.value, heap_entry.type};
    } else {
        int32_t map_index = get_int_bits(addr, 30, 16);
        int32_t var_index = get_int_bits(addr, 15, 0);
        return var_map_get(var_index, map_index, vmm);
    }
}


void set_by_addr(int32_t addr, struct var var, struct heap *heap, struct var_map_map *vmm) {
    int32_t is_in_var_map = get_int_bits(addr, 31, 31);

    if (!is_in_var_map) {
        heap_set(addr, var, heap);
    } else {
        int32_t map_index = get_int_bits(addr, 30, 16);
        int32_t var_index = get_int_bits(addr, 15, 0);
        var_map_set(var, var_index, map_index, vmm);
    }
}


struct var get_data_from_data_stack_top(struct data_stack *data_stack, struct var_map_map *vmm) {
    struct var var = data_stack_pop(data_stack);

    if (var.type == VAR) { // if var.type == VAR, then var.value = var index in var_map
        var = var_map_get(var.value, vmm->num_of_entries - 1, vmm);
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


void interpret(struct interpreter *interpreter, int32_t *byte_code, int32_t start, int32_t *byte_code_size_ptr) {
    int32_t byte_code_size = *byte_code_size_ptr;
    int32_t curr_addr = start;

    struct data_stack *data_stack = interpreter->data_stack;
    struct ret_stack *ret_stack = interpreter->ret_stack;
    struct var_map_map *vmm = interpreter->vmm;
    struct heap *heap = interpreter->heap;

    while (byte_code[curr_addr] != EXIT) {
        switch (byte_code[curr_addr]) {
            case EXIT: {
                return;
            }
            case JMP: {
                curr_addr = byte_code[curr_addr + 1];
                break;
            }
            case JDEC: { // auto-decrement jump
                curr_addr++; // now curr_addr points to offset relative to current addr
                int32_t temp = curr_addr; // in case if counter == 0
                curr_addr += byte_code[curr_addr];
                int32_t counter = --byte_code[curr_addr];
                if (!counter) {
                    curr_addr = temp;
                }
                break;
            }
            case JRET: {
                curr_addr = ret_stack_pop(ret_stack);
                remove_var_map(vmm, heap);
                break;
            }
            case LOOP: {
                int32_t lower_bound = get_from_cycle_value(&curr_addr, byte_code, vmm);
                int32_t upper_bound = get_from_cycle_value(&curr_addr, byte_code, vmm);
                int32_t step = get_from_cycle_value(&curr_addr, byte_code, vmm);
                int32_t counter = (upper_bound - lower_bound) / step;
                byte_code[++curr_addr] = counter;
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
                struct var var = data_stack_pop(data_stack);

                if (var.type != BOOL) {
                    // in condition was call of func that returns not bool value
                }

                if (var.value == 0) {
                    curr_addr += (byte_code[curr_addr + 1] - 1);
                } else {
                    curr_addr++;
                }

                break;
            }
            case CALL: {
                ret_stack_push(curr_addr + 2, ret_stack);

                curr_addr = byte_code[curr_addr + 1];

                vmm->num_of_entries++; // var_map for func created

                int32_t num_of_args = byte_code[++curr_addr];
                struct var vars[num_of_args];

                for (int32_t i = num_of_args; i > 0; i--) {
                    enum byte_code req_type = byte_code[curr_addr + i];
                    struct var var = data_stack_pop(data_stack);
                    if (req_type != var.type) {
                        // different types
                    }
                    vars[i - 1] = var;
                }

                for (int32_t i = 0; i < num_of_args; i++) {
                    struct var var;

                    if (vars[i].type == VAR) {
                        var = var_map_get(vars[i].value, vmm->num_of_entries - 2, vmm);
                    } else {
                        var = vars[i];
                    }

                    var_map_push(var, vmm);
                }

                curr_addr += num_of_args;

                break;
            }
            case LIT: {
                enum byte_code type = byte_code[++curr_addr];
                int32_t value = byte_code[++curr_addr];
                data_stack_push((struct var) {value, type}, data_stack);
                break;
            }
            case VAR: {
                data_stack_push((struct var) {byte_code[++curr_addr], VAR}, data_stack);
                break;
            }
            case OFC: {
                // can't meet
                break;
            }
            case RLIT: {
                enum byte_code type = byte_code[++curr_addr];
                int32_t value = byte_code[++curr_addr];
                data_stack_push((struct var) {value, type}, data_stack);
                break;
            }
            case RVAR: {
                struct var var = var_map_get(byte_code[++curr_addr], vmm->num_of_entries - 1, vmm);
                data_stack_push(var, data_stack);
                break;
            }
            case ROFC: {
                break;
            }
            case BOOL: {
                var_map_push((struct var) {NULL_BOOL_VALUE, BOOL}, vmm);
                break;
            }
            case INT: {
                var_map_push((struct var) {NULL_INT_VALUE, INT}, vmm);
                break;
            }
            case FLT: {
                var_map_push((struct var) {NULL_FLT_VALUE, FLT}, vmm);
                break;
            }
            case STR: {
                var_map_push((struct var) {NULL_STR_VALUE, STR}, vmm);
                break;
            }
            case PTR: {
                var_map_push((struct var) {NULL_PTR_VALUE, PTR}, vmm);
                break;
            }
            case VOID: {
                // can't meet
                break;
            }
                // core library
            case PRINT: {
                struct var data = data_stack_pop(data_stack);

                switch (data.type) {
                    case BOOL: {
                        if (data.value) {
                            printf("TRUE");
                        } else {
                            printf("FALSE");
                        }
                        break;
                    }
                    case INT: {
                        printf("%"PRId32"", data.value);
                        break;
                    }
                    case FLT : {
                        printf("%f", int_to_float(data.value));
                        break;
                    }
                    case STR: {
                        int32_t str_size = byte_code[data.value];
                        int32_t str_start = data.value + 1;
                        for (size_t i = 0; i < str_size; i++) {
                            printf("%c", byte_code[str_start + i]);
                        }

                        break;
                    }
                    case PTR: {
                        printf("%"PRId32"", data.value);
                        break;
                    }
                    default: {
                        // wrong argument
                    }
                }

                break;
            }
            case ASSIGN: {
                struct var new_data = data_stack_pop(data_stack); // assigning value
                struct var var = data_stack_pop(data_stack); // var to assign value to

                if (var.type != VAR) { // if passing arg through var, then its type = VAR, value - index in var_map
                    // wrong argument
                }

                struct var old_data = var_map_get(var.value, vmm->num_of_entries - 1, vmm);

                if (old_data.type != NOT_YET_DEFINED_TYPE || old_data.type != new_data.type) {
                    // different types
                }

                if (old_data.type == PTR) {
                    heap_dec_num_of_links(old_data.value, heap);
                    heap_inc_num_of_links(new_data.value, heap);
                }

                var_map_set(new_data, var.value, vmm->num_of_entries - 1, vmm);

                break;
            }
            case GET_DATA: {
                struct var ptr = data_stack_pop(data_stack); // ptr.value = ptr index in var_map

                if (ptr.type != VAR) {
                    // wrong argument
                    // here should have been name of ptr
                }

                int32_t data_addr = var_map_get(ptr.value, vmm->num_of_entries - 1, vmm).value;

                struct var data = get_by_addr(data_addr, heap, vmm);

                data_stack_push(data, data_stack);

                break;
            }
            case SET_DATA: {
                struct var data = data_stack_pop(data_stack);
                struct var ptr = data_stack_pop(data_stack);

                if (ptr.type != PTR) {
                    // not a ptr
                }

                struct var var = get_by_addr(ptr.value, heap, vmm);

                if (var.type != data.type) {
                    // different types
                }

                set_by_addr(ptr.value, data, heap, vmm);

                break;
            }
            case GET_ADDR: {
                struct var var = data_stack_pop(data_stack);

                if (var.type != VAR) {
                    // wrong argument
                    // here should have been name of var
                }

                int32_t addr = 1 << 15;
                addr += (vmm->num_of_entries - 1);
                addr = addr << 16;
                addr += var.value;

                int32_t var_index = var.value;

                enum byte_code type = var_map_get(var_index, vmm->num_of_entries - 1, vmm).type;

                data_stack_push((struct var) {addr, type}, data_stack);

                break;
            }
            case MALLOC: {
                data_stack_push((struct var) {heap->next_free_entry, PTR}, data_stack);
                break;
            }
            case AND: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                if (arg1.value == 1 && arg2.value == 1) {
                    data_stack_push((struct var) {1, BOOL}, data_stack);
                } else {
                    data_stack_push((struct var) {0, BOOL}, data_stack);
                }

                break;
            }
            case OR: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                if (arg1.value == 1 || arg2.value == 1) {
                    data_stack_push((struct var) {1, BOOL}, data_stack);
                } else {
                    data_stack_push((struct var) {0, BOOL}, data_stack);
                }

                break;
            }
            case NOT: {
                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != BOOL) {
                    // wrong arguments, expected BOOL
                }

                if (arg1.value == 0) {
                    data_stack_push((struct var) {1, BOOL}, data_stack);
                } else {
                    data_stack_push((struct var) {0, BOOL}, data_stack);
                }

                break;
            }
            case CONCAT: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != STR) {
                    // wrong argument, expected STR
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != STR) {
                    // wrong argument, expected STR
                }

                int32_t second_str_size = byte_code[arg2.value];
                int32_t first_str_size = byte_code[arg1.value];
                int32_t concat_str_size = second_str_size + first_str_size;
                int32_t concat_str_start_addr = byte_code_size;

                byte_code[byte_code_size++] = concat_str_size;

                for (size_t i = 0; i < first_str_size; i++) {
                    byte_code[byte_code_size++] = byte_code[arg1.value + i];
                }

                for (size_t i = 0; i < second_str_size; i++) {
                    byte_code[byte_code_size++] = byte_code[arg1.value + i];
                }

                data_stack_push((struct var) {concat_str_start_addr, STR}, data_stack);

                break;
            }
            case SUBSTR: {
                struct var arg3 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg3.type != INT) {
                    // wrong argument
                }

                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != INT) {
                    // wrong argument
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != STR) {
                    // wrong argument
                }

                int32_t str_size = byte_code[arg1.value];
                int32_t str_start = arg1.value + 1;
                int32_t new_str_size = arg3.value;
                int32_t new_str_start_addr = byte_code_size;
                int32_t substr_pos_start = arg2.value;

                if (str_size < new_str_size) {
                    // error
                }

                if (new_str_size <= 0) {
                    // size less or equal than 0
                    break;
                }

                byte_code[byte_code_size++] = new_str_size;

                for (size_t i = 0; i < new_str_size; i++) {
                    byte_code[byte_code_size++] = byte_code[str_start + substr_pos_start + i];
                }

                data_stack_push((struct var) {new_str_start_addr, STR}, data_stack);

                break;
            }
            case LIKE: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != STR) {
                    // wrong argument, expected STR
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != STR) {
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
                        for (int32_t i = 0; i < first_str_size; i++) {
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

                data_stack_push((struct var) {entry_like, INT}, data_stack);

                break;
            }
            case LENGTH: {
                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != STR) {
                    // wrong argument, expected STR
                }

                data_stack_push((struct var) {byte_code[arg1.value], INT}, data_stack);

                break;
            }
            case ABS: {
                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                struct var var;

                if (arg1.type == INT) {
                    if (arg1.value < 0) {
                        var.value = -arg1.value;
                    } else {
                        var.value = arg1.value;
                    }
                    var.type = INT;
                }

                if (arg1.type == FLT) {
                    float num = int_to_float(arg1.value);

                    if (num < 0) {
                        var.value = float_to_int(-num);
                    } else {
                        var.value = float_to_int(num);
                    }
                    var.type = FLT;
                }

                data_stack_push(var, data_stack);

                break;
            }
            case INC: {
                struct var var = data_stack_pop(data_stack);

                if (var.type != VAR) {
                    // wrong argument, expected variable
                }

                struct var data = var_map_get(var.value, vmm->num_of_entries - 1, vmm);

                if (data.type != INT && data.type != FLT) {
                    // wrong argument
                }

                if (data.type == INT) {
                    var_map_set((struct var) {data.value + 1, INT}, var.value, vmm->num_of_entries - 1, vmm);
                }

                if (data.type == FLT) {
                    float f = int_to_float(data.value);

                    f++;

                    var_map_set((struct var) {float_to_int(f), FLT}, var.value, vmm->num_of_entries - 1, vmm);
                }

                break;
            }
            case DEC: {
                struct var var = data_stack_pop(data_stack);

                if (var.type != VAR) {
                    // wrong argument, expected variable
                }

                struct var data = var_map_get(var.value, vmm->num_of_entries - 1, vmm);

                if (data.type != INT && data.type != FLT) {
                    // wrong argument
                }

                if (data.type == INT) {
                    var_map_set((struct var) {data.value - 1, INT}, var.value, vmm->num_of_entries, vmm);
                }

                if (data.type == FLT) {
                    float f = int_to_float(data.value);

                    f--;

                    var_map_set((struct var) {float_to_int(f), FLT}, var.value, vmm->num_of_entries, vmm);
                }

                break;
            }
            case SUM: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float sum = int_to_float(arg1_value) + int_to_float(arg2_value);
                    data_stack_push((struct var) {float_to_int(sum), FLT}, data_stack);
                } else if (arg1.type == FLT) {
                    float sum = int_to_float(arg1_value) + (float) arg2_value;
                    data_stack_push((struct var) {float_to_int(sum), FLT}, data_stack);
                } else if (arg2.type == FLT) {
                    float sum = (float) arg1_value + int_to_float(arg2_value);
                    data_stack_push((struct var) {float_to_int(sum), FLT}, data_stack);
                } else {
                    int32_t sum = arg2_value + arg1_value;
                    data_stack_push((struct var) {sum, INT}, data_stack);
                }

                break;
            }
            case SUB: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float sub = int_to_float(arg1_value) - int_to_float(arg2_value);
                    data_stack_push((struct var) {float_to_int(sub), FLT}, data_stack);
                } else if (arg1.type == FLT) {
                    float sub = int_to_float(arg1_value) - (float) arg2_value;
                    data_stack_push((struct var) {float_to_int(sub), FLT}, data_stack);
                } else if (arg2.type == FLT) {
                    float sub = (float) arg1_value - int_to_float(arg2_value);
                    data_stack_push((struct var) {float_to_int(sub), FLT}, data_stack);
                } else {
                    int32_t sub = arg2_value - arg1_value;
                    data_stack_push((struct var) {sub, INT}, data_stack);
                }

                break;
            }
            case MUL: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                if (arg1.type == FLT && arg2.type == FLT) {
                    float mul = int_to_float(arg1_value) * int_to_float(arg2_value);
                    data_stack_push((struct var) {float_to_int(mul), FLT}, data_stack);
                } else if (arg1.type == FLT) {
                    float mul = int_to_float(arg1_value) * (float) arg2_value;
                    data_stack_push((struct var) {float_to_int(mul), FLT}, data_stack);
                } else if (arg2.type == FLT) {
                    float mul = (float) arg1_value * int_to_float(arg2_value);
                    data_stack_push((struct var) {float_to_int(mul), FLT}, data_stack);
                } else {
                    int32_t mul = arg2_value * arg1_value;
                    data_stack_push((struct var) {mul, INT}, data_stack);
                }

                break;
            }
            case DIV: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }


                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

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

                    data_stack_push((struct var) {float_to_int(div), FLT}, data_stack);
                } else if (arg1.type == FLT) {
                    float numerator = int_to_float(arg1_value);
                    float denominator = (float) arg2_value;

                    if (denominator == 0) {
                        // division by 0
                    }

                    float div = numerator / denominator;

                    data_stack_push((struct var) {float_to_int(div), FLT}, data_stack);
                } else if (arg2.type == FLT) {
                    float numerator = (float) arg1_value;
                    float denominator = int_to_float(arg2_value);

                    if (denominator == 0) {
                        // division by 0
                    }

                    float div = numerator / denominator;

                    data_stack_push((struct var) {float_to_int(div), FLT}, data_stack);
                } else {
                    int numerator = arg1_value;
                    int denominator = arg2_value;

                    if (denominator == 0) {
                        // division by 0
                    }

                    int32_t div = numerator / denominator;

                    data_stack_push((struct var) {div, INT}, data_stack);
                }

                break;
            }
            case LESS: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                int32_t less;

                if (arg1.type == FLT && arg2.type == FLT) {
                    less = int_to_float(arg1_value) < int_to_float(arg2_value);
                } else if (arg1.type == FLT) {
                    less = int_to_float(arg1_value) < (float) arg2_value;
                } else if (arg2.type == FLT) {
                    less = (float) arg1_value < int_to_float(arg2_value);
                } else {
                    less = arg1_value < arg2_value;
                }

                data_stack_push((struct var) {less, BOOL}, data_stack);

                break;
            }
            case GREATER: {
                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg2.type != INT && arg2.type != FLT) {
                    // wrong argument
                }

                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);

                if (arg1.type != INT && arg1.type != FLT) {
                    // wrong argument
                }

                int32_t arg2_value = arg2.value;
                int32_t arg1_value = arg1.value;

                int32_t greater;

                if (arg1.type == FLT && arg2.type == FLT) {
                    greater = int_to_float(arg1_value) > int_to_float(arg2_value);
                } else if (arg1.type == FLT) {
                    greater = int_to_float(arg1_value) > (float) arg2_value;
                } else if (arg2.type == FLT) {
                    greater = (float) arg1_value > int_to_float(arg2_value);
                } else {
                    greater = arg1_value > arg2_value;
                }

                data_stack_push((struct var) {greater, BOOL}, data_stack);

                break;
            }
            case MAIN: {
                vmm->num_of_entries++;
                break;
            }
            default: {
                // error
            }
        }

        curr_addr++;
    }

    *byte_code_size_ptr = byte_code_size;
}


int main() {
    struct var data_stack_data[DATA_STACK_SIZE];
    int32_t ret_stack_data[RET_STACK_SIZE];
    struct var var_map_data[VAR_MAP_SIZE];
    struct var_map vmm_data[RET_STACK_SIZE];
    struct heap_entry heap_data[HEAP_SIZE];

    for (size_t i = 0; i < DATA_STACK_SIZE; i++) {
        data_stack_data[i] = NULL_DATA_STACK_ENTRY;
    }

    for (size_t i = 0; i < RET_STACK_SIZE; i++) {
        ret_stack_data[i] = NULL_RET_STACK_ENTRY;
    }

    for (size_t i = 0; i < VAR_MAP_SIZE; i++) {
        var_map_data[i] = NULL_VAR_MAP_ENTRY;
    }

    for (size_t i = 0; i < RET_STACK_SIZE; i++) {
        vmm_data[i].data = var_map_data;
    }

    for (size_t i = 0; i < HEAP_SIZE; i++) {
        heap_data[i] = NULL_HEAP_ENTRY;
    }

    struct data_stack my_data_stack = {.num_of_entries = 0, .data = data_stack_data};
    struct ret_stack my_ret_stack = {.num_of_entries = 0, .data = ret_stack_data};
    struct var_map_map my_vmm = {.num_of_entries = 0, .data = vmm_data};
    struct heap my_heap = {.num_of_entries = 0, .data = heap_data, .next_free_entry = 0};
    struct interpreter my_interpreter = {
            .data_stack = &my_data_stack,
            .ret_stack = &my_ret_stack,
            .vmm = &my_vmm,
            .heap = &my_heap
    };

    int32_t main_program_start = 45;
    int32_t byte_code_size = 60;

    int32_t byte_code[MAX_BYTE_CODE_SIZE] = {
            201,
            1,
            201,
            201,
            111,
            1,
            110,
            201,
            1,
            701,
            201,
            111,
            2,
            110,
            201,
            1,
            701,
            111,
            0,
            736,
            10,
            11,
            1,
            12,
            0,
            11,
            1,
            0,
            111,
            2,
            111,
            2,
            111,
            1,
            759,
            112,
            701,
            111,
            1,
            736,
            2,
            -14,
            121,
            2,
            3,
            999,
            201,
            111,
            0,
            110,
            201,
            5,
            701,
            111,
            0,
            100,
            0,
            112,
            700,
            0
    };

    interpret(&my_interpreter, byte_code, main_program_start, &byte_code_size);

    return 0;
}
