const http = require('http');
const fs = require('fs'); //동기식 IO와 비동기식 IO를 제공해주는 JS 모듈.
const url = require('url');
const parse = require('querystring');

const app = http.createServer((request, response) => {
    //http 모듈에는 createServer 함수를 실행시키며, 인자로 Request와 response를 받는다.
    //let baseUrl = 'http://' + request.headers.host;
    //const queryData = new URL(_url, baseUrl);
    let _url = request.url;
    const queryString = parse.parse(request.url);
    console.log("queryString : ", queryString);

    if (_url == '/') {
        _url = '/index.html';
    }

    if (_url == '/favicon.ico') {
        return response.writeHead(404);
    }

    let template = `
            <!doctype html>
            <html lang = 'kr'>

            <head>
            <title>WEB1 - ${queryString.id}</title>
            <meta charset="utf-8">
            </head>

            <body>
            <h1><a href="index.html">WEB</a></h1>
            <ol>
                <li><a href="1.html?id=HTML">HTML</a></li>
                <li><a href="2.html?id=CSS">CSS</a></li>
                <li><a href="3.html?id=JS">JavaScript</a></li>
            </ol>
            <h2>${queryString.id}</h2>
            <p><a href="https://www.w3.org/TR/html5/" target="_blank" title="html5 speicification">Hypertext Markup Language
                (HTML)</a> is the standard markup language for <strong>creating <u>web</u> pages</strong> and web applications.Web
                browsers receive HTML documents from a web server or from local storage and render them into multimedia web pages.
                HTML describes the structure of a web page semantically and originally included cues for the appearance of the
                document.
                <img src="coding.jpg" width="100%">
            </p>
            <p style="margin-top:45px;">HTML elements are the building blocks of HTML pages. With HTML constructs, images and
                other objects, such as interactive forms, may be embedded into the rendered page. It provides a means to create
                structured documents by denoting structural semantics for text such as headings, paragraphs, lists, links, quotes
                and other items. HTML elements are delineated by tags, written using angle brackets.
            </p>
            </body>

            </html>
    `;

    response.writeHead(200);
    response.end(template); //fs.readFileSync(__dirname + _url)
    //비동기식 io 메소드는 readFile()
    //*fs.readFileSync 함수
    // node.js가 해당 경로에 대한 파일을 읽는다는 것.
    //*response.end()
    // 사용자에게 전송할 data를 생성
    // ==> 즉 사용자에게 전송할 data를 생성한다.
});

app.listen(80); //listen함수는 port를 정해주는 함수