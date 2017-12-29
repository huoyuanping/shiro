<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>layout.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
     <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<script type="text/javascript" src="jquery.min.js"></script>
	<script type="text/javascript" src="jquery.easyui.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<script type="text/javascript">
		function urlClick(myTitle,myUrl){
			//判断title= '学生管理' tab页是否存在
			var ifExist=$('#myTable').tabs("exists",myTitle);
			//判断不存在的时候添加选项卡
			if(!ifExist){
				// 添加一个选项卡面板  closable:true可关闭的   scrolling="no"去除滚动条
				$('#myTable').tabs('add',{
					title: myTitle,
					closable:true,
					selected:true,
					content:'<iframe frameborder=0 width=100% height=100% src="'+myUrl+'" scrolling="no"></iframe>'
				});
			}
			// 选择一个选项卡面板
			$('#myTable').tabs("select",myTitle);
			
		}
	</script>
  </head>
  
  <body>
    <div style="margin:1px ;padding:1px"></div>
	<div class="easyui-layout" style="width:100%;height:100%">
	<!-- 北（导航栏只能设置高度） 一般不会设置宽度-->
		<div data-options="region:'north'" style="height:10%;background:url(top.png)no-repeat; background-size:100%;">
			<div style="height: 80%"></div>
			<div style="text-align: right;width: 95%"><a href="#" style="text-decoration: none;">安全退出</a></div>
		</div>
		
		<div data-options="region:'west',split:true" title="导航菜单" style="width:17%;">
			<div class="easyui-accordion" style="width:500px;height:400px;">
				<div title="权限管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
					<c:forEach var="menu" items="${requestScope.menuList}">
						<a href="javascript:urlClick('${menu.menuName}','${pageContext.request.contextPath}${menu.menuUrl}')"  style="text-decoration: none;"><img src="themes/icons/man.png"/>${menu.menuName}</a><br/>
					</c:forEach>
				</div>
				<div title="系统设置" data-options="iconCls:'icon-setup'" style="padding:10px;">
				</div>
		
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
		<!-- 添加选项卡的空件 -->
			<div id="myTable" class="easyui-tabs" style="width:100%;height:100%">
				<!-- 添加选项卡
					data-options="closable:true" 可关闭的标签页
				 -->
				<div title="欢迎使用" style="padding:10px">
					<img src="h.jpg" style="width:100%;height:100%">
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
