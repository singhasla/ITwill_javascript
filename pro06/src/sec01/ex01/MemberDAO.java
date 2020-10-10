package sec01.ex01;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {

	// 1. import java.sql.* 과 오라클 DBMS의 테이블과 연결할 4가지 접속할 정보 설정
	// 		드라이버 파일명 		-> oracle.jdbc.driver.OracleDriver
	// 		DB에 접속주소 정보 		-> jdbc:oracle:thin:@ip주소:포트번호:SID
	// 		DB접속 사용자 아이디 	-> scott
	// 		DB접속 사용자 비밀번호 	-> tiger
	
	// OracleDriver.class 실행파일(오라클 드라이버)을 통해
	// 오라클DMBS와 프로젝트에 있는 MemberDAO파일과 연결할 수 있다.
	
	//ojdbc6.jar파일 내부에 있는 OracleDriver.class 경로 정보
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	//DB에 접속할 주소 정보
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	//DB접속 사용자 아이디 	-> scott
	private static final String user = "scott";
	//DB접속 사용자 비밀번호	-> tiger
	private static final String pwd = "tiger";

	
	//위 네가지 접속 설정값을 이용해서 오라클 DB와 접속한 정보를 지니고 있는 connection객체를 저장할 변수 선언
	private Connection conn;
	
	//DB와 접속 후 우리가 만들 sql문을 생성후 실행할 실행 객체를 담을 변수 선언
	private Statement stmt;

	//검색한 회원정보들을 임시로 저장할 변수 선언
	private ResultSet rs;
	
	//DBMS와 연동시키는 메소드
	private void connDB(){
	
		try {
			
			// 2. 오라클 드라이버 파일을 JVM메모리에 로딩(드라이버 로딩)
			//		설명 : 자바 파일과 오라클 DB와의 연동을 위해
			//				forName("오라클 드라이버 파일이 존재하는 주소 문자열명")을 이용하여
			//				OracleDriver클래스에 대한 인스턴스를 동적으로 생성해서 JVM이 차지하고 있는 메모리 중..
			//				DriverManager클래스에 저장한다.
			Class.forName(driver);

			// 3. MemberDAO파일과 오라클DBMS와의 접속 맺기(접속한 정보들을 Connection객체에 저장된다)
			//		설명 : Class.forName("oracle.jdbc.driver.OracleDriver"); 메소드를 이용하여
			//				동적으로 생성된 new OracleDriver(); 인스턴스는 /.../DriverManager클래스에 등록되어 잇으므로
			//				이 드라이버 인스튼스를 통하여 자바 파일과 오라클DB와 접속을 한다.
			//				자바 파일과 오라클 DB와 접속을 의미하는 T4CConnection인스턴스를 리턴받아 conn변수에 저장함.
			conn = DriverManager.getConnection(url, user, pwd);
			
			// 4. Statement객체(SQL문을 오라클DB에 전달하여 실행할 객체) 생성하기
			stmt = conn.createStatement();
					
		} catch (Exception e) {
			System.out.println("DB연결 실패 또는 Statement실행객체 얻기 실패 : " + e);
		}
		
	}//connDB메소드 끝
	
	
	//DB의 모든 회원정보를 조회하는 역할의 메소드
	public ArrayList listMembers(){
		
		ArrayList list = new ArrayList();
		
		try {
			connDB();	//4가지 정보(오라클드라이버, 오라클DB접속 주소정보, ID, PW)로 DB와 연결
			
			// 5. Query 작성하기
			String query = "select * from t_member";
			
			// 6. Query DBMS에 전송하여 실행
			//		Select구문으로 회원정보를 검색한 후 검색한 결과 레코드들을 ResultSet객체에 담아 얻기
			rs = stmt.executeQuery(query);
			
			//검색한 데이터가 ResultSet객체 메모리에 존재하는 동안 반복
			while(rs.next()){
				
				// 7. select문인 경우 검색한 결과값이 저장된 ResultSet내부의 데이터 꺼내오기
				String id = rs.getString("id");			//검색한 회원 id 얻기
				String pwd = rs.getString("pwd");		//검색한 회원 pwd 얻기
				String name = rs.getString("name");		//검색한 회원 이름 얻기
				String email = rs.getString("email");	//검색한 회원 email 얻기
				Date joindate = rs.getDate("joindate");	//검색한 회원 가입날짜 정보 얻기
				
				//위 변수에 저장된 각 컬럼값을 다시 MemerVO객체를 생성해
				//그 객체 내부에 있는 각각의 인스턴스 변수에 저장한다.(setter메소드 호출)
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoindate(joindate);
				
				//위에 설정된 memberVO객체를 다시 ArrayList 가변길이배열에 추가해서 저장시킨다.
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("listMembers메소드 내부에서 오류 : " + e);
		} finally {
			
			try {
				// 8. 자원해제
				if(rs != null){rs.close();} //ResultSet객체를 사용하고 있다면 자원 해제
				
				if(stmt != null){stmt.close();}			
				
				if(conn != null){conn.close();}
				
			} catch (Exception e2) {
				//?????????????????????????
				e2.printStackTrace();
			}
			
		}
		
		return list;	//DB로부터 검색한 회원정보들은 ArrayList배열에 저장되어 있기 때문에
						//현재 listMembers메소드를 호출하는 서블릿으로 ArrayList배열 전체를 반환
		
	}//listMembers메소드 끝
	
}

