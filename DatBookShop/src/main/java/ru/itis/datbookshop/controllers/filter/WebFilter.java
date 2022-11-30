package ru.itis.datbookshop.controllers.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@jakarta.servlet.annotation.WebFilter(filterName = "WebFilter", value = "/*")
public class WebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String url = httpServletRequest.getServletPath();

        if (session.getAttribute("admin") == null || session.getAttribute("admin").equals(0)) {
            if (url.equals("/admins_manager_controller") || url.equals("/load_employee_controller") || url.equals("/global_history_controller")) {
                httpServletResponse.sendRedirect("home_controller");
                return;
            }
            if (session.getAttribute("seller") == null || session.getAttribute("seller").equals(0)) {
                if (url.equals("/manager_controller") || url.equals("/load_book_controller")) {
                    httpServletResponse.sendRedirect("home_controller");
                    return;
                }
            }
            if (session.getAttribute("root_id") == null || session.getAttribute("root_id").equals(0)) {
                if (url.equals("/profile_controller") || url.equals("/purchase_history_controller")) {
                    httpServletResponse.sendRedirect("home_controller");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }
}
