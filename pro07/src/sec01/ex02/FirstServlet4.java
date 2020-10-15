package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first4")
public class FirstServlet4 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//클라이언트의 웹브라우저로 응답(출력)할 데이터 종류 설정
		resp.setContentType("text/html;charset=utf-8");

		//dispatch방법을 이용해 두번째 서블릿인 SecondServlet4으로 재요청(포워딩)
		//해석 : request객체의 메소드 호출시... 재요청할 두번째 서블릿의 매핑 주소이름을 전달하면
		//		 그 매핑주소를 저장한 RequestDispatcher객체를 반환받는다.
		//							   req.getRequestDispatcher("재요청할 두번째 서블릿의 매핑주소");
		RequestDispatcher dispatcher = req.getRequestDispatcher("second4?name=lee");	//get방식
		
		//두번째 서블릿 재요청시, 기존에 존재하던 request와 response객체를 전달함.
		dispatcher.forward(req, resp); 
		
		
		/* 
		 * 중요!!! 디스패쳐 방식의 포워딩 기술은?
		 *		두번째 서버페이즈를 재요청할 때, !!!웹브라우저를 거치지 않고!!! 재요청하기 때문에
		 *		장점1. 첫번째 서버페이지로부터 전달받은 request객체메모리를 두번째 서버페이지에 공유해서 사용할 수 있다.
		 *		장점2. 클라이언트의 응답한 두번째 서버페이지의 출력결과는 화면에 나오지만 웹브라우저 주소창의 주소를 보면 
		 *				처음 요청할 때의 첫번째 서버페이지의 요청주소가 그대로 남아있어	보안에 좋다.
		 */
	}

	
}
