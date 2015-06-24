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
                <li><a href="${ctx}/match/${projectAchievement.prize.matchId!}/detail">专项主题明细</a></li>
                <li class="fin">项目成果类申报资料</li>
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
        <form action="${ctx}/projectAchievement<#if projectAchievement.projectAchievementId??>/${projectAchievement.projectAchievementId}</#if>" method="post">
            <input type="hidden" name="prizeId" value="${projectAchievement.prize.prizeId!}"/>
            <input type="hidden" name="prize.matchId" value="${projectAchievement.prize.matchId!}"/>
            <input type="hidden" name="departmentId" value="${Session.departmentId!2506}"/>
            <input type="hidden" name="projectAchievementId" value="${projectAchievement.projectAchievementId!}"/>
            <#if projectAchievement.projectAchievementId??><input type="hidden" name="_method" value="put"/></#if>

            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">参赛板块：</td>
                    <td>
                        <select name="module">
                            <option value="">请选择</option>
                            <option value="1">安全保障</option>
                            <option value="2">服务品牌</option>
                            <option value="3">团队建设</option>
                        </select>
                    </td>
                    <td class="t_r lableTd">项目名称：</td>
                    <td>
                        <input type="text" class="input_xxlarge" name="prjectName" value="${projectAchievement.prjectName!}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">申报集体全称：</td>
                    <td colspan="3">
                        <input type="text" class="input_xxlarge" name="department" value="${projectAchievement.department!}"/>
                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd">负责人：</td>
                    <td >
                        <input class="input_xxlarge" type="text" name="responsibilePerson"
                               value="${projectAchievement.responsibilePerson!}">

                    </td>

                    <td class="t_r lableTd">联系电话：</td>
                    <td>

                        <input type="text" class="input_xxlarge" name="telephone" value="${projectAchievement.telephone!}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">项目简介：</td>
                    <td colspan="3">
                        <textarea  rows="10" name="introduct">${projectAchievement.introduct!}</textarea >
                    </td>
                </tr>

                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/match/${projectAchievement.prize.matchId!}/detail?tab=3'"/>&nbsp;
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
                department: {
                    required: true
                }, prjectName: {
                    required: true
                },
                module: {required: true},
                responsibilePerson:{required: true},
                telephone:{required:true}
            },
            messages: {
                introduct: {
                    required: "项目简介不能为空",
                    maxlength:"项目简介内容长度不能超过300个字符"
                },
                module:"参赛板块不能为空",
                prjectName:"项目名称不能为空",
                department: "申报集体名称不能为空",
                responsibilePerson:"责任人不能为空",
                telephone:"联系电话不能为空"
            }

        });


        $("select[name=module] > option[value=${projectAchievement.module!}]").prop("selected", true);
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
