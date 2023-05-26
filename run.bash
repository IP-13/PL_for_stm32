#!/bin/bash
# generate antlr4 grammar
antlr4 compiler/src/main/java/com/ip13/antlr/plClab.g4

# build compiler
mvn package -f compiler/

# build interpreter


# remove compiler
mvn clean -f compiler/

# remove interpreter

