@echo off
REM 2. Dataset kecil 50 task (demo kilat sidang, <1 menit)
call build.bat
java -cp "target/classes;lib/cloudsim-3.0.3.jar;lib/commons-math3-3.2.jar" soka.simulasi.DatasetMetricExtractor dataset/GoCJ_Dataset_50.txt 3 50 2
echo SELESAI - lihat folder hasil/
pause
