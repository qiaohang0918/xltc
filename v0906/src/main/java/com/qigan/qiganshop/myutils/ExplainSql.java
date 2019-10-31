package com.qigan.qiganshop.myutils;


import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @Aurher: QiaoHang
 * @Description: 解释sql
 * @Data: 2019/8/27 8:24
 * @Modified By:
 */
public class ExplainSql implements Serializable {

    //基类型未实例化(无法映射表)
    public static final String ERROR = "ERROR";

    //求count 时存在分组，无法进行
    public static final String COUNT_GROUP_ERR = ERROR+"：sql存在分组，无法直接求total，请自行解决";


    /**
     * 获取容器
     * @return
     */
    public static StringBuffer getBuffer(){
        return new StringBuffer();
    }

    /**
     * Base_Result_Concat
     * 拼接目标属性链
     * @param supperDoc
     * @return
     */
    public static String mappingFieleds(SupperDoc supperDoc){
        //获取目标属性链
        List<String> wantedFields = supperDoc.getWantedFields();
        String destChain = wantedFields.toString().replace("[", "").replace("]", "");
        //获取并织入分组依据属性
        String[] groupDeplays = supperDoc.getGroupDeplay().split(",");
        for (String groupDeplay : groupDeplays) {
            //不包含则拼接
            if(!destChain.contains(groupDeplay)){
                destChain += " ," + groupDeplay;
            }
        }
        //获取分组过滤条件并织入
        Map<String,Object> groupFilters = supperDoc.getGroupFilters();
        if(groupFilters!=null && groupFilters.size()>0){
            Set<String> set = groupFilters.keySet();
            String[] split=null;
            for (String filterKey : set) {
                if(filterKey.indexOf("$")>0){
                    //条件过滤中存在运算符
                    split = filterKey.split("\\$");
                    //不包含目标属性就拼接
                    if(!destChain.contains(split[0])){
                        destChain += " ," + split[0];
                    }
                }else if(filterKey.indexOf("~")>0){
                    split = filterKey.split("~");
                    //不包含目标属性就拼接
                    if(!destChain.contains(split[0])){
                        destChain += " ," + split[0];
                    }
                }else{
                    //条件过滤中不存在运算符，判定是否存在该目标属性，不包含则拼接
                    if(!destChain.contains(filterKey)){
                        destChain += " ," + filterKey;
                    }
                }
            }
        }
        return destChain;
    }

    /**
     * 拼接结果集字段
     * @param supperDoc
     * @param buffer
     */
    public static void concatResultFields(SupperDoc supperDoc,StringBuffer buffer){
        //判断是否获取总条数，如果是直接 count(0) 返回
        Map pageAble = supperDoc.getPageAble();
        if(pageAble!=null){
            Object wantTotal = pageAble.get("wantTotal");
            if(wantTotal!=null && wantTotal instanceof  Boolean && (boolean)wantTotal == true){
                buffer.append(" count(0) as count \n");
                return;
            }
        }
        //判断是否存在目标属性链(不存在则* ， 存在则判断是否包含分组，获取属性链)
        List<String> wantedFields = supperDoc.getWantedFields();
        if(wantedFields==null || wantedFields.size()<=0){
            buffer.append(" * \n");
            return;
        }else {
            String groupDeplay = supperDoc.getGroupDeplay();
            //存在属性链，不存在分组过滤，不重构属性链
            if(groupDeplay==null || "".equals(groupDeplay.trim())){
                buffer.append( wantedFields.toString().replace("[", "").replace("]", "") + "\n");
                return;
            }else {
                //有属性链，有分组依据，则重构属性链
                String reConstructFieldsResult = mappingFieleds(supperDoc);
                buffer.append(reConstructFieldsResult +"\n");
                return;
            }
        }
    }

