package com.sfl.coolmonkey.coolfs.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/28/15
 * Time: 6:45 AM
 */
@Aspect
@Component
@SuppressWarnings({
        "checkstyle:com.puppycrawl.tools.checkstyle.checks.coding.IllegalThrowsCheck",
        "squid:S00112",
})
public class ServiceMethodLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceMethodLoggingAspect.class);

    //region Constructors
    public ServiceMethodLoggingAspect() {
    }
    //endregion

    //region Public methods
    @Around("execution(public * com.sfl.coolmonkey.coolfs.service..* (..))" +
            " && !execution(* com.sfl.coolmonkey.coolfs.service.helper..* (..))")
    public Object around(final ProceedingJoinPoint point) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object result = point.proceed();
        LOGGER.info(
                "#{}.{}({}): {} in {}ms",
                point.getSignature().getDeclaringType().getSimpleName(),
                point.getSignature().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );
        return result;
    }
    //endregion
}
