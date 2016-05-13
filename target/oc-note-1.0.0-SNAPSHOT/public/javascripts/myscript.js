/*& init &*/ 
$ = new u();
M = Math;
R = Math.random;

function init() {
	var con = $.$( "#container" );
	con.style.height = $.viewHeight() + "px";

	$.addEvent( window , "resize" , function () {
		con.style.height = $.viewHeight() + "px";
	});

	$.addEvent( window , "resize" ,  initSelfPrompt );

	$.Ajax({
		url: "primaryCategorys",
		method: "GET",
		success: function(data){
			var oPrimary = JSON.parse(data);
			for(var i = 0 ; i < oPrimary.length;i++){
				var newLi = document.createElement("li");
				$.addClass( newLi , "m-li" );
				oListWrap.appendChild(newLi);
				newLi.innerHTML = '<a href="#'+oPrimary[i].id+'"><h1 class="m-li-title"><img src="public/img/folder.png" alt="folder">'+ oPrimary[i].categoryName +'(<span class="num">0</span>)<span class="m-li-title-opt"><img class="m-li-title-close" src="public/img/close.png" alt="close"><img class="m-li-title-add" src="public/img/add.png" alt=""></span></h1></a><div class="m-list-wrap"><ul class="m-list"></ul></div>';			
				
				var oSeconds = oPrimary[i].secondCategorys;
				for(var j = 0 ; j < oSeconds.length ; j++){
					var oList = $.findElement( "m-list" , newLi , $.findByClass );
					var oA = document.createElement("a");
					oA.href = "#" + oSeconds[j].id;
					oA.innerHTML = '<li class="m-list-li"><img src="public/img/file.png" alt="file">'+ oSeconds[j].categoryName +'<img class="m-list-li-close-task" src="public/img/close.png" alt="close-task"></li>';				
					
					oList.appendChild( oA );
					getNum();

					var oWrap = $.findElement( "m-list-wrap" , newLi , $.findByClass );
					var oUl   = $.findElement( "m-list" , oWrap , $.findByClass );
					!!!oWrap.offsetHeight ? 
						oWrap.style.height = 0
						: oWrap.style.height = oUl.offsetHeight + "px";				
				}
			}
		}
	})
	var dProfile = $.$(".top-nav-profile");
	$.Ajax({
		url: "username",
		method: "GET",
		success: function(data){
			dProfile.children[0].children[0].innerText = data;
		}
	})
	getNum();
	module1();
	module2();
	module3();
	initSelfPrompt();
	
	
}

var activeClassify = null;
var activeTask     = null;

// module1
var oListWrap = $.$(".all-task-list .list-wrap");
var mLis = $.findElement( "m-li" , oListWrap , $.findByClass );

// module2
var oConMask = $.$(".con-mask");
var oTaskProcessCon = $.$(".task-process-con");
var oTaskBlock = $.$(".task-process-con .task-block");

var oTaskProcess= $.$(".task-process");
var oTaskProcessA = $.$(".task-process-title a");
var oAddClassifyA   = $.$(".task-process .add-classify a");

// module3
var oEdit = $.$(".edit");
var oFinishEdit = $.$(".finish-edit");
var editContent = $.$(".edit-content");

var oContentMask = $.$(".content-mask");
var oContentTitle = $.$(".content-title");
var oConD = $.$(".con-d");

var oContent = $.$(".content");

