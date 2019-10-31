package com.qigan.qiganshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qigan.qiganshop.pojo.Banner;

public interface BannerMapper {

    List<Banner> selectBanner(@Param("kind")String kind);
    
    List<Banner> selectBannerByType(@Param("kind")String kind);

    int insertBanner(Banner banner);

    int updateBanner(Banner banner);

    int deleteBanner(Integer bannerId);

    int sortBanner(@Param("bannerId") Integer bannerId, @Param("sort") String sort);

    Banner selectBannerById(Integer bannerId);
    
    @Select({
    	"select bannerId,moduleType,typeId,itemId,url, sort, type,",
    	"if(left(image,4) = 'http', image, concat(#{host},  SUBSTRING_INDEX(image, ',', 1)))  as image, ",
    	"if(left(url,4) = 'http', image, concat(#{host},  SUBSTRING_INDEX(url, ',', 1)))  as url ",
    	"from tb_banner ",
    	"where isdel='0' ",
    	"and moduleType in ('1', '2', '3') ",// 1 banner 2 ad 3 activity
    	"order by sort "
    })
    List<Banner> selectAll(String host);
    
    @Select({
    	"<script>",
    	"select bannerId,moduleType,typeId,itemId,url, sort, type,",
    	"if(left(image,4) = 'http', image, concat(#{host},  SUBSTRING_INDEX(image, ',', 1)))  as image, ",
    	"if(left(url,4) = 'http', image, concat(#{host},  SUBSTRING_INDEX(url, ',', 1)))  as url ",
    	"from tb_banner ",
    	"where isdel='0' ",
    	"<if test='wareId != null'>",
    	"  and warehouseId = #{wareId}",
    	"</if>",
    	"<if test='wareId == null'>",
    	"  and warehouseId is null",
    	"</if>",
    	"and moduleType in ('1', '2', '3') ",// 1 banner 2 ad 3 activity
    	"order by sort ",
    	"</script>"
    })
    List<Banner> selectAllByConi(@Param("host") String host, @Param("wareId") String wareId);
    
    @Update({
    	"update tb_banner set isDel = #{status}",
    	"where bannerId = #{id}"
    })
    void updateEnabel(@Param("status") String status, @Param("id") Integer id);
    
    @Update({
    	"update tb_banner set warehouseId = #{wareId}",
    	"where bannerId = #{id}"
    })
    void updateWare(@Param("wareId") String wareId, @Param("id") Integer id);
    
    @Update({
    	"update tb_banner set enabelStartTime = #{start}, enabelEndTime = #{end}",
    	"where bannerId = #{id}"
    })
    int updateEnabelDate(@Param("start") String start, @Param("end") String end, @Param("id") Integer id);
    
    @Update({
    	"update tb_banner set enabelStartTime = null, enabelEndTime = null where bannerId = #{id}"
    })
    int updateDelEnabelDate(@Param("id") Integer id);
    
    @Delete({
    	"delete from tb_banner where bannerId = #{id}"
    })
    int deleteBannerById(Integer id);
    
    @Select({
    	"<script>",
    	"select * from tb_banner where isdel='0' and moduleType = #{modelType} ",
    	"<if test='wareId != null'>",
    	"  and warehouseId = #{wareId}",
    	"</if>",
    	"<if test='wareId == null'>",
    	"  and warehouseId is null",
    	"</if>",
    	"order by sort",
    	"</script>",
    }) 
    List<Banner> selectIndexImg(@Param("modelType") String modelType, @Param("wareId") String wardId);
}
