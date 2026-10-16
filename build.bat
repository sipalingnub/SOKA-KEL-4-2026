@echo off
REM Build tanpa Maven: compile semua .java ke target\classes
if not exist target\classes mkdir target\classes
dir /s /b src\main\java\*.java > sources.txt
javac -encoding UTF-8 -cp "lib/cloudsim-3.0.3.jar;lib/commons-math3-3.2.jar" -d target/classes @sources.txt
del sources.txt
echo BUILD SELESAI
