/******************************************************
*	(c) Ger Versluis 2000 version 13.21               *
*	You may use this script on non commercial sites.  *
*	www.burmees.nl/menu			                      *
*                                                     *
*   Used with permission. 13.21 Mods by Chris Maunder *
*******************************************************/

function HVMenu(config)
{
var cfg = config;
var thisobj = this;

if(document.getElementById){
	var AgntUsr=navigator.userAgent.toLowerCase(),AppVer=navigator.appVersion.toLowerCase(),
	Opr=AgntUsr.indexOf("opera")!=-1,Opr7=Opr&&parseInt(AgntUsr.substring(AgntUsr.indexOf("opera")+6))>6?true:false;
	if((!Opr||Opr7)){
		if(AppVer.indexOf("msie 7")!=-1)cfg.OverFormElements=0;
		var NavYes=((AgntUsr.indexOf("mozilla")!=-1&&AgntUsr.indexOf("compatible")==-1)||Opr7)?true:false,ExpYes=(AgntUsr.indexOf("msie")!=-1&&!Opr7)?	true:false;
		if(cfg.MenuUsesFrames&&!parent.frames[0])cfg.MenuUsesFrames=0;
		var Exp6Plus=((AppVer.indexOf("msie 6")!=-1||AppVer.indexOf("msie 7")!=-1)&&!Opr7)?true:false,MacExp5=(AppVer.indexOf("mac")!=-1&&AppVer.indexOf("msie 5")!=-1)?true:false,
		FLoc,ScLoc,DcLoc,SLdAgnWin,FWinH,FColW,SColW,DColW,SWinW,SWinH,FWinW,RLvl=0,FntFc=1,StaticPos=0,Ztop=100,FrstCreat=true,Ldd=false,
		Crtd=false,FCmplnt=false,SCmplnt=false,M_Clckd=false,IniFlg=false,ShwFlg=false,AcrssFrms=true,FrstCntnr=null,CurOvr=null,FHtml=null,ScHtml=null,
		CloseTmr=null,OpnTmr=null,RmbrNow=null,FStr="",M_Hide="hidden",M_Show="visible",P_X="px",M_StrtTp=cfg.StartTop,M_StrtLft=cfg.StartLeft,
		Par=cfg.MenuUsesFrames?parent:window,Doc=Par.document,Bod=Doc.body,Trigger=NavYes?Par:Bod,
		Nav_Op=NavYes&&AgntUsr.indexOf("gecko/")!=-1&&AgntUsr.indexOf("mac")==-1&&parseInt(AgntUsr.substring(AgntUsr.indexOf("gecko/")+6,AgntUsr.indexOf("gecko/")+12))>=200303?true:false,
		InitLdd,MstSz=0;this.LdTmr=setInterval(function(){thisobj.ChckInitLd()},100);}}

this.ChckInitLd = function(){
	InitLdd=ExpYes?
		cfg.MenuUsesFrames?Par.frames[cfg.FirstLineFrame].document.readyState=="complete"&&Par.frames[cfg.SecLineFrame].document.readyState=="complete"?
		1:0:Par.document.readyState=="complete"?
		1:0:cfg.MenuUsesFrames?Par.frames[cfg.FirstLineFrame].document.body&&Par.frames[cfg.SecLineFrame].document.body?1:0:Par.document.body?1:0;
	if(InitLdd)	thisobj.Go();}

this.CnclSlct = function(){return false}

this.RePos = function(){
	FWinW=ExpYes?FCmplnt?FHtml.clientWidth:FLoc.document.body.clientWidth:FLoc.innerWidth;
	FWinH=ExpYes?FCmplnt?FHtml.clientHeight:FLoc.document.body.clientHeight:FLoc.innerHeight;
	SWinW=ExpYes?SCmplnt?ScHtml.clientWidth:ScLoc.document.body.clientWidth:ScLoc.innerWidth;
	SWinH=ExpYes?SCmplnt?ScHtml.clientHeight:ScLoc.document.body.clientHeight:ScLoc.innerHeight;
	if(MstSz)thisobj.SizeMenu(FrstCntnr);
	StaticPos=-1;
	thisobj.ClcRl();
	if(cfg.TargetLoc){thisobj.ClcTrgt();}
	thisobj.ClcLft();
	thisobj.ClcTp();
	thisobj.PosMenu(FrstCntnr,cfg.StartTop,cfg.StartLeft);
	if(cfg.RememberStatus)this.StMnu()}

this.UnLdd = function(){
	Ldd=Crtd=false;
	SetMenu="0";
	var M=FrstCntnr?FrstCntnr.FrstMbr:null;
	if(ExpYes){while(M!=null){if(M.CCn){this.MakeNull(M.CCn);M.CCn=null}M=M.PrvMbr}}
	/*this.LdTmr=setInterval(function(){thisobj.ChckLdd()},100)*/}

this.UnLddTotal = function(){if(ExpYes){
	thisobj.MakeNull(FrstCntnr);
	FrstCntnr=RmbrNow=FLoc=ScLoc=DcLoc=SLdAgnWin=CurOvr=CloseTmr=Doc=Bod=Trigger=null}}

this.MakeNull = function(P){
	var M=P.FrstMbr,Mi;
	while(M!=null){Mi=M;if(M.CCn){this.MakeNull(M.CCn);M.CCn.mif=null;M.CCn=null}M=M.PrvMbr;Mi.PrvMbr=null;Mi=null}
	P.FrstMbr=null}

this.ChckLdd = function(){if(!ExpYes){
	if(ScLoc.document.body)this.Go();}
	else if(ScLoc.document.readyState=="complete")this.Go();}

this.Go = function(){
	if(this.LdTmr)clearInterval(this.LdTmr);this.LdTmr=null;
	
	if(!Ldd){	Crtd=false;Ldd=true;
		cfg.BeforeStart();
		if(cfg.ScaleMenu){
			if(document.defaultView&&document.defaultView.getComputedStyle)FntFc=parseInt(document.defaultView.getComputedStyle(document.body,'').getPropertyValue("font-size"))/16;
			else if(ExpYes&&document.body.currentStyle)FntFc=parseInt(document.body.currentStyle.fontSize)/12}
		status="Building menu";
		FLoc=cfg.MenuUsesFrames?parent.frames[cfg.FirstLineFrame]:window;
		ScLoc=cfg.MenuUsesFrames?parent.frames[cfg.SecLineFrame]:window;
		DcLoc=cfg.MenuUsesFrames?parent.frames[cfg.DocTargetFrame]:window;
		if(cfg.MenuUsesFrames){if(!FLoc)FLoc=window;if(!ScLoc)ScLoc=window;if(!DcLoc)DcLoc=window}
		if(FLoc==ScLoc)AcrssFrms=false;
		if(AcrssFrms)cfg.FirstLineHorizontal=cfg.MenuFramesVertical?0:cfg.FirstLineHorizontal?cfg.FirstLineHorizontal:1;
		if(Exp6Plus||Opr7){
			FHtml=FLoc.document.getElementsByTagName("HTML")[0];ScHtml=ScLoc.document.getElementsByTagName("HTML")[0];
			FCmplnt=FLoc.document.compatMode.indexOf("CSS")==-1?false:true;SCmplnt=ScLoc.document.compatMode.indexOf("CSS")==-1?false:true}
		FWinW=ExpYes?FCmplnt?FHtml.clientWidth:FLoc.document.body.clientWidth:FLoc.innerWidth;
		FWinH=ExpYes?FCmplnt?FHtml.clientHeight:FLoc.document.body.clientHeight:FLoc.innerHeight;
		SWinW=ExpYes?SCmplnt?ScHtml.clientWidth:ScLoc.document.body.clientWidth:ScLoc.innerWidth;
		SWinH=ExpYes?SCmplnt?ScHtml.clientHeight:ScLoc.document.body.clientHeight:ScLoc.innerHeight;
		FColW=FLoc.document.body;SColW=ScLoc.document.body;DColW=ScLoc.document.body;
		if(cfg.TakeOverBgColor)FColW.style.backgroundColor=AcrssFrms?SColW.bgColor:DColW.bgColor;
		if(FrstCreat||FLoc==ScLoc)FrstCntnr=this.CreateMenuStructure("cfg.Menu",cfg.NoOffFirstLineMenus,null);
		else this.CreateMenuStructureAgain("cfg.Menu",cfg.NoOffFirstLineMenus);
		this.SizeMenu(FrstCntnr);
		thisobj.ClcRl();
		if(cfg.TargetLoc){thisobj.ClcTrgt();}thisobj.ClcLft();thisobj.ClcTp();
		this.PosMenu(FrstCntnr,cfg.StartTop,cfg.StartLeft);
		Crtd=true;
		SLdAgnWin=ExpYes?ScLoc.document.body:ScLoc;
		//SLdAgnWin.onunload=thisobj.UnLdd;
		//Trigger.onunload=thisobj.UnLddTotal;
		//Trigger.onresize=thisobj.RePos;
		
		if (ExpYes) {
			//try { 
				SLdAgnWin.attachEvent('onunload', thisobj.UnLdd); 
				//Trigger.attachEvent('onunload', thisobj.UnLddTotal); 
				//Trigger.attachEvent('onresize', thisobj.RePos); 
				window.attachEvent('onresize', thisobj.RePos); 				
				window.attachEvent('onunload', thisobj.UnLddTotal); 
			//} catch(e) {}
		} else {
			SLdAgnWin.addEventListener('unload', thisobj.UnLdd, true);
			Trigger.addEventListener('unload', thisobj.UnLddTotal, true);
			Trigger.addEventListener('resize', thisobj.RePos, true);
		}
		cfg.AfterBuild();
		if(cfg.RememberStatus)thisobj.StMnu();
		FrstCreat=false;
		if(cfg.MenuVerticalCentered=="static"&&!AcrssFrms)setInterval(function(){thisobj.KeepPos()},50);
		if(!ExpYes){FWinW=-1;thisobj.RePos()}
		IniFlg=true;thisobj.Initiate();
		status="Menu ready for use"}}

this.KeepPos = function(){
	var TS=ExpYes?SCmplnt?ScHtml.scrollTop:ScLoc.document.body.scrollTop:ScLoc.pageYOffset;
	if(TS!=StaticPos){
		StaticPos+=(TS-StaticPos)*.2;
		if(Math.abs(StaticPos-TS)<=1)StaticPos=TS;
		FrstCntnr.OrgTop=cfg.StartTop+StaticPos;
		FrstCntnr.style.top=FrstCntnr.mif.style.top=FrstCntnr.OrgTop+P_X}}

this.ClcRl = function() {
	cfg.StartTop=M_StrtTp<1&&M_StrtTp>0?M_StrtTp*FWinH:M_StrtTp;cfg.StartLeft=M_StrtLft<1&&M_StrtLft>0?M_StrtLft*FWinW:M_StrtLft;}

this.ClcTrgt = function(){
	var TLoc=FLoc.document.getElementById(cfg.TargetLoc);
	while(TLoc){cfg.StartTop+=TLoc.offsetTop;cfg.StartLeft+=TLoc.offsetLeft;TLoc=TLoc.offsetParent}}

this.ClcLft = function(){
	if(cfg.MenuCentered.indexOf("left")==-1){
		var Sz=FWinW-parseInt(FrstCntnr.style.width);
		cfg.StartLeft+=cfg.MenuCentered.indexOf("right")!=-1?Sz:Sz/2;if(cfg.StartLeft<0)cfg.StartLeft=0}}

this.ClcTp = function(){
	if(cfg.MenuVerticalCentered!="top"&&cfg.MenuVerticalCentered!="static"){
		var Sz=FWinH-parseInt(FrstCntnr.style.height);
		cfg.StartTop+=cfg.MenuVerticalCentered=="bottom"?Sz:0;if(cfg.StartTop<0)cfg.StartTop=0}}

this.SizeMenu = function(C){
	var Cmplnt=C.Lvl==1?FCmplnt:SCmplnt,M=C.FrstMbr,Mi,i,W=0,H=0,Wd,Ht,RI,BRW=C.Lvl==1?cfg.BorderWidthMain:2*cfg.BorderWidthSub,BTWn=C.Lvl==1?cfg.BorderBtwnMain:cfg.BorderBtwnSub;
	if(C.Lvl<=cfg.FirstLineHorizontal){
		while(M!=null){W+=Math.round((M.Arr[5]<1?(M.Arr[5]*(M.Lvl==1?FWinW:SWinW)):(M.Arr[5]*FntFc)));Mi=M;M=M.PrvMbr}
		W+=((C.NrItms-1)*BTWn+2*BRW)+cfg.LeftPaddng;
		Ht=H=Math.round((Mi.Arr[4]<1?(Mi.Arr[4]*(Mi.Lvl==1?FWinH:SWinH)):(Mi.Arr[4]*FntFc)));H+=(2*BRW)}
	else {	
		while(M!=null){H+=Math.round((M.Arr[4]<1?(M.Arr[4]*(M.Lvl==1?FWinH:SWinH)):(M.Arr[4]*FntFc)));Mi=M;M=M.PrvMbr}
		H+=((C.NrItms-1)*BTWn+2*BRW)+cfg.TopPaddng;
		Wd=W=Math.round((Mi.Arr[5]<1?(Mi.Arr[5]*(Mi.Lvl==1?FWinW:SWinW)):(Mi.Arr[5]*FntFc)));W+=(2*BRW)}
	C.style.width=C.mif.style.width=W+P_X;C.style.height=C.mif.style.height=H+P_X;
	M=C.FrstMbr;
	while(M!=null){
		if(M.Arr[5]<1){W=C.Lvl<=cfg.FirstLineHorizontal?(M.Arr[5]*(M.Lvl==1?FWinW:SWinW)):Wd;MstSz=1}
		else W=(C.Lvl<=cfg.FirstLineHorizontal?M.Arr[5]:Wd)*FntFc;
		if(M.Arr[4]<1){H=C.Lvl<=cfg.FirstLineHorizontal?Ht:(M.Arr[4]*(M.Lvl==1?FWinH:SWinH));MstSz=1}
		else H=(C.Lvl<=cfg.FirstLineHorizontal?Ht:M.Arr[4])*FntFc;
		W=Math.round(W);H=Math.round(H);
		if (C.Lvl>1)W-=2;
		if(M.value.indexOf("<")==-1) {
			M.tpd=cfg.TopPaddng<0?(Math.round(H*.5-M.Arr[12]*FntFc)):cfg.TopPaddng;if(M.tpd<0||M.tpd>H)M.tpd=0;
			M.ltp=cfg.LeftPaddng;if(M.ltp<0||M.ltp>W)M.ltp=0;
			if((NavYes&&!Opr7)||MacExp5||Cmplnt){M.lx=M.ltp;M.tx=M.tpd}}
		else
		{
			M.tpd = 0;
			M.ltp = 0;
			M.lx = 0;
			M.tx = 0;
		}
		M.style.width=W-M.lx+P_X;M.style.height=H-M.tx+P_X;
		M.style.paddingLeft=M.ltp+P_X;M.style.paddingTop=M.tpd+P_X;
		if(M.ro){RI=(M.Lvl==1?FLoc:ScLoc).document.images[M.rid];RI.style.width=W+P_X;RI.style.height=H+P_X}
		if(M.CCn)this.SizeMenu(M.CCn);
		M=M.PrvMbr}}

this.PosMenu = function(Ct,Tp,Lt){
	RLvl++;
	var a,Mb=Ct.FrstMbr,MWt=parseInt(Mb.style.width)+Mb.lx,
	MHt=parseInt(Mb.style.height)+Mb.tx,CWt=parseInt(Ct.style.width),CHt=parseInt(Ct.style.height),CCw,CCh,STp,SLt,Ti,Li,Hi,
	BRW=RLvl==1?cfg.BorderWidthMain:cfg.BorderWidthSub,BTWn=RLvl==1?cfg.BorderBtwnMain:cfg.BorderBtwnSub;
	if(RLvl==1&&AcrssFrms)!cfg.MenuFramesVertical?Tp=cfg.BottomUp?cfg.DistFrmFrameBrdr:FWinH-CHt-cfg.DistFrmFrameBrdr:Lt=cfg.RightToLeft?cfg.DistFrmFrameBrdr:FWinW-CWt-cfg.DistFrmFrameBrdr;
	if(RLvl==2&&AcrssFrms)!cfg.MenuFramesVertical?Tp=cfg.BottomUp?SWinH-CHt:0:Lt=cfg.RightToLeft?SWinW-CWt:0;
	if(RLvl==2){Tp+=cfg.VerCorrect;Lt+=cfg.HorCorrect}
	Ct.style.top=Ct.mif.style.top=RLvl==1?Tp+P_X:0;Ct.OrgTop=Tp;
	Ct.style.left=Ct.mif.style.left=RLvl==1?Lt+P_X:0;Ct.OrgLeft=Lt;
	if(RLvl<=cfg.FirstLineHorizontal){Hi=1;Li=CWt-MWt-2*BRW;Ti=0}
	else{Hi=Li=0;Ti=CHt-MHt-2*BRW}
	if (RLvl>1)Ti-=cfg.BorderWidthSub+1;
	if (RLvl>1)Li+=cfg.BorderWidthSub+1;
	while(Mb!=null){
		Mb.style.left=Li+BRW+P_X;Mb.style.top=Ti+BRW+P_X;
		if(Mb.CCn){
			if(cfg.RightToLeft)CCw=parseInt(Mb.CCn.style.width);
			if(cfg.BottomUp)CCh=parseInt(Mb.CCn.style.height);
			if(Hi){STp=cfg.BottomUp?Ti-CCh:Ti+MHt+2*BRW;SLt=cfg.RightToLeft?Li+MWt-CCw:Li}
			else{	SLt=cfg.RightToLeft?Li-CCw+cfg.ChildOverlap*MWt+BRW:Li+(1-cfg.ChildOverlap)*MWt+BRW;
				STp=RLvl==1&&AcrssFrms?cfg.BottomUp?Ti-CCh+MHt:Ti:cfg.BottomUp?Ti-CCh+(1-cfg.ChildVerticalOverlap)*MHt+2*BRW:Ti+cfg.ChildVerticalOverlap*MHt}
			this.PosMenu(Mb.CCn,STp,SLt)}
		a=RLvl<=cfg.FirstLineHorizontal?cfg.BottomUp?9:3:cfg.RightToLeft?6:0;
		if(Mb.ai){	Mb.ai.style.top=(RLvl<=cfg.FirstLineHorizontal?cfg.BottomUp?2:MHt-cfg.Arrws[a+2]-2:(MHt-cfg.Arrws[a+2])/2)+P_X;Mb.ai.style.left=(cfg.RightToLeft?2:MWt-cfg.Arrws[a+1]-2)+P_X}
		Mb=Mb.PrvMbr;
		if(Mb){MWt=parseInt(Mb.style.width)+Mb.lx;MHt=parseInt(Mb.style.height)+Mb.tx;Hi?Li-=MWt+BTWn:Ti-=MHt+BTWn}}
	RLvl--}

this.StMnu = function(){
	if(Crtd){	var i,Pntr=FrstCntnr,Str=ScLoc.SetMenu?ScLoc.SetMenu:"0";
		while(Str.indexOf("_")!=-1&&cfg.RememberStatus==1){
			i=Pntr.NrItms-parseInt(Str.substring(0,Str.indexOf("_")));Str=Str.slice(Str.indexOf("_")+1);
			Pntr=Pntr.FrstMbr;for(i;i;i--)Pntr=Pntr.PrvMbr;Pntr.OM();Pntr=Pntr.CCn}
		i=Pntr.NrItms-parseInt(Str);Pntr=Pntr.FrstMbr;
		for(i;i;i--)	Pntr=Pntr.PrvMbr;
		if(RmbrNow!=null){this.SetItem(RmbrNow,0);RmbrNow.Clckd=0}
		if(Pntr!=null){this.SetItem(Pntr,1);Pntr.Clckd=1;if(cfg.RememberStatus==1)Pntr.OM()}
		RmbrNow=Pntr;
		this.ClrAllChlds(FrstCntnr.FrstMbr);
		this.Rmbr(FrstCntnr)}}

this.Initiate = function(){
	if(IniFlg&&Ldd){
		this.Init(FrstCntnr);IniFlg=false;if(cfg.RememberStatus)Rmbr(FrstCntnr);
		if(ShwFlg)cfg.AfterCloseAll();ShwFlg=M_Clckd=false}}

this.Rmbr = function(CntPtr){
	var Mbr=CntPtr.FrstMbr,St;
	while(Mbr!=null){
		if(Mbr.DoRmbr){
			HiliteItem(Mbr);if(Mbr.CCn&&cfg.RememberStatus==1){St=Mbr.CCn.style;St.visibility=M_Show;Rmbr(Mbr.CCn)}break}
		else Mbr=Mbr.PrvMbr}}

this.Init = function(CPt){
	var Mb=CPt.FrstMbr;
	RLvl++;
	CPt.style.visibility=CPt.mif.style.visibility=RLvl==1?M_Show:M_Hide;
	CPt.Shw=RLvl==1?1:0;
	while(Mb!=null){if(Mb.Hilite)this.LowItem(Mb);if(Mb.CCn)this.Init(Mb.CCn);Mb=Mb.PrvMbr}
	RLvl--}

this.ClrAllChlds = function(Pt){
	var PSt,Pc;
	while(Pt){
		if(Pt.Hilite){
			Pc=Pt;if(Pc!=CurOvr)this.LowItem(Pt);
			if(Pt.CCn)	{PSt=Pt.CCn.style;if(Pc!=CurOvr){PSt.visibility=Pt.CCn.mif.style.visibility=M_Hide;Pt.CCn.Shw=0}this.ClrAllChlds(Pt.CCn.FrstMbr)}
			break}
		Pt=Pt.PrvMbr}}

this.SetItem = function(Pntr,x){while(Pntr!=null){Pntr.DoRmbr=x;Pntr=Pntr.Ctnr.Cllr}}

this.GoTo = function(){
	if(cfg.UnfoldsOnClick&&!M_Clckd&&this.Arr[3]){M_Clckd=true;thisobj.OpenMenuII(this)}
	else if(this.Arr[1]){
		status="";
		if(this.Arr[0].indexOf("<input")==-1) {thisobj.LowItem(this);IniFlg=true;thisobj.Initiate();}
		if(this.Arr[1].indexOf("javascript:")!=-1) eval(this.Arr[1]);
		else if(DcLoc.location.href!=cfg.BaseHref+this.Arr[1])DcLoc.location.href=cfg.BaseHref+this.Arr[1]}}
		
this.HiliteItem = function(P){
	if(P.rob)P.style.backgroundImage="url(\""+P.rib2+"\")";
	if(P.ro){var Lc=P.Lvl==1?FLoc:ScLoc;Lc.document.images[P.rid].src=P.ri2}
	else{if(P.Arr[7])P.className=P.Arr[7];}
	P.Hilite=1}

this.LowItem = function(P){
	P.Hilite=0;
	if(P.rob)P.style.backgroundImage="url(\""+P.rib1+"\")";
	if(P.ro){var Lc=P.Lvl==1?FLoc:ScLoc;Lc.document.images[P.rid].src=P.ri1}
	else{if(P.Arr[6])P.className=P.Arr[6];}}

this.OpenMenu = function(){
	if(Ldd&&Crtd){
		if(cfg.UnfoldsOnClick&&!M_Clckd){CurOvr=this;IniFlg=false;thisobj.ClrAllChlds(this.Ctnr.FrstMbr);thisobj.HiliteItem(this);status=this.Arr[8]}
		else thisobj.OpenMenuII(this)}}

this.OpenMenuII = function(Ptr){
	if(OpnTmr)clearTimeout(OpnTmr);
	if(Opr7&&Ptr.Lvl==1&&cfg.MenuUsesFrames&&Ptr.CCn){
		var Childs=ScLoc.document.body.children,x=0;
		if(Childs!=null){for(i=0;i<Childs.length;i++){if(Childs.item(i)==Ptr.CCn){x=1;break}}}
		if(!x)Ptr.CCn=null}
	if(Ptr.Arr[3]&&!Ptr.CCn){
		RLvl=Ptr.Lvl;
		Ptr.CCn=thisobj.CreateMenuStructure(Ptr.MN+"_",Ptr.Arr[3],Ptr);
		this.SizeMenu(Ptr.CCn);
		var Ti,Li,Hi,CCw,CCh,STp,SLt,
		MWt=parseInt(Ptr.style.width)+Ptr.lx,MHt=parseInt(Ptr.style.height)+Ptr.tx,BRW=RLvl==1?cfg.BorderWidthMain:cfg.BorderWidthSub;
		if(cfg.RightToLeft)CCw=parseInt(Ptr.CCn.style.width);
		if(cfg.BottomUp)CCh=parseInt(Ptr.CCn.style.height);
		if(RLvl<=cfg.FirstLineHorizontal){Hi=1;Li=parseInt(Ptr.style.left)-BRW;Ti=0}
		else{Hi=Li=0;Ti=parseInt(Ptr.style.top)-BRW}
		if(Hi){STp=cfg.BottomUp?Ti-CCh:Ti+MHt+2*BRW;SLt=cfg.RightToLeft?Li+MWt-CCw:Li}
		else{	SLt=cfg.RightToLeft?Li-CCw+cfg.ChildOverlap*MWt+BRW:Li+(1-cfg.ChildOverlap)*MWt;
			STp=RLvl==1&&AcrssFrms?cfg.BottomUp?Ti-CCh+MHt:Ti:cfg.BottomUp?Ti-CCh+(1-cfg.ChildVerticalOverlap)*MHt+2*BRW:Ti+cfg.ChildVerticalOverlap*MHt+BRW}
		this.PosMenu(Ptr.CCn,STp,SLt);
		RLvl=0}
	CurOvr=Ptr;
	IniFlg=false;thisobj.ClrAllChlds(Ptr.Ctnr.FrstMbr);
	if(!Ptr.Hilite)thisobj.HiliteItem(Ptr);
	//if(Exp6Plus){FStr="";if(MenuShadow)FStr+=MenuShadow;if(FStr!="")Ptr.filter=FStr}
	if(Ptr.CCn!=null&&!Ptr.CCn.Shw)cfg.RememberStatus?thisobj.Unfld():OpnTmr=setTimeout(function(){thisobj.Unfld()},cfg.UnfoldDelay);
	status=Ptr.Arr[8]}

this.Unfld = function(){
	var P=CurOvr,CSt=P.Ctnr.style,CCnt=P.CCn,CCSt=CCnt.style,CCmSt=CCnt.mif.style,
	THt=parseInt(P.style.height),TWt=parseInt(P.style.width),TLt=AcrssFrms&&P.Lvl==1&&!cfg.FirstLineHorizontal?0:parseInt(CSt.left),
	TTp=AcrssFrms&&P.Lvl==1&&cfg.FirstLineHorizontal?0:parseInt(CSt.top),CCW=parseInt(CCSt.width),CCH=parseInt(CCSt.height),
	TS=ExpYes?SCmplnt?ScHtml.scrollTop:ScLoc.document.body.scrollTop:ScLoc.pageYOffset,
	LS=ExpYes?SCmplnt?ScHtml.scrollLeft:ScLoc.document.body.scrollLeft:ScLoc.pageXOffset,
	SLt=AcrssFrms&&P.Lvl==1?CCnt.OrgLeft+TLt+LS:CCnt.OrgLeft+TLt,	STp=AcrssFrms&&P.Lvl==1?	CCnt.OrgTop+TTp+TS:CCnt.OrgTop+TTp;
	if(!ShwFlg){ShwFlg=true;cfg.BeforeFirstOpen()}
	if(cfg.MenuWrap){
		if(cfg.RightToLeft){if(SLt<LS)SLt=P.Lvl==1?LS:SLt+(CCW+(1-2*cfg.ChildOverlap)*TWt);if(SLt+CCW>SWinW+LS)SLt=SWinW+LS-CCW}
		else{if(SLt+CCW>SWinW+LS)SLt=P.Lvl==1?SWinW+LS-CCW:SLt-(CCW+(1-2*cfg.ChildOverlap)*TWt);if(SLt<LS)SLt=LS}
		if(cfg.BottomUp){if(STp<TS)STp=P.Lvl==1?TS:STp+(CCH-(1-2*cfg.ChildVerticalOverlap)*THt);if(STp+CCH>SWinH+TS)STp=SWinH+TS-CCH+0}
		else{if(STp+CCH>TS+SWinH)STp=P.Lvl==1?STp=TS+SWinH-CCH:STp-CCH+(1-2*cfg.ChildVerticalOverlap)*THt;if(STp<TS)STp=TS}}
	CCSt.top=CCmSt.top=STp+P_X;CCSt.left=CCmSt.left=SLt+P_X;
	if(Exp6Plus&&cfg.MenuSlide){CCnt.filters[0].Apply();CCnt.filters[0].play()}
	CCSt.visibility=CCmSt.visibility=M_Show}

this.CloseMenu = function()
{
	if(Ldd&&Crtd){status="";
	if(this==CurOvr) {
		if(OpnTmr)clearTimeout(OpnTmr);
		if(CloseTmr)clearTimeout(CloseTmr);
		IniFlg=true;
		CloseTmr=setTimeout(function(){thisobj.Initiate(CurOvr)},cfg.DissapearDelay)
	}}}

this.CntnrSetUp = function(NoOff,WMu,Mc){
	var x=eval(WMu+"[8]")!=""?eval(WMu+"[8]"):cfg.ContainerClass,TS=this.style;
	this.Lvl=RLvl;this.FrstMbr=null;this.NrItms=NoOff;this.Cllr=Mc;this.Shw=0;this.OrgLeft=this.OrgTop=0;
	if(x&&RLvl>1)this.className=x;
	TS.zIndex=RLvl+Ztop;if(ExpYes&&!MacExp5)this.mif.style.zIndex=TS.zIndex-1;
	if(Nav_Op&&!Opr7&&cfg.MenuOpacity)TS.MozOpacity=parseInt(cfg.MenuOpacity.substring(cfg.MenuOpacity.indexOf("=")+1,cfg.MenuOpacity.indexOf(")")))/105;
	if(Exp6Plus)
	{
		if (RLvl>1) {
			FStr="";
			if(cfg.MenuSlide)FStr=cfg.MenuSlide;
			//if(DropMenuShadow)FStr+=DropMenuShadow;
			if(cfg.MenuOpacity)FStr+=cfg.MenuOpacity;
			if(FStr!="")TS.filter=FStr
			//TS.filter="";
		}
	}
	if(ExpYes&&!MacExp5&&cfg.OverFormElements)this.mif.style.filter="Alpha(Opacity=0)"}

this.MbrSetUp = function(MbC,PrMmbr,WMu){
	var Lctn=RLvl==1?FLoc:ScLoc,t,a,ta2=this.Arr[2],Tfld=this.Arr[0];
	this.PrvMbr=PrMmbr;this.Lvl=RLvl;this.Ctnr=MbC;this.CCn=this.ai=null;this.Hilite=this.DoRmbr=this.Clckd=this.ro=this.rob=this.lx=this.tx=this.ltp=this.tpd=0;
	this.OM=this.OpenMenu;this.MN=WMu;this.style.overflow="hidden";
	this.style.cursor=(this.Arr[1]||(RLvl==1&&cfg.UnfoldsOnClick))?ExpYes?"hand":"pointer":"default";
	if(Tfld.indexOf("rollover")!=-1){
		this.ro=1;this.ri1=Tfld.substring(Tfld.indexOf("?")+1,Tfld.lastIndexOf("?"));this.ri2=Tfld.substring(Tfld.lastIndexOf("?")+1,Tfld.length);this.rid=WMu+"i";
		Tfld="<img src=\""+this.ri1+"\" name=\""+this.rid+"\">"}
	this.value=Tfld;
	if(!this.Arr[6])this.Arr[6]=RLvl==1?cfg.MainClass:cfg.SubClass;
	if(!this.Arr[7])this.Arr[7]=RLvl==1?cfg.MainHoverClass:cfg.SubHoverClass;
	this.className=this.Arr[6];

	if(!this.Arr[8])this.Arr[8]=this.Arr[1];
	if(ta2){	if(ta2.indexOf("rollover")==-1)this.style.backgroundImage="url(\""+ta2+"\")";
		else{	this.rob=1;this.rib1=ta2.substring(ta2.indexOf("?")+1,ta2.lastIndexOf("?"));this.rib2=ta2.substring(ta2.lastIndexOf("?")+1,ta2.length);
			this.style.backgroundImage="url(\""+this.rib1+"\")";
			this.style.backgroundRepeat="no-repeat";
			this.style.backgroundPosition=cfg.BgImgLeftOffset+"px"}}
	if(Tfld.indexOf("<")==-1){t=Lctn.document.createTextNode(Tfld);this.appendChild(t)}
	else this.innerHTML=Tfld;
	if(this.Arr[3]){
		a=RLvl<=cfg.FirstLineHorizontal?cfg.BottomUp?9:3:cfg.RightToLeft?6:0;
		if(cfg.Arrws[a]){
			t=Lctn.document.createElement("img");this.appendChild(t);
			t.style.position="absolute";t.src=cfg.Arrws[a];t.style.width=cfg.Arrws[a+1]*FntFc+P_X;t.style.height=cfg.Arrws[a+2]*FntFc+P_X;this.ai=t}}
	if(ExpYes){this.onselectstart=thisobj.CnclSlct;this.onmouseover=thisobj.OpenMenu;this.onmouseout=thisobj.CloseMenu;this.onclick=thisobj.GoTo}
	else{this.addEventListener("mouseover",thisobj.OpenMenu,false);	this.addEventListener("mouseout",thisobj.CloseMenu,false);
		this.addEventListener("click",thisobj.GoTo,false)}
}

this.CreateMenuStructure = function(MNm,No,Mcllr){
	RLvl++;
	var i,Mbr,PMb=null,WMnu=MNm+"1",MWd=eval(WMnu+"[5]")*FntFc,MHt=eval(WMnu+"[4]")*FntFc,
	Lctn=RLvl==1?FLoc:ScLoc,MbC=Lctn.document.createElement("div"),MIF=!ExpYes||MacExp5||!cfg.OverFormElements?MbC:Lctn.document.createElement("iframe");
	Lctn.document.body.appendChild(MbC);
	//if (cfg.TargetLoc)Lctn.document.getElementById(cfg.TargetLoc).appendChild(MbC);else Lctn.document.body.appendChild(MbC);
	if(ExpYes&&!MacExp5&&cfg.OverFormElements){MIF.src="/script/Menu/blank.html";Lctn.document.body.appendChild(MIF)}
	MbC.style.position=MIF.style.position="absolute";
	MbC.style.visibility=MIF.style.visibility=M_Hide;
	MbC.mif=MIF;
	MbC.SetUp=this.CntnrSetUp;MbC.SetUp(No,MNm+"1",Mcllr);
	for(i=1;i<No+1;i++){
		WMnu=MNm+eval(i);
		Mbr=Lctn.document.createElement("div");Mbr.style.position="absolute";Mbr.style.visibility="inherit";MbC.appendChild(Mbr);
		Mbr.Arr=eval(WMnu);if(!Mbr.Arr[4])Mbr.Arr[4]=MHt;if(!Mbr.Arr[5])Mbr.Arr[5]=MWd;
		Mbr.SetUp=this.MbrSetUp;Mbr.SetUp(MbC,PMb,WMnu, (MNm=="cfg.Menu"));
		if(Mbr.Arr[3]&&!cfg.BuildOnDemand){Mbr.CCn=this.CreateMenuStructure(WMnu+"_",Mbr.Arr[3],Mbr)}
		PMb=Mbr}
	MbC.FrstMbr=Mbr;
	RLvl--;return(MbC)}

this.CreateMenuStructureAgain = function(MNm,No){
	var Mbr=FrstCntnr.FrstMbr,i,WMnu;
	if(!cfg.BuildOnDemand)	{RLvl++;for(i=No;i>0;i--){WMnu=MNm+eval(i);if(Mbr.Arr[3])Mbr.CCn=this.CreateMenuStructure(WMnu+"_",Mbr.Arr[3],Mbr);Mbr=Mbr.PrvMbr}RLvl--}
	else{while(Mbr){Mbr.CCn=null;Mbr=Mbr.PrvMbr}}}

}