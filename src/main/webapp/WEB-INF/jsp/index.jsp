<html>
    <head>
        <title>Milford Home Page</title>
        <meta charset='utf-8' />
               <script>
                   var result;
                    window.onload = function(){
                        var xhr = new XMLHttpRequest();
                        xhr.open("GET", "http://localhost:8080/WebResponse/calEventArray", true);
                        xhr.send();
                        xhr.onreadystatechange = function(){

                                result = xhr.responseText;
                                document.getElementById("demo").innerHTML = result;
                            }
                        }
               </script>
    </head>
    <body>
        <div>Ballllhhhh blahhhh stuff and things </div>
        <div id='demo'></div>
    </body>
</html>
