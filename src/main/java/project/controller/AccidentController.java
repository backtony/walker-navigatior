package project.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.dao.AccidentDAO;
import project.dto.AccidentDTO;

import java.util.List;

@RestController
@MapperScan(basePackages="project.dao")//탐색할 패키시 설정
public class AccidentController {
    @Autowired
    private AccidentDAO AccidentDAO;//UserDAO bean을 자동으로 주입

    @GetMapping("/accident")
    public String accidents() throws Exception { //query String으로 county를 받도록 설정
        final AccidentDTO param = new AccidentDTO();//전달 받은 country를 받은 UserDTO 객체 생성 이 객체는 MyBatis에 파라미터로 전달
        //final List<AccidentDTO> accidentList = AccidentDAO.getAccidents(param);// 22번 째 줄에서 생성한 객체를 파라미터로 전달하여 DB로부터 사용자 목록을 불러온다.
        String tet = AccidentDAO.test();
        return tet;
    }

}

