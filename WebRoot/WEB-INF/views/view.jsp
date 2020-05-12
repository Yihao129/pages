<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = "<h1>hello</h1>";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="javascripts/bootstrap/jquery.min.js"></script>
<script>
function getUrlVars()
{
    var vars = [], hash;
    var url=decodeURIComponent(window.location.href)
    var hashes = url.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
var para=getUrlVars();

$.ajax({
	       url: "getPageCode",
	       data:{"page_id":para["page_id"]},
	       success: function(r){
	       		$("body").html(eval(r));
	       }
	       })

</script>