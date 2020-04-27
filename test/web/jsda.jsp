<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Charles-WebSocket</title>

  <script type="text/javascript">

    var websocket = null;

    var target = "ws://localhost:8080/helloSocket";

    function buildConnection() {
      if('WebSocket' in window) {
        websocket = new WebSocket(target);
      } else if('MozWebSocket' in window) {
        websocket = MozWebSocket(target);
      } else {
        window.alert("浏览器不支持WebSocket");
      }
    }

  </script>
</head>
<body>

<button onclick="buildConnection();">开始建立链接</button>

</body>
</html>