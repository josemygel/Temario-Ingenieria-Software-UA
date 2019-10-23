function menu_NavbarConfig() {

this.version = "3.21"
this.MenuName = "Nav Bar";

this.BaseHref="";

this.MainHoverClass="NavBarMainHover";
this.MainClass="NavBarMain";
this.SubHoverClass="NavBarSubHover";
this.SubClass="NavBarSub";
this.ContainerClass="NavBarCont";

this.MenuCentered="left";
this.MenuVerticalCentered="top";
this.PartOfWindow=0.7;

this.BorderWidthMain=2;
this.BorderWidthSub=2;
this.BorderBtwnMain=3;
this.BorderBtwnSub=1;

this.StartTop=0;
this.StartLeft=0;
this.VerCorrect=1;
this.HorCorrect=0;
this.LeftPaddng=5;
this.RightPadding=5;
this.TopPaddng=2;
this.FirstLineHorizontal=1;
this.TargetLoc="MenuPos";

this.BgImgLeftOffset=5;
this.ScaleMenu=0;
this.DistFrmFrameBrdr=0;

this.DissapearDelay=300;
this.UnfoldDelay=200;
this.ChildOverlap=0;
this.ChildVerticalOverlap=0;
this.BuildOnDemand=0;
this.OverFormElements=1;

this.BottomUp=0;
this.MenuWrap=1;
this.RightToLeft=0;
this.UnfoldsOnClick=0;
this.RememberStatus=0;

this.TakeOverBgColor=1;
this.FirstLineFrame="";
this.SecLineFrame="";
this.DocTargetFrame="";
this.MenuFramesVertical=1;
this.MenuUsesFrames=0;

this.MenuSlide="";
this.MenuOpacity=""; //"progid:DXImageTransform.Microsoft.Alpha(opacity=85)";
//this.DropMenuShadow="progid:DXImageTransform.Microsoft.Shadow(color=#999999, direction=135, strength=3)";
//this.MainMenuFilter="progid:DXImageTransform.Microsoft.Shadow(color=#000000, direction=135, strength=1)";

this.BeforeStart = function(){return;};
this.AfterBuild = function(){return;};
this.BeforeFirstOpen = function(){return;};
this.AfterCloseAll = function(){return;};

this.Arrws=[this.BaseHref+"",,,this.BaseHref+"",,,this.BaseHref+"",,,this.BaseHref+"",,];

this.NoOffFirstLineMenus=5;

this.Menu1=new Array("Help!","/info/faq.aspx","",5,18,45,"","","");
	this.Menu1_1=new Array("What is 'The Code Project'?","/info/Guide.aspx","",0,18,190,"","","");
	this.Menu1_2=new Array("General FAQ","/info/faq.aspx","",0,18,190,"","","");
	this.Menu1_3=new Array("Post a Question","/script/Forums/List.aspx","",0,18,190,"","","");
	this.Menu1_4=new Array("Site Directory","/script/Content/SiteMap.aspx","",0,18,190,"","","");
	this.Menu1_5=new Array("About Us","/info/about.aspx","",0,18,190,"","","");
this.Menu2=new Array("Articles","/info/latest.aspx","",8,18,60,"","","");
	this.Menu2_1=new Array("Latest","/script/articles/latest.aspx","",0,18,150,"","","");	
	this.Menu2_2=new Array("Search","/info/search.aspx","",0,18,150,"","","");		
	this.Menu2_3=new Array("Most Popular","/script/articles/TopArticles.aspx","",0,18,150,"","","");	
	this.Menu2_4=new Array("Beginner Articles","/info/search.aspx?vidlst=152&sa_us=True","",0,18,150,"","","");	
	this.Menu2_5=new Array("Topic List","/script/Content/SiteMap.aspx","",0,18,190,"","","");
	this.Menu2_6=new Array("Submit an Article","/info/submit.aspx","",0,18,150,"","","");
	this.Menu2_7=new Array("Update an Article","/info/submit.aspx#Update","",0,18,150,"","","");
	this.Menu2_8=new Array("Article Competition","/script/awards/CurrentCompetitions.aspx","",0,18,150,"","","");
this.Menu3=new Array("Message Boards","/script/Forums/List.aspx","",20,18,120,"","","");
	this.Menu3_1=new Array("ASP.NET","/script/Forums/View.aspx?fid=12076","",0,18,175,"","","");
	this.Menu3_2=new Array("ATL / WTL / STL","/script/Forums/View.aspx?fid=4486","",0,18,175,"","","");
	this.Menu3_3=new Array("C++ / MFC","/script/Forums/View.aspx?fid=1647","",0,18,175,"","","");
	this.Menu3_4=new Array("(Managed) C++/CLI","/script/Forums/View.aspx?fid=3785","",0,18,175,"","","");
	this.Menu3_5=new Array("C#","/script/Forums/View.aspx?fid=1649","",0,18,175,"","","");
	this.Menu3_6=new Array("COM","/script/Forums/View.aspx?fid=1648","",0,18,175,"","","");
	this.Menu3_7=new Array("Hardware","/script/Forums/View.aspx?fid=186301","",0,18,175,"","","");
	this.Menu3_8=new Array("LINQ and .NET 3.5","/script/Forums/View.aspx?fid=1004117","",0,18,175,"","","");
	this.Menu3_9=new Array(".NET Framework","/script/Forums/View.aspx?fid=1650","",0,18,175,"","","");
	this.Menu3_10=new Array("OS / SysAdmin","/script/Forums/View.aspx?fid=1644","",0,18,175,"","","");
	this.Menu3_11=new Array("Silverlight","/script/Forums/View.aspx?fid=1004257","",0,18,175,"","","");
	this.Menu3_12=new Array("SQL and Database","/script/Forums/View.aspx?fid=1725","",0,18,175,"","","");
	this.Menu3_13=new Array("VB.NET","/script/Forums/View.aspx?fid=1646","",0,18,175,"","","");
	this.Menu3_14=new Array("Web Development","/script/Forums/View.aspx?fid=1640","",0,18,175,"","","");
	this.Menu3_15=new Array("WPF / WCF / WF","/script/Forums/View.aspx?fid=1004114","",0,18,175,"","","");
	this.Menu3_16=new Array("XML / XSL","/script/Forums/View.aspx?fid=3421","",0,18,175,"","","");
	this.Menu3_17=new Array("General Discussions","/script/Forums/View.aspx?fid=1642","",0,18,175,"","","");
	this.Menu3_18=new Array("Suggestions / Site Bugs","/script/Forums/View.aspx?fid=1645","",0,18,175,"","","");
	this.Menu3_19=new Array("The Soapbox","/script/Forums/View.aspx?fid=2605","",0,18,175,"","","");
	this.Menu3_20=new Array("All Message Boards...","/script/Forums/List.aspx","",0,18,175,"","","");
this.Menu4=new Array("Job Board","/script/Jobs/","",0,18,80,"","","");
this.Menu5=new Array("Lounge","/Lounge.aspx","",0,18,60,"","","");
}