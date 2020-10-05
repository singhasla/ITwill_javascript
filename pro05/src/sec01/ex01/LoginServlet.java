package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//순서1. 웹브라우저 주소창에 http://localhos:8080/pro05/login.html을 요청한다.
//			아이디, 비밀번호를 입력할 수 있는 화면이 나타난다.

//순서2. login.html화면에서 아이디와 비밀번호를 입력한 후 로그인 전송 버튼을 클릭하면
//			입력한 아이디와 비밀번호를 request 내장객체 메모리에 저장 후 서블릿으로 전송(전달)한다.

//순서3. GET방식으로 요청했기 때문에 doGet메소드가 호출될 때 매개변수로 request 내장객체 메모리를 전달 받는다.

//순서4. requeset내장객체 메모리영역에서 저장된 클라이언트가 입력한 요청값을 꺼내어서 얻는다.

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트가 입력한 값(요청한 값) 얻기
		request.setCharacterEncoding("UTF-8");
		
		//request객체의 getParameter()메소드를 이용하여 입력한 요청값들을 request메모리에서 꺼내오기
		//입력한 아이디 얻기
		String user_id = request.getParameter("user_id");
		//입력한 비밀번호 얻기
		String user_pw = request.getParameter("user_pw");
		
		System.out.println("입력한 아이디: " + user_id);
		System.out.println("입력한 비밀번호: " + user_pw);
		//2. 요청한 값을 이용해서 클라이언트의 웹브라우저로 응답할 값을 마련
		
		//3. 마련한 응답할 값을 클라이언트의 웹브라우저로 다시 전송해서 출력(응답)
		
		System.out.println("doGet메소드 호출됨");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost메소드 호출됨");
	}

}
