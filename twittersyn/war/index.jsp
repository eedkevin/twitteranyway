<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" language="java" %>
<%@page import="java.util.*" %>
<%@page import="com.allenzheng.twittersyn.*" %>
<%@page import="com.allenzheng.twittersyn.common.*" %>
<%request.setCharacterEncoding("utf-8"); %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>Twitter Anyway for Allen Zheng</title>
	<style type="text/css">
        body {margin:0 auto; padding:0 auto; width:460px;}
        .mainFrame{width:450px; height:auto; margin:80px 0px 0px 0px; padding:10px; border:solid 1px #78a3d3; background-color:#f5f8fc;}
        .mainLine{width:450px; height:1px; background-color:#9c9c9c; font-size:1px; margin-top:3px;}
        .userName{color:#5FA207; font-weight:bold;}
        .checkLbl{font-size:14px; margin-left:15px}
        .explain{font-size:12px; color:#6c6c6c;}
        .explain a{color:#5FA207; text-decoration:none;}
        .explain a:hover{color:#5FA207; text-decoration:underline;}
    </style>
</head>
	
<body>
	<form action="/login" method="post">
		<div class="mainFrame">
			<div style="font-size:14px; font-weight:bold; color:#000;">
        		<span>
			 		Twitter Anyway for Allen Zheng
				</span>
				<span style="font-size:12px; font-weight:normal; color:#6c6c6c; display:inline; margin-left:120px;">
					最近更新：2010-12-8 00:00
		    	</span>
			</div>
			<div class="mainLine"></div>
			
			<table width="100%" cellpadding="0" cellspacing="5" border="0" style="margin-top:5px;">
				<tr>
					<td>
						<p>用户名：</p>
					</td>
					<td>
						<input type="text" id="useerName" name="userName"/>
					</td>
				</tr>
				<tr>
					<td>
						<p>密码：</p>
					</td>
					<td>
						<input type="password" id="passWd" name="passWd"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="登录"/>
					</td>
				</tr>
			</table>
			
			<div class="explain">
            <p>说明：1、本工具提供同步更新<a href="http://twitter.com" target="_blank">Twitter</a>、
            	<a href="http://t.sina.com.cn" target="_blank">新浪微博</a>、
            	<a href="http://www.renren.com" target="_blank">人人网</a>、
            	<a href="http://digu.com" target="_blank">嘀咕</a>、
            	<a href="http://www.zuosa.com" target="_blank">做啥</a>的功
           	</p>
            <p style="margin-left:54px;">能，您可以在<a href="account.jsp">管理同步源</a>中设置同步账号。</p>
            <p style="margin-left:36px;">2、对您提供的账号和密码，本工具不会做任何记录。</p>
            <p style="margin-left:36px;">3、本工具仅供开发者个人使用</p>
            <p style="margin-left:36px;">4、本工具部分源码取至“围着脖子推 V2.0 Beta1“ 的开放源码版本，感谢原作者 </p>
            <p style="margin-left:36px;">5、围着脖子推原作者Twitter账号：<a id="HyperLink1" href="http://twitter.com/StevenWang87" target="_blank">StevenWang87</a>，围脖账号：<a id="HyperLink2" href="http://t.sina.com.cn/StevenWang" target="_blank">StevenWang</a>。</p>
        </div>
		</div>
	</form>
</body>

</html>