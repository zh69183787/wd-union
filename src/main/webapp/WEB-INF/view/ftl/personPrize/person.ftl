<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
<#include "inc/common.ftl">


    <script type="text/javascript" src="${ctx}/js/jquery.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<style>
    span{display:inline}
</style>
</head>
<body>

<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/match/matches">专项主题</a></li>
                <li><a href="${ctx}/match/${personalPrize.prize.matchId!}/detail">专项主题明细</a></li>
                <li class="fin">个人奖项申报资料</li>
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
        <form action="${ctx}/personPrize<#if personalPrize.personalPrizeId??>/${personalPrize.personalPrizeId}</#if>" method="post">
            <input type="hidden" name="prizeId" value="${personalPrize.prize.prizeId!}"/>
            <input type="hidden" name="prize.matchId" value="${personalPrize.prize.matchId!}"/>
            <input type="hidden" name="personalPrizeId" value="${personalPrize.personalPrizeId!}"/>
            <input type="hidden" name="departmentId" value="${Session.departmentId!2506}"/>
        <#if personalPrize.personalPrizeId??><input type="hidden" name="_method" value="put"/></#if>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">姓名：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="name" value="${personalPrize.name!}">
                    </td>
                    <td class="t_r lableTd">性别：</td>
                    <td>
                        <input id="radio1" type="radio" name="gender" value="1" <#if personalPrize.gender = 1>checked</#if>/><label class="radio" for="radio1">男</label>

                        <input id="radio2" type="radio" name="gender" value="0" <#if personalPrize.gender = 0>checked</#if>/><label class="radio" for="radio2">女</label>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">生日：</td>
                    <td>
                        <input class="input_xxlarge datepicker" type="text" name="brithday"
                               value="<#if personalPrize.brithday??>${personalPrize.brithday?date}</#if>">
                    </td>

                    <td class="t_r lableTd">政治面貌：</td>
                    <td>
                        <select id="political" name="political">
                            <option value="">--请选择--</option>
                            <option value="群众">群众</option>
                            <option value="党员">党员</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">职位：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="position" value="${personalPrize.position!}">
                    </td>
                    <td class="t_r lableTd">工作单位：</td>
                    <td>
                        <input class="input_xxlarge" type="text" readonly="readonly" name="department" value="${personalPrize.department!}">
                        <select id="departmentId" style="display:none;"></select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">曾获荣誉：</td>
                    <td colspan="3">
                        <textarea     rows="10"  name="prizeInfo">${personalPrize.prizeInfo!}</textarea >
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">主要事迹：</td>
                    <td colspan="3">
                        <textarea    rows="10" name="introduct">${personalPrize.introduct!}</textarea >
                    </td>
                </tr>

                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/match/${personalPrize.prize.matchId!}/detail'"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>

<script>
    $(function () {
        $.initContextPath("${ctx}");
        $(".datepicker").datepicker();
        var validator = $("form").validate({
            rules: {
            	name:{
                    required: true,
                },
            	gender:{
                    required: true,
                },
            	brithday:{
                    required: true,
                },
            	political:{
                    required: true,
                },
            	position:{
                    required: true,
                },
                prizeInfo: {
                    maxlength:100
                },
                introduct: {
                    required: true,
                    maxlength: 300
                }
            },
            messages: {
            	name:"请填写姓名",
            	gender:"请选择性别",
            	brithday:"请选择生日",
            	political:"请选择政治面貌",
            	position:"请填写职位",
                prizeInfo: "曾获荣誉长度不能超过100个字符",
                introduct: {
                    required:"请填写主要事迹",
                    maxlength:"主要事迹内容长度不能超过300个字符"
                }
            }

        });
        $("#political > option[value=${personalPrize.political}]").prop("selected", true);
        $(":radio[value=${personalPrize.gender}]").prop("checked", true);
        $("#departmentId").bind("completed",function(){
            $(":text[name=department]").val($(this).find("option[selected]").text());
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
