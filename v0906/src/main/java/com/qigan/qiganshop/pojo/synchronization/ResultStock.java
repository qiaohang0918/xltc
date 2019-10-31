package com.qigan.qiganshop.pojo.synchronization;

import java.util.List;

/**
 * 获取商品库存信息
 *
 * @author wanghao
 */
public class ResultStock {

    /**
     * success : true
     * errorCode :
     * subErrorCode :
     * errorDesc :
     * subErrorDesc :
     * requestMethod : gy.erp.new.stock.get
     * stocks : [{"barcode":null,"del":true,"qty":1500,"warehouse_code":"0001","item_code":"0001","sku_code":"0002","salable_qty":1500,"road_qty":1000,"pick_qty":1500,"item_name":"测试商品修改","item_sku_name":"规格 1","warehouse_name":"测试仓库"},{"barcode":"2000020001","del":false,"qty":10000,"warehouse_code":"0001","item_code":"20000","sku_code":"20001","salable_qty":10000,"road_qty":0,"pick_qty":10000,"item_name":"统一冰红茶250ml","item_sku_name":"500ml","warehouse_name":"测试仓库"}]
     * total : 2
     */

    private boolean success;
    private String errorCode;
    private String subErrorCode;
    private String errorDesc;
    private String subErrorDesc;
    private String requestMethod;
    private int total;
    private List<StocksBean> stocks;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSubErrorCode() {
        return subErrorCode;
    }

    public void setSubErrorCode(String subErrorCode) {
        this.subErrorCode = subErrorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getSubErrorDesc() {
        return subErrorDesc;
    }

    public void setSubErrorDesc(String subErrorDesc) {
        this.subErrorDesc = subErrorDesc;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StocksBean> getStocks() {
        return stocks;
    }

    public void setStocks(List<StocksBean> stocks) {
        this.stocks = stocks;
    }

    public static class StocksBean {
        /**
         * barcode : null
         * del : true
         * qty : 1500
         * warehouse_code : 0001
         * item_code : 0001
         * sku_code : 0002
         * salable_qty : 1500
         * road_qty : 1000
         * pick_qty : 1500
         * item_name : 测试商品修改
         * item_sku_name : 规格 1
         * warehouse_name : 测试仓库
         */

        private Object barcode;
        private boolean del;
        private int qty;
        private String warehouse_code;
        private String item_code;
        private String sku_code;
        private int salable_qty;
        private int road_qty;
        private int pick_qty;
        private String item_name;
        private String item_sku_name;
        private String warehouse_name;

        public Object getBarcode() {
            return barcode;
        }

        public void setBarcode(Object barcode) {
            this.barcode = barcode;
        }

        public boolean isDel() {
            return del;
        }

        public void setDel(boolean del) {
            this.del = del;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getWarehouse_code() {
            return warehouse_code;
        }

        public void setWarehouse_code(String warehouse_code) {
            this.warehouse_code = warehouse_code;
        }

        public String getItem_code() {
            return item_code;
        }

        public void setItem_code(String item_code) {
            this.item_code = item_code;
        }

        public String getSku_code() {
            return sku_code;
        }

        public void setSku_code(String sku_code) {
            this.sku_code = sku_code;
        }

        public int getSalable_qty() {
            return salable_qty;
        }

        public void setSalable_qty(int salable_qty) {
            this.salable_qty = salable_qty;
        }

        public int getRoad_qty() {
            return road_qty;
        }

        public void setRoad_qty(int road_qty) {
            this.road_qty = road_qty;
        }

        public int getPick_qty() {
            return pick_qty;
        }

        public void setPick_qty(int pick_qty) {
            this.pick_qty = pick_qty;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_sku_name() {
            return item_sku_name;
        }

        public void setItem_sku_name(String item_sku_name) {
            this.item_sku_name = item_sku_name;
        }

        public String getWarehouse_name() {
            return warehouse_name;
        }

        public void setWarehouse_name(String warehouse_name) {
            this.warehouse_name = warehouse_name;
        }

		@Override
		public String toString() {
			return "StocksBean [barcode=" + barcode + ", del=" + del + ", qty=" + qty + ", warehouse_code="
					+ warehouse_code + ", item_code=" + item_code + ", sku_code=" + sku_code + ", salable_qty="
					+ salable_qty + ", road_qty=" + road_qty + ", pick_qty=" + pick_qty + ", item_name=" + item_name
					+ ", item_sku_name=" + item_sku_name + ", warehouse_name=" + warehouse_name + "]";
		}
        
    }

	@Override
	public String toString() {
		return "ResultStock [success=" + success + ", errorCode=" + errorCode + ", subErrorCode=" + subErrorCode
				+ ", errorDesc=" + errorDesc + ", subErrorDesc=" + subErrorDesc + ", requestMethod=" + requestMethod
				+ ", total=" + total + ", stocks=" + stocks + "]";
	}
    
}
