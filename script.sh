#!/bin/bash
# Remove all .class files in this directory and immediate child directories
find . -maxdepth 2 -name "*.class" -type f -delete
echo "Deleted .class files in current dir and immediate children."
