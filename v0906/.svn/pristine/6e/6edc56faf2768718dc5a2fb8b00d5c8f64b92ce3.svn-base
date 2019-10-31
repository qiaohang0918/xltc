package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerMapper {

    int insert(Manager manager);

    int update(Manager manager);

    List<Manager> getManagerPage(@Param("page") int page, @Param("size") int size, @Param("order") String order, @Param("sort") String sort);

    long count();

    Manager getManager(Integer managerId);

    Manager getManagerByNo(String no);
}
