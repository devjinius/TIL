# looping

##Until

while 문의 반대이다. if / unless와 같은 관계라고 생각하면 된다.

```ruby
i = 0
until i == 6
  i = i + 1
end
puts i
```

`while i == 6` 은 i가 6인 동안 도는것이고, `until i == 6`은 i가 6일 때까지 돌아라 라는 의미입니다. 직관적입니다.

### 증감연산자

`++` `-- ` 는 사용할 수 없습니다. 원래 별로 좋아하지도 않는 연산자니까 자주쓰는 `+=` `-=`을 사용합시다.

## for loop

일단 문법은 이러합니다.

```ruby
for num in 1...15
  puts num
end
```

이럴 경우 1 ~ 14까지 반복합니다.

```ruby
for num in 1..15
  puts num
end
```

이렇게 될 경우 1 ~ 15까지 반복합니다. 마지막값을 포함하게 됩니다.

### {} 와 loop

루비에서 중괄호는 일반적으로 `do - end`로 치환 가능합니다. 그리고 loop라는 것을 이용하여 매우 쉽게 반복도 가능합니다.

```ruby
loop do
  i += 1
  print "#{i}"
  break if i > 5
end
```

### next

continue의 기능을 합니다. if 조건문에 만족하면 반복하지 않습니다.

```ruby
for i in 1..5
  next if i % 2 == 0
  print i
end
```

## iterator

### .each

배열과 같이 반복가능한 객체를 반복할때는 each가 적합합니다.

문법은 아래와 같습니다.

```ruby
array = [1,2,3,4,5]

array.each do |x|
  x += 10
  print "#{x}"
end
```

### times

for loop의 compact버전입니다.

```ruby
10.times {puts  "hello"}
```

이렇게 사용하면 끝입니다.