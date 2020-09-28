package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1. 사용자 정의 서블릿 클래스 만들기
//	- 톰캣 서버 쪽에서 실행되면서 기능을 수행하는 클래스
//	- 클라이언트가 웹브라우저를 이용해 요청시 기능을 수행하는 클래스
//	- HttpServelet클래스를 상속받는 형태로 사용자 정의 서블릿 클래스를 생성해야 한다. 
public class FirstServlet extends HttpServlet{

	//메소드 오버라이딩 단축키 : alt + shift + s -> v

	/*
	 *	init메소드는 서블릿클래스 요청시 맨 처음에 한번만 호출되는 메소드로
	 *	서블릿 생성시 초기화 작업을 주로 수행한다
	 *	용도 : 실행 초기에 서블릿 기능 수행과 관련된 기능을 설정하는 용도로 사용된다.
	 */
	@Override
	public void init() throws ServletException {	//생략 가능! 생략해도 호출된다.
		System.out.println("init 메소드 호출");
		//super.init();
	}

	/*
	 * 	클라이언트가 GET방식으로 요청하면 호출되는 콜백 메소드로서,
	 *	요청에 관한 응답할 로직을 처리할 메소드 영역이다.(반드시 작성해 주어야 하는 메소드)
	 *	용도 : 웹브라우저를 이용해 요청한 정보를 처리하는 메소드
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메소드 호출");
		//super.doGet(req, resp);
	}

	/*	
	 *	서블릿이 기능을 수행하고 톰캣 메모리에서 소멸될 때 호출되는 콜백 메소드.
	 *	서블릿의 마무리 작업을 주로 수행한다.
	 */
	@Override
	public void destroy() {		//생략 가능! 생략해도 호출된다.
		//super.destroy();
	}

	
	
}
