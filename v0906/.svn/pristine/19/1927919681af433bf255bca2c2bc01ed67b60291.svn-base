package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.controller.utils.PublicControl;
import com.qigan.qiganshop.mapper.BalanceMapper;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.Balance;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.BalanceService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class BalanecServiceImpl implements BalanceService {
    @Autowired
    private BalanceMapper mapper;
    @Autowired
    private AppUserService service;

    /**
     * 增加明细
     *
     * @param balance
     * @return
     */
    @Override
    public int insert(Balance balance, String token) {
        AppUser appUser = service.getAppUserByToken(token);
        if (!PublicControl.checkNotNull(appUser)) {
            return 0;
        }
        balance.setUserId(appUser.getUserId());
        Balance oldBalance = mapper.findByUserId(balance.getUserId());
        balance.setBalanceId(UUID.randomUUID().toString().replace("-", ""));
        if (oldBalance != null) {
            balance.setOldMoney(oldBalance.getMoney());
        } else {
            balance.setOldMoney(0.00);
        }
        String moneyFlow = balance.getMoneyFlow();
        if ("+".equals(moneyFlow)) {
            balance.setMoney(balance.getOldMoney() + balance.getFlowNum());
        } else if ("-".equals(moneyFlow)) {
            balance.setMoney(balance.getOldMoney() - balance.getFlowNum());
        } else {
            return 0;
        }
        balance.setCreateTime(System.currentTimeMillis() + "");


        return mapper.insert(balance);
    }

    /**
     * 查询单个订单
     *
     * @param balanceId
     * @return
     */
    @Override
    public Balance findOne(String balanceId) {
        Balance balance = mapper.findOne(balanceId);
        MillisToDate(balance);
        return balance;
    }

    private void MillisToDate(Balance balance) {
        String createTime = balance.getCreateTime();
        Date date = new Date();
        date.setTime(Long.valueOf(createTime));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        balance.setCreateTime(format);
    }

    /**
     * 查询消费记录
     *
     * @param beginTime
     * @param endTime
     * @param token
     * @return
     */
    @Override
    public PageResult findPage(String beginTime, String endTime, String token, Integer pageNum, Integer sizeNum) {
        beginTime = DateToMillions(beginTime).toString();
        endTime = (DateToMillions(endTime) + 1000L) + "";
        AppUser appUser = service.getAppUserByToken(token);
        if (NotNull.checkString(appUser.getUserId())) {
            List<Balance> all = mapper.findPage(beginTime, endTime, appUser.getUserId(), null, null);
            List<Balance> page = mapper.findPage(beginTime, endTime, appUser.getUserId(), (pageNum - 1) * sizeNum, sizeNum);
            ArrayList<Balance> result = new ArrayList<>();
            for (Balance balance : page) {
                MillisToDate(balance);
                result.add((Balance) NotNull.checkNull(balance));
            }
            return new PageResult(all.size(), result);
        }
        return new PageResult(0, new ArrayList());
    }

    private Long DateToMillions(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long dateToSecond = 0;
        try {
            dateToSecond = sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateToSecond;
    }

}
