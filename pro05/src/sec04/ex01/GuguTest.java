package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/guguTest")
public class GuguTest extends HttpServlet {

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	// 1. 인코딩 방식 지정 UTF-8
    	request.setCharacterEncoding("utf-8");
    	// 2. 응답할 데이터 종류를 text/html;charset=utf-8로 설정
    	response.setContentType("text/html;charset=utf-8");
    	// 3. 응답할 출력 스트림 통로 얻기
    	PrintWriter out = response.getWriter();
    	// 4.입력한 단수를 request메모리에서 꺼내오기
    	int dan = Integer.parseInt(request.getParameter("dan")); 
    	// 5. for문과 <table>태그 이용해서 출력
    	
    	out.print("<html>");
		out.print("<body>");
		out.print("<table border='1' align='center' width='800'>");
			out.print("<th colspan='2' bgcolor='yellow'>" + dan + "단 출력</th>");
			for(int i=1;i<=9;i++){
					out.print("<tr>");
						out.print("<td align='center'>");
						out.print(dan + " * " + i);
						out.print("</td>");
						
						out.print("<td align='center'>");
						out.print(dan * i);
						out.print("</td>");
					out.print("</tr>");
				}
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");
		
    	
	}

}
