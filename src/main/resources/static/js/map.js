var currentPosition;
var map;
var marker_s, marker_e, marker_p1, marker_p2;
var totalMarkerArr = [];
var drawInfoArr = [];
var resultdrawArr = [];
// keyword 통한 검색 -> response 로 목록 받아옴
function searchAddress(keyword, target){
  $.ajax({
    url: `/TMap/keyword?keyword=${keyword}`,
    type: 'GET',
    dataType: "json",
    contentType: "application/json",
    success: function (data) {
      drawAddressList(data["address"], target);
    },
    error: function (err) {
      console.error("/TMap/keyword error!");
      console.log(err);

      //            window.location.reload();
    }
  })
}

// 주소 검색 통해서 얻어온 목록
function drawAddressList(address, target){
  $("section#destination-list").text('');
  if (address.length > 0){
    $.each(address, function(i, place){
      let place_name = place["name"];
      let x = place['x'];
      let y = place['y'];
      let listEl = `<li class="nav-item">
              <a href="#" class="nav-link"  data-target="${target}" data-x="${x}" data-y="${y}">
                <i class="icon-location3"></i>
                <span>
                  ${place_name}
                </span>
              </a>
            </li>`;
      $("section#destination-list").append(listEl);
    })
  } else {
    $("section#desstination-list").html(`
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="icon-location3"></i>
                <span>
                  검색된 장소가 없습니다. 다시 검색해주세요.
                </span>
              </a>
            </li>
        `)
  }
}

// 주소 목록중 하나 선택
function chooseDestination(sectionEl){
  $(sectionEl).on("click", "li", function(e){

    input$El = $(`input#${$(e.target).data('target')}`);
    $(input$El).val($(e.target).text().trim());
    //        $(e.target).addClass("active");
    $("section#destination-list").text('');


  })
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

  $(".search-button").on("click", function(e){
    let input$El = $(e.target).siblings("input");
    let keyword = input$El.val();
    if(keyword != ''){
      searchAddress(keyword, input$El.attr("id"));
    } else {
      alert("검색어를 입력하세요");
    }
  })

  chooseDestination("section#destination-list");
}


function initTmap(position) {
  currentPosition = position;
  map = new Tmapv2.Map("map_div", {
    center: new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude), // 지도 초기 좌표
    width: "100vw",
    height: "100vh",
    zoom: 15
  });

  let lat = position.coords.latitude;
  let lng = position.coords.longitude;
  console.log(`current: ${lat}, ${lng}`);
}
/*
position -> {latitude, longitude}
 target -> "cctv" or "lamp"
 */
function drawMark(positions, map, target){
    var markerList = []
    /*  test code
    var position = [{latitude : '37', longitude : '126'},
        {latitude : '37.001', longitude : '126.001'}]
     */
    positions.forEach(function (latlng){
        markerList.push(new Tmapv2.Marker({
            position: new Tmapv2.LatLng(latlng.y, latlng.x),
            map: map,
            icon : "https://github.com/makerdark98/walker-navigator/blob/map_rendering/src/main/resources/img/"+target+".png?raw=true",
            iconSize : new Tmapv2.Size(24,24)
        }));
    })
    return markerList;
};
// latitude, longitude's ratio is different
function latToMeter(diffInCoord){
    return diffInCoord * 109958.489129649955
}
function lngToMeter(diffInCoord){
    return diffInCoord * 88740
}

function getLocation(callback) {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(callback);
  } else {
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

/**
 * x: longitude, y : latitude
 dummyData
 127.0451583, 37.503595
 127.0587325, 38.49992264
 **/

function test() {
  fetchRoutes(currentPosition.coords.longitude, currentPosition.coords.latitude, 127.0587325, 37.49992264)
    .then(candidates => {
      for (var i = 0; i < candidates.length; i += 1) {
        try {
          if (candidates[i].routes) drawRoute(candidates[i].routes);
          drawMark(candidates[i].cctvs, map, "cctv");
          drawMark(candidates[i].lamps, map, "lamp");
        } catch (err) {
          console.log(err);
        }
      }
    });
}

function fetchRoutes(startX, startY, endX, endY) {
  var req = fetch(`routes/path?startX=${startX}&startY=${startY}&endX=${endX}&endY=${endY}`).then(res=>res.json());
  return req;
}

function addComma(num) {
  var regexp = /\B(?=(\d{3})+(?!\d))/g;
  return num.toString().replace(regexp, ',');
}

function drawLine(arrPoint) {
  var polyline_;

  polyline_ = new Tmapv2.Polyline({
    path : arrPoint,
    strokeColor : "#DD0000",
    strokeWeight : 6,
    map : map
  });
  resultdrawArr.push(polyline_);
}


function drawRoute(routes) {
  var resultData = routes.features;
  /*
  if (resultdrawArr.length > 0) {
    for ( var i in resultdrawArr) {
      resultdrawArr[i]
        .setMap(null);
    }
    resultdrawArr = [];
  }
  */

  drawInfoArr = [];

  for ( var i in resultData) {
    var geometry = resultData[i].geometry;
    var properties = resultData[i].properties;

    if (geometry.type == "LineString") {
      for ( var j in geometry.coordinates) {
        var convertChange = new Tmapv2.LatLng(geometry.coordinates[j][1], geometry.coordinates[j][0]);
        drawInfoArr.push(convertChange);
      }
    } else {
      var markerImg = "";
      var pType = "";
      var size;

      if (properties.pointType == "S") { 
        markerImg = "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png";
        pType = "S";
        size = new Tmapv2.Size(24, 38);
      } else if (properties.pointType == "E") {
        markerImg = "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png";
        pType = "E";
        size = new Tmapv2.Size(24, 38);
      } else {
        markerImg = "http://topopen.tmap.co.kr/imgs/point.png";
        pType = "P";
        size = new Tmapv2.Size(8, 8);
      }
      marker_p = new Tmapv2.Marker(
        {
          position : new Tmapv2.LatLng(geometry.coordinates[1], geometry.coordinates[0]),
          icon : markerImg,
          iconSize : size,
          map : map
        });
    }
  }
  drawLine(drawInfoArr);
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
