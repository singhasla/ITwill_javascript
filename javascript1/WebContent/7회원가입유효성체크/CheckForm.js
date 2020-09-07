//Register.html페이지의 값들이 유효한지 체크 함수
function CheckForm() {
	//이름을 입력할 수 있는 <input>태그 선택해서 가져와서 uName변수에 저장
	var uName =  document.getElementById("uName");
	//ID를 입력할 수 있는 <input>태그 선택해서 가져와서 uID변수에 저장
	var uID = document.getElementById("uID");
	//비밀번호를 입력할 수 있는 <input>태그 선택해서 가져와서 uPWD변수에 저장
	var uPWD = document.getElementById("uPWD");
	//비밀번호확인을 입력할 수 있는 <input>태그 선택해서 가져와서 uPWD_Confirm변수에 저장
	var uPWD_Confirm = document.getElementById("uPWD_Confirm");
	//직업을 선택할 수 있는 select태그를 선택해서 가져와서 work변수에 저장
	var work = document.getElementById("work");
	//가입 동기 입력란 <textarea>태그를 선택해서 가져와서 motivation변수에 저장
	var motivation = document.getElementsByName("motivation");
	//성별 라디오 버튼을 선택해서 가져와서 sex변수에 저장
	//<input type="radio" name="sex" id="s1" value="male"/>, <input type="radio" name="sex" id="s2" value="female"/>
	var sex = document.getElementsByName("sex");
	//취미 체크박스들을 선택해서 hobby변수에 저장
	var hobby = document.getElementsByName("hobby");
	
	console.debug(hobby);
	return false;
	/*
	 	document.getElementsByTagName(태그명) 	-> 해당 태그명의 요소를 모두 선택해서 배열에 저장되어 가져옴
	 	
	 	document.getElementById					-> Id속성값에 해당하는 요소 하나를 선택해서 가져옴
	 	
	 	document.getElementByClassName(class속성의 값)	->	class속성값에 해당하는 요소들을 모두 선택해서 가져옴
	 	
	 	document.getElementByName				-> name 속성값을 가지는 요소를 모두 선택해서 가져옴
	 */

}


//아이디 체크 메소드 만들기
function CheckID() {
	
	//새로운 창을 띄워주자
	window.open("CheckID.html", "아이디중복확인", "width=500 height=300");
}