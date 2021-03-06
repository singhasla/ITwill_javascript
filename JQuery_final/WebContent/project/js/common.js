$(function() {
	
/*
 주제 : Show & Hide 로그인폼 만들기
 
 - 화면상단에 [로그인]버튼을 눌렀을때, 화면 상단 바깥에 숨겨져 있던
 로그인 폼이 위에서 내려와 화면에 나타나도록 만들자.

 -그리고 로그인 폼에 [close(닫기)]버튼을 눌렀을때는 
 폼이 다시 화면 상단 바깥으로 이동되어 숨겨지도록 만들자.
 
 -또한 로그인 폼의 아이디 및 비밀번호 입력 요소에 안내가이드도 만들자
*/ 

	
	//class속성값이 login_warp인 <li>태그의 하위<a>태그(로그인 이미지버튼을 감싸고 있는 태그)를 선택하여
	//클릭했을때..
	$(".login_wrap > a").on("click",function(){
		
		//id속성값이 login_f인 <form>태그 전체를 선택해서..
		//<dl id="util_menu">태그영역을 기준으로
		//<form>요소가 0.5초만에 상단 바깥에서 20px만큼 내려오는 효과를 주자.
		$("#login_f").animate({
			top:"20px"
		},500);
		
		
		//링크된 <a>태그를 클릭했을때 다른 페이지로 요청되는 것을 막아주자.
		return false;
	});
	
	
    //class속성값이 login_close_btn인 [close]버튼 또는 
	//input태그의 alt속성의 값이 '로그인버튼'인 이미지를 클릭했을때..
	$(".login_close_btn, input[alt='로그인버튼']").on("click",function(){
		
		//id속성값이 login_f인 <form>태그 전체를 선택해서
		//<form>태그를 0.5초만에 문서 상단 밖으로 이동되도록 함.
		$("#login_f").animate({
			top:"-200px"
		},500);
		
		return false;
	})
		
	
	//아이디, 비밀번호를 입력할 수 있는 <input>태그들을 선택해서 가져와서
	//선택한 <input> 태그에 포커스가 생성되었을때..
	$("#user_id,#user_pw").focus(function(){
		
		//this		->	포커스가 생성된 입력태그<input>태그를 말함
		//prev()	-> 	이전 태그를 선택할 수 있다.
		//포커스가 생성되어 있는 <input>태그의 이전 <label>태그를 선택
		//left속성값 "-9999px"로 적용 하여
		//레이블영역에 적힌 텍스트(이미지)가 문서 밖으로 이동되도록 함.
		$(this).prev().css("left","-9999px");
		
	});
	
	//아이디, 비밀번호를 입력할 수 있는 <input>태그들을 선택해서
	//선택한 <input> 태그에 포커스가 벗어났을때..(blur이벤트)
	$("input[id='user_id'],input[name='user_pw']").on('blur',function(){
		
		//만약에~ 포커스가 떠난 <input>태그에 입력한 내용이 없다면?
		if( $(this).val() == ""){
			
			//포커스가 떠난 <input>태그 이전 <label>태그에 left속성을 2px로 적용하여
			//안내가이드(아이디 또는 비밀번호 힌트 텍스트)가 나오도록 다시 설정함.
			$(this).prev().css("left","2px");
			
		}
		
	});
	
//-----------------------------------------------------------------------------------------------------

	
//-----------------------------------------------------------------------------------------------------

/*
 주제 :  ZOOM 버튼 만들기
 [+]를 클릭하면 화면이 확대 되고,
 [-]를 클릭하면 화면이 축소 됩니다.
 그리고 [100]을 눌렀을때는 원래 100%사이즈로 되돌리도록 만들기
 
 참고 : zoom 버튼을 클릭 했을때 화면 확대/축소를 적용하기 위해서는 
 		CSS속성중에 zoom속성과 transform의 scale속성 사용법을 잘 알고 있어야 합니다.
 		
 		이때 웹브라우저 마자 CSS속성이 모두 정상적으로 작동하지 않으므로 구분해서 사용 해야 하는데,
 		zoom속성의 경우에는 현재 파이어폭스를 제외한 모든 브라우저에서 지원하고 있습니다.
 		
 		그리고 transform의 scale속성은 현재 IE8 이하를 제외한 모든 브라우저에서 지원 하고 있습니다.
 
 		1.제이쿼리의 zoom속성 사용법
 			문법-> $(요소선택).css("zoom", "확대비율%");
 			
 			예-> $("body").css("zoom","200%");
 			 	//<body>영역의 모든 태그요소가 2배로 확대 됩니다.
 		
 		2.제이쿼리의 transform의 scale속성 사용법	 	
 			문법-> $(요소선택).css("transform","scale(확대비율)")
 			
 			예-> $("body").css("transform","scale(2)")
 				//화면에서 <body>영역의 모든 태그요소가 2배로 확대 됩니다.
 				
 			참고 :  	 transform의 scale속성을 이용해 보면 확대 기준점이 가운데로 지정되어 있어
 			         사방으로 퍼져 확대됨.
 			         확대 기준점을 바꾸고 싶으면 CSS에 transform-origin속성을 사용 하면됨.
 		
 		3.제이쿼리의 transform-origin속성 사용법
 			문법-> $(요소선택).css("transform-origin","x축 위칫값 y축 위칫값")
 			
 			예-> $("body").css("transform-origin","0 0");
 				//<body>영역의 확대 기준점이 "0 0" 으로 설정되어  
 				//(0,0)위치에서 x축 과 y축 방향으로 확대됨
 
 */  
  
  
  /*zoom 버튼*/
 
	//확대 비율 초기값 저장
	var base = 100;
	//body 요소 전체를 선택해서 지정
	var mybody = $("body");
	
	//id속성의 값이 zoom인 ul 태그 내부의 모든 a태그를 선택해서 가져와서
	//a태그를 감싸고 있는 [+] [100] [-] 이미지 중 하나를 클릭했을때...
	$("#zoom a").on("click",function(){
		
		//클릭한 <a>요소의 인덱스위치값을 반환하여 저장
		var zNum = $("#zoom a").index(this);
		
		//만약에 <a>요소의 인덱스 위치값이 0이라면( 확대[+]이미지를 감싸고 있는 <a>요소를 클릭했다면...)
		if(zNum == 0){
			
			//base 변수의 값을 10증가 되게 하고
			//클릭할 때마다 누적시키기
			base += 10;
			
		//만약에 <a>요소의 인덱스 위치값이 1이라면( [100]이미지를 감싸고 있는 <a>요소를 클릭했다면...)
		}else if(zNum == 1){	
			
			//base변수의 값을 100으로 저장
			base = 100;
			
		//만약에 <a>요소의 인덱스 위치값이 2이라면( [-]이미지를 감싸고 있는 <a>요소를 클릭했다면...)	
		}else if(zNum == 2){
			
			//base변수의 값을 10감소 되게 함
			base -= 10;
		}
		
		
		
		//<body>요소 전체를 선택해서 <--- mybody변수에 저장된 요소
		mybody
		.css("overflow-x", "auto")	//overflow-x속성을 적용하면 IE8이하에서 정상적으로 작동되도록 함.
		
		//<body>영역의 확대 기준점이 0 0으로 설정되어 (0,0)위치에서 x축과 y축 방향을 확대되도록 설정
		.css("transform-origin", "0 0")
		
		//화면에서 <body>영역의 모든 요소들이 base/100의 값만큼 base변수값 배로 확대되게 하기
		.css("transform","scale(" + base / 100 + ")")
		
		//<body>영역 내부의 모든 요소들이 base의 값만큼 확대 / 축소 되게 하기
		.css("zoom", base + "%");
		
		//클릭한 <a>의 이동을 막습니다.
		return false;
		
	});
 
//-----------------------------------------------------------------------------------------------------

  
  /*
   	주제 : 인쇄 버튼 만들기 
   	인쇄 버튼 웹사이트에서 인쇄 버튼을 방문자가 눌렀을대.. 인쇄창을 뛰우는 방법을 알아 봅시다.
   */
  /*인쇄 버튼*/

	  $(".print_btn").click(function(){
		  
		  window.print();
		  
	  })

//-----------------------------------------------------------------------------------------------------

  /*
   주제 : 검색 창 안내 가이드 만들기
   검색 입력 요소에 마우스로 클릭해 포커스가 이동되면 
   안내 가이드 변경 이미지가 사라지고,
   포커스가 다른 요소로 이동되었을때 검색 입력 요소에 아무 내용이 없으면
   다시 안내가이드 배경 이미지가 다시 나타나게 하기 
   */
  /*검색 창 안내 가이드*/

	  //검색창 입력 태그 요소에 포커스가 생성되었을때...
	  $("#keyword").on("focus", function(){
		  
		  //focus가 생성된 <input>검색어 입력창을 선택해서
		  //검색창의 스타일속성 background-position속성의 값을 이용하여
		  //검색어를 입력하세요 <--라는 이미지를 <input>요소 바깥으로 이동되도록 하자.
		  //방법 : 포커스가 생성된 입력요소<input>에 배경위치(position)속성이 위로 -500px만큼 이동되게 하자.
		  $(this).css("background-position", "0 -500px");
		  
	  })
	  
	  // .체이닝기법을 이용하여 이어서 이벤트를 등록해주는데..
	  // 검색창 입력요소에 포커스가 다른 곳으로 이동되었을때...(포커스가 <input>요소에서 벗어났을때)
	  .on("blur",function(){
		 
		  //<input>요소에 입력된 값이 없으면 배경의 위치속성이 0px만큼 이동되도록하여
		  //다시 안내가이드 이미지가 보여지도록 하자.
		  if( $(this).val() == "" ){
			  $(this).css("background-position", "0 0");  
		  }
			  
	  });
	  
//-----------------------------------------------------------------------------------------------------
  
 /*
  주제 : GNB(글로벌 네비게이션 바) 메뉴 만들기
  사이트의 모든 페이지에 노출되는 메뉴를 가리키며,
  보통 사이트 상단에 위치합니다.
  GNB상위 메뉴에 마우스가 올라갔을때, 해당 상위 메뉴 이미지는 활성화(컬러)된 이미지로 바뀌게 됨.
  이어서 마우스를 다른 상위메뉴로 이동하면,
  앞서 활성화된 상위 메뉴 이미지는 다시 비활성화(무채도)된 이미지로 바뀌고,
  현재 마우스가 올라간 상위 메뉴의 이미지는 활성화된 이미지로 바뀌도록 만들자 
  */ 
  /*GNB 메뉴*/

	  //초기 변수 선언
	  var beforeEl;
	  
	  //id속성값이 gnb인 <ul>태그 내부의 <li>태그 내부의 <a>를 선택하여
	  //<a>요소에 마우스포인터를 올리거나 포커스가 생성되었을때..
	  //요약 : 상위 메뉴에 마우스를 올리거나 Tab키를 눌러서 포커스가 생성되었을때...
	  $("#gnb>li>a").on("mouseover focus",function(){
		
		  //마우스 포인터가 올라가거나 포커스가 생성된 이벤트가 발생된) <a>요소를 선택해 beforeEl변수에 저장
		  beforeEl = $(this);
		  
		  //만약 변수 beforeEl에 앞서 마우스가 올라간 <a>요소가 저장되어 있다면
		  if(beforeEl){
			  
			//beforEl변수에 저장된 <a>요소의 자식 요소인 <img>요소를 선택해
			//<img>요소의 src속성값 중에.. "over.gif"를 "out.gif"로 치환하여 다시 <img>의 src속성값으로 설정
			  beforeEl.children("img")
			  .attr("src",beforeEl.children("img").attr("src").replace("over.gif","out.gif"));
		  }
		  
		  
		  //상위 메뉴에 마우스를 올리거나 포커스가 생성되었을때..
		  //서브메뉴 <ul>태그 중.. 펼쳐져 보이는 메뉴가 있으면? slideUP()효과 메소드를 적용하여 위로 접으면서 숨김
		  $("#gnb ul:visible").slideUp("fast");
		  
		  
		  //상위 메뉴에 마우스를 올리거나 포커스가 생성되었을때..
		  //마우스가 오버된 <a>태그의 하위요소<img>태그에 attr("속성명", "새 값") 속성메소드를 사용해
		  //비활성화(무채도)버튼 이미지를 활성화(컬러)이미지로 바꿉니다.
		  $("img", this).attr("src", $("img",this).attr("src").replace("out.gif","over.gif"));
		  
		  //$(this)로 마우스가 오버된 <a>태그를 선택하고 next()메소드를 이용하여 해당 서브메뉴를 구합니다.
		  //그리고 slideDown()메소드를 이용하여 서브메뉴가 아래로 노출시킵니다.
		  //요약 : 상위 메뉴에 마우스를 올리거나 포커스가 생성되었을때...
		  //		이벤트가 발생한 <a>태그 다음에 나오는 서브메뉴인 <ul>태그가 아래로 펼쳐지며 노출되게 하기
		  $(this).next().stop().slideDown("normal");
		  
		  
	  });
	  
	  

	  //id속성값이 gnb인 <ul>태그의 경계범위 내에서 마우스가 완전히 벗어났을때.. 이벤트발생
	  $("#gnb>li").on("mouseleave", function(){
		
		  //만일 펼쳐져잇는 서브메뉴가 있으면 slideUp()효과 메소드를적용하여 위로 접으면서 숨깁니다.
		  $("#gnb ul:visible").slideUp("fast");
		  
		  //만약 beforeEl변수에 앞서 마우스를 올린 상위 메뉴의 <a>태그가 저장되어 있으면?
		  if(beforeEl){
			  
			  //attr("속성", "새 값")속성 메소드를 이용하여 앞서 활성화된 버튼이미지를 비활성화된 이미지로
			  //src속성을 바꿉니다.
			  beforeEl.children("img")
			  .attr("src", beforeEl.children("img").attr("src").replace("over.gif","out.gif"));
		  }
		  
	  });
	  
	  
//-----------------------------------------------------------------------------------------------------
  /*
   주제: 슬라이드 전체 메뉴 만들기
   - 전체 메뉴를 클릭 했을 때 전체메뉴가 slide효과로 펼쳐지고
     전체 메뉴 버튼 이미지도 바뀌도록 만들어 보자
   - [전체메뉴]버튼을 클릭 했을때 전체 메뉴가 아래로 펼쳐지며
     [CLOSE]버튼을 클릭했을때는 가시 전체메뉴가 위로 접히면서 사라지게 해보자.  
   */
  
  /*전체메뉴*/

	  //id속성값이 total_btn인 <p>태그 내부의 자식요소 <a>를 선택해서 클릭했을때..
	  $("#total_btn>a").on("click",function(){
		  
		  //id속성값이 total_menu인 <div>요소영역(서브메뉴를 나타내는 전체영역)을
		  //아래로 펼쳐지며 노출되게 하기!
		  //아래로 펼쳐져 있으면 다시 위로 접으며 숨기게 하기!
		  $("#total_menu").slideToggle("fast");
		  
 
		  if($("img",this).attr("src") == "images/allmenu_btn_out.gif"){
			  
			  //[전체메뉴]▼ 이미지일때.. [전체메뉴]▲이미지로 바뀌게 하기
			  $("img",this)
			  .attr("src" , $("img",this).attr("src").replace("out.gif","over.gif") );
		  }else {
			  //[전체메뉴]▲ 이미지일때.. [전체메뉴]▼이미지로 바뀌게 하기
			  $("img",this)
			  .attr("src" , $("img",this).attr("src").replace("over.gif","out.gif") );
			  
		  }
		  
		  //<a>태그를 클릭했을 때 이동되는것을 막아 버리자
		  return false;
		  
	  });
	  
	  
	  /*전체 메뉴 닫기 버튼*/
	  
	  //id속성값이  total_close인  <p>태그 내부의  <a>태그를 클릭했을때..[CLOSE] 영역을 클릭했을때..
	  //id속성값이  totla_menu인  <div id="total_menu">(하위 메뉴 영역)이 
	  //위로 접히면서 숨겨지게 하기  효과속도는 "fast"
	  $("#total_close a").on("click",function(){
		  $("#total_menu").slideUp("fast");
		  $("#total_btn a img").attr("src","images/allmenu_btn_out.gif");
		  
        //<a>태그의 전송(이동)을 차단 
		  return false;
	  });
	  
//-----------------------------------------------------------------------------------------------------

  /*
    주제 :  현재 날짜 표기 하기
    사이트 헤더 영역에  현재 연도, 월, 일을 표기 합니다.
  Date객체를 사용하여 오늘의 날짜 정보를 구해 올것입니다.
  */
  
	  clock();
	  
	  /*날짜 표기*/  
	  function clock() {
		    var date = new Date();
		    // date Object를 받아오고 

		    var year = date.getFullYear();
		    // 연도를 받아옵니다
		    
		    var month = date.getMonth();
		    // 달을 받아옵니다 
		    
		    var clockDate = date.getDate();
		    // 몇일인지 받아옵니다 
		   
		    var day = date.getDay();
		    // 요일을 받아옵니다. 
		   
		    var week = ['일', '월', '화', '수', '목', '금', '토'];
		    // 요일은 숫자형태로 리턴되기때문에 미리 배열을 만듭니다. 
		    
		    var hours = date.getHours();
		    // 시간을 받아오고 
		    
		    var minutes = date.getMinutes();
		    // 분도 받아옵니다.
		    var min = dasi(minutes);
		    
		    
		    var seconds = date.getSeconds();
		    // 초까지 받아온후 
		    var sec = dasi(seconds);
		    

		  $("#date_wrap .year").text(year);
	      $("#date_wrap .month").text(month+1);
	      $("#date_wrap .date").text(clockDate);
	      $("#date_wrap .hour").text(hours);
	      $("#date_wrap .minute").text(min);
	      $("#date_wrap .second").text(sec);
	      
	      
	      setInterval(clock , 1000);		//	일정한 시간간격으로 실행문을 실행시키는 함수
		  // <-> clearInterval(intervalID)	//	setInterval()함수를 제거(정지)
	      
	  }
	  
	  	  	  
	  
	  //dasi함수 : 분, 초 정보를 매개변수로 전달받아 만약 그 수가 10보다 작다면 글자앞에 0을 추가시키는 함수
		function dasi(i) {
			
			if(i < 10){
				i = "0" + i;	//한자리 숫자를 두자리숫자로 바꾸기. ex) 1 => 01 ,		9 => 09
			}
			
			return i;
		}

		
//-----------------------------------------------------------------------------------------------------

  /*
   주제 : 관련 사이트 이동 선택 상자 만들기
   -푸터 영역에는 관련 사이트 이동 선택 상자가 있습니다.
  	사이트에 방문객체 관련 사이트를 선택한 후 [이동]버튼을 클릭하였을 때 새창으로 선택한 사이트가 열리도록 할것입니다.
  	여기서는 [이동] 버튼을 클릭했을때 <form>태그에서 전송이 일어나므로 submit 이벤트를 적용 하겠습니다.
  	이벤트가 발생 했을 때 이벤트 핸들러에 window.open(사이트 경로)메서드를 이용해 새창 으로 지정한 사이트가 열리도록 만들것입니다.  
  */
  
   /*관련 사이트 이동*/
		
		//요약 : [이동] 이미지 버튼(전송버튼)을 클릭했을때..
		$("form[name=rel_f]").on("submit",function() {
			
			//전송이 일어난 <form>요소의 하위 <select>태그 선택
			var url = $("select", this).val();
			
			//새로운 브라우저창을 띄워 변수 url에 저장된 주소값을 이용해 사이트로 이동하여 나타내자
			window.open(url);
			
			//[이동] <img>전송버튼을 눌렀을때.. <form>태그의 action속성의 서버페이지로 전송을 차단
			return false;
		});
  

 //-----------------------------------------------------------------------------------------------------

  /*옆쪽 퀵 메뉴*/

		//index.html 처음 실행 했을 때 문서 상단에서 퀵 메뉴의 여백 거리값을 구하기 위해
		//css파일에서 #quick_menu의 position속성의 top값을 구함
		//이때 픽셀단위가 붙게 되는데.. 정수만 남기기 위해 parseInt()메소드를 사용하여 호출
		//요약 : 기본 문서 상단에서 퀵메뉴가 이동한 거리값을 구한다
		var defaultTop = parseInt($("#quick_menu").css("top"));	// 100px --> parseInt("100px"); --> 100
		

		//브라우저(window)에 스크롤 막대바가 이동될 때마다
		$(window).on("scroll", function() {
			
			//스크롤 막대바의 이동 높이값을 얻어 scv변수에 저장
			var scv = $(window).scrollTop();
			
			//스크롤 막대바가 이동될 때마다 <div id="quick_menu">태그에 animate()메소드를 적용시키자
			//이때 scv변수값(스크롤바의 이동높이값)을 top이라는 속성에 적용하여..
			//1초만에 스크롤바의 이동거리만큼 <div>태그가 이동되도록 함.
			//그리고 index.html을 처음 실행 했을때 문서상단에서 퀵 메뉴의 여백 거리값을 이용해서
			//defaultTop변수의 값을 더하여 상단에서 여백거리만큼 떨어져서 이동되도록 함.
			//이때 방문자가 스크롤막대를 위 아래로 계속해서 움직이면 animate()메소드가 여러번 작동되어
			//큐에 animate()메소드가 많이 쌓이게 되는데,
			//이를 방지하기 위해 stop()메소드를 적용하여 앞서 적용된 animate()메소드는 정지 되도록 함.
			$("#quick_menu").stop().animate({top: defaultTop + scv + "px"}, 1000);
			
		});
		

});