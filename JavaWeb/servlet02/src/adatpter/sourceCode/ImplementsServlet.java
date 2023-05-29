package adatpter.sourceCode;

import jakarta.servlet.*;

import java.io.IOException;
/*
大多数情况下，我们只需要重写service、init、destroy方法中的内容
当程序中也会出现其他被要求重写的方法，这样就造成程序的可读性与观赏性降低*/
public class ImplementsServlet  implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init's method execute...");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service's method execute");
    }

    @Override
    public void destroy() {
        System.out.println("destroy's method execute...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

}
