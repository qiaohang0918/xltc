package com.qigan.qiganshop.myutils;

import java.util.*;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/8/27 8:45
 * @Modified By:
 */
public class SupperDoc<T> {

    //分页属性
    public static Integer PAGE_DEFAULT=1;
    public static Integer SIZE_DEFAULT=10;

    {
        //条件体
        conditions=new LinkedHashMap<>();
        //目标字段
        wantedFields=new ArrayList<>();
        //过滤条件体
        groupFilters=new LinkedHashMap<>();
        //分页支持
        pageAble = new LinkedHashMap<>();
        //默认排序
        sortedPartten = "DESC" ;
    }

    //base实体
    public T bean;

    //条件体
    public Map<String,Object> conditions;

    //映射表的泛型( 泛型和表明必传一个，否则解析失败 ERROR )
    public Class<T> aClass;

    //表名( 泛型和表明必传一个，否则解析失败 ERROR )
    public String table_name;

    //目标字段(size == 0 ? * : list)
    public List<String> wantedFields;

    //分组依据
    public String groupDeplay;

    //分组后的过滤条件
    public Map<String,Object> groupFilters;

    //排序字段
    public String sortedField;

    //排序规则 默认DESC
    public String sortedPartten;

    //分页支持
    public Map<String,Object> pageAble;

    @Override
    public String toString() {
        return "SupperDoc{" +
                "bean=" + bean +
                ", conditions=" + conditions +
                ", aClass=" + aClass +
                ", table_name='" + table_name + '\'' +
                ", wantedFields=" + wantedFields +
                ", groupDeplay='" + groupDeplay + '\'' +
                ", groupFilters=" + groupFilters +
                ", sortedField='" + sortedField + '\'' +
                ", sortedPartten='" + sortedPartten + '\'' +
                ", pageAble=" + pageAble +
                '}';
    }

    public static Integer getPageDefault() {
        return PAGE_DEFAULT;
    }

    public static void setPageDefault(Integer pageDefault) {
        PAGE_DEFAULT = pageDefault;
    }

    public static Integer getSizeDefault() {
        return SIZE_DEFAULT;
    }

    public static void setSizeDefault(Integer sizeDefault) {
        SIZE_DEFAULT = sizeDefault;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public Map<String, Object> getPageAble() {
        return pageAble;
    }

    public void setPageAble(Map<String, Object> pageAble) {
        this.pageAble = pageAble;
    }

    public String getGroupDeplay() {
        return groupDeplay;
    }

    public void setGroupDeplay(String groupDeplay) {
        this.groupDeplay = groupDeplay;
    }

    public Map<String, Object> getGroupFilters() {
        return groupFilters;
    }

    public void setGroupFilters(Map<String, Object> groupFilters) {
        this.groupFilters = groupFilters;
    }

    public String getSortedField() {
        return sortedField;
    }

    public void setSortedField(String sortedField) {
        this.sortedField = sortedField;
    }

    public String getSortedPartten() {
        return sortedPartten;
    }

    public void setSortedPartten(String sortedPartten) {
        this.sortedPartten = sortedPartten;
    }


    public List<String> getWantedFields() {
        return wantedFields;
    }

    public void setWantedFields(List<String> wantedFields) {
        this.wantedFields = wantedFields;
    }

    public SupperDoc(T bean, Map<String, Object> conditions, Class<T> aClass) {
        this.bean = bean;
        this.conditions = conditions;
        this.aClass = aClass;
    }

    public SupperDoc() {

    }

    public Class<T> getaClass() {
        return aClass;
    }

    public void setaClass(Class<T> aClass) {
        this.aClass = aClass;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public Map<String, Object> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }
}
