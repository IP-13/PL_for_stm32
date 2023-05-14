#pragma once

#include <stdlib.h>

// system functions
void itmova_print(void *el);
float itmova_random();


// float functions ?  We can implements it for each type TODO
void *itmova_min(void *el1, void *el2);
void *itmova_max(void *el1, void *el2);


// true float functions
float itmova_sin(float x);
float itmova_cos(float x);
float itmova_log(float base, float x);
float itmova_abs(float x);


// string functions
char *itmova_concat(char *s1, char *s2);
char *itmova_substr(char *s, int32_t start, int32_t end);
int32_t itmova_like(char *s1, char *s2);
int32_t itmova_length(char *s);


// all-type functions
int32_t itmova_less(void *el1, void *el2);
int32_t itmova_greater(void *el1, void *el2);
int32_t itmova_equal(void *el1, void *el2);

