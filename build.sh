export JAVA_HOME=/usr/lib/jvm/java-8-jdk
mvn clean compile package assembly:single
rm artifacts/*
cp target/custom_dump-jar-with-dependencies.jar artifacts/custom_dump.jar
mvn install
