package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.PayMapper;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.pojo.Pay;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class PayServiceImpl implements PayService {

    @Autowired
    private AppUserService userService;
    @Autowired
    private PayMapper payMapper;

    @Override
    public int insertPay(Pay pay) {
       return  payMapper.insertPay(pay);
    }

    @Override
    public Pay selectByOutpaycode(String out_trade_no) {
        return payMapper.selectByOutpaycode(out_trade_no);
    }

    @Override
    public Pay selectByOrderId(String orderId) {
        return payMapper.selectByOrderId(orderId);
    }

    @Override
    public void updateMember(String token) throws Exception{

        AppUser user = userService.getAppUserByToken(token);
        String member = user.getMember();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(new Date());
        if("0".equals(member)){
            user.setMember("1");
            user.setMbeginTime(date1);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 30);
            String date2 = sdf.format(cal.getTime());
            user.setMendTime(date2);
        }else if("1".equals(member)){
            String date3 = user.getMendTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date3));
            cal.add(Calendar.DATE, 30);
            String date2 = sdf.format(cal.getTime());
            user.setMendTime(date2);
        }
        userService.updateAppUser(user);
    }

    @Override
    public void updateBalance(String token,Double total) {
        AppUser user = userService.getAppUserByToken(token);
        Double balance = user.getBalance();
        user.setBalance(balance+total);
        userService.updateAppUser(user);
    }
}
