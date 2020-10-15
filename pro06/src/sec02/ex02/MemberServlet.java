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

		// 클라이언트에게 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		// 클라이언트의 웹브라우저로 응답할 출력 스트림 통로 역할을 하는 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();

		// DB작업을 위한 MemberDAO객체 생성
		MemberDAO dao = new MemberDAO();

		// 1. 클라이언트가 요청한 가입할 내용들 중, 한글이 존재하면
		// HttpServletRequest객체 메모리에서 꺼내올 때 한글이 깨지므로
		// 인코딩 방식을 UTF-8로 설
		request.setCharacterEncoding("utf-8");

		// 2. 클라이언트가 요청한(입력한) 회원정보 얻기
		// 요약 : 요청한 값 얻기
		// 2-1. 서블릿으로 요청한 값 중에 회원가입 요청을 알리는 키워드 값 얻기
		String command = request.getParameter("command");

		// 2-2. 서블릿으로 요청이 회원가입 요청일 때
		if (command != null && command.equals("addMember")) {
			// 회원가입창에서 입력한 정보를 HttpServletRequest객체 메모리에서 꺼내오기
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");

			// 회원가입창에서 입력한 정보들을 임시로 저장할 MemberVO객체 생성
			MemberVO vo = new MemberVO();
			// setter메소드들을 호출하여 각 변수에 입력한 회원정보들 저장
			vo.setId(_id);
			vo.setPwd(_pwd);
			vo.setName(_name);
			vo.setEmail(_email);

			// 3. 회원가입창에서 입력한 회원정보들을 DB에 INSERT하기 위해
			// MemberDAO객체의 addMember()메소드 호출시
			// MemberVO객체를 인자로 전달
			dao.addMember(vo);

			// 클라이언트가 요청한 값이 삭제 요청이면(delMember이면)
			// 상세 : command변수에 저장되어 잇는 값이 delMember인 경우 삭제할 회원의 ID를 얻어
			//			SQL문장을 만든다.
		} else if (command.equals("delMember")) {

			//삭제 링크를 클릭햇을 때 회원삭제를 위해 삭제할 회원아이디를 request메모리에서 꺼내오기
			String id  = request.getParameter("id");
			
			//MemberDAO객체의 delMember메소드 호출시 삭제할 회원 id를 전달하여
			//DELETE문장을 완성 후 DB에 저장되어 있는 회원정보를 삭제 시킨다.
			dao.delMember(id);
		}

		// 각각의 MemberVO객체들을 최종적으로 ArrayList가변길이 배열에 저장후
		// DB에서 검색한 회원정보들(MemberVO객체들)을 담고 있는 ArrayList배열을 리턴 받는다.
		ArrayList list = dao.listMembers();

		out.print("<html><body>");
		out.print("<table border=1>");
		out.print("<tr align=center bgcolor=lightgreen>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td>");
		out.print("</tr>");

		// ArrayList배열 내부에 저장된 MemberVO객체들의 갯수만큼 반복
		for (int i = 0; i < list.size(); i++) {
			// 조회한 회원정보들(MemberVO객체들)은 ArrayList가변길이 배열에 저장되어 있으므로
			// ArrayList가변길이 배열 공간에 접근하여 조회한 회원정보들(MemberVO)을 얻는다(get(index위치값))
			Object obj = list.get(i);// ArrayList배열의 인덱스 위치를 이용하여
										// 인덱스 위치에 저장된 MemberVO객체를 꺼내온다.
										// 업캐스팅이 일어남
			// obj.getter?X
			// 다운캐스팅을 시켜줌으로써, MemeberVO객체의 getter메소드들을 호출할수 있게 되었다.
			MemberVO memberVO = (MemberVO) obj;

			String id = memberVO.getId();// 조회한 회원정보중 아이디 얻기
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoindate();

			// 조회한 회원 정보를 바깥 for문과 <tr>태그를 이용해 출력(응답)
			out.print("<tr>");
			out.print("<td>" + id + "</td>");
			out.print("<td>" + pwd + "</td>");
			out.print("<td>" + name + "</td>");
			out.print("<td>" + email + "</td>");
			out.print("<td>" + joinDate + "</td>");
			out.print("<td><a href='/pro06/member3?command=delMember&id=" + id + "'>삭제</a><td>");
			out.print("</tr>");

		} // for
		out.print("</table>");
		out.print("</body></html>");

		out.print("<a href='pro06/memberFrom.html'>새 회원 등록하기</a>");
	}

}
