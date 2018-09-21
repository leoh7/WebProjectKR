$(document).ready(
	function(){
		$('input[name=jobpId]').on(
			'keyup',
			function(event){
				var jobpId = $('input[name=jobpId]').val();
				
				if( jobpId){
					$.ajax({
						type : "POST",
						data : {
							jobpId : jobpId 
						},
						url : 'Jbp/idcheck.jsp',
						dataType : 'xml',
						success : function(data){								
							$('.idresult').text($(data).find('message').text());
						},
						error : function(e){
							$('.idresult').text(e.message);
						}
					});
				}
			}
		);
		
		$('input[name=jobpPasswd]').on(
			'keyup',
			function(event){
				var jobpPasswd = $('input[name=jobpPasswd]').val();
				if(jobpPasswd){
					if(isNaN(jobpPasswd)){
						$('.passwdresult').text('사용할 수 있습니다');
					}else{
						$('.passwdresult').text('사용할 수 없습니다.');
					}
				}
				$('input[name=jobpPasswd]').val()
			}
		);
		
		//비밀번호가 같으면 사용할 수 있다
		//다르면 사용할 수 없다
		$('input[name=rejobpPasswd]').on(
			'keyup',
			function(event){
			var repasswd = $('input[name=rejobpPasswd]').val();
			if(repasswd){
				if($('input[name=jobpPasswd]').val()== $('input[name=rejobpPasswd]').val()){
					
					$('.repasswdresult').text('사용할 수 있는 아이디입니다');	// FIXME : (임시)유효성검사 만든 후, 재작업 필요
				}else{
					$('.repasswdresult').text('사용할 수 없는 아이디입니다');	// FIXME : (임시)유효성검사 만든 후, 재작업 필요
				}
			}
			$('input[name=rejobpPasswd]').val()
			}
		);
		
		$('input:button[name=checkBizID]').on(
			'click',
			function checkBizID() {
				if( !$('input:text[name=jobpBno]').val() ){
					alert("사업자번호를 입력해주세요");
					return false;
				} else {
					var bizID = $('input:text[name=jobpBno]').val();
				    var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
				    var tmpBizID, i, chkSum=0, c2, remander;
				    var result;
				    
				    bizID = bizID.replace(/-/gi,'');	// 들어있는 - 를 모두 빈칸으로 치환함
				 
				    for (i=0; i<=7; i++) {
				        chkSum += checkID[i] * bizID.charAt(i);
				    }
				 
				    c2 = "0" + (checkID[8] * bizID.charAt(8));
				    c2 = c2.substring(c2.length - 2, c2.length);
				    chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
				    remander = (10 - (chkSum % 10)) % 10 ;
				 
				    if (Math.floor(bizID.charAt(9)) == remander) {
				    	alert('사업자등록번호 형식에 맞는 번호입니다');
				    	// TODO : 맞는 사업자 등록번호일 시, bizID 앞 6자리 파싱해서 공공데이터 포털날리고
				    	// 폼 만들어서 선택하게끔
				    	var bizName = $('input:text[name=jobpCn]').val();
				    	var parseBizID = bizID.substring(0, 6);
				    	alert(parseBizID)
				    	result = true ; // OK!
				    } else {
				    	alert('사업자등록번호 형식에 맞지 않습니다');
				        result = false;
				    }
				    return result;
				}
			}
		);	// 사업자번호 유효성 검사
	}		
);