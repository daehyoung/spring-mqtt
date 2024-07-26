package kr.luxsoft.iot.mqtt.spring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class CircularQueue<E> implements Iterable<E> {
    private final List<E> queue;
    private final int maxSize;
    private int head;
    private int tail;
    private int size;

    public CircularQueue(int size) {
        this.queue = new ArrayList<>(size);
        this.maxSize = size;
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        for (int i = 0; i < size; i++) {
            queue.add(null); // 초기화
        }
    }

    public void offer(E element) {
        if (size == maxSize) {
            head = (head + 1) % maxSize; // 가장 오래된 요소를 대체
        } else {
            size++;
        }
        queue.set(tail, element);
        tail = (tail + 1) % maxSize;
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        E element = queue.get(head);
        queue.set(head, null); // 요소 제거
        head = (head + 1) % maxSize;
        size--;
        return element;
    }

    public E peek() {
        if (size == 0) {
            return null;
        }
        return queue.get(head);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularQueueIterator();
    }

    public Iterator<E> reverseIterator() {
        return new ReverseCircularQueueIterator();
    }

    private class CircularQueueIterator implements Iterator<E> {
        private int currentIndex = head;
        private int elementsVisited = 0;

        @Override
        public boolean hasNext() {
            return elementsVisited < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = queue.get(currentIndex);
            currentIndex = (currentIndex + 1) % maxSize;
            elementsVisited++;
            return element;
        }
    }

    private class ReverseCircularQueueIterator implements Iterator<E> {
        private int currentIndex = (tail - 1 + maxSize) % maxSize;
        private int elementsVisited = 0;

        @Override
        public boolean hasNext() {
            return elementsVisited < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = queue.get(currentIndex);
            currentIndex = (currentIndex - 1 + maxSize) % maxSize;
            elementsVisited++;
            return element;
        }
    }


//    @Override
//    public String toString(){
//        return queue.toString();
//    }

    public List<E> getElements() {
        List<E> elements = new ArrayList<>();
        Iterator<E> iterator = reverseIterator();
        while (iterator.hasNext()) {
            elements.add(iterator.next());
        }
        return elements ;
    }
}
