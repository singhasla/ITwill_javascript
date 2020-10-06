package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//흐름> 클라이언트가 웹브라우저 주소창에 http://localhost:8080/pro05/login3.html를 입력 후
//		정적인 페이지 login3.html을 아파치톰캣에 요청한다.
//		정적인 login3.html페이지가 웹브라우저 화면에 나오면 클라이언트는 아이디, 비밀번호를 입력 후
//		로그인 전송버튼으로 LoginServlet3서블릿을 요청(POST전송방식으로 요청)한다.
//		POST방식의 요청이 일어나며 서블릿을 요청한 매핑주소는 <form>태그의 action속성의 값으로 login3이다.
//		이때 입력한 데이터는 웹브라우저를 통해 LoginServlet3서블릿의 doPost메소드의 인자로 전달되게 된다.



@WebServlet("/login3")	//서블릿으로 요청한 매핑 주소
public class LoginServlet3 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
				
	}

}
