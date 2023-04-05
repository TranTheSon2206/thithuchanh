package thithuchanhh;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;


public class menu {

        private ArrayList<Student> studentList = new ArrayList<>();
        public void run() {
            while (true) {
                displayMenu();
                int option = getOption();
                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        saveStudents();
                        break;
                    case 4:
                        System.out.println("Exitting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option!");
                }
            }
        }

        public void displayMenu() {
            System.out.println("\n----------File Student----------");
            System.out.println("1. Add student records");
            System.out.println("2. Display student records");
            System.out.println("3. Save");
            System.out.println("4. Exit");
        }

        public int getOption() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter option: ");
            return scanner.nextInt();
        }

        public void addStudent() {
            System.out.println("\n-----Add student records-----");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter student ID: ");
            String studentID = scanner.next();

            System.out.print("Enter name: ");
            String name = scanner.next();

            System.out.print("Enter address: ");
            String address = scanner.next();

            System.out.print("Enter phone: ");
            String phone = scanner.next();

            Student student = new Student(studentID, name, address, phone);
            studentList.add(student);
            System.out.println("Added student records");
        }

        public void displayStudents() {
            System.out.println("\n-----Display student records-----");
            for (Student student : studentList) {
                System.out.println(student.toString());
            }
        }

        public void saveStudents() {



            for (Student student : studentList) {

                try {
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hocsinh","root","");
                    cn.setAutoCommit(false);

                    String sql = "insert into student values (?, ?, ?, ?)";
                    PreparedStatement pst1 = cn.prepareStatement(sql);
                    pst1.setString(1, student.getStudentID());
                    pst1.setString(2,student.getName());
                    pst1.setString(3,student.getAddress());
                    pst1.setString(4,student.getPhone());



                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Save successful");
        }

    class Student {
        private String studentID;
        private String name;
        private String address;
        private String phone;

        public Student(String studentID, String name, String address, String phone) {
            this.studentID = studentID;
            this.name = name;
            this.address = address;
            this.phone = phone;
        }

        public String getStudentID() {
            return studentID;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "studentID='" + studentID + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    }
