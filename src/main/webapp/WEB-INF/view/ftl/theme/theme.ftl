<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
    <#include "inc/common.ftl"/>

    <script type="text/javascript" src="${ctx}/js/jquery.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>

</head>
<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/theme/themes">竞赛名称管理</a></li>
                <li class="fin">竞赛名称表单</li>
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
        <form action="${ctx}/theme<#if theme.matchThemeId??>/${theme.matchThemeId}</#if>" method="post">
            <input type="hidden" name="matchThemeId" value="${theme.matchThemeId!}"/>
        <#if theme.matchThemeId??><input type="hidden" name="_method" value="put"/></#if>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">年份：</td>
                    <td>
                        <select id="select" name="year">
                            <option value="">--请选择--</option>
                            <option value="2014">2014</option>
                            <option value="2015">2015</option>
                            <option value="2016">2016</option>
                            <option value="2017">2017</option>
                        </select>
                    </td>
                    <td class="t_r lableTd">竞赛名称：</td>
                    <td>
                        <input type="text" name="themeName" class="input_xxlarge" value="${theme.themeName!}"/>
                    </td>
                </tr>


                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/theme/themes'"/>&nbsp;
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
                year: {
                    required: true
                },
                themeName: {
                    required: true
                },
                department: {required: true}
            },
            messages: {
                year: "请输入年份",
                themeName: "请选择竞赛主题",
                department: "请选择考评部门",
            }

        });

        $("#select > option[value=${theme.year}]").prop("selected", true);
    })
</script>
</body>
</html>
