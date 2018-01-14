### splat

*로 표시한다. argument가 여러개가 들어올 수 있을때 *로 표시하여 메서드를 정의합니다.

```ruby
def what_up(greeting, *friends)
  friends.each { |friend| puts "#{greeting}, #{friend}!" }
end

what_up("What up", "Ian", "Zoe", "Zenas", "Eleanor")

```

### 오름차순 내림차순

To sort our books in ascending order, in-place

`books.sort! { |firstBook, secondBook| firstBook <=> secondBook }`

Sort your books in descending order, in-place below

`books.sort! { |f, s| s <=> f}`

5<=>5을 하면 0이 리턴됩니다. 

4<=>5을 하면 -1이 리턴됩니다.

 5<=>4을 하면 1이 리턴됩니다. 

기본적으로 두 숫자가 같으면, 0이 리턴됩니다. 작은 수에서 큰 수로 정렬될 때는 -1 그 반대의 경우 1이 리턴됩니다.