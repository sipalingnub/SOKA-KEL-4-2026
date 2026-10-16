@echo off
REM 1. Default 600 task, infra asli (3+3 host, 9+6 VM, 1 DC) - cepat demo: 1 run, skala 100 saja
call build.bat
java -cp "target/classes;lib/cloudsim-3.0.3.jar;lib/commons-math3-3.2.jar" soka.simulasi.DatasetMetricExtractor dataset/GoCJ_Dataset_600.txt 1 100 1
echo SELESAI - lihat folder hasil/
pause
