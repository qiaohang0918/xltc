package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Manager;
import com.qigan.qiganshop.util.result.PageResult;

/**
 * @author starlord
 */
public interface ManagerService {

    PageResult getManagerPage(int page, int size, String order, String sort);

    void save(final Manager manager) throws Exception;

    void modifyPwd(final Manager manager) throws Exception;

    void enable(final Manager manager) throws Exception;

    Manager getManager(Integer managerId);

    Manager getManager(String no);
}
