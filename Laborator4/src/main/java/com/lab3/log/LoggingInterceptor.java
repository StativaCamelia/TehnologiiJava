package com.lab3.log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Log
public class LoggingInterceptor {

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {


        System.out.println("Entering method: " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
