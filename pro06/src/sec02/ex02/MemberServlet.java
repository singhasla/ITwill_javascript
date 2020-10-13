package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	순서1. 클라이언트가 웹브라우저 주소창에 http://localhost:8080/pro06/memberForm.html 주소로 입력하여 
 *			회원가입 양식페이지를 웹서버에 요청한다.
 *
 *	순서2. 웹서버는 memberForm.html페이지의 코드를 읽어들여 클라이언트의 웹브라우저 화면에 회원가입 화면을 출력함.
 *
 *	순서3. 클라이언트는 회원가입 화면(memberForm.html)에서 회원가입을 위해 신규회원정보를 입력하고 회원가입 버튼을 클릭해서
 *			모든 내용을 입력했다면 <form>태그를 이용해 MemberServlet서블릿 요청시,,, 
 *			HttpServletRequest객체 메모리에 입력한 회원정보를 저장시켜 전송한다.
 *
 *	순서4. Post전송방식으로 요청받은 MemberServlet서블릿의 doPost메소드가 호출될 때
 * 			매개변수로 HttpServletRequest객체메모리를 얻는다.
 * 		참고) HttpServletRequest객체 메모리에는 입력한 회우너정보들이 저장되어 있다.
 */
@WebServlet("/member3")
public class MemberServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB작업을 위한 MemberDAO객체 생성
		MemberDAO dao = new MemberDAO();
		
		
		//1. 클라이언트가 요청한 가입할 내용들 중, 한글이 존재하면
		//		HttpServletRequest객체 메모리에서 꺼내올 때 한글이 깨지므로
		//		인코딩 방식을 UTF-8fh tjfwjd
		request.setCharacterEncoding("utf-8");
		
		//2. 클라이언트가 요청한(입력한) 회원정보 얻기
		// 	요약 : 요청한 값 얻기
		//	2-1. 서블릿으로 요청한 값 중에 회원가입 요청을 알리는 키워드 값 얻기
		String command = request.getParameter("command");
		
		//	2-2. 서블릿으로 요청이 회원가입 요청일 때
		if(command != null && command.equals("addMember")){
			//회원가입창에서 입력한 정보를 HttpServletRequest객체 메모리에서 꺼내오기
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			//회원가입창에서 입력한 정보들을 임시로 저장할 MemberVO객체 생성
			MemberVO vo = new MemberVO();
			//setter메소드들을 호출하여 각 변수에 입력한 회원정보들 저장
			vo.setId(_id);
			vo.setPwd(_pwd);
			vo.setName(_name);
			vo.setEmail(_email);
			
			//3. 회원가입창에서 입력한 회원정보들을 DB에 INSERT하기 위해
			//		MemberDAO객체의 addMember()메소드 호출시
			//		MemberVO객체를 인자로 전달
			dao.addMember(vo);
			
			
			
			
			
		}
		
	}

}

