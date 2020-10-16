package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.sound.RealTimeSequencerProvider;

//첫번째 서블릿에서 조회한 회원정보를 ArrayList배열에 저장한 후
//다시 ~~~ request객체에 ArrayList를 바인딩(저장)한 후
//두번째 서블릿 재요청시 request객체 메모리를 전달하여 공유하여
//뷰 페이지 역할을 하는 두번째 서블릿이 받아, 클라이언트의 웹브라우저로 조회된 회원정보 출력!

//회원 정보를 모두 조회할 요청할 주소값 -> http://localhost:8080/pro07/member

//첫번째 서블릿
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	//순서2. doGet메소드가 호출될때 요청시 새로운 HttpServletRequest객체 메모리를 인자로 전달 받는다.
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 클라이언트가 요청한 가입할 내용들 중, 한글이 존재하면
		// HttpServletRequest객체 메모리에서 꺼내올 때 한글이 깨지므로
		// 인코딩 방식을 UTF-8로 설정
		request.setCharacterEncoding("utf-8");

		// 2. 클라이언트에게 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		// 3. 클라이언트의 웹브라우저로 응답할 출력 스트림 통로 역할을 하는 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();

		// 4. DB작업을 위한 MemberDAO객체 생성
		// 		이유 : DB에 저장된 모든 회원정보들을 조회해서 얻기 위함
		MemberDAO dao = new MemberDAO();
		ArrayList membersList = dao.listMembers();		// 각각의 MemberVO객체들을 최종적으로 ArrayList가변길이 배열에 저장후
										// DB에서 검색한 회원정보들(MemberVO객체들)을 담고 있는 ArrayList배열을 리턴 받는다.
		
		// 5. 조회된 회원정보(ArrayList)를 두번째 서블릿 (뷰페이지)로 공유해서 사용하기 위해..
		//		request내장객체 메모리에 ArrayList배열을 바인딩 시킴
		request.setAttribute("list", membersList);
		
		// 6. 두번째 서블릿(뷰 페이지역할)으로 디스패처 방식으로 포워딩시 기존에 존재했던 request메모리 전달
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewMembers");
		//실제 포워딩
		dispatcher.forward(request, response);
		
		
	}

}