    /**
     * 头拼接
     * @param supperDoc
     * @param buffer
     */
    public static String concatSqlBase(SupperDoc supperDoc,StringBuffer buffer){
        //确定容器
        if(buffer==null){
            buffer=new StringBuffer();
        }
        //解析表名
        String tableName=null;
        Class aClass = supperDoc.getaClass();
        if(aClass!=null){
            Annotation annotation = aClass.getAnnotation(PersistanceAble.class);
            if(annotation!=null){
                PersistanceAble persistanceAble = (PersistanceAble)annotation;
                if(persistanceAble.tableName()!=null && !"".equals(persistanceAble.tableName().trim())){
                    tableName= persistanceAble.tableName();
                }
            }
        }else {
            String table_name = supperDoc.getTable_name();
            if(table_name!=null && !"".equals(table_name))
                tableName=table_name;
        }
        if(tableName==null || "".equals(tableName)){
            buffer.append(" "+ERROR+" ");
            return ERROR;
        }else {
            buffer.append(" select \n");
            concatResultFields(supperDoc,buffer);
            buffer.append(" from "+tableName+" \n");
        }
        return  buffer.toString().indexOf(ERROR)>0 ? ERROR : buffer.toString();
    }

    /**
     * 基础查询
     * @param supperDoc
     * @return
     */
    public static  StringBuffer getBuffer(SupperDoc supperDoc){
        //获取容器
        StringBuffer buffer = getBuffer();
        return buffer;
    }

    /**
     * 参数值解析
     * @param waitingDeal
     * @return
     */
    public static  String parseValue(Object waitingDeal){
        if(waitingDeal instanceof  String){
            String ss=(String)waitingDeal;
            if(!ss.contains(","))
                return "'"+ss+"'";
            else {
                StringBuffer buffer = new StringBuffer(" ");
                String[] split = ss.split(",");
                if(split!=null && split.length>0){
                    for (String s : split) {
                        buffer.append("'"+s+"',");
                    }
                }
                buffer.replace(buffer.length()-1,buffer.length(),"");
                return buffer.toString();
            }
        }else if(waitingDeal instanceof Integer || waitingDeal instanceof  Long || waitingDeal instanceof Double || waitingDeal instanceof Float){
            return  String.valueOf(waitingDeal);
        }else if(waitingDeal instanceof  Boolean){
            return  "'"+ ((Boolean) waitingDeal==true? 1 : 0) +"'";
        }else if(waitingDeal instanceof  List){
            StringBuffer buffer = new StringBuffer(" ");
            List<Object> ss= (List<Object>)waitingDeal;
            if(ss!=null && ss.size()>0){
                for (Object s : ss) {
                    buffer.append("'"+s+"',");
                }
            }
            buffer.replace(buffer.length()-1,buffer.length(),"");
            return buffer.toString();
        }else {
            //....有待扩展
            return " ";
        }
    }

    /**
     * 条件解析
     * @param entry
     * @return
     */
    public static String paseCondition(Map.Entry<String, Object> entry){
        String key = entry.getKey();
        String[] split = null;
        if(key.indexOf("$")>0){
            split = key.split("\\$");   //切分and子句
            return dealAndOrSubStr(entry,split);
        }else if(key.indexOf("~")>0){
            split = key.split("~");   //切分or子句
            return dealAndOrSubStr(entry,split);
        }else {
            return key + " = " + parseValue(entry.getValue());
        }
    }

    /**
     * 处理 and / or
     * @param entry
     * @param split
     * @return
     */
    public static  String  dealAndOrSubStr(Map.Entry<String, Object> entry, String... split){
        if("gt".equalsIgnoreCase(split[1])){
            return split[0] + " > " + parseValue(entry.getValue());
        }
        else if("gte".equalsIgnoreCase(split[1])){
            return split[0] + " >= " + parseValue(entry.getValue());
        }
        else if("lt".equalsIgnoreCase(split[1])){
            return split[0] + " < " + parseValue(entry.getValue());
        }
        else if("lte".equalsIgnoreCase(split[1])){
            return split[0] + " <= " + parseValue(entry.getValue());
        }
        else if("eq".equalsIgnoreCase(split[1])){
            return split[0] + " = " + parseValue(entry.getValue());
        }
        else if("neq".equalsIgnoreCase(split[1])){
            return split[0] + " != " + parseValue(entry.getValue());
        }
        else if("like".equalsIgnoreCase(split[1])){
            return split[0] + " like " + "'%"+entry.getValue()+"%'";
        }
        else if("in".equalsIgnoreCase(split[1])){
            return split[0] + " in (" + parseValue(entry.getValue())+" )";
        }
        else if("nin".equalsIgnoreCase(split[1])){
            return split[0] + " not in (" + parseValue(entry.getValue())+" )";
        }
        else if("isnull".equalsIgnoreCase(split[1])){
            return split[0] + " is null ";
        }
        else if("isnotnull".equalsIgnoreCase(split[1])){
            return split[0] + " is not null ";
        }else {
            //....条件语句有待扩展
            return  " ";
        }
    }

