package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {

	//클라이언트가 GET전송 방식으로 요청하면 호출되는 메소드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet메소드 호출");
		doHandle(request, response);
	}
	
	//클라이언트가 POST전송 방식으로 요청하면 호출되는 메소드	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost메소드 호출");
		doHandle(request, response);
	}

	
	//클라이언트가 GET방식으로 요청하든, POST방식으로 요청하든 모든 호출방식에 대해서 처리할 수 있는 메소드
	//오버라이딩 X
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doHandle메소드 호출");
		//요청한 값 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		//요청한 값 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//출력
		System.out.println("아이디 : " + user_id);
		System.out.println("비밀번호 : " + user_pw);
	}

}
