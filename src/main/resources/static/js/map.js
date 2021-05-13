function initTmap(position) {
  var map = new Tmapv2.Map("map_div",
    {
      center: new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude), // 지도 초기 좌표
      width: "100vw",
      height: "100vh",
      zoom: 15
    });

    let lat = position.coords.latitude;
    let lng = position.coords.longitude;
    console.log(`current: ${lat}, ${lng}`);

    // get current location name as start point

    // 
}

// keyword 통한 검색 -> response 로 목록 받아옴
function searchAddress(keyword){
    $.ajax({
        url: `/Tmap/keyword?keyword=${keyword}`,
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            drawAddressList(data);
        },
        error: function (err) {
            console.error("/Tmap/keyword error!");
            console.log(err);
            alert(err);
            window.location.reload();
        }
    })
}

// 주소 검색 통해서 얻어온 목록
function drawAddressList(response){
    console.log(response)
    debugger
}

// 주소 목록중 하나 선택
function chooseDestination(el){

    // set el && form 

}

// 길찾기 요청
function requestNavigation(form){

}


function getLocation(callback) {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(callback);
  } else {
    locationErr(error, app) 
  }
}

function load() {
  getLocation(initTmap);
}

function locationErr(error, app) {

    switch (error.code) {
        case error.PERMISSION_DENIED:
            let result = confirm("위치 권한이 없습니다. 설정에서 위치 추적 권한을 허용해주세요.")
            if(result){
                if (window.Android) {
                    window.location = "package:root=location-settings"
                }
            }
            
            break;
        case error.POSITION_UNAVAILABLE:
            console.warn("POSITION_UNAVAILABLE")
            break;
        case error.TIMEOUT:
            console.warn("TIMEOUT ERR")
            break;
        case error.UNKNOWN_ERR:
            console.warn("UNKNOWN_ERR")
            break;
    }
}