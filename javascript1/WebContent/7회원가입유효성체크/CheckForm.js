//성별 선택 여부 확인, 취미 체크 여부 확인 메소드(함수)
function CheckValue(obj) {
	var value = 0;
	
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked == true){
			value = 1;
			break;		//for문을 빠져나감
		}
	}
	
	if(value == 0){		//라디오 버튼 또는 체크박스를 선택하지 않았을 경우
		return false;
	}else{
		return true;
	}
}


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
	
	console.debug(hobby);	//실행후 F12를 눌러 console탭에 로그로 출력 되는 것 확인!
	
	
	//이름을 입력하지 않았을 경우
	if(uName.value == ""){
		
		alert("이름을 입력하시오");
		uName.focus();
		
		return false;	//form요소로 false를 리턴하여 action속성의 서버페이로 전송하는 것을 차단시키기
	}
	
	//이름을 2자 미만 또는 5자 초과로 입력했을 경우
	if(uName.value.length < 2 || uName.value.length > 5){
		alert("이름은 2~5자 이내로 작성하시오");
		
		//이름입력란 <input>요소에 입력된 텍스트가 선택 되도록 하자
		uName.select();
		
		return false;
	}
	
	//아이디를 입력하지 않았을 경우
	if(uID.value == ""){
		
		alert("아이디를 입력하시오");
		
		return false;
	}
	
	
	
	//비밀번호를 입력하지 않았을 경우
	if(uPWD.value == ""){
		
		alert("비밀번호를 입력해야 합니다");
		
		return false;
	}
	
	//입력한 비밀번호가 8자 미만 또는 12자 초과로 입력했을 경우
	if(uPWD.value.length < 8 || uPWD.value.length > 12){
		alert("비밀번호는 8~12자 이내로 작성하시오");
		
		//비밀번호 입력 공간에 입력했던 내용을 지우기 위해 공백으로 설정
		uPWD.value = "";
		
		return false;
	}
	
	//비밀번호 입력공간에 입력한 값과 비밀번호 확인입력공간에 입력한 값이 다를 경우?
	if(uPWD.value != uPWD_Confirm.value){
		//두 비밀번호가 일치하지 않습니다. <--경고메세지창
		alert("두 비밀번호가 일치하지 않습니다");
		
		//비밀번호 확인입력 공간에 입력했던 내용을 지우기 위해 공백으로 설정
		uPWD_Confirm.value = "";
		
		return false;
	}
	
	//직업을 선택하지 않았을 경우
	if(work.options.selectedIndex == 0){
		alert("직업을 선택해주세요");
		return false;
	}
	
	//성별을 선택하지 않았을 경우
	if(!CheckValue(sex)){
		alert("성별을 체크해주세요");
		return false;
	}

	//취미를 선택하지 않았을 경우
	if(!CheckValue(hobby)){
		alert("취미를 체크해주세요");
		return false;
	}
	
	//가입동기를 작성하지 않았을 경우
	if(motivation[0].value == ""){
		alert("가입동기를 작성해주세요");
		return false;
	}
	
	return false;
	
	/* 	->getElementById 빼고 전부 getElement's'
	 
	 	document.getElementById					-> Id속성값에 해당하는 요소 하나를 선택해서 가져옴

	 	document.getElementsByTagName(태그명) 	-> 해당 태그명의 요소를 모두 선택해서 배열에 저장되어 가져옴
	
		document.getElementsByClassName(class속성의 값)	->	class속성값에 해당하는 요소들을 모두 선택해서 가져옴
	 	
	 	document.getElementsByName				-> name 속성값을 가지는 요소를 모두 선택해서 가져옴
	 */

}


//아이디 체크 메소드 만들기
function CheckID() {
	
	//새로운 창을 띄워주자
	window.open("CheckID.html", "아이디중복확인", "width=500 height=300");
}