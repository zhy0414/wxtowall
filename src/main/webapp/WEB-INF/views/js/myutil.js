function request(paras){
	var url = location.href;
	var paraString = url.substring(url.indexOf("?")+1, url.length).split("&");
	var paraObj = {};

	for(var i=0;i<paraString.length;i++){
		j=paraString[i];
		//alert("j is:"+j);
		paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1, j.length);
		//alert("paraObj["+j.substring(0, j.indexOf("=")).toLowerCase()+"] is:"+j.substring(j.indexOf("=")+1, j.length));
	}

	var returnValue = paraObj[paras.toLowerCase()];

	if(typeof(returnValue) == "undefined"){
		return "";
	} else {
		return returnValue;
	}
}