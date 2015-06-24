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
    <div >
        <!--Filter-->
        <div class="filter">
            <div class="query">
                <div class="p8 filter_search">
                    <form action="${ctx}/projectPrize/projectPrizes" method="get" id="projectPrizes">
                        <input type="hidden"  name="prize.matchId" value="${projectPrize.prize.matchId!}" />
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">项目名称</td>
                                <td>
                                    <input type="text" name="prjectName" value="${projectPrize.prjectName!}" class="input_large"/>
                                </td>
                                <td class="t_r">奖项名称</td>
                                <td>
                                    <input type="text"  name="prize.prizeName" value="${projectPrize.prize.prizeName!}" class="input_large" />
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
                                    <input type="submit" id="search" value="检 索" /></td>
                            </tr>
                        </table>
                    </form>




                </div>
            </div>
            <div class="fn clearfix">
                <h5 class="fl"><a href="#" class="colSelect fl">优秀创新项目申报资料列表</a></h5>
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
                    <td class="t_c">项目名称</td>
                    <td class="t_c">申报类型</td>
                    <td class="t_c">申报内容</td>
                    <td class="t_c">责任人</td>
                    <td class="t_c">电话</td>
                    <td class="t_c">所属单位</td>
                    <td class="t_c">状态</td>
                    <td class="t_c" style="width:100px">操作</td>
                </tr>

                <#list list as projectPrize>
                <tr>
                    <td class="t_c">
                        <input type="checkbox" value="${projectPrize.projectPrizeId}" name="chk">
                    </td>
                    <td class="t_c">
                    ${(projectPrize_index+1+(pageSize*(pageIndex-1)))}
                    </td>
                    <td class="t_c">
                    ${projectPrize.prize.prizeName!}
                    </td>
                    <td class="t_c">
                    ${projectPrize.prjectName!}
                    </td>
                    <td class="t_c">
                        <#if projectPrize.projectType??>
                            <#if projectPrize.projectType == '1'>
                                合理化建议
                            <#elseif projectPrize.projectType == '2'>
                                对策成果
                            <#else>
                                ${projectPrize.projectType!}
                            </#if>
                        </#if>
                    </td>
                    <td class="t_c">
                        <#if projectPrize.projectContentType??>
                            <#if projectPrize.projectContentType == '1'>
                                管理类
                            <#elseif projectPrize.projectContentType == '2'>
                                技术类
                            </#if>
                        </#if>
                    </td>
                    <td class="t_c">
                    ${projectPrize.responsibilePerson!}
                    </td>
                    <td class="t_c">

                    ${projectPrize.telephone!}
                    </td>
                    <td class="t_c">

                    ${projectPrize.department!}
                    </td>
                    <td class="t_c">

                    </td>
                    <td class="t_c">
                        <a class="button white" name=""><span>审  核</span></a>

                    </td>
                </tr>
                </#list>


                </tbody>
                <tr class="tfoot">
                    <td colspan="15">
                    <@p.pager pageIndex="${pageIndex!1}" pageSize="${pageSize!15}"  totalPages="${totalPages}" totalRows="${totalRows}" formId="projectPrizes" containerId = "${RequestParameters.containerId!}" async ="${RequestParameters.async!false}" />
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
        $.initContextPath("${ctx}");
        $("select[name=departmentId]").wrapDepartmentSelect({label:"name",value:"id",selectVal:"${projectPrize.departmentId!}",root:"hashMap.children"});
        $.initGridAction({ns: "${ctx}", module: "projectPrize"});
    <#if error??>
        $.error("${error}");
    </#if>
    <#if success??>
        $.success("${success}");
    </#if>
    

    });
</script>
</html>