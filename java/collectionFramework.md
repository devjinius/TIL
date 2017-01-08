#Collection Framework
컬렉션 프레임워크란 데이터 군을 저장하는 클래스들을 표준화한 설계다. 즉 다수의 데이터 그룹을 표준화 시킨것을 말한다.

##core interface
크게 3가지 타입이 존재한다. List, Set, Map이다. List와 Set은 Collection을 상속받는다. Map is like dictionary at python.

###Collection
List와 Set의 조상인 Collection인터페이스에는 여러 메서드들이 존재한다. 
add(), addAll(), clear(), contains(), equals(), hashCode(), iterator(), remove(), retainAll(), size(), toArray() 등이 있다.

### Collections

Arrays처럼 컬렉션과 관련된 메서드를 제공한다. fill(), copy(), sort() 같은 메서드 들이 있다. 

기본메서드도 중요하지만 동기화가 더 중요하다. 



##### 동기화

> ​ 자동동기화 :arrow_right: 성능 하락 :arrow_right: 필요할때만 켜게 메서드 만듦 :arrow_right: 성능향상

새로 추가된 ArrayList 와 HashMap같은 컬렉션은 동기화를 필요할때만 사용한다. 

`List syncList = Collections.synchronizedList(new ArrayList(...));`

이렇게 사용한다. 



##### 싱글톤 컬렉션

new연산자가 아닌 메서드만을 이용해 인스턴스를 생성하도록 하는 것이다. 이는 인스턴스 개수를 제한하는 방법이다. 생성자를 `private`로 만든다. 이 경우 상속이 불가능!

``` java
static List sigletonList(Object o);
static Set sigleton(Object o);    // singletonSet이 아님
static Map sigletonMap(Object o);
```



##### 한 종류의 객체만 저장하기

컬렉션은 모든 종류의 객체를 저장하는 것이 가능한데 이는 장점이자 단점이다. 대부분의 경우 한 종류만 저장한다. 

`static List checkedList(List list, Class type);`

이렇게 checked를 사용하면 가능하다. 이는 JDK1.5이후부터는 제네릭으로도 처리할 수 있다. 



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

#### Comparator와 Comparable

비교할 수 있게 만들어주는 클래스와 인터페이스다. Integer나 String Date와 같은 것들이 Comparable을 구현하여 비교후 정렬하게 만들어주는 기능을한다.

Comparator는 compare(Object o1, Object o2), Comparable은 compareTo(Object o) 로 비교한다. 

기본 정렬은 오름차순으로 인터페이스를 이용하면 되고 기본 외의 다른 기준으로 하고 싶으면 클래스를 이용한다.

### Set
set은 중복을 허용하지 않고 저장순서가 유지되지 않는 컬렉션이다. 집합이다. HashSet, TreeSet이 있다. 

#### HashSet

Set인터페이스를 구현한 가장 대표적인 컬렉션이다. HashSet은 순서를 유지하지 않으므로 혹 순서 유지를 위한다면 LinkedHashSet을 이용한다. 

중복을 허용하지 않으나 Integer 1과 String 1은 구별하여 저장 가능하다. 

메서드는 다른 것들과 거의 동일하여 기술하지 않겠다. 

참고로 load factor라는 개념이 있는데 컬렛견 클래스에 저장공간이 가득 차기 전에 미리 용량을 확보하기 위한 것으로 0.8로 설정하면 저장공간의 80%가 채워지면 용량이 두배로 늘어난다. 기본값은 0.75. 사용 방법은 HashSet(int initialCapacity, float loadFactor)

Ex_3) HashSet안에 예를들어 Person이라는 객체를 저장할 경우

person은 name과 age를 가진다. 초기화시 두 멤버변수에 같은 값을 넘겨주어 객체생성을 하면 이는 같은 객체다. 그러나 주소값이 다르기에 중복되어 저장된다. 이를 해결하려면 person이라는 객체에 equals와 hashCode를 오버라이딩 해주면 해결할 수 있다. 그 이유는 add메서드가 호출될 때 equals()와 hashCode()를 호출하기 때문이다. equals()에서는 각 멤버변수를 비교하여 t/f를 반환하면 된다. hashcode()는  `return Objects.hash(name, age);`를 이용하자.

#### TreeSet

이진검색트리 라는 자료구조의 형태로 데이터를 저장한다. 중복데이터는 허용하지 않으며 크기를 비교한 후 정렬하여 저장한다. 따라서 순서유지도 하지 않는다. 

이진검색트리는 부모노드의 왼쪽에는 부모보다 작은값을, 오른쪽에는 큰 값을 가진다. 이렇게 정렬이 되려면 당연히 저장되는 객체가 comparable을 구현한 객체거나 comparator를 이용하여 비교해주어야 저장 가능하다. 

데이터가 정렬되었기에 검색에서 우수한 성능을 보인다. 링크드리스트에 비해 검색과 정렬기능은 더 뛰어나지만 추가/삭제기능은 느리다. 

###Map
map은 키와 값을 가지는 컬렉션이다. HashMap(Hashtable), LinkedHashMap, SortedMap, TreeMap이 있다. 
위와 좀 다른 메서드는 containsKey(Object key), containsValue(Object value), entrySet(), keySet(), put(Object key, Object value), putAll(Map t), values()가 있다. 

Map은 내부인터페이스로 Entry를 가진다. entry안에 key와 value가 배열이 아닌 멤버변수로 선언되어있다. 이는 객체지향의 특징을 잘 살린 케이스라고 볼 수 있다.

#### HashMap과 HashTable

​HashTable :arrow_right: HashMap의 관계다. HashMap은 key와 value를 하나의 entry로 저장한다. 

특징으로는  Object put(key, value)가 저장하는 메서드인데, key가 중복될 경우 덮어쓰기한다. 조심하자.

#### Properties

HashTable을 상속받아 구현한 것으로 Key, Value를 Stiring, String만 가진다. 주로 애플리케이션의 환경설정과 속성을 저장하는데 사용된다.

### 해싱과 해시함수

Hashing이란 hash function을 이용하여 데이터를 저장하고 검색하는 기법을 이야기한다. 이게 적용된 예로는 쉽게 환자를 주민번호로 정렬한 것이다. 한 배열에 주민등록번호를 키로 9X, 8X, 7X, 6X 년생들을 나눈다. 79년생을 해시함수를 통해 7이라는 해시코드를 얻은 다음 7X년생의 배열로 간다. 그 안에는 LinkedList로 값이 저장되어있고, 이제 완전한 주민번호로 환자 정보를 찾아내는 것이다. 