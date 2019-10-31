package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.domain.TbLocalCategory;
import com.qigan.qiganshop.domain.TbLocalCategoryExample.Criteria;
import com.qigan.qiganshop.domain.TbLocalCategoryExample.Criterion;
import com.qigan.qiganshop.domain.TbLocalCategoryExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TbLocalCategorySqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    public String countByExample(TbLocalCategoryExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("tb_local_category");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    public String deleteByExample(TbLocalCategoryExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("tb_local_category");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    public String insertSelective(TbLocalCategory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_local_category");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getTypeCode() != null) {
            sql.VALUES("type_code", "#{typeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getTypeName() != null) {
            sql.VALUES("type_name", "#{typeName,jdbcType=VARCHAR}");
        }
        
        if (record.getTypeSort() != null) {
            sql.VALUES("type_sort", "#{typeSort,jdbcType=INTEGER}");
        }
        
        if (record.getTypeEnable() != null) {
            sql.VALUES("type_enable", "#{typeEnable,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.VALUES("creater", "#{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.VALUES("update_date", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdater() != null) {
            sql.VALUES("updater", "#{updater,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    public String selectByExample(TbLocalCategoryExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("type_code");
        sql.SELECT("type_name");
        sql.SELECT("type_sort");
        sql.SELECT("type_enable");
        sql.SELECT("create_date");
        sql.SELECT("creater");
        sql.SELECT("update_date");
        sql.SELECT("updater");
        sql.FROM("tb_local_category");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TbLocalCategory record = (TbLocalCategory) parameter.get("record");
        TbLocalCategoryExample example = (TbLocalCategoryExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("tb_local_category");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getTypeCode() != null) {
            sql.SET("type_code = #{record.typeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getTypeName() != null) {
            sql.SET("type_name = #{record.typeName,jdbcType=VARCHAR}");
        }
        
        if (record.getTypeSort() != null) {
            sql.SET("type_sort = #{record.typeSort,jdbcType=INTEGER}");
        }
        
        if (record.getTypeEnable() != null) {
            sql.SET("type_enable = #{record.typeEnable,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{record.createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("creater = #{record.creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.SET("update_date = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdater() != null) {
            sql.SET("updater = #{record.updater,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("tb_local_category");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("type_code = #{record.typeCode,jdbcType=VARCHAR}");
        sql.SET("type_name = #{record.typeName,jdbcType=VARCHAR}");
        sql.SET("type_sort = #{record.typeSort,jdbcType=INTEGER}");
        sql.SET("type_enable = #{record.typeEnable,jdbcType=VARCHAR}");
        sql.SET("create_date = #{record.createDate,jdbcType=TIMESTAMP}");
        sql.SET("creater = #{record.creater,jdbcType=VARCHAR}");
        sql.SET("update_date = #{record.updateDate,jdbcType=TIMESTAMP}");
        sql.SET("updater = #{record.updater,jdbcType=VARCHAR}");
        
        TbLocalCategoryExample example = (TbLocalCategoryExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TbLocalCategory record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_local_category");
        
        if (record.getTypeCode() != null) {
            sql.SET("type_code = #{typeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getTypeName() != null) {
            sql.SET("type_name = #{typeName,jdbcType=VARCHAR}");
        }
        
        if (record.getTypeSort() != null) {
            sql.SET("type_sort = #{typeSort,jdbcType=INTEGER}");
        }
        
        if (record.getTypeEnable() != null) {
            sql.SET("type_enable = #{typeEnable,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("creater = #{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            sql.SET("update_date = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdater() != null) {
            sql.SET("updater = #{updater,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_local_category
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TbLocalCategoryExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}