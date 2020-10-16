package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/s1")
public class SecondServlet1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//첫번째 서블릿으로부터 전달된 request객체 메모리에 저장된 address키에 대응되는 "서울시 성북구"객체를 얻는다
		//request객체에 저장된 데이터 꺼내오기 -> getAttribute(키); -> 값
		Object obj = req.getAttribute("address");	//업캐스팅
		
		String address = (String)obj;	//다운캐스팅
		
		
		//클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)설정
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//"서울시 성북구" -> 디스패처 방식으로 두번째 서블릿을 재요청(포워딩)하기 때문에
		//					두번째 서블릿 재요청시 웹브라우저를 거치지 않고 재요청한다.
		out.print(address); 
		
		
		
	}
	
	

}
