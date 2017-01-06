#Collection Framework
컬렉션 프레임워크란 데이터 군을 저장하는 클래스들을 표준화한 설계다. 즉 다수의 데이터 그룹을 표준화 시킨것을 말한다.

##core interface
크게 3가지 타입이 존재한다. List, Set, Map이다. List와 Set은 Collection을 상속받는다. Map is like dictionary at python.

###Collection
List와 Set의 조상인 Collection인터페이스에는 여러 메서드들이 존재한다. 
add(), addAll(), clear(), contains(), equals(), hashCode(), iterator(), remove(), retainAll(), size(), toArray() 등이 있다.

### List
list는 중복을 허용하면서 저장순서가 유지되는 컬렉션이다. Vector(Stack), ArrayList, LinkedList가 있다. 
대표 메서드로는 get(int index), indexOf(Object o), set(int index, Object element), sort(Comparator c), subList(int fromIndex, int toIndex) 등이 있다.

####ArrayList
ArrayList는 CF에서 가장 많이 사용되는 클래스다. 기존의 Vector를 개선한 것이다. Object배열을 만들어 그 안에 순차적으로 저장한다. 만일 배열의 공간이 부족하면 더 큰 배열을 생성하여 복사한다.
특별한 메서드로는 ensureCapacity(int minCapacity)(용량이 최소 minCapacity가 되게 만든다.), trimToSize()(빈공간을 제거한다.) 가 있다. 

```java
for(int i=list2.size()-1; i>=0; i--){
	if(list1.contains(list2.get(i)))
		list2.remove(i);
}
// list2와 list1의 공통요소를 삭제하는 코드다.
```
주목할 점은 index를 뒤에서부터 하나씩 줄이고 있다. 앞에서부터 줄일경우 요소가 삭제될때마다 빈 공간을 채우려 자리이동이 발생하여 코드가 복잡해진다.

#####capacity와 size
말그대로 용량과 크기다. ArrayList가 담을 수 있는 용량은 capacity고 현재 가지고있는 크기는 size다. 

####LinkedList
배열은 간단하고 사용하기가 쉽고 데이터를 가져오는 속도가 매우 빠르다. 그러나 크기를 변경할 수 없어 복사할경우 많은 작업이 소요된다. 또한 비순차적인 데이터를 추가하거나 삭제하는 경우 시간이 많이 걸린다.(중간에 데이터 추가)
따라서 그 점을 보완한 LinkedList가 있다. 다음 Node의 주소값과 데이터로 구성되어있다.  LinkedList는 서로 끝이 연결된 Double Circular Linked List로 이루어져있어 접근성도 좋다.

#####Array와 Linked의 비교
1. 순차적 데이터의 경우 A>L
2. 중간 데이터를 추가/삭제의 경우 A<L

합쳐서 사용하면 좋다. 즉 처음 데이터 저장은 ArrayList로, 수정은 LinkedList로 하는것이 가장 최선이다.
```java
ArrayList al = new ArrayList(1000000);
for(int i=0; i<100000; i++) al.add(i+"");

LinkedList ll = new LinkedList(al);
for(int i=0; i<1000; i++) ll.add(500, "X"); //500번 index에 X추가
```

####Stack과 Que
이미 알고있는 내용이다. Stack은 LIFO, Que는 FIFO다. 
Stack은 ArrayList, Que는 LinkedList로 구현하는것이 적합하다.

Stack은 클래스로 제공
+ peek() 맨위 객체 제거하지않고 반환
+ pop() 맨위 객체 꺼냄
+ push() 객체 저장
+ 수식계산, undo/redo, 웹브라우져의 뒤로/앞으로
  
Que는 인터페이스와 구현된 클래스 제공
+ element() 삭제없이 요소 읽음(비었을 경우 Exception)
+ offer() 객체저장
+ poll() 객체 꺼냄
+ peek() 삭제없이 요소읽음(비었을 경우 null)
+ 최근사용문서, 인쇄대기목록, 버퍼

Deque는 양쪽끝에 추가/삭제가 가능하다. 이는 스택과 큐를 합쳐놓았으며 메서드도 같이 사용이 가능하다.

####Iterator, ListIterator, Enumeration
세가지는 모두 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스다.
E-> I -> LI 순서대로 발전했다.
collection객체를 iterator()해서 `Iterator it = list.iterator()// 이런식으로`
`while(it.hasnext()) System.out.println(it.next());`
하면 쉽게 가져올 수 있다.

Map의 경우 키와 값을 쌍으로 저장하기때문에 set으로 변경한 후 iterator를 호출한다.
`Iterator list = map.entrySet().iterator();`

*메서드를 연속적으로 사용할 경우 return값이 일정하면 가능하다.*

####Arrays
배열을 다루는데 유용한 메서드가 들어있다. 
copyOf(), copyOfRange(), fill(), setAll(), sort(), binarySearch()(절반으로 줄여가며 검색하는 방식이다. **반드시 정렬후 사용**), equals(), deepToEquals(), toString(), deepToString(), asList() 등이있다.

### Set
set은 중복을 허용하지 않고 저장순서가 유지되지 않는 컬렉션이다. 집합이다. HashSet, TreeSet이 있다. 

###Map
map은 키와 값을 가지는 컬렉션이다. HashMap(Hashtable), LinkedHashMap, SortedMap, TreeMap이 있다. 
위와 좀 다른 메서드는 containsKey(Object key), containsValue(Object value), entrySet(), keySet(), put(Object key, Object value), putAll(Map t), values()가 있다. 
Map은 내부인터페이스로 Entry를 가진다. entry안에 key와 value가 배열이 아닌 멤버변수로 선언되어있다. 이는 객체지향의 특징을 잘 살린 케이스라고 볼 수 있다.