cmake_minimum_required(VERSION 3.25)
project(main)

set(CMAKE_C_STANDARD 11)

include_directories(include)

add_executable(main main.c)

target_link_libraries(main m)