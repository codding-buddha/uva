import java.util.*;

public class MaxHeap<T extends Comparable<T>>  {
	private int _index = 0;
	List<T> _items;

	public MaxHeap(int size) {
		if(size < 10) {
			size = 10;
		}
		_items = new ArrayList<T>(size);
	}

	public void insert(T item) {
		_items.add(_index++, item);
		upHeapify(_index-1);
	}

	public int size() {
		return _index;
	}

	public T max() {
		if(_index <= 0)
			return null;

		return _items.get(0);
	}

	public T extract() {
		if(_index <= 0)
			return null;

		T top = _items.get(0);
		swap(_items, 0, _index-1);
		_items.set(_index-1, null);
		_index--;
		downHeapify(0);		
		return top;
	}

	public T delete(int index) {
		if(index < 0 || index >= _index) {
			return null;
		}

		T item = _items.get(index);
		swap(_items, index, _index-1);
		_items.set(_index-1, null);
		_index--;
		downHeapify(index);
		return item;
	}

	public void print() {
		for(int i =0; i < _index; i++) {
			System.out.print(_items.get(i) +  " ");
		}
		System.out.println();
	}

	private void upHeapify(int index) {
		if(index <= 0 )
			return;
		int parentIndex = index/2;
		if(_items.get(index).compareTo(_items.get(parentIndex)) > 0) {
			swap(_items, index, parentIndex);
			upHeapify(parentIndex);
		}
	}

	private void downHeapify(int index) {
		int childIndex1 = index*2 + 1;
		int childIndex2 = index*2 + 2;

		if(index >= _index || childIndex1 >= _index) {
			return;
		}

		
		if(childIndex2 >= _index) {
			if(_items.get(index).compareTo(_items.get(childIndex1)) < 0) {
				swap(_items, index, childIndex1);
				downHeapify(childIndex1);
			}
		} else {
			int indx = _items.get(childIndex1).compareTo(_items.get(childIndex2)) > 0 ? childIndex1 : childIndex2;
			if(_items.get(index).compareTo(_items.get(indx)) < 0) {
				swap(_items, index, indx);
				downHeapify(indx);
			}
		}
	}

	private void swap(List<T> items, int i , int j) {
		T temp = items.get(i);
		items.set(i, items.get(j));
		items.set(j, temp);
	}

	public static void main(String[] args) {
		MaxHeap<Integer> heap = new MaxHeap<Integer>(20);
		for(int i = 1; i <= 20; i++) {
			heap.insert((int)(Math.random()*i));
		}

		for(int i = 4; i >=0; i--) {
			System.out.print(heap.delete(i) + " ");
		}

		System.out.println();
		
		while(heap.size() > 0) {
			System.out.print(heap.delete(0) +  " ");
		}
		System.out.println();

		for(int i = 5; i >= 1; i--) {
			heap.insert(i);
		}

		while(heap.size() > 0) {
			System.out.print(heap.delete(0) +  " ");
		}
		System.out.println();
	}
}