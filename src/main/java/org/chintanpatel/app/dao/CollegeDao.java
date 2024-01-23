package org.chintanpatel.app.dao;

import org.chintanpatel.app.bean.College;
import org.chintanpatel.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollegeDao {

//    Create method for inserting college records into the database.

    public boolean insertCollegeDetails(College college) {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("insert into tbl_college(college_name)values (?)"))
        {
                pstmt.setString(1, college.getCollegeName());
                int noOfRecordsAffected = pstmt.executeUpdate();
                    if (noOfRecordsAffected > 0) {
                        flag = true;
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


//  Create method for fetching college records from the database.

    public List<College>getAllCollegeList() {
        List<College>collegeList = new ArrayList<>();
        College college;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from tbl_college");
             ResultSet rs = pstmt.executeQuery())
        {
                if (rs != null) {
                    while (rs.next()) {
                        college = new College();
                        college.setCollegeId(rs.getInt("college_id"));
                        college.setCollegeName(rs.getString("college_name"));
                        collegeList.add(college);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collegeList;
    }


//    Create method for finding single college record from the database.

    public College findCollegeById(int collegeId) {
        College college = null;
        ResultSet rs = null;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from tbl_college where college_id=?"))
        {
                pstmt.setInt(1,collegeId);
                rs = pstmt.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            college = new College();
                            college.setCollegeId(rs.getInt("college_id"));
                            college.setCollegeName(rs.getString("college_name"));
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
        return college;
    }


//    Create method for updating college record and store back it into the database.

    public boolean updateCollegeDetails(College college) {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("update tbl_college set college_name=? " +
                "where college_id=?"))
        {
                pstmt.setString(1, college.getCollegeName());
                pstmt.setInt(2,college.getCollegeId());
                int noOfRecordsAffected = pstmt.executeUpdate();
                    if (noOfRecordsAffected > 0) {
                        flag = true;
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


//    Create method for deleting college record from the database.

    public boolean deleteCollegeById(int collegeId) {
        boolean flag = false;
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("delete from tbl_college where college_id=?"))
        {
                pstmt.setInt(1,collegeId);
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
