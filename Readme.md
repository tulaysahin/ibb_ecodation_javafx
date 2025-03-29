
JavaFX Desktop
Java FX GitHub Address Java Core

Git
git init
git add .
git commit -m "initalize javafx"
git remote add origin URL
git push -u origin master
Git Clone
git clone https://github.com/hamitmizrak/ibb_ecodation_javafx
JDK Dikkat
JDK JavaFx bizlere önerdiği JDK sürümü 17'dir.
Eğer JDK ile alakalı hatalar alırsak nereleri JDK 17 yapmalıyız ?
Settings => Build, Execution => Compiler => Build Compiler (JDK 17 seçelim)
Projects Structure => Project (JDK 17 seçelim)
Projects Structure => Modules => Module,Source,Dependency (JDK 17 seçelim)
Projects Structure => SDK =>  (JDK 17 seçelim)

Build => Rebuild Project
Eğer durduk yere veya JDK değiştirdikten sonra sistem çalışmazsa;
Build => Rebuild Project
Maven Codes
mvn clean
mvn clean install
mvn clean package
mvn clean package -DskipTests