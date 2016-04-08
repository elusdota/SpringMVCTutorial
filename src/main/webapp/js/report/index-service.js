/**
 * 
 */
var zTree;
var demoIframe;
var demoIframe1;
var codeState;
var newURL = window.location.protocol + "//" + window.location.host + "/";
var url = window.location.pathname.split('/')[1];
var setting = {
	view : {
		dblClickExpand : false,
		showLine : true,
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : ""
		}
	},
	callback : {
		beforeClick : function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				codeState = treeNode.pId;
				demoIframe.attr("src", newURL + url + "/reports/"
						+ treeNode.file + "?code=" + treeNode.pId + "&state="
						+ false);
				demoIframe1.attr("src", newURL + url + "/reports/"
						+ treeNode.file + "?code=" + treeNode.pId + "&state="
						+ true);
				return true;
			}
		}
	}
};
var zNodes = [ {
	id : 1,
	pId : 0,
	name : "昆明傣灵民族乐器销售有限公司",
	open : false
}, {
	id : 10,
	pId : 1,
	name : "底稿",
	open : false
}, {
	id : 1001,
	pId : 10,
	name : "利润表",
	file : "Profit"
}, {
	id : 1002,
	pId : 10,
	name : "现流表",
	file : "CashFlow"
}, {
	id : 1003,
	pId : 10,
	name : "资产负债",
	file : "AssetLiabilities"
}, {
	id : 1004,
	pId : 10,
	name : "资产负债（续）",
	file : "AssetLiabilitiesContinued"
}, {
	id : 2,
	pId : 0,
	name : "昆明旭函工贸有限公司",
	open : false
}, {
	id : 11,
	pId : 2,
	name : "底稿",
	open : false
}, {
	id : 1101,
	pId : 11,
	name : "利润表",
	file : "Profit"
}, {
	id : 1102,
	pId : 11,
	name : "现流表",
	file : "CashFlow"
}, {
	id : 1103,
	pId : 11,
	name : "资产负债",
	file : "AssetLiabilities"
}, {
	id : 1104,
	pId : 11,
	name : "资产负债（续）",
	file : "AssetLiabilitiesContinued"
} ];
$(document).ready(function() {
	var t = $("#tree");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	demoIframe.bind("load", loadReady);
	demoIframe1 = $("#testIframe1");
	demoIframe1.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id", 101));

	exportclick();

});
function loadReady() {
	var bodyH = demoIframe.contents().find("body").get(0).scrollHeight, htmlH = demoIframe
			.contents().find("html").get(0).scrollHeight, maxH = Math.max(
			bodyH, htmlH), minH = Math.min(bodyH, htmlH), h = demoIframe
			.height() >= maxH ? minH : maxH;
	if (h < 530)
		h = 600;
	else
		h = 700;
	demoIframe.height(h);
	var bodyH1 = demoIframe1.contents().find("body").get(0).scrollHeight, htmlH1 = demoIframe1
			.contents().find("html").get(0).scrollHeight, maxH = Math.max(
			bodyH1, htmlH1), minH1 = Math.min(bodyH1, htmlH1), h1 = demoIframe1
			.height() >= maxH ? minH : maxH;
	if (h1 < 530)
		h1 = 600;
	else
		h1 = 700;
	demoIframe1.height(h1);
};
function setTab(name, cursel, n) {
	for (i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "hover" : "";
		con.style.display = i == cursel ? "block" : "none";
	}
}
function exportclick() {
	$("#excel").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					window.location.href = "export?filename=" + urliframe
							+ "&type=xls" + "&code=" + codeState + "&state="
							+ false;
				}
			});
	$("#pdf").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					window.location.href = "export?filename=" + urliframe
							+ "&type=pdf" + "&code=" + codeState + "&state="
							+ false;
				}
			});
	$("#docx").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					window.location.href = "export?filename=" + urliframe
							+ "&type=docx" + "&code=" + codeState + "&state="
							+ false;
				}
			});
	$("#excel1").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					window.location.href = "export?filename=" + urliframe
							+ "&type=xls" + "&code=" + codeState + "&state="
							+ true;
				}
			});
	$("#pdf1").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					window.location.href = "export?filename=" + urliframe
							+ "&type=pdf" + "&code=" + codeState + "&state="
							+ true;
				}
			});
	$("#docx1").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					window.location.href = "export?filename=" + urliframe
							+ "&type=docx" + "&code=" + codeState + "&state="
							+ true;
				}
			});
	$("#print").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					demoIframe.attr("src", newURL + url
							+ "/reports/print?filename=" + urliframe
							+ "&type=print" + "&code=" + codeState + "&state="
							+ false);
					// window.location.href = "print?filename=" +
					// urliframe
					// + "&type=print" + "&code=" + codeState;
				}
			});
	$("#print1").click(
			function() {
				var a = document.createElement('a');
				a.href = $('iframe').attr('src');
				var urliframe = a.pathname.split('/')[3];
				if (urliframe == 'home' || urliframe == 'homeCompleted') {
					alert('请选择底稿！');
				} else {
					demoIframe1.attr("src", newURL + url
							+ "/reports/print?filename=" + urliframe
							+ "&type=print" + "&code=" + codeState + "&state="
							+ true);
					// window.location.href = "print?filename=" + urliframe
					// + "Completed" + "&type=print" + "&code="
					// + codeState;
				}
			});
}