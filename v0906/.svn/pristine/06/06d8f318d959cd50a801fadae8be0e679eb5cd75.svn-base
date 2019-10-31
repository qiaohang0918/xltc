package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Specification;
import com.qigan.qiganshop.pojo.Warehouse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 仓库表的 DAO 层
 *
 * @author wanghao
 */
public interface WarehouseMapper {

    /**
     * 增加
     *
     * @param warehouse
     * @return
     */
    int insert(Warehouse warehouse);


    /**
     * 删除
     *
     * @param warehouseId
     * @return
     */
    int delete(String warehouseId);


    /**
     * 修改
     *
     * @param warehouse
     * @return
     */
    int update(@Param("record") Warehouse warehouse);

    /**
     * 查询所有或者查询单个
     * 当查询所有时,传递 null
     * 当查询单个时,传递 warehouse
     *
     * @return
     */
    List<Warehouse> findByWareHouse(@Param("record") Warehouse warehouse,
                                    @Param("page") Integer page,
                                    @Param("size") Integer size);

    /**
     * 查询单个规格
     *
     * @param warehouseId
     * @return
     */
    Warehouse findOne(@Param("warehouseId") String warehouseId);
    
    @Select({
    	"select warehouseId ",
        "from tb_warehouse",
        "where warehouseId = #{warehouseId,jdbcType=VARCHAR}"
    })
    String countById(String warehouseId);


}