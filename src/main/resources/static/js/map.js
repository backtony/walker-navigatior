function generateGPSMarker(position, map) {
  var tmapMarker = new Tmapv2.Marker({
    position: new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude),
    map: map,
  });
  return tmapMarker;
}

/*
position -> {latitude, longitude}
 target -> "cctv" or "lamp"
 */
function drawMark(position, map, target){
    var markerList = []
    /*  test code
    var position = [{latitude : '37', longitude : '126'},
        {latitude : '37.001', longitude : '126.001'}]
     */
    position.forEach(function (latlng){
        markerList.push(new Tmapv2.Marker({
            position: new Tmapv2.LatLng(latlng.latitude, latlng.longitude),
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

function initTmap(position) {
  var map = new Tmapv2.Map("map_div", {
      center: new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude), // 지도 초기 좌표
      width: "890px",
      height: "400px",
      zoom: 15
    });
  var marker = generateGPSMarker(position, map);
  // drawMark(position, map, 'lamp');
}

function getLocation(callback) {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(callback);
  } else {
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function load() {
  getLocation(initTmap);
}
