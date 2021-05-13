function initTmap(position) {
  var map = new Tmapv2.Map("map_div",
    {
      center: new Tmapv2.LatLng(position.coords.latitude, position.coords.longitude), // 지도 초기 좌표
      width: "890px",
      height: "400px",
      zoom: 15
    });
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