function module1 () {
	//点击PrimaryCategory.
	$.delegateEvent( oListWrap , "h1.m-li-title" , "click" , function (ev , target) {
		var oLi = target.parentNode.parentNode;
		var oWrap = oLi.getElementsByTagName("div")[0];
		var oUl   = oWrap.getElementsByTagName("ul")[0];

		!!!oWrap.offsetHeight ? 
			oWrap.style.height = oUl.offsetHeight + "px"
			: oWrap.style.height = 0;
	});

	// 添加分类 primaryCategorys
	$.addEvent( $.$(".all-task-list .add-classify a") , "click" , function () {
		var name = prompt("请输入需要添加的分类名称");
		if(name === null) {
			return;
		}	
		$.Ajax({
			url: "primaryCategorys",
			method: "POST",
			data: "pName=" + name,
			requestType:"application/x-www-form-urlencoded",
			success:function(data){
				var newLi = document.createElement("li");
				$.addClass( newLi , "m-li" );
				
				oListWrap.appendChild(newLi);
				newLi.innerHTML = '<a href="#'+data+'"><h1 class="m-li-title"><img src="public/img/folder.png" alt="folder">'+ name +'(<span class="num">0</span>)<span class="m-li-title-opt"><img class="m-li-title-close" src="public/img/close.png" alt="close"><img class="m-li-title-add" src="public/img/add.png" alt=""></span></h1></a><div class="m-list-wrap"><ul class="m-list"></ul></div>';
			}
		});
	});

	// 添加新分类任务 secondCategorys
	$.delegateEvent(oListWrap , "img.m-li-title-add" , "click" , function (ev , target) {
		var name = prompt("请输入你需要新增的任务名称");
		if ( !name ) {
			return ;
		};
		var pId = target.parentNode.parentNode.parentNode.href.split("#")[1];
		$.Ajax({
			url: "secondCategorys",
			method: "POST",
			data: "sName=" + name + "&pId=" + pId,
			requestType: "application/x-www-form-urlencoded",
			success:function(data){
				var oLi = target.parentNode.parentNode.parentNode.parentNode;
				var oList = $.findElement( "m-list" , oLi , $.findByClass );
				var oA = document.createElement("a");
				oA.href = "#" + data;
				oA.innerHTML = '<li class="m-list-li"><img src="public/img/file.png" alt="file">'+ name +'<img class="m-list-li-close-task" src="public/img/close.png" alt="close-task"></li>';				
				
				oList.appendChild( oA );
				getNum();

				var oWrap = $.findElement( "m-list-wrap" , oLi , $.findByClass );
				var oUl   = $.findElement( "m-list" , oWrap , $.findByClass );
				!!!oWrap.offsetHeight ? 
					oWrap.style.height = 0
					: oWrap.style.height = oUl.offsetHeight + "px";
			}
		});	
	});

	// 删除分类 primaryCategory
	$.delegateEvent(oListWrap , "img.m-li-title-close" , "click" , function (ev , target) {
		var is = confirm("你是否真的要删除分类？");
		if (is) {
			var pId = target.parentNode.parentNode.parentNode.href.split("#")[1];
			$.Ajax({
				url:"primaryCategorys/" + pId,
				method:"DELETE",
				success:function(data){
					getNum();
					oListWrap.removeChild(target.parentNode.parentNode.parentNode.parentNode);
					
					editContent.contentEditable = "false";
					editContent.innerHTML = "";
					editContent.blur();
					oEdit.style.display = "block";
					oFinishEdit.style.display = "none";
					
					activeClassify = null;
					
					oConMask.style.display = "block";

					oContentMask.style.display = "block";
					oContentTitle.innerHTML = "";
					oConD.innerHTML = "";
					
					if( oTaskBlock.length === undefined ) {
						oTaskProcessCon.removeChild(oTaskBlock);
					} else {
						for( var i = 0 ; i<oTaskBlock.length ; i++ ) {
							oTaskProcessCon.removeChild(oTaskBlock[i]);
						}
					}
				}
			});
		} else {
			return;
		}
	});
	
	
	$.delegateEvent( oListWrap , "img li.m-list-li" , "click" , function (ev , target) {
		activeClassify = target;
		removeTaskBlock();
		var sId = activeClassify.parentNode.href.split("#")[1];
		
		$.Ajax({
			url: "tasks/" + sId,
			method: "GET",
			success: function(date){
				var oJson = JSON.parse(date);
				for(var i=0 ; i < oJson.length ; i++){
					var id = oJson[i].id;
					var time = oJson[i].createDate;
					var title = oJson[i].taskName;
					
					addTaskBlock(id, time, title);					
				}
			}
		})

	});
	function addTaskBlock (id, time , title) {
		
		var arrTask = $.$(".task-process-con .task-block");
		if(!(arrTask instanceof Array)){
			var arrTask = new Array(arrTask);
		}
		for(var i = 0 ; i < arrTask.length ; i++){
			var date = arrTask[i].children[0].innerText;//
			if(time === date){
				var li = document.createElement("li");
				$.addClass( li , "task-unfinish" );

				 li.innerHTML = title+'<a href=" #' + date + "/" + id+'"><img class="task-close" src="public/img/close.png" alt="task-close"></a><a href="#'+date + '/' + id+'"><img class="choose-unfinish" src="public/img/false.png" alt="true"></a><a href="#'+date + "/" +id+'"><img class="choose-finish" src="public/img/true.png" alt="true"></a>';
				 arrTask[i].children[1].appendChild(li);
				 return;
			}
		}
		
		var oDiv = document.createElement("div");
		
		$.addClass(oDiv,"task-block");
		oDiv.innerHTML += '<h1 class="tasks-data">'+time+'<a href="#'+ time +'/'+id +'"><img class="tasks-data-del" src="public/img/close.png" alt="add-data"></a><a href="#'+ time +'/'+ id+'"><img class="tasks-data-add" src="public/img/add.png" alt="add-data"></a></h1><ul class="task-block-list"><li class=" task-unfinish">'+title+'<a href="#'+ time +'/'+ id+'"><img class="task-close" src="public/img/close.png" alt="task-close"></a><a href="#'+ time +'/'+ id+'"><img class="choose-unfinish" src="public/img/false.png" alt="true"></a><a href="#'+ time +'/'+ id+'"><img class="choose-finish" src="public/img/true.png" alt="true"></a></li></ul></div>'
		
		oTaskProcessCon.appendChild(oDiv);
	}
	// 激活分类任务
	$.delegateEvent( oListWrap , "li.m-list-li" , "click" , function (ev , target) {
		var allLis = $.findElement( "m-list-li" , oListWrap , $.findByClass );
		
		for( var i = 0 ; i<allLis.length ; i++) {
			$.removeClass( allLis[i] , "m-list-li-active" );	
		}

		$.addClass( target , "m-list-li-active" );
		
		if( activeClassify !== null ) {
			oConMask.style.display = "none";
		}
	});
	
	// 删除分类任务
	$.delegateEvent( oListWrap , "img.m-list-li-close-task" , "click" , function (ev , target) {
		var is = confirm("确定是否要删除任务？");
		if( !is ) {
			return;
		} else {
			//得到target的父节点的父节点(a标签).
			var oA = target.parentNode.parentNode;	
			var hrefStr = oA.href;
			var sId = hrefStr.split("#")[1];
			$.Ajax({
				url:"secondCategorys/" + sId,
				method:"DELETE",
				success:function(data){
					
					var oList = oA.parentNode;
					oList.removeChild(oA);

					oList.parentNode.style.height = oList.offsetHeight + "px";			
					
					activeClassify = null;
					
					oConMask.style.display = "block";

					editContent.contentEditable = "false";
					editContent.innerHTML = "";
					editContent.blur();
					
					oEdit.style.display = "block";
					oFinishEdit.style.display = "none";

					oContentMask.style.display = "block";
					oContentTitle.innerHTML = "";
					oConD.innerHTML = "";

					var oTaskProcessCon = $.$(".task-process-con");
					var oTaskBlock = $.$(".task-process-con .task-block")

					if( oTaskBlock.length === undefined ) {
						oTaskProcessCon.removeChild(oTaskBlock);
					} else {
						for( var i = 0 ; i<oTaskBlock.length ; i++ ) {
							oTaskProcessCon.removeChild(oTaskBlock[i]);
						}
					}

					getNum();
				}
			})
		}
	});
	
	
	
	function removeTaskBlock() {
		var taskBlocks = $.ff( ".task-process-con .task-block" );
		Array.prototype.forEach.call(taskBlocks , function (value , idx , array) {
			oTaskProcessCon.removeChild(value);
		});
	}
}

