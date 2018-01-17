# OOP

##객체지향 프로그래밍

가장 중요한 것은 각각의 인스턴스는 다른 변수, 같은 함수, 다른 결과를 가집니다.

### 루비의 OOP 특징

클래스.new() => 인스턴스 생성

생성자는 

def initialize(name) 
   @name = name
end

인스턴스 변수는 @로 설정합니다.

클래스 변수는 @@로 설정합니다. 

클래스 메서드는 self를 붙여야 합니다.

```ruby
def self.number_of_instance
    puts "a......"
end
```

```ruby
class Person
    @@people_count = 0
    
    def initialize(name)
        @name = name
        @@people_count += 1
    end
    
    def run
        puts "#{@name} is running"
    end

    def drink
        puts "#{@name}가 물을 마십니다!"
    end
    
    def self.number_of_instance
        puts "#{@@people_count}만큼 현재 인스턴스가 생성되었습니다."
    end
end

p1 = Person.new("도도")
p1.run
p1.drink

p2 = Person.new("바우")
p2.run
p2.drink

puts Person.number_of_instance
```

### 상속

상속은 이전의 클래스를 이용해서 재활용 / 중복을 제거하는 방법입니다.

`<`를 사용합니다.

루비에서는 다중상속은 지원하지 않으며 믹스인을 사용하여 해결합니다.

```ruby
def run
    puts "#{@name} 이 상대 수비수를 제칩니다."
    super
end
```

super를 통해 상속받은 메서드를 받아오며 super를 쓰지 않으면 상속을 거부하고 메서드를 만들 수 있습니다.