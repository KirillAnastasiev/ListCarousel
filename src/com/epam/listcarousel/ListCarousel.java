package com.epam.listcarousel;

import java.util.*;

public class ListCarousel {

	/**
	 * Initialize list with integer values
	 *
	 * @param list list to initialize with values
	 * @return list with integer values
	 * @throws IllegalArgumentException if argument {@code list} is {@code null}
	 */
	public static List<Integer> init(List<Integer> list) throws IllegalArgumentException {
		if (Objects.isNull(list))
			throw new IllegalArgumentException("List is null");

		for (int i = 0; i < 100_000; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * Removes each k-element using removing by idx
	 *
	 * @param list list of integers
	 * @param k elements position to remove, must be greater than 0
	 * @return execution time
	 * @throws IllegalArgumentException if argument {@code list} is {@code null} or argument {@code k} <= 0
	 */
	public static long removeByIndex(List<Integer> list, final int k) throws IllegalArgumentException {
		if (Objects.isNull(list))
			throw new IllegalArgumentException("List is null");
		if (k <= 0)
			throw new IllegalArgumentException("k <= 0");

		long start = System.currentTimeMillis();
		int cursor = 0;
		int countOfWalks = 0;

		while (list.size() > 1) {
			countOfWalks++;
			if (cursor >= list.size()) {
				cursor = 0;
			}

			if (countOfWalks % k == 0) {
				list.remove(cursor);
			} else {
				cursor++;
			}
		}
		return System.currentTimeMillis() - start;
	}

	/**
	 * Removes each k-element using iterator
	 *
	 * @param list list of integers
	 * @param k element position to remove, must be greater than 0
	 * @return execution time
	 * @throws IllegalArgumentException if argument {@code list} is {@code null} or argument {@code k} <= 0
	 */
	public static long removeByIterator(List<Integer> list, final int k) throws IllegalArgumentException {
		if (Objects.isNull(list))
			throw new IllegalArgumentException("List is null");
		if (k <= 0)
			throw new IllegalArgumentException("k <= 0");

		long start = System.currentTimeMillis();
		Iterator<Integer> iter = list.iterator();
		int countOfWalks = 0;

		while (list.size() > 1) {
			countOfWalks++;
			if (!iter.hasNext()) {
				iter = list.iterator();
			}
			iter.next();
			if (countOfWalks % k == 0) {
				iter.remove();
			}
		}
		return System.currentTimeMillis() - start;
	}

	public static void main(String[] args) {
		System.out.printf("ArrayList removedByIndex: %d ms%n", removeByIndex(init(new ArrayList<>()), 3));
		System.out.printf("LinkedList removedByIndex: %d ms%n", removeByIndex(init(new LinkedList<>()), 3));
		System.out.printf("ArrayList removedByIterator: %d ms%n", removeByIterator(init(new ArrayList<>()), 3));
		System.out.printf("LinkedList removedByIterator: %d ms%n", removeByIterator(init(new LinkedList<>()), 3));
	}
}
