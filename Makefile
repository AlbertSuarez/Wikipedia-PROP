#quick and dirty Makefile

.SUFFIXES: .java .class

JAVA_TAGS = -tag pre:cm:"Precondition:"  -tag post:cm:"Postcondition:"

ifeq ($(OS), Windows_NT)
CLASSES = "classes;lib/guava-18.0.jar;lib/jgraphx.jar"
else
CLASSES = "classes:lib/guava-18.0.jar:lib/jgraphx.jar"
endif

all: classes
	javac -d classes -cp $(CLASSES) -sourcepath src src/wikipedia/Main.java
	javac -d classes -cp $(CLASSES) -sourcepath src src/g13/DriverGraph.java
	javac -d classes -cp $(CLASSES) -sourcepath src src/wikipedia/persistence/DriverPersistence.java
	javac -d classes -cp $(CLASSES) -sourcepath src src/wikipedia/domain/DriverDomain.java
##	javac -d classes -cp classes -sourcepath src src/wikipedia/Main.java


## Extrae el contenido del jar en classes
## En el caso de guava nos interesa la carpeta "com"

##libExtract:
##	cd classes; jar xf ../lib/*.jar;

run:
	@java -cp $(CLASSES) wikipedia.Main

DriverGraph:
	@java -cp $(CLASSES) g13.DriverGraph

DriverPersistence:
	@java -cp $(CLASSES) wikipedia.persistence.DriverPersistence

DriverDomain:
	@java -cp $(CLASSES) wikipedia.domain.DriverDomain

doc:
	@javadoc -private $(JAVA_TAGS) -d html -sourcepath src -classpath $(CLASSES) -subpackages wikipedia:g13

classes:
	@mkdir -p classes

jar: all
	#@unzip lib/guava-18.0.jar
	@jar -cvfm Wikipedia.jar MANIFEST.MF lib/guava-18.0.jar -C classes .

clean:
	@rm -rf classes latex html Wikipedia.jar
