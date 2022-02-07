/**
 * 
 */

function toggleTable(event){
   	const id = "#" + event.currentTarget.getAttribute("id") + "_table";
   	$(id).toggle();
}
   	
$("#range").change(e => {
   	const getter = e.target.value;
   	console.log(getter);
   	const span = document.createElement("span");
   	$(span).attr("id", "child");
   	$(span).append(document.createTextNode(getter+"점"));
   	$("#child").replaceWith(span);
});

/*function reReview(event){
	let check = false;
	$.ajax({
		method: 'post',
		host: 'localhost',
		url: './service',
		data: {
			'command': 'writeReReview',
			'code': event.target.getAttribute("id").substring(0,1)
		}
	}).done(result => {
		console.log(result);
		
	})
};*/

//function modifyReview

form.addEventListener("submit",e=>{
	e.preventDefault();
	const button = document.querySelector("#btn button");
	
	
})

 $(".btn-like").click(function() {
            $(this).toggleClass("done");
});
