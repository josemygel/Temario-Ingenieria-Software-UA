// Copyright (c) 2008, The Code Project. All rights reserved.
var obid, obtid, value

function rateItem(objId, objTypeId){
    if(objId > 0 && objTypeId > 0){
        obid  = objId.toString();    
        obtid = objTypeId.toString();
        value = 0;
        
        PrepElements();
        
        var elems = document.getElementsByTagName("input");
        var reg = /.+VoteRBL_\d{1}/;
        
        for (var i = 0; i < elems.length; i++){
			var attr = elems[i].getAttribute("id");
			if(!attr)continue;
            if(reg.test(attr)){
                var radio = document.getElementById(attr);
                if(radio&&radio.checked){
                    value = radio.getAttribute("value");
                    break;
                }
            }      
        }
        if(value > 0)
            jx.load("/Script/Ratings/Ajax/RateItem.aspx?obid=" + obid + "&obtid=" + obtid + "&rvv=" + value, callback);
        else
            ShowErrorMessage();
    }
    return false;
}

function callback(data){
    if(data.length > 0){
	    var voteRes = document.getElementById("voteRes");
	    if(voteRes) {
			voteRes.innerHTML = data;
  			voteRes.style.display = "";
  		}
  		voteRes = document.getElementById("CurRat");
  		if(voteRes)voteRes.style.display = "none";
    }
    var loader = document.getElementById("loaderImg");
    if(loader)loader.style.display = "none";
}

function PrepElements(){
    var loader = document.getElementById("loaderImg");
    if(loader&&loader.style.display == "none")
        loader.style.display = "";
        
	loader = document.getElementById("voteTbl");
	if(loader)loader.style.display = "none";

	loader = document.getElementById("voteRes");
	if(loader)loader.style.display = "none";
}

function ShowErrorMessage(){
    var loader = document.getElementById("loaderImg");
    if(loader)loader.style.display = "none";
    
    var voteRes = document.getElementById("voteRes");
    if(voteRes) {
		voteRes.innerHTML = "An error occurred. Your vote has not been saved. " +
                            "Please try again later.";
		voteRes.style.display = "";
	}
}