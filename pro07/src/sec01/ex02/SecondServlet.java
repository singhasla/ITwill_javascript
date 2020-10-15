package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//SecondServlet서블릿클래스는 
//첫번째 서블릿인 FirstServlet서블릿에서 재요청을 받아 실행하는 두번째 서블릿클래스이다.
@WebServlet("/second")
public class SecondServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//클라이언트의 웹브라우저로 응답(출력)할 데이터 종류 설정
		resp.setContentType("text/html;charset=utf-8");
		
		//클라이언트의 웹브라우저로 응답(출력)할 스트림통로 생성
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("리다이렉트 방식을 이용한 포워딩(재요청) 실습");
		out.println("</body></html>");
	}

}
