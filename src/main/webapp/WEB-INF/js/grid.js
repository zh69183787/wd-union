/**
 * Created by Administrator on 2014/8/26.
 */
(function ($) {
    $.extend({
        initGridAction: function (setting) {
            var defaults = {
                module: "",
                ns: "/",
                addToolBar: "#addToolBarBtn",
                delToolBar: "#delToolBarBtn",
                del: "#delsBtn",
                viewToolBar: "#viewToolBarBtn",
                view: ".viewBtn",
                chkName: "chk",
                edit: ".editBtn",
                editToolBar: "#editToolBarBtn",
                chkAll: "#chkAll",
                async: false
            };
            var opts = $.extend(defaults, setting);

            var module = opts.module;
            var ns = opts.ns;
            var $addToolBarBtn = $(opts.addToolBar);
            var $delToolBarBtn = $(opts.delToolBar);
            var $del = $(opts.del);
            var $edit = $(opts.edit);
            var $viewToolBarBtn = $(opts.viewToolBar);
            var $view = $(opts.view);
            var $editToolBarBtn = $(opts.editToolBar);
            var chkName = opts.chkName;
            var $chkAll = $(opts.chkAll);

            ns = ns.indexOf("/") == -1 ? "/" + ns : ns;
            module = module.indexOf("/") == 0 ? module : "/" + module;

            $("body").append('<form id="delForm" method="post"><input type="hidden" name="_method" value="delete"></form>');

            $(".tablebox > tbody > tr:even").addClass("odd");
            $(".tablebox > tbody > tr:odd").addClass("even");

            $chkAll.change(function () {
                var $this = $(this);
                $(":checkbox[name=" + chkName + "]").each(function () {
                    $(this).prop("checked", $this.prop("checked"));
                });
            });

            $addToolBarBtn.click(function () {
                location.href = ns + module+location.search;
            });
            $editToolBarBtn.click(function () {

                var $chkedObj = $(":checkbox[name=" + chkName + "][checked]");
                if ($chkedObj.isSelectedOne()) {
                    location.href = ns + module  + "/"+ $chkedObj.getSelectedVal();
                }
            });
            $edit.click(function () {
                location.href = ns + module + "/" + $(this).getRowId();
            });
            $del.click(function () {
                $.confirm("是否确认删除数据?",function(){
                    $("#delForm").attr("action",ns + module + "/" + $(this).getRowId()) ;
                    $("#delForm").submit();
                });
            });
            $delToolBarBtn.click(function () {

                var $chkedObj = $(":checkbox[name=" + chkName + "][checked]");
                if ($chkedObj.isSelected()) {

                    $.confirm("是否确认删除数据?",function(){
                        $("#delForm").attr("action",ns + module + "/" + $chkedObj.getSelectedVal()) ;
                        $("#delForm").submit();
                    });
                }
            });
            $view.click(function () {
                location.href = ns + module  + "/"+ $(this).getRowId();
            });
            $viewToolBarBtn.click(function () {

                var $chkedObj = $(":checkbox[name=" + chkName + "][checked]");
                if ($chkedObj.isSelectedOne()) {
                    location.href = ns + module + "/" + $chkedObj.getSelectedVal()+"/detail";
                }
            });
        },
    getUrlParam:function(name)
    {
        var reg
            = new RegExp("(^|&)"+
            name +"=([^&]*)(&|$)");
        var r
            = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return null;
    }
    });

    $.fn.extend({
        getSelectedVal: function () {
            var ids = new Array();
            $(this).each(function () {
                ids.push($(this).val());
            });
            return ids.toString();
        },
        getRowId: function () {
            var $chk = $(this).parents("tr").find(':checkbox');
//            $chk.attr("checked", "checked");
            return $chk.val();
        },
        isSelectedOne: function () {
            if ($(this).length != 1) {
                $.info("请选择一条记录!");
                return false;
            }
            return true;
        },
        isSelected: function () {
            if ($(this).length == 0) {
                $.info("请至少选择一条记录!");
                return false;
            }
            return true;
        }
    });
})(jQuery);
