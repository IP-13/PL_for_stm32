#pragma once

#include "interpreter.h"


void interpret(struct interpreter interpreter, int32_t *byte_code, int32_t start) {
    int32_t curr_command_addr = start;
    int32_t curr_command = byte_code[curr_command_addr];
    while (curr_command != EXIT) {
        switch (byte_code[curr_command_addr]) {
            case EXIT: {
                break;
            }
            case JMP: {
                curr_command_addr = byte_code[curr_command_addr + 1];
                break;
            }
            case JDEC: {
                int32_t temp = curr_command_addr;
                curr_command_addr += byte_code[curr_command_addr + 1];
                int32_t counter = --byte_code[curr_command_addr];
                if (!counter) {
                    curr_command_addr = temp;
                }
                break;
            }
            case JRET: {

            }
            case LOOP: {

            }
            case FINT: {

            }
            case FVAR: {

            }
            case JT: {

            }
            case CALL: {

            }
            case LIT: {

            }
            case VAR: {

            }
            case OFC: {

            }
            case RLIT: {

            }
            case RVAR: {

            }
            case ROFC: {

            }
            case BOOL: {

            }
            case INT: {

            }
            case FLT: {

            }
            case STR: {

            }
            case PTR: {

            }
            case VOID: {

            }
            case PRINT: {

            }
            case ASSIGN: {

            }
            case GET_DATA: {

            }
            case SET_DATA: {

            }
            case GET_ADDR: {

            }
            case MALLOC: {

            }
            case AND: {

            }
            case OR: {

            }
            case NOT: {

            }
            case CONCAT: {

            }
            case SUBSTR: {

            }
            case LIKE: {

            }
            case LENGTH: {

            }
            case ABS: {

            }
            case SIN: {

            }
            case COS: {

            }
            case INC: {

            }
            case DEC: {

            }
            case LOG: {

            }
            case POW: {

            }
            case SUM: {

            }
            case SUB: {

            }
            case MUL: {

            }
            case DIV: {

            }
            case MOD: {

            }
            case MIN: {

            }
            case MAX: {

            }
            case LESS: {

            }
            case GREATER: {

            }
            case EQUAL: {

            }
            case PI: {

            }
            case E: {

            }
            case RANDOM: {

            }
            case MAIN: {

            }
        }

        curr_command_addr++;
        curr_command = byte_code[curr_command_addr];
    }
}