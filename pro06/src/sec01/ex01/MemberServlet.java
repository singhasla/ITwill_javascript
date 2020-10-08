package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 순서1. 클라이언트가 웹브라우저 주소창에 http://localhost:8080/pro06/member주소로 
// 			회원정보를 모두 DB로부터 검색해달라고 요청한다.
@WebServlet("/member")
public class MemberServlet extends HttpServlet {

	//순서2. doGet메소드가 호출될 때 요청시 새로운 HttpServletRequest객체 메모리를 인자로 전달 받는다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트에게 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		
		//클라이언트의 웹브라우저로 응답할 출력 스트림 통로 역할을 하는 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();
		
		//DB작업(SQL문을 작성하여 조회작업)할 MemberDAO객체 생성
		MemberDAO dao = new MemberDAO();
		
		//listMembers()메소드를 호출하여 검색한 회원정보를 각각 MeberVO객체에 저장하여
		//각각의 MemberVO객체들을 최종적으로 ArrayList가변길이 배열에 저장 후
		//DB에서 검색한 회원정보들(MemberVO객체들)을 담고 있는 ArrayList배열을 리턴받는다.
		ArrayList list = dao.listMembers();
	}

}
