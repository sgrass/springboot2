package org.cx.asyncweb;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet3.0之前都是同步servlet，为了兼容默认异步是关闭的 需设置asyncSupported=true
 *
 * @author grass
 * @date 2017/11/18
 */
@WebServlet(value = "/simple/async", asyncSupported = true)
public class SimpleAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter printWriter = resp.getWriter();

        printWriter.println(Thread.currentThread().getName() + "-->开始执行\n");

        //启动异步上下文
        AsyncContext asyncContext = req.startAsync();

        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                printWriter.println(Thread.currentThread().getName() + " 请求完成了！\n");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                printWriter.println(Thread.currentThread().getName() + " 请求超时了！\n");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                printWriter.println(Thread.currentThread().getName() + " 请求错误了！\n");
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                printWriter.println(Thread.currentThread().getName() + " 异步请求开始了！\n");
            }
        });

        //同步方式
        asyncContext.complete();

        // 异步方式
//        asyncContext.start(()->{
//            printWriter.println(Thread.currentThread().getName() + " 执行中！\n");
//            asyncContext.complete();
//        });



    }
}
