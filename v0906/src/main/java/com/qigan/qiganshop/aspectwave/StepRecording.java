package com.qigan.qiganshop.aspectwave;

import com.qigan.qiganshop.service.XltcOptInfoService;
import com.qigan.qiganshop.util.notnull.StringNotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @Aurher: QiaoHang
 * @Description:
 * @Data: 2019/9/3 9:01
 * @Modified By:
 */
@Aspect
@Component
public class StepRecording {


    @Autowired
    private XltcOptInfoService optInfoService;

    @Pointcut("@annotation(com.qigan.qiganshop.annocation.StepRecordWatchAble)")
    public void stepRecordingPoint(){}

    /**
     * 记录操作日志
     * @param joinPoint
     */
    @Before("stepRecordingPoint()")
    public void stepRecording(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        String no=null;
        String name=null;
        String inter=null;
        String func=null;
        if(strings!=null && strings.length>0){
            for (int i = 0; i < strings.length; i++) {
                if("no".equals(strings[i].trim())){
                    no = (String) args[i];
                }else if("name".equals(strings[i].trim())){
                    name = (String) args[i];
                }else if("inter".equals(strings[i].trim())){
                    inter = (String) args[i];
                }else if("Func".equals(strings[i].trim())){
                    func = (String) args[i];
                }else {

                }
            }
        }
        if(StringNotNull.check(no,name,inter,func)){
            //记录操作日志
            optInfoService.recordOptInfo(no,name,inter,func);
        }
    }


}
