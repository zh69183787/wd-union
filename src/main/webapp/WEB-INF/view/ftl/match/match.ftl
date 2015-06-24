<!DOCTYPE html>
<html lang="cn">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> 工会立功和劳动竞赛 </title>
    <#include "inc/common.ftl">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/uploadify/uploadify.css">
    <link rel="stylesheet" href="${ctx}/css/tree/style.css" type="text/css">


    <script type="text/javascript" src="${ctx}/js/jquery.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/js/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/grid.js"></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.tree.core.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/tree/jquery.tree.excheck.min.js"></script>
    <style>
        span{display: inline;}
    </style>
</head>
<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="${ctx}/css/stpt/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li><a href="${ctx}/match/matches">专项主题</a></li>
                <li class="fin">专项主题表单</li>
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
        <form action="${ctx}/match<#if match.matchId??>/${match.matchId}</#if>" method="post">
            <input type="hidden" name="matchId" value="${match.matchId!}"/>
            <input type="hidden" name="departmentId" value="${match.departmentId!}"/>
        <#if match.matchId??><input type="hidden" name="_method" value="put"/></#if>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">专项主题名：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="matchName" value="${match.matchName!}">
                    </td>
                    <td class="t_r lableTd">类别：</td>
                    <td>
                        <select name="matchType">
                            <option value="1">职工技能素质</option>
                            <option value="2">运营安全服务管理</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">开始日期：</td>
                    <td>
                        <input class="input_xxlarge datepicker" type="text" name="beginDate"
                               value="<#if match.beginDate??>${match.beginDate?date}</#if>">
                    </td>
                    <td class="t_r lableTd">结束日期：</td>
                    <td>
                        <input class="input_xxlarge datepicker" type="text" name="endDate"
                               value="<#if match.endDate??>${match.endDate?date}</#if>">
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">竞赛名称：</td>
                    <td>
                        <select id="matchThemeId" name="matchThemeId">
                        </select>
                    </td>
                    <td class="t_r lableTd">考评部门：</td>
                    <td>
                        <input class="input_xxlarge" readonly type="text" name="department" value="${match.department!}"> <a id="selDepartment" style="display:inline;cursor:pointer">选择考评部门</a>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">附件：</td>
                    <td colspan="3">
                        <div id="upload-file"></div>
                    <table width="60%" id="attachTable" class="table_1" style="<#if (match.attachments)??> <#if (match.attachments)?size < 1>display:none;</#if></#if>">
                    <tbody id="attachmentList">
                    <tr class="tit">
                        <td class="t_c" style="width:10px; display:none;"><input type="checkbox" id="chkAll"></td>
                        <td class="t_c">文件名</td>
                        <td class="t_c">文件大小</td>
                        <td class="t_c" style="width:100px">操作</td>
                    </tr>

                        <#list match.attachments  as attachment>
                        <tr>
                            <td style="display:none;">
                                <input type="hidden" name="attachments[${attachment_index}].removed">
                                <input type="checkbox"
                                       name="attachments[${attachment_index}].attachmentId"
                                       checked="checked"
                                       value="${attachment.attachmentId}"></td>
                            <td class="t_c">${attachment.attachName!}</td>
                            <td class="t_c">${attachment.attachSize}</td>
                            <td class="t_c"><a name="delBtn" class="mr5" href="#">取 消</a>
                            </td>
                        </tr>
                        </#list>

                    </tbody>
                    </table>


                    </td>
                </tr>
                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="submit"  value="保 存"/>&nbsp;
                        <input type="button" value="后 退" onclick="location.href='${ctx}/match/matches'"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>
<div style="display:none;" id="departmentDialog" title="选择考评部门">
    <ul id="departmentTree" class="ztree"></ul>
</div>
<script>
    $(function () {



        $.initContextPath("${ctx}");
        initAttachmentGrid();
        $(".datepicker").datepicker();
        $("#matchThemeId").wrapMatchThemeSelect({label:"themeName",value:"matchThemeId",selectVal:"${match.matchThemeId!}"});

        $("#departmentTree").wrapDepartmentTree();
        $("#departmentTree").bind("initCompleted",function(event, nodes){
            for (var i = 0; i < nodes.length; i++) {
                if (nodes[i].id.toString() == $(":hidden[name=departmentId]").val()) {
                    $.fn.zTree.getZTreeObj("departmentTree").selectNode(nodes[i], true);
                }
            }
        });


        $("#selDepartment").click(function(){
            $("#departmentDialog").dialog({
                autoOpen: true,
                width: 376,
                height: 400,
                modal: true,
                resizable: false,
                buttons:{
                    "关闭": function() {
                        $( this ).dialog( "close" );
                    },
                    "确定": function() {
                        var nodes = $.fn.zTree.getZTreeObj("departmentTree").getSelectedNodes();
                        $.each(nodes,function(i,n){
                            $(":text[name=department]").val(n.name);
                            $(":hidden[name=departmentId]").val(n.id);
                        });
                        $( this ).dialog( "close" );
                    }
                }
            });
        });

        $("#upload-file").uploadify({
            height: 30,
            buttonText: '上传文件',
            swf: '${ctx}/js/uploadify/uploadify.swf',
            uploader: '${ctx}/attachment?format=json',
            fileObjName: "filedata",
            onUploadSuccess: function (file, data, response) {
                $("#attachTable").show();
                var i = $("#attachmentList > tr").length;
                var $html = $('<tr><td  style="display:none;"><input type="hidden" name="attachments['+i+'].removed"><input type="checkbox" name="attachments[' + i + '].attachmentId" checked="checked"></td><td  class="t_c"></td><td  class="t_c"></td><td class="t_c"><a class="mr5"  name="delBtn" href="#">取 消</a></td></tr>');
                $("#attachmentList").append($html);

                data = eval("(" + data + ")").attachment;
                $html.find(":checkbox").val(data.attachmentId);
                $html.find("td:eq(1)").html(data.attachName);
                $html.find("td:eq(2)").html(data.attachSize);

                initAttachmentGrid();
            },
            width: 120
        });


        var validator = $("form").validate({
            rules: {
                matchName: {
                    required: true
                },
                matchType: {
                    required: true
                },
                department: {required: true}
            },
            messages: {
                matchName: "请输入竞赛主题",
                matchType: "请选择竞赛类别",
                department: "请选择考评部门",
            }

        });
        $("#rtnBtn").click(function () {
            location.href = "${ctx}/match/matches";
        })
    })

    function initAttachmentGrid() {


        var $delBtn = $(".tablebox").find("a[name=delBtn]");
        $delBtn.click(function () {
            var $this = $(this);
            $.confirm("是否确认删除数据?", function () {

                var $hid =$this.parents("tr td:first :hidden[name$=removed]");
                $hid.val("1");
                $this.parents("tr").hide();
            })
        });

    }
</script>
</body>
</html>
