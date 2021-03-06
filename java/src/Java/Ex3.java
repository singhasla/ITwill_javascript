package Java;

//다운캐스팅?

class Parent1{	// = 슈퍼, 조상, 부모클래스
	public void parentPrn(){
		System.out.println("슈퍼클래스 : parentPrn메소드");
	}
}

class Child1 extends Parent1{
	public void childPrn(){
		System.out.println("서브클래스 : childPrn메소드");
	}
}

public class Ex3 {

	public static void main(String[] args) {
		
		/* 참조(레퍼런스)자료형의 형변환 */
		
			//부모클래스로부터 객체 생성
			Parent1 p = new Parent1();
			
			/*
			 * 2) 다운캐스팅 : 자식클래스타입의 참조변수에 부모클래스의 참조변수의 객체의 주소값을 대입할 때..
			 * 					자식클래스형으로 객체를 형변환 시키는 것(다운캐스팅)
			 */
			
			//자식클래스형 참조변수 선언
			Child1 c;
			
			//자식클래스형 참조변수에 부모클래스의 참조변수의 객체의 주소값을 대입할 때
			//자식클래스형으로 형변환 시키는 것(다운캐스팅)
			c = (Child1)p;	//다운캐스팅(강제로 형변환)
			//c = p;		//다운캐스팅(자바컴파일러에 의해 자동형변환 불가능!)
			
			/*
			 *	업캐스팅에서 자동형변환이 가능했지만..
			 *	다운캐스팅에서 자동형변환이 불가능한 이유?
			 *		- 생성한 것은 Parent1 객체인데, 생성된 영역은 Parent1객체 영역뿐이지
			 *			Child1객체에 대한 영역은 존재하지 않는다.
			 *			존재하지 않는 멤버를 참조할 수 없다.
			 */	
			
				
			
		/*
		 * 다운캐스팅이란?
		 * 		- 자식클래스형으로 형변환 시키는 것(다운캐스팅)
		 * 
		 * 		- 다운캐스팅 이후 자식클래스형의 참조변수로 부모객체를 참조할 때 참조가능한 영역이 확대된다.
		 * 
		 * 		- 다운캐스팅은? 컴파일러에 의해 자동 형변환 되지 않는다.
		 * 			(왜냐하면 존재하지 않는 영역을 참조할 위험이 있기 때문에...)
		 */
		
	}

}
