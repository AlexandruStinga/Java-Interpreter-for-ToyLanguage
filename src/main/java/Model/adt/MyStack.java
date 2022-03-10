package Model.adt;
import Model.exceptions.MyException;

import java.util.List;
import java.util.Stack;
public class MyStack<T> implements IStack<T> {
    Stack<T> stack;

    public MyStack(){
        stack = new Stack<T>();
    }

    @Override
    public T pop() {
        if(stack.isEmpty())
            throw new MyException("Empty stack");
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }


    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public String toString()
    {
        return stack.toString();
    }

    @Override
    public List<T> getAll() {
        return stack;
    }
}