function module2 () {
	// 改变激活样式
	$.delegateEvent( oTaskProcess , "a.choose-list" , "click" , function (ev , target) {
		for ( i = 0 ; i<oTaskProcessA.length ; i++ ) {
			$.removeClass( oTaskProcessA[i], "task-process-active");
		}

		$.addClass( target , "task-process-active" );
	});

	$.delegateEvent( oTaskProcess , "a.unfinish-list" , "click" , function (ev , target) {
		var a = $.$(".task-process-con .task-block .task-block-list li");
		var allLi = [];

		if( a.length === undefined ) {
			allLi.push(a);
		} else {
			allLi = a;
		}

		hiddenlist(allLi , "task-finish");
	});

	$.delegateEvent( oTaskProcess , "a.finish-list" , "click" , function (ev , target) {
		var a = $.$(".task-process-con .task-block .task-block-list li");
		var allLi = [];

		if( a.length === undefined ) {
			allLi.push(a);
		} else {
			allLi = a;
		}

		hiddenlist(allLi , "task-unfinish");
	});

	$.delegateEvent( oTaskProcess , "a.all-list" , "click" , function (ev , target) {
		var a = $.$(".task-process-con .task-block .task-block-list li");
		var allLi = [];

		if( a.length === undefined ) {
			allLi.push(a);
		} else {
			allLi = a;
		}

		hiddenlist(allLi);
	});

	// 选取所有未完成
	function hiddenlist(arr , tclass) {
		tclass = tclass || null;
		for( var i = 0 ; i<arr.length ; i++ ) {
			if( $.hasClass(arr[i] , tclass) ) {
				arr[i].style.display = "none";
			} else {
				arr[i].style.display = "block";
			}
		}
	}

	

	// 删除日程
	$.delegateEvent( oTaskProcess , "img.tasks-data-del" , "click" , function (ev , target) {
		var is = confirm("是否确定要删除日程，如删除，内部所有的任务全部会清空！");
		if( !is ) {
			return;
		}
		var tB = target.parentNode.parentNode.parentNode;
		var createTime = target.parentNode.parentNode.innerText;
		var sId = activeClassify.parentNode.href.split("#")[1];
		$.Ajax({
			url: "tasks/" + createTime + "?sId=" + sId,
			method: "DELETE",
			success:function(data){
				var oUl = tB.childNodes[1];
				var arrLi = oUl.childNodes;
				var arrTasksId = {};
				for (var i = 0 ; i < arrLi.lenght ; i++){
					arrTasksId = arrLi[i].lastChild.href.split("#")[1];
				}
				var con = tB.parentNode;
				con.removeChild(tB);
				
				activeTask = null;
				
				editContent.contenteditable = false;
				editContent.innerHTML = "";
				
				oContentTitle.innerHTML = "";
				oConD.innerHTML = "";
				
				oEdit.style.display = "block";
				oFinishEdit.style.display = "none";
				
				oContentMask.style.display = "block";
			}
		})
	});

	// 在已有时间线上添加task.
	$.delegateEvent( oTaskProcess , "img.tasks-data-add" , "click" , function (ev , target) {
		var name = prompt("请输入您需要添加的日程名？");
		if(name === "") {
			alert("日程名称不能为空");
			return;
		} else {
			if( name === null ) {
				return;
			}
		}
		var createTime = target.parentNode.parentNode.innerText;
		var sId = activeClassify.parentNode.href.split("#")[1];
		$.Ajax({
			url: "tasks/" + createTime,
			method: "POST",
			data: "sId=" + sId + "&taskName=" + name,
			success: function(data){
				var tB = target.parentNode.parentNode.parentNode;
				var list = $.findElement( "task-block-list" , tB , $.findByClass );

				var li = document.createElement("li");
				$.addClass( li , "task-unfinish" );
				
				li.innerHTML = name+'<a href=" #' + createTime + "/" + data+'"><img class="task-close" src="public/img/close.png" alt="task-close"></a><a href="#'+createTime + '/' + data+'"><img class="choose-unfinish" src="public/img/false.png" alt="true"></a><a href="#'+createTime + "/" +data+'"><img class="choose-finish" src="public/img/true.png" alt="true"></a>';
				list.appendChild(li);
			}
		})
	});

	// 选择日程已完成
	$.delegateEvent( oTaskProcess , "img.choose-finish" , "click" , function (ev,target) {
		
		var taskId = target.parentNode.href.split("#")[1];
		$.Ajax({
			url: "tasks/" + taskId,
			data: "isComplation=true", 
			method: "POST",
			success:function(data){
			    var oLi = target.parentNode.parentNode;
				
				$.removeClass( oLi , "task-unfinish" );
				$.addClass( oLi , "task-finish" );
				
				if( $.hasClass($.$(".task-process-title .unfinish-list") , "task-process-active") ) {
					oLi.style.display = "none";
				}	
			}
		})
		
	});

	// 选择日程未完成
	$.delegateEvent( oTaskProcess , "img.choose-unfinish" , "click" , function (ev,target) {
		
		var oLi = target.parentNode.parentNode;
		var taskId = target.parentNode.href.split("#")[1];
		$.Ajax({
			url: "tasks/" + taskId,
			method: "POST",
			data: "isComplation=false",
			success: function(data){
				$.removeClass( oLi , "task-finish" );
				$.addClass( oLi , "task-unfinish" );
				
				if( $.hasClass($.$(".task-process-title .finish-list") , "task-process-active") ) {
					oLi.style.display = "none";
				}				
			}
		})
		
	});

	// 选中日程
	$.delegateEvent( oTaskProcess , "li" , "click" , function (ev , target) {
		var tBLli = $.$(".task-block-list li");
		
		for( var i = 0 ; i<tBLli.length ; i++) {
			$.removeClass(tBLli[i] , "task-li-active");
		}
		$.addClass( target , "task-li-active" );
		activeTask = target;

		oContentTitle.innerHTML = target.innerHTML;
		
		var SbilingH = $.findElement( "tasks-data" , target.parentNode.parentNode , $.findByClass );
		var time = SbilingH.innerText ? SbilingH.innerText : SbilingH.textContent;
		oConD.innerHTML = time;

		if ( activeTask !== null ) {
			oContentMask.style.display = "none";

			editContent.contentEditable = "true";
			editContent.focus();
			
			oEdit.style.display = "none";
			oFinishEdit.style.display = "block";	
			var taskId = activeTask.children[0].href.split("#")[1];
			var u = "tasks/" + taskId;			
			$.Ajax({
				url: u,
				method: "GET",
				success: function(date){
					editContent.innerText = date;
				}
			})
		}
	});

	// 删除日程
	$.delegateEvent( oTaskProcess , "img.task-close" , "click" , function (ev , target) {
		var is = confirm("是否删除该日程任务？");
		if (is) {
			var taskId = target.parentNode.href.split("#")[1];
			$.Ajax({
				url: "tasks/" + taskId,
				method: "DELETE",
				success: function(data){
					var oLi = target.parentNode.parentNode;
					var oUl = oLi.parentNode;

					oUl.removeChild(oLi);
					activeTask = null;

					oContentMask.style.display = "block";

					editContent.contenteditable = false;
					editContent.innerHTML = "";
					
					oEdit.style.display = "block";
					oFinishEdit.style.display = "none";	

					oContentTitle.innerHTML = "";
					oConD.innerHTML = "";
				}
			})
			
		} else {
			return;
		}
	});

	// 新增日程 create Task
	$.addEvent( oAddClassifyA , "click" , function ( ev , target ) {
		if ( activeClassify === null ) {
			alert("请先在一个分类中创建一个任务，并点击锁定");
			return;
		}
		
		$.addClass( myselfPrompt , "show-myself-prompt" );
		
	});
	
	
	
	
	
}

