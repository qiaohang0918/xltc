/**
 * 
 */
package com.qigan.qiganshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qigan.qiganshop.pojo.XltcActivityModel;

/**
 * @author wyy
 *
 */
public interface XltcActivityMapper {
	
	@Insert({
		"<script> ",
		"insert into tb_activity(goods_id,status) values",
		"<foreach collection='list' item='record' index='index' separator=','> ",
		"(#{record.goodsId}, #{record.status})",
		"</foreach>",
    	"  ON DUPLICATE KEY UPDATE ",
    	"goods_id = VALUES(goods_id), status=VALUES(status)",
		"</script> "
	})
	void insert(List<XltcActivityModel> list);
	
	@Update({
		"update tb_activity set status = #{status} where id = #{id}"
	})
	void delete(@Param("status") String status, @Param("id") Long id);
	
	@Select({
		"select id,goods_id as goodsId, status, create_date as createDate, update_date as updateDate ",
		"from tb_activity "
	})
	List<XltcActivityModel> findAll();
	
	@Select("select goods_id from tb_activity where status = '1' ")
	List<String> findGoodsId();

	@Delete("delete from tb_activity where id = #{id}")
	void deleteById(Long id);
	
}
