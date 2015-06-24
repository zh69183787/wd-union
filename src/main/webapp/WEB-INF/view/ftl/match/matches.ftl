<!DOCTYPE html>
<html lang="cn">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
    <#include "inc/common.ftl">
    <link rel="stylesheet" href="${ctx}/css/tree/style.css" type="text/css">

    <script type="text/javascript" src="${ctx}/js/grid.js"></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.tree.core.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.tree.excheck.min.js"></script>

</head>
<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/match/matches">专项主题</a></li>
                <li class="fin">专项主题列表</li>
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
                    <form action="${ctx}/match/matches" method="get">


                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="t_r">竞赛名称</td>
                                <td>
                                    <input name="matchTheme.themeName" type="text"
                                           value="${match.matchTheme.themeName!}" class="input_large">
                                </td>
                                <td class="t_r">专项主题</td>
                                <td>
                                    <input name="matchName" type="text" value="${match.matchName!}" class="input_large">
                                </td>

                                <td class="t_r">主题状态</td>
                                <td>
                                    <select>
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
                <h5 class="fl"><a href="#" class="colSelect fl">专项主题列表</a></h5>
                <div class="fr">
                    <input type="button" id="addToolBarBtn"  value="新 增" style="margin-left: 5px"/>
                    <input id="editToolBarBtn" type="button"  value="修 改" style="margin-left: 5px">
                    <input type="button" id="viewToolBarBtn"  value="查 看" style="margin-left: 5px">
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
                    <td class="t_c">类别</td>
                    <td class="t_c">专项主题</td>
                    <td class="t_c">开始日期</td>
                    <td class="t_c">结束日期</td>
                    <td class="t_c">考评部门</td>
                    <td class="t_c">经办人</td>
                    <td class="t_c">状态</td>
                    <td class="t_c" style="width:230px">操作</td>
                </tr>

                <#list list as match>
                <tr>
                    <td class="t_c">
                        <input type="checkbox" value="${match.matchId}" name="chk">
                        <input type="hidden" value="${match.operatorId}">
                    </td>
                    <td class="t_c">
                    ${(match_index+1+(pageSize*(pageIndex-1)))}
                    </td>
                    <td class="t_c">
                        <@spring.message "match.type.${match.matchType}" />
                    </td>
                    <td class="t_c">
                    ${match.matchName}
                    </td>
                    <td class="tc">
                        <#if match.beginDate??>${match.beginDate?date}</#if>
                    </td>
                    <td class="t_c">
                        <#if match.endDate??>${match.endDate?date}</#if>
                    </td>
                    <td class="t_c">
                    ${match.department}
                    </td>
                    <td class="t_c">
                    ${match.operator!}
                    </td>
                    <td class="t_c">
                        <span class="bullet bullet-green"></span>
                    </td>
                    <td class="t_c">
                        <a class="mr5" name="dept_${match.departmentId}">经办人</a>
                        <a class="mr5" name="prize">奖项</a>
                        <a class="mr5">审核</a>
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

<div style="display:none;" id="operatorDialog" title="选择经办人">
    <ul id="tree" class="ztree"></ul>
</div>

<script>
    $(function () {
        $.initContextPath("${ctx}");
        $("input[name='matchTheme.themeName']").wrapMatchThemeAutoCompleted({label: "themeName"});
        $.initGridAction({ns: "${ctx}", module: "match"});
        $("#delForm").append('<input type="hidden" name="matchThemeId" value="${RequestParameters.matchThemeId!}"/>')
        $("a[name=prize]").click(function () {
            location.href = "${ctx}/match/"+$(this).getRowId()+"/prize/prizes";
        });
        $("a[name^=dept_]").click(function () {
            var matchId= $(this).getRowId();
            var deptId = $(this).attr("name").substring(5);
            var operatorId = $(this).parents("tr").find("td:eq(0) > :hidden").val();
            if(!deptId){
                $.info("没有指定考评部门!");
                return false;
            }
            $("#operatorDialog").dialog({
                autoOpen: true,
                width: 376,
                height: 400,
                modal: true,
                resizable: false,
                open:function(){
                    $("#tree > li").remove();
                    $("#tree").wrapEmployeeTree({deptId:deptId});
                    $("#tree").bind("initCompleted",function(event,nodes){
                        for (var i = 0; i < nodes.length; i++) {
                            if (nodes[i].id.toString() == operatorId) {
                                $.fn.zTree.getZTreeObj("tree").selectNode(nodes[i], true);
                            }
                        }
                    })

                },
                buttons:{
                    "确定": function() {
                        var nodes = $.fn.zTree.getZTreeObj("tree").getSelectedNodes();
                        if(nodes.length==0)
                        {
                            return false;
                        }else{
                            for(var i = 0;i<nodes.length;i++){
                                $.assignOperator({"matchId":matchId,"operatorId":nodes[i].id,"operator":nodes[i].name},function(data){
                                    $("#operatorDialog").dialog( "close" );
                                    if(data.result=="1"){
                                        $(":checkbox").each(function(i,n){
                                            var chk = $(n);
                                            if(chk.val()==matchId){
                                                chk.parents("tr").find("td:eq(7)").text(data.match.operator);
                                                chk.parents("tr").find("td:eq(0) > :hidden").val(data.match.operatorId);
                                            }
                                        })
                                    }
                                });
                            }
                        }

                    },
                    "关闭": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        });

    });
</script>

</body>

</html>
