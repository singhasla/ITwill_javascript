package Java;

//메소드 오버라이딩 복습

//도형클래스(부모클래스)
class DObject{
	
	//클래스형의 참조변수 선언
	public DObject next;
	
	//생성자
	public DObject() {
		next = null;
	}
	
	//그리는 기능을 출력하는 메소드
	public void draw(){
		System.out.println("DObject draw");
	}
}


//도형(DObject)를 상속받아 선을 그리기 위한 Line자식클래스 만들기
class Line extends DObject{

	//메소드 오버라이딩 : 부모클래스의 메소드의 구현부를 재정의
	@Override
	public void draw() {	//선언부
		
		//구현부 재정의
		System.out.println("Line");
		
		//참고 : 부모클래스에 있는 draw()메소드를 오버라이딩 하면..
		//			Line클래스로 생성된 객체는 부모클래스의 메소드가 은닉되어 상속 받지 못하게 된다.
	}

}

//도형 클래스를 상속받아 사각형을 그리기 위한 Rect자식클래스 만들기
class Rect extends DObject{

	//메소드 오버라이딩
	@Override
	public void draw() {

		//구현부 재정의
		System.out.println("Rect");
	}
	
}

//도형 클래스를 상속받아 원을 그리기 위한 Circle자식클래스 만들기
class Circle extends DObject{

	//메소드 오버라이딩
	@Override
	public void draw() {
		
		//구현부 재정의
		System.out.println("Circle");
	}
	
}

public class Ex6 {

	public static void main(String[] args) {

		//오버라이딩된 메소드 호출 해보기
		Line a = new Line();
		
		a.draw();
		
		//업캐스팅 : 부모클래스 타입의 참조변수로 자식객체 참조하는 것
		//			=부모클래스 타입의 참조변수에 자식객체를 저장하는 것
		DObject p = new Line();
		
		p.draw();
		//부모클래스(Dobject)형 참조변수 p로 draw()메소드 호출시..
		//참조변수 p는 Dobject클래스 타입이므로
		/* 순서:
		 * 		1. 먼저 은닉된 부모클래스의 draw()메소드를 찾고
		 * 		2. 부모의 draw()와 같은 이름의 메소드가 자식클래스에 있는지 찾아서
		 * 			동적으로 바인딩되어 오버라이딩된 자식의 draw()메소드가 호출된다.  
		 */
		
	}

}
