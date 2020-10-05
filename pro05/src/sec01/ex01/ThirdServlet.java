package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//순서1. 클라이언트(유저)가 웹브라우저를 이용해서 아래의 ThirdServlet 서버페이지를 요청한다.
//			요청할 주소 : http://192.168.3.14:8080/pro05/third

//순서2. 톰캣(웹 애플리케이션 서버)은 위 요청주소에 해당하는 ThirdServlet 서블릿페이지를 찾아서 매칭시킨 후
//			ThirdServlet을 실행시킨다.

//순서3. 클라이언트는 웹 브라우저 주소창에 주소를 입력해서 요청(GET요청방식)하였으니
//			ThirdServlet클래스 내부의 doGet()메소드가 자동 호출되면서 
//			한 사람의 요청당 하나의 HttpServletRequest 내장객체 메모리 생성된 후
//			doGet()메소드의 매개변수로 그 객체를 전달 받아 사용하게 된다.

//순서4. doGet메소드 내부에서 요청받은 HttpServletRequest 객체 메모리가 생성되면서 요청한 정보를 전달 받을 수 있다.

@WebServlet("/third")
public class ThirdServlet extends HttpServlet {

	//클라이언트가 GET방식으로 요청하면 호출되는 콜백 메소드로
	//요청 하는 값이 있으면 새로운 HttpServletRequest객체 메모리가 생성되면서 요청한 정보를 전달 받을 수 있다.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		System.out.println(path);
	}

}
