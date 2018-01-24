# I/O

input과 output을 합친것이다. 말그대로 입출력이다.

입출력을 하려면 데이터를 한쪽에서 다른쪽으로 전달하는 것이 필요한데 이를 *스트림* 이라고 부른다.

여기서의 stream은 람다와 스트림의 stream과는 같은 용어지만 다른 개념이다.

> 쉽게 stream은 통로라고 생각하자. pipe같은 것

큐와 같은 구조다. FIFO다. 예를 들어 우리가 유투브 동영상을 보면 데이터가 순차적으로 보이는 것 처럼 말이다.

### 바이트기반 스트림

스트림은 바이트단위로 데이터를 전송한다.

자바에서는 java.io패키지를 통해 많은 입출력 클래스를 제공하고 있다. 

상위의 클래스는 InputStream, OutputStream이다.

input과 output은 각각 read(), write() 의 추상메서드를 구현하여 사용한다. 이때 read는 반환값이 int이며 write는 void다. 

대상에 따라 File : 파일, ByteArray : byte배열 메모리, Piped : 프로세스, Audio : 오디오 종류를 가진다.

### 보조 스트림

보조스트림은 실제 데이터를 주고 받는 스트림은 아니다. 하지만 기능을 향상시키거나 새로운 기능을 추가할 수 있다. 대표적으로 파일을 읽을 때 성능 향상을 위해BufferedInputStream을 사용한다. 

### 문자기반 스트림

스트림은 기본적으로 1바이트 단위다. 하지만 문자 char형의 경우 2byte를 사용한다. 따라서 문자기반 스트림을 이용하면 훨씬 수월하다.

이름만 Reader, Writer라고 바꿔주면 된다. ByteArrayInputStream의 경우 CharArrayReader로 바뀐다.

바이트 기반과 크게 다르지 않다.

## 바이트기반 스트림

### ByteArrayStream

바이트 배열의 스트림입니다.자주 사용하지는 않지만 가장 기본이 되는 예제입니다.

```java
byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
byte[] outSrc = null;

ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
ByteArrayOutputStream output = new ByteArrayOutputStream();

int data = 0;

while((data = input.read()) != -1) {

  output.write(data);
}

outSrc = output.toByteArray();

System.out.println("input : " + Arrays.toString(inSrc) );
System.out.println("output : " + Arrays.toString(outSrc) );
```

기본적인 입출력 코드다.  input, output을 만든 뒤 배열의 값을 하나씩 차례대로 넣고 output에 저장한다. 그 뒤 마지막에 output을 배열로 반환하였다.

```java
byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
byte[] outSrc = null;
byte[] temp = new byte[4];

ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
ByteArrayOutputStream output = new ByteArrayOutputStream();

System.out.println("input src = " + Arrays.toString(inSrc));

try {
  while (input.available() > 0) {
    System.out.println(input.available());

    input.read(temp);
    output.write(temp);
    System.out.println("temp : " + Arrays.toString(temp));

    outSrc = output.toByteArray();
    System.out.println("output : " + Arrays.toString(outSrc));
  }
} catch (IOException e) {}

/*
input src = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
10
temp : [0, 1, 2, 3]
output : [0, 1, 2, 3]
6
temp : [4, 5, 6, 7]
output : [0, 1, 2, 3, 4, 5, 6, 7]
2
temp : [8, 9, 6, 7]
output : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7]
*/
```

`available()`을 사용하게 되면 현재 가능한 개수를 보여주게 됩니다.  temp의 크기가 4기 때문에 4개씩 가져와서 보여줍니다. 마지막의 경우 2개만 가져오는데 이 경우 8, 9만 덮어쓰기가 되고 나머지 뒤의 6, 7은 그대로 남게됩니다.

### FileStream

가장 많이 사용되는 스트림 중 하나입니다.

```java
public class Stream {

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            FileOutputStream fos = new FileOutputStream(args[1]);

            int data = 0;

            while ((data=fis.read()) != -1) {
                fos.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// > java Stream Stream.java Copy.txt
```

이렇게 하게되면 Copy.txt라는 파일이 생기면서 복사되게 됩니다.

### DataStream

이 친구는 byte단위가 아닌 기본 자료형의 단위로 읽고 쓸 수 있습니다. 