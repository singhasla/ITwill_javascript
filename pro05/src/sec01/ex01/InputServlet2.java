package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 요청한 값들 중에 한글이 존재하면 request메모리에서 꺼내올 때 한글이 깨지므로,
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//전송되어 오는 데이터가 많은 경우에는 getParameterNames()메소드 호출하여
		//<input>태그의 name속성값들을 모두 Enumeration 인터페이스를 구현한 자식배열객체에 담아 리턴받는다.
		Enumeration enu = request.getParameterNames();
		
		while(enu.hasMoreElements()){	//Enumeration배열에 값이 저장되어 있는 동안 반복
			String name = (String)enu.nextElement();
			String[] values = request.getParameterValues(name);
			
			for(String str:values){
				System.out.println("name속성값 : " + name + " , value : " + str);
			}
		}
	}

}
