### splat

*로 표시한다. argument가 여러개가 들어올 수 있을때 *로 표시하여 메서드를 정의합니다.

```ruby
def what_up(greeting, *friends)
  friends.each { |friend| puts "#{greeting}, #{friend}!" }
end

what_up("What up", "Ian", "Zoe", "Zenas", "Eleanor")

```

### Comparable

To sort our books in ascending order, in-place

`books.sort! { |firstBook, secondBook| firstBook <=> secondBook }`

Sort your books in descending order, in-place below

`books.sort! { |f, s| s <=> f}`

* 5<=>5을 하면 0이 리턴됩니다. 
* 4<=>5을 하면 -1이 리턴됩니다.
* 5<=>4을 하면 1이 리턴됩니다. 

기본적으로 두 숫자가 같으면, 0이 리턴됩니다. 작은 수에서 큰 수로 정렬될 때는 -1 그 반대의 경우 1이 리턴됩니다.

sorting할때는 이렇게 사용합니다.

```ruby
a = [1,2,3,4]

a.sort! do |first, second|
  first <=> second  # 오름차순
  second <=> first  # 내림차순
end
```

### Default parameter

기본값을 설정하면 값이 들어오지 않았을 때 기본값을 가지게 됩니다. javascript의 extend를 이용한 기본값 설정과 하고자하는 바는 비슷합니다.

```ruby
def foo(x, y=0)
	return x + y
end

foo(3, 2)    # 5
foo(3)       # 3
```

