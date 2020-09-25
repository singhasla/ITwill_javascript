package Java;

class Parent2{	// = 슈퍼, 조상, 부모클래스
	public void parentPrn(){
		System.out.println("슈퍼클래스 : parentPrn메소드");
	}
}

class Child2 extends Parent2{
	public void childPrn(){
		System.out.println("서브클래스 : childPrn메소드");
	}
}

public class Ex4 {

	public static void main(String[] args) {

		/* 업캐스팅 및 다운캐스팅의 예 */
		
			//업캐스팅 : 부모클래스타입 참조변수에 자식 객체 저장
			Parent2 p2 = new Child2();
			
			p2.parentPrn();		//Parent2 클래스의 멤버는 당연히 사용가능		
			//p2.childPrn();	//참조변수 p2는 Child2 자식객체의 메소드를 가지고 있지 않기 때문에 호출할 수 없다.
			
			/*		
			 * Child2객체의 childPrn()메소드를 호출할 수 있는 유일한 방법은?
			 * Child2클래스(자식클래스) 타입으로 다운캐스팅(형변환)해서 메소드를 호출해야 함
			 */
		
		
			//다운캐스팅
			//순서1. 자식클래스 타입의 참조변수 선언
			Child2 c2;
			//순서2. 자식클래스 타입으로 선언한 참조변수 c2에 부모클래스의 참조변수 안의 참조주소값을 대입함
			//		 이때 강제로 자식클래스 타입으로 형변환 시켜야 함.
			c2 = (Child2)p2;
			
			c2.parentPrn();		//호출가능
			c2.childPrn();		//호출가능
			
			// ---------------------------------------------------------------------------------------------------------------//
			
		/* 다운캐스팅의 잘못된 예 */
			//부모 객체를 자식 클래스형의 참조변수로 접근하려고 시도한 잘못된 예
			
			//부모클래스형의 참조변수로 부모객체 생성해서 저장
			Parent2 p3 = new Parent2();
			
			//자식클래스형으로 참조변수 선언 후
			Child2 c3;
			
			//자식클래스형 참조변수에 부모클래스형의 참조변수 안의 객체의 주소값 대입??
			//자식클래스형으로 강제 형변환 시켜 다운캐스팅함
			c3 = (Child2)p3;
			
			c3.parentPrn();
			c3.childPrn();
	}

}
