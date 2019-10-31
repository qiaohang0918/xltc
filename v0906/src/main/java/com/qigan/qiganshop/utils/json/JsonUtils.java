/**
 * 
 */
package com.qigan.qiganshop.utils.json;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wyy
 *
 */
public class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static String writeValueAsString(Object obj) {
		String json = "";
		try {
			json = MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		}

		return json;
	}

	public static <T> T readValue(String json, Class<T> classz) {
		T result = null;
		try {
			result = MAPPER.readValue(json, classz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		return result;
	}

	public static <T> List<T> readToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {

		}
		return null;
	}

}
