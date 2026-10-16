@echo off
REM 6. Ubah DATACENTER: 2 datacenter + dataset 600
call build.bat
java -cp "target/classes;lib/cloudsim-3.0.3.jar;lib/commons-math3-3.2.jar" soka.simulasi.DatasetMetricExtractor dataset/GoCJ_Dataset_600.txt 3 600 2 hostA=3 hostB=3 vm1=9 vm2=6 dc=2
echo SELESAI - lihat folder hasil/
pause
