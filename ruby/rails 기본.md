# Rails

## html.erb

embeded ruby입니다. html문서안에서 ruby 코드를 작성할 수 있습니다. 컨트롤러의 변수도 쉽게 사용 가능합니다.

+ <% @변수명 %>
+ <%= @변수명 %>

두가지 방식으로 루비코드를 사용할 수 있는데 전자는 보이지 않고 후자는 보여지는 코드입니다.

## 컨트롤러 만들기

`rails generate controller home index`

`rails g controller home index`라고 쓰면 만들어지고 

`rails destroy controller home`

`rails d controller home` 라고 쓰면 지워집니다.

cli를 사용해서 생성하면 뷰, 컨트롤러, routes의 추가 등 모두 같이 만들어집니다.

```ruby
<% 3.times do %>
    <%= @msg %>
<% end %>
```

이런식으로 활용합니다.

중요한 점은 코딩컨벤션 상 레일즈의 컨트롤러는 복수입니다. Users, Posts 이렇게 말입니다. 항상 유의해서 생성합시다.

## params

루비에서도 역시 param을 사용하여 지난 페이지에서 넘어온 값을 사용한다. 약간의 문법이 다르지만 거의 동일하다.

`params[:username]`으로 사용한다. 딱히 할 것은 없고 form에 담긴 값이 해쉬로 값이 넘어옵니다.

`{"username"=>"dkdkdk", "controller"=>"home", "action"=>"result"}` 의 형식으로 넘어옵니다. 컨트롤러에서 puts로 찍어본 것입니다. "속성" : "속성값"의 형태입니다.

## 랜덤뽑기

```ruby
컨트def result
  result = ['바보', '멍청이', '갑순이', '흑두루미', '코인충', '꿀잼']        # 배열
  r_hash = Hash.new												   # 결과를 담을 해쉬
  # @username = params[:username]  중요x
  2.times do |i|												# 두번 반복
    r_number = rand(result.length)								   # rand사용해서 개수만큼 돌림
    r_hash[i] = result.delete_at(r_number)							# delete_at이라는 메서드는 index값 반환하고 제거함
  end
  @first = r_hash[0]
  @second = r_hash[1]
end
```

중복을 제거하기 위해 해쉬를 사용하여 돌렸습니다. 그런데 더 '루비'스럽게 하려면

```ruby
def result
  result = ['바보', '멍청이', '갑순이', '흑두루미', '코인충', '꿀잼']     # 배열
  #@username = params[:username]
  ran_result = result.sample(2)									  # 배열.sample(2)를 사용하여 매우 간단하게 중복을 제거한 배열생성가능
  @first = ran_result[0]
  @second = ran_result[1]
end
```

더 나아가서 새로운 배열을 생성할때도 랜덤으로 생성이 가능합니다.

```ruby
(1..5).to_a.sample(3)   # 1부터 5까지 배열을 만드는데 샘플(랜덤)로 3개만
```

이런식으로 직관적이고 간결하게 생성이 가능합니다.

## routes.rb

`/`는 경로, `#` 는 액션을 표시합니다.

```ruby
Rails.application.routes.draw do
  get 'home/index'
  get 'hel--lo--' => 'home#result'
  root 'home#index'
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
```

이렇게 사용할 수 있습니다. root는 welcome page를 설정합니다.

home / index로 보내라 라는의미고, => 를 사용해서 다른 액션을 취하게 할 수 있습니다. `home#index`는 home 컨트롤러의 index액션을 취해라라는 뜻입니다.

##컨트롤러와 뷰

컨트롤러는 액션의 집합이며

액션은 뷰로 같은 이름을 만들면 자동으로 대응됩니다.