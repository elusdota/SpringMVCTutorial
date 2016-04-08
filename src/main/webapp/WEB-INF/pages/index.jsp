<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<c:url value='/js/ztree/css/metroStyle/metroStyle.css'/>"
	type="text/css">
<link rel="stylesheet" href="<c:url value='/css/index.css'/>"
	type="text/css">
<script src="<c:url value='/js/jquery.js'/>"></script>
<script src="<c:url value='/js/ztree/js/jquery.ztree.core-3.5.js'/>"></script>
<title>Insert title here</title>
</head>
<body>
	<TABLE border=0 height=600px align=left>
		<TR>
			<TD width=260px align=left valign=top
				style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width: 260px; overflow: auto;"></ul>
			</TD>
			<TD width=1030px align=left valign=top>
				<div id="Tab">
					<div class="Menubox">
						<ul>
							<li id="menu1" onmouseover="setTab('menu',1,2)" class="hover">未审计</li>
							<li id="menu2" onmouseover="setTab('menu',2,2)">已审计</li>
						</ul>
					</div>
					<div class="Contentbox">
						<div id="con_menu_1" style="display: none">
							<ul>
								<div>
									<button type="button" class="btn btn-default" id="excel">导出Excel</button>
									<button type="button" class="btn btn-default" id="pdf">导出PDF</button>
									<button type="button" class="btn btn-default" id="docx">导出DOCX</button>
									<button type="button" class="btn btn-default" id="print">打印</button>
									<IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0
										SCROLLING=AUTO width=100% height=600px SRC="home"></IFRAME>
								</div>
							</ul>
						</div>
						<div id="con_menu_2" style="display: none">
							<ul>
								<div>
									<button type="button" class="btn btn-default" id="excel1">导出Excel</button>
									<button type="button" class="btn btn-default" id="pdf1">导出PDF</button>
									<button type="button" class="btn btn-default" id="docx1">导出DOCX</button>
									<button type="button" class="btn btn-default" id="print1">打印</button>
									<IFRAME ID="testIframe1" Name="testIframe1" FRAMEBORDER=0
										SCROLLING=AUTO width=100% height=600px SRC="home"></IFRAME>
								</div>
							</ul>
						</div>
					</div>
				</div>

			</TD>
		</TR>
	</TABLE>
</body>
<script src="<c:url value='/js/report/index-service.js'/>"></script>
</html>