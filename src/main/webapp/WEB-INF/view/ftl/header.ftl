<!DOCTYPE html>
<html lang="cn">
<head>
<#assign ctx = pageContext.contextPath>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="${ctx}/css/stpt/formalize.css" />
<link rel="stylesheet" href="${ctx}/css/stpt/reset.css" />
<link rel="stylesheet" href="${ctx}/css/stpt/page.css" />
<link rel="stylesheet" href="${ctx}/css/stpt/default/imgs.css" />
<!--[if IE 6.0]>
<script src="${ctx}/js/stpt/iepng.js" type="text/javascript"></script>
<script type="text/javascript">
    EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
</script>
<![endif]-->
<script src="${ctx}/js/stpt/html5.js"></script>
<script src="${ctx}/js/stpt/jquery.js"></script>
</head>

<body>
<header class="mw1002">
    <div class="logo clearfix">
        <!--Color-->
        <div class="color"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','${ctx}/css/stpt/default/images/index_1_04_open.png',1)"><img src="${ctx}/css/stpt/default/images/index_1_04_close.png" name="Image1" width="31" height="42" border="0"></a></div>
        <!--Color End-->
        <!---->
        <ul>
            <li class="selected"><a href="javascript:void(0);" class="home" title="首页">首页</a></li>
            <li><a href="/portal/logout.jsp"  target="_top" class="logout" title="退出">退出</a></li>
        </ul>
        <!---->
        <!--User-->
        <div class="user"><b>admin</b><span id="wc">&nbsp;&nbsp;欢迎登录&nbsp;</span></div>
        <!--User End-->
    </div>
    <!--Logo End-->
    <nav>
        <ul>

            <li ><a style="cursor:pointer;" href="javascript:void(0);" target="mainFrame"><span>代办事项</span></a></li>

            <li ><a style="cursor:pointer;" href="${ctx}/theme/themes" target="mainFrame"><span>竞赛名称</span></a></li>

            <li ><a style="cursor:pointer;" href="${ctx}/match/matches"  target="mainFrame"><span>竞赛专项主题</span></a></li>

            <li><a style="cursor:pointer;" href="${ctx}/rule/rules" target="mainFrame"><span>奖项规则</span></a></li>

        </ul>


    </nav>
</header>
</body>
</html>
