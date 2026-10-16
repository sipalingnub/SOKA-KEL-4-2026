@echo off
REM 5. Ubah HOST+VM: infra kecil (2 host A + 1 host B, 4 VM cepat + 2 VM lambat) - uji sempit
call build.bat
java -cp "target/classes;lib/cloudsim-3.0.3.jar;lib/commons-math3-3.2.jar" soka.simulasi.DatasetMetricExtractor dataset/GoCJ_Dataset_600.txt 3 600 2 hostA=2 hostB=1 vm1=4 vm2=2 dc=1
echo SELESAI - lihat folder hasil/
pause
