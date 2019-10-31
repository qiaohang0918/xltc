package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.TbDepositMapper;
import com.qigan.qiganshop.pojo.TbDeposit;
import com.qigan.qiganshop.pojo.TbDepositExample;
import com.qigan.qiganshop.service.XltcDepositService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.XltcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/10/8 11:23
 * @Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XltcDepositServiceImpl implements XltcDepositService {

    @Autowired
    TbDepositMapper depositMapper;


    @Override
    public int insert(TbDeposit tbDeposit) {
        return depositMapper.insert(tbDeposit);
    }

    /**
     * 根据贸易号 查询充值日志记录
     * @param out_trade_no
     * @return
     */
    @Override
    public List<TbDeposit> findByOutTradeNum(String out_trade_no) {
        TbDepositExample example = new TbDepositExample();
        TbDepositExample.Criteria criteria = example.createCriteria();
        criteria.andOutTradeNumEqualTo(out_trade_no);
        return depositMapper.selectByExample(example);
    }


    /**
     * 根据orderId (primaryKey) 修改充值日志记录
     * @param deposit
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(TbDeposit deposit) {
        return depositMapper.updateByPrimaryKeySelective(deposit);
    }

    /**
     * 查询充值记录 (报表使用)
     * @param parms
     * @return
     */
    @Override
    public XltcResult findDepositRecordByConditions(Map<String, String> parms) {
//        String page = parms.get("page");
//        String size = parms.get("size");
        String order_id = parms.get("order_id");
        String deposit_type = parms.get("deposit_type");
        String out_trade_num = parms.get("out_trade_num");
        String user_id = parms.get("user_id");
        String phone = parms.get("phone");
        String status = parms.get("status");
        String transaction_id = parms.get("transaction_id");
        String deposit_content = parms.get("deposit_content");
        String paytime_start = parms.get("paytime_start");
        String paytime_end = parms.get("paytime_end");
        StringBuffer buffer = new StringBuffer(" ");
        if(StringNotNull.check(order_id)){
            buffer.append(" and order_id = '"+order_id+"'");
        }
        if(StringNotNull.check(deposit_type)){
            buffer.append(" and deposit_type = '"+deposit_type+"'");
        }
        if(StringNotNull.check(out_trade_num)){
            buffer.append(" and out_trade_num = '"+out_trade_num+"'");
        }
        if(StringNotNull.check(user_id)){
            buffer.append(" and user_id = '"+user_id+"'");
        }
        if(StringNotNull.check(phone)){
            buffer.append(" and phone = '"+phone+"'");
        }
        if(StringNotNull.check(status)){
            buffer.append(" and status = '"+status+"'");
        }
        if(StringNotNull.check(transaction_id)){
            buffer.append(" and transaction_id = '"+transaction_id+"'");
        }
        if(StringNotNull.check(deposit_content)){
            buffer.append(" and deposit_content like '%"+deposit_content+"%'");
        }
        if(StringNotNull.check(paytime_start)){
            buffer.append(" and paytime >= '"+paytime_start+"'");
        }
        if(StringNotNull.check(paytime_end)){
            buffer.append(" and paytime <= '"+paytime_end+"'");
        }
        buffer.append(" order by paytime desc ");
        List<Map<String,Object>>  mapList = depositMapper.findDepositRecordByConditions(buffer.toString());
        return XltcResult.ok(mapList);
    }
}
