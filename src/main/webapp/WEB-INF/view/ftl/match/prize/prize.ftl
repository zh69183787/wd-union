<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
<#include "inc/common.ftl">
    <link rel="stylesheet" href="${ctx}/css/tree/style.css" type="text/css">

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
                <li><a href="${ctx}/match/${prize.matchId!}/detail">专项主题明细</a></li>
                <li class="fin">奖项设置</li>
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
        <form action="${ctx}/match/${prize.matchId!}/prize<#if prize.prizeId??>/${prize.prizeId}</#if>"
              method="post">
            <div id="applicantDeptDiv"></div>
            <input type="hidden" name="matchId" value="${prize.matchId!}"/>
        <#if prize.prizeId??><input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="prizeId" value="${prize.prizeId!}"/></#if>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">奖项类型：</td>
                    <td colspan="3">
                        <select name="prizeType">
                            <option value="">请选择</option>
                            <option value="1">个人类</option>
                            <option value="2">集体类</option>
                            <option value="3">项目类</option>
                            <option value="4">项目成果类</option>
                        </select><select name="prizeSubType" style="margin-left: 10px;">
                        <option value="">请选择</option>
                        <option value="1">分等次竞赛</option>
                        <option value="2">不分等次竞赛</option>
                    </select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">奖项名称：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="prizeName"
                               value="${prize.prizeName!}">
                    </td>

                    <td class="t_r lableTd">奖金：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="bonus"
                               value="${prize.bonus!}">
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">参与人数：</td>
                    <td>
                        <select name="personRange">
                            <option value="">请选择</option>
                            <option value="1">单位</option>
                            <option value="2">集体人数<=20人</option>
                            <option value="3">20人< 集体人数 < 50人</option>
                            <option value="4">集体人数 >= 50人</option>
                        </select>
                    </td>
                    <td class="t_r lableTd">数量：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="quantity"
                               value="${prize.quantity}">

                    </td>
                </tr>
                <tr>
                    <td class="t_r lableTd">参赛单位：</td>
                    <td colspan="3">
                        <textarea class="form-field small" rows="5" readonly name="applicatDept"  id="applicatDept"></textarea><input type="button" id="selDepartment" value="选择参赛单位"/>
                    </td>
                </tr>

                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/match/${prize.matchId!}/detail'"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>



<div style="display:none;" id="departmentDialog" title="选择参赛单位">
    <ul id="departmentTree" class="ztree"></ul>
</div>
<script>
    $(function () {
        $.initContextPath("${ctx}");
        var applicantDept;
        $("#history").click(function () {
            location.href = "${ctx}/match/${prize.matchId!}/prize/prizes";
        });

        $("select[name=prizeType] > option[value=${prize.prizeType!}]").attr("selected", true);
        $("select[name=prizeSubType] > option[value=${prize.prizeSubType!}]").attr("selected", true);
        $("select[name=personRange] > option[value=${prize.personRange!}]").attr("selected", true);

        $.loadApplicantDepartment({"matchId": "${prize.matchId!}", "prizeId": "${prize.prizeId}"},function(data){
            applicantDept = data.appDept || [];
            addApplicantDept(applicantDept);
        });
        $("#selDepartment").click(function() {
            $("#departmentDialog").dialog({
                autoOpen: true,
                width: 376,
                height: 400,
                modal: true,
                resizable: false,
                open: function () {
                    $("#departmentTree > li").remove();

                    $("#departmentTree").wrapDepartmentTree({selectedMulti: true});
                    $("#departmentTree").bind("initCompleted", function (event, nodes) {
                        for (var i = 0; i < nodes.length; i++) {
                            for (var j = 0; j < applicantDept.length; j++) {
                                if (nodes[i].id.toString() == applicantDept[j].departmentId) {
                                    $.fn.zTree.getZTreeObj("departmentTree").checkNode(nodes[i], true, true);
                                    $.fn.zTree.getZTreeObj("departmentTree").selectNode(nodes[i], true);
                                }
                            }
                        }
                    })
                },
                buttons: {
                    "确定": function () {
                        var nodes = $.fn.zTree.getZTreeObj("departmentTree").getCheckedNodes();
                        if (nodes.length == 0) {
                            return false;
                        }
                        $.each(nodes, function (i, n) {
                            if (!n.isParent) {
                                var isExist = false
                                for(var i = 0;i<applicantDept.length;i++){
                                    if(applicantDept[i].departmentId == n.id){
                                        isExist = true;
                                        break;
                                    }
                                }
                                if(!isExist)
                                applicantDept.push({prizeId: "${prize.prizeId!}", departmentId: n.id, departmentName: n.name});
                            }
                        });
                        addApplicantDept(applicantDept);
                        $("#departmentDialog").dialog("close");


                    },
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    <#if error??>
        $.error("${error}");
    </#if>

    });

    function addApplicantDept(applicantDept){
        var info = "" ;
        $("#applicatDept,#applicantDeptDiv").html("");
        $.each(applicantDept,function(i,n){
            info = info+n.departmentName+";"

            $("#applicantDeptDiv").append("<input type='hidden' name='applicationDepartmentList["+i+"].prizeId' value='"+ n.prizeId+"'/><input type='hidden' name='applicationDepartmentList["+i+"].departmentName' value='"+ n.departmentName+"'/><input type='hidden' name='applicationDepartmentList["+i+"].departmentId' value='"+ n.departmentId+"'/>")
        })
        $("#applicatDept").text(info);
    }
</script>
</body>
</html>
