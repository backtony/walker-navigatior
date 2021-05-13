package project.map;


import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TMapDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vo.Result;
import vo.XyLocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/TMap")
public class TMapController {

    private final ObjectMapper objectMapper;
    //private final LocationServiceImpl locationService;
    private final String apiKey = "l7xx2eabb79f0d1f45928c70df41c0313665";


    // 키워드로 검색
    @GetMapping("/keyword")
    @ResponseBody
    public ResponseEntity search_keyword(@RequestParam String keyword) {

        try {
            String reqUrl = searchPlace(keyword); // 요청 url
            HttpURLConnection con = getHttpURLConnection(apiKey, reqUrl); // 요청


            if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
                StringBuilder sb = getStringBuilder(con); // 응답값 json으로 받기

                con.disconnect(); // 연결 끊기

                // 가게명, x,y 10개 뽑기
                TMapDto tMapDto = objectMapper.readValue(sb.toString(), TMapDto.class);

                //가공
                List<XyLocation> xyLocations = DtoToVo(tMapDto);
                Result result = new Result(xyLocations);

                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return "bad";

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    // x가 centerLon
    private List<XyLocation> DtoToVo(TMapDto tMapDto) {
        List<XyLocation> vo = new ArrayList<>();

        List<TMapDto.Poi> poi = tMapDto.getSearchPoiInfo().getPois().getPoi();
        for (TMapDto.Poi ex : poi) {

            XyLocation xyLocation = XyLocation.builder()
                    .name(ex.getName())
                    .x(ex.getNewAddressList().getNewAddress().get(0).getCenterLon())
                    .y(ex.getNewAddressList().getNewAddress().get(0).getCenterLat())
                    .build();
            vo.add(xyLocation);
        }
        return vo;

    }


    private String searchPlace(String keyword) throws UnsupportedEncodingException {
        String word = URLEncoder.encode(keyword, "UTF-8"); // 인코딩

        String reqUrl = "https://apis.openapi.sk.com/tmap/pois?version=1&searchKeyword="
                + word
                + "&appKey="+apiKey;

        return reqUrl;
    }

    private HttpURLConnection getHttpURLConnection(String apiKey, String reqUrl) throws IOException {
        URL url = new URL(reqUrl);  // 유효한 주소인지 확인
        HttpURLConnection con = (HttpURLConnection) url.openConnection();// 연결
        con.setRequestMethod("GET"); // get 방식
        //con.setRequestProperty("Authorization", "KakaoAK " + apiKey); // 헤더에 추가
        return con;
    }

    private StringBuilder getStringBuilder(HttpURLConnection con) throws IOException {
        StringBuilder sb = new StringBuilder(); // json 형식 담을 것
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        in.lines().forEach(line -> {
            sb.append(line);
        });
        in.close();
        return sb;
    }

//    // 주소로 검색 -> 지번 주소, 도로명 주소, 좌표, 우편번호, 빌딩명 등
//    @GetMapping("/address")
//    @ResponseBody
//    public ResponseEntity search_address(@RequestParam String address) {
//        try {
//            String reqUrl = searchPlace(address); // 요청 url
//            HttpURLConnection con = getHttpURLConnection(apiKey, reqUrl); // 요청
//
//
//            if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
//                StringBuilder sb = getStringBuilder(con); // 응답값 json으로 받기
//
//                con.disconnect(); // 연결 끊기
//
//                // 장소명, 주소, 도로명주소, x,y값 뽑기 -> 관련된 검색어로 검색되는데 제일 첫 번째 것이 관련도 최우선
//                KakaoDto kakaoDto = objectMapper.readValue(sb.toString(), KakaoDto.class);
//
//                return ResponseEntity.status(HttpStatus.OK).body(kakaoDto.getDocuments().get(0));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }



//    // db에 저장
//    @GetMapping("/places")
//    @ResponseBody
//    public ResponseEntity save(@RequestParam String keyword) {
//        try {
//            String reqUrl = searchPlace(keyword); // 요청 url
//            HttpURLConnection con = getHttpURLConnection(apiKey, reqUrl); // 요청
//
//            if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
//                StringBuilder sb = getStringBuilder(con); // 응답값 json으로 받기
//
//                con.disconnect(); // 연결 끊기
//
//                // 가게명, 주소, 도로명주소, x,y값 뽑기 -> 관련된 검색어로 검색되는데 제일 첫 번째 것이 관련도 최우선
//                KakaoDto kakaoDto = objectMapper.readValue(sb.toString(), KakaoDto.class);
//
//                locationService.create(kakaoDto.getDocuments().get(0));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }





}
