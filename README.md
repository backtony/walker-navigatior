# 👋팀 소개

![image](/libs/team.PNG)

- #### 팀 이름 : (주).진섭이네
- #### 팀장 : 최준성
- #### 팀원 : 김준기,양진우,이철희,이민욱,백동진
- #### 역할 :

| 리드프로그래밍 | 프론트 | 백엔드
|---|---|---|  
| - 이민욱 | - 이철희 | - 백동진 </br> - 양진우 </br> - 김준기 </br> - 최준성



</br></br>
# 데모 링크
  * [아남타워-서울대도초등학교](https://sharedws-unibb.run.goorm.io/data1)
  * [삼성타워팰리스 2차-국악고등학교](https://sharedws-unibb.run.goorm.io/data2)
  * [삼성타워팰리스 2차-천호시장앞사거리](https://sharedws-unibb.run.goorm.io/data3)
  * [실제 사이트(인원제한있음)](https://sharedws-unibb.run.goorm.io/)
# 📝개요 
  서울시는 2013년부터 안심귀가서비스를 시행했고 이후 5년간 약 54만건의 서비스가 진행되었습니다. 
  하지만 부실한 사후관리 탓에 '전시행정' 이라는 지적이 나오고 있을 뿐만 아니라 점차 예산까지 감소하고
  있습니다. 특히 번화가와 1인 가구가 밀집한 마포구의 안심귀가 스카우트는 10명에 불과하고, 최근 공분을 샀던 
  '전처 살인사건' 범행지인 서울 강서구 등촌동은 서비스조차 제공되지 않는 지역으로 확인되었습니다.<br>
  저희는 이러한 문제를 해결하고자 공공 API를 활용하여 보행자에게 안전한 경로의 길을 제공해주는 서비스를 만들게 되었습니다.
  
</br></br>
# 페르소나
+ 야간 보행자
+ 교통약자(노약자, 아동 등)
+ 초행길을 걷는 사람

# 📈아키텍쳐
![image](/libs/structure.PNG)

</br></br>

# ⚓️ 사용한 공공데이터
+ 서울특별시 보안등(가로등) 데이터
+ 서울특별시 CCTV 데이터
+ 교통 약자 사고 다발 지점 데이터
    - 강남구, 강동구, 서초구, 송파구 데이터 한정으로 사용


</br></br>
# 📐동작 구현

1. 출발지, 도착지 입력
2. 안전도 가중치 경로추천 알고리즘에 의해 여러가지 경로 추천
3. 경로 선택
4. 네비게이션 동작

</br></br>

## 경로추천 알고리즘
* ![equation](https://chart.apis.google.com/chart?cht=tx&chl=(%5Ctext%7Bdanger%20rate%7D)%20%3D%20%5Cfrac%7B%5Ctext%7B%5C%23accident%7D%20%5Ctimes%20(%5Ctext%7B%5C%23death%7D%20%2B%201)%7D%7Bmin_%7B%5Cforall%20point%7D(distance)%7D)

```
findRoute(start, end):
  safe_routes = []
  sectors = seperate_sector(start, end)
  
  for sector in sectors:
    for (cctv, lamp) in random(all cctv, lamp):
      sub_route = Tmap.getRoute(start, (cctv, lamp), end)
      danger_rate = ( accident * (death + 1) ) / min (distance)
      
    route = accumulate(sub_route)
    
    if danger_rate < threshold:
        safe_routes.append(route)
        
  return safe_routes
```

# 👀실행 화면
## 웹 화면
![image](/libs/operation_web.PNG)
</br></br>

## 모바일 화면
![image](/libs/operation_mobile.png)

안드로이드 프로젝트 주소 : https://github.com/chulhee23/walker-navigator-adnroid

</br></br>
# 🎁 편의성
+ 프로젝트 제한사항 -> 웹서비스
+ 길찾기 서비스 -> 대부분이 모바일로 사용
+ 웹서비스 -> 앱으로 패키징 -> 모바일로 사용 가능 -> 사용성 증대

</br></br>
# 🚀 기대 효과
+ 안전 귀가
+ 보행자 부주의 사고 감소

