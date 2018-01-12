# Ruby

### 타입

기본타입은 세가지가 있습니다. 

Numbers, String, Booleans가 있으며 동적타이핑 언어이기 때문에 타입선언은 하지 않고 그냥 쓰면 됩니다.

###puts and print

둘다 화면에 출력하는 기능이다. 다만 puts는 println이고, print는 print다. 개행문자의 차이다.

문법은 세미콜론이나 괄호(parentheses )는 없다. 그냥쓰면된다.

```ruby
puts "What's up?"
print "Oxnard Montalvo"

puts "a의 값은 #{a}입니다."
```

마지막의 경우 `#{}`를 이용하여 변수를 출력했습니다.

###upcase and downcase

보통 uppercase나 lowercase로 알고있는데 여기선 upcase, downcase다. 

더욱 놀라운것은 메서드에 ()가 없다. 따라서 length, upcase, downcase, reverse 모두 괄호를 사용하지 않는다.

### 주석

single line은 '#'를 이용합니다.

multi line은 조금 다릅니다.

```ruby
=begin
I'm a comment!
I don't need any # symbols.
=end
```

이렇게 사용합니다. 좀 귀찮네요.

### Naming convention

camel보다는 _(underscore)를 더 자주 사용합니다. 첫글자는 소문자가 일반적이며 $나 @는 사용은 가능하나 사용하지 않는것이 좋습니다.

첫글자로 숫자는 오지 못합니다.

### chaining

루비에서는 흔히 체이닝이라고 부르는 메서드의 연결형태를 사용하기 쉽습니다. 그 이유는 우선 모든 값은 object기때문에 연결된 메서드를 사용할 수 있습니다.

`name.method1.method2.method3`이런식으로 말입니다.

### 입력값

gets로 입력을 받습니다. 근데 보통 `gets.chomp`로 받는경우가 많습니다. 둘 다 상관은 없지만 `gets`로만 받으면 line break를 인식하기때문에 원하는 결과값이 나오지 않을 수 있습니다.

`chomp`의 역할이 마지막 개행을 없애줍니다.

### !의 역할

`x = x.method` 를 줄여 `x.method!`라고 사용할 수 있습니다. 즉 객체 자체의 값을 변경할 때 !를 사용합니다. 

다른 메서드들은 기본적으로 불변의 원칙을 지키기 때문에 원형이 변형되지 않습니다.