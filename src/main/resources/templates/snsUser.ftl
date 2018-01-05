<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>OAuth2.0网页授权</title>
</head>
<body>
<#if snsUser??>
<table width="100%" cellspacing="0" cellpadding="0">
    <tr><td width="20%">属性</td><td width="80%">值</td></tr>
    <tr><td>OpenID</td><td>${snsUser.openId}</td></tr>
    <tr><td>昵称</td><td>${snsUser.nickname}</td></tr>
    <tr><td>性别</td><td>${snsUser.sex}</td></tr>
    <tr><td>国家</td><td>${snsUser.country}</td></tr>
    <tr><td>省份</td><td>${snsUser.province}</td></tr>
    <tr><td>城市</td><td>${snsUser.city}</td></tr>
    <tr><td>头像</td><td>${snsUser.headImgUrl}</td></tr>
    <tr><td>特权</td><td>${snsUser.privilegeList}</td></tr>
    <tr><td>state:</td><td>${snsUser.state}</td></tr>
    </#if>
</table>
</body>
</html>