package learn.test.dao.mapper;

import learn.test.dao.dto.Enwaste;
import learn.test.dao.dto.EnwasteKey;

public interface EnwasteMapper {
   
    Enwaste selectByPrimaryKey(EnwasteKey key);
    Enwaste selectByEnInfo(Enwaste key);
}