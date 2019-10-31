package com.qigan.qiganshop.service;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * create nby qh
 */
public interface XltcLocationService {

    Map<String, Object> getLocationsWithInCountry() throws InvocationTargetException, IllegalAccessException;
}
