function checkId(){
	//let userId = document.querySelector("#id");
	let userId = document.getElementById("id").value;
	event.preventDefault();
	let response = await fetch("/service/")
//	console.log("id : " + userId);
//	alert("id : " + userId);
}