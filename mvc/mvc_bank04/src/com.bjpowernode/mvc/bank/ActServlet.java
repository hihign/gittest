package com.bjpowernode.mvc.bank;

import com.bjpowernode.mvc.exception.AppException;
import com.bjpowernode.mvc.exception.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/transfer")
//直接与用户产生联系，由它调度Module和View
public class ActServlet extends HttpServlet {

    AccountService accountService = new AccountService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        Double money = Double.parseDouble(request.getParameter("money"));

        //调度转账服务
        try {
            accountService.transfer(fromActno,toActno,money);
            response.sendRedirect(request.getContextPath()+"/success.jsp");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath()+"/moneynotenogh.jsp");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
}
