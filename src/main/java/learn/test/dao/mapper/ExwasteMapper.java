package learn.test.dao.mapper;

import learn.test.dao.dto.Exwaste;

public interface ExwasteMapper {
   
    Exwaste selectByPrimaryKey(Exwaste key);
    Exwaste selectByExInfo(Exwaste key);
}