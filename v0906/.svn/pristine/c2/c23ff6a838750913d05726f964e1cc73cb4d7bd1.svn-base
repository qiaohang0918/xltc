package com.qigan.qiganshop.service;

import com.qigan.qiganshop.pojo.Invoice;
import com.qigan.qiganshop.util.result.PageResult;

import java.util.List;

/**
 * 发票服务层
 *
 * @author wanghao
 * @time 2019-05-08 15:28
 */
public interface InvoiceService {
    /**
     * 新增
     *
     * @param invoice
     * @return
     */
    int add(Invoice invoice);

    /**
     * 查询单个
     *
     * @param invoiceId
     * @return
     */
    Invoice findOne(String invoiceId);

    /**
     * 三合一
     *
     * @param invoice
     * @param page
     * @param size
     * @return
     */
    PageResult findPage(Invoice invoice, Integer page, Integer size);

    /**
     * 根据订单 ID 查询发票
     *
     * @param orderId
     * @return
     */
    Invoice findByOrderId(String orderId);

    /**
     * 用户合并开发票,可以开一个订单,也可多个
     *
     * @param userId
     * @param orderIds
     * @param invoice
     * @return
     */
    Integer newInvoice(String userId, String orderIds, Invoice invoice);
}
