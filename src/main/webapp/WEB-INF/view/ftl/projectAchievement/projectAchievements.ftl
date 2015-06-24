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
                    <form action="${ctx}/projectAchievement/projectAchievements" method="get" id="projectAchievements">
                        <input type="hidden"  name="prize.matchId" value="${projectAchievement.prize.matchId!}" />
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>

                                <td class="t_r">项目名称</td>
                                <td>
                                    <input type="text" name="prjectName" value="${projectAchievement.prjectName!}" class="input_large"/>
                                </td>
                                <td class="t_r">奖项名称</td>
                                <td>
                                    <input type="text"  name="prize.prizeName" value="${projectAchievement.prize.prizeName!}" class="input_large" />
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
                <h5 class="fl"><a href="#" class="colSelect fl">品牌成果申报资料列表</a></h5>
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
                    <td class="t_c">参赛板块</td>
                    <td class="t_c">项目名称</td>
                    <td class="t_c">申报集体全称</td>
                    <td class="t_c">负责人</td>
                    <td class="t_c">电话</td>
                    <td class="t_c">所属单位</td>
                    <td class="t_c">状态</td>
                    <td class="t_c" style="width:100px">操作</td>
                </tr>

                <#list list as projectAchievement>
                <tr>
                    <td class="t_c">
                        <input type="checkbox" value="${projectAchievement.projectAchievementId}" name="chk">
                    </td>
                    <td class="t_c">
                    ${(projectAchievement_index+1+(pageSize*(pageIndex-1)))}
                    </td>
                    <td class="t_c">
                    ${projectAchievement.prize.prizeName}
                    </td>
                    <td class="t_c">
                        <#if projectAchievement.module??>
                            <#if projectAchievement.module == '1'>
                                安全保障
                            <#elseif projectAchievement.module == '2'>
                                服务品牌
                            <#elseif projectAchievement.module == '3'>
                                团队建设
                            </#if>
                        </#if>
                    </td>
                    <td class="t_c">
                    ${projectAchievement.prjectName}
                    </td>
                    <td class="t_c">
                    ${projectAchievement.department}
                    </td>
                    <td class="t_c">
                    ${projectAchievement.responsibilePerson}
                    </td>

                    <td class="t_c">
                    ${projectAchievement.telephone}
                    </td>
                    <td class="t_c">
                        <span name="deptName" style="display:none">${projectAchievement.departmentId!}</span>
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
                    <@p.pager pageIndex="${pageIndex!1}" pageSize="${pageSize!15}"  totalPages="${totalPages}" totalRows="${totalRows}" formId="projectAchievements" containerId = "${RequestParameters.containerId!}" async ="${RequestParameters.async!false}" />
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
        $.initGridAction({ns: "${ctx}", module: "projectAchievement"});
        $("select[name=departmentId]").wrapDepartmentSelect({label:"name",value:"id",selectVal:"${projectAchievement.departmentId!}",root:"hashMap.children"});
        $("select[name=departmentId]").bind("completed",function(){
            $("span[name=deptName]").each(function(i,n){
                if($(this).text()!=""){
                    $(this).text( $("select[name=departmentId]").find("option[value="+$(this).text()+"]").text());
                    $(this).show();
                }
            });
        });
    <#if error??>
        $.error("${error}");
    </#if>
    <#if success??>
        $.success("${success}");
    </#if>

    });
</script>
</html>