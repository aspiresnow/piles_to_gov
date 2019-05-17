<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- #assign指定和修改全局变量 ， #local指定和修改局部变量 -->

	<#--使用 !给设置默认值-->
	<h1>Welcome ${user!"visitor"}</h1>
	<!--使用??判断是否存在,使用()括住变量，可以避免父路径不存在报错--> 
	<#if (user.username)??><h1>Welcome ${user}!</h1></#if>
	<#list users as user>
	<ul>
		<input type ="text" value = "${user.username?upper_case}"/>
		username : ${user.username}
		<br /> password : ${user.password}
		<br />
		<#--没有被引号引用或者使用()括起来的默认为变量--> 
		<#if user.username=="lisi">尼古拉斯小四 <#elseif user.username=="zhangsan">尼古拉斯小三
		<#else>xx </#if> <br />
		<!-- #assign指定一个变量 -->
		<#assign s = "Hello ${user.username}!">
		<!-- freemarker指令中不能再次含指令，除非在引号里面，在指令里面直接使用变量值 -->
		<#assign ss = "Hello "+user.username+"!">
		${s}&nbsp;
		<!-- 获取字符 -->
		${ss[0]}
	</ul>
	</#list>
	
	<#--避免list为空时输出一个ul-->
	<#list users>
	<ul>
		<#items as user>
			username : ${user.username} <br />
			password : ${user.password} <br />
			<#--没有被引号引用或者使用()括起来的默认为变量--> 
			<#if user.username=="lisi">尼古拉斯小四 <#elseif user.username=="zhangsan">尼古拉斯小三<#else>xx</#if> <br/>
		</#items>
	</ul>
	</#list>
	
	<#--使用#sep连接多个值-->
	<#list users as user>${user.username}<#sep>~ </#list><br />
	<#assign str = "ABCDEF">
	${str[2..3]} &nbsp;
	${str[2..<4]} &nbsp;
	${str[2..*3]} &nbsp;
	${str[2..*100]} &nbsp;
	${str[2..]} &nbsp;
	<br/>
	<!-- 取整 -->
	${-1.1?int}<br/>
	<!-- 加上 ! 避免报错，取默认值 -->
	${mouse!}<br/>
	<#assign mouse = "Jerry">
	${mouse!}<br/>
	
	<!-- 自定义指令 -->
	<#macro greet>
  		<font size="+2">Hello Joe!</font>
	</#macro>
	<@greet/><br/>
	
	<!-- 多个参数 -->
	<#macro greet person time>
  		<font size="+2">Hello ${person}! 在 ${time}</font>
	</#macro>
	<@greet person="尼古拉斯赵四" time ="2017-02-07"/><br/>
	
	<!-- 带有默认值的参数,存在默认值的参数必须放在最后一位 -->
	<#macro greet time person="小黑">
  		<font size="+2">Hello ${person}! 在 ${time}</font>
	</#macro>
	<@greet time="2017-05-21"/><br/>
	
	<!-- 带内容的指令 -->
	<#macro border>
	    <table border=4 cellspacing=0 cellpadding=4><tr><td>
		<#nested>
		</tr></td></table>
	</#macro>
	<@border>The bordered text</@border><br/>
	
	<!-- 执行多次 -->
	<#macro do_thrice>
	  <#nested>
	  <#nested>
	  <#nested>
	</#macro>
	
	<#macro greet person>
  		<font size="+2">Hello ${person}!</font>
	</#macro>
	<@border>
	  	<ul>
		  	<@do_thrice>
		    	<li><@greet person="Joe"/>
		  	</@do_thrice>
	  	</ul>
	</@border>
	
	<#macro repeat count>
	  <#local y = "test">
	  <#list 1..count as x>
	    ${y} ${count}/${x}: <#nested>
	  </#list>
	</#macro>
	<@repeat count=3>${y!"?"} ${x!"?"} ${count!"?"}</@repeat>
	<br/>
	
	<#macro repeat count>
	  <#list 1..count as x>
	  	<!-- #nested 上的参数 -->
	    <#nested x, x/2, x==count>
	  </#list>
	</#macro>
	  	<!-- 使用#nested 上的参数  ;隔开 各个参数之间用 , 分割-->
	<@repeat count=4 ; c, halfc, last>
	  ${c}. ${halfc}<#if last> Last!</#if><br/>
	</@repeat>
	
	<#assign s = "R&D">
	${s?ends_with("a")?string}
	
</body>
</html>

