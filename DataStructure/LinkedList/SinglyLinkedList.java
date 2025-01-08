import Interface.List;

public class SinglyLinkedList<E> implements List<E> {
	
	private Node<E> head; // 노드의 첫 부분
	private Node<E> tail; // 노드의 마지막 부분
	private int size; // 요소 개수
	
	// 생성자
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// 특정 위치의 노드를 반환하는 메소드
	private Node<E> search(int index) {
		
		// 범위 밖일 경우 예외
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> x = head; // head가 가리키는 노드부터 시작
		
		for (int i = 0; i < index; i++) {
			x = x.next;
		}
		
		return x;
	}
	
	public void addFirst(E value) {
		
		Node<E> newNode = new Node<E>(value); // 새 노드 생성
		newNode.next = head;
		head = newNode;
		size++;
		
		if (head.next == null)
			tail = head;
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		Node<E> newNode = new Node<E>(value);
		
		if (size == 0) { // 처음 넣는 노드일 경우 addFirst로 추가
			addFirst(value);
			return;
		}
		
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	@Override
	public void add(int index, E value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == 0) {
			addFirst(value);
			return;
		}
		
		if (index == size) {
			addLast(value);
			return;
		}
		
		Node<E> newNode = new Node<E>(value);
		
		Node<E> preNode = search(index - 1);
		
		newNode.next = preNode.next;
		preNode.next = newNode;
		size++;
	}
	
	// 가장 앞에 있는 요소 삭제
	public E remove() {
		E item = head.data;
		head = head.next;
		size--;
		
		if (size == 0)
			tail = null;
		
		return item;
	}
	
	@Override
	public E remove(int index) {
		
		if (index == 0)
			return remove();
		
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		
		Node<E> preNode = search(index - 1);
		Node<E> removeNode = preNode.next;
		E item = removeNode.data;
		
		preNode.next = removeNode.next;
		
		if (preNode.next == null)
			tail = preNode;
		
		size--;
		
		return item;
	}
	
	@Override
	public boolean remove(Object value) {
		Node<E> preNode;
		Node<E> x = head;
		boolean hasValue = false;
		
		for (; x != null; x = x.next) {
			if (value.equals(x.data) {
				hasValue = true;
				break;
			}
			preNode = x;
		}
		
		if (x == null)
			return false;
		
		if (x.equals(head)) {
			remove();
			return true;
		}
		
		else {
			preNode.next = x.next;
			
			if (preNode.next == null)
				tail = preNode;
			
			size--;
			return true;
		}
	}
	
	@Override
	public E get(int index) {
		return search(index).data;
	}
	
	@Override
	public void set(int index, E value) {
		Node<E> replaceNode = search(index);
		replaceNode.data = value;
	}
	
	@Override
	public int indexOf(Object value) {
		int index = 0;
		
		for (Node<E> x = head; x != null; x = x.next) {
			if (value.equals(x.data)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	@Override
	public boolean contains(Object item) {
		return indextOf(item) >= 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void clear() {
		for (Node<E> x = head; x != null) {
			Node<E> nextNode = x.next;
			x.data = null;
			x.next = null;
			x = nextNode;
		}
		head = tail = null;
		size = 0;
	}
}