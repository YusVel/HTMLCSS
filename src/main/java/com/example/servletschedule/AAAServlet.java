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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;

@WebServlet("/")
public class AAAServlet extends HttpServlet {
    ArrayList<ArrayList<Employee>> arraysWithEmployees = new ArrayList<>(); // массивы с сотрудниками
    ArrayList<MainTable> arraysWithMainTables = new ArrayList<MainTable>();
    String idParametr = "";
    String yearParametr = null;
    String monthParametr = null;
    TreeSet<String> years = new TreeSet<String>();


    @Override
    public void init() throws ServletException {
        super.init();
        initArraysWithEmployees();
        initArraysWithMainTables();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        idParametr = req.getParameter("id");
        Employee employeeWithIdParameter = null;
        if (idParametr != null) {
            for (ArrayList<Employee> employees : arraysWithEmployees) {
                employeeWithIdParameter = Employee.isEmployeeInArray(idParametr, employees);
                if (employeeWithIdParameter != null) {
                    break;
                }
            }
        }
        if (employeeWithIdParameter != null) {
            yearParametr = req.getParameter("year");
            monthParametr = req.getParameter("month");
            String month = null;
            String year = null;
            MainTable table = null;
            if (yearParametr != null && monthParametr != null) {
                table = getMainTableFromArray(monthParametr, yearParametr);
            }
            if(table!=null) {
                month = DatePicker.MONTHS_OF_YEAR[table.getDate().get(Calendar.MONTH)];
                year = String.valueOf(table.getDate().get(Calendar.YEAR));
                }
                req.setAttribute("mainTable", table);
                req.setAttribute("month", month);
                req.setAttribute("year", year);
                req.setAttribute("name", employeeWithIdParameter.getFullName());
                req.setAttribute("years",years);
                req.setAttribute("idParametr",idParametr);
            req.getRequestDispatcher("/i.jsp").forward(req, resp);
        } else {
            if (idParametr != null) {
                req.setAttribute("ERROR", "Сотрудника с ID:" + idParametr + " не существует!");
            }
            this.getServletContext().getRequestDispatcher("/AAA.jsp").forward(req, resp);
            idParametr = "";
        }
    }

    private void initArraysWithEmployees() {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); ///временное решение получения пути к файлам
        path = path.substring(0, path.indexOf("ServletSchedule")) + "ServletSchedule";
        File rootPath = new File(path);
        String[] arrayFilesInCatalog = rootPath.list();
        ArrayList<File> fileWithEmployees = new ArrayList<>();
        for (String p : arrayFilesInCatalog) {
            if (p.endsWith(".emp")) {
                Path pathToEmp = rootPath.toPath().resolve(p);
                fileWithEmployees.add(new File(pathToEmp.toString()));
            }
        }
        for (File f : fileWithEmployees) {
            try {
                ArrayList<Employee> employees = Employee.readFromFile(f.getAbsolutePath());
                arraysWithEmployees.add(employees);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("<p> ФАЙЛ СО СПИСКОМ СОТРУДНИКОВ " + f + " ПОВРЕЖДЕН!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            } catch (FileNotFoundException e) {
                throw new RuntimeException("<p> ФАЙЛ СО СПИСКОМ СОТРУДНИКОВ " + f + " НЕ НАЙДЕН!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            } catch (IOException e) {
                throw new RuntimeException("<p>ВО ВРЕМЯ ЧТЕНИЯ ФАЙЛА СО СПИСКОМ СОТРУДНИКОВ " + f + " ПРОИЗОШЛА ОШИБКА!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            }
        }
    }

    private void initArraysWithMainTables() {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); ///временное решение получения пути к файлам
        path = path.substring(0, path.indexOf("ServletSchedule")) + "ServletSchedule";
        File rootPath = new File(path);
        String[] arrayFilesInCatalog = rootPath.list();
        ArrayList<File> filesWithMainTables = new ArrayList<>();
        for (String p : arrayFilesInCatalog) {
            if (p.endsWith(".tbl")) {
                Path pathToTables = rootPath.toPath().resolve(p);
                filesWithMainTables.add(new File(pathToTables.toString()));
            }
        }
        for (File f : filesWithMainTables) {
            try {
                MainTable table = MainTable.readTableFromFile(f.getAbsolutePath());
                arraysWithMainTables.add(table);
                years.add(String.valueOf(table.getDate().get(Calendar.YEAR)));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("<p> ФАЙЛ С ГРАФИКОМ " + f + " ПОВРЕЖДЕН!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            } catch (FileNotFoundException e) {
                throw new RuntimeException("<p> ФАЙЛ С ГРАФИКОМ " + f + " НЕ НАЙДЕН!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            } catch (IOException e) {
                throw new RuntimeException("<p>ВО ВРЕМЯ ЧТЕНИЯ ФАЙЛА С ГРАФИКОМ " + f + " ПРОИЗОШЛА ОШИБКА!!!</p> Обратитесь к АДМИНИСТРАТОРУ!");
            }
        }
    }

    private MainTable getMainTableFromArray(String monthParametr, String yearParametr) {
        int month = interpretMonth(monthParametr);
        int year = Integer.parseInt(yearParametr);
        for (MainTable t : arraysWithMainTables) {
            if (t.getDate().get(Calendar.YEAR) == year && t.getDate().get(Calendar.MONTH) == month) {
                return t;
            }
        }
        return null;
    }

    private int interpretMonth(String monthParametr) {
        int ret = -1;
        String param = monthParametr.toLowerCase();
        switch (param) {
            case "январь" -> ret = 0;
            case "февраль" -> ret = 1;
            case "март" -> ret = 2;
            case "апрель" -> ret = 3;
            case "май" -> ret = 4;
            case "июнь" -> ret = 5;
            case "июль" -> ret = 6;
            case "август" -> ret = 7;
            case "сентябрь" -> ret = 8;
            case "октябрь" -> ret = 9;
            case "ноябрь" -> ret = 10;
            case "декабрь" -> ret = 11;
        }
        return ret;
    }
}
