#Django
***

##Site란 App들의 결합
1. App 생성
+ app을 만들때는 `python manage.py startapp <appname>`
2. index 페이지 만들기
+ 폴더의 views.py 수정 필요하다.
```python
from django.http import HttpResponse
def index(request):
	return HttpRestponse("Hello World")
# 기능을 구현
```
장고는 프로젝트 - app - 기능으로 구성되어었다. 위의 코드는 기능을 구현한 것

3. 접근지정조건 설정
+ 프로젝트가 앱을 실행시키는 조건과 앱이 기능을 실행시키는 조건 정의를 해주어야 한다. 
      + 앱 실행은 urls.py의 urlpatterns를 수정한다. 
	      +  ` url(r'^', include('elections.urls')) `
	      +  이때 include는 단지 전달하는 함수이며 추가로 *import* 해줘야 한다.
      +  기능실행은 app디렉토리에 urls.py를 추가하여 패턴을 넣어준다.
	      +  `url(r'^$', views.index)`
	      +  앱에서 기능인 views.index를 실행시킨다.
***
##DB와 모델
1. model class 작성
+ app안의 models.py에 저장
+ models.Model을 상속받은 class 생성
2. db에 저장
+ settings.py의 INSTALLED_APPS 리스트에 앱 이름을 추가해준다.
+ `python manage.py makemigrations`
+ `python manage.py migrate`
***
### 장고 admin과 DB 등록
작성한 모델을 DB와 연동시킬 것임

1. admin 사용자 만들기
+ 콘솔에 `python manage.py createsuperuser`
2. app 디렉에 들어가 admin.py에 candidate(model)추가
+ `admin.site.register(Candidate)`
3.  admin으로 접속해서 instance 생성
+ 이때 객체표현(`__str__`)메소드 오버라이딩
	+ `def __str__ (self): return self.name`
4. 데이터 보여주기
+ views.py에서 model을 import해서 HTML 형식으로 불러준다.
```python
def index(request):
    candidates = Candidate.objects.all()
    str = ""
    for candidate in candidates:
        str += "{}기호 {}번 ({})<BR>".format(
	        candidate.name,
	        candidate.party_number,
	        candidate.area)
        str += candidate.introduction + "<P>"
    return HttpResponse(str)
```
여기까지는 단순 데이터 보여주기 이제 템플릿 이용할 것
+ app/template/app/index.html 생성
+ template안에 DB정보 전달
	+ `context = {'candidates':candidates} # dictionary 형태로`
	+ `return render(request, 'election/index.html', context)`