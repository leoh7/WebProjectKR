
$(function(){
	// debug
//	$('*').on(
//		'click',
//		function(event){
//			alert( $(this).val() );
//			return false;
//		}
//	);
	
	// 부트스트랩
	$('table').addClass(
		'table table-bordered'
	);
	
	// Date Picker
	$(document).find('.from, .to').removeClass('hasDatepicker').datepicker();	

	var dateFormat = 
		"mm/dd/yy",
		from = $( ".from" ).datepicker({
				language : "kr",
				defaultDate: "+1w",
				changeMonth: true,
			}),
		to = $( ".to" ).datepicker({
				language : "kr",
				defaultDate: "+1w",
				changeMonth: true,
			});
	function getDate( element ) {
		var date;
		try {
			date = $.datepicker.parseDate( dateFormat, element.value );
		} catch( error ) {
			date = null;
		}
		return date;
	}
	
	// 기간 구하기
	$('.from, .to').on(
		'change',
		function(event){
			var stDate = new Date($('.from').val());
		    var endDate = new Date($('.to').val());
		    var btMs = endDate.getTime() - stDate.getTime() ;
		    var btDay = btMs / (1000*60*60*24) ;
		    
		    if($('.fromTo').val() != NaN){
		    	if(btDay >= 30){
		    		btDay = btDay / 30;
		    		$('.fromTo').val(parseInt(btDay) + ' 개월');
		    	} else {
		    		$('.fromTo').val('1 개월');
		    	}
		    }
		}
	);
	
	// 입력경력 행추가
	$('input:button[name="btn_add_tbody"]').on(
		'click',
		function(event){
			var rowItem = '<tr>';
			rowItem += 		'<td width="8px"><input type="text" name=iptId value="" placeholder="번호 / hidden예정" /></td>';
			rowItem += 		'<td><input type="text" name="iptCompany" value="" placeholder="업체명"  /></td>';
			rowItem += 		'<td><input type="text" name="iptWh" value="" placeholder="근무지" /></td>';
			rowItem += 		'<td><input type="text" class="from" name="iptStart" value=""></td>';
			rowItem += 		'<td><input type="text" class="to" name="iptEnd" value=""></td>';
			
			rowItem += 		'<td><select class="posId form-control" name="posId">';
			rowItem +=			'<option value="1">팀장</option>';
			rowItem +=			'<option value="2">스캔</option>';
			rowItem +=			'<option value="3">예도</option>';
			rowItem +=			'<option value="4">안내</option>';
			rowItem +=			'<option value="5">경호</option>';
			rowItem +=			'<option value="6">기타</option>';
			rowItem +=		'</select></td>';
			
			rowItem += 		'<td><input type="text" name="iptPeriod" clss="fromTo" value="" readonly placeholder="기간" /></td>';
			rowItem += 		'<td>';
			rowItem += 			'<button type="button" class="delCrr btn btn-danger"> <i class="fa fa-minus"></i></button>';
			rowItem += 		'</td>';
			rowItem += '<tr>';
			$('#iptCrr_tbody').append(rowItem);
			
			$(document).find('.from, .to').removeClass('hasDatepicker').datepicker();
			
		}
	);
	
	// 입력경력 행삭제
	$('#iptCrr_tbody').on(
		'click',
		'.delCrr',
		function(event){
			$(this).closest('tr').remove();
		}
	);
	
	$('input:button[name=btn_submit_tbody]').on(
		'click',
		function(){
			$('#iptCrr_tbody tr').each(function () {
				var cellItem = $(this).find(":input");
				var itemObj = new Object();
				
				var stDate = new Date( cellItem.eq(3).val() );
			    var endDate = new Date( cellItem.eq(4).val() );
			    var btMs = endDate.getTime() - stDate.getTime() ;
			    var btDay = btMs / (1000*60*60*24) ;
			    
		    	if(btDay >= 30){
		    		btDay = parseInt(btDay / 30)+' 개월';
		    	} else {
		    		btDay = '1 개월';
		    	}
				
				itemObj.iptId = cellItem.eq(0).val();	// 번호
				itemObj.iptCompany = cellItem.eq(1).val();	// 업체명
				itemObj.iptWh = cellItem.eq(2).val();	// 근무지
				itemObj.iptStart = cellItem.eq(3).val();	// 시작일
				itemObj.iptEnd = cellItem.eq(4).val();	// 종료일
				itemObj.posId = cellItem.eq(5).val();	// 근무직무
				
				if(itemObj.iptId != undefined){
					var realPeriod = btDay.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|" "]/g,"");
					var queryStr = {
						iptId : itemObj.iptId,
						iptCompany : itemObj.iptCompany,
						iptWh : itemObj.iptWh,
						iptStart : itemObj.iptStart,
						iptEnd : itemObj.iptEnd,
						posId : itemObj.posId,
						iptPeriod : realPeriod
					}
					
					$.ajax({
						type : 'POST',
						url : 'iptCrrWrt.do',
						data : queryStr,
						dataType : 'json',
						success : function(json){
							alert ( "삽입성공" );
						},
						error:function(request,status,error){
//						    alert( "code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							alert ("삽입실패 / 라고 뜨지만 들어는 감");
						}
					});
				}
			})
		}     
	);
	            
});             
                