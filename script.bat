@echo off
rem Remove all .class files in this directory and immediate child directories
del /q /f "*.class" 2>nul
for /d %%D in (*) do (
    del /q /f "%%D\*.class" 2>nul
)
echo Deleted .class files in current dir and immediate children.