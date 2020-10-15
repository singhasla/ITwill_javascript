package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	//위 네가지 접속 설정값을 이용해서 오라클 DB와 접속한 정보를 지니고 있는 connection객체를 저장할 변수 선언
	private Connection conn;
	
	//DB와 접속 후 우리가 만들 sql문을 생성후 실행할 실행 객체를 담을 변수 선언	
////private Statement stmt;
	//Statement인터페이스를 구현한 자식객체 대신에 
	//PreparedStatement인터페이스를 구현한 자식객체를 사용하기 위해 변수 선언
	private PreparedStatement pstmt;

	//검색한 회원정보들을 임시로 저장할 변수 선언
	private ResultSet rs;
	
	
	//커넥션풀 객체를 저장할 변수 선언
	private DataSource dataFactory;
	
	//기본 생성자 역할 : JNDI기술을 이용하여 context.xml파일의 Resource를 불러와 커넥션풀을 얻는다.
	public MemberDAO(){
		
		try {
			//톰캣이 실행되면 context.xml의 <Resource>태그의 설정을 읽어와서
			//톰캣 메모리에 프로젝트별로 Context객체들을 생성
			//이때 initialContext객체가 하는 역할은 톰캣 실행시 
			//context.xml에 의해서 생성된 Context객체들에 접근하는 역할을 한다.
			Context ctx = new InitialContext();
			
			//JNDI방법으로 접근하기 위해 기본경로(java:/comp/env)를 지정한다.
			//lookup("java:/comp/env")는 그 중 환경설정에 관련된 Context객체에 접근하기 위한 기본주소이다.
			Context envContext = (Context)ctx.lookup("java:/comp/env");
		
			//그런 후 다시 톰캣은 context.xml에 설정한 <Resource name="jdbc/oracle".../>태그의
			//name값 "jdbc/oracle"을 이용해 톰캣이 미리 연결한 DataSource객체(커넥션풀 역할을 하는 객체)를 받아옴
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("커넥션풀(DataSource객체)얻기 실패" + e);
		}
	}
	
	//DB의 모든 회원정보를 조회하는 역할의 메소드
	public ArrayList listMembers(){
		
		ArrayList list = new ArrayList();
		
		try {
			//DataSource(커넥션풀)객체 내부의 Connection객체를 빌려와 DBMS와 연결을 맺음
			conn = dataFactory.getConnection();		//DB연결
			
			// 5. Query 작성하기
			String query = "select * from t_member";
			System.out.println(query);
			
			
			/* 	추가
			 * 	> Connection객체의 prepareStatement()메소드 호출시..
			 * 		생성된 SQL문장을 인자로 전달하면 SQL문장을 PreparedStatement실행객체에 저장후
			 * 		PreparedStatement실행객체 자체를 반환받는다.
			 */
			pstmt = conn.prepareStatement(query);
			
			
			// 6. Query DBMS에 전송하여 실행
			//		Select구문으로 회원정보를 검색한 후 검색한 결과 레코드들을 ResultSet객체에 담아 얻기
////////////rs = stmt.executeQuery(query);	//executeQuery()메소드 : select쿼리만 실행할 수 있음
			rs = pstmt.executeQuery();
			
			//검색한 데이터가 ResultSet객체 메모리에 존재하는 동안 반복
			while(rs.next()){	//ResultSet객체에서 한줄씩 꺼내옴
				
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
			
			//자원해제
			rs.close();
			pstmt.close();
			conn.close();	//DataSource커넥션풀에 Connection반납
			
		} catch (Exception e) {
			System.out.println("listMembers메소드 내부에서 오류 : " + e);
			e.printStackTrace();

		} /* finally {
			
			try {
				// 8. 자원해제
				if(rs != null){rs.close();} //ResultSet객체를 사용하고 있다면 자원 해제
				
				if(pstmt != null){pstmt.close();}			
				
				if(conn != null){conn.close();}
				
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			
		} */
		
		return list;	//DB로부터 검색한 회원정보들은 ArrayList배열에 저장되어 있기 때문에
						//현재 listMembers메소드를 호출하는 서블릿으로 ArrayList배열 전체를 반환
		
	}//listMembers메소드 끝

	
	//memberForm.html에서 입력한 회우너정보들을 서블릿으로 전달해
	//서블릿 내부에서 입력한 회원정보들을 -> MemberVO객체의 각 변수에 저장하여
	//MemberDAO객체의 addMember메소드 호출시 매개변수로 전달하게 된다.
	//전달받은 MemberVO객체의 각 변수값들을 이용해 DB와 연결하여 INSERT작업 하는 메소드
	public void addMember(MemberVO memberVO) {
		
		try {
			//1. DB연결
			//		DataSource(커넥션풀)객체 내부의 Connection객체를 빌려와 DBMS와 연결을 맺음
			conn = dataFactory.getConnection();		//DB연결
			
			// 2. DB에 Insert할 정보들을 MemberVO객체 내부의 각 변수에 저장된 값 얻기
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			// 3. INSERT문장을 문자열로 만들기
			String query = "insert into t_member(id,pwd,name,email) values(?,?,?,?)";

			// 4. insert문장 전체 중에서..? 
			//		기호에 대응되는 값을 제외한 전체 문자열을
			//		OraclePreparedStatementWrapper실행객체에 담아..
			//		실행객체 자체를 반환 받는다.
			pstmt = conn.prepareStatement(query);
			
			// 5. 153번째줄 마지막 '?'기호들에 대응되는 값들을 우리가 입력한 회원정보들로 설정해주자
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			// 6. DB에 INSERT
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("addMember메소드 내부에서 오류 : " + e);
		} finally {
							
				try {
					// 8. 자원해제
					if(rs != null){rs.close();} 
					
					if(pstmt != null){pstmt.close();}			
					
					if(conn != null){conn.close();}	//DataSource커넥션풀에 Connection반납
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
		
	}

	//삭제 링크 <a>태그를 클릭했을때.. 서블릿을 거쳐 전송해 온 회원 ID를 delMember메소드의 인자로 전달받아..
	//DB에 저장되어 있는 회원 한 명의 정보를 삭제!
	public void delMember(String id) {

		try {
			//1. DB연결
			//		DataSource(커넥션풀)객체 내부의 Connection객체를 빌려와 DBMS와 연결을 맺음
			conn = dataFactory.getConnection();		//DB연결
			
			//2. Query문(DELETE문장 만들기)
			//		-> 매개변수로 전달받은 회원 id에 해당하는 회원레코드를 삭제
			//		String query = "delete from t_member where id = " + id;
			String query = "delete from t_member where id = ?";
			
			//3. Connection객체의 prepareStatement()메소드를 호출해서
			//	DBMS에 delete문장 전체를 전달하여 실행시킬 PreparedStatement실행객체를 반환받는다.
			//	단, 위 delete문장 전체를 prepareStatement()메소드 호출시 전달하여
			//	? 기호에 대응되는 설정값을 제외함.
			pstmt = conn.prepareStatement(query);
			
			//3-1. PreparedStatement실행객체에 ? 기호에 대응되는 값을 설정
			pstmt.setString(1, id);
			
			//4. PreparedStatement실행객체의 executeUpdate메소드를 호출해
			//	delete전체 문장을 DB에 전송하여 실행!
			pstmt.executeUpdate();	//참고 -> insert, update, delete 문장 실행 가능!
			
			} catch (Exception e) {
				System.out.println("delMember메소드 내부에서 오류 : " + e);
		} finally {

			try {
				// 5. 자원해제
				if(pstmt != null){pstmt.close();}			
				
				if(conn != null){conn.close();}	//DataSource커넥션풀에 Connection반납
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}