function module3 () {
	$.delegateEvent( oContent , "img.edit" , "click" , function (ev , target) {
		if ( activeClassify === null ) {
			alert("请先锁定新的任务分类");
			return;
		}else if ( activeTask === null ) {
			alert("请先建立锁定新的日程");
			return;
		}

		editContent.contentEditable = "true";
		editContent.focus();
		oFinishEdit.style.display = "block";
		target.style.display = "none";
		
	});

	$.delegateEvent( oContent , "img.finish-edit" , "click" , function (ev , target) {
		if ( activeClassify === null ) {
			alert("请先锁定新的任务分类");
			return;
		}else if ( activeTask === null ) {
			alert("请先建立锁定新的日程");
			return;
		}
		var taskId = activeTask.lastChild.href.split("#")[1];
		var oContent = target.parentNode.parentNode.parentNode.parentNode;
		var taskName = oContent.childNodes[1].innerText;//children//contentText
//		var text = $.getInnerText(oContent.children[0]);
		var taskText = oContent.childNodes[5].innerText;
		$.Ajax({
			url: "tasks/" + taskId ,
			method: "PUT",
			data: "taskText=" + taskText,
			requestType: "application/x-www-form-urlencoded",
			success: function(data){
				editContent.contentEditable = "false";
				editContent.blur();
				oEdit.style.display = "block";
				target.style.display = "none";				
			}
		})
	});
}

