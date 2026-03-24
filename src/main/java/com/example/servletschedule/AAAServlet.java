package com.example.servletschedule;
import Date.DatePicker;
import Table.MainTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import schedule.employee.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet("/")
public class AAAServlet extends HttpServlet {
    ArrayList<ArrayList<Employee>> arraysWithEmployees = new ArrayList<>(); // массивы с сотрудниками
    String idParametr = "";

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        path =path.substring(0,path.indexOf("ServletSchedule"))+"ServletSchedule";
        File pathFile = new File(path);
        String[] arrayFilesInCatalog = pathFile.list();
        ArrayList<File> fileWithEmployees = new ArrayList<>();
        for(String p:arrayFilesInCatalog)
        {
            if(p.endsWith(".emp"))
            {

                fileWithEmployees.add(new File(Paths.get(path,p).toAbsolutePath().toString()));
            }
        }
        for(File f: fileWithEmployees)
        {
            try {
                String p  =f.getAbsolutePath();
                ArrayList<Employee> employees = Employee.readFromFile(p);
                arraysWithEmployees.add(employees);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("<p> ФАЙЛ СО СПИСКОМ СОТРУДНИКОВ "+f+" ПОВРЕЖДЕН!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            } catch (FileNotFoundException e) {
                throw new RuntimeException("<p> ФАЙЛ СО СПИСКОМ СОТРУДНИКОВ "+f+" НЕ НАЙДЕН!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            } catch (IOException e) {
                throw new RuntimeException("<p>ВО ВРЕМЯ ЧТЕНИЯ ФАЙЛ СО СПИСКОМ СОТРУДНИКОВ "+f+" ПРОИЗОШЛА ОШИБКА!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        idParametr = req.getParameter("id");
        Employee employeeWithIdParameter = null;
        if(idParametr!=null)
        {
            for(ArrayList<Employee> employees:arraysWithEmployees)
            {
                employeeWithIdParameter =Employee.isEmployeeInArray(idParametr,employees);
                if(employeeWithIdParameter !=null){break;}
            }
        }
        if(idParametr!=null&&employeeWithIdParameter!=null)
        {
            try {
                MainTable table = MainTable.readTableFromFile("E:\\Study\\JavaProj\\ServletSchedule\\Schedue_of_Январь_2026.tbl");
                String month = DatePicker.MONTHS_OF_YEAR[table.getDate().get(Calendar.MONTH)];
                String year = String.valueOf(table.getDate().get(Calendar.YEAR));
                req.setAttribute("mainTable", table);
                req.setAttribute("month", month);
                req.setAttribute("year", year);
                req.setAttribute("name",employeeWithIdParameter.getFullName());
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

            idParametr = "";
        }



    }
}
