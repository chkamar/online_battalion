<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/static/js/layui/css/layui.css">
<script src="/static/js/layui/layui.js"></script>
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
    <textarea id="demo" style="display: none;">
             把编辑器的初始内容放在这textarea即可
    </textarea>

    <script>
        layui.use('layedit', function () {
            var layedit = layui.layedit;
            layedit.build('demo', {
                tool: ['left', 'center', 'right', '|', 'link', 'unlink', 'face', 'image']
                , height: 100
                , uploadImage: {
                    url: '${request.contextPath}/upload/image'
                }
            });
        });

    </script>
</body>
</html>