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
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/match/matches">专项主题</a></li>
                <li class="fin">专项主题明细</li>
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
    <div class="mb10 pt45">

        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4"><strong>专项主题信息</strong></th>
            </thead>
            <tbody>
            <tr>
                <td class="t_r lableTd">年度:</td>
                <td>
                ${match.matchTheme.year!}
                </td>
                <td class="t_r lableTd">竞赛名称:</td>
                <td>
                ${match.matchTheme.themeName!}
                </td>
            </tr>
            <tr>
                <td class="t_r lableTd">类别:</td>
                <td>${match.matchType!}</td>
                <td class="t_r lableTd">专项主题名:</td>
                <td>${match.matchName!}</td>
            </tr>
            <tr>
                <td class="t_r lableTd">开始日期:</td>
                <td><#if match.beginDate??>${match.beginDate?date}</#if></td>
                <td class="t_r lableTd">结束日期:</td>
                <td><#if match.beginDate??>${match.endDate?date}</#if></td>
            </tr>
            <tr>
                <td class="t_r lableTd">附件:</td>
                <td colspan="3">
                <#list match.attachments  as attachment>
                    <a href="${attachment.attachUrl}" style="text-decoration: underline" class="fl mr5" name="dept_${match.departmentId}">${attachment.attachName!}</a>
                </#list>
                </td>
            </tr>
            </tbody>

        </table>

    </div>
    <div class="filter">
        <div class="fn clearfix">
            <h5 class="fl"><a href="#" class="colSelect fl">奖项列表</a></h5>
            <div class="fr">
                <input type="button" id="addPrizeBtn"  value="新 增" style="margin-left: 5px"/>
                <input id="editPrizeBtn" type="button"  value="修 改" style="margin-left: 5px">
            <#--<input type="button" id="viewToolBarBtn"  value="查 看" style="margin-left: 5px">-->
                <input type="button"  id="delPrizeBtn" value="删 除" style="margin-left: 5px">
            </div>
        </div>
    </div>
    <div class="mb10">
        <table width="100%"  class="table_1">
            <tbody>
            <tr class="tit">
                <td class="t_c" style="width:10px"><input type="checkbox" id="prizeChkAll"></td>
                <td class="t_c" width="30">序号</td>
                <td class="t_c">类别</td>
                <td class="t_c">名称</td>
                <td class="t_c">奖金</td>
                <td class="t_c">数量</td>
                <td class="t_c" width="150">操作</td>
            </tr>

            <#list list as prize>
            <tr class="odd">
                <td class="t_c">
                    <input type="checkbox" value="${prize.prizeId}" name="prizeChk">
                </td>
                <td class=" first t_c"><input type="hidden" value="">
                ${(prize_index+1)}
                </td>
                <td class="t_c">  <#if prize.prizeType??>
                    <#if prize.prizeType == '1'>
                        个人类
                    <#elseif prize.prizeType == '2'>
                        集体类
                    <#elseif prize.prizeType == '3'>
                        项目类
                    <#elseif prize.prizeType == '4'>
                        项目成果类
                    </#if>
                </#if></td>
                <td class="t_c"> ${prize.prizeName}</td>
                <td class="t_c"> ${prize.bonus}</td>
                <td class="t_c"> ${prize.quantity}</td>
                <td class="t_c">
                    <a class="mr5" name="applicant">申报</a>
                    <a class="mr5" name="dept">参赛单位</a>
                </td>
            </tr>
            </#list>

            </tbody>
        </table>

    </div>

    <div class="mb10">
        <div class="tabs_2">
            <ul>
                <li class="selected"><a
                        href="javascript:void(0);"><span>先进个人申报资料</span></a>
                </li>
                <li ><a
                        href="javascript:void(0);"><span>先进集体申报资料</span></a>
                </li>
                <li ><a
                        href="javascript:void(0);"><span>优秀创新项目申报资料</span></a>
                </li>
                <li ><a
                        href="javascript:void(0);"><span>品牌成果申报资料</span></a>
                </li>


            </ul>
        </div>
        <div id="content"></div>
    </div>
</div>
<div style="display:none;" id="departmentDialog" title="选择参赛单位">
    <ul id="departmentTree" class="ztree"></ul>
</div>
<script>
    $(function () {
        $.initContextPath("${ctx}");
        var tree = $.fn.zTree;

        $("#departmentTree").wrapDepartmentTree({selectedMulti: true});
        var urls = [
            "${ctx}/personPrize/persons?prize.matchId=${match.matchId!}&containerId=content&async=true",
            "${ctx}/teamPrize/teams?prize.matchId=${match.matchId!}&containerId=content&async=true&pageIndex=1",
            "${ctx}/projectPrize/projectPrizes?prize.matchId=${match.matchId!}&containerId=content&async=true",
            "${ctx}/projectAchievement/projectAchievements?prize.matchId=${match.matchId!}&containerId=content&async=true"
        ];

        var treeNodes;
        $.initGridAction({ns: "${ctx}", module: "match/${match.matchId}/prize",addToolBar:"#addPrizeBtn",delToolBar:"#delPrizeBtn",editToolBar:"#editPrizeBtn",chkName:"prizeChk",chkAll:"#prizeChkAll"});



        $("#departmentTree").bind("initCompleted", function (event, nodes) {
            treeNodes = nodes
        });
        $("a[name=dept]").click(function () {
            var prizeId = $(this).getRowId();
            $("#departmentDialog").dialog({
                autoOpen: true,
                width: 376,
                height: 400,
                modal: true,
                resizable: false,
                open: function () {
                    tree.getZTreeObj("departmentTree").checkAllNodes(false);
                    $.loadApplicantDepartment({matchId: "${match.matchId}", prizeId: prizeId}, function (data) {
                        for (var i = 0; i < treeNodes.length; i++) {
                            for (var j = 0; j < data.appDept.length; j++) {
                                if (treeNodes[i].id.toString() == data.appDept[j].departmentId) {
                                    tree.getZTreeObj("departmentTree").checkNode(treeNodes[i], true, true);
                                }
                            }
                        }
                    })
                },
                buttons: {
                    "确定": function () {
                        var nodes = tree.getZTreeObj("departmentTree").getCheckedNodes();
                        if (nodes.length == 0) {
                            return false;
                        }
                        var param = "";
                        $.each(nodes, function (i, n) {
                            var x = i - 1;
                            if (!n.isParent) {
                                param += "applicationDepartmentList[" + x + "].departmentId=" + n.id + "&applicationDepartmentList[" + x + "].departmentName=" + n.name + "&applicationDepartmentList[" + x + "].prizeId=" + prizeId + "&";
                            }
                        });
                        $.saveDepartment("${match.matchId}", param, function () {
                            $("#departmentDialog").dialog("close");
                        })


                    },
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }
            });
        });

        $(".tabs_2").find("a").click(function(){
            var index = $(this).parent().index();
            $(this).parents("ul").find(".selected").removeClass("selected");
            $(this).parent().addClass("selected");
            loadContent(urls[index]);
        })

        $($(".tabs_2").find("a")[${RequestParameters.tab!0}]).trigger("click");

        $("a[name=applicant]").click(function(){
            location.href = "${ctx}/match/${match.matchId}/prize/"+$(this).getRowId()+"/applicantInfo";
        })
    });

    function loadContent(url){
        $("#content").html("");
        $("#content").load(url,function(){

            $("#delForm").remove();
        });
    }
</script>
</body>
</html>
