package Java;

/*
	메소드 오버라이딩시 상속되지 않고 은닉된 부모클래스의 메소드와 같이...
	부모클래스에 정의된 변수와  같은 이름을 가진 변수를 자식클래스에서 선언할수 있는데..
	이러한 경우에도 부모클래스의 변수는 자식클래스에서 사용할수 없게 된다.
*/

// ex) 예제 : 부모클래스의 멤버변수를 상속받아 사용하는 예
class Point2D{	//부모클래스
	protected int x = 10;
	protected int y = 20;
}

class Point3D extends Point2D{	//자식클래스
	
	/* 	추가~
	 * 	부모클래스에 존재하는 멤버변수 x,y를 자식클래스에 다시 한번 정의 한다면..
	 *  Point2D에 정의된 멤버변수(상속받은)는 사용하지 못하고
	 *  Point3D클래스에 정의된 x, y변수의 값만 사용할 수 있다.
	 */	
	protected int x = 40;
	protected int y = 50;
	
	protected int z = 30;
	
	public void print(){
		//x와 y는 상속받아서 사용하는 멤버변수이다.
		System.out.println(x + ","+ y + "," + z);
	}
	
	public void print2(){
		//Point2D의 x, y값
		System.out.println(super.x + ","+ super.y + "," + z);
	}
}

public class Ex8 {

	public static void main(String[] args) {

		Point3D pt = new Point3D();
		
		pt.print();
		
		/* 결론 : 자식클래스의 x값 40, y값 50이 출력되는 것을 볼 수 있다.
		 *			자식클래스에서 같은 이름의 멤버변수 x, y에 의해서 숨겨지는
		 *			부모클래스의 멤버변수 x, y를 은닉변수 또는 쉐도우변수라 한다.
		 *		
		 * 은닉된 부모클래스의 멤버변수의 값을 가져다 써야할 경우?
		 * 		-> 자식클래스 메소드 내부에서 super로 접근해서 사용하면 됨
		 * 			super.x;
		 * 			super.y;
		 */
		pt.print2();	//Point2D의 x, y값
		
	}

}
