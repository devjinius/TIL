# 제어문

## if

루비의 if는 indentation과는 무관합니다.

if 문 뒤에 expression이 온 뒤 python의 경우 : 을 붙이고 indentation으로 구분합니다. 그러나 루비는 상관없이 작동하지만 coding convention상 띄우는 것을 권장합니다. 

또한 block이 없기때문에 end로 구분합니다.

```ruby
if 1 < 2
  print "I'm getting printed because one is less than two!"
end
```

### elsif

else if의 경우 문법이 elsif입니다.

```ruby
if x < y 
  puts "x is less than y!"
elsif x > y
  puts "x is greater than y!"
else
  puts "x equals y!"
end
```

### unless

루비에 있는 기능으로 if의 반대다. expression이 false일 때 실행된다.

### ?

루비에서 ?로 끝나는경우 true나 false입니다.

따라서 `"input".include? "s"` 라고 물을경우 evaluate하면 false가 됩니다.