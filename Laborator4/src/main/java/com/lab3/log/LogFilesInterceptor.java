package com.lab3.log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.FileWriter;
import java.io.IOException;

@Interceptor
@LogFiles
public class LogFilesInterceptor {

    @AroundInvoke
    public Object logFile(InvocationContext ctx) throws Exception {

        try {
            FileWriter myWriter = new FileWriter("filename.txt", true);
            myWriter.write(String.valueOf(ctx.getParameters()[0]));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ctx.proceed();
    }
}
