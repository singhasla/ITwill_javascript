package sec01.ex02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//클라이언트의 웹브라우저로 응답(출력)할 데이터 종류 설정
		resp.setContentType("text/html;charset=utf-8");
		
		//리다이렉트 방식의 포워딩(재요청!!!)기술을 이용 -> resp.sendRedirect()
		resp.sendRedirect("second");
	}

	
}
