@echo off
REM 3. Ubah DATASET: 1000 task resmi + sweep 5x (uji skalabilitas)
call build.bat
java -cp "target/classes;lib/cloudsim-3.0.3.jar;lib/commons-math3-3.2.jar" soka.simulasi.DatasetMetricExtractor "dataset/GoCJ Google Cloud Jobs Dataset/GoCJ_Dataset_1000.txt" 5 100,500,1000 2
echo SELESAI - lihat folder hasil/
pause
