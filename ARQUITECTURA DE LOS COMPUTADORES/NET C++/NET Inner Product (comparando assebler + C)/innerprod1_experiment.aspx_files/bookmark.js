// Copyright (c) 2008, The Code Project. All rights reserved.

function bookmarkMe(objId, objTypeId)
{
    var idPrefix = "bm_";
	var bmkLink = GetBmkLink(idPrefix, objId.toString(), objTypeId.toString());
	var bmkStatusMsg = GetBmkStatusMsg(idPrefix, objId.toString(), objTypeId.toString());

	if (LoadWaitMessage(bmkLink, bmkStatusMsg))
	    $(bmkStatusMsg).load("/Script/Bookmarks/Ajax/Add.aspx?obtid=" + objTypeId + "&obid=" + objId + "&bma=addbookmark");
	return false;
}

function watchMe(objId, objTypeId)
{
    var idPrefix = "bmw_";
    var bmkLink = GetBmkLink(idPrefix, objId.toString(), objTypeId.toString());
    var bmkStatusMsg = GetBmkStatusMsg(idPrefix, objId.toString(), objTypeId.toString());
    
	if (LoadWaitMessage(bmkLink, bmkStatusMsg))
	    $(bmkStatusMsg).load("/Script/Bookmarks/Ajax/Add.aspx?obtid=" + objTypeId + "&obid=" + objId + "&bma=addwatch");
	return false;
}

function GetBmkLink(idPrefix, objectId, objectTypeId)
{
    return $("a[name=" + idPrefix + objectId + "_" + objectTypeId + "]");
}
function GetBmkStatusMsg(idPrefix, objectId, objectTypeId)
{
    return $("span[name=" + idPrefix + objectId + "_" + objectTypeId + "]");
}

function LoadWaitMessage(bmkLink, bmkStatusMsg)
{
    if (bmkLink.length <= 0 || bmkStatusMsg.length <= 0)
        return false;
        
    $(bmkLink).hide();
    $(bmkStatusMsg).html(" please wait... ");
    $(bmkStatusMsg).css("visibility", "visible");
        
	return true;
}
