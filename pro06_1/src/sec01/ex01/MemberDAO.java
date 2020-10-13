package sec01.ex01;

import java.sql.*;
import java.util.ArrayList;

//����Ŭ DBMS�� ���̺�� ���� �Ͽ� �۾��� Ŭ���� 
public class MemberDAO {

	//1. import java.sql.* �� ����Ŭ  DBMS�� ���̺�� ������ �װ��� ������ ���� ����
	//����̹� ���ϸ� -> oracle.jdbc.driver.OracleDriver
	//DB�� ���� �ּ� ���� -> jdbc:oralce:thin:@ip�ּ�:��Ʈ��ȣ:SID
	//DB���� ����� ���̵� -> scott
	//DB���� ����� ��й�ȣ -> tiger
	
	//OracleDriver.class ��������(����Ŭ����̹�)�� ���� 
	//����Ŭ DBM�� ������Ʈ�� �ִ� MemberDAO���ϰ� �����Ҽ� �ִ�.
	
	//ojdbc6.jar���� ���ο� �ִ� OracleDriver.class ��� ����
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	//DB�� ���� �ּ� ���� 
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	//DB���� ����� ���̵�
	private static final String user = "scott";
	//DB���� ����� ��й�ȣ
	private static final String pwd = "tiger";
	
	
	//�� �װ��� ���� �������� �̿��ؼ� ����Ŭ DB�� ������ ������ ���ϰ� �ִ� Connection��ü�� ������ ���� ����
	private Connection con;
	
	//DB�� ������ �츮�� ���� SQL���� ������ ������ ���� ��ü�� ���� ���� ����
	private Statement  stmt;
	
	//�˻��� ȸ���������� �ӽ÷� ������ �޸� ��ü�� ���� ���� ����
	private ResultSet  rs;
	
	
	//DBMS�� ���� ��Ű�� �޼ҵ�
	private void connDB(){
		
		try{
			//2.����Ŭ ����̹� ������  JVM�޸𸮿� �ε� (����̹� �ε�)
			//���� : �ڹ� ���ϰ� ����Ŭ DB���� ������ ����
			//forName("����Ŭ����̹������� ���� �ϴ� �ּ� ���ڿ���")�� �̿��Ͽ�
			//OracleDriverŬ������ ���� �ν��Ͻ��� �������� �����ؼ� JVM�� ���� �ϰ� �ִ� �޸� ��..
			//DriverManagerŬ������ ����(�ε�)�մϴ�.
			Class.forName(driver); // "oracle.jdbc.driver.OracleDriver"
			
			//3.MemberDAO���ϰ�  ����ŬDBMS���� ���� �α� (������ �������� Connection��ü�� ����ȴ�)
			//���� : Class.forName("oracle.jdbc.driver.OracleDriver"); �޼ҵ带 �̿��Ͽ�
			//�������� ������ new OracleDriver();�ν��Ͻ���....DriverManagerŬ������ ��� �Ǿ� �����Ƿ�
			//�� ����̹� �ν��Ͻ��� ���Ͽ� �ڹ� ���ϰ� ����Ŭ DB�� ������ �Ѵ�.
			//�ڹ� ���ϰ� ����Ŭ DB�� ������ �ǹ� �ϴ� T4CConnection�ν��Ͻ��� ���� �޾� con������ ������.
			con = DriverManager.getConnection(url, user, pwd);
			
			//4. Statement��ü(SQL���� ����ŬDB�� �����Ͽ� ������ ��ü) �����ϱ�
			stmt = con.createStatement();
			
			
		}catch(Exception e){
			System.out.println("DB���� ���� �Ǵ� Statement���ఴü ��� ����" + e);
		}	
	}//connDB�޼ҵ� ��

	//DB�� ��� ȸ�������� ��ȸ �ϴ� ������ �޼ҵ�
	public ArrayList listMembers(){
		
		ArrayList list = new ArrayList(); //�������� �þ�� �迭��ü �޸� ����
		
		try{
			connDB();//4���� ����(����Ŭ����̹�,����ŬDB���� �ּ�����, ID, pw)��  DB�� ���� �մϴ�.
			
			//5. Query�ۼ��ϱ�
			String query = "select * from t_member";
			
			//6. Query DBMS�� �����Ͽ� �����ϱ�
			//Select�������� ȸ�������� �˻���  �˻��� ��� ���ڵ���� ResultSet��ü�� ��� ���
			 rs  = stmt.executeQuery(query);
			
			//�˻��� �����Ͱ� ResultSet��ü �޸𸮿� ���� �ϴ� ���� �ݺ�
			while (rs.next()) {
				//7. select���� ��� �˻��� ������� ����� ResultSet������ ������ ��������	
				String id = rs.getString("id"); //�˻��� id ��� 
				String pwd = rs.getString("pwd"); //�˻��� ��й�ȣ ��� 
				String name = rs.getString("name");//�˻��� ȸ���̸� ��� 
				String email = rs.getString("email");//�˻��� ȸ���� �̸����ּ� ��� 
				Date joinDate = rs.getDate("joinDate");//�˻��� ȸ���� ���Գ�¥ ���� ��� 
				
				//�� ������ ����� ��ȸ�� ���÷����� �ٽ� MemberVO��ü�� ������ 
				//�װ�ü ���ο� �ִ� ������ �ν��Ͻ� ������ �����մϴ�.(setter�޼ҵ� ȣ��)
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				//���� ������ MemberVO��ü�� �ٽ� ArrayList�������̹迭�� �߰��ؼ� ���� ��Ų��.
				list.add(vo);
			}
			
		}catch(Exception e){
			System.out.println("listMembers�޼ҵ� ���ο��� ���� : " + e);
		
		}finally{
			
			try {
				//8.�ڿ�����
				if(rs != null){//ResultSet��ü�� ����ϰ� �ִٸ�
					//�ڿ�����
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		
		return list; //DB�κ��� �˻��� ȸ���������� ArrayList�迭�� ����Ǿ� �ֱ⋚����
					 //���� listMembers�޼ҵ带 ȣ���ϴ� �������� ArrayList�迭 ��ü�� ��ȯ
	}//listMembers()�޼ҵ� ��

}//MemberDAOŬ���� ��





