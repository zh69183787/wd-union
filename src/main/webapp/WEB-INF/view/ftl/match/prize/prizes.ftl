<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign ctx = pageContext.contextPath>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery/jquery.ui.all.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" title="style_blue" media="screen"/>
    <link rel="stylesheet" href="${ctx}/css/tree/style.css" type="text/css">

    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${ctx}/js/grid.js"></script>
    <script type="text/javascript" src="${ctx}/js/message_box.js"></script>
    <script type="text/javascript" src="${ctx}/js/custom_widgets.js"></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.tree.core.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.tree.excheck.min.js"></script>

</head>
<body>
<div id="content">

    <div>

        <div class="box themed_box">
            <h2 class="box-header">竞赛奖项维护 </h2>

            <div class="box-content">
                <input type="hidden" name="_method" value="put">
                <input type="hidden" name="checkStatus" value="">
                <table class="detailTb">
                    <tbody>
                    <tr>
                        <td width="100" class="bfont">年度:</td>
                        <td width="40%">${match.matchTheme.year!}</td>
                        <td width="100" class="bfont">竞赛名称:</td>
                        <td>${match.matchTheme.themeName!}</td>
                    </tr>
                    <tr>
                        <td class="bfont">类别:</td>
                        <td>${match.matchType!}</td>
                        <td class="bfont">竞赛主题:</td>
                        <td>${match.matchName}</td>
                    </tr>
                    <tr>
                        <td class="bfont">开始日期:</td>
                        <td><#if match.beginDate??>${match.beginDate?date}</#if></td>
                        <td class="bfont">结束日期:</td>
                        <td><#if match.beginDate??>${match.endDate?date}</#if></td>
                    </tr>
                    <tr>
                        <td class="bfont">附件:</td>
                        <td colspan="3">
                        <#list match.attachments  as attachment>
                            <a style="line-height: 18px;" class="button white" href="${attachment.attachUrl}"><span
                                    class="icon_text pack _276"></span>${attachment.attachName!}</a>
                        </#list>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <br>

                <div class="box">
                    <h2 class="box-header">奖项列表</h2>

                    <div class="fg-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix">
                        <div>
                            <button id="addToolBarBtn" class="button white"><span class="icon_text addnew"></span>新 增
                            </button>
                            <button id="editToolBarBtn" class="button white"><span class="icon_text edit"></span>修 改
                            </button>
                            <button id="viewToolBarBtn" class="button white"><span
                                    class="icon_text pack _12"></span>查 看
                            </button>
                            <button class="button white" id="delToolBarBtn"><span
                                    class="icon_text cancel"></span>删 除
                            </button>
                        </div>
                    </div>
                    <div class="box-content box-table">
                        <table class="tablebox">
                            <thead class="table-header">
                            <tr>
                                <th class="tc" style="width:10px"><input type="checkbox" id="chkAll"></th>
                                <th class="tc" width="30">序号</th>
                                <th class="tc">类别</th>
                                <th class="tc">名称</th>
                                <th class="tc">奖金</th>
                                <th class="tc">数量</th>
                                <th class="tc" width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list list as prize>
                            <tr class="odd">
                                <td class="tc">
                                    <input type="checkbox" value="${prize.prizeId}" name="chk">
                                </td>
                                <td class=" first tc"><input type="hidden" value="">
                                ${(prize_index+1)}
                                </td>
                                <td class="tc"> <#if prize.prizeType??>
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
                                <td class="tc"> ${prize.prizeName}</td>
                                <td class="tc"> ${prize.bonus}</td>
                                <td class="tc"> ${prize.quantity}</td>
                                <td class="tc"><a name="dept" class="button white"><span>参赛单位</span></a></td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>

<div style="display:none;" id="departmentDialog" title="选择参赛单位">
    <ul id="departmentTree" class="ztree"></ul>
</div>

<script>
    $(function () {
        var treeNodes;
        $.initGridAction({ns: "${ctx}", module: "match/${match.matchId}/prize"});
        $.initContextPath("${ctx}");

        $("#departmentTree").wrapDepartmentTree({selectedMulti: true});
        $("#departmentTree").bind("initCompleted", function (event, nodes) {
            treeNodes = nodes
        });
        $("a[name=dept]").click(function () {
            var prizeId = $(this).getRowId();
            $("#departmentDialog").dialog({
                autoOpen: true,
                width: 376,
                height: 500,
                modal: true,
                resizable: false,
                open: function () {
                    $.fn.zTree.getZTreeObj("departmentTree").checkAllNodes(false);
                    $.loadApplicantDepartment({matchId: "${match.matchId}", prizeId: prizeId}, function (data) {
                        for (var i = 0; i < treeNodes.length; i++) {
                            for (var j = 0; j < data.appDept.length; j++) {
                                if (treeNodes[i].id.toString() == data.appDept[j].departmentId) {
                                    $.fn.zTree.getZTreeObj("departmentTree").checkNode(treeNodes[i], true, true);
                                }
                            }
                        }
                    })
                },
                buttons: {
                    "关闭": function () {
                        $(this).dialog("close");
                    },
                    "确定": function () {
                        var nodes = $.fn.zTree.getZTreeObj("departmentTree").getCheckedNodes();
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


                    }
                }
            });
        });
    })
</script>
</body>
</html>
