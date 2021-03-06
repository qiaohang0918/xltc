package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.StockMapper;
import com.qigan.qiganshop.pojo.OrderItem;
import com.qigan.qiganshop.pojo.Stock;
import com.qigan.qiganshop.service.StockService;
import com.qigan.qiganshop.util.notnull.NotNull;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author wanghao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {
    @Autowired
    private StockMapper mapper;

    /**
     * 根据商品 ID 和仓库 ID 检测是否在某仓库存在某商品的库存
     *
     * @param stock
     * @return
     */
    @Override
    public Stock checkExits(Stock stock) {
        return mapper.checkExits(stock.getItemId(), stock.getWarehouseId());
    }

    /**
     * 更新库存
     *
     * @param checkExits
     * @return
     */

    @Override
    public Integer update(Stock checkExits) {
        if (checkExits == null || checkExits.getStockId() == null) {
            return 0;
        }
        if (checkExits.getStockNum() == checkExits.getSalableNum() + checkExits.getLockNum()) {
            /**
             * 数据校验
             */
            return mapper.update(checkExits);
        }
        return 0;
    }

    /**
     * 添加库存
     *
     * @param stock
     * @return
     */
    @Override
    public Integer add(Stock stock) {
        if (stock == null) {
            return 0;
        }
        stock.setStockId(UUID.randomUUID().toString().replace("-", ""));
        if (stock.getStockNum() == stock.getSalableNum() + stock.getLockNum()) {
            /**
             * 数据校验
             */
            return mapper.insert(stock);
        }
        return 0;
    }

    /**
     * 删除库存
     *
     * @param ids
     * @return
     */
    @Override
    public Integer delete(String[] ids) {
        Integer count = 0;
        for (String id : ids) {
            count += mapper.delete(id);
        }
        return count;
    }

    /**
     * 查询单个商品的库存
     *
     * @param stockId
     * @return
     */
    @Override
    public Stock findOne(String stockId) {
        if (stockId != null && !"".equals(stockId)) {
            return mapper.findOne(stockId);
        }
        return new Stock();
    }

    /**
     * 分页查询, 条件查询,查询全部
     *
     * @param stock
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Stock> findPage(Stock stock, Integer page, Integer size) {
        if (page != null && "".equals(page) && size != null && !"".equals(size)) {
            return (List<Stock>) NotNull.checkListNull(mapper.findPage(stock, (page - 1) * size, size));
        }
        return (List<Stock>) NotNull.checkListNull(mapper.findPage(stock, null, null));
    }

    /**
     * 查询某仓库中所有库存数大于 0 的商品
     *
     * @param wareHouseId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Stock> findStockNum(String wareHouseId, Integer page, Integer size) {
        List<Stock> stockNum = null;
        if (page != null && size != null) {

            stockNum = mapper.findStockNum(wareHouseId, (page - 1) * size, size);
        } else {
            stockNum = mapper.findStockNum(wareHouseId, null, null);
        }
        return (List<Stock>) NotNull.checkListNull(stockNum);
    }

    /**
     * 查询某仓库中所有库存数大于 0 的商品
     *
     * @param wareHouseId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Stock> findStock(String wareHouseId, Integer page, Integer size) {
        List<Stock> stockNum = null;
        if (page != null && size != null) {

            stockNum = mapper.findStock(wareHouseId, (page - 1) * size, size);
        } else {
            stockNum = mapper.findStock(wareHouseId, null, null);
        }
        return (List<Stock>) NotNull.checkListNull(stockNum);
    }

    @Override
    public Stock findByItemIdAndWareHouseId(String s, String wareHouseId) {
        return mapper.findByItemIdAndWareHouseId(s, wareHouseId);
    }

    /**
     * 还原锁定库存
     *
     * @param orderItem
     * @return
     */
    @Override
    public int cancelLock(OrderItem orderItem, String warehouseId, Integer saleNum) {
        if (NotNull.checkObject(orderItem.getCount(), orderItem.getItemId(), warehouseId)) {
            return mapper.cancelLock(warehouseId, orderItem.getItemId(), orderItem.getCount(), saleNum);
        }
        return 0;
    }

	@Override
	public void updates(List<Stock> list) {
		// TODO Auto-generated method stub
		for (Stock stock : list) {
			this.update(stock);
		}
	}

    @Override
    public List<Stock> findEnabledStockByItemIdsAndWarehouseCode(String skuIdsConstructed, String warehouseCode) {
        return mapper.findEnabledStockByItemIdsAndWarehouseCode(skuIdsConstructed,warehouseCode);
    }

    @Override
    public Integer increQuestionsRefundOrderItemsStock(OrderItem orderItem, String warehouseCode) {
        return  mapper.increQuestionsRefundOrderItemsStock(orderItem,warehouseCode);
    }


}
