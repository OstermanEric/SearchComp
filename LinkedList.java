/**
 * name: Eric Osterman
 * Assignment: ALA9
 * assistance from Prof.Urban
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E>{
  // Data members
	private Node head, tail;
	int size;
  // Inner class Node
	private class Node{
		E value;
		Node next;
		Node(E initialValue){
			value = initialValue; 
			next = null;
		}
	}
  // Constructor
	public LinkedList() { // empty linked list - O(1)
		head = tail = null;
		size = 0;
	}
	
	// Adding an item to the list
	public boolean addFirst(E item) { // O(1)
		Node newNode = new Node(item);
		if(head == null) { // adding the first node
			head = tail = newNode; 
		}
		else { 
			newNode.next = head;
			head = newNode;
		}
		size++; 
		return true;
	}
	public boolean addLast(E item) { // O(1)
		Node newNode = new Node(item);
		if(head == null) { 
			head = tail = newNode; 
		}
		else { 
			tail.next = newNode; 
			tail = newNode; 
		}
		size++; 
		return true;
	}
	public boolean add(E item) { // O(1)
		return addLast(item);
	}
	// Retrieving an item from the list
	public E getFirst() {// O(1)
		if (head == null)
			throw new NoSuchElementException();
		return head.value;
	}

	public E getLast() {// O(1)
		if (head == null)
			throw new NoSuchElementException();
		return tail.value;
	}
	
	// Removing an item from the list
	public boolean removeFirst() { // O(1)
		if (head == null) 
			throw new NoSuchElementException();
		head = head.next;
		if(head == null) // removed the only node in the list
			tail = null;
		size--; 
		return true;
	}
	public boolean removeLast() { // O(n)
		if (head == null) 
			throw new NoSuchElementException();
		if(size == 1) 
			return removeFirst();
		Node current = head;
		Node previous = null;
		while(current.next != null) { // looking for the tail
			previous = current; 
			current = current.next;
		}
		previous.next = null; 
		tail = previous;
		size--; 
		return true;
	}
	// toString() method
	public String toString() { // O(n)
		String output = "[";
		Node node = head;
		while(node != null) {
			output += node.value + " ";
			node = node.next;
		}
		output += "]";
		return output;
	}
	// clear, check if empty, and size of the list
	public void clear() { // O(1)
		head = tail = null; 
		size = 0; 
	}
	public boolean isEmpty() { // O(1)
		return (size == 0); 
	}
	public int size() {	 // O(1)
		return size; 
	}
	// Implementing an iterator for the list
	public Iterator<E> iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements Iterator<E>{
		private Node current = head;
		
		public boolean hasNext() { // O(1)
			return (current != null);
		}
		
		public E next() { // O(1)
			if(current == null)
				throw new NoSuchElementException();
			E value = current.value;
			current = current.next; 
			return value;
		}
	}
	
	public int contains(E item) {
		int iterations = 0;
		Iterator<E> iter = iterator();
		while(iter.hasNext()) {
			iterations++;
			if(iter.next().equals(item)) {
				return iterations;
			}
		}
		return iterations;
	}
	public int add(int index, E item) {
		int iterations = 0;
		if(index > size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		if(index == 0) {
			addFirst(item);
		}
		if (index == size-1) {
			addLast(item);
		}
		Node current = head;
		Node previous = null;
		int i=0;
		while(i < index){// O(n)
			iterations++;
			i++;
			previous = current;
			current = current.next;
		}
		Node newNode = new Node(item);
		previous.next = newNode;
		newNode.next = current;
		size++;
		return iterations;
	}
	public int remove(E item) {
		int iterations = 0;
		Node current = head;
		Node previous = null;
		int i=0;
		while(current != null && !current.value.equals(item)){// O(n)
			iterations++;
			previous = current;
			current = current.next;
		}
		if (current != null) {
			previous.next = current.next;
			size--;
		}
		return iterations;
	}
}