package Java;

//메소드 오버라이딩시 상속되지 않고 은닉된 부모클래스의 메소드 호출하는 방법

class Parent7{
	public void ParentPrn(){
		System.out.println("부모클래스의 parentPrn메소드");
	}
}

class Child7 extends Parent7{
	//참고 : 부모클래스에 있는 parentPrn메소드를 오버라이딩 하면...
	//			Child7클래스로부터 생성된 객체는 부모클래스의 메소드가 은닉(보호)되어서 상속받지 못하게 된다.
	
	//부모클래스의 ParertPrn메소드 오버라이딩
	@Override
	public void ParentPrn() {
		
		//13번째 줄 해결방법 : super키워드로 부모클래스의 은닉화된 메소드호출이 가능하다.
		super.ParentPrn();
		
		//구현부 재정의
		System.out.println("자식클래스의 parentPrn메소드");
	}

	public void ChildPrn(){
		System.out.println("자식클래스의 ChildPrn메소드");
	}

	
	
	
}
public class Ex7 {

	public static void main(String[] args) {

		Child7 c = new Child7();
		
		c.ParentPrn();	//부모클래스의 parentPrn메소드와	<- super.은닉된 부모메소드 호출 
						//자식클래스의 parentPrn메소드가 호출된다.
		c.ChildPrn();	//자식클래스의 ChildPrn메소드가 호출된다.
	}

}
