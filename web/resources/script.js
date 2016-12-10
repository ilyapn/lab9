function imageClickHandler(e){
	e = e || window.event;

	if (e.pageX == null && e.clientX != null ) { 
		var html = document.documentElement;
		var body = document.body;
	
		e.pageX = e.clientX + (html && html.scrollLeft || body && body.scrollLeft || 0) - (html.clientLeft || 0);
		e.pageY = e.clientY + (html && html.scrollTop || body && body.scrollTop || 0) - (html.clientTop || 0);
	}
	
	var image_left_corner = getOffsetRect(document.getElementById("myform:imageGraph"));
	
	//var radius = +document.getElementById('myform:input_r').value;
	var user_x = ( e.pageX - image_left_corner.left - 200 ) / 50;
	var user_y = ( 200 + image_left_corner.top - e.pageY ) / 50;
	
	document.getElementById('myform:hiddenXval').value = user_x;
	document.getElementById('myform:hiddenYval').value = user_y;
	document.getElementById('myform:hiddenXval').click();
	document.getElementById('myform:hiddenYval').click();
	document.getElementById('myform:hiddenSubmit').click();
	
	
}

function getOffsetRect(elem) {
	var box = elem.getBoundingClientRect();
	
	var body = document.body;
	var docElem = document.documentElement;
	
	var scrollTop = window.pageYOffset || docElem.scrollTop || body.scrollTop;
	var scrollLeft = window.pageXOffset || docElem.scrollLeft || body.scrollLeft;
	
	var clientTop = docElem.clientTop || body.clientTop || 0;
	var clientLeft = docElem.clientLeft || body.clientLeft || 0;
	
	var top  = box.top +  scrollTop - clientTop;
	var left = box.left + scrollLeft - clientLeft;
	
	return { top: Math.round(top), left: Math.round(left) };
}

function updateImage(){  
        document.getElementById('myform:updateGraph').click();
}

function zhop(){
//    var r = document.getElementById('myform:input_r').value;
//    if(r<0)
//        alert("hellozhopa");
}