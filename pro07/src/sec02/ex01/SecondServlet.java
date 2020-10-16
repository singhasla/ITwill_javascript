package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//request객체에 저장된 데이터 꺼내오기 -> getAttribute(키); -> 값
		Object obj = req.getAttribute("address");	//업캐스팅
		
		String address = (String)obj;	//다운캐스팅
		
		//클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)설정
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.print(address); //null -> 두번째 서블릿을 재요청 받을 때 리다이렉트 방식으로 재요청했기 때문에
		
		
		
	}
	
	

}
