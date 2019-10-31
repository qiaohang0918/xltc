package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Invoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvoiceMapper {


    int delete(String invoiceId);

    int insert(Invoice record);

    Invoice findOne(String invoiceId);

    List<Invoice> findPage(@Param("record") Invoice invoice, @Param("page") Integer page, @Param("size") Integer size);

    int update(Invoice record);

    Invoice findByOrderId(String orderId);
}