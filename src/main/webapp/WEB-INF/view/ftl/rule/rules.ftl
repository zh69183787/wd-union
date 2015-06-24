<!DOCTYPE html>
<html lang="cn">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
<#include "inc/common.ftl">

    <script type="text/javascript" src="${ctx}/js/grid.js"></script>
</head>
<body>

<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/rule/rules">奖项规则</a></li>
                <li class="fin">规则列表</li>
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
    <div class="pt45">
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                    <form action="${ctx}/rule/rules" method="get">

                    </form>




                </div>
            </div>
            <div class="fn clearfix">
                <h5 class="fl"><a href="#" class="colSelect fl">奖项规则列表</a></h5>
                <div class="fr">
                    <input type="button" id="addToolBarBtn"  value="新 增" style="margin-left: 5px"/>
                    <input id="editToolBarBtn" type="button"  value="修 改" style="margin-left: 5px">
                    <input type="button"  id="delToolBarBtn" value="删 除" style="margin-left: 5px">
                </div>
            </div>
        </div>



        <!--Filter End-->
        <!--Table-->

        <div class="mb10">
            <table width="100%"  class="table_1">
                <tbody>
                <tr class="tit">
                    <th class="tc" style="width:10px"><input type="checkbox" id="chkAll"></th>
                    <th class="tc" style="width:40px">序号</th>
                    <th class="tc">规则类型</th>
                    <th class="tc">人数范围</th>
                    <th class="tc">奖金</th>
                </tr>

                <#list list as rule>
                <tr>
                    <td class="tc">
                        <input type="checkbox" value="${rule.prizeRuleId}" name="chk">
                    </td>
                    <td class="tc">
                    ${(rule_index+1+(pageSize*(pageIndex-1)))}
                    </td>
                    <td class="tc">
                        <#if rule.prizeType??>
                            <#if rule.prizeType == '1'>
                                个人类
                            <#elseif rule.prizeType == '2'>
                                集体类
                            <#elseif rule.prizeType == '3'>
                                项目类
                            <#elseif rule.prizeType == '4'>
                                项目成果类
                            </#if>
                        </#if>
                        <#if rule.prizeSubType??>
                            <#if rule.prizeSubType == '1'>
                                分等次奖
                            <#elseif rule.prizeSubType == '2'>
                                不分等次奖
                            </#if>
                        </#if>
                    </td>
                    <td class="tc">
                        <#if rule.personRange??>
                            <#if rule.personRange == '1'>
                                单位
                            <#elseif rule.personRange == '2'>
                                集体人数<=20人
                            <#elseif rule.personRange == '3'>
                                20人< 集体人数 < 50人
                            <#elseif rule.personRange == '4'>
                                集体人数 >= 50人
                            </#if>
                        </#if>
                    </td>
                    <td class="tc">
                        <#if rule.maxBonus == rule.minBonus>
                        ${rule.maxBonus}
                        <#else>
                        ${rule.minBonus} - ${rule.maxBonus}
                        </#if>
                    </td>
                </tr>
                </#list>


                </tbody>
                <tr class="tfoot">
                    <td colspan="15">
                    <@p.pager pageIndex="${pageIndex!1}" pageSize="${pageSize!15}"  totalPages="${totalPages}" totalRows="${totalRows}" />
                    </td>
                </tr>
            </table>

        </div>
        <!--Table End-->
    </div>
</div>


<script>
    $(function () {
        $.initContextPath("${ctx}");
        $.initGridAction({ns: "${ctx}", module: "rule"});

    });
</script>
</body>
</html>