    /**
     * 分页支持
     * @param supperDoc
     * @param buffer
     */
    public static void concatPageSupport(SupperDoc supperDoc, StringBuffer buffer){
        Map pageAble = supperDoc.getPageAble();
        Object wantPage = pageAble.get("wantPage");
        if(wantPage!=null){
            if(wantPage instanceof Boolean && (boolean) wantPage==true){
                //分页。需要总条数
                Object page = pageAble.get("page");
                Object size = pageAble.get("size");
                page = page==null?SupperDoc.PAGE_DEFAULT:(Integer)page;
                size = size==null?supperDoc.SIZE_DEFAULT:(Integer)size;
                buffer.append(" limit "+page+" , "+size+" \n");
            }
        }
    }

    /**
     * 是否分组
     * @param supperDoc
     * @param buffer
     */
    public static  void concatGroupChoose(SupperDoc supperDoc,StringBuffer buffer){
        //是否分组
        if(supperDoc.getGroupDeplay()!=null && supperDoc.getGroupDeplay().length()>0){
            String groupDeplay = supperDoc.getGroupDeplay();
            buffer.append(" group by \n"+groupDeplay+" \n");
            Map<String,Object> map = supperDoc.getGroupFilters();
            if(map!=null && map.size()>0){
                Set<Map.Entry<String, Object>> entries = map.entrySet();
                buffer.append(" having 1=1 \n");
                for (Map.Entry<String, Object> entry : entries) {
                    buffer.append((entry.getKey().indexOf("$")>0?"and":"or")+ " "+ paseCondition(entry) +" \n" );
                }
            }
        }
    }

    /**
     * 封装条件集
     * @param supperDoc
     * @param buffer
     */
    public static  void concatConditionls(SupperDoc supperDoc,StringBuffer buffer){
        Map<String,Object> conditions = supperDoc.getConditions();
        //where条件
        if(conditions!=null && conditions.size()>0){
            Set<Map.Entry<String, Object>> entries = conditions.entrySet();
            buffer.append(" where 1=1 \n");
            for (Map.Entry<String, Object> entry : entries) {
                buffer.append((entry.getKey().indexOf("$")>0?"and":"or")+ " "+ paseCondition(entry) +" \n" );
            }
        }
    }

    /**
     * 设置排序
     * @param supperDoc
     * @param buffer
     */
    public static  void concatSorted(SupperDoc supperDoc,StringBuffer buffer){
        String sortedField = supperDoc.getSortedField();
        if(sortedField!=null && sortedField.length()>0){
            buffer.append(" order by "+sortedField + " "+supperDoc.getSortedPartten()+"\n");
        }
    }

    /**
     * 条件查询
     * @param supperDoc
     * @return
     */
    public static  String findDataByConditions(SupperDoc supperDoc){
        if(supperDoc==null)
            return ERROR;
        //获取容器
        StringBuffer buffer = getBuffer(supperDoc);

        //拼接基础查询 select  concatResultFields... from   tb_table
        String sqlBase = concatSqlBase(supperDoc, buffer);
        if(sqlBase.indexOf(ERROR)>0)  //未拼接表名
            return ERROR;

        //拼接条件
        concatConditionls(supperDoc,buffer);
        //拼接分组 以及分组过滤
        concatGroupChoose(supperDoc,buffer);
        //拼接排序
        concatSorted(supperDoc,buffer);
        //分页支持
        concatPageSupport(supperDoc,buffer);
        System.out.println("\n [---SELECT---MULTI_FUNC---]  \n"+buffer.toString());
        return buffer.toString();
    }

    /**
     * 查询总条数(存在分组情况无效)
     * @param supperDoc
     * @return
     */
    public static String countTotalByCase(SupperDoc supperDoc){
        if(supperDoc==null)
            return ERROR;
        //获取容器
        StringBuffer buffer = getBuffer(supperDoc);

        //拼接基础查询 select  concatResultFields... from   tb_table
        String sqlBase = concatSqlBase(supperDoc, buffer);
        if(sqlBase.indexOf(ERROR)>0)  //未拼接表名
            return ERROR;

        //拼接条件
        concatConditionls(supperDoc,buffer);
        System.out.println("\n [---SELECT---COUNT---]  \n"+buffer.toString());
        return buffer.toString();
    }


}
