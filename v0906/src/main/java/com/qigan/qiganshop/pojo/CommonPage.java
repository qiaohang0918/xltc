/**
 * 
 */
package com.qigan.qiganshop.pojo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageHelper;

/**
 * @author WYY
 *
 */
public class CommonPage {

	private int page = 1;

	private int size = 10;

	private boolean pagination = false;

	private Map<String, Object> wheres;
	
	private List<Object> arrays;
	
	public CommonPage(){
		
	}
	
	public CommonPage(Integer page, Integer size, boolean pagination){
		this.page = page;
		this.size = size;
		this.pagination = pagination;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String, Object> getWheres() {
		return wheres;
	}

	public void setWheres(Map<String, Object> wheres) {
		this.wheres = wheres;
	}

	public List<Object> getArrays() {
		return arrays;
	}

	public void setArrays(List<Object> arrays) {
		this.arrays = arrays;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public void startPageHelper() {
		if (pagination) 
			PageHelper.startPage(page, size);
	}

	public void startPageHelper(String orderBy) {
		if (pagination) 
			PageHelper.startPage(page, size, orderBy);
	}

	private static final String SPE = "_";

	private enum WhereType {
		eq, noteq, gt, lt, ge, le, between, like, notLike, isNull, isNotNull;
	}

	private enum DateType {
		y, ym, ymd, ymdt
	}

	public String sqlWheres() {
		if (wheres == null) {
			return null;
		}
		String whereR = "";
		Set<String> keys = wheres.keySet();
		for (String key : keys) {
			if (key.indexOf(SPE) < 1)
				continue;
			whereR += caseWheres(key) + "and";
		}
		String result = whereR.substring(0, whereR.lastIndexOf("and"));
		return result;
	}

	private String caseWheres(String key) {

		String[] filedAndtype = key.split(SPE);
		WhereType type = WhereType.valueOf(filedAndtype[1]);
		String where = " " + filedAndtype[0] + " %s " + wheres.get(key) + " ";

		switch (type) {
		case eq:
			where = where("=", filedAndtype, key, where);
			break;
		case noteq:
			where = where("!=", filedAndtype, key, where);
			break;
		case le:
			where = where("<=", filedAndtype, key, where);
			break;
		case ge:
			where = where(">=", filedAndtype, key, where);
			break;
		case lt:
			where = where("<", filedAndtype, key, where);
			break;
		case gt:
			where = where(">", filedAndtype, key, where);
			break;
		case like:
			where = " " + filedAndtype[0] + " like '%" + wheres.get(key) + "%' ";
			break;
		case notLike:
			where = " " + filedAndtype[0] + " not like '%" + wheres.get(key) + "%' ";
			break;
		case isNull:
			where = " " + filedAndtype[0] + "is null ";
			break;
		case isNotNull:
			where = " " + filedAndtype[0] + "is not null ";
			break;
		case between:
			String btw = (String) wheres.get(key);
			if (btw.indexOf(",") < 0)
				return null;
			String[] btws = btw.split(",");
			where = " " + filedAndtype[0] + " between " + btws[0] + " and " + btws[1] + " ";
			break;

		}
		return where;
	}

	private String where(String sign, String[] keys, String key, String where) {
		String dateWhere = caseDateWhere(keys, key, sign);
		if (dateWhere != "") 
			return dateWhere;
		where = String.format(where, sign);
		return where;
	}

	private static final String Y = "'%Y'";

	private static final String YM = "'%Y-%m'";

	private static final String YMD = "'%Y-%m-%d'";

	private String caseDateWhere(String[] keys, String key, String sign) {
		if (keys.length < 3) 
			return "";
		
		String where = "";
		DateType type = DateType.valueOf(keys[2]);
		switch (type) {
		case y:
			where = btwDate(key, keys, sign, Y);
			break;
		case ym:
			where = btwDate(key, keys, sign, YM);
			break;
		case ymd:
			where = btwDate(key, keys, sign, YMD);
			break;
		case ymdt:

			break;
		}
		return where;
	}

	private String btwDate(String key, String[] keys, String sign, String datetype) {
		String btw = (String) wheres.get(key);
		if (btw.indexOf(",") < 0)
			return btwSql(datetype, sign, key, keys);
		String[] btws = btw.split(",");
		return " date_format(" + keys[0] + ", " + datetype + ") " + "between date_format('" + btws[0] + "', " + datetype
				+ ") and date_format('" + btws[1] + "', " + datetype + ") ";
	}

	private String btwSql(String dateType, String sign, String key, String[] keys) {
		return " date_format(" + keys[0] + ", " + dateType + ") " + sign + " date_format('" + wheres.get(key) + "', "
				+ dateType + ") ";
	}

	@Override
	public String toString() {
		return "CommonPage [page=" + page + ", size=" + size + ", pagination=" + pagination + ", wheres=" + wheres
				+ "]";
	}

}
