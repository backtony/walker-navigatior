package project.dao;

import org.apache.ibatis.annotations.Param;
import project.dto.AccidentDTO;
import project.routes.model.Accident;

import java.util.List;


public interface AccidentDAO {
     List<AccidentDTO> getAccidents(AccidentDTO param) throws Exception;
     List<Accident> getAccidentsInRange(
             @Param("x1") double x1,
             @Param("y1") double y1,
             @Param("x2") double x2,
             @Param("y2") double y2
     );
     String test() throws  Exception;

}
