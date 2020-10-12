package sec01.ex02;

import java.sql.Date;

/*	VO(Value Object)역할을 하는 클래스
 * 
 * 	- VO역할이란?
 * 
 * 	1. DB의 테이블에서 조회한 레코드의 컬럼 값들을 아래의 MemberVO클래스의 각 변수에 저장하거나
 *  2. 회원 가입 창에서 입력한 회원 정보들을 DB에 추가시키기 전에 임시로 각 변수에 저장시켜
 *  	new MemberVO객체를 생성하여 DB에 한번에 추가시켜 주기 위해 제공해주는 클래스
 */

/*	VO역할을 하는 클래스 정의 방법
 * 		테이블에서 조회한 레코드의 컬럼값을 변수에 저장해야 하므로,
 * 		컬럼 이름과 동일한 자료형과 이름으로 변수를 선언하고 getter/setter메소드를 각각 만들어준다. 
 */

/* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *	역할 요약:
 *		1. DB로부터 조회한 회원정보 한 명의 정보를 임시로 저장할 클래스
 * 		2. 가입할 한 사람의 회원정보를 임시로 저장해 놓을 클래스이며
 * 			DB에 가입한 insert시 new memberDO(); 객체 단위로 추가시킬 수 있다.
 */

public class MemberVO {

	// 변수
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joindate;
	

	public MemberVO(){	//생성자
		System.out.println("MembeVO 생성자 호출");
	}
	
	// getter, setter
	// getter 역할을 하는 메소드?
	//		private으로 선언된 위 변수의 값을 반환할 목적으로 사용됨
	
	public String getId() {			//규칙>		public 리턴타입 'get변수명첫글자대문자'(){
		return id;					//				return '변수'; 
	}								//			}
	public void setId(String id) {	//			public void 'set변수명첫글자대문자'(변수타입 매개변수){
		this.id = id;				//				this.변수 = '매개변수';
	}								//			}
	
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
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
}
