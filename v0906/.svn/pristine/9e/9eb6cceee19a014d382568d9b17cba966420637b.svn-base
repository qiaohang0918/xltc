package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.pojo.Invoice;
import com.qigan.qiganshop.service.InvoiceService;
import com.qigan.qiganshop.util.result.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-08 15:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class InvoiceServiceImplTest {
    @Autowired
    private InvoiceService service;

    @Test
    public void add() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceTitleType(1);
        service.add(invoice);
    }

    @Test
    public void findOne() {
        Invoice one = service.findOne("1557301217307");
        System.err.println(one.getInvoiceTitleType());
    }

    @Test
    public void findPage() {
        PageResult page = service.findPage(null, null, null);
        System.err.println(page.getTotal());
    }
}