# Thread

## process & thread

프로세스란 실행중인 프로그램을 얘기한다. 프로그램을 실행하면 수행하는데 필요한 데이터와 메모리같은 자원을 받아 프로세스가 된다. 이 프로세스는 할당받은 자원과 쓰레드로 구성된다. 그래서 모든 프로세스는 하나 이상의 쓰레드가 존재한다.

쓰레드는 이렇게 실제로 작업을 수행하는 것을 이야기한다. 

*프로세스가 공장이라면 쓰레드는 일꾼이다.* 

현실세계에선 일꾼이 많으면 일이 엄청 빨라지지만 쓰레드는 좀 다르다. 그 이유는 context switching때문인데 이 것때문에 싱글코어에서는 오히려 느려진다.

### 멀티태스킹과 멀티쓰레딩

우리가 사용하는 OS들은 멀티태스킹을 지원한다. 한번에 프로세스가 동시에 여러개가 실행되어 그렇게 이야기한다. 멀티쓰레딩도 마찬가지다. 하나의 프로세스에서 여러 쓰레드가 동시에 작업을 수행하는 것이다. CPU의 코어가 아주 짧은 시간동안 여러 작업을 번갈아 가며 수행하기에 모두 동시에 처리되는것 처럼 보이게 된다.

### 멀티쓰레딩의 장점

1. CPU의 사용률 향상
2. 자원을 효율적으로 이용
3. 사용자 응답성 향상
4. 코드가 간결해짐

이 멀티쓰레딩 덕에 우리는 채팅하면서 파일을 다운받을 수 있다.

그렇지만 아까도 얘기했듯 context switching, synchronization, deadlock(교착상태: 서로 같은 자원을 점유하여 상대방의 사용종료를 기다리는 상태) 들로 더 낮은 효율을 보일 수 있다.

## Thread의 구현과 실행

구현하는 방법은 Thread 클래스 상속과 Runnable 인터페이스 구현이 있다. 클래스 상속은 크게 다르지 않지만 인터페이스 구현은 조금 다르다. 코드를 보자

```java
ThreadEx1_1 t1 = new ThreadEx1_1(); // Thread 상속받은 클래스 인스턴스 생성
Thread t2 = new Thread(new ThreadEx1_2()); 
// Runnable을 구현한 클래스의 인스턴스를 매개변수로 Thread 클래스 생성자 호출
```

인터페이스를 구현한 클래스를 그대로 사용하는 것이 아니라 Thread 객체를 만들어 낸다. 그 이유는 Runnable의 소스를 보면 알 수 있다.

`public interface Runnable {public abstract void run();}`

Runnable은 이게 다다. 그래서 인터페이스를 구현할 경우 run메서드만 구현하면 간단하다. 대신 그 외의 많은 메서드 들을 이용할 수 없기 때문에 매개변수로 Thread객체를 생성하는 것이다.

**Runnable을 구현할 경우 Thread 클래스의 static 메서드인 currentThread를 호출하여 쓰레드를 얻어와야만 호출이 가능하다.** 왜냐하면 멤버가 run() 밖에 없기 때문에 그냥 호출이 안된다.

`System.out.prinln(Thread.currentThread.getName());`

쓰레드는 이름을 지정하면 이름이 생긴다. 딱히 지정하지 않으면 default값으로 Thread-번호가 지정된다.

#### start()

앞에서 구현은 run()을 했지만 실행은 start()로 한다. 그 이유는 run()은 단순히 Thread를 실행하는 메서드인데 start()는 callstack을 만들어주는 메서드다. start()로 callstack을 만들고 그 위에 run()메서드를 호출하여 쓰레드를 실행해야 일꾼이 두명이 된다. 그냥 메인 콜스택에 실행하면 아무 효과가 없다.

start는 한번만 호출이 가능하다. 한번 실행이 종료된 쓰레드는 다시 실행할 수 없는 것이다. 만일 다시 실행하고자 한다면,

```java
Thread t1 = new Thread();
t1.start();
t1 = new Thread();
t1.start();
```

이렇게 다시 새로 생성해서 사용하면 된다. 

#### main Thread

