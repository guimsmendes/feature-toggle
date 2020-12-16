from openjdk:8u275

copy . /feature-toggle
workdir /feature-toggle

run apt-get update || true
run apt-get install -y maven
run mvn install

expose 8080
cmd ["java", "-jar", "target/itau-desafio-feature-toggle-1.0.7-exec.jar"]
