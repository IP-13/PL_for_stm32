cmake_minimum_required(VERSION 3.25)
project(ITMOVA C)

set(CMAKE_C_STANDARD 11)

include_directories(include)

add_executable(ITMOVA
        main.c
        src/itmova_core.c
        )
