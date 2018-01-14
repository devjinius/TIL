##Hash

루비의 해쉬는 자바스크립트의 object, python의 dictionaries, java의 map과 같습니다. 키 - 값의 쌍으로 이루어져 있습니다.

```ruby
hash = {
  key1 => value1,
  key2 => value2,
  key3 => value3
}
```

이렇게 구성됩니다. 이게 리터럴 방식입니다.

`my_hash = Hash.new`처럼 빈 해쉬를 만들 수도 있습니다.

그 뒤 `my_hash["hi"] = 2` 이렇게 편하게 값을 추가하면 됩니다.

### 기본값

기본값을 설정할 수 있습니다.

```ruby
h = Hash.new("nothing here")

h["hi"]

puts h["hi"]  #"nothing here"
```

이렇게 기본값을 설정하게 됩니다. 기본값을 설정하게 되면 key가 없는 값에 접근했을때 nil이 아니라 기본값이 나온다는 점입니다. 만일 기본값 설정이 되어있지 않다면 nil값을 반환하게 됩니다.

### 1.9버전

1.9버전부터 hash를 심볼과 함께 사용하여 변형하는 방법이 생겼습니다.

```ruby
movies = {
  titanic: "Romantic",
  harry: "sf"
  }
```

이런식으로 사용합니다. 변경된 사항으로는

1. 콜론이 앞이 아니라 뒤로 붙어도 됌.
2. `=>`이 사라짐

입니다.

### select method

each + if입니다. 루핑 시에 특정 조건에 만족하는 결과를 뽑아낼때 사용합니다. filter와 비슷합니다.

### each_key, each_value

```ruby
my_hash = { one: 1, two: 2, three: 3 }

my_hash.each_key { |k| print k, " " }
# ==> one two three

my_hash.each_value { |v| print v, " " }
# ==> 1 2 3
```

string으로 반환하며 뒤에 delemeter를 설정합니다.

## symbol

:(colon)으로 시작하는 심볼은 일종의 이름입니다. 문자열과는 다릅니다.

```ruby
puts "string".object_id
puts "string".object_id

puts :symbol.object_id
puts :symbol.object_id
```

object_id는 고유의 object id를 반환합니다. 매번 새로운 객체를 만드는 string은 당연하게도 다른 값을 반환하지만 심볼은 같은 값을 반환하게 됩니다.

### 사용하는 곳

주로 hash의 키와 메서드 이름을 참조하는것에 사용됩니다.

```ruby
sounds = {
  :cat => "meow",
  :dog => "woof",
  :computer => 10010110,
}
```

이렇게 키로 사용되면 좋은 이유는 세가지가 있습니다.

1. immutable하기 때문에 side effect에 대한 걱정이 없습니다.
2. 오직 한번만 생성되기 때문에 메모리 소요가 적습니다.
3. 1번과 2번의 이유로 속도가 빠릅니다.

### string과 변환

```ruby
:sasquatch.to_s
# ==> "sasquatch"

"sasquatch".to_sym
# ==> :sasquatch
"sasquatch".intern
# 같은 역할을 하는 다른 메서드입니다.
```

이렇게 변형이 가능합니다.

