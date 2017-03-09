<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/27
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jstack</title>
</head>
<body>
<%
    ThreadGroup group = Thread.currentThread().getThreadGroup();
    ThreadGroup topGroup = group;
// 遍历线程组树，获取根线程组
    while (group != null) {
        topGroup = group;
        group = group.getParent();
    }
// 激活的线程数加倍
    int estimatedSize = topGroup.activeCount() * 2;
    Thread[] slackList = new Thread[estimatedSize];
// 获取根线程组的所有线程
    int actualSize = topGroup.enumerate(slackList);
// copy into a list that is the exact size
    Thread[] list = new Thread[actualSize];
    System.arraycopy(slackList, 0, list, 0, actualSize);
    out.print("Thread list size = " + list.length);

    out.print("<table border='1px' style='border-collapse:collapse;'>");
    for (Thread thread : list) {
        StackTraceElement[] stack= thread.getStackTrace();
        out.print("<tr>");
        out.print("<td>");
        out.print(thread.getName());
        out.print("</td>");

        out.print("<td>");
        out.print(Arrays.toString(stack));
        out.print("</td>");

        out.print("</tr>");
    }
    out.print("</table>");

%>
</body>
</html>
