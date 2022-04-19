package project3;

import java.util.ArrayList;

public class GenericStack<E> implements GenericStackInterface<E> {
	
	private ArrayList<E> list = new ArrayList<>();
	private int count;
	
	public GenericStack() {
		this.count = 0;
	}
	
	public int getSize() {
		return count;
	}
	
	public E peek() throws RuntimeException{
		if(list.isEmpty() == true){
			throw new RuntimeException("The stack is empty.");
		}
		E z = list.get(list.size() - 1);
		return z;
	}
	
	public void push (E e) {
		list.add(e);
		count++;
	}
	public E pop() throws RuntimeException{
		E a;
		if(list.isEmpty() == true) {
			throw new RuntimeException("There are no objects to pop.");
		}
		else
		a = peek();
		list.remove(this.getSize() - 1);
		count--;
		return a; // Place holder method.
	}
	
	public boolean isEmpty() {
		if(count == 0) {
			return true;
		}
		else
			return false; // Another place holder.
	}
	

}
