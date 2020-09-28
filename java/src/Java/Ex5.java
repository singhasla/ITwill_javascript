package Java;


//메소드 오버라이딩(메소드 재정의, 메소드 재구현)

	/*	 	
	 * 		메소드 오버로딩이란?
	 * 
	 * 		-상속관계의 부모클래스의 메소드 이름, 매개변수 자료형타입, 리턴타입이 모두 동일하게 하되
	 * 			자식클래스에서 메소드의 구현부를 재작성하는 것.						
	 */

//ex)
class Parent5{	//부모클래스
	
	//부모클래스에 선언된 메소드
	public void parentPrn(){	//메소드 선언부

		//메소드 구현부
		System.out.println("부모클래스의 parentPrn메소드");
	}

}

//Parent5를 부모클래스로 하는 Child5 자식클래스 정의
class Child5 extends Parent5{
	
	//참고 : 부모클래스에 있는 parentPrn()메소드를 오버라이딩 하면..
	//			Child5클래스로 생성된 객체는 부모클래스의 메소드가 은닉되어 상속받지 못하게 된다.
	
	//메소드 오버라이딩
	//단축키 : ALT + Shift + S	-> V 키 눌러 메소드 오버라이딩
	@Override
	public void parentPrn(){	//메소드 선언부

		//메소드 구현부 재작성!
		System.out.println("자식클래스의 parentPrn메소드");
	}
	
	public void childPrn(){
		
		//Child5 자식클래스에서 선언된 메소드
		System.out.println("자식클래스의 childPrn메소드");
	}
}



public class Ex5 {

	public static void main(String[] args){
		
		//자식클래스로부터 객체 생성
		Child5 c = new Child5();
		
		//오버라이딩된 자식클래스 내부의 메소드 호출	-> '동적바인딩' 
		c.parentPrn();
		
		//자식클래스의 자기자신의 객체의 메소드 호출
		c.childPrn();
		
		//------------------------------------------------//
		
		//부모클래스로부터 객체를 생성
		Parent5 p = new Parent5();
		
		//부모클래스(자기자신)의 메소드 호출
		p.parentPrn();
	}
}
