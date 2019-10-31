package com.qigan.qiganshop.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.qigan.qiganshop.mapper.ManagerMapper;
import com.qigan.qiganshop.pojo.Manager;
import com.qigan.qiganshop.service.ManagerService;
import com.qigan.qiganshop.util.result.PageResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author starlord
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public PageResult getManagerPage(int page, int size, String order, String sort) {
        List<Manager> managers = managerMapper.getManagerPage(page, size, order, sort);
        long total = managerMapper.count();
        return new PageResult(total, managers);
    }

    @Override
    public void save(Manager manager) throws Exception {
        if (StringUtils.isEmpty(manager.getNo())) {
            throw new Exception("工号不允许为空");
        }
        if (StringUtils.isEmpty(manager.getName())) {
            throw new Exception("姓名不允许为空");
        }
        if (StringUtils.isEmpty(manager.getPhone())) {
            throw new Exception("手机号不允许为空");
        }
        final Manager manager1 = managerMapper.getManager(manager.getManagerId());
        int result = -1;
        try {
            if (manager1 == null) {
                result = managerMapper.insert(manager);
            } else {
                manager1.setNo(manager.getNo());
                manager1.setName(manager.getName());
                manager1.setPhone(manager.getPhone());
                result = managerMapper.update(manager1);
            }
        } catch (Exception e) {
            throw new Exception("操作失败");
        } finally {
            if (result != 1) {
                throw new Exception("操作失败");
            }
        }
    }

    @Override
    public void modifyPwd(Manager manager) throws Exception {
        int result = -1;
        try {
            result = managerMapper.update(new Manager(manager.getManagerId(), DigestUtils.md5Hex(manager.getPassword())));
        } catch (Exception e) {
            throw new Exception("操作失败");
        } finally {
            if (result != 1) {
                throw new Exception("操作失败");
            }
        }
    }

    @Override
    public void enable(Manager manager) throws Exception {
        int result = -1;
        try {
            result = managerMapper.update(manager);
        } catch (Exception e) {
            throw new Exception("操作失败");
        } finally {
            if (result != 1) {
                throw new Exception("操作失败");
            }
        }
    }

    @Override
    public Manager getManager(Integer managerId) {
        return managerMapper.getManager(managerId);
    }

    @Override
    public Manager getManager(String no) {
        return managerMapper.getManagerByNo(no);
    }

}
