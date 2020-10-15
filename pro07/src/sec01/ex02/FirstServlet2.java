package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first2")
public class FirstServlet2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//클라이언트의 웹브라우저로 응답(출력)할 데이터 종류 설정
		resp.setContentType("text/html;charset=utf-8");
		
		//첫번째 서블릿에서 PrintWriter객체(출력스트림통로)를 이용해 자바스크립트 코드를 작성해서
		//두번째 서블릿인 SecondServlet2서블릿으로 location객체방법으로 재요청해서 이동시킴
		PrintWriter printWriter = resp.getWriter();
		printWriter.print("<script type='text/javascript'>");
		printWriter.print("location.href='second2'");	//재요청
		printWriter.print("</script>");
	}

	
}
