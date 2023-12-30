package org.chintanpatel.app.dao;

import org.chintanpatel.app.domain.Student;
import org.chintanpatel.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    // Create insert method to adding student records into the database.

    public boolean insertStudent(Student student) {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("insert into tbl_student(first_name, middle_name, " +
                     "last_name, address, email, mobile, user_name, password, college_id)values (?,?,?,?,?,?,?,?,?)"))
        {
                pstmt.setString(1, student.getFirstName());
                pstmt.setString(2,student.getMiddleName());
                pstmt.setString(3, student.getLastName());
                pstmt.setString(4, student.getAddress());
                pstmt.setString(5, student.getEmail());
                pstmt.setLong(6, student.getMobile());
                pstmt.setString(7, student.getUserName());
                pstmt.setString(8, student.getPassword());
                pstmt.setInt(9, student.getCollegeId());
                int noOfRecordsAffected = pstmt.executeUpdate();
                    if (noOfRecordsAffected > 0) {
                        flag = true;
                    }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    // Create list method for fetching student records from the database.

    public List<Student>getAllStudentList() {
        List<Student>studentList = new ArrayList<>();
        Student student;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select s.*, c.college_id,c.college_name from tbl_college c, tbl_student s where s.college_id=c.college_id");
             ResultSet rs = pstmt.executeQuery())
        {
                if (rs != null) {
                    while (rs.next()) {
                        student = new Student();
                        student.setStudentId(rs.getInt("student_id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setMiddleName(rs.getString("middle_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setAddress(rs.getString("address"));
                        student.setEmail(rs.getString("email"));
                        student.setMobile(rs.getLong("mobile"));
                        student.setUserName(rs.getString("user_name"));
                        student.setPassword(rs.getString("password"));
                        student.setCollegeId(rs.getInt("college_id"));
                        student.setCollegeName(rs.getString("college_name"));
                        studentList.add(student);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }


    // Create find method for fetching single student record from the database.

    public Student findStudentById(int studentId) {
        Student student = null;
        ResultSet rs =  null;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select s.*, c.college_id, c.college_name from " +
                "tbl_college c, tbl_student s where s.college_id=c.college_id and student_id=?"))
        {
                pstmt.setInt(1,studentId);
                rs = pstmt.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            student = new Student();
                            student.setStudentId(rs.getInt("student_id"));
                            student.setFirstName(rs.getString("first_name"));
                            student.setMiddleName(rs.getString("middle_name"));
                            student.setLastName(rs.getString("last_name"));
                            student.setAddress(rs.getString("address"));
                            student.setEmail(rs.getString("email"));
                            student.setMobile(rs.getLong("mobile"));
                            student.setUserName(rs.getString("user_name"));
                            student.setPassword(rs.getString("password"));
                            student.setCollegeId(rs.getInt("college_id"));
                            student.setCollegeName(rs.getString("college_name"));
                        }
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
    }


    // Create method for updating student record and store it back to database.

    public boolean updateStudent(Student student) {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("update tbl_student set first_name=?, middle_name=?, " +
                "last_name=?, address=?, email=?, mobile=?, user_name=?, password=?, college_id=? where student_id=?"))
        {
                pstmt.setString(1, student.getFirstName());
                pstmt.setString(2,student.getMiddleName());
                pstmt.setString(3, student.getLastName());
                pstmt.setString(4, student.getAddress());
                pstmt.setString(5, student.getEmail());
                pstmt.setLong(6, student.getMobile());
                pstmt.setString(7, student.getUserName());
                pstmt.setString(8, student.getPassword());
                pstmt.setInt(9, student.getCollegeId());
                pstmt.setInt(10,student.getStudentId());
                int noOfRecordsAffected = pstmt.executeUpdate();
                    if (noOfRecordsAffected > 0) {
                        flag = true;
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    // Create method for deleting single student record from the database.

    public boolean deleteStudentById(int studentId) {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("delete from tbl_student where student_id=?"))
        {
                pstmt.setInt(1,studentId);
                int noOfRecordsAffected = pstmt.executeUpdate();
                    if (noOfRecordsAffected > 0) {
                        flag = true;
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
