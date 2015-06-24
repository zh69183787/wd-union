<!DOCTYPE html>
<html lang="cn">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
<#include "inc/common.ftl">

    <script type="text/javascript" src="${ctx}/js/grid.js"></script>

</head>
<body>

<div>
    <!--Ctrl-->
    <!--Ctrl End-->
    <div id="subContent">
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                    <form action="${ctx}/personPrize/persons" method="get" id="persons">
                        <input type="hidden" name="prize.matchId" value="${personalPrize.prize.matchId!}"/>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">姓名</td>
                                <td>
                                    <input type="text" name="name" value="${personalPrize.name!}" class="input_large"/>
                                </td>
                                <td class="t_r">奖项名称</td>
                                <td>
                                    <input type="text" name="prize.prizeName" value="${personalPrize.prize.prizeName!}" class="input_large"/>
                                </td>

                                <td class="t_r">所属单位</td>
                                <td>
                                    <select name="departmentId">
                                        <option>请选择</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="6" class="t_c">
                                    <input type="submit" value="检 索" /></td>
                            </tr>
                        </table>
                    </form>




                </div>
            </div>
            <div class="fn clearfix">
                <h5 class="fl"><a href="#" class="colSelect fl">个人申报资料列表</a></h5>
                <div class="fr">
                    <input type="button"  value="审 核" style="margin-left: 5px">
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
                    <td class="t_c">奖项名称</td>
                    <td class="t_c">姓名</td>
                    <td class="t_c">性别</td>
                    <td class="t_c">出生年月</td>
                    <td class="t_c">政治面貌</td>
                    <td class="t_c">职位</td>
                    <td class="t_c">所属单位</td>
                    <td class="t_c">状态</td>
                    <td class="t_c" style="width:100px">操作</td>
                </tr>

                <#list list as person>
                <tr>
                    <td class="t_c">
                        <input type="checkbox" value="${person.personalPrizeId}" name="chk">
                    </td>
                    <td class="t_c">
                    ${(person_index+1+(pageSize*(pageIndex-1)))}
                    </td>
                    <td class="t_c">
                    ${person.prize.prizeName!}
                    </td>
                    <td class="t_c">
                    ${person.name}
                    </td>
                    <td class="t_c">
                        <#if person.gender=1>男<#else>女</#if>
                        <!--${person.gender}-->
                    </td>
                    <td class="t_c">
                        <#if person.brithday??>${person.brithday?date}</#if>

                    </td>
                    <td class="t_c">
                    ${person.political}
                    </td>
                    <td class="t_c">
                    ${person.position}
                    </td>
                    <td class="t_c">
                    ${person.department}
                    </td>
                    <td class="t_c">

                    </td>
                    <td class="t_c" style="width:250px">
                        <a class="mr5" >审核</a>
                    </td>
                </tr>
                </#list>


                </tbody>
                <tr class="tfoot">
                    <td colspan="15">
                    <@p.pager pageIndex="${pageIndex!1}" pageSize="${pageSize!15}"  totalPages="${totalPages}" totalRows="${totalRows}" formId="persons" containerId = "${RequestParameters.containerId!}" async ="${RequestParameters.async!false}" />
                    </td>
                </tr>
            </table>

        </div>
        <!--Table End-->
    </div>
</div>
<script  type="text/javascript">
    $(function () {
        $.initContextPath("${ctx}");
        $.initGridAction({ns: "${ctx}", module: "personPrize"});
    <#if error??>
        $.error("${error}");
    </#if>
    <#if success??>
        $.success("${success}");
    </#if>
        $("select[name=departmentId]").wrapDepartmentSelect({label:"name",value:"id",selectVal:"${personalPrize.departmentId!}",root:"hashMap.children"});
    });
</script>
</body>

</html>