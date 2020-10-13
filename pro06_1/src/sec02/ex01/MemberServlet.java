package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//순서1. 클라이언트가 웹브라우저 주소창에  http://localhost:8080/pro06/member2주소로
//      회원정보를 모두 DB로 부터 검색해줘~ 라고 요청 한다.

@WebServlet("/member2")
public class MemberServlet extends HttpServlet {
	
	//순서2. doGet메소드가 호출될때 요청시 새로운 HttpServletRequest객체 메모리를 인자로 전달 받는다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트에게 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		//클라이언트의 웹브라우저로 응답할 출력 스트림 통로 역할을 하는 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();
		
		//DB작업(SQL문을 작성하여 조회작업)할 MemberDAO객체 생성
		MemberDAO dao = new MemberDAO();
		
		//listMembers()메소드를 호출하여  검색한 회원정보를 각각 MembeVO객체에 저장하여
		//각각의 MemberVO객체들을 최종적으로 ArrayList가변길이 배열에 저장후
		//DB에서 검색한 회원정보들(MemberVO객체들)을 담고 있는 ArrayList배열을 리턴 받는다.
		ArrayList list = dao.listMembers();
			
		out.print("<html><body>");
		out.print("<table border=1>");
		out.print("<tr align=center bgcolor=lightgreen>");		
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td>");
		out.print("</tr>");
		
		//ArrayList배열 내부에 저장된 MemberVO객체들의 갯수만큼 반복
		for(int i=0; i<list.size(); i++){
		//조회한 회원정보들(MemberVO객체들)은 ArrayList가변길이 배열에 저장되어 있으므로
		//ArrayList가변길이 배열 공간에 접근하여  조회한 회원정보들(MemberVO)을 얻는다(get(index위치값))
			Object obj	 = list.get(i);//ArrayList배열의 인덱스 위치를 이용하여 
									   //인덱스 위치에 저장된 MemberVO객체를 꺼내온다.
									   //업캐스팅이 일어남 
			//obj.getter?X
			//다운캐스팅을 시켜줌으로써,  MemeberVO객체의 getter메소드들을 호출할수 있게 되었다.
			MemberVO memberVO = (MemberVO)obj;
			
			String id = memberVO.getId();//조회한 회원정보중 아이디 얻기
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			
			//조회한 회원 정보를 바깥 for문과 <tr>태그를 이용해 출력(응답)
			out.print("<tr>");
			out.print("<td>"+id+"</td>");
			out.print("<td>"+pwd+"</td>");
			out.print("<td>"+name+"</td>");
			out.print("<td>"+email+"</td>");
			out.print("<td>"+joinDate+"</td>");
			out.print("</tr>");
			
		}//for	
		out.print("</table>");
		out.print("</body></html>");	
	}
}





