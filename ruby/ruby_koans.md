# Ruby_koans

## Objects

### Difference between to_s and inspect in Ruby

`nil.to_s == ""` 

`nil.inspect == "nil"`

to_s는 기본적으로 string representation(문자열 표현)으로 바꾼것을 반환합니다.

```ruby
User.first.to_s
#<User:0x007fd15cc57238>
```

model이나 ActiveRecord를 출력할 때 object_id에 기반하여 문자열표현으로 바꾸어 반환합니다.

그런데 inspect는 좀 더 개발자 친화적인 버전입니다.

```ruby
puts "This is #{user.inspect} information"
This is #<User id: 1, email: "username@example.com", encrypted_password: "$2a$10$D57y73Q9HUXG9Hym3bLl8.MizOdTRxd6NQH6snHi4Q....", reset_password_token: nil, reset_password_s...> information"
```

객체와 관련된 속성들을 말그대로 "inspect"합니다. 따라서 디버깅이나 로깅에 유용합니다.

추가적으로 puts, print를 사용하면 to_s를 기반으로, p를 사용하면 inspect를 기반으로 작동합니다.

```ruby
p User
puts User
```

[관련 링크](https://rubyinrails.com/2014/11/01/difference-between-to_s-and-inspect-in-ruby/)

### Object_id

object_id 는 int를 반환합니다. `Integer == obj.object_id.class`

## Hash

key가 존재하지 않으면 nil을 반환

### fetch

fetch를 사용하면 값이 없을 때 기본값을 지정할 수 있습니다.

``` ruby
h.fetch("z", "go fish")                 #=> "go fish"
h.fetch("z") { |el| "go fish, #{el}"}   #=> "go fish, z"
# 이런식으로 설정할 수 있다.
```
### unordered

hash는 순서가 없습니다. 따라서 데이터의 순서가 다르더라도 내용이 같으면 같은 해쉬입니다.

### 배열

```ruby
def test_default_value_is_the_same_object
  hash = Hash.new([])

  hash[:one] << "uno"
  hash[:two] << "dos"
  # `<<`` array에 추가한다는 의미입니다

  assert_equal ["uno", "dos"], hash[:one]
  assert_equal ["uno", "dos"], hash[:two]
  assert_equal ["uno", "dos"], hash[:three]

  assert_equal true, hash[:one].object_id == hash[:two].object_id

  #  hash = Hash.new([]) 이것은 배열 오브젝트 하나를 만든 것입니다.
  # 아래와 같이 만들어야 원하는 대로 프로그램이 작동합니다.
end

def test_default_value_with_block
  hash = Hash.new {|hash, key| hash[key] = [] }

  hash[:one] << "uno"
  hash[:two] << "dos"

  assert_equal ["uno"], hash[:one]
  assert_equal ["dos"], hash[:two]
  assert_equal [], hash[:three]
end
```

### Method

루비의 메서드는 괄호 없이 인자를 ,로 구분하여 전달할 수 있습니다.

`result = my_global_method 2, 3`

루비는 return을 적지 않으면 제일 마지막 표현식을 반환하게 됩니다.

```ruby
def method_with_var_args(*args)
  args
end

# 반환문이 없으면 제일 마지막 표현식을 반환합니다.

def test_calling_with_variable_arguments
  assert_equal Array, method_with_var_args.class
  assert_equal [], method_with_var_args
  assert_equal [:one], method_with_var_args(:one)
  assert_equal [:one, :two], method_with_var_args(:one, :two)
end
```

### Control Statement

```ruby
def test_break_statement_returns_values      
  i = 1
  result = while i <= 10
    break i if i % 2 == 0
    i += 1
  end

  assert_equal 2, result
end
```

조금은 다른 코드라 가져와봤다. `break i if i  % 2 == 0` 이라는 부분을 보면 if 뒤의 조건식이 참이면 while반복을 중단하고 i를 반환해라라는 표현식이다. 
기존의 경우 

```java
if (i % 2 == 0) {
  return i;
}
```

라고 작성했을텐데 조금 다른점이다.

### iteration

#### map과 collect의 차이

```ruby
# Creates a new array containing the values returned by the block.

def test_collect_transforms_elements_of_an_array
  array = [1, 2, 3]
  new_array = array.collect { |item| item + 10 }
  assert_equal [11, 12, 13], new_array

  # NOTE: 'map' is another name for the 'collect' operation
  another_array = array.map { |item| item + 10 }
  assert_equal [11, 12, 13], another_array
end
```

실질적으로 차이는 없습니다. 두 메서드는 동일합니다. 함수형 프로그래밍에서는 map이라고 불리고, C++에서는 transform, C#에서는 select 등입니다. 이름의 차이고 여러 이름을 지원하는 것이니 그냥 코딩 컨벤션에 일치하게만 사용하면 됩니다.

[stack overflow 자료](https://stackoverflow.com/questions/5254732/difference-between-map-and-collect-in-ruby)

### select 와 find_all의 미묘한 차이점

```ruby
def test_select_selects_certain_items_from_an_array
  array = [1, 2, 3, 4, 5, 6]

  even_numbers = array.select { |item| (item % 2) == 0 }
  assert_equal [2, 4, 6], even_numbers

  # NOTE: 'find_all' is another name for the 'select' operation
  more_even_numbers = array.find_all { |item| (item % 2) == 0 }
  assert_equal [2, 4, 6], more_even_numbers
end
```

select와 find_all은 모두 iteration에 if문을 더한 것입니다. 배열에서는 차이가 없지만 hash에서는 차이가 생깁니다. select는 hash가 반환되고 find_all은 Enumeration을 반환합니다. 해쉬의 경우 이중배열을 만들어 반환하게 됩니다.

[stack overflow 자료](https://stackoverflow.com/questions/20999192/is-find-all-and-select-the-same-thing)

### inject()

정의는 inject(initial) { |memo, obj| block } 입니다. obj를 반복하는데 memo라는 변수를 initial로 만들어서 사용할 수 있습니다. 반환값은 memo입니다.

```ruby
def test_inject_will_blow_your_mind
  result = [2, 3, 4].inject(0) { |sum, item| sum + item }
  assert_equal 9, result

  result2 = [2, 3, 4].inject(1) { |product, item| product * item }
  assert_equal 24, result2
end
```

## Class

### reader writer

```ruby
class Person
  def name
    @name
  end

  def name=(str)
    @name = str
  end
end

person = Person.new
person.name = 'Dennis'
person.name # => "Dennis"
```

name 이 reader다. name=(str) 이 writer다. 즉 인스턴스변수에 접근할 수 있는 것이다. 근데 이렇게 매번 쓰기 귀찮으니까 reader와 writer를 만들었다.

```ruby
class Person
  attr_accessor :name
  # attr_reader :name
  # attr_writer :name
end
```

이렇게 하면 reader와 writer 모두 간단하게 가능합니다.

[참고자료](https://stackoverflow.com/questions/4370960/what-is-attr-accessor-in-ruby)

### 상속

모든 클래스는 Object의 자손이다. 따라서 아무것도 하지 않은 클래스에도 58개의 메서드가 들어있다. 

```ruby
class Dog
end

def test_objects_have_methods
  fido = Dog.new
  assert fido.methods.size > 0
  # Object.methods.length == 111
  # 상속받은 메서드는 58개(fido.methods.size)
end
```

### 클래스 메서드

static과 같다. `클래스명.메서드명`으로 호출하는 메서드다. 메서드 정의는 두 가지 방법으로 할 수 있다. 클래스명, self 둘 다 가능하고 클래스 안, 밖 모두 가능하다.

```ruby
class Dog2
  def wag
    :instance_level_wag
  end
end

# 클래스 밖에서 정의했다.
def Dog2.wag
  :class_level_wag
end

def test_since_classes_are_objects_you_can_define_singleton_methods_on_them_too
  assert_equal :class_level_wag, Dog2.wag
end
```

``` ruby
class Dog
  # 클래스 안에서 정의했다.
  def Dog.a_class_method       #이 부분에서 Dog를 self로 바꿔도 무관하다.
    :dogs_class_method
  end
end

def test_you_can_define_class_methods_inside_the_class
  assert_equal :dogs_class_method, Dog.a_class_method
end
```

물론 클래스 밖에서는 self의 사용이 안 된다.

또 다른 한가지 방법이 있습니다. 바로 싱글턴 객체를 이용하여 클래스 메서드처럼 약간의 trick을 사용하는 방법입니다.

```ruby
class Dog
  class << self
    def another_class_method
      :still_another_way
    end
  end
end

def test_heres_still_another_way_to_write_class_methods
  assert_equal :still_another_way, Dog.another_class_method
end
```

이런 방식입니다. `Dog.another_class_method`처럼 클래스명.메서드명으로 사용하는것은 같습니다만 `class << self` 를 사용했습니다. eigenclass라고 하며 정확히는 클래스의 싱글턴 객체에 메서드를 추가한 것 입니다.

배열에서도 `<<`를 push 대용으로 사용했었는데 여기서는 다른 개념입니다.

1.9.2버전부터는 obj.singleton_class로 사용한다고 합니다.

참고로 싱글턴의 경우 상속시에도 메서드가 남게됩니다.

[관련자료](https://codequizzes.wordpress.com/2014/04/11/singleton-classes-in-ruby-aka-eigenclasses/)