package servlet;

import static util.CommenConstant.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import test.web.pojo.Emp;
import test.web.pojo.Member;

@WebServlet("/testEmp")
public class testEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收前端傳來的Json格式字串, 並轉成Emp物件
		Gson gson = new Gson();
		Emp emp = gson.fromJson(req.getReader(), Emp.class);
		List<Emp> list = selectByCondition(emp);
		//至資料庫查詢(select)並取得結果
//		emp = selectByEmpno(emp.getEmpno());
		//將查詢結果轉成Json格式字串並回應至前端
		String jString = gson.toJson(list);
		resp.getWriter().write(jString);
	}
	
	
	private List<Emp> selectByCondition(Emp emp) {
		StringBuilder sql = new StringBuilder("select * from EMP where 1 = 1 ");
		
		Integer empno = emp.getEmpno();
		if (empno != null) {
			sql.append("and EMPNO = ? ");
		}
		String ename = emp.getEname();
		if (ename != null) {
			sql.append("and ENAME = ? ");
		}
		String job = emp.getJob();
		if (job != null) {
			sql.append("and JOB = ? ");
		}
		Integer mgr = emp.getMgr();
		if (mgr != null) {
			sql.append("and MGR = ? ");
		}
		Timestamp hiredate = emp.getHiredate();
		if (hiredate != null) {
			sql.append("and HIREDATE = ? ");
		}
		Double sal = emp.getSal();
		if (sal != null) {
			sql.append("and SAL = ? ");
		}
		Double comm = emp.getComm();
		if (comm != null) {
			sql.append("and COMM = ? ");
		}
		Integer deptno = emp.getDeptno();
		if (deptno != null) {
			sql.append("and DEPTNO = ? ");
		}
		
		DataSource dataSource = null; // 先給予值. 下面的Connection才能取值
		// 因datasource是介面, 故無法實力化, 改為InitialContext()
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			int position = 1;
			if (empno != null) {
				pstmt.setInt(position++, empno);
			}
			if (ename != null) {
				pstmt.setString(position++, ename);
			}
			if (job != null) {
				pstmt.setString(position++, job);
			}
			if (mgr != null) {
				pstmt.setInt(position++, mgr);
			}
			if (hiredate != null) {
				pstmt.setTimestamp(position++, hiredate);
			}
			if (sal != null) {
				pstmt.setDouble(position++, sal);
			}
			if (comm != null) {
				pstmt.setDouble(position++, comm);
			}
			if (deptno != null) {
				pstmt.setInt(position, deptno);
			}
			
			try(ResultSet rs = pstmt.executeQuery()) {
				List<Emp> list = new ArrayList<>();
				while(rs.next()) {
					Emp e = new Emp();
					e.setEmpno(rs.getInt("EMPNO"));
					e.setEname(rs.getString("ENAME"));
					e.setJob(rs.getString("JOB"));
					e.setMgr(rs.getInt("MGR"));
					e.setHiredate(rs.getTimestamp("HIREDATE"));
					e.setSal(rs.getDouble("SAL"));
					e.setComm(rs.getDouble("COMM"));
					e.setDeptno(rs.getInt("DEPTNO"));
					list.add(e);
				}
				return list;
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	} 
//---------------------------------------------------------------------------------
	private Emp selectByEmpno(Integer empno) {
		//不知道為什麼, 在此環境需要再加上forName(不過未來不是用Driver Manager)
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try (
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement("select * from EMP where empno = ?");	
		){
			pstmt.setInt(1, empno);
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Emp emp = new Emp();
					emp.setEmpno(rs.getInt("EMPNO"));
					emp.setEname(rs.getString("ENAME"));
					emp.setJob(rs.getString("JOB"));
					emp.setMgr(rs.getInt("MGR"));
					emp.setHiredate(rs.getTimestamp("HIREDATE"));
					emp.setSal(rs.getDouble("SAL"));
					emp.setComm(rs.getDouble("COMM"));
					emp.setDeptno(rs.getInt("DEPTNO"));
					return emp;
				}
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
