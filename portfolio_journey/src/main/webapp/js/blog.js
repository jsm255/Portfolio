/**
 * 
 */

/*async function uploadFile(){
	event.preventDefault();
	
	let fileReqDto = {
		title : document.querySelector("#title").value,
		countryName : document.querySelector("#countryName"),
		range: document.querySelector("#range"),
		content: document.querySelector("#content"),
		image: document.querySelector("#image")
	}
	
	let response = await fetch("/FileUploadServlet", {
		method: "post",
		body: JSON.stringify(fileReqDto),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	});
}*/


async function deleteBlog(code){
	event.preventDefault();
	var blogCode = code;
	 if (confirm("정말 삭제하시겠습니까?") == true){    //확인
		location.href="service?command=deleteBlog&code="+code;
		console.log("code :" + code);
 }else{   //취소

     return false;

 }
}