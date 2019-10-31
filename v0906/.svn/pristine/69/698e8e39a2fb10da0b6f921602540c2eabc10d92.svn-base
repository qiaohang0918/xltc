package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.InvoiceMapper;
import com.qigan.qiganshop.pojo.*;
import com.qigan.qiganshop.pojo.group.GroupOrder;
import com.qigan.qiganshop.pojo.synchronization.ResultCart;
import com.qigan.qiganshop.service.InvoiceService;
import com.qigan.qiganshop.service.OrderItemService;
import com.qigan.qiganshop.service.OrderService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import com.qigan.qiganshop.util.result.PageResult;
import com.qigan.qiganshop.util.result.format.JsonResult;
import com.qigan.qiganshop.util.result.format.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.regex.Pattern.compile;

/**
 * 发票服务层
 *
 * @author wanghao
 * @time 2019-05-08 15:31
 */
@SuppressWarnings("all")
@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceMapper mapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    /**
     * 新增
     *
     * @param invoice
     * @return
     */
    @Override
    public int add(Invoice invoice) {
        invoice.setInvoiceId(System.currentTimeMillis());
        return mapper.insert(invoice);
    }

    /**
     * 查询单个
     *
     * @param invoiceId
     * @return
     */
    @Override
    public Invoice findOne(String invoiceId) {
        return mapper.findOne(invoiceId);
    }

    /**
     * 三合一
     *
     * @param invoice
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult findPage(Invoice invoice, Integer page, Integer size) {
        List<Invoice> result = new ArrayList<>();
        if (page != null && size != null) {
            result = mapper.findPage(invoice, (page - 1) * size, size);
        } else {
            result = mapper.findPage(invoice, null, null);

        }
        return new PageResult(mapper.findPage(invoice, null, null).size(), result);
    }

    /**
     * 根据订单 ID 查询发票
     *
     * @param orderId
     * @return
     */
    @Override
    public Invoice findByOrderId(String orderId) {
        return mapper.findByOrderId(orderId);
    }

    /**
     * 用户合并开发票,可以开一个订单,也可多个
     *
     * @param userId
     * @param orderIds
     * @param invoice
     * @return
     */
    @Override
    public Integer newInvoice(String userId, String orderIds, Invoice invoice) {
        String[] orders = orderIds.split(",");

        invoice.setInvoiceId(System.currentTimeMillis() + new Random().nextInt(100) * 1000000);

        StringBuilder detail = new StringBuilder();
        Double invoiceAmount = 0.00;
        for (String orderId : orders) {
            Order one = orderService.findOne(orderId);

            if (one == null || !userId.equals(one.getUserId()) || one.getInvoiceId() != null) {
                // 查无此订单||该订单不属于该用户||订单已开过发票
                return -1;
            }
            // todo 校验订单状态
            List<OrderItem> byOrderId = orderItemService.findByOrderId(one.getOrderId());
            for (OrderItem byItem : byOrderId) {
                detail.append(byItem.getGoodsName()).append(" \t");
                detail.append(byItem.getItemName()).append("  \t");
                detail.append(byItem.getUnit() == null ? "1件" : byItem.getUnit() + "*" + byItem.getCount() + " \t");
                detail.append("￥").append(byItem.getSubTotal()).append("  \t");
                detail.append("\n");
            }
            invoiceAmount += one.getPayMoney();
        }
        invoice.setInvoiceAmount(invoiceAmount);
        invoice.setInvoiceContent(detail.toString());
        for (String order : orders) {
            Order updateInvoice = new Order();
            updateInvoice.setOrderId(order);
            updateInvoice.setInvoiceId(invoice.getInvoiceId());
            orderService.update(updateInvoice);
        }
        return null;
    }

}
