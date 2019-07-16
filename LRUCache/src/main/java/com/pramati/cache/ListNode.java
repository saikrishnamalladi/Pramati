package com.pramati.cache;
/**
 * List node to represent doubly linked list
 * Key is 
 */
public class ListNode {
	
	private String key;
	
	private ListNode prev;
	
	private ListNode next;
	
	/**
	 * Default Constructor
	 */
	public ListNode() {
		prev = next = this;
	}
	/**
	 * Constructor to create Node with key
	 */
	public ListNode(String key) {
		this();
		this.key = key;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the prev
	 */
	public ListNode getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(ListNode prev) {
		this.prev = prev;
	}

	/**
	 * @return the next
	 */
	public ListNode getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(ListNode next) {
		this.next = next;
	}

}
