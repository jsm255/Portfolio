function deleteCheck(form){

	var result = window.confirm("정말 탈퇴하시겠습니까?");
	const pwCheck = form.pwCheck.value;
	const pw = form.pw.value;
	
	if(result){
		if(pwCheck === pw){
			form.submit();
			alert("탈퇴 되었습니다");
			return true;
		}
		else{
			alert("잘못된 비밀번호입니다.");
			return false;
		}
	}
	else
		return false;
	
}

$(`#changeBtn`).click(function(form){
	
	var result = window.confirm("수정하시겠습니까?");
	const tel = form.tel.value;
	if(checkTel())
	if(result)
		return true;
	else
		return false;
	
});

function checkTel(){
	var tel = document.querySelector("#tel").value;
	
	var telReg = /^01([0|1]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	
	if(tel.match(telReg) != null){
		return false;
	}
	else{
		alert("전화번호를 다시 입력해 주세요");
		return true;
	}
}

function checkLetter(){
	var letter = document.querySelector("#country").value;
	
	var letterReg = /^[가-힣]+$/;
	
	if(letter.match(letterReg) != null){
		return false;
	}
	else{
		alert("한글로 입력해 주세요");
		return true;
	}
	
}



