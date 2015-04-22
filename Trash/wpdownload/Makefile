TARGET	= wpdownload
OBJS	= wpdownload.o utility.o

$(TARGET): $(OBJS)
	g++ -o $@ $^

%.o: %.cpp
	g++ -c $? -o $@

clean:
	@rm -rf $(TARGET) $(OBJS)
