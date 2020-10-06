package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {

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
	
		//login5.html에서 요청한 값들 인코딩방식을 UTF-8로 설정
		request.setCharacterEncoding("utf-8");
		//클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)을 text/html;charset=utf-8로 설정
		response.setContentType("text/html;charset=utf-8");
		//클라이언트로 웹브라우저로 응답할 출력 스트림 통로 역할을 하는 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();
		//login5.html에서 요청한 값들을 request객체 메모리에서 꺼내오기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		String address = request.getParameter("user_address");
		//클라이언트의 웹브라우저로 응답할 데이터 만들기
		//아이디 : admin
		//비밀번호 : 1234
		//주소 : busan
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		System.out.println("주소 : " + address);
	}

}
