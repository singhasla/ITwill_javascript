package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {

	//login2.html에서 입력한 아이디와 비밀번호 모두 HttpServletRequest객체에 저장한 뒤
	//아래의 doGet메소드의 매개변수 request로 전달 받는다.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트가 입력한 값(요청한 값) 얻기
		request.setCharacterEncoding("UTF-8");
		
		//2.request객체의 getParameter()메소드를 이용하여 입력한 요청값들을 request메모리에서 꺼내오기
		//입력한 아이디 얻기
		String id = request.getParameter("user_id");
		//입력한 비밀번호 얻기
		String pw = request.getParameter("user_pw");
		
		//3. 요청한 값을 이용해서 클라이언트의 웹브라우저로 응답할 값을 마련
			//순서1. 요청받은 서블릿 입장에서는 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)를 설정
			  response.setContentType("text/html;charset=utf-8");	//응답할 데이터 종류 -> HTML태그로 설정
			  														//응답할 데이터의 인코딩 방식 설정
			//순서2. HttpServletResponse객체의 getWriter()메소드를 호출하여
			//		클라이언트의 웹브라우저와 연결된 출력 스트림 통로 역할을 하는 PrintWriter객체 얻기
			  PrintWriter out = response.getWriter();
			
			//순서3. 
			  String data =  "<html>";
			  		 data += "<body>";
			  		 data += "입력 받은 아이디: " + id;
			  		 data += "<br>";
			  		 data += "입력 받은 비밀번호: " + pw;
			  		 data += "</body>";
			  		 data += "</html>";
		
		//4. 마련한 응답할 값을 클라이언트의 웹브라우저로 다시 전송해서 출력(응답)
		//		PrintWriter객체의 print()메소드를 호출해 HTML태그 문자열을 웹브라우저로 출력(응답)
			  out.print(data);
			  
	}

}
