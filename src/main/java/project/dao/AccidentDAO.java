package project.dao;

import project.dto.AccidentDTO;

import java.util.List;

public interface AccidentDAO {
     List<AccidentDTO> getAccidents(AccidentDTO param) throws Exception;
     String test() throws  Exception;

}
