<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
    <#include "inc/common.ftl">

</head>
<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/rule/rules">奖项规则</a></li>
                <li class="fin">规则表单</li>
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
        <form action="${ctx}/rule/${rule.prizeRuleId!}" method="post">

            <#if rule.prizeRuleId??><input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="prizeRuleId" value="${rule.prizeRuleId!}"/></#if>

            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">奖项类型：</td>
                    <td>
                        <select name="prizeType">
                            <option value="">请选择</option>
                            <option value="1">个人类</option><option value="2">集体类</option><option value="3">项目类</option><option value="4">项目成果类</option>
                        </select>
                        <select name="prizeSubType">
                            <option value="">请选择</option> <option value="1">分等次竞赛</option> <option value="2">不分等次竞赛</option>
                        </select>
                    </td>
                    <td class="t_r lableTd">人数范围：</td>
                    <td>
                        <select name="personRange">
                            <option value="">请选择</option><option value="1">单位</option><option value="2">集体人数<=20人</option><option value="3">20人< 集体人数 < 50人</option><option value="4">集体人数 >= 50人</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">最高奖金：</td>
                    <td >
                        <input type="text" class="input_xxlarge" name="maxBonus" value="${rule.maxBonus!}"/>

                    </td>

                    <td class="t_r lableTd">最低奖金：</td>
                    <td>
                        <input type="text" class="input_xxlarge" name="minBonus" value="${rule.minBonus!}"/>
                    </td>
                </tr>

                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/rule/rules'"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>
<script>
    $(function () {
       $("select[name=prizeType] > option[value=${rule.prizeType!}]").prop("selected",true);
        $("select[name=prizeSubType] > option[value=${rule.prizeSubType!}]").prop("selected",true);
        $("select[name=personRange] > option[value=${rule.personRange!}]").prop("selected",true);
    })
</script>
</body>
</html>
