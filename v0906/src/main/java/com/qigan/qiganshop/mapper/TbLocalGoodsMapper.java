package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.domain.TbLocalGoods;
import com.qigan.qiganshop.domain.TbLocalGoodsExample;
import com.qigan.qiganshop.domain.TbLocalGoodsKey;
import com.qigan.qiganshop.pojo.XltcGoodsModel;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TbLocalGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @SelectProvider(type=TbLocalGoodsSqlProvider.class, method="countByExample")
    long countByExample(TbLocalGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TbLocalGoodsSqlProvider.class, method="deleteByExample")
    int deleteByExample(TbLocalGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @Delete({
        "delete from tb_local_goods",
        "where category_id = #{categoryId,jdbcType=BIGINT}",
          "and tb_goodsId = #{tbGoodsid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TbLocalGoodsKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @Insert({
        "insert into tb_local_goods (category_id, tb_goodsId, ",
        "goods_sort, goods_enable)",
        "values (#{categoryId,jdbcType=BIGINT}, #{tbGoodsid,jdbcType=VARCHAR}, ",
        "#{goodsSort,jdbcType=INTEGER}, #{goodsEnable,jdbcType=VARCHAR})"
    })
    int insert(TbLocalGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @InsertProvider(type=TbLocalGoodsSqlProvider.class, method="insertSelective")
    int insertSelective(TbLocalGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @SelectProvider(type=TbLocalGoodsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="tb_goodsId", property="tbGoodsid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="goods_sort", property="goodsSort", jdbcType=JdbcType.INTEGER),
        @Result(column="goods_enable", property="goodsEnable", jdbcType=JdbcType.VARCHAR)
    })
    List<TbLocalGoods> selectByExample(TbLocalGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "category_id, tb_goodsId, goods_sort, goods_enable",
        "from tb_local_goods",
        "where category_id = #{categoryId,jdbcType=BIGINT}",
          "and tb_goodsId = #{tbGoodsid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="tb_goodsId", property="tbGoodsid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="goods_sort", property="goodsSort", jdbcType=JdbcType.INTEGER),
        @Result(column="goods_enable", property="goodsEnable", jdbcType=JdbcType.VARCHAR)
    })
    TbLocalGoods selectByPrimaryKey(TbLocalGoodsKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TbLocalGoodsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TbLocalGoods record, @Param("example") TbLocalGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TbLocalGoodsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TbLocalGoods record, @Param("example") TbLocalGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TbLocalGoodsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TbLocalGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_goods
     *
     * @mbg.generated
     */
    @Update({
        "update tb_local_goods",
        "set goods_sort = #{goodsSort,jdbcType=INTEGER},",
          "goods_enable = #{goodsEnable,jdbcType=VARCHAR}",
        "where category_id = #{categoryId,jdbcType=BIGINT}",
          "and tb_goodsId = #{tbGoodsid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TbLocalGoods record);
    
    @Select({
    	"select t.goods_sort as sort, t.goods_enable as enable, y.goodsId, y.name as goodsName,y.code as goodsCode,",
    	"y.simpleName,y.salesPrice",
    	"from tb_local_goods t ",
    	"left join tb_local_category x on t.category_id = x.id",
    	"left join tb_goods y on y.goodsId = t.tb_goodsId",
    	"where x.id = #{localId}"
    })
    List<XltcGoodsModel> selectGoods(Long localId);
    
    
    
}