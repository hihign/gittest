<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
<script type="text/javascript">

	$(function(){
		//给"创建"按钮添加单击事件
		$("#createActivityBtn").click(function () {
			//初始化工作
			//重置表单
			$("#createActivityForm").get(0).reset();

			//弹出创建市场活动的模态窗口
			$("#createActivityModal").modal("show");
		});
		
		//给"保存"按钮添加单击事件
		$("#saveCreateActivityBtn").click(function () {
			//收集参数
			var owner=$("#create-marketActivityOwner").val();
			var name=$.trim($("#create-marketActivityName").val());
			var startDate=$("#create-startDate").val();
			var endDate=$("#create-endDate").val();
			var cost=$.trim($("#create-cost").val());
			var description=$.trim($("#create-description").val());
			//表单验证
			if(owner==""){
				alert("所有者不能为空");
				return;
			}
			if(name==""){
				alert("名称不能为空");
				return;
			}
			if(startDate!=""&&endDate!=""){
				//使用字符串的大小代替日期的大小
				if(endDate<startDate){
					alert("结束日期不能比开始日期小");
					return;
				}
			}

			var regExp=/^(([1-9]\d*)|0)$/;
			if(!regExp.test(cost)){
				alert("成本只能为非负整数");
				return;
			}
			//发送请求
			$.ajax({
				url:'workbench/activity/saveCreateActivity.do',
				data:{
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if(data.code=="1"){
						//关闭模态窗口
						$("#createActivityModal").modal("hide");
						//刷新市场活动列，显示第一页数据，保持每页显示条数不变(保留)

					}else{
						//提示信息
						alert(data.message);
						//模态窗口不关闭
						$("#createActivityModal").modal("show");//可以不写。
					}
				}
			});
		});
		$(".mydate").datetimepicker({
			language:"zh-CN",
			format:"yyyy-mm-dd",
			minView:"month",
			initialDate:new Date(),
			autoclose:true,
			todayBtn:true,
			clearBtn:true
		});
		//首页加载，默认显示第一页的十条数据，
		queryActivityByConditionForPage(1,10);

		//给查询按钮绑定单击事件
		$("#queryActivityBtn").click(function (){
			//给查询按钮固定每页显示记录条数
			queryActivityByConditionForPage(1,$("#pagination_div").bs_pagination('getOption','rowsPerPage'));
		});

		//给全选按钮添加单击事件
		$("#checkAll").click(function (){
			$("#tBody input[type='checkbox']").prop("checked",this.checked);
		});

		//给列表中所有checkbox添加单击事件
		$("#tBody").on("click","input[type='checkbox']",function (){
			if ($("#tBody input[type='checkbox']").size() == $("#tBody input[type='checkbox']:checked").size()) {
				$("#checkAll").prop("checked",true);
			}else {
				$("#checkAll").prop("checked",false);

			}
		});

		//给删除按钮添加单击事件
		$("#deleteActivityBtn").click(function (){
			var checkedIds = $("#tBody input[type='checkbox']:checked");
			if (checkedIds.size() == 0) {
				alert("请选择市场活动");
				return;
			}
			if (window.confirm("确认删除吗？")){
				var ids="";
				$.each(checkedIds,function (){
					ids+="id="+this.value+"&";
				});
				ids=ids.substr(0,ids.length-1);
				$.ajax({
					type:'post',
					url:'workbench/activity/deleteActivity.do',
					data:ids,
					dataType:'json',
					success:function (data){
						if (data.code == 1) {
							queryActivityByConditionForPage(1,$("#pagination_div").bs_pagination('getOption','rowsPerPage'))
						}else{
							alert(data.message);
						}
					}
				});
			}
		});

		//给修改按钮添加单击事件
		$("#editActivityBtn").click(function(){
			var checkedId = $("#tBody input[type='checkbox']:checked");
			//表单验证
			if (checkedId.size() == 0) {
				alert("请选择要修改的市场活动");
				return;
			}
			if (checkedId.size() > 1) {
				alert("只能选择一个市场活动");
				return;
			}
			var id=checkedId[0].value;
			$.ajax({
				type:'post',
				url:'workbench/activity/queryActivityById.do',
				data:{id:id},
				dataType:'json',
				success:function (data){
					$("#edit-id").val(data.id);
					$("#edit-marketActivityOwner").val(data.owner);
					$("#edit-marketActivityName").val(data.name);
					$("#edit-startTime").val(data.startDate);
					$("#edit-endTime").val(data.endDate);
					$("#edit-cost").val(data.cost);
					$("#edit-description").val(data.description);
					$("#editActivityModal").modal("show");
				}
			})
		})

		//给更新按钮添加单击事件
		$("#saveEditBtn").click(function(){
			//收集参数
			var id=$("#edit-id").get(0).value;
			var owner=$("#edit-marketActivityOwner").get(0).value;
			var name=$.trim($("#edit-marketActivityName").get(0).value);
			var startDate=$("#edit-startTime").get(0).value;
			var endDate=$("#edit-endTime").get(0).value;
			var cost=$.trim($("#edit-cost").get(0).value);
			var description=$("#edit-description").get(0).value;
			//表单验证
			if (owner == "") {
				alert("所有者不能为空");
				return;
			}
			if (name==""){
				alert("市场活动名称不能为空");
				return;
			}
			if (startDate > endDate) {
				alert("开始日期必须小于结束日期");
				return;
			}
			var regExp=/^(([1-9]\d*)|0)$/;
			if(!regExp.test(cost)){
				alert("成本只能为非负整数");
				return;
			}
			$.ajax({
				type:'post',
				url:'workbench/activity/saveEditActivity.do',
				data:{
					id:id,
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				dataType:'json',
				success:function (data){
					if (data.code == 1) {
						//关闭修改市场活动的模态窗口，刷新列表
						$("#editActivityModal").modal("hide");
						queryActivityByConditionForPage($("#pagination_div").bs_pagination('getOption','currentPage'),$("#pagination_div").bs_pagination('getOption','rowsPerPage'))
					}else{
						$("#editActivityModal").modal("show");
						alert(data.message);
					}
				}
			})
		})

		//给批量导出按钮添加单击事件
		$("#exportActivityAllBtn").click(function (){
			window.location="workbench/activity/exportAllActivities.do";
		});

		//给选择导出添加单击事件
		$("#exportSelectedActivityBtn").click(function(){
			//收集参数
			var checkedId=$("#tBody input[type='checkbox']:checked");
			//表单验证
			if (checkedId.size()==0){
				alert("请选择要导出的市场活动");
				return;
			}
			var ids="";
			$.each(checkedId,function (index,id){
				ids+="id="+id.value+"&";
			});
			ids=ids.substr(0,ids.length-1);
			window.location="workbench/activity/exportSelectedActivity.do?"+ids;
		});

		//给上传按钮添加单击事件
		$("#uploadActivityBtn").click(function(){
			$("#importActivityModal").modal("show");
		});

		//给导入按钮添加单击事件
		$("#importActivityBtn").click(function(){
			//这样只能获取文件的名字
			var fileName = $("#activityFile").val();
			//表单验证：是否为xls文件
			var suffix = fileName.substr(fileName.lastIndexOf(".")+1).toLowerCase();
			if (suffix != "xls") {
				alert("只能导入.xls文件");
				return;
			}
			//通过它DOM对象的files属性真正拿到文件本身
			var activityFile = $("#activityFile")[0].files[0];
			if (activityFile.size > 5 * 1024 * 1024) {
				alert("文件不能超过5MB");
				return;
			}

			/*向后台提交数据：
			* 	data:"id=1&id=2..."
			* 	data:{id:"1",name:"zs"}
			* 这两种提交数据方式，只能提交字符串数据
			*
			* 提交二进制数据：
			* 	Ajax提供的接口（类）： FormData，它既能提交文本数据也能提交二进制数据
			* */
			var formData = new FormData();
			formData.append("activityFile",activityFile);
			formData.append("userName","张三");
			$.ajax({
				type:'post',
				url:"workbench/activity/fileUpload.do",
				data:formData,
				dataType:'json',
				processData:false,//设置ajax向后台提交参数之前，是否把参数统一转换成字符串：true--是,false--不是,默认是true
				contentType:false,//设置ajax向后台提交参数之前，是否把所有的参数统一按urlencoded编码：true--是,false--不是，默认是true
				success:function(data){
					if (data.code == 1) {
						alert("成功导入"+data.retData+"记录");
						$("#importActivityModal").modal("hide");
						queryActivityByConditionForPage(1,$("#pagination_div").bs_pagination("getOption","rowsPerPage"));
					}else{
						alert(data.message);
						$("#importActivityModal").modal("show");
					}
				}
			});
		});
	});

	function queryActivityByConditionForPage(pageNo,pageSize){
		var name=$("#activityName").val();
		var owner=$("#activityOwner").val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();

		$.ajax({
			type:'post',
			url:"workbench/activity/queryActivity.do",
			data:{
				name:name,
				owner:owner,
				startDate:startDate,
				endDate:endDate,
				pageNo:pageNo,
				pageSize:pageSize
			},
			dataType:'json',
			success:function (data){
				$("#totalRowsB").text(data.count);
				var html="";
				$.each(data.activityList,function (index,activity){
					html+="<tr class=\"active\">"
					html+="	<td><input type=\"checkbox\" value=\""+activity.id+"\"/></td>"
					html+="	<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/activity/queryActivityDetail.do?id="+activity.id+"';\">"+activity.name+"</a></td>"
					html+="	<td>"+activity.owner+"</td>"
					html+="	<td>"+activity.startDate+"</td>"
					html+="	<td>"+activity.endDate+"</td>"
					html+="</tr>"
				});
				$("#tBody").html(html);
				var totalPages = 1;
				if (data.count%pageSize == 0){
					totalPages=data.count/pageSize;
				}else{
					totalPages=parseInt(data.count/pageSize) + 1;
				}
				$("#pagination_div").bs_pagination({
					currentPage:pageNo,
					rowsPerPage:pageSize,
					totalRows:data.count,
					totalPages:totalPages,
					visiblePageLinks:5,
					showGoToPage: true,
					showRowsPerPage:true,
					showRowsInfo: true,

					onChangePage:function(event,obj){
						queryActivityByConditionForPage(obj.currentPage,obj.rowsPerPage);
					}
				});
			}
		});
	}
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="createActivityForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityOwner">
								  <c:forEach items="${userList}" var="u">
									   <option value="${u.id}">${u.name}</option>
								  </c:forEach>
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-marketActivityName">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="create-startDate" readonly>
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="create-endDate" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveCreateActivityBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<input type="hidden" id="edit-id"/>
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
									<c:forEach items="${userList}" var="u">
										<option value="${u.id}">${u.name}</option>
									</c:forEach>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="edit-endTime" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveEditBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 导入市场活动的模态窗口 -->
    <div class="modal fade" id="importActivityModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <div style="position: relative;top: 20px; left: 50px;">
                        请选择要上传的文件：<small style="color: gray;">[仅支持.xls]</small>
                    </div>
                    <div style="position: relative;top: 40px; left: 50px;">
                        <input type="file" id="activityFile">
                    </div>
                    <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
                        <h3>重要提示</h3>
                        <ul>
                            <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                            <li>给定文件的第一行将视为字段名。</li>
                            <li>请确认您的文件大小不超过5MB。</li>
                            <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                            <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                            <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                            <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="activityName">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="activityOwner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="queryActivityBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createActivityBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editActivityBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteActivityBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
                    <button type="button" class="btn btn-default" id="uploadActivityBtn" ><span class="glyphicon glyphicon-import"></span> 上传列表数据（导入）</button>
                    <button id="exportActivityAllBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（批量导出）</button>
                    <button id="exportSelectedActivityBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（选择导出）</button>
                </div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="checkAll"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="tBody">
<%--						<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>--%>
					</tbody>
				</table>
				<div id="pagination_div"></div>
			</div>
			
	<%--		<div style="height: 50px; position: relative;top: 30px;">
				<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b id="totalRowsB">50</b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							10
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div>--%>
			
		</div>
		
	</div>
</body>
</html>