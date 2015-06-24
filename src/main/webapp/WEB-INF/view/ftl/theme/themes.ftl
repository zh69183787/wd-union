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
                <li><a href="${ctx}/theme/themes">竞赛名称管理</a></li>
                <li class="fin">竞赛名称列表</li>
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
                    <form action="${ctx}/theme/themes" method="get">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">选择年度</td>
                                <td>
                                    <select id="select" name="year" >
                                        <option value="">请选择</option>
                                        <option value="2014">2014</option>
                                        <option value="2015">2015</option>
                                        <option value="2016">2016</option>
                                        <option value="2017">2017</option>
                                    </select>
                                </td>
                                <td class="t_r">竞赛名称</td>
                                <td>
                                    <input type="text"  name="themeName" value="${theme.themeName!}" class="input_large" />
                                </td>

                            </tr>

                            <tr>
                                <td colspan="4" class="t_c">
                                    <input type="submit" id="search" value="检 索" /></td>
                            </tr>
                        </table>
                    </form>




                </div>
            </div>
            <div class="fn clearfix">
                <h5 class="fl"><a href="#" class="colSelect fl">竞赛名称列表</a></h5>
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
                    <td class="t_c" style="width:10px"><input type="checkbox" id="chkAll"></td>
                    <td class="t_c" style="width:40px">序号</td>
                    <td class="t_c">年度</td>
                    <td class="t_c">竞赛主题</td>
                    <td class="t_c" style="width:230px">操作</td>
                </tr>

                <#list list as theme>
                <tr>
                    <td class="t_c">
                        <input type="checkbox" value="${theme.matchThemeId}" name="chk">
                    </td>
                    <td class="t_c">
                    ${(theme_index+1+(pageSize*(pageIndex-1)))}
                    </td>
                    <td class="t_c">
                    ${theme.year}
                    </td>
                    <td class="t_c">
                    ${theme.themeName}
                    </td>
                    <td class="t_c">
                        <a class="mr5" name="names">竞赛主题</a>
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




</body>
<script>
    $(function () {
        $.initGridAction({ns: "${ctx}", module: "theme"});
    <#if error??>
        $.error("${error}");
    </#if>
    <#if success??>
        $.success("${success}");
    </#if>

        $("a[name=names]").click(function () {
            location.href = "${ctx}/match/matches?matchThemeId=" + $(this).getRowId();
        });

        $("#select > option[value=${theme.year}]").prop("selected", true);
    });
</script>
</html>