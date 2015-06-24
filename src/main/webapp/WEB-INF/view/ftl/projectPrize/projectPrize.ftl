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
                <li><a href="${ctx}/match/${projectPrize.prize.matchId!}/detail">专项主题明细</a></li>
                <li class="fin">项目类申报资料</li>
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
        <form action="${ctx}/projectPrize<#if projectPrize.projectPrizeId??>/${projectPrize.projectPrizeId}</#if>" method="post">
            <input type="hidden" name="prizeId" value="${projectPrize.prize.prizeId!}"/>
            <input type="hidden" name="prize.matchId" value="${projectPrize.prize.matchId!}"/>
            <input type="hidden" name="departmentId" value="${Session.departmentId!2506}"/>
            <input type="hidden" name="projectPrizeId" value="${projectPrize.projectPrizeId!}"/>
        <#if projectPrize.projectPrizeId??><input type="hidden" name="_method" value="put"/></#if>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">申报类型：</td>
                    <td>
                        <select id="projectTypeDesc" name="projectTypeDesc">
                            <option value="">请选择</option>
                            <option value="3">工作室</option>
                            <option value="1">合理化建议</option>
                            <option value="2">对策成果</option>
                        </select>
                        <input style="display:none;" type="text" class="input_xxlarge" name="projectType" value="${projectPrize.projectType!}"/>
                    </td>
                    <td class="t_r lableTd">申报内容：</td>
                    <td>
                        <select name="projectContentType">
                            <option value="">请选择</option>
                            <option value="1">管理类</option>
                            <option value="2">技术类</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">项目名称：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="prjectName"
                               value="${projectPrize.prjectName!}">
                    </td>

                    <td class="t_r lableTd">承担单位：</td>
                    <td>
                        <input class="input_xxlarge" type="text"  readonly="readonly" name="department" value="${projectPrize.department!}">
                        <select id="departmentId" style="display:none;"></select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">责任人：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="responsibilePerson"
                               value="${projectPrize.responsibilePerson!}">
                    </td>
                    <td class="t_r lableTd">联系电话：</td>
                    <td>
                        <input type="text" class="input_xxlarge" name="telephone" value="${projectPrize.telephone!}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">项目简介：</td>
                    <td colspan="3">
                        <textarea    rows="10" name="introduct">${projectPrize.introduct!}</textarea >
                    </td>
                </tr>

                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/match/${projectPrize.prize.matchId!}/detail?tab=2'"/>&nbsp;
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
                projectTypeDesc: {
                    required: true
                },
                projectType: {
                    required: true
                }, prjectName: {
                    required: true
                },
                projectContentType: {required: true},
                responsibilePerson:{required: true},
                telephone:{required:true}
            },
            messages: {
                introduct: {
                    required: "项目简介不能为空",
                    maxlength:"项目简介内容长度不能超过300个字符"
                },
                prjectName:"项目名称不能为空",
                projectTypeDesc:"申报类型不能为空",
                projectType: "申报类型为工作室时需要填写工作室名称",
                projectContentType: "申报内容不能为空",
                responsibilePerson:"责任人不能为空",
                telephone:"联系电话不能为空"
            }

        });

        $.initContextPath("${ctx}");
        $("#departmentId").bind("completed",function(){
            $(":text[name=department]").val($(this).find("option[selected]").text());
        })
        $("#departmentId").wrapDepartmentSelect({label:"name",value:"id",selectVal:"${Session.departmentId!2506}",root:"hashMap.children"});
        $("select[name=projectContentType] > option[value=${projectPrize.projectContentType}]").prop("selected", true);
        <#if projectPrize.projectType??>
            <#if projectPrize.projectType != "1" && projectPrize.projectType != "2">
                $("#projectTypeDesc > option[value=3]").prop("selected", true);
                $(":text[name=projectType]").show();
            </#if>
        </#if>
        $("#projectTypeDesc > option[value=${projectPrize.projectType!}]").prop("selected", true);
        $("#projectTypeDesc").change(function(){
            var value = $(this).val();
            $(":text[name=projectType]").val(value || "4");
            if(value == "3"){
                $(":text[name=projectType]").val("");
                $(":text[name=projectType]").show();
            }else{
                $(":text[name=projectType]").hide();
            }
        });
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
