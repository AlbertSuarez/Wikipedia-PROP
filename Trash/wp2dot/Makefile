TARGET	= wp2dot
OBJS	= wp2dot.o

$(TARGET): $(OBJS)
	g++ -o $@ $^

%.o: %.cpp
	g++ -c $? -o $@

clean:
	@rm -rf $(TARGET) $(OBJS)
