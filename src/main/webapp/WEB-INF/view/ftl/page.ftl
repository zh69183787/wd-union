<#macro pager pageIndex=1 pageSize=0 totalPages=1 totalRows=0 formId="" async=false containerId="">
<script type="text/javascript">
    jQuery(document).ready(function () {
    var $form = <#if formId=="">jQuery(jQuery("form")[0])<#else>jQuery("#${formId}")</#if>;
        $form.find(":submit").click(function(){
            $form.find(":hidden[name=pageIndex]").val(1);
            $form.find(":hidden[name=pageSize]").val(${pageSize});
        });
        <#if async>
            $form.submit(function(){
                $("#${containerId}").load($(this).attr("action")+"?"+$(this).serialize()+"&async=true&containerId=${containerId}");
                return false;
            });
        </#if>

        if ($form) {
            $form.append("<input type='hidden' id='pageIndex' name='pageIndex' value='${pageIndex}'/> ");
            $form.append("<input type='hidden' id='pageIndex' name='pageSize' value='${pageSize}'/> ");
        }
        $("#tabledata_last").not(".ui-state-disabled").click(function () {
            $("#pageIndex").val(${totalPages});
            $form.submit();
        });
        $("#tabledata_next").not(".ui-state-disabled").click(function () {
            $("#pageIndex").val(${pageIndex}+1);
            $form.submit();
        });
        $("#tabledata_first").not(".ui-state-disabled").click(function () {
            $("#pageIndex").val(1);
            $form.submit();
        });
        $("#tabledata_previous").not(".ui-state-disabled").click(function () {
            $("#pageIndex").val(${pageIndex}-1);
            $form.submit();
        });
        $(".page-no").not(".ui-state-disabled").click(function () {
            $("#pageIndex").val($(this).text());
            $form.submit();
        });
    });


</script>
<div style="text-align: right;line-height: 15px;" class="fg-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix">
    <div  class="dataTables_info" id="tabledata_info" >显示${pageIndex?number *pageSize?number-pageSize?number+1} 到  ${pageIndex?number*pageSize?number}
        共 ${totalRows}条记录
    </div>
    <ul class="pagination">
        <li> <a id="tabledata_first" href="javascript:" class="button white <#if pageIndex<=1 >ui-state-disabled</#if>">首页</a></li>
        <li> <a id="tabledata_previous" href="javascript:" class="button white <#if pageIndex<=1 >ui-state-disabled</#if>">上页</a></li>
        <#if totalPages?number <= 10 >
            <#if totalPages?number ==0 >
                <#assign startPageNo=0 />
            <#else>
                <#assign startPageNo=1 />
            </#if>
            <#assign maxPageNo=totalPages?number />
        <#else>
            <#if pageIndex?number < 6  >
                <#assign maxPageNo=10 />
                <#assign startPageNo=1 />
            <#elseif 5 < pageIndex?number && (pageIndex?number + 4) < totalPages?number>
                <#assign maxPageNo=pageIndex?number + 4 />
                <#assign startPageNo=pageIndex?number - 5 />
            <#else>
                <#assign maxPageNo=totalPages?number />
                <#assign startPageNo=totalPages?number - 9/>
            </#if>
        </#if>

        <#list startPageNo..maxPageNo?number as i>
            <li> <a href="javascript:" class="page-no button white <#if i== pageIndex?number>active </#if><#if totalPages?number == 0 >ui-state-disabled</#if>">${i}</a></li>
        </#list>
        <li> <a id="tabledata_next" href="javascript:" class="button white <#if totalPages?number <= pageIndex?number >ui-state-disabled</#if>">下页</a></li>
        <li> <a id="tabledata_last" href="javascript:" class="button white <#if totalPages?number <= pageIndex?number >ui-state-disabled</#if>">尾页</a></li>
    </ul>

</div>
</#macro> 