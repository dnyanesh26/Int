package org.example.java8.Streams;

import org.example.java8.Streams.pojo.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class EmployeeStreams {

    static List<Employee> employees = Arrays.asList(
            new Employee(1, "Abraham", 29, "IT", "Mumbai", 20000, "Male"),
            new Employee(2, "Mary", 27, "Sales", "Chennai", 25000, "Female"),
            new Employee(3, "Joe", 28, "IT", "Chennai", 22000, "Male"),
            new Employee(4, "John", 29, "Sales", "Gurgaon", 29000, "Male"),
            new Employee(5, "Liza", 25, "Sales", "Bangalore", 32000, "Female"),
            new Employee(6, "Peter", 27, "Admin", "Mumbai", 31500, "Male"),
            new Employee(7, "Harry", 30, "Research", "Kochi", 21000, "Male")
    );

    public static void main(String[] args) {

        List<Employee> EmpnamewithA = employees.stream().filter(emp -> emp.getName().startsWith("A")).toList();
        System.out.println("Emp names starts with A:"+ EmpnamewithA);

        List<String> namewithA = employees.stream().map(Employee::getName).filter(name -> name.startsWith("A")).toList();
        System.out.println("Emp names starts with A:"+ namewithA);

        Map<String,List<Employee>> ObjdepEmp = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames));
        System.out.println("ObjdepEmp: "+ ObjdepEmp);
        Map<String,List<String>> depEmp = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.mapping(Employee::getName,toList())));
        System.out.println("depEmp: "+ depEmp);

        int maxAge = employees.stream().map(Employee::getAge).max(Integer::compare).get();
        System.out.println("maxAge: "+maxAge);

        List<String> deptName = employees.stream().map(Employee::getDepartNames).distinct().toList();
        System.out.println("DeptName: "+deptName);

        Map<String,Long> countEmpDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.counting()));
        System.out.println("countEmpDept: "+countEmpDept);

        List<String> empLess30 = employees.stream().filter(emp -> emp.getAge()<30).map(emp -> emp.getName()).toList();
        System.out.println("empLess30: "+empLess30);

        Map<String, Double> gendAvgAge = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println("gendAvgAge: "+gendAvgAge);

        String deptMaxEmp = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        System.out.println("deptMaxEmp: "+deptMaxEmp);

        Map<String,List<String>> delhiEmp = employees.stream().filter(emp -> emp.getAddress().equals("Mumbai") || emp.getAddress().equals("Chennai")).sorted(Comparator.comparing(Employee::getName)).collect(Collectors.groupingBy(Employee::getAddress,Collectors.mapping(Employee::getName,toList())));
        System.out.println("delhiEmp: "+delhiEmp);

        Map<String, Double> avgDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("avgDept: "+ avgDept);

        Map<String, Optional<Employee>> maxSalDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        Map<String, Double> maxSalDept1 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames, Collectors.mapping(Employee::getSalary,Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingDouble(l -> l)),
                opt -> opt.orElse(0.0) // Converts Optional<Double> to Double
        ))));

        System.out.println("maxSalDept: "+maxSalDept);
        System.out.println("maxSalDept1: "+maxSalDept1);

        String empName = employees.stream().sorted(Comparator.comparing(Employee::getSalary)).skip(1).findFirst().get().getName();
        System.out.println("empName: "+empName);


    }
}