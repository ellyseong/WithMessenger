package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

	private Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "java7";
		String pw = "java7";
		Connection con = DriverManager.getConnection(url, id, pw);
		return con;
	}

	public boolean isIdExist(String account) throws Exception {
		Connection con = this.getConnection();
		String sql = "select * from messenger where account = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, account);
		ResultSet rs = pstat.executeQuery();
		boolean result = rs.next();
		con.close();
		return result;
	}

	public int insertNewMember(MemberDTO mem) throws Exception {
		Connection con = this.getConnection();
		String sql = "insert into messenger values(with_seq.nextval,?,?,?,?,?,?,sysdate)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, mem.getAccount());
		pstat.setString(2, mem.getPw());
		pstat.setString(3, mem.getName());
		pstat.setString(4, mem.getQuestion());
		pstat.setString(5, mem.getAnswer());
		pstat.setString(6, mem.getWith());
		int result = pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}

	public String searchAccount(MemberDTO mem) throws Exception {
		Connection con = this.getConnection();
		String sql = "select account from messenger where name = ?and question = ? answer=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, mem.getName());
		pstat.setString(2, mem.getQuestion());
		pstat.setString(3, mem.getQuestion());
		ResultSet result = pstat.executeQuery();

		String id = null;
		if (result.next()) {
			id = result.getString("id");
		} else {
			id = "false";
		}
		con.commit();
		con.close();
		return id;
	}

	public String searchPW(MemberDTO mem) throws Exception {
		Connection con = this.getConnection();
		String sql = "select pw from davinci where account =? and name = ? and question =? and answer =?";
		PreparedStatement pstat = getConnection().prepareStatement(sql);
		pstat.setString(1, mem.getAccount());
		pstat.setString(2, mem.getName());
		pstat.setString(3, mem.getQuestion());
		pstat.setString(4, mem.getAnswer());

		ResultSet result = pstat.executeQuery();
		String pw = null;
		if (result.next()) {
			pw = result.getString("pw");
		} else {
			pw = "false";
		}
		con.commit();
		con.close();
		return pw;
	}

	public boolean login(String account, String pw) throws Exception {
		
		Connection con = this.getConnection();
		String sql = "select * from messenger where account=? and pw=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, account);
		pstat.setString(2, pw);

		ResultSet rs = pstat.executeQuery();
		boolean result = rs.next();

		con.commit();
		con.close();
		
		return result;
	}

	public String JoinWith(MemberDTO mem) throws Exception {
		
		String result;
		Connection con = this.getConnection();
		
		boolean isAccount = isIdExist(mem.getWith());
		if (!isAccount) {
			result = "pAccountNotE";
		} else {
			String sql = "update messenger set with_account =? where account = ? and pw = ?";
			PreparedStatement pstat = con.prepareStatement(sql);

			pstat.setString(1, mem.getWith());
			pstat.setString(2, mem.getAccount());
			pstat.setString(3, mem.getPw());

			ResultSet rs = pstat.executeQuery();
			boolean check = rs.next();
			if(check) {
				result = "Ok";
			}else {
				result = "Failed";
			}
			
			con.commit();
			con.close();
		}
		return result;
	}
}
