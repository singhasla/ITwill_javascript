package sec01.ex02;

import java.sql.Date;

//VO(Value Object)역할을 하는 클래스 
//VO역할이란?
//1.DB의 테이블에서 조회한 레코드의 컬럼 값들을  아래의 MemberVO클래스의 각 변수에 저장 하거나 
//2.회원 가입 창에서 입력한 회원 정보들을  DB에 추가 시키기 전에 임시로 각변수에 저장 시켜~~~~
//  new MemberVO객체를 생성하여 DB에 한번에 추카시켜 주기 위해 제공 해주는 클래스 

//VO역할을 하는 클래스 정의 방법
	//테이블에서 조회한 레코드의 컬럼값을 변수에 저장 해야 하므로
	//컬럼 이름과 동일한 자료형과 이름으로 변수를 선언하고  getter/setter메소드를 각각 만들어 준다.

//역할 요약 : 
//1. DB로 부터 조회한 회원정보 한명의 정보를 임시로 저장할 클래스
//2. 가입할 한사람의 회원정보를 임시로 저장 해 놓을 클래스 이며  
//   DB에 INSERT시  new MemberVO();객체 단위로  추가시킬수 있다.
public class MemberVO {

	//변수
	private String	id;  //회원아이디
	private String pwd;  //회원비밀번호
	private String name; //회원이름
	private String email;//회원이메일
	private Date joinDate;//회원가입한 날짜 
	
	//각변수에 관한 setter , getter메소드 추가 ~
	//단축키  alt + shift + s   r
	
	//setter역할을 하는 메소드?
		//privet으로 선언된 위 변수의 값을 새롭게 저장(셋팅)할 목적으로 사용됨
		//메소드를 생성하는 규칙
		//1. public  2. 리턴타입이 없으므로 무조건 void 
		//3. set접두사 입력하고 그뒤에 변수명을 붙이되 변수명의 첫글자를 대문자로 작성
		//4. 매개변수가 존재 해야 한다.
		//5. 위 private으로 선언된 id변수에 매개변수의 값을 저장 시켜야 한다. this.id = id;
	public void setId(String id){
		this.id = id;
	}
		
	//getter역할을 하는 메소드 ?
		//private으로 선언된 위 변수의 값을 반환할 목적으로 사용됨
		//메소드를 생성하는 규칙
		//1.public 2.리턴타입을적어야한다. 
	    //3.get접두사 입력하고 그뒤에 변수명을 붙이되 변수명의 첫글자를 대문자로 작성
		//4.위변수에 저장된 값을 리턴 해야 한다.
	public String getId(){
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
}



