@echo off
REM 4. Ubah HOST+VM: infra besar (5 host A + 5 host B, 12 VM cepat + 8 VM lambat)
call build.bat
java -cp "target/classes;lib/cloudsim-3.0.3.jar;lib/commons-math3-3.2.jar" soka.simulasi.DatasetMetricExtractor dataset/GoCJ_Dataset_600.txt 3 600 2 hostA=5 hostB=5 vm1=12 vm2=8 dc=1
echo SELESAI - lihat folder hasil/
pause
