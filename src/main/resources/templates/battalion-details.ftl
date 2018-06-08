<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${battalion.name}</title>
</head>
<body>
    <#include "/common/header.ftl"/>

    <#if paths??>
    <div class="layui-carousel" id="index" style="margin-top: 10px;">
        <div carousel-item>
            <#list paths as path>
                <img src="${path}">
            </#list>
        </div>
    </div>

    </#if>

    <div class="layui-container">

        <div class="layui-row">

            <div class="layui-col-md9">
                <div class="layui-row" style="padding: 10px 20px;border: 1px solid #f6f5f5">
                    <span style="font-size: larger">${battalion.name}</span>
                    <span class="status" style="font-size: medium;float: right">${battalion.status!'-'}</span>
                </div>
                <div class="layui-row" style="padding: 10px 20px;border: 1px solid #f6f5f5">
                    <div class="layui-col-xs5">
                        <img class="image" src="${battalion.imagePath}">
                    </div>
                    <div class="layui-col-xs7">
                        <input type="hidden" id="id" value="${battalion.id}"/>
                        <div class="layui-row">
                            价格
                            <span style="padding-left: 20px"> ${battalion.price}</span>
                            <#if enrollStatus??>
                                <#if enrollStatus == 0>
                                    <button id="join" class="layui-btn layui-btn-primary layui-btn-xs" style="float: right" onclick="join()">报名</button>
                                </#if>
                                <#if enrollStatus == 1>
                                    <button disabled="disabled" class="layui-btn layui-btn-primary layui-btn-xs" style="float: right">已报名</button>
                                </#if>
                            </#if>

                               </div>

                        <div class="layui-row" style="padding-top: 5px">
                            报名人数<span id="enrollment" style="padding-left: 20px">${battalion.enrollment}</span>
                        </div>
                        <div class="layui-row" style="padding-top: 5px">
                            训练营类型<span style="padding-left: 20px">${battalion.type}</span>
                        </div>
                        <div class="layui-row" style="padding-top: 5px">
                            训练天数<span style="padding-left: 20px">${battalion.duration}</span>
                        </div>
                        <div class="layui-row" style="padding-top: 5px">
                            时间段<span style="padding-left: 20px">${battalion.timeStart}到${battalion.timeEnd}</span>
                        </div>
                        <div class="layui-row" style="padding-top: 5px">
                            报名截止<span style="padding-left: 20px">${battalion.deadline}</span>
                        </div>
                        <div class="layui-row" style="padding-top: 5px">
                            地点<span style="padding-left: 20px">${battalion.place}</span>
                        </div>
                    </div>
                </div>
                <div class="description" style="padding: 10px 20px;border: 1px solid #f6f5f5">
                    <p>${battalion.description}</p>
                </div>
            </div>

            <div class="layui-col-md3" style="padding: 0 30px">
                <h2 align="center">同类训练营推荐</h2>
                <br>
                <#if recommendations??>
                    <#list recommendations as recommendation>
                        <div class="layui-col-md12" style="padding-top: 3px">
                            <a href="/battalion/details/${recommendation.id}" style="width: 95%">
                                <img class="image" src="${recommendation.imagePath}">
                                <span id="top1Title" style="margin:10px auto;display: block;text-align: center">${recommendation.name}</span>
                            </a>
                        </div>
                    </#list>
                <#else>
                    <h2 align="center">暂无推荐</h2>
                </#if>


            </div>

        </div>

    </div>

    <script>
        function succJoin(result) {
            alert(result.msg);
            if (result.status) {
                $("#join").text('已报名');
                $("#join").attr("disabled",true);
                var enrollment = parseInt($("#enrollment").text())+1;
                $("#enrollment").text(enrollment);
            }

        }

        var id = ${battalion.id};
        id = id.replace(/,/g, "");
        function join(){

            var url = '${request.contextPath}/battalionUserRelation/user/add/'+id;
            ajaxGet(url,succJoin,errorCallBack);
        }
    </script>

    <#include "/common/footer.ftl"/>
</body>
</html>