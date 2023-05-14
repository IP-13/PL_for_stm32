#include "structs.h"


int main() {
    int32_t data_stack_data[MAX_DATA_STACK_SIZE];
    int32_t ret_stack_data[MAX_RET_STACK_SIZE];
    struct var_map var_map[MAX_RET_STACK_SIZE];
    struct heap_entry heap_data[MAX_HEAP_SIZE];



    struct stack data_stack = {.size = 0, .data = data_stack_data};
    struct stack ret_stack = {.size = 0, .data = ret_stack_data};
    struct var_map_map var_map_map = {.size = 0, .data = var_map};
    struct heap heap = {.size = 0, .data = heap_data};

    

    return 0;
}
