package com.tindev.mongo.repository;

import com.tindev.mongo.entity.LogByTipo;
import com.tindev.mongo.entity.LogEntity;
import com.tindev.mongo.enums.TipoLog;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<LogEntity, String> {

    List<LogEntity> findAllByTipoLog(TipoLog tipoLog);

    @Aggregation(pipeline = {
            "{ '$unwind' : '$tipoLog' }",
            "{'$group':{ '_id': '$tipoLog', 'quantidade' : {'$sum': 1} }}"
    })
    List<LogByTipo> groupByTipoLogAndCount();

    List<LogEntity> findAllByDataContains(String data);

    Integer countByTipoLog(TipoLog tipoLog);

}
