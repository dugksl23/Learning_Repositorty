# Docker_Basic


---

# 2. 목차

#### 1. 프로젝트 소개
#### 2. 들어가며...
#### 3. 도커란?
#### 4. 도커의 핵심 요소
      - 컨테이너
      - 이미지
      - 허브

---

## 1.  프로젝트 소개

도커에 대한 이해 및 도커를 활용한 간단한 서비스 구현.

## 2. 들어가며...

도커를 사용하기 이전까지의 상황은 어떠할까? 각각의 서비스별로 vm을 만들고, 해당 vm 서비서와 관련된 환경세팅을 진행해야 한다. 때로는 하나의 서버에 동일 라이브러리이지만, 버전이 다른 경우에는 여러 개의 프로그램을 설치해야 하는 문제가 발생한다. 이는 서버를 관리함에 있어서 복잡하고 어려움을 더하는 행위이다. 도커의 등장 배경은 이러한 불편함으로부터 비롯되었다.

## 3. 도커란?

컨테이너 기반의 오픈소스 가상화 플랫폼이다. 컨테이너라는 단어에 떠오르는 이미지는, 커다란 네모 화물 수송용 박스를 생각할 수 있을 것이다. 컨테이너 내부에는 당연히 여러가지 제품들이 들어가 있으며, 운송수단의 개념으로서 컨테이너 선이나 다양한 트레일러 등에서 사용되어지고 있다.

서버에서 이야기하는 컨테이너 또한 비슷한데 다양한 프로그램과 실행환경을 컨테이너로 추상화하는 동일한 인터페이스를 제공하며, 프로그램의 배포 및 관리를 단순하게 만들어준다. 예를 들면, 라이브러리, DB 서버, 메시지 큐, API 서버 등 어떤 프로그램도 컨테이너로 추상화할 수 있고, 실행 환경 또한 Laptop, Cloud 등 어디서든 실행할 수 있다.


### 4. 도커의 핵심 요소

1) 컨테이너

컨테이너는 격리된 공간에서 프로세스가 동작하는 기술이다. 일반적으로 우리에게 익숙한 방식은 OS를 가상화 하는 것이다. 우리에게 익숙한 VMware를 이용하여 기존 Physical pc 기반에 VMware를 통해 호스트 OS 위에 게스트 OS 전체를 가상화하여 이용하는 방식이다. 이 방식은 여러가지 OS를 가상화 할 수 있고, 비교적 사용법이 간단하지만, 무겁고 느려서 운영환경에선 사용하는 것은 효율적이지 못하다.

따라서 이러한 상황을 개선하기 위해 Docker와 같이 CPU의 가상화 기술을 이용하여 AWS와 같은 클라우드 서비스에서 가상 컴퓨팅 기술의 기반이 되었다.

<img width="673" alt="스크린샷 2021-11-02 오후 6 05 15" src="https://user-images.githubusercontent.com/68539491/139817377-fce59f9b-21ba-492a-bbca-2361a67aa530.png">

--- https://subicura.com/2017/01/19/docker-guide-for-beginners-1.html

