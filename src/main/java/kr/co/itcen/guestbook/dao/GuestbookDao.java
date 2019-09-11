package kr.co.itcen.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.guestbook.vo.GuestbookVo;

public class GuestbookDao {
	private Connection getConnection() throws SQLException{

	      Connection connection = null;
	      try {
	         Class.forName("org.mariadb.jdbc.Driver");   
	         String url = "jdbc:mysql://192.168.1.66:3306/webdb?characterEncoding=utf8";

	         connection = DriverManager.getConnection(url, "webdb", "webdb");
	      } catch (ClassNotFoundException e) {
	         System.out.println("Fail to Loading Driver: "+e);
	      } 

	      return connection;
	   }

	public Boolean insert(GuestbookVo vo) {
	      Boolean result = false;
	      Connection connection = null;
	      
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {

	         connection = getConnection();

	         String sql = "insert into guestbook values(null, ?, ?, ?,now())";
	         pstmt = connection.prepareStatement(sql);
	         
	         pstmt.setString(1, vo.getName());
	         pstmt.setString(2, vo.getPassword());
	         pstmt.setString(3, vo.getContents());
	         
	         int count = pstmt.executeUpdate();
	         result = (count == 1);


	      } catch (SQLException e) {
	         System.out.println("error: "+e);
	      } finally {
	         try {
	            if(rs != null) {
	               rs.close();
	            }
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            if(connection !=  null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }

	      return result;
	   }
	
	public List<GuestbookVo> getList() {
		 List<GuestbookVo> result = new ArrayList<GuestbookVo>();
	      Connection connection = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         connection = getConnection();

	         String sql = "select no,name,contents,reg_date from guestbook order by no desc";
	         pstmt = connection.prepareStatement(sql);

	         rs = pstmt.executeQuery();

	         while(rs.next()) {
	            Long no = rs.getLong(1);
	            String name = rs.getString(2);
	            String password = rs.getString(3);
	            String contents = rs.getString(4);
	            String regdate = rs.getString(4);

	            GuestbookVo vo = new GuestbookVo();
	            vo.setNo(no);
	            vo.setName(name);
	            vo.setPassword(password);
	            vo.setContents(contents);
	            vo.setRegDate(regdate);

	            result.add(vo);
	         }

	      } catch (SQLException e) {
	         System.out.println("error: "+e);
	      } finally {
	         try {
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            if(connection !=  null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }

	      return result;
	   }
		
	public void delete(String no, String password) {
		Connection connection = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         connection = getConnection();

	         String sql = "delete from guestbook where password =? and no = ?";
	         pstmt = connection.prepareStatement(sql);
	         pstmt.setString(1, password);
	         pstmt.setInt(2, Integer.parseInt(no));
	         
	         pstmt.executeUpdate();

	      } catch (SQLException e) {
	         System.out.println("error: "+e);
	      } finally {
	         try {
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            if(connection !=  null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	
	   }
		
}
	  

