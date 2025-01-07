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
		
		Node<E> x = search(index - 1);
		
		newNode.next = x.next;
		x.next = newNode;
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
}