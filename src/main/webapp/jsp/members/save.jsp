<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td><%=member.getId()%></td>
        <td><%=member.getUsername()%></td>
        <td><%=member.getAge()%></td>
    </tr>
</table>
<a href="/index.html">메인</a>
</body>
</html>
