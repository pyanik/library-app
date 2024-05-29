package com.app.library.aspect;

import com.app.library.aop.annotation.BusinessObjectVersionValidator;
import com.app.library.exception.BusinessObjectVersionNotValidException;
import com.app.library.model.dto.BusinessObjectVersionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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

        BusinessObjectVersionDto joinPointArg = (BusinessObjectVersionDto) joinPointArgs[bodyObjectNameIndex];

        Object service = applicationContext.getBean(annotation.serviceClass());
        Class<?> serviceClass = service.getClass();
        Method declaredMethod = serviceClass.getDeclaredMethod(annotation.methodName(), BusinessObjectVersionDto.class);

        String result = (String) declaredMethod.invoke(service, joinPointArg);

        if (StringUtils.isNotEmpty(result)) {
            throw new BusinessObjectVersionNotValidException(result);
        }
    }
}