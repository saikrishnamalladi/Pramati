package com.pramati.cache;
/**
 * Double linked list with pointer to head
 * 
 */
public class DoublyLinkedList {

	private ListNode head;
	private int size;

	/**
	 * Constructor to create DoublyLinkeList
	 * 
	 */
	public DoublyLinkedList() {
		head = new ListNode();
		this.size = 0;
	}
	/**
	 * @return the size
	 */
	public int size() {
		return size;
	}

	/**
	 * removes fist element of the list
	 * @return ListNode
	 */
	public ListNode removeFirst() {
		ListNode tmp = head.getNext();
		tmp.getPrev().setNext(tmp.getNext());
		tmp.getNext().setPrev(tmp.getPrev());
		--size;
		return tmp;
	}
	/**@param ListNode
	 * remove the node and add it to the end of list 
	 */
	public void removeAddLast(ListNode tmp) {
		tmp.getPrev().setNext(tmp.getNext());
		tmp.getNext().setPrev(tmp.getPrev());
		
		tmp.setPrev(head.getPrev());
		tmp.setNext(head);
		head.getPrev().setNext(tmp);
		head.setPrev(tmp);
	}
	/**@param key
	 * Creates node and adds it to end of list 
	 * @return ListNode
	 */
	public ListNode addLast(String key) {
		ListNode tmp = new ListNode(key);
		tmp.setPrev(head.getPrev());
		tmp.setNext(head);
		head.getPrev().setNext(tmp);
		head.setPrev(tmp);
		++size;
		return tmp;
	}
	/**
	 * Display the Elements of the list 
	 */
	public void display() {
		System.out.print("Keys in the cache : ");
		for (ListNode current = head.getNext(); current != head; current = current.getNext()) {
			if (current.getNext() == head) {
				System.out.print(current.getKey());
			} else {
				System.out.print(current.getKey() + ",");
			}
		}
		System.out.println();
	}
	
	/**
	 * @param ListNode
	 * Returns the position of the node in the list 
	 * @return integer
	 */
	public int getPosition(ListNode node) {
		int index=0;
		ListNode current;
		for (current = head.getNext(); current != head && current!= node; current = current.getNext()) {
			index++;
		}	
		return current == node ? index+1 : -1;
	}

}