// 自制提示框
var myselfPrompt = $.$(".myself-prompt");
var myselfUrl    = $.$(".myself-prompt .myself-url");

function initSelfPrompt () {
	myselfUrl.innerHTML = window.location.host + " ";
	myselfPrompt.style.top = $.viewHeight() * 0.2 + "px";
	myselfPrompt.style.left = $.viewWidth() * 0.5 + "px";
	$.delegateEvent( myselfPrompt , "button" , "click" , function (ev , target) {
		var  oMP = target.parentNode;
		var  isSure = false;
		
		if( target.dataset.submit === "true") {
			isSure = true;
		} else if (target.dataset.submit === "false") {
			isSure = false;
		}
		var dDate = $.findElement( "get-prompt-date" , oMP , $.findByClass );
		var dName = $.findElement( "prompt-input" , oMP , $.findByClass );

		var YYYY  = $.findElement( "get-year" , dDate , $.findByClass );
		var MM    = $.findElement( "get-month" , dDate , $.findByClass );
		var DD    = $.findElement( "get-day" ,dDate , $.findByClass );
		// 转类型
		if(isSure) {
			var Mvalue = MM.value,
				Dvalue = DD.value;
			
			
			if(MM.value.length < 2) {
				Mvalue = '0' + MM.value;
			}
			
			if(DD.value.length < 2) {
				Dvalue = '0' + DD.value;
			}
			
			console.log(Mvalue, Dvalue);
			
			var Datet = YYYY.innerHTML +"-"+ Mvalue + "-" + Dvalue;
			var Namet = dName.value;
			
			/* 从后端传数据过来，判断日期是否已经存在，如果存在弹出输入框 */ 
//			 if (Datet === ""){
//			 	alert("日程日期不能为空！");
//			 	return;
//			 }
			
			if(Namet === "") {
//				alert("请输入要添加的日程任务！");
//				return;
			} else {
				//从activeClassify中得到sId.
				var sId = activeClassify.parentNode.href.split("#")[1];
				$.Ajax({
					url: "tasks",
					method: "POST",
					data: "sId=" + sId + "&taskName=" + Namet + "&createDate=" + Datet,
					success: function(data){
						
						
						var arrTask = $.$(".task-process-con .task-block");
						if(!(arrTask instanceof Array)){
							var arrTask = new Array(arrTask);
						}
						for(var i = 0 ; i < arrTask.length ; i++){
							var date = arrTask[i].children[0].innerText;
							if(date === Datet){
								var li = document.createElement("li");
								$.addClass( li , "task-unfinish" );
				
								 li.innerHTML = Namet+'<a href=" #' + Datet + "/" + data+'"><img class="task-close" src="public/img/close.png" alt="task-close"></a><a href="#'+Datet + '/' + data+'"><img class="choose-unfinish" src="public/img/false.png" alt="true"></a><a href="#'+Datet + "/" +data+'"><img class="choose-finish" src="public/img/true.png" alt="true"></a>';
								 arrTask[i].children[1].appendChild(li);
								 return;
							}
						}
						
						//生成task节点.
						var oDiv = document.createElement("div");
						$.addClass( oDiv , "task-block" );
						
						
						oDiv.innerHTML = '<h1 class="tasks-data">'+ Datet +'<a href="javascript:;"><img class="tasks-data-del" src="public/img/close.png" alt="add-data"></a><a href="javascript:;"><img class="tasks-data-add" src="public/img/add.png" alt="add-data"></a></h1><ul class="task-block-list"><li class=" task-unfinish">'+ Namet +'<a href="#'+Datet + "/" + data +'"><img class="task-close" src="public/img/close.png" alt="task-close"></a><a href="#'+Datet + "/" + data +'"><img class="choose-unfinish" src="public/img/false.png" alt="true"></a><a href="#'+Datet + "/" + data +'"><img class="choose-finish" src="public/img/true.png" alt="true"></a></li></ul>';
						
						oTaskProcessCon.appendChild( oDiv );	

											
					}
				})
				
			}

		} else {
			$.removeClass( myselfPrompt , "show-myself-prompt" );

			return;
		}

		dName.value = "";
		$.removeClass( myselfPrompt , "show-myself-prompt" );
	});
}



function getNum () {
	var oLis = $.$(".m-li");
	if( $.ObjectTest(oLis) === "Array" ) {
		for (var i = 0 ; i<oLis.length; i++) {
			var oNum = $.findElement( "num" , oLis[i] , $.findByClass );
			var aLis = $.findElement( "m-list-li" , oLis[i] , $.findByClass );
			if( $.ObjectTest( aLis ) === "Array" ) {
				oNum.innerHTML = aLis.length;
			} else {
				oNum.innerHTML = 1;
			}		
		}
	} else {
		var oNum = $.findElement( "num" , oLis , $.findByClass );
		var aLis = $.findElement( "m-list-li" , oLis , $.findByClass );
		if( $.ObjectTest( aLis ) === "Array" ) {
			oNum.innerHTML = aLis.length;
		} else {
			oNum.innerHTML = 1;
		}		
	}
	(function () {
		var aN = $.$(".all-num");
		var num = $.$(".num");
		var r = 0;
		if( $.ObjectTest(num) === "Array" ) {
			for( var i = 0 ; i<num.length ; i++ ) {
				r += ~~num[i].innerHTML;
			}	
			aN.innerHTML = r;
		} else {
			aN.innerHTML = ~~num.innerHTML;
		}
	})();
}