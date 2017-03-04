package com.sfl.coolmonkey.coolfs.api.rest.resources.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/28/15
 * Time: 11:15 AM
 */
@Aspect
@Component
@SuppressWarnings({
        "checkstyle:com.puppycrawl.tools.checkstyle.checks.coding.IllegalThrowsCheck",
        "squid:S00112",
})
public class ResourceMethodLoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceMethodLoggingAspect.class);

    //region Constructors
    public ResourceMethodLoggingAspect() {
    }
    //endregion

    //region Public methods
    @Around("execution(public * com.sfl.coolmonkey.coolfs.api.rest.resources.*..* (..))")
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

    @AfterThrowing(value = "execution(public * com.sfl.coolmonkey.coolfs.api.rest.resources.*..* (..))",
            throwing = "e")
    public void afterThrowing(final JoinPoint point, final Exception e) {
        LOGGER.error(
                "#{}.{}({}):",
                point.getSignature().getDeclaringType().getSimpleName(),
                point.getSignature().getName(),
                point.getArgs(),
                e
        );
    }
    //endregion
}
