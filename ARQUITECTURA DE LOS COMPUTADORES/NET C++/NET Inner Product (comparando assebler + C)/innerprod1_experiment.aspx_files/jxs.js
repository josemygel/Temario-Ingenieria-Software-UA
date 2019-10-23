//V2.1 - Based on http://www.openjs.com/scripts/jx/ - released under BSD license
// 2.1 version by Chris Maunder (www.codeproject.com) 
//  - use XMLHttpRequest as 1st priority + use window.XMLHttpRequest in test, allow loading 
//    indicator to be specified. Also added '$'. Added custom error handler. Added optional 
//    load parameters removed bind method
jx = {
	http:false, //HTTP Object
	format:'text',
	callback:function(data){},
	indicator:false,
	customError:false,
	customhandler:false,
	$: function(id) {return document.getElementById(id);},
	error: function(code){alert('An error occurred (status '+code.toString()+')')},
	//Create a xmlHttpRequest object - this is the constructor. 
	getHTTPObject : function() {
		var http = false;
		if (window.XMLHttpRequest) {
			try {http = new XMLHttpRequest();}
			catch (e) {http = false;}
		} else	if(typeof ActiveXObject != 'undefined') {
			try {http = new ActiveXObject("Msxml2.XMLHTTP");}
			catch (e) {
				try {http = new ActiveXObject("Microsoft.XMLHTTP");}
				catch (E) {http = false;}
			}
		}
		return http;
	},
	// This function is called from the user's script. 
	//Arguments - 
	//	url	- The url of the serverside script that is to be called. Append all the arguments to 
	//			this url - eg. 'get_data.php?id=5&car=benz'
	//	callback - Function that must be called once the data is ready.
	//  loadingId - id of 'loading' HTML element to be displayed
	//  errCallback - custom error callback
	//	format - The return type for this function. Could be 'xml','json' or 'text'. If it is json, 
	//			the string will be 'eval'ed before returning it. Default:'text'
	//	method - GET or POST. Default 'GET'
	//  customHandler - custom error callback
	load : function (url,callback /*,loadingId,errCallback,format,method,customHandler*/) {
		var method='GET';
		var format='text';
		this.init(); //The XMLHttpRequest object is recreated at every call - to defeat Cache problem in IE
		if(!this.http||!url) return;
		//XML Format need this for some Mozilla Browsers
		if (this.http.overrideMimeType) this.http.overrideMimeType('text/xml');

		this.callback=callback;
		if(arguments[2])this.indicator=this.$(arguments[2]);
		if(arguments[3])this.customError=arguments[3];
		if(arguments[4])method = arguments[4].toUpperCase();
		if(arguments[5])format = arguments[5].toLowerCase();
		if(arguments[6])this;customhandler = arguments[6]
		var ths = this; //Closure
		var now = "cache-bust=" + new Date().getTime(); // IE cache kill
		url += (url.indexOf("?")+1) ? "&" : "?";
		url += now;

		var parameters = null;

		if(method=="POST") {
			var parts = url.split("\?");
			url = parts[0];
			parameters = parts[1];
		}
		this.http.open(method, url, true);

		if(method=="POST") {
			this.http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			this.http.setRequestHeader("Content-length", parameters.length);
			this.http.setRequestHeader("Connection", "close");
		}

		if(this.customhandler) { //If a custom handler is defined, use it
			this.http.onreadystatechange = this.customhandler;
		} else {
			this.http.onreadystatechange = function () {
				if(!ths) return;
				var http = ths.http;
				if (ths.indicator)ths.indicator.style.display='';
				if (http.readyState == 4) {//4 = document loaded.
					if (ths.indicator)ths.indicator.style.display='none';
					if(http.status == 200||http.status==0) {
						var result = "";
						if(http.responseText) result = http.responseText;
						if(ths.format.charAt(0) == "j") { // JSON -> eval result first
							result = result.replace(/[\n\r]/g,""); //\n's in JSON string cause errors in IE
							result = eval('('+result+')');
						} else if(ths.format.charAt(0) == "x") { //XML
							result = http.responseXML;
						}
						if(ths.callback) ths.callback(result); // process
					} else {					
						if(ths.customError)ths.customError(http.status); else ths.error(http.status);
					}
				}
			}
		}
		this.http.send(parameters);
	},
	init : function() {this.http = this.getHTTPObject();}
}
