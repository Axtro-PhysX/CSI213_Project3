package project3;

public interface GenericStackInterface<E> {
	
	int getSize();
	E peek();
	void push(E e);
	E pop();
	boolean isEmpty();

}
