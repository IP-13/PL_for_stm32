#include "interpreter.h"


int main() {
    struct var data_stack_data[DATA_STACK_SIZE];

    for (size_t i = 0; i < DATA_STACK_SIZE; i++) {
        struct var data_stack_entry = {.value = NULL};
        data_stack_data[i] = data_stack_entry;
    }

    int32_t ret_stack_data[RET_STACK_SIZE];

    for (size_t i = 0; i < RET_STACK_SIZE; i++) {
        ret_stack_data[i] = 0;
    }

    struct var var_map_data[VAR_MAP_SIZE];

    for (size_t i = 0; i < VAR_MAP_SIZE; i++) {
        struct var var = {.value = NULL};
        var_map_data[i] = var;
    }

    struct var_map var_map_map_data[RET_STACK_SIZE];

    for (size_t i = 0; i < RET_STACK_SIZE; i++) {
        var_map_map_data[i].data = var_map_data;
    }

    struct heap_entry heap_data[HEAP_SIZE];

    for (size_t i = 0; i < HEAP_SIZE; i++) {
        struct heap_entry heap_entry = {.value = NULL, .num_of_links = 0};
        heap_data[i] = heap_entry;
    }

    struct data_stack data_stack = {.size = 0, .data = data_stack_data};
    struct ret_stack ret_stack = {.size = 0, .data = ret_stack_data};
    struct var_map_map var_map_map = {.size = 0, .data = var_map_map_data};
    struct heap heap = {.size = 0, .data = heap_data};
    struct interpreter interpreter = {
            .data_stack = &data_stack,
            .ret_stack = &ret_stack,
            .var_map_map = &var_map_map,
            .heap = &heap
    };

    int32_t program_start = 21;

    int32_t byte_code[] = {201,
                           2,
                           203,
                           203,
                           201,
                           111,
                           2,
                           111,
                           0,
                           728,
                           112,
                           111,
                           1,
                           728,
                           112,
                           757,
                           112,
                           701,
                           121,
                           2,
                           3,
                           999,
                           110,
                           203,
                           33,
                           110,
                           203,
                           40,
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
                           3};


    return 0;
}
