package com.example.servletschedule;
import Date.DatePicker;
import Table.MainTable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet("/")
public class AAAServlet extends HttpServlet {
String idParametr = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        idParametr = req.getParameter("id");
        if(idParametr!=null&&idParametr.equals("1111111111"))
        {
            try {
                MainTable table = MainTable.readTableFromFile("E:\\Study\\JavaProj\\ServletSchedule\\Schedue_of_Январь_2026.tbl");
                String month = DatePicker.MONTHS_OF_YEAR[table.getDate().get(Calendar.MONTH)];
                String year = String.valueOf(table.getDate().get(Calendar.YEAR));
                req.setAttribute("mainTable", table);
                req.setAttribute("month", month);
                req.setAttribute("year", year);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/i.jsp").forward(req,resp);
        }
        else {
            if(idParametr!=null)
            {
                req.setAttribute("ERROR","Сотрудника с ID:" +idParametr+" не существует!");
            }

            this.getServletContext().getRequestDispatcher("/AAA.jsp").forward(req,resp);
            PrintWriter printWriter = resp.getWriter();
            printWriter.println(req.getContextPath());
            idParametr = "";
        }



    }
}