메인을 작업하는 콜스택도 쓰레드다. 우리는 이를 메인쓰레드 라고 부른다. 지금까진 메인메서드가 종료되면 프로그램이 종료되었지만 새로운 쓰레드를 만들고 그 쓰레드가 아직 실행중이라면 메인이 끝나도 프로그램이 끝나지 않는다. 즉 *실행 중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료된다.*

### 싱글쓰레드와 멀티쓰레드

두 쓰레드가 같은 자원을 사용하는 경우 쓰레드를 나누는 것은 그다지 좋지 않다. 또 싱글코어의 경우도 마찬가지다. 쓰레드를 생성하나마나 의미가 없다. 

하지만 두 쓰레드가 서로 다른 자원을 사용하는 작업의 경우 멀티쓰레드가 큰 힘을 가진다. 예를들어 데이터를 입력받는다던가, 파일을 주고받는 경우, 프린터로 출력하는 경우가 이에 해당한다. 아까 말한 채팅하면서 파일을 보내며 영상통화를 하는 것과 같은경우도 이에 해당한다. 서로 다른 자원을가지고 각자의 작업을 수행하는 경우 굉장히 큰 효율을 보인다. 

### 쓰레드의 우선순위

쓰레드는 priority라는 멤버번수를 가진다. 이 우선순위에 따라 얻는 시간이 달라진다. main메서드 내에서 생성하는 쓰레드의 우선순위는 5이며 우선순위는 1~10이다. 높은 숫자일수록 우선순위가 높다.

`void setPriority(10);//우선순위 설정`

### 쓰레드 그룹

쓰레드는 디렉토리처럼 그룹을 나누어 관리 할 수 있는데 이는 역시 Access에 관한 문제다. 접근권한과 보안 상의 이유로 이렇게 관리한다. 쓰레드그룹을 만들 때는 ThreadGroup()메서드를 이용하고, 쓰레드를 그룹에 포함시키려면 Thread(ThreadGroup group, String name) 을 이용하자.

모든 쓰레드는 반드시 어떤 쓰레드 그룹에 포함되어 있어야 한다. 특정 그룹을 지정하지 않으면 기본적으로 자신을 생성한 쓰레드와 같은 그룹에 속한다. 

JVM은 main과 System이라는 쓰레드 그룹을 가지고 운영하며 우리가 만드는 쓰레드는 모두 main의 하위 그룹에 속하게 된다.

### 데몬 쓰레드

Daemon Thread는 일반 쓰레드의 작업을 돕는 보조적인 역할을 한다. 일반 쓰레드가 모두 종료되면 데몬도 같이 종료된다. 예로는 가비지 컬렉터, 워드프로세서의 자동저장, 화면자동갱신등이 있다.

일반쓰레드와 작성과 실행이 같고 단지 setDaemon(true)를 호출만 해주면 되는데 이는 start()를 호출하기 전에 호출해야 한다.

### 쓰레드의 실행

쓰레드는 몇가지 상태가 존재한다.

| 상태         | 설명                         |
| ---------- | -------------------------- |
| NEW        | 쓰레드가 생성되고 아직 START호출이 안된상태 |
| RUNNABLE   | 실행중이거나 실행 가능한 상태           |
| BLOCKED    | 일시정지 상태로 LOCK이 풀리길 기다리는 상태 |
| WAITING    | 작업 종료는 아니지만 실행 불가능한 정지상태   |
| TERMINATED | 작업이 종료된 상태                 |

749페이지에 그림으로 잘 설명 되어 있다. 틈틈히 들여다 볼것

#### sleep(long millis)

지정된 시간동안 일시정지 시킨다. 지정된 시간이 다 되거나 interrupt()가 호출되면 exception발생하여 실행대기상태가 된다.(que에 들어간다.)

sleep은 static으로 선언되어 있어 Thread.sleep으로 호출한다!

#### interrupt(), interrupted()

쓰레드의 상태를 전환시켜준다. 만일 큰 파일을 다운중이라면 중간에 멈출 수 있어야 한다. 이 때 이 메서드를 사용한다. 쓰레드를 강제로 종료시키는 것은 아니며 멈추는 정도다.

이와 반대로 sleep, wait, join에 의해 WAITING상태에 있을 때 exception을 발생시켜 실행 가능한 상태로 만들 수 도 있다.

#### suspend(), resume(), stop()

