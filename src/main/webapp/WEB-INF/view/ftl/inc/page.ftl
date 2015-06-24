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
        $("#page-no").not(".ui-state-disabled").click(function () {
            $("#pageIndex").val($("#pageNumber").val());
            $form.submit();
        });
    });


</script>
<div class="clearfix"><span class="fl">${totalRows}条记录</span>
    <ul class="fr clearfix pager">
        <li>Pages:${pageIndex}/${totalPages}
            <input type="text" id="pageNumber" min="0" max="999" step="1" class="input_tiny" value="1">
            <input type="button" name="button" id="page-no" value="Go">
        </li>



        <li><a id="tabledata_last" href="javascript:void(0)" class="<#if totalPages?number <= pageIndex?number >ui-state-disabled</#if>">&gt;&gt;</a></li>


        <li>


            <a id="tabledata_next" href="javascript:void(0)" class="<#if totalPages?number <= pageIndex?number >ui-state-disabled</#if>" >下一页</a>

        </li>
        <li>

            <a id="tabledata_previous" href="javascript:void(0)" class="<#if pageIndex<=1 >ui-state-disabled</#if>">上一页</a>



        </li>


        <li><a id="tabledata_first" class="<#if pageIndex<=1 >ui-state-disabled</#if>" href="javascript:void(0)">&lt;&lt;</a></li>




    </ul>
</div>

</#macro>