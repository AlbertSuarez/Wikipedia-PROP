#quick and dirty Makefile

.SUFFIXES: .java .class

all: classes libExtract
	javac -d classes -cp classes -sourcepath src src/wikipedia/Main.java
	javac -d classes -cp classes -sourcepath src src/g13/DriverGraph.java
	javac -d classes -cp classes -sourcepath src src/wikipedia/persistence/DriverPersistence.java

## Extrae el contenido del jar en classes
## En el caso de guava nos interesa la carpeta "com"
libExtract:
	cd classes; jar xf ../lib/*.jar;

run:
	@java -cp classes wikipedia.Main

DriverGraph:
	@java -cp classes g13.DriverGraph

DriverPersistence:
	@java -cp classes wikipedia.persistence.DriverPersistence

classes:
	@mkdir -p classes

clean:
	@rm -rf classes
