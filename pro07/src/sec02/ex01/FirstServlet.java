package sec02.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/f")
public class FirstServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//응답할 데이터 종류 설정
		resp.setContentType("text/html;charset=utf-8");
		
		//웹브라우저에서 요청한 request객체 메모리에 address의 값으로 "서울시 성북구"를 바인딩한다.
		req.setAttribute("address", "서울시 성북구");
		
		//두번째 서블릿 재요청(포워딩)
		resp.sendRedirect("s");
		
	}

	
}
