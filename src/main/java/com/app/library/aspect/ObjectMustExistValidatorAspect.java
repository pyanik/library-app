package com.app.library.aspect;

import com.app.library.aop.annotation.BusinessObjectVersionValidator;
import com.app.library.aop.annotation.ObjectMustExistValidator;
import com.app.library.exception.BusinessObjectVersionNotValidException;
import com.app.library.exception.ObjectNotFoundException;
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
import java.util.UUID;

@Aspect
@Slf4j
@Component
@AllArgsConstructor
public class ObjectMustExistValidatorAspect {

    private final ApplicationContext applicationContext;

    @Before("@annotation(com.app.library.aop.annotation.ObjectMustExistValidator)")
    public void validateIfObjectExist(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UUID joinPointArg;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        ObjectMustExistValidator annotation = method.getAnnotation(ObjectMustExistValidator.class);
        String idPropertyName = annotation.idPropertyName();
        Object[] joinPointArgs = joinPoint.getArgs();

        if (StringUtils.isEmpty(idPropertyName)) {
            joinPointArg = (UUID) joinPointArgs[0];
        } else {
            String[] parameterNames = signature.getParameterNames();
            int idPropertyNameIndex = ArrayUtils.indexOf(parameterNames, idPropertyName);
            joinPointArg = (UUID) joinPointArgs[idPropertyNameIndex];
        }

        Object service = applicationContext.getBean(annotation.serviceClass());
        Class<?> serviceClass = service.getClass();
        Method declaredMethod = serviceClass.getDeclaredMethod(annotation.methodName(), UUID.class);

        boolean result = (boolean) declaredMethod.invoke(service, joinPointArg);

        if (!result) {
            throw new ObjectNotFoundException(joinPointArg);
        }
    }
}
