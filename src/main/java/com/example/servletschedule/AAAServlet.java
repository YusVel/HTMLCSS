package com.example.servletschedule;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class AAAServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("INIT");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParameter = req.getParameter("id");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("""
                    <!DOCTYPE html>
                     <html lang="en">
                     <head>
                         <meta charset="UTF-8">
                         <meta name="viewport" content="width=device-width, initial-scale=1.0">
                         <title>Введите Ваш ID</title>
                     </head>
                     <body>
                         <form action="Http://localhost:8080/">
                             <label>Ваш ID: </br>
                                 <input type="text" name="id" maxlength="10">
                             </label>
                
                         </form>
                     </body>
                     </html>
                """);
        if(idParameter.equals("1111111111"))
        {

        }
        else
        {
            writer.println("<h3>Сотрудника с ID: "+idParameter+" не существует</h3>");
        }

    }
}