suspend로 정지 resume으로 재개할 수 있다. stop은 호출즉시 쓰레드를 종료시킨다. 이 메서드들은 deadlock을 일으킬 수 있어 deprecated되었다. 사용하지 않는것을 권장한다.

#### yield()

자신에게 주어진 실행시간을 양보한다. 중간에 다른 쓰레드에게 실행기회를 넘기고 자신은 다시 que로 돌아간다. 만일 자신이 잠시 멈춰있어야 되는 경우 그 시간을 버리지 않고 기다리며 실행권한을 다른쓰레드에게 넘기는 것이다.

#### join()

자신이 하던 작업을 잠시 멈추고 다른 쓰레드가 지정된 시간동안 작업을 수행하도록 한다. 시간을 지정하지않으면 자신이 작업을 마칠 때까지 기다린다. 작업중 다른 쓰레드의 작업이 우선되어야 할 경우 사용한다. 

`try{th1.join() // 현재 실행중인 쓰레드가 쓰레드 th1의 작업이 끝날때까지 기다린다.}catch(Exception e){}`

sleep()과 유사한 점이 많은데 static이 아니라는 점에서 다르다.

### Synchronize

멀티쓰레드 프로세스의 경우 A가 작업할 때 B가 A가 현재 사용하는 자원을 변경할 경우 A의 작업의 결과가 의도했던 것과 다를 수 있다. 그 예로 A가 계좌조회를 하는데 B가 그 사이에 돈을 뽑았거나 잔액을 변경할 경우 예상했던 결과가 아닐 수 있다. 일이 동시에 일어나서 생기는 문제다. 따라서 이럴 경우 어떠한 작업을 하나의 단위로 묶어 중간에 다른 쓰레드가 영향을 주지 못하게 해야 할 것이다. 

이를 lock이라고 하고 그 영역을 critical section이라고 한다. 데이터베이스의 transaction과 비슷한 개념이라고 생각하자. 자바에서는 synchronized블럭과 locks, atomic 패키지 이렇게 다양한 방법으로 이를 지원한다.

#### synchronized

키워드를 이용하는 방법이다. 두가지 방식이 있다.

`public synchronized void calcSum(){}`  메서드 동기화

`synchronized (referenceVal){}`  블럭 동기화

{}을 이용하여 critical section을 만들고 그 안의 작업을 수행할 때 lock을 얻어 작업을 수행한다. 작업이 끝나면 lock을 반납한다. 이때 critical section은 최소화하여 프로그램의 성능을 생각 해야한다.

#### wait(), notify()

앞의 sync는 동기화하여 공유 데이터를 보호하는 것이었다. 하지만 만일 계좌에 돈이 부족해서 출금을 못할 경우 쓰레드는 락을 보유한 채로 돈이 들어오기까지 기다리고 있을 것이다. 이럴 경우 하는일 없이 프로그램이 반복문을 계속 돌 것이다. 

이걸 개선하기 위해 wait()과 notify()가 생겼다. 코드를 수행하다 어느 조건에서는 wait()을 실행해서 락을 반납하고 기다리다가, 어떤 조건이 해결되면 notify()해서 알려주는 것이다. lock을 반납하였다가 재진입해서 프로그램을 실행하는 것이다. 

하지만 이 두 메서드도 한계가 있다. starvation과 race condition이다. starvation은 notify()때문에 생긴다. 이 통지라는 메서드는 waiting pool에 있는 쓰레드들 중 랜덤하게 아무 쓰레드나 호출한다. 만일 계속해서 다른 쓰레드를 호출할 경우 프로그램 실행시간이 계속 증가하는 것이다. 이는 notifyAll()메서드로 해결은 가능하다. 이는 모든 쓰레드를 que로 부르는 것이다. 이는 race condition을 초래한다. que에 들어가지 않아도 될 쓰레드까지 들어가 프로그램을 돌릴 것이다.

#### Lock&Condition

이를 해결하기 위해 Lock클래스가 나왔다. 

* ReentrantLock - 일반적인 lock
* ReentrantReadWriteLock - 읽기에는 공유적, 쓰기에는 배타적
* StampedLock - ReadWrite에 낙관적 lock 추가

이렇게 종류가 세가지가 있다. 

