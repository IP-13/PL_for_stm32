package com.ip13.compiler;

import com.ip13.Exceptions.UnknownFuncCallException;
import com.ip13.Exceptions.UnknownTypeException;
import com.ip13.antlr.plClabBaseListener;
import com.ip13.antlr.plClabLexer;
import com.ip13.antlr.plClabParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Objects.isNull;

public class SuperClass {
    private static final List<String> byteCode = new ArrayList<>();
    private static final List<FuncInfo> funcList = new ArrayList<>();
    private static Map<String, VarInfo> varMap = new HashMap<>();
    private static final List<String> stringLiteralsStorage = new ArrayList<>();
    private static int entryPoint = 0; //
    private static final Stack<Integer> fromCycleStack = new Stack<>();
    private static final Stack<Integer> ifOperatorStack = new Stack<>();


    public static void generateByteCode(String program) {
        plClabLexer lexer = new plClabLexer(CharStreams.fromString(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        plClabParser parser = new plClabParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new plClabBaseListener(), tree);
    }

    public static void optimizeStringLiterals() {
        int byteCodeSize = byteCode.size();

        for (int i = 0; i < byteCodeSize; i++) {
            if (byteCode.get(i).equals(ByteCodeCommands.lit.toString()) ||
                    byteCode.get(i).equals(ByteCodeCommands.rlit.toString())) {                     // check for literal
                if (byteCode.get(i + 1).equals(ByteCodeCommands.STR.toString())) {                  // check if it's a string literal
                    String s = stringLiteralsStorage.get(Integer.parseInt(byteCode.get(i + 2))); // gets string literal
                    int len = s.length() - 2;                                                       // subtract 2 because string has quotes at both sides
                    byteCode.set(i + 2, String.valueOf(byteCode.size()));                         // sets addr of string start at the end of bytecode
                    byteCode.add(String.valueOf(len));                                            // adds string literal size
                    for (int j = 0; j < len; j++) {                                                 // adds string chars
                        byteCode.add(String.valueOf((int) s.charAt(j + 1)));
                    }
                }
            }
        }
    }


    public static List<Integer> translateByteCodeToNumberFormat() {
        List<Integer> byteCodeInNumberFormat = new ArrayList<>();
        byteCode.forEach(command -> {
            if (command.equals("counter")) {
                byteCodeInNumberFormat.add(0);
            } else {
                int numberFormat = ByteCodeCommands.getCommandInNumberFormat(command);

                if (numberFormat != -1) {
                    byteCodeInNumberFormat.add(numberFormat);
                } else {
                    try {
                        byteCodeInNumberFormat.add(Integer.parseInt(command));
                    } catch (NumberFormatException ex) {
                        try {
                            float f = Float.parseFloat(command);
                            int i = Float.floatToIntBits(f);
                            byteCodeInNumberFormat.add(i);
                        } catch (NumberFormatException ignored) {
                            command.chars().forEach(byteCodeInNumberFormat::add);
                        }
                    }
                }
            }
        });

        return byteCodeInNumberFormat;
    }


    public static int getEntryPoint() {
        return entryPoint;
    }


    public static int getByteCodeSize() {
        return byteCode.size();
    }


    public static void showByteCode(boolean showCommandsNumber) {
        if (showCommandsNumber) {
            for (int i = 0; i < byteCode.size(); i++) {
                System.out.println(i + ":\t" + byteCode.get(i));
            }
        } else {
            byteCode.forEach(System.out::println);
        }
    }


    public static void showByteCodeInNumberFormat(boolean showCommandsNumber) {
        List<Integer> byteCodeInNumberFormat = translateByteCodeToNumberFormat();

        if (showCommandsNumber) {
            for (int i = 0; i < byteCodeInNumberFormat.size(); i++) {
                System.out.println(i + ":\t" + byteCodeInNumberFormat.get(i));
            }
        } else {
            byteCodeInNumberFormat.forEach(System.out::println);
        }
    }


    public static void generateFileForCLabExecution() {
        File cLabFile = new File("YourProgram" + LocalDateTime.now() + ".plCLab");
        try {
            boolean isCreated = cLabFile.createNewFile();
            if (!isCreated) {
                throw new IOException("Unable to create file");
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(cLabFile)) {
            writer.println("#include \"main.h\"\n" +
                    "#include \"iwdg.h\"\n" +
                    "#include \"gpio.h\"\n" +
                    "#include \"trace.h\"\n" +
                    "#include <inttypes.h>\n" +
                    "\n" +
                    "#define DATA_STACK_SIZE 32\n" +
                    "#define RET_STACK_SIZE 32\n" +
                    "#define VAR_MAP_SIZE 32\n" +
                    "#define HEAP_SIZE 32\n" +
                    "\n" +
                    "#define NULL_BOOL_VALUE 0\n" +
                    "#define NULL_INT_VALUE 0\n" +
                    "#define NULL_FLT_VALUE 0\n" +
                    "#define NULL_PTR_VALUE HEAP_SIZE\n" +
                    "#define NULL_STR_VALUE (-1)\n" +
                    "#define MAX_BYTE_CODE_SIZE 1000\n" +
                    "\n" +
                    "\n" +
                    "void Error_Handler(void) {\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "enum byte_code {\n" +
                    "    EXIT = 0,\n" +
                    "    JMP = 1,\n" +
                    "    JDEC = 2,\n" +
                    "    JRET = 3,\n" +
                    "    LOOP = 10,\n" +
                    "    FINT = 11,\n" +
                    "    FVAR = 12,\n" +
                    "    JT = 20,\n" +
                    "    CALL = 100,\n" +
                    "    LIT = 110,\n" +
                    "    VAR = 111,\n" +
                    "    OFC = 112,\n" +
                    "    RLIT = 120,\n" +
                    "    RVAR = 121,\n" +
                    "    ROFC = 122,\n" +
                    "    BOOL = 200,\n" +
                    "    INT = 201,\n" +
                    "    FLT = 202,\n" +
                    "    STR = 203,\n" +
                    "    PTR = 204,\n" +
                    "    VOID = 205,\n" +
                    "    NOT_YET_DEFINED_TYPE = 206,\n" +
                    "    PRINTF = 700,\n" +
                    "    ASSIGN = 701,\n" +
                    "    GET_DATA = 710,\n" +
                    "    SET_DATA = 711,\n" +
                    "    GET_ADDR = 712,\n" +
                    "    MALLOC = 713,\n" +
                    "    AND = 720,\n" +
                    "    OR = 721,\n" +
                    "    NOT = 722,\n" +
                    "    CONCAT = 725,\n" +
                    "    SUBSTR = 726,\n" +
                    "    LIKE = 727,\n" +
                    "    LENGTH = 728,\n" +
                    "    ABS = 735,\n" +
                    "    INC = 736,\n" +
                    "    DEC = 737,\n" +
                    "    SUM = 757,\n" +
                    "    SUB = 758,\n" +
                    "    MUL = 759,\n" +
                    "    DIV = 760,\n" +
                    "    LESS = 762,\n" +
                    "    GREATER = 763,\n" +
                    "    MAIN = 999\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "struct var {\n" +
                    "    int32_t value;\n" +
                    "    enum byte_code type;\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "struct data_stack {\n" +
                    "    int32_t num_of_entries;\n" +
                    "    struct var *data;\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "struct ret_stack {\n" +
                    "    int32_t num_of_entries;\n" +
                    "    int32_t *data;\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "struct var_map_map {\n" +
                    "    int32_t num_of_maps;\n" +
                    "    int32_t *num_of_vars_in_maps;\n" +
                    "    struct var *data;\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "struct heap_entry {\n" +
                    "    int32_t value;\n" +
                    "    enum byte_code type;\n" +
                    "    int32_t num_of_links;\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "struct heap {\n" +
                    "    int32_t num_of_entries;\n" +
                    "    struct heap_entry *data;\n" +
                    "    int32_t next_free_entry;\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "struct interpreter {\n" +
                    "    struct data_stack *data_stack;\n" +
                    "    struct ret_stack *ret_stack;\n" +
                    "    struct var_map_map *vmm;\n" +
                    "    struct heap *heap;\n" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "const struct var NULL_DATA_STACK_ENTRY = {0, NOT_YET_DEFINED_TYPE};\n" +
                    "const int32_t NULL_RET_STACK_ENTRY = 0;\n" +
                    "const struct var NULL_VAR_MAP_ENTRY = {0, NOT_YET_DEFINED_TYPE};\n" +
                    "const struct heap_entry NULL_HEAP_ENTRY = {0, NOT_YET_DEFINED_TYPE, 0};\n" +
                    "\n" +
                    "\n" +
                    "struct var data_stack_pop(struct data_stack *data_stack) {\n" +
                    "    if (data_stack->num_of_entries == 0) {\n" +
                    "        // data_stack underflow\n" +
                    "    }\n" +
                    "\n" +
                    "    struct var top = data_stack->data[--data_stack->num_of_entries];\n" +
                    "    data_stack->data[data_stack->num_of_entries] = NULL_DATA_STACK_ENTRY;\n" +
                    "    return top;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void data_stack_push(struct var var, struct data_stack *data_stack) {\n" +
                    "    if (data_stack->num_of_entries == DATA_STACK_SIZE) {\n" +
                    "        // data_stack overflow\n" +
                    "    }\n" +
                    "\n" +
                    "    data_stack->data[data_stack->num_of_entries++] = var;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "int32_t ret_stack_pop(struct ret_stack *ret_stack) {\n" +
                    "    if (ret_stack->num_of_entries == 0) {\n" +
                    "        // ret_stack underflow\n" +
                    "    }\n" +
                    "\n" +
                    "    int32_t top = ret_stack->data[--ret_stack->num_of_entries];\n" +
                    "    ret_stack->data[ret_stack->num_of_entries] = NULL_RET_STACK_ENTRY;\n" +
                    "    return top;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void ret_stack_push(int32_t top, struct ret_stack *ret_stack) {\n" +
                    "    if (ret_stack->num_of_entries == RET_STACK_SIZE) {\n" +
                    "        // ret_stack overflow\n" +
                    "    }\n" +
                    "\n" +
                    "    ret_stack->data[ret_stack->num_of_entries++] = top;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "struct var var_map_get(int32_t var_index, int32_t map_index, struct var_map_map *vmm) {\n" +
                    "    if (map_index >= vmm->num_of_maps) {\n" +
                    "        // index out of bounds\n" +
                    "    }\n" +
                    "\n" +
                    "    if (var_index >= vmm->num_of_vars_in_maps[map_index]) {\n" +
                    "        // index out of bounds\n" +
                    "    }\n" +
                    "\n" +
                    "    return vmm->data[map_index * VAR_MAP_SIZE + var_index];\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void var_map_set(struct var var, int32_t var_index, int32_t map_index, struct var_map_map *vmm) {\n" +
                    "    if (map_index >= vmm->num_of_maps) {\n" +
                    "        // index out of bounds\n" +
                    "    }\n" +
                    "\n" +
                    "    if (var_index >= vmm->num_of_vars_in_maps[map_index]) {\n" +
                    "        // index out of bounds\n" +
                    "    }\n" +
                    "\n" +
                    "    struct var curr_data_at_var_index = vmm->data[map_index * VAR_MAP_SIZE + var_index];\n" +
                    "\n" +
                    "    if (var.type != curr_data_at_var_index.type) {\n" +
                    "        // different types\n" +
                    "    }\n" +
                    "\n" +
                    "    vmm->data[map_index * VAR_MAP_SIZE + var_index].value = var.value;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void var_map_push(struct var var, struct var_map_map *vmm) {\n" +
                    "    int32_t index_to_push = vmm->num_of_vars_in_maps[vmm->num_of_maps - 1];\n" +
                    "\n" +
                    "    if (index_to_push == VAR_MAP_SIZE) {\n" +
                    "        // var_map overflow\n" +
                    "    }\n" +
                    "\n" +
                    "    vmm->data[(vmm->num_of_maps - 1) * VAR_MAP_SIZE + index_to_push] = var;\n" +
                    "\n" +
                    "    vmm->num_of_vars_in_maps[vmm->num_of_maps - 1]++;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void heap_collect_garbage(struct heap *heap) {\n" +
                    "    for (int32_t i = 0; i < heap->num_of_entries; i++) {\n" +
                    "        if (heap->data[i].num_of_links == 0) {\n" +
                    "            heap->data[i] = NULL_HEAP_ENTRY;\n" +
                    "            heap->num_of_entries--;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    for (int32_t i = 0; i < HEAP_SIZE; i++) {\n" +
                    "        if (heap->data[i].num_of_links == 0) {\n" +
                    "            heap->next_free_entry = i;\n" +
                    "            return;\n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "struct heap_entry heap_get(int32_t addr, struct heap *heap) {\n" +
                    "    if (addr >= heap->num_of_entries) {\n" +
                    "        // index out of bounds\n" +
                    "    }\n" +
                    "\n" +
                    "    return heap->data[addr];\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void heap_set(int32_t addr, struct var var, struct heap *heap) {\n" +
                    "    if (heap->data[addr].type != NOT_YET_DEFINED_TYPE && var.type != heap->data[addr].type) {\n" +
                    "        // different types\n" +
                    "    }\n" +
                    "\n" +
                    "    heap->data[addr].value = var.value;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "int32_t heap_malloc(struct heap *heap) { // return allocated addr\n" +
                    "    if (heap->next_free_entry == HEAP_SIZE) {\n" +
                    "        heap_collect_garbage(heap);\n" +
                    "    }\n" +
                    "\n" +
                    "    if (heap->next_free_entry == HEAP_SIZE) {\n" +
                    "        // heap overflow\n" +
                    "    }\n" +
                    "\n" +
                    "    heap->num_of_entries++;\n" +
                    "\n" +
                    "    int32_t old_next_free_entry = heap->next_free_entry;\n" +
                    "\n" +
                    "    heap->next_free_entry = HEAP_SIZE;\n" +
                    "\n" +
                    "    for (int32_t i = old_next_free_entry + 1; i < HEAP_SIZE; i++) {\n" +
                    "        if (heap->data[i].num_of_links == 0) {\n" +
                    "            heap->next_free_entry = i;\n" +
                    "            break;\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    return old_next_free_entry;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void heap_dec_num_of_links(int32_t addr, struct heap *heap) {\n" +
                    "    if (addr >> 31 != 0) {\n" +
                    "        // reference to a non-heap addr\n" +
                    "        // heap addr starts with 0\n" +
                    "        // with 1 starts var_map addr\n" +
                    "    }\n" +
                    "\n" +
                    "    if (heap->data[addr].num_of_links == 0) {\n" +
                    "        return; // memory is free already\n" +
                    "    }\n" +
                    "\n" +
                    "    heap->data[addr].num_of_links--;\n" +
                    "\n" +
                    "    if (heap->data[addr].num_of_links == 0) {\n" +
                    "        heap->data[addr] = NULL_HEAP_ENTRY;\n" +
                    "        heap->num_of_entries--;\n" +
                    "        if (heap->next_free_entry > addr) {\n" +
                    "            heap->next_free_entry = addr;\n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void heap_inc_num_of_links(int32_t addr, struct heap *heap) {\n" +
                    "    if (addr >> 31 != 0) {\n" +
                    "        // reference to a non-heap addr\n" +
                    "    }\n" +
                    "\n" +
                    "    heap->data[addr].num_of_links++;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void remove_var_map(struct var_map_map *vmm, struct heap *heap) { // removes (nullifies) top var_map\n" +
                    "    vmm->num_of_maps--;\n" +
                    "\n" +
                    "    int32_t start_index = vmm->num_of_maps * VAR_MAP_SIZE;\n" +
                    "\n" +
                    "    for (int32_t i = start_index; i < start_index + vmm->num_of_vars_in_maps[vmm->num_of_maps]; i++) {\n" +
                    "        struct var var = vmm->data[i];\n" +
                    "\n" +
                    "        if (var.type == PTR) {\n" +
                    "            heap_dec_num_of_links(var.value, heap);\n" +
                    "        }\n" +
                    "\n" +
                    "        vmm->data[i] = NULL_VAR_MAP_ENTRY;\n" +
                    "    }\n" +
                    "\n" +
                    "    vmm->num_of_vars_in_maps[vmm->num_of_maps] = 0;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "int32_t get_from_cycle_value(int32_t *curr_command_addr, const int32_t *byte_code, struct var_map_map *vmm) {\n" +
                    "    int32_t value;\n" +
                    "\n" +
                    "    if (byte_code[++(*curr_command_addr)] == FINT) {\n" +
                    "        value = byte_code[++(*curr_command_addr)];\n" +
                    "    } else {\n" +
                    "        int32_t var_index = byte_code[++(*curr_command_addr)];\n" +
                    "        int32_t map_index = vmm->num_of_maps - 1;\n" +
                    "        value = var_map_get(var_index, map_index, vmm).value;\n" +
                    "    }\n" +
                    "\n" +
                    "    return value;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "int32_t get_int_bits(int32_t bits, int32_t from, int32_t to) { // 'from' and 'to' include\n" +
                    "    return bits << (31 - from) >> (to + 31 - from);\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "struct var get_by_addr(int32_t addr, struct heap *heap, struct var_map_map *vmm) {\n" +
                    "    int32_t is_in_var_map = get_int_bits(addr, 31, 31);\n" +
                    "\n" +
                    "    if (!is_in_var_map) {\n" +
                    "        struct heap_entry heap_entry = heap_get(addr, heap);\n" +
                    "        return (struct var) {heap_entry.value, heap_entry.type};\n" +
                    "    } else {\n" +
                    "        int32_t map_index = get_int_bits(addr, 30, 16);\n" +
                    "        int32_t var_index = get_int_bits(addr, 15, 0);\n" +
                    "        return var_map_get(var_index, map_index, vmm);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void set_by_addr(int32_t addr, struct var var, struct heap *heap, struct var_map_map *vmm) {\n" +
                    "    int32_t is_in_var_map = get_int_bits(addr, 31, 31);\n" +
                    "\n" +
                    "    if (!is_in_var_map) {\n" +
                    "        heap_set(addr, var, heap);\n" +
                    "    } else {\n" +
                    "        int32_t map_index = get_int_bits(addr, 30, 16);\n" +
                    "        int32_t var_index = get_int_bits(addr, 15, 0);\n" +
                    "        var_map_set(var, var_index, map_index, vmm);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "struct var get_data_from_data_stack_top(struct data_stack *data_stack, struct var_map_map *vmm) {\n" +
                    "    struct var var = data_stack_pop(data_stack);\n" +
                    "\n" +
                    "    if (var.type == VAR) { // if var.type == VAR, then var.value = var index in var_map\n" +
                    "        var = var_map_get(var.value, vmm->num_of_maps - 1, vmm);\n" +
                    "    }\n" +
                    "\n" +
                    "    return var;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "float int_to_float(int32_t num) {\n" +
                    "    void *p = &num;\n" +
                    "    float f = *(float *) p;\n" +
                    "    return f;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "int32_t float_to_int(float f) {\n" +
                    "    void *p = &f;\n" +
                    "    int32_t num = *(int32_t *) p;\n" +
                    "    return num;\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "void interpret(struct interpreter *interpreter, int32_t *byte_code, int32_t start, int32_t *byte_code_size_ptr) {\n" +
                    "    int32_t byte_code_size = *byte_code_size_ptr;\n" +
                    "    int32_t curr_addr = start;\n" +
                    "\n" +
                    "    struct data_stack *data_stack = interpreter->data_stack;\n" +
                    "    struct ret_stack *ret_stack = interpreter->ret_stack;\n" +
                    "    struct var_map_map *vmm = interpreter->vmm;\n" +
                    "    struct heap *heap = interpreter->heap;\n" +
                    "\n" +
                    "    while (byte_code[curr_addr] != EXIT) {\n" +
                    "        switch (byte_code[curr_addr]) {\n" +
                    "            case EXIT: {\n" +
                    "                return;\n" +
                    "            }\n" +
                    "            case JMP: {\n" +
                    "                curr_addr = byte_code[curr_addr + 1];\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case JDEC: { // auto-decrement jump\n" +
                    "                curr_addr++; // now curr_addr points to offset relative to current addr\n" +
                    "                int32_t temp = curr_addr; // in case if counter == 0\n" +
                    "                curr_addr += byte_code[curr_addr];\n" +
                    "                int32_t counter = --byte_code[curr_addr];\n" +
                    "                if (!counter) {\n" +
                    "                    curr_addr = temp;\n" +
                    "                }\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case JRET: {\n" +
                    "                curr_addr = ret_stack_pop(ret_stack);\n" +
                    "                remove_var_map(vmm, heap);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case LOOP: {\n" +
                    "                int32_t lower_bound = get_from_cycle_value(&curr_addr, byte_code, vmm);\n" +
                    "                int32_t upper_bound = get_from_cycle_value(&curr_addr, byte_code, vmm);\n" +
                    "                int32_t step = get_from_cycle_value(&curr_addr, byte_code, vmm);\n" +
                    "                int32_t counter = (upper_bound - lower_bound) / step;\n" +
                    "                byte_code[++curr_addr] = counter;\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case FINT: {\n" +
                    "                // can't meet\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case FVAR: {\n" +
                    "                // can't meet\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case JT: {\n" +
                    "                struct var var = data_stack_pop(data_stack);\n" +
                    "\n" +
                    "                if (var.type != BOOL) {\n" +
                    "                    // in condition was call of func that returns not bool value\n" +
                    "                }\n" +
                    "\n" +
                    "                if (var.value == 0) {\n" +
                    "                    curr_addr += (byte_code[curr_addr + 1]);\n" +
                    "                } else {\n" +
                    "                    curr_addr++;\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case CALL: {\n" +
                    "                ret_stack_push(curr_addr + 2, ret_stack);\n" +
                    "\n" +
                    "                curr_addr = byte_code[curr_addr + 1];\n" +
                    "\n" +
                    "                vmm->num_of_maps++; // var_map for func created\n" +
                    "\n" +
                    "                int32_t num_of_args = byte_code[++curr_addr];\n" +
                    "                struct var vars[num_of_args];\n" +
                    "\n" +
                    "                for (int32_t i = num_of_args; i > 0; i--) {\n" +
                    "                    enum byte_code req_type = byte_code[curr_addr + i];\n" +
                    "                    struct var var = data_stack_pop(data_stack);\n" +
                    "                    if (req_type != var.type) {\n" +
                    "                        // different types\n" +
                    "                    }\n" +
                    "                    vars[i - 1] = var;\n" +
                    "                }\n" +
                    "\n" +
                    "                for (int32_t i = 0; i < num_of_args; i++) {\n" +
                    "                    struct var var;\n" +
                    "\n" +
                    "                    if (vars[i].type == VAR) {\n" +
                    "                        var = var_map_get(vars[i].value, vmm->num_of_maps - 2, vmm);\n" +
                    "                    } else {\n" +
                    "                        var = vars[i];\n" +
                    "                    }\n" +
                    "\n" +
                    "                    var_map_push(var, vmm);\n" +
                    "                }\n" +
                    "\n" +
                    "                curr_addr += num_of_args;\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case LIT: {\n" +
                    "                enum byte_code type = byte_code[++curr_addr];\n" +
                    "                int32_t value = byte_code[++curr_addr];\n" +
                    "                data_stack_push((struct var) {value, type}, data_stack);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case VAR: {\n" +
                    "                data_stack_push((struct var) {byte_code[++curr_addr], VAR}, data_stack);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case OFC: {\n" +
                    "                // can't meet\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case RLIT: {\n" +
                    "                enum byte_code type = byte_code[++curr_addr];\n" +
                    "                int32_t value = byte_code[++curr_addr];\n" +
                    "                data_stack_push((struct var) {value, type}, data_stack);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case RVAR: {\n" +
                    "                struct var var = var_map_get(byte_code[++curr_addr], vmm->num_of_maps - 1, vmm);\n" +
                    "                data_stack_push(var, data_stack);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case ROFC: {\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case BOOL: {\n" +
                    "                var_map_push((struct var) {NULL_BOOL_VALUE, BOOL}, vmm);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case INT: {\n" +
                    "                var_map_push((struct var) {NULL_INT_VALUE, INT}, vmm);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case FLT: {\n" +
                    "                var_map_push((struct var) {NULL_FLT_VALUE, FLT}, vmm);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case STR: {\n" +
                    "                var_map_push((struct var) {NULL_STR_VALUE, STR}, vmm);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case PTR: {\n" +
                    "                var_map_push((struct var) {NULL_PTR_VALUE, PTR}, vmm);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case VOID: {\n" +
                    "                // can't meet\n" +
                    "                break;\n" +
                    "            }\n" +
                    "                // core library\n" +
                    "            case PRINTF: {\n" +
                    "                struct var data = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                switch (data.type) {\n" +
                    "                    case BOOL: {\n" +
                    "                        if (data.value) {\n" +
                    "                            SDK_TRACE_Print(\"TRUE\");\n" +
                    "                        } else {\n" +
                    "                            SDK_TRACE_Print(\"FALSE\");\n" +
                    "                        }\n" +
                    "                        break;\n" +
                    "                    }\n" +
                    "                    case INT: {\n" +
                    "                        SDK_TRACE_Print(\"%\"PRId32\"\", data.value);\n" +
                    "                        break;\n" +
                    "                    }\n" +
                    "                    case FLT : {\n" +
                    "                        SDK_TRACE_Print(\"%f\", int_to_float(data.value));\n" +
                    "                        break;\n" +
                    "                    }\n" +
                    "                    case STR: {\n" +
                    "                        int32_t str_size = byte_code[data.value];\n" +
                    "                        int32_t str_start = data.value + 1;\n" +
                    "                        for (int32_t i = 0; i < str_size; i++) {\n" +
                    "                            SDK_TRACE_Print(\"%c\", byte_code[str_start + i]);\n" +
                    "                        }\n" +
                    "\n" +
                    "                        break;\n" +
                    "                    }\n" +
                    "                    case PTR: {\n" +
                    "                        SDK_TRACE_Print(\"%\"PRId32\"\", data.value);\n" +
                    "                        break;\n" +
                    "                    }\n" +
                    "                    default: {\n" +
                    "                        // wrong argument\n" +
                    "                    }\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case ASSIGN: {\n" +
                    "                struct var new_data = data_stack_pop(data_stack); // assigning value\n" +
                    "                struct var var = data_stack_pop(data_stack); // var to assign value to\n" +
                    "\n" +
                    "                if (var.type != VAR) { // if passing arg through var, then its type = VAR, value - index in var_map\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var old_data = var_map_get(var.value, vmm->num_of_maps - 1, vmm);\n" +
                    "\n" +
                    "                if (old_data.type != NOT_YET_DEFINED_TYPE || old_data.type != new_data.type) {\n" +
                    "                    // different types\n" +
                    "                }\n" +
                    "\n" +
                    "                if (old_data.type == PTR) {\n" +
                    "                    heap_dec_num_of_links(old_data.value, heap);\n" +
                    "                    heap_inc_num_of_links(new_data.value, heap);\n" +
                    "                }\n" +
                    "\n" +
                    "                var_map_set(new_data, var.value, vmm->num_of_maps - 1, vmm);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case GET_DATA: {\n" +
                    "                struct var ptr = data_stack_pop(data_stack); // ptr.value = ptr index in var_map\n" +
                    "\n" +
                    "                if (ptr.type != VAR) {\n" +
                    "                    // wrong argument\n" +
                    "                    // here should have been name of ptr\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t data_addr = var_map_get(ptr.value, vmm->num_of_maps - 1, vmm).value;\n" +
                    "\n" +
                    "                struct var data = get_by_addr(data_addr, heap, vmm);\n" +
                    "\n" +
                    "                data_stack_push(data, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case SET_DATA: {\n" +
                    "                struct var data = data_stack_pop(data_stack);\n" +
                    "                struct var ptr = data_stack_pop(data_stack);\n" +
                    "\n" +
                    "                if (ptr.type != PTR) {\n" +
                    "                    // not a ptr\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var var = get_by_addr(ptr.value, heap, vmm);\n" +
                    "\n" +
                    "                if (var.type != data.type) {\n" +
                    "                    // different types\n" +
                    "                }\n" +
                    "\n" +
                    "                set_by_addr(ptr.value, data, heap, vmm);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case GET_ADDR: {\n" +
                    "                struct var var = data_stack_pop(data_stack);\n" +
                    "\n" +
                    "                if (var.type != VAR) {\n" +
                    "                    // wrong argument\n" +
                    "                    // here should have been name of var\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t addr = 1 << 15;\n" +
                    "                addr += (vmm->num_of_maps - 1);\n" +
                    "                addr = addr << 16;\n" +
                    "                addr += var.value;\n" +
                    "\n" +
                    "                int32_t var_index = var.value;\n" +
                    "\n" +
                    "                enum byte_code type = var_map_get(var_index, vmm->num_of_maps - 1, vmm).type;\n" +
                    "\n" +
                    "                data_stack_push((struct var) {addr, type}, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case MALLOC: {\n" +
                    "                data_stack_push((struct var) {heap->next_free_entry, PTR}, data_stack);\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case AND: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != BOOL) {\n" +
                    "                    // wrong arguments, expected BOOL\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != BOOL) {\n" +
                    "                    // wrong arguments, expected BOOL\n" +
                    "                }\n" +
                    "\n" +
                    "                if (arg1.value == 1 && arg2.value == 1) {\n" +
                    "                    data_stack_push((struct var) {1, BOOL}, data_stack);\n" +
                    "                } else {\n" +
                    "                    data_stack_push((struct var) {0, BOOL}, data_stack);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case OR: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != BOOL) {\n" +
                    "                    // wrong arguments, expected BOOL\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != BOOL) {\n" +
                    "                    // wrong arguments, expected BOOL\n" +
                    "                }\n" +
                    "\n" +
                    "                if (arg1.value == 1 || arg2.value == 1) {\n" +
                    "                    data_stack_push((struct var) {1, BOOL}, data_stack);\n" +
                    "                } else {\n" +
                    "                    data_stack_push((struct var) {0, BOOL}, data_stack);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case NOT: {\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != BOOL) {\n" +
                    "                    // wrong arguments, expected BOOL\n" +
                    "                }\n" +
                    "\n" +
                    "                if (arg1.value == 0) {\n" +
                    "                    data_stack_push((struct var) {1, BOOL}, data_stack);\n" +
                    "                } else {\n" +
                    "                    data_stack_push((struct var) {0, BOOL}, data_stack);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case CONCAT: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != STR) {\n" +
                    "                    // wrong argument, expected STR\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != STR) {\n" +
                    "                    // wrong argument, expected STR\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t second_str_size = byte_code[arg2.value];\n" +
                    "                int32_t first_str_size = byte_code[arg1.value];\n" +
                    "                int32_t concat_str_size = second_str_size + first_str_size;\n" +
                    "                int32_t concat_str_start_addr = byte_code_size;\n" +
                    "\n" +
                    "                byte_code[byte_code_size++] = concat_str_size;\n" +
                    "\n" +
                    "                for (int32_t i = 0; i < first_str_size; i++) {\n" +
                    "                    byte_code[byte_code_size++] = byte_code[arg1.value + 1 + i];\n" +
                    "                }\n" +
                    "\n" +
                    "                for (int32_t i = 0; i < second_str_size; i++) {\n" +
                    "                    byte_code[byte_code_size++] = byte_code[arg2.value + 1 + i];\n" +
                    "                }\n" +
                    "\n" +
                    "                data_stack_push((struct var) {concat_str_start_addr, STR}, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case SUBSTR: {\n" +
                    "                struct var arg3 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg3.type != INT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != INT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != STR) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t str_size = byte_code[arg1.value];\n" +
                    "                int32_t str_start = arg1.value + 1;\n" +
                    "                int32_t new_str_size = arg3.value;\n" +
                    "                int32_t new_str_start_addr = byte_code_size;\n" +
                    "                int32_t substr_pos_start = arg2.value;\n" +
                    "\n" +
                    "                if (str_size < new_str_size) {\n" +
                    "                    // error\n" +
                    "                }\n" +
                    "\n" +
                    "                if (new_str_size <= 0) {\n" +
                    "                    // size less or equal than 0\n" +
                    "                    break;\n" +
                    "                }\n" +
                    "\n" +
                    "                byte_code[byte_code_size++] = new_str_size;\n" +
                    "\n" +
                    "                for (int32_t i = 0; i < new_str_size; i++) {\n" +
                    "                    byte_code[byte_code_size++] = byte_code[str_start + substr_pos_start + i];\n" +
                    "                }\n" +
                    "\n" +
                    "                data_stack_push((struct var) {new_str_start_addr, STR}, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case LIKE: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != STR) {\n" +
                    "                    // wrong argument, expected STR\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != STR) {\n" +
                    "                    // wrong argument, expected STR\n" +
                    "                }\n" +
                    "\n" +
                    "\n" +
                    "                int32_t second_str_size = byte_code[arg2.value];\n" +
                    "                int32_t first_str_size = byte_code[arg1.value];\n" +
                    "\n" +
                    "                int32_t second_str_start = arg2.value + 1;\n" +
                    "                int32_t first_str_start = arg1.value + 1;\n" +
                    "\n" +
                    "                int32_t curr_pos_in_second_str = 0;\n" +
                    "\n" +
                    "                int32_t entry_like = -1;\n" +
                    "\n" +
                    "                while (curr_pos_in_second_str < second_str_size) {\n" +
                    "                    if (entry_like != -1) {\n" +
                    "                        break;\n" +
                    "                    }\n" +
                    "                    if (byte_code[second_str_start + curr_pos_in_second_str] == byte_code[first_str_start]) {\n" +
                    "                        entry_like = curr_pos_in_second_str;\n" +
                    "                        for (int32_t i = 0; i < first_str_size; i++) {\n" +
                    "                            int32_t curr_str2_symbol = byte_code[second_str_start + curr_pos_in_second_str + i];\n" +
                    "                            int32_t curr_str1_symbol = byte_code[first_str_start + i];\n" +
                    "\n" +
                    "                            if (curr_str1_symbol != curr_str2_symbol) {\n" +
                    "                                entry_like = -1;\n" +
                    "                                break;\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "\n" +
                    "                    curr_pos_in_second_str++;\n" +
                    "                }\n" +
                    "\n" +
                    "                data_stack_push((struct var) {entry_like, INT}, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case LENGTH: {\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != STR) {\n" +
                    "                    // wrong argument, expected STR\n" +
                    "                }\n" +
                    "\n" +
                    "                data_stack_push((struct var) {byte_code[arg1.value], INT}, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case ABS: {\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != INT && arg1.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var var;\n" +
                    "\n" +
                    "                if (arg1.type == INT) {\n" +
                    "                    if (arg1.value < 0) {\n" +
                    "                        var.value = -arg1.value;\n" +
                    "                    } else {\n" +
                    "                        var.value = arg1.value;\n" +
                    "                    }\n" +
                    "                    var.type = INT;\n" +
                    "                }\n" +
                    "\n" +
                    "                if (arg1.type == FLT) {\n" +
                    "                    float num = int_to_float(arg1.value);\n" +
                    "\n" +
                    "                    if (num < 0) {\n" +
                    "                        var.value = float_to_int(-num);\n" +
                    "                    } else {\n" +
                    "                        var.value = float_to_int(num);\n" +
                    "                    }\n" +
                    "                    var.type = FLT;\n" +
                    "                }\n" +
                    "\n" +
                    "                data_stack_push(var, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case INC: {\n" +
                    "                struct var var = data_stack_pop(data_stack);\n" +
                    "\n" +
                    "                if (var.type != VAR) {\n" +
                    "                    // wrong argument, expected variable\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var data = var_map_get(var.value, vmm->num_of_maps - 1, vmm);\n" +
                    "\n" +
                    "                if (data.type != INT && data.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                if (data.type == INT) {\n" +
                    "                    var_map_set((struct var) {data.value + 1, INT}, var.value, vmm->num_of_maps - 1, vmm);\n" +
                    "                }\n" +
                    "\n" +
                    "                if (data.type == FLT) {\n" +
                    "                    float f = int_to_float(data.value);\n" +
                    "\n" +
                    "                    f++;\n" +
                    "\n" +
                    "                    var_map_set((struct var) {float_to_int(f), FLT}, var.value, vmm->num_of_maps - 1, vmm);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case DEC: {\n" +
                    "                struct var var = data_stack_pop(data_stack);\n" +
                    "\n" +
                    "                if (var.type != VAR) {\n" +
                    "                    // wrong argument, expected variable\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var data = var_map_get(var.value, vmm->num_of_maps - 1, vmm);\n" +
                    "\n" +
                    "                if (data.type != INT && data.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                if (data.type == INT) {\n" +
                    "                    var_map_set((struct var) {data.value - 1, INT}, var.value, vmm->num_of_maps, vmm);\n" +
                    "                }\n" +
                    "\n" +
                    "                if (data.type == FLT) {\n" +
                    "                    float f = int_to_float(data.value);\n" +
                    "\n" +
                    "                    f--;\n" +
                    "\n" +
                    "                    var_map_set((struct var) {float_to_int(f), FLT}, var.value, vmm->num_of_maps, vmm);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case SUM: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != INT && arg2.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != INT && arg1.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t arg2_value = arg2.value;\n" +
                    "                int32_t arg1_value = arg1.value;\n" +
                    "\n" +
                    "                if (arg1.type == FLT && arg2.type == FLT) {\n" +
                    "                    float sum = int_to_float(arg1_value) + int_to_float(arg2_value);\n" +
                    "                    data_stack_push((struct var) {float_to_int(sum), FLT}, data_stack);\n" +
                    "                } else if (arg1.type == FLT) {\n" +
                    "                    float sum = int_to_float(arg1_value) + (float) arg2_value;\n" +
                    "                    data_stack_push((struct var) {float_to_int(sum), FLT}, data_stack);\n" +
                    "                } else if (arg2.type == FLT) {\n" +
                    "                    float sum = (float) arg1_value + int_to_float(arg2_value);\n" +
                    "                    data_stack_push((struct var) {float_to_int(sum), FLT}, data_stack);\n" +
                    "                } else {\n" +
                    "                    int32_t sum = arg1_value + arg2_value;\n" +
                    "                    data_stack_push((struct var) {sum, INT}, data_stack);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case SUB: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != INT && arg2.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != INT && arg1.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t arg2_value = arg2.value;\n" +
                    "                int32_t arg1_value = arg1.value;\n" +
                    "\n" +
                    "                if (arg1.type == FLT && arg2.type == FLT) {\n" +
                    "                    float sub = int_to_float(arg1_value) - int_to_float(arg2_value);\n" +
                    "                    data_stack_push((struct var) {float_to_int(sub), FLT}, data_stack);\n" +
                    "                } else if (arg1.type == FLT) {\n" +
                    "                    float sub = int_to_float(arg1_value) - (float) arg2_value;\n" +
                    "                    data_stack_push((struct var) {float_to_int(sub), FLT}, data_stack);\n" +
                    "                } else if (arg2.type == FLT) {\n" +
                    "                    float sub = (float) arg1_value - int_to_float(arg2_value);\n" +
                    "                    data_stack_push((struct var) {float_to_int(sub), FLT}, data_stack);\n" +
                    "                } else {\n" +
                    "                    int32_t sub = arg1_value - arg2_value;\n" +
                    "                    data_stack_push((struct var) {sub, INT}, data_stack);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case MUL: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != INT && arg2.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != INT && arg1.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t arg2_value = arg2.value;\n" +
                    "                int32_t arg1_value = arg1.value;\n" +
                    "\n" +
                    "                if (arg1.type == FLT && arg2.type == FLT) {\n" +
                    "                    float mul = int_to_float(arg1_value) * int_to_float(arg2_value);\n" +
                    "                    data_stack_push((struct var) {float_to_int(mul), FLT}, data_stack);\n" +
                    "                } else if (arg1.type == FLT) {\n" +
                    "                    float mul = int_to_float(arg1_value) * (float) arg2_value;\n" +
                    "                    data_stack_push((struct var) {float_to_int(mul), FLT}, data_stack);\n" +
                    "                } else if (arg2.type == FLT) {\n" +
                    "                    float mul = (float) arg1_value * int_to_float(arg2_value);\n" +
                    "                    data_stack_push((struct var) {float_to_int(mul), FLT}, data_stack);\n" +
                    "                } else {\n" +
                    "                    int32_t mul = arg1_value * arg2_value;\n" +
                    "                    data_stack_push((struct var) {mul, INT}, data_stack);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case DIV: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != INT && arg2.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != INT && arg1.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t arg2_value = arg2.value;\n" +
                    "                int32_t arg1_value = arg1.value;\n" +
                    "\n" +
                    "                if (arg1.type == FLT && arg2.type == FLT) {\n" +
                    "                    float numerator = int_to_float(arg1_value);\n" +
                    "                    float denominator = int_to_float(arg2_value);\n" +
                    "\n" +
                    "                    if (denominator == 0) {\n" +
                    "                        // division by 0\n" +
                    "                    }\n" +
                    "\n" +
                    "                    float div = numerator / denominator;\n" +
                    "\n" +
                    "                    data_stack_push((struct var) {float_to_int(div), FLT}, data_stack);\n" +
                    "                } else if (arg1.type == FLT) {\n" +
                    "                    float numerator = int_to_float(arg1_value);\n" +
                    "                    float denominator = (float) arg2_value;\n" +
                    "\n" +
                    "                    if (denominator == 0) {\n" +
                    "                        // division by 0\n" +
                    "                    }\n" +
                    "\n" +
                    "                    float div = numerator / denominator;\n" +
                    "\n" +
                    "                    data_stack_push((struct var) {float_to_int(div), FLT}, data_stack);\n" +
                    "                } else if (arg2.type == FLT) {\n" +
                    "                    float numerator = (float) arg1_value;\n" +
                    "                    float denominator = int_to_float(arg2_value);\n" +
                    "\n" +
                    "                    if (denominator == 0) {\n" +
                    "                        // division by 0\n" +
                    "                    }\n" +
                    "\n" +
                    "                    float div = numerator / denominator;\n" +
                    "\n" +
                    "                    data_stack_push((struct var) {float_to_int(div), FLT}, data_stack);\n" +
                    "                } else {\n" +
                    "                    int32_t numerator = arg1_value;\n" +
                    "                    int32_t denominator = arg2_value;\n" +
                    "\n" +
                    "                    if (denominator == 0) {\n" +
                    "                        // division by 0\n" +
                    "                    }\n" +
                    "\n" +
                    "                    int32_t div = numerator / denominator;\n" +
                    "\n" +
                    "                    data_stack_push((struct var) {div, INT}, data_stack);\n" +
                    "                }\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case LESS: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != INT && arg2.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != INT && arg1.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t arg2_value = arg2.value;\n" +
                    "                int32_t arg1_value = arg1.value;\n" +
                    "\n" +
                    "                int32_t less;\n" +
                    "\n" +
                    "                if (arg1.type == FLT && arg2.type == FLT) {\n" +
                    "                    less = int_to_float(arg1_value) < int_to_float(arg2_value);\n" +
                    "                } else if (arg1.type == FLT) {\n" +
                    "                    less = int_to_float(arg1_value) < (float) arg2_value;\n" +
                    "                } else if (arg2.type == FLT) {\n" +
                    "                    less = (float) arg1_value < int_to_float(arg2_value);\n" +
                    "                } else {\n" +
                    "                    less = arg1_value < arg2_value;\n" +
                    "                }\n" +
                    "\n" +
                    "                data_stack_push((struct var) {less, BOOL}, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case GREATER: {\n" +
                    "                struct var arg2 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg2.type != INT && arg2.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                struct var arg1 = get_data_from_data_stack_top(data_stack, vmm);\n" +
                    "\n" +
                    "                if (arg1.type != INT && arg1.type != FLT) {\n" +
                    "                    // wrong argument\n" +
                    "                }\n" +
                    "\n" +
                    "                int32_t arg2_value = arg2.value;\n" +
                    "                int32_t arg1_value = arg1.value;\n" +
                    "\n" +
                    "                int32_t greater;\n" +
                    "\n" +
                    "                if (arg1.type == FLT && arg2.type == FLT) {\n" +
                    "                    greater = int_to_float(arg1_value) > int_to_float(arg2_value);\n" +
                    "                } else if (arg1.type == FLT) {\n" +
                    "                    greater = int_to_float(arg1_value) > (float) arg2_value;\n" +
                    "                } else if (arg2.type == FLT) {\n" +
                    "                    greater = (float) arg1_value > int_to_float(arg2_value);\n" +
                    "                } else {\n" +
                    "                    greater = arg1_value > arg2_value;\n" +
                    "                }\n" +
                    "\n" +
                    "                data_stack_push((struct var) {greater, BOOL}, data_stack);\n" +
                    "\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            case MAIN: {\n" +
                    "                vmm->num_of_maps++;\n" +
                    "                break;\n" +
                    "            }\n" +
                    "            default: {\n" +
                    "                // error\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        curr_addr++;\n" +
                    "    }\n" +
                    "\n" +
                    "    *byte_code_size_ptr = byte_code_size;\n" +
                    "}");

            writer.println("int main(void) {\n" +
                    "    // DON'T REMOVE THIS CODE FROM BELOW UNDER ANY CIRCUMSTANCES\n" +
                    "    HAL_Init();\n" +
                    "    MX_GPIO_Init();\n" +
                    "    MX_IWDG_Init();\n" +
                    "    MX_TRACE_Init();\n" +
                    "    SDK_TRACE_Start();\n" +
                    "    HAL_GPIO_TogglePin(GPIOD, GPIO_PIN_15);\n" +
                    "    SDK_TRACE_Timestamp(LED3, HAL_GPIO_ReadPin(GPIOD, GPIO_PIN_15));\n" +
                    "    // DON'T REMOVE THIS CODE FROM ABOVE UNDER ANY CIRCUMSTANCES\n" +
                    "\n" +
                    "\n" +
                    "    // THEORETICALLY YOU CAN PUT YOUR CODE BELOW, BUT I WOULDN'T RECOMMEND\n" +
                    "\n" +
                    "    struct var data_stack_data[DATA_STACK_SIZE];\n" +
                    "    int32_t ret_stack_data[RET_STACK_SIZE];\n" +
                    "    int32_t num_of_vars_in_map[RET_STACK_SIZE];\n" +
                    "    struct var vmm_data[RET_STACK_SIZE * VAR_MAP_SIZE];\n" +
                    "    struct heap_entry heap_data[HEAP_SIZE];\n" +
                    "\n" +
                    "    for (int32_t i = 0; i < DATA_STACK_SIZE; i++) {\n" +
                    "        data_stack_data[i] = NULL_DATA_STACK_ENTRY;\n" +
                    "    }\n" +
                    "\n" +
                    "    for (int32_t i = 0; i < RET_STACK_SIZE; i++) {\n" +
                    "        ret_stack_data[i] = NULL_RET_STACK_ENTRY;\n" +
                    "    }\n" +
                    "\n" +
                    "    for (int32_t i = 0; i < RET_STACK_SIZE; i++) {\n" +
                    "        num_of_vars_in_map[i] = 0;\n" +
                    "    }\n" +
                    "\n" +
                    "    for (int32_t i = 0; i < RET_STACK_SIZE * VAR_MAP_SIZE; i++) {\n" +
                    "        vmm_data[i] = NULL_VAR_MAP_ENTRY;\n" +
                    "    }\n" +
                    "\n" +
                    "    for (int32_t i = 0; i < HEAP_SIZE; i++) {\n" +
                    "        heap_data[i] = NULL_HEAP_ENTRY;\n" +
                    "    }\n" +
                    "\n" +
                    "    struct data_stack my_data_stack = {.num_of_entries = 0, .data = data_stack_data};\n" +
                    "    struct ret_stack my_ret_stack = {.num_of_entries = 0, .data = ret_stack_data};\n" +
                    "    struct var_map_map my_vmm = {.num_of_maps = 0, .num_of_vars_in_maps = num_of_vars_in_map, .data = vmm_data};\n" +
                    "    struct heap my_heap = {.num_of_entries = 0, .data = heap_data, .next_free_entry = 0};\n" +
                    "    struct interpreter my_interpreter = {\n" +
                    "            .data_stack = &my_data_stack,\n" +
                    "            .ret_stack = &my_ret_stack,\n" +
                    "            .vmm = &my_vmm,\n" +
                    "            .heap = &my_heap\n" +
                    "    };");

            writer.print("\n");

            writer.println("\tint32_t main_program_start = " + getEntryPoint() + ";");

            writer.println("\tint32_t byte_code_size = " + getByteCodeSize() + ";");

            writer.print("\n");

            writer.println("\tint32_t byte_code[MAX_BYTE_CODE_SIZE] = {");

            translateByteCodeToNumberFormat().forEach(byteCodeCommand -> writer.println("\t\t" + byteCodeCommand + ','));

            writer.println("\t};");

            writer.print("\n");

            writer.println("    interpret(&my_interpreter, byte_code, main_program_start, &byte_code_size);\n" +
                    "\n" +
                    "    // THEORETICALLY YOU CAN PUT YOUR CODE ABOVE, BUT I WOULDN'T RECOMMEND\n" +
                    "\n" +
                    "\n" +
                    "    // DON'T REMOVE THIS CODE FROM BELOW UNDER ANY CIRCUMSTANCES\n" +
                    "    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_13, GPIO_PIN_RESET);\n" +
                    "    SDK_TRACE_Timestamp(LED1, HAL_GPIO_ReadPin(GPIOD, GPIO_PIN_13));\n" +
                    "    HAL_GPIO_WritePin(GPIOD, GPIO_PIN_15, GPIO_PIN_RESET);\n" +
                    "    SDK_TRACE_Timestamp(LED3, HAL_GPIO_ReadPin(GPIOD, GPIO_PIN_15));\n" +
                    "    SDK_TRACE_Timestamp(PRINT, 1);\n" +
                    "    SDK_TRACE_Timestamp(PRINT, 0);\n" +
                    "    SDK_TRACE_Stop();\n" +
                    "    // DON'T REMOVE THIS CODE FROM ABOVE UNDER ANY CIRCUMSTANCES\n" +
                    "}");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // antlr rules
    public static void program() {
        byteCode.add(ByteCodeCommands.exit.toString());
        optimizeStringLiterals();
    }


    public static void statements() {

    }


    public static void statement() {

    }


    public static void entryPoint(int line) {
        varMap = new HashMap<>(); // new scope of global vars starts with program entry point
        entryPoint = byteCode.size();
        byteCode.add("MAIN");
    }


    public static void fromCycle() {
        int counterAddr = fromCycleStack.pop();
        int currAddr = byteCode.size(); // addr of first command after last fromCycle command
        byteCode.add(ByteCodeCommands.jdec.toString());
        byteCode.add(String.valueOf(counterAddr - (currAddr + 1))); // after adding jdec command curr addr increased by 1
    }


    public static void stepInt(String lit) {
        byteCode.add(ByteCodeCommands.fint.toString());
        byteCode.add(lit);
        byteCode.add("counter");
        fromCycleStack.add(byteCode.size() - 1);
    }


    public static void stepVar(String varName) {
        byteCode.add(ByteCodeCommands.fvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
        byteCode.add("counter");
        fromCycleStack.add(byteCode.size() - 1);
    }


    public static void upperBorderInt(String lit) {
        byteCode.add(ByteCodeCommands.fint.toString());
        byteCode.add(lit);
    }


    public static void upperBorderVar(String varName) {
        byteCode.add(ByteCodeCommands.fvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
    }


    public static void lowerBorderInt(String lit) {
        byteCode.add(ByteCodeCommands.loop.toString());
        byteCode.add(ByteCodeCommands.fint.toString());
        byteCode.add(lit);
    }


    public static void lowerBorderVar(String varName) {
        byteCode.add(ByteCodeCommands.loop.toString());
        byteCode.add(ByteCodeCommands.fvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
    }


    public static void ifOperator() {
        int jtAddr = ifOperatorStack.pop();
        int currAddr = byteCode.size();
        String addrToJt = String.valueOf(currAddr - jtAddr);
        byteCode.set(jtAddr, addrToJt);
    }


    public static void boolExpr() {
        byteCode.add(ByteCodeCommands.jt.toString());
        byteCode.add("stub for address to jump if condition is false");
        ifOperatorStack.add(byteCode.size() - 1);
    }


    public static void funcDef(String name, String type, int line) {
        Optional<FuncInfo> isPresentoldFuncDefinition = funcList.stream().limit(funcList.size() - 1).
                filter(funcInfo -> funcInfo.getName().equals(name)).
                findFirst();

        if (isPresentoldFuncDefinition.isPresent()) {
            FuncInfo oldFuncDefinition = isPresentoldFuncDefinition.get();
            ByteCodeCommands newType = defineType(type, "Unknown return type in func " + name + " definition at line " + line);
            FuncInfo newFuncDefinition = funcList.get(funcList.size() - 1);
            oldFuncDefinition.setStart(newFuncDefinition.getStart());
            oldFuncDefinition.setType(newType);
            byteCode.add(newFuncDefinition.getStart(), String.valueOf(newFuncDefinition.getNumOfParams()));
            byteCode.add(newFuncDefinition.getStart(), newType.toString());
            funcList.remove(funcList.size() - 1);
        } else {
            ByteCodeCommands funcType = defineType(type, "Unknown return type in func " + name + " definition at line " + line);
            FuncInfo lastFunc = funcList.get(funcList.size() - 1);
            lastFunc.setName(name);
            lastFunc.setType(funcType);
            byteCode.add(lastFunc.getStart(), String.valueOf(lastFunc.getNumOfParams()));
            byteCode.add(lastFunc.getStart(), funcType.toString());
        }
    }


    public static void returnValueFuncCall() {
        byteCode.add(ByteCodeCommands.rofc.toString());
        byteCode.add(ByteCodeCommands.jret.toString());
    }


    public static void returnValueVariable(String varName) {
        byteCode.add(ByteCodeCommands.rvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
        byteCode.add(ByteCodeCommands.jret.toString());
    }


    public static void returnValueLiteral() {
        byteCode.add(byteCode.size() - 2, ByteCodeCommands.rlit.toString());
        byteCode.add(ByteCodeCommands.jret.toString());
    }


    public static void funcParams() {
        varMap = new HashMap<>(); // new scope starts with new func def
        funcList.add(new FuncInfo(null, byteCode.size(), null, 0));
    }


    public static void funcParam(String name, String type, int line) {
        if (funcList.size() == 0 || funcList.get(funcList.size() - 1).getName() != null) { // second condition means that previous function reached top rule: func def
            varMap = new HashMap<>(); // new scope starts with new func def
            funcList.add(new FuncInfo(null, byteCode.size(), null, 0));
        }

        ByteCodeCommands byteCodeType = defineType(type, "Unknown type " + type + " of func param " + name + " at line " + line);

        varMap.put(name, new VarInfo(varMap.size(), byteCodeType));
        funcList.get(funcList.size() - 1).incNumOfParams();
        byteCode.add(byteCodeType.toString());
    }


    public static void funcCall(String funcName, int line) {
        String coreFunc = ByteCodeCommands.getCore(funcName);

        if (!isNull(coreFunc)) {
            byteCode.add(coreFunc);
        } else {
            try {
                int userDefinedFuncStart = funcList.stream().
                        filter(funcInfo -> funcInfo.getName().equals(funcName)).
                        findFirst().
                        orElseThrow(() -> new UnknownFuncCallException("Call of unknown func " + funcName + " at line " + line)).
                        getStart();
                byteCode.add(ByteCodeCommands.call.toString());
                byteCode.add(String.valueOf(userDefinedFuncStart));
            } catch (NullPointerException ex) {
                throw new UnknownFuncCallException("Call of unknown func " + funcName + " at line " + line);
            }
        }
    }


    public static void funcArgFuncCall() {
        byteCode.add(ByteCodeCommands.ofc.toString());
    }


    public static void funcArgVariable(String name) {
        byteCode.add(ByteCodeCommands.var.toString());
        byteCode.add(String.valueOf(varMap.get(name).getIndex()));
    }


    public static void funcArgLiteral() {
        byteCode.add(byteCode.size() - 2, ByteCodeCommands.lit.toString());
    }


    public static void varDef(String name, String type, int line) {
        ByteCodeCommands byteCodeType = defineType(type, "Definition of variable " + name + " with unknown type " + type + " at line " + line);
        varMap.put(name, new VarInfo(varMap.size(), byteCodeType));
        byteCode.add(byteCodeType.toString());
    }


    public static void literal(String literal, ByteCodeCommands type, int line) {
        byteCode.add(type.toString());
        if (type.equals(ByteCodeCommands.STR)) {
            byteCode.add(String.valueOf(stringLiteralsStorage.size()));
            stringLiteralsStorage.add(literal);
        } else if (type.equals(ByteCodeCommands.BOOL)) {
            if (literal.equals("TRUE")) {
                byteCode.add("1");
            } else {
                byteCode.add("0");
            }
        } else {
            byteCode.add(literal);
        }
    }


    private static ByteCodeCommands defineType(String type, String message) {
        switch (type) {
            case "bool" -> {
                return ByteCodeCommands.BOOL;
            }
            case "int" -> {
                return ByteCodeCommands.INT;
            }
            case "float" -> {
                return ByteCodeCommands.FLT;
            }
            case "string" -> {
                return ByteCodeCommands.STR;
            }
            case "pointer" -> {
                return ByteCodeCommands.PTR;
            }
            case "void" -> {
                return ByteCodeCommands.VOID;
            }
            default -> throw new UnknownTypeException(message);
        }
    }
}