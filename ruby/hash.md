# Hash

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

이렇게 기본값을 설정하게 됩니다.

### sorting

