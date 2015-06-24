<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
<#include "inc/common.ftl">

    <script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
</head>
<body>

<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/match/matches">专项主题</a></li>
                <li><a href="${ctx}/match/${teamPrize.prize.matchId!}/detail">专项主题明细</a></li>
                <li class="fin">集体奖项申报资料</li>
            </ul>
        </div>
        <div style="display:none;" class="fr lit_nav nwarp">
            <ul>
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="express" href="#">导出数据</a></li>
                <li class="selected"><a class="table" href="#">表格模式</a></li>
                <li><a class="treeOpen" href="#">打开树</a></li>
                <li><a class="filterClose" href="#">关闭过滤</a></li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <!--Filter--><!--Filter End-->
    <!--Table-->

    <div class="mb10 pt45">
        <form action="${ctx}/teamPrize<#if teamPrize.teamPrizeId??>/${teamPrize.teamPrizeId}</#if>" method="post">
            <input type="hidden" name="prizeId" value="${teamPrize.prize.prizeId!}"/>
            <input type="hidden" name="prize.matchId" value="${teamPrize.prize.matchId!}"/>
            <input type="hidden" name="teamPrizeId" value="${teamPrize.teamPrizeId!}"/>
            <input type="hidden" name="departmentId" value="${Session.departmentId!2506}"/>
        <#if teamPrize.teamPrizeId??><input type="hidden" name="_method" value="put"/></#if>

            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">集体名称：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="name" value="${teamPrize.name!}">
                    </td>
                    <td class="t_r lableTd">人数：</td>
                    <td>
                        <input type="text" class="input_xxlarge" name="persons" value="${teamPrize.persons!}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">责任人：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="responsibilePerson"
                               value="${teamPrize.responsibilePerson!}">
                    </td>

                    <td class="t_r lableTd">联系电话：</td>
                    <td>

                        <input type="text" class="input_xxlarge" name="telephone" value="${teamPrize.telephone!}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">所属单位：</td>
                    <td colspan="3">
                        <input class="input_xxlarge" type="text" readonly="readonly" name="department" value="">
                        <select id="departmentId" style="display:none;"></select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">简要事迹：</td>
                    <td colspan="3">
                        <textarea  rows="10" name="introduct">${teamPrize.introduct!}</textarea >
                    </td>
                </tr>

                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/match/${teamPrize.prize.matchId!}/detail?tab=1'"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>
<script>
    $(function () {
        var validator = $("form").validate({
            rules: {
                introduct: {
                    required: true,
                    maxlength:300
                },
                name: {
                    required: true
                },
                persons: {required: true},
                responsibilePerson:{required: true},
                telephone:{required:true}
            },
            messages: {
                introduct: {
                    required: "简要事迹不能为空",
                    maxlength:"简要事迹内容长度不能超过300个字符"
                },
                name: "集体名称不能为空",
                persons: "人数不能为空",
                responsibilePerson:"责任人不能为空",
                telephone:"联系电话不能为空"
            }

        });
        $.initContextPath("${ctx}");
        $("#departmentId").bind("completed",function(){
            $(":text[name=department]").val($(this).find("option[selected]").text());
            <#if teamPrize.name??>
                <#else>
                $(":text[name=name]").val($(":text[name=department]").val());
            </#if>
        })
        $("#departmentId").wrapDepartmentSelect({label:"name",value:"id",selectVal:"${Session.departmentId!2506}",root:"hashMap.children"});

    <#if error??>
        $.error("${error}");
    </#if>
    <#if success??>
        $.success("${success}");
    </#if>
    })
</script>
</body>
</html>
