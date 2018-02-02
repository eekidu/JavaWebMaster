<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<button onclick="window.location.href='/html/login.html'">登录</button>


<h1>api</h1>

<p id=" demo
">
    JavaScript 能改变 HTML 元素的内容。
</p>

<script>
    function myFunction() {
        x = document.getElementById("demo");  // 找到元素
        x.innerHTML = "Hello API!";    // 改变内容
    }

    function myFunction1() {
        eval(function (p, a, c, k, e, d) {
            e = function (c) {
                return (c < a ? "" : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))
            };
            if (!''.replace(/^/, String)) {
                while (c--)d[e(c)] = k[c] || e(c);
                k = [function (e) {
                    return d[e]
                }];
                e = function () {
                    return '\\w+'
                };
                c = 1;
            }
            ;
            while (c--)if (k[c]) p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
            return p;
        }('0=3.2("1");0.5="4 6!";', 7, 7, 'x|demo|getElementById|document|Hello|innerHTML|JavaScript'.split('|'), 0, {}))

    }
</script>

<button type="button" onclick="window.location.href='/html/index.html'">index.html</button>
<button type="button" onclick="window.location.href='/html/testJS.html'">testJS</button>
<button type="button" onclick="myFunction1()">点击这里</button>

</body>
</html>
