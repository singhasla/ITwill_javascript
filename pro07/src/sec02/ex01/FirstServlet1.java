package sec02.ex01;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 1. 웹브라우저 주소창에 주소를 http://localhost:8080/pro07/f1 입력하여 FirstServlet1 서블릿 요청
 *
 * 2. FirstServlet서블릿 클래스 내부에서 웹브라우저의 요청으로 받은 
 *	  request객체 메모리에 address-서울시성북구 쌍으로 묶어서 바인딩 함
 *
 * 3. 디스패처 방식으로 두번째 서블릿인 SecondServlet1서블릿을 재요청시...
 *	  기존의 request메모리를 두번째 서블릿으로 전달(공유)해서 사용하게 된다
*/

@WebServlet("/f1")
public class FirstServlet1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//한글처리
		req.setCharacterEncoding("utf-8");
		
		//응답할 데이터 종류 설정
		resp.setContentType("text/html;charset=utf-8");
		
		//웹브라우저에서 요청한 request객체 메모리에 address의 값으로 "서울시 성북구"를 바인딩(저장)한다.
		req.setAttribute("address", "서울시 성북구");
		
		//두번째 서블릿 재요청(포워딩) - 디스패처 방식 이용
		 RequestDispatcher dispatcher = req.getRequestDispatcher("s1");
		 dispatcher.forward(req, resp); //두번째 서블릿 재요청시 request와 response객체 전달
		
	}

	
}
