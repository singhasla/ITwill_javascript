package sec02.ex02;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//오라클 DBMS의 테이블과 연결 하여 작업할 클래스 
public class MemberDAO {

	//위 네가지 접속 설정값을 이용해서 오라클 DB와 접속한 정보를 지니고 있는 Connection객체를 저장할 변수 선언
	private Connection con;
	
	//DB와 접속후 우리가 만들 SQL문을 생성후 실행할 실행 객체를 담을 변수 선언
//	private Statement  stmt;
	//Statement인터페이스를 구현한 자식객체 대신~~ 
	//PreparedStatement인터페이스를 구현한 자식객체를 사용하기 위해 변수 선언
	private PreparedStatement pstmt;
	
	//검색한 회원정보들을 임시로 저장할 메모리 객체를 담을 변수 선언
	private ResultSet  rs;
	
	//커넥션풀 객체를 저장할 변수 선언 
	private DataSource dataFactory;
	
	//기본 생성자 역할 : JNDI기술을 이용하여  context.xml파일의 Resource를 불러와 커넥션풀을 얻는다.
	public MemberDAO() {   
		try{
			//톰캣이 실행되면 context.xml의 <Resource/>태그의 설정을 읽어와
			//톰캣 메모리에 프로젝트별로 Context객체들을 생성합니다.
			//이때 InitialContext객체가 하는 역할은 
			//톰캣 실행시 context.xml에 의해서 생성된 Context객체들에 접근하는 역할을 합니다.
			Context ctx = new InitialContext();
			
			//JNDI방법으로 접근하기 위해 기본 경로(java:/comp/env)를 지정 합니다
			//lookup("java:/comp/env")는 그 중 환경 설정에 관련된 컨텍스트 객체에 접근하기 위한 기본주소입니다.
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			
			/*커넥션풀 얻기*/
			//그런후 다시 톰캣은  context.xml에 설정한 <Resource name="jdbc/oracle".../>태그의 
			//name값 "jdbc/oracle"을 이용해 톰캣이 미리 연결한 DataSource객체(커넥션풀 역할을 하는 객체)를 받아옵니다
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
			
		}catch(Exception e){
			System.out.println("커넥션풀(DataSource객체)얻기 실패" + e);
		}
	}//MemberDAO생성자 
	
	
	//DB의 모든 회원정보를 조회 하는 역할의 메소드
	public ArrayList listMembers(){
		
		ArrayList list = new ArrayList(); //가변으로 늘어나는 배열객체 메모리 생성
		
		try{
			//DataSource(커넥션풀)객체 내부의 Connection객체를 빌려와 DBMS와 연결을 맺음 
			con = dataFactory.getConnection(); //DB연결 
			
			//5. Query작성하기
			String query = "select * from t_member";
		
			//추가 > Connection객체의 preparedStatement()메소드 호출시..
			//생성된 SQL문장을 인자로 전달 하면  SQL문장을  PreparedStatement실행객체에 저장후 
			//PreparedStatement실행객체 자체를 반환 받는다.
			pstmt = con.prepareStatement(query);
			
			//6. Query DBMS에 전송하여 실행하기
			//Select구문으로 회원정보를 검색후  검색한 결과 레코드들을 ResultSet객체에 담아 얻기
			//rs  = stmt.executeQuery(query);
			rs = pstmt.executeQuery();
			
		
			//검색한 데이터가 ResultSet객체 메모리에 존재 하는 동안 반복
			while (rs.next()) {
				//7. select문인 경우 검색한 결과값이 저장된 ResultSet내부의 데이터 꺼내오기	
				String id = rs.getString("id"); //검색한 id 얻기 
				String pwd = rs.getString("pwd"); //검색한 비밀번호 얻기 
				String name = rs.getString("name");//검색한 회원이름 얻기 
				String email = rs.getString("email");//검색한 회원의 이메일주소 얻기 
				Date joinDate = rs.getDate("joinDate");//검색한 회원의 가입날짜 정보 얻기 
				
				//위 변수에 저장된 조회한 각컬럼값을 다시 MemberVO객체를 생성해 
				//그객체 내부에 있는 각각의 인스턴스 변수에 저장합니다.(setter메소드 호출)
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				//위에 설정된 MemberVO객체를 다시 ArrayList가변길이배열에 추가해서 저장 시킨다.
				list.add(vo);
			}
			
		}catch(Exception e){
			System.out.println("listMembers메소드 내부에서 오류 : " + e);
		
		}finally{
			
			try {
				//8.자원해제
				if(rs != null){//ResultSet객체를 사용하고 있다면
					//자원해제
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(con != null){
					con.close(); //DataSoruce커넥션풀에 Connection반납 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		
		return list; //DB로부터 검색한 회원정보들은 ArrayList배열에 저장되어 있기떄문에
					 //현재 listMembers메소드를 호출하는 서블릿으로 ArrayList배열 전체를 반환
	}//listMembers()메소드 끝

	//membeForm.html에서 입력한 회원정보들을 서블릿으로 전달 해
	//서블릿 내부에서 입력한 회원정보들을 -> MemberVO객체의 각변수에 저장하여
	//MemberDAO객체의 addMember메소드 호출시 매개변수로 전달 하게 된다.
	//전달받은 MemberVO객체의 각변수값들을 이용해  DB와 연결하여 INSERT작업 하는 메소드 
	public void addMember(MemberVO memberVO){
		
		try{
			//1.DB연결
			//-> DataSource커넥션풀 공간에 저장되어 있는 DB와 미리 연결을 맺은 Connection객체 얻기
			con = dataFactory.getConnection();
			
			//2.DB에 INSERT회원가입 시킬 정보들을 MemberVO객체 내부의 각변수에 저장된 값 얻기 
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			//3.INSERT문장을 문자열로  만들기
			String query = "insert into t_member(id,pwd,name,email)values(?,?,?,?)";
			
			//4. insert문장 전체중에서..
			//   ?기호에 대응되는 값을 제외한 전체 문자열을 
			//   OraclePreparedStatementWrapper실행객체에 담아..
			//   실행객체 자체를 반환 받는다
			pstmt = con.prepareStatement(query);
			//5. ?기호들에 대응되는 값들을 우리가 입력한 회원정보들로 설정 해주자 
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			//6.DB에 INSERT
			pstmt.executeUpdate();			
			
		}catch(Exception e){
			System.out.println("addMember메소드 내부에서 오류 : " + e);
		}finally{
			//7.자원해제
			try {
				if(rs != null){//ResultSet객체를 사용하고 있다면
					//자원해제
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(con != null){
					con.close(); //DataSoruce커넥션풀에 Connection반납 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}//catch				
		}//finally끝
	}//addMember()메소드 끝
	
}//MemberDAO클래스 끝





