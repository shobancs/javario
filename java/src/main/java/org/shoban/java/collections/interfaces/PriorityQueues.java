package org.shoban.java.collections.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueues {
	public static void main(String[] args) {
		
	}
	
	static <E> List<E> heapSort(Collection<E> c) {
	    Queue<E> queue = new PriorityQueue(c);
	    List<E> result = new ArrayList<E>();

	    while (!queue.isEmpty())
	        result.add(queue.remove());

	    return result;
	}
}
