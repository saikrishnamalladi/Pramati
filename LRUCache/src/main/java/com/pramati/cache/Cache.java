package com.pramati.cache;

import java.util.HashMap;
/**
 * Represents LRU Cache
 * 
 */
public class Cache {

	private DoublyLinkedList list;
	private HashMap<String, ListNode> index;
	private int capacity;
	/**
	 * Constructor to create cache with capacity
	 * 
	 */
	public Cache(int capacity) {
		this.capacity = capacity;
		list = new DoublyLinkedList();
		index = new HashMap<String, ListNode>();
	}
	/**
	 * @param key
	 * Creates entry if the key is not available
	 * If the key is available removes and add it to end
	 * If the cache if full removes head of list and add the item to the end of the list
	 */
	public void access(String key) {
		// Key is already available
		ListNode p = index.get(key);
		if (p != null) {
			int position = list.getPosition(p);
			p.setKey(key);
			list.removeAddLast(p);
			System.out.println("Key found at position "+position+",Moved to position "+list.size());
			return;
		}
		// is cache full?
		if (list.size() == capacity) {
			ListNode tmp = list.removeFirst();
			index.remove(tmp.getKey());
			p = list.addLast(key);
			index.put(key, p);
			System.out.println("Deleted old key "+tmp.getKey()+" ,inserted new key at position "+list.size());
		}else {
			p = list.addLast(key);
			index.put(key, p);
			System.out.println("Key not found in the cache,inserted at position "+list.size());
		}
	}

	public void cacheState() {
		list.display();
	}
}
