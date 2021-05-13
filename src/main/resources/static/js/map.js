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