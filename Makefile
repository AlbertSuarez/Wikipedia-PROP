#quick and dirty Makefile

.SUFFIXES: .java .class

all: classes
	javac -d classes -cp ./lib/*.jar -sourcepath src src/wikipedia/Main.java

run:
	@java -cp classes wikipedia.Main

classes:
	@mkdir -p classes

clean:
	@rm -rf classes
