package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 요청한 값들 중에 한글이 존재하면 request메모리에서 꺼내올 때 한글이 깨지므로,
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//클라이언트가 요청한 값 얻기
		//입력한 아이디, 비밀번호 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");

		System.out.println("입력한 아이디: " + user_id);
		System.out.println("입력한 비밀번호: " + user_pw);
		
		//체크박스에 체크한 여러 값을 한꺼번에 전송 받기 위해
		//getParameterValues("input의 name속성값")메소드 호출
		//반환값은 String[]배열로 반환 받는다.
		String[] subject = request.getParameterValues("subject");
		
		//향상된 for문
		for(String str:subject){
			System.out.println("선택한 과목 : " + str);
		}
	}

}
