package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 역할 : login.html에서 입력한 아이디나 비밀번호가 제대로 입력하지 않으면 오류메세지를 출력한 후에
 * 		  다시 로그인창(login.html)으로 이동시킨다.
 */

@WebServlet("/loginTest")	//http://localhost:8080/pro05/
public class LoginTest extends HttpServlet {

	// 1순위 호출
	public void init(ServletConfig config) throws ServletException {
		System.out.println("톰캣 메모리에 LoginTest서블릿 클래스가 로드 되는 시점에 호출됨");
	}

	// 2순위 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 클라이언트가 입력한 아이디 비밀번호를 얻기 전에 한글인코딩방식 지정
		request.setCharacterEncoding("utf-8");
		
		// 2. 클라이언트가 입력한 아이디, 비밀번호를 request 내장객체 메모리 영역에서 꺼내오기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		// 3. 클라이언트의 웹브라우저로 응답할 값 생성
			//순서1. 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE) 설정
		response.setContentType("text/html;charset=utf-8");
			//순서2. 클라이언트의 웹브라우저로 응답(출력)할 출력스트림 통로 얻기 
		PrintWriter out = response.getWriter();
			//순서3. 응답할 값 생성하고 클라이언트의 웹브라우저로 응답(출력)
		if( id != null && (id.length() != 0) ){	//아이디를 입력했다면
			out.print("<html>");
			out.print("<body>");
				out.print(id + "님, 로그인 하셨습니다.");
			out.print("</body>");
			out.print("</html>");
		} else {	//아이디를 입력 하지 않았다면
			out.print("<html>");
			out.print("<body>");
				out.print("아이디를 입력하세요.<br>");
				out.print("<a href='http://localhost:8080/pro05/test01/login.html'> 로그인 페이지로 이동 </a>");
			out.print("</body>");
			out.print("</html>");
		}

	}

	// 마지막 호출
	public void destroy() {
		System.out.println("톰캣을 중지하면(서블릿프로그램이 종료되면) 호출되는 메소드");
	}

	
}
