package com.app.library.aspect;

import com.app.library.aop.annotation.BusinessObjectVersionValidator;
import com.app.library.model.dto.BookDto;
import com.app.library.model.dto.DomainDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class BusinessObjectVersionValidatorAspect {

    private final ApplicationContext applicationContext;

    @Before("@annotation(com.app.library.aop.annotation.BusinessObjectVersionValidator)")
    public void validateBusinessObjectVersion(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        BusinessObjectVersionValidator annotation = method.getAnnotation(BusinessObjectVersionValidator.class);
        String bodyObjectName = annotation.bodyObjectName();

        String[] parameterNames = signature.getParameterNames();
        int bodyObjectNameIndex = ArrayUtils.indexOf(parameterNames, bodyObjectName);

        Object[] joinPointArgs = joinPoint.getArgs();

//        Integer joinPointArg = (Integer) joinPointArgs[bodyObjectNameIndex];

        DomainDto joinPointArg = (DomainDto) joinPointArgs[bodyObjectNameIndex];
        int businessObjectVersion = joinPointArg.getBusinessObjectVersion();

        Object service = applicationContext.getBean(annotation.serviceClass());
        Class<?> serviceClass = service.getClass();
        Method declaredMethod = serviceClass.getDeclaredMethod(annotation.methodName(), DomainDto.class);

        declaredMethod.invoke(service, joinPointArg);
//        declaredMethod.invoke(service, joinPointArg);
    }
}