ReentrantReadWriteLock은 읽기와 쓰기의 lock을 제공한다. 읽기는 내용을 변경하지 않으므로 서로 동시에 쓰레드가 사용해도 상관이 없다. write의 경우에만 배타적으로 lock을 준다.

앞에서 사용한 sync는 brace로 묶기에 예외가 발생하거나 프로그램이 끝나면 자동으로 lock을 반납한다. 그러나 Lock클래스는 그렇지 않다. unlock() 으로 풀어주어야 한다.

수동으로 lock을 해제해야 하기에 예외가 발생하거나 return으로 critical section을 빠져나간다면 lock이 풀리지 않을 수 있으므로 항상 try-final로 감싸는것이 일반적이다. 

위의 문제들을 해결하는 방식은 간단하다. 조건을 주는 것이다. condition을 주어 기다리게 만드는거다. 요리사-손님의 관계를 생각해보자. 손님이 없으면 요리사는 기다리고 주문이 들어오면 만든다. 손님은 주문을하고 요리사가 만들때까지 음식을 기다려야 한다. 요리사와 손님이 둘다 waiting pool에 들어갈 수 도 있고, 다른 주문을 한 손님들끼리 섞일 수 도 있다. 따라서 condition을 주면된다.

사용법은 아래와 같고 wait()&notify()는 await()&signal()로 사용한다.

```java
private ReentrantLock lock = new ReentrantLock();
private Condition forCook = lock.newCondition();
private Condition forCust1 = lock.newCondition();
private Condition forCust2 = lock.newCondition();

forCook.await(); //요리사쓰레드 기다리게하기.
forCust1.signal() //손님 1 깨우기
```

Stamped는 lock을 걸거나 풀 때 스탬프를 사용한다. 또한 optimistic reading lock을 추가하였다. 이게 뭐냐면 만일 reading lock이 걸려있다면 write lock이 필요하더라도 reading이 끝나야 writing의 lock이 걸리는 것이다.

#### volatile

컴퓨터의 각 코어는 메모리에서 코어의 캐시로 값을 읽어온다. 그런 뒤 그 캐시값을 이용해서 작업을 하게 되어있다. 헌데 멀티 코어 컴퓨터의 경우 값을 읽어왔는데 다른 코어가 작업하면서 그 값을 변경할 수 있다. 그렇게 되면 결과가 달라질 것이다. 이를 막기위해 volatile을 붙여주면 변수의 값을 읽어올 때 캐시가 아닌 메모리에서 직접 읽어오게 된다. 그래서 불일치가 해결된다. 하지만 이것이 동기화를 의미하는 것은 아니다! 단지 atomic을 의미할 뿐이다. 

참고로 final에는 volatile을 붙일 수 없다. 상수는 변하지 않으므로 Thread-Safe하기 때문이다.붙일 이유가 없다.

#### Fork & Join

하나의 작업을 여러 쓰레드로 나누는걸 도와주는 프레임워크다. 

* RecursiveAction   non-return시 사용
* RecursiveTask       return시 사용

두 클래스는 추상클래스다. 추상메서드는 단 하나만 있는데 compute()다. 사용법은 아래와 같다.

```java
class SumTask extends RecursiveTask<Long>{
  // ...
  public Long compute(){
    //...
  }
  //...
}

psvm{
  ForkJoinPool pool = new ForkJoinPool();  // 프레임워크에서 제공하는 쓰레드 풀.
  SumTask task = new SumTask();
  Long result = pool.invoke(task); // run()-start()관계처럼 compute()-invoke()관계.
}
```

##### compute()구현

쓰레드를 나누어 메서드를 만들어주어야 한다. 거의 정형인데 재귀함수를 이용한다.

```java
public Long compute(){   // from부터 to까지 더하는 메서드
  long half = (from+to) / 2;   // 절반을 구한뒤 두개로 나눠준다.
  SumTask left = new SumTask(from, half);
  SumTask right = new SumTask(half+1, to);
  
  left.fork();  // 절반 중 왼쪽을 que에 넣는다.
  return right.compute() + left.join();    // 오른쪽은 재귀함수로 다시 반을 나눈다.
  // join은 que에 넣은 결과를 반환하는 것이다. 
}
```

