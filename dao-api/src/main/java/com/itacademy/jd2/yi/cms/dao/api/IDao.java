package com.itacademy.jd2.yi.cms.dao.api;
import java.util.List;

public interface IDao<ENTITY, ID> {

    ENTITY createEntity();

    ENTITY get(ID id);

    void update(ENTITY entity);

    void insert(ENTITY entity);

    void delete(ID id);

    void deleteAll();

    List<ENTITY> selectAll();
}