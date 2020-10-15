package sec01.ex02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first1")
public class FirstServlet1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//클라이언트의 웹브라우저로 응답(출력)할 데이터 종류 설정
		resp.setContentType("text/html;charset=utf-8");
		
		//refresh방식의 포워딩(재요청)기술 
		//		-> response.addHeader("Refresh", "휴식시간; url=재요청할 서블릿매핑주소")
		//	addHead호출시,,, Refresh로 설정하고 1초 후 url=second에 지정한 SecondServlet1(두번째 서블릿)을 재요청하게 됨
		resp.addHeader("Refresh", "3; url=second1"); 
	}

	
}
