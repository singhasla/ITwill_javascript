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

//두번째 서블릿
//	-> 첫번째 서블릿에서 조회된 회원정보들을 모두 ArrayList에 저장 후
//		ArrayList배열을 request바인딩 한 후
//		두번째 서블릿을 재요청을 받을 때 request객체를 공유받아 사용
@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청받은 request메모리의 데이터 한글처리 UTF-8설정
		request.setCharacterEncoding("utf-8");

		// 1-1. 클라이언트에게 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		// 2. 클라이언트의 웹브라우저로 응답할 출력 스트림 통로 역할을 하는 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();
		
		// 3. MemberServlet서블릿(첫번째 서블릿)으로부터 바인딩해서 넘어온 request객체 영역내부에 저장된
		//		DB로부터 검색한 회원정보들(ArrayList에 저장된 MemberVO객체들)을 꺼내어 가져옴
		ArrayList membersList = (ArrayList)request.getAttribute("list");
		
		// 4. ArrayList배열에 저장되어 있는 조회한 회원정보들(MemberVO)을 얻어 반복해서 출력
		out.print("<html><body>");
		out.print("<table border=1>");
		out.print("<tr align=center bgcolor=lightgreen>");
		out.print("<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th><th>삭제</th>");
		out.print("</tr>");

		// ArrayList배열 내부에 저장된 MemberVO객체들의 갯수만큼 반복
		for (int i = 0; i < membersList.size(); i++) {
			// 조회한 회원정보들(MemberVO객체들)은 ArrayList가변길이 배열에 저장되어 있으므로
			// ArrayList가변길이 배열 공간에 접근하여 조회한 회원정보들(MemberVO)을 얻는다(get(index위치값))
			Object obj = membersList.get(i);// ArrayList배열의 인덱스 위치를 이용하여
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
			out.print("<td><a href='/pro07/member?command=delMember&id=" + id + "'>삭제</a><td>");
			out.print("</tr>");

		} // for
		out.print("</table>");
		out.print("</body></html>");
	}
}
