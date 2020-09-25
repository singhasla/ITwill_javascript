/*
콘텐츠 영역 개발하기
-콘텐츠 영역은 크게 
  비주얼배너, 
  알림판, 
  최근게시물, 
  알림배너, 
  베스트Book, 
  페이스북,
  마케팅, 
  온라인서점
 으로 나뉩니다.
 
-레이아웃은 비주얼 배너가 들어가는 visual영역과
  나머지 주제 소스들이 들어가는 content영역으로 나뉘었음.  
*/

$(function() {
	
//-----------------------------------------------------------
/*
 주제 : 비주얼 배너 터치 슬라이드 만들기
 비주얼 배너 영역은 배너 중 한개만 노출되어 이루어져 있으며,
 [이전/다음]버튼을 누르면 배너가 이동되어 바뀌게 됨.
 스마트폰에서는 손가락으로 터치 했을때도 배너가 바뀔수 있도록 제작 하자
 */

	
	
	
	/*터치 슬라이드 비주얼 영역*/
	   window.mySwipe =$('#mySwipe').Swipe({
	     auto: 3000,  //배너가 3초 간격으로 이동합니다.
	     continuous: true, // 반복해서 롤링됩니다.
	     
	     //배너의 슬라이드 이동이 완료될 때마다 중괄호 내의 실행문이 실행 됩니다.
	     //이떄 index매개변수에는 노출된 배너 이미지를 포함 하는 <li>태그의 인덱스위치값이 할당 됩니다.
	     //그리고 element매개변수 에는 노출된 <li>태그자체가 할당 됩니다. 
	     callback: function(index, element) { //이벤트가 완료되면 실행합니다.
	    
	    	 
	        /*클래스 "active"를 포함하는 불릿 버튼을 비활성화 버튼으로 만들고, 
	        "active" 클래스를 삭제합니다.*/
	    	 
	         //클래스값이 active인 배너 위치 표시 동그라미 이미지<img>태그의 활성화(컬러)버튼 이미지를
	         //attr("속성", 새값)를 이용해 비활성화된 이미지로 바꿉니다.
	         //그리고 removeClass("클래스이름")를 이용해 "active"클래스속성을 삭제 합니다.
	        $(".touch_bullet .active")
	        .attr("src",$(".touch_bullet .active").attr("src").replace("on.png","off.png"))
	        .removeClass("active"); 

	        
	        /*인덱스(index)에 해당하는 불릿 버튼을 활성화된 버튼으로 만들고, 
	        "active" 클래스 를 생성합니다.*/
	        
	        //클래스의 값이 touch_bullet인 하위 <img>태그 중 매개변수 index에 할당된 인덱스 값에 해당하는<img>태그의 
	        //비활성화 블릿 버튼이미지를 선택하여
	        //활성화(컬러)된 버튼이미지로 바꿉니다.
	        //그리고 선택한 이미지 태그에 "active"클래스를 생성 합니다.
	        $(".touch_bullet img")
	        .eq(index)
	        .attr("src",$(".touch_bullet img").eq(index).attr("src").replace("off.png","on.png"))
	        .addClass("active");
	        
	        
	     }
	  }).data('Swipe');
	    

	    
	  /*비주얼 이전, 다음 버튼*/
	   //class = touch_left_btn인 <p class="touch_left_btn">태그의 하위 <a>태그를 클릭 했을떄..
	   //요약 :  < 이전 이미지 버튼을 클릭 했을때..
	  $(".touch_left_btn a").on("click",function(){//이전 버튼을 누를 때마다...
	     mySwipe.prev(); //이전 배너로 이동 하는 메서드 입니다.
	     return false; //링크를 차단합니다.
	  });
	  //요약 : > 다음 이미지 버튼을 클릭 했을때...
	  $(".touch_right_btn a").on("click",function(){//다음 버튼을 누를 때마다...
	     mySwipe.next(); //다음 배너로 이동 하는 메서드 입니다.
	     return false; //링크를 차단합니다.
	  });
 

  //-----------------------------------------------------------

  /*
   주제 : 자동 롤링 배너와 제어 버튼을 활용한 알림판 만들기
  
   알림판은 일정 시간 간격으로 자동으로 배너 이미지가 바뀌면서 해당하는 배너에 버튼이 활성화 됨.
   이때 버튼을 마우스로 클릭하면 버트넹 해당하는 배너로 이동 됨.
   그리고 정지 ■ 버튼을 누르면 자동으로 넘어가던 배너가 정지되거, 재상 ▶ 버튼을 누르면 다시 배너가 넘어가게 됨 
   */
  
  /*롤링 버튼 배너*/
	  //id속성값이 roll_banner_wrap인 <div id="roll_banner_wrap">요소의 
	  //하위 모든 <dd>태그들을 배열형식으로 담아 선택해오자
	  //그 중에서 첫번째 <dd>태그(첫번째 배너)를 제외한 나머지 배너들을 최종적으로 선택해서
	  //첫번째 배너dd를 제외하고 숨긴다.
	  $("#roll_banner_wrap dd").not(":first").hide();
	  
	  //첫번째 버튼의 <a>요소를 선택해서 onBtn변수에 저장
	  var onBtn = $("#roll_banner_wrap dt a:first");
	  
	  //id속성값이 roll_banner_wrap 인 <div>태그의 하위 모든 <dt>태그의, 하위 모든 <a>태그를 선택해서
	  //( [1], [2], [3], [4] <img>를 포함하고 있는 <a>태그들을 배열에 담아서 선택해 옴)
	  $("#roll_banner_wrap dt a").on("click", function() {
		
		  /*onBtn변수에 저장되어있는 <a>태그의 하위 <img>태그를 선택해
		   * "src"속성의 값을 비활성화된 이미지 경로로 바꿉니다.
		   */
		  
		  //노출 되어 있는 배너 이미지 영역을 숨긴다.
		  $("#roll_banner_wrap dd:visible").hide();
		  
		  //변수 onBtn에 저장되어 있는 <a>태그의 하위 버튼<img>태그를 선택
		  $("img", onBtn).attr( "src" , $("img", onBtn).attr("src").replace("over.gif","out.gif") );
		  

		  //[1]~[4]번 버튼 이미지중.. 클릭한 <a>태그를 선택해 그 <a>태그의 index위치값을 구해 옵니다.
		  //예를 들어,	[1]번 버튼을 클릭했을 때 변수 num에는 0이 저장되고,
		  //			[3]번 버튼을 클릭했을 때 변수 num에는 2가 저장되게 하자.
		  var num =  $("#roll_banner_wrap dt a").index(this);
		  
		  //클릭한 버튼에 인덱스 위치값과 일치하는 <dd>태그영역(배너이미지영역)만 화면에 나타나게 하기
		  //풀이 : 변수 num에 저장된 클릭한 <img>를 감싸고 있는 <a>의 인덱스 값을 이용해
		  //		해당하는 배너 <img>를 포함하고 있는 <dd>태그영역을 선택해 show()메소드를 이용하여 노출시키자
		  $("#roll_banner_wrap dd").eq(num).show();
		  
		  //클릭한 <a>에 하위<img>선택해 src속성의 값을 주어 활성화된 버튼 이미지 경로로 바꾸기
		  //참고 :  $("img",this)를 이용하여 클릭한 <a>태그의 하위 버튼 이미지를 선택합니다.
		  //		그리고 attr("속성명","바꿀값")메소드를 이용하여 활성화된 이미지로 바꿉니다.
		  $("img",this).attr("src", $("img",this).attr("src").replace("out.gif","over.gif"));
		  
		  
		  //[1]~[4]버튼중에서 클릭 이벤트가 발생한 버튼 <a>요소를 선택해  onBtn에 저장
		  onBtn = $(this);
		  
		//<a>링크 기능을 차단
		  return false;
		  
		   
	  });
	  
 
  
  /*
  autoPlay 함수가 4초 간격으로 호출되어 1~4번 버튼이 순차적으로 강제로 클릭되어 자동으로 배너가 바뀝니다.
  */
 
	  var btnNum = 0;
	  
	 //함수의 전체 흐름
	 //최초 한번은 3초 간격으로 autoPlay함수를 호출하여 실행문을 실행하고,
	 //다음번에는 4초 간격으로  반복적으로 재귀함수(autoPaly()함수)를 호출하여 실행문을 실행 시킴.
 
	  function autoPlay() {
	
		//일정한 간격으로 실행문이 실행될 때마다 변수 btnNum의 값이 1씩 증가됩니다.
		  btnNum++;
		  

		  /*
		   Auto 동작 원리에 대해서...
		   버튼은 총 4개로, 1번부터 4번까지 차례대로 일정한 시간간격으로 강제 클릭이벤트가 반복되어 발생된다면
		   자동으로 배너 이미지가 바뀌게 될 것입니다.
		   이때 버튼<img>를 감싸고 있는 <a>의 인덱스 위치값은 0부터 3까지 입니다.
		   그러므로 일정한 간격으로 인덱스 위치값이 0부터 1씩 증가되고
		   값이 4이상이면 다시 0로 돌려 강제 이벤트를 발생시킵니다.
		  */
		  //만약 btnNum변수의 값이 4이상이 되면 btnNum변수의 값을 0으로 초기화시킴
		  if(btnNum >= 4) btnNum = 0;
		  
		  //변수 btnNum에 저장된 값을 이용해 버튼<img>를 감싸고 있는 <a>를 차례로 선택해 온 후
		  //trigger("이벤트명") 함수를 적용하여 강제로 클릭이벤트를 발생시킨다.
		  $("#roll_banner_wrap dt a").eq(btnNum).trigger("click");
		  
		  //2초 간격으로 재귀함수 호출이 발생한다.
		  auto1 = setTimeout(autoPlay, 2000);
	  }//autoPlay함수 끝
	  
	  //최초 로딩시 2초 이후에 autoPlay함수를 호출하여 실행합니다
	  var auto1 = setTimeout(autoPlay, 2000);

 
 
 /* 재생 버튼 ▶ 을 클릭했을때 다시 배너가 일정한 간격으로 바뀌게 하기  */  
 
	  $(".playBtn").on("click", function() {
		  
		  //방문자가 재생버튼을 클릭하게 되면 auto1변수에 저장된 setTimeout()함수는
		  //스택에 실행문이 여러번 쌓여 문제가 될 수 있다.
		  //이 문제를 해결하기 위해서는 clearTimeout()메소드를 사용해 삭제한 후에
		  //setTimeout()함수를 다시 실행해야한다.
		  //이 결과, 여러번 중복되어 스택에 쌓이는 것을 방지할 수 있게됨.
		  
		  clearTimeout(auto1);	//auto1변수에 할당된 setTimeout함수를 스택메모리에서 제거시킴
		  setTimeout(autoPlay, 1000);	//1초 뒤에 autoPlay함수를 호출하여 실행
		  $("img", this).attr("src", $("img", this).attr("src").replace("off.gif","on.gif"));
		  $(".stopBtn img").attr("src", $(".stopBtn img").attr("src").replace("on.gif","off.gif") );
		  
		  //<a>태그의 이동기능을 차단
		  return false;
	  });
	  
 
  
  
/*
 정지 버튼 ■을 클릭 했을때 일정한 간격으로 함수를 실행하여 버튼을 순차적으로 클릭 되게 하는 setTimeout()메서드를 제거하고,
 정지 버튼 ■을 활성화 시킵니다. 즉, 자동 배너를 정지 시킵니다.  
 */  
	  $(".stopBtn").on("click", function() {
		  clearTimeout(auto1);
		  $("img", this).attr("src", $("img", this).attr("src").replace("off.gif","on.gif"));
		  $(".playBtn img").attr("src", $(".playBtn img").attr("src").replace("on.gif","off.gif") );
		  
		//<a>링크 기능을 차단
		  return false;
	  });
  


 //-----------------------------------------------------------
  /*
   주제 : 탭 메뉴를 이용해 최근 게시물 리스트 만들기
  
  - 탭메뉴의 경우 최초 탭버튼인[공지사항]이 활성화되어 보입니다.
        만일 방문자가 [질문과답변]탭을 클릭했을 때는 [공지사항]은 숨겨져야 하고,
    [질문과 답변]의 내용은 활성화되어 보여야 합니다.
    
  - 먼저 탭버튼에 <a>에 on()메서드를 사용하여 mouseover,focus,click이벤트를 등록하였고,
        이벤트 핸들러에는 이벤트가 발생 했을때 마우스를 올린 탭 버튼과 탭에 해당하는 게시물 목록이 활성화되어 보이도록 만들자. 
   */
  
 
  /*탭메뉴*/

	  //id속성의 값이 tabmenu인 <dl id="tabmenu">태그의 하위 dt태그의 하위 첫번째 <a>태그를 선택해서
	  //요약 : 초기에 활성화된 첫번째 탭 <a>만 선택해서 변수 onTab에 할당
	  var onTab = $("#tabmenu dt a:first"); //[공지사항] 탭
	  
	  //탭영역을 나타내는 <a>태그들 (공지사항, 질문과 답변, 저자문의) 중에..
	  //마우스를 올리거나 포커스가 생성시키거나 클릭 했을 때..
	  $("#tabmenu dt a").on("mouseover focus click", function() {
		
		  //먼저 현재 노출(:visible)되어 있는 <dd>태그영역(게시물 리스트목록화면)을 숨김
		  //요약 : 현재 보이는 게시물 목록을 숨김
		  $("#tabmenu dd:visible").hide();
		  
		  //onTab변수에 할당된 [공지사항] <a>태그의 하위<img>태그를 선택해 src속성값을 바꾸어
		  //비활성화된 [공지사항]이미지로 만들자
		  $("img",onTab).attr( "src", $("img",onTab).attr("src").replace("over.gif","out.gif") );
		  
		  //마우스를 올리거나 클릭한 <a>태그(공지사항 or 질문과 답변 or 저자문의)에 
		  //부모요소인 <dt>태그 다음에 오는 <dd>태그를 show()메소드로 노출시키자
		  $(this).parent().next().show();
		  
		  //마우스를 올리거나 클릭한 <a>태그에 하위 버튼<img>태그를 선택해 활성화된 이미지로 바꿉니다.
		  $("img",this).attr("src",$("img",this).attr("src").replace("out.gif","over.gif"));
		  
		  //탭 버튼의 <a>태그들 (공지사항, 질문과 답변, 저자문의)중에 마우스를 올리거나 포커스 또는 클릭했을 경우
		  //클릭 할때마다 클릭한 요소에 <a>를 선택해서 onTab변수에 다시 할당.
		  onTab = $(this);
		  
		  //<a>링크 기능을 차단
		  return false;
		  
	  });
	

 //-----------------------------------------------------------
  
/*  
주제 : 자동 슬라이드 배너 를 이용한 베스트 Book영역   
	 https://bxslider.com/ 접속하여 사용법 보기 
*/
  /* 베스트북 슬라이더 */
	  //$("#best_bg ul")로 베스트Book 목록 태그인 <ul>태그를 선택하여
	  // bxSlider()메소드 적용하고 슬라이드 옵션을 지정하면 된다.
	  var mySlider = $("#best_bg ul").bxSlider({
		  
		  mode : "horizontal",	//수평방향으로 이동시키기
		  speed : 500		,	//이동속도(500 = 0.5초)
		  moveSlides : 1	,	//이동시킬 슬라이드 수 설정
		  slideWidth : 125	,	//슬라이드 폭
		  minSlides : 1		,	//최소 노출되는 슬라이드 수
		  maxSlides : 3		,	//최대 노출되는 슬라이드 수
		  slideMargin : 30	,	//슬라이드 간의 간격
		  auto : true		,	//자동 슬라이드 여부(true:자동, false:수동)
		  autoHover : true	,	//마우스 오버시 자동 정지 여부(true:자동정지, false:정지안함)
		  pager : false		,	//페이징 표시를 제어(true: 노출, false:숨김)
		  controls : false		//이전(prev), 다음(Next) 버튼 노출여부(true:노출, false:숨김)
		  
	  });

	  // '<' 버튼을 클릭했을때..
	  $(".prev_btn").on("click", function() {
		
		  //goToPrevSlide()메소드를 이용하여 슬라이드를 한단계 이전으로 이동
		  mySlider.goToPrevSlide();
		  
		  return false;
	
	  });
	  
	// '>' 버튼을 클릭했을때..
	  $(".next_btn").on("click", function() {
		
		  //goToNextSlide()메소드를 이용하여 슬라이드를 한단계 다음으로 이동
		  mySlider.goToNextSlide();
		  
		  return false;
	
	  });
 
  //-----------------------------------------------------------

  /*  
  주제 : 제이쿼리 UI플러그인과 쿠키 플러그인 사용 하기
  - 팝업창을 드래그 하여 이동시키여면, 제이쿼리 UI플러그인을 사용함.
  - [하루동안 이창 열지 않기]버튼 기능을 하용하기 위해서는 쿠키 플러그인을 사용함
  
  참고 : 쿠키 플러그인 사용법
  	       
  	    <쿠키를 생성 하는 기본 사용법>
  	  	 $.cookie("쿠키명","쿠키값",{expires:만료일});
  	  	 설명 : 쿠키명은 나중에 저장된 쿠키의 값을 불러올때 구분하기 위한 이름임.
  	  	           생성된 쿠키는 현재 부터 며칠동안 클라이언트 컴퓨터의 웹브라우저에 보관할건지 만료일(expires)을 지정할수 있음.

			예)
	 	     $.cookie("pop","no",1);
	 	         설명: 브라우저에는 "pop"라는 이름으로 "no"라는 값이 1일 동안 쿠키가 보관 됩니다.
 	         
 	    <쿠키 플러그인을 이용하여  브라우저에 저장된 쿠키를 불러오는 기본 사용법>
 	    $.cookie("쿠키명");
 	    
	 	       저장된 쿠키값인 "no" 불러오는 방법의 예)
	 	    $.cookie("pop");
 	    
 	    <쿠키 플러그인을 이용하여 브라우저에 저장된 쿠키를 삭제하는 기본 사용법>
 	    $.cookie("쿠키명",null);
 	    
	 	    "pop"에 저장된 쿠키값 삭제의 예)
 	         $.cookie("pop",null);
  	  		
  */
  
   /*팝업 연동*/
	  //설명: 저장된 "pop"에 쿠키값을 검사하여 만일 쿠키가 저장되어 있지 않으면 숨겨져 있떤 팝업창이 보이도록 함
	  //		그리고 사이트 방문객이 [하루동안 이 창 열지 않기]를 눌렀을때
	  //		쿠키가 생성되어 웹브라우저에 하루동안 보관 되도록 함
	  
	  //만약 "pop"쿠키명에 해당하는 쿠키값 no가 웹브라우저에 저장되어 있지않다면?
	  if($.cookie("pop") != "no"){
		  //숨겨져 잇던 팝업창 영역 $("#pop_wrap")을 노출 show()
		  $("#pop_wrap").show();
	  }
	  
	  //팝업창 영역에 마우스포인터를 올리면 커서 모양으로 바뀌게 하기
	  //그리고 draggable()메소드를 이용하여 마우스로 드래그 했을 때 팝업창이 부드럽게 이동되도록 해보자
	  $("#pop_wrap").css("cursor","move").draggable();

	  //팝업창 $("#pop_wrap")의 하위 <area>태그 중.. 
	  //0번째 인덱스 위치에 있는 [창닫기] 메뉴 버튼 영역을 선택해서 클릭했을때
	  $("#pop_wrap area:eq(0)").on("click",function(){
		  
		  //팝업창 <p>요소영역을 선택해 fadeOut("fast")메소드를 이용하여
		  //팝업창이 점점 투명해 지면서 사라지게 만들자
		  $("#pop_wrap").fadeOut("fast");
		  
		  return false;
	  });
	  
	  //[하루동안 이 창 열지 않기] 영역 (2번째 area태그영역)을 클릭햇을때..
	  
	  $("#pop_wrap area:eq(1)").on("click",function(){
		  
		  //쿠키를 생성
		  //쿠키명 "pop"에 쿠키값 "no"를 저장합니다.
		  //만료일(삭제)(expires)은 오늘로부터 하루가 지나야 합니다.
		  $.cookie("pop","no",{expires:1});
		  
		//팝업창이 점점 투명해 지면서 사라지게 만들자
		  $("#pop_wrap").fadeOut("fast");
		  
		  return false;
	  });
	  
	  
	  
  //크롬(Chrome)브라우저로 쿠키를 확인하는 방법을 알아보도록 해요. 
//  - 개발자도구(F12) 를 연후 Appliecation -> Storage -> Cookies 에서 확인 가능하다.





});
