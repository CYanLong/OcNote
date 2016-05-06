rules = { // 内置基础规则
        username : {
            required : true,
            max : 10,
            min : 4,
            regexp : /^[\w\u4e00-\u9fa5]{4,10}$/,
            alertInfo : {
                tooLong : "用户名长度不能超过10个字符",
                tooTall : "用户名长度不能少于4个字符",
                illegalInput : "用户名不能有特殊字符"
            },
            qualified : false
        },

        phone : {
            required : true,
            len : 11,
            regexp : /^(13|15|18|14|17)\d{9}$/,
            alertInfo : {
                illegalInput : "手机号码必须为11位数字",
                illegalStart : "号码须以 13、15、18、14、17开头"
            },
            qualified : false
        },

        email : {
            required : true,
            regexp: /^\w[\w\-]*@\w+\.[a-zA-Z\u4e00-\u9fa5]{2,6}$/,
            alertInfo : {
                illegalStart : "开头须是数字，字母、_",
                illegalInput : "不符合正常邮箱格式"
            },
            qualified : false
        }, 
        
        password : {
            required : true,
            min : 8,
            regexp : /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}$/,
            alertInfo : {
                tooTall : "长度需8位以上",
                mustHasDigit : "至少包含一个数字",
                mustHasLowerCase : "至少包含一个小写字母",
                mustHasUpperCase : "至少包含一个大写字母",
                mustHasSpecialLetter : "至少包含一个特殊字符", 
            },  
            qualified : false
        },

        reInputPassWord : {
            required : true,
            i : "",
            alertInfo : {
                illegalInput : "两次输入的密码不一致",
                emptyInput   : "请先输入密码"
            },
            qualified : false
        },

        verificationCode : {
            required : true,
            len : 4,
            regexp : /^[\d|a-z|A-Z]{4}$/,
            alertInfo : {
                illegalInput : "验证码输入错误"
            },
            qualified : false
        } 
}

function validate ( Te , infoWrap ) {
   var $type = Te.type;
   var $value = Te.value;

   if( $value === "" ) {
        infoWrap.innerHTML = "输入框不能为空";
        return false;
   }

   switch ($type) {
        case "text" :
            if ( Te.dataset.contentType === "verificationCode" ) {
                // 验证码

                return "verificationCode";
            } else {
                // 用户名
                if ( rules["username"].regexp.test( $value ) ) {
                    return "username";
                } else {
                    if ( $value.length > rules["username"].max ) {
                        infoWrap.innerHTML = rules["username"].alertInfo.tooLong;
                    } else if ( $value.length < rules["username"].min ) {
                        infoWrap.innerHTML = rules["username"].alertInfo.tooTall;
                    } else {
                        infoWrap.innerHTML = rules["username"].alertInfo.illegalInput
                    }
                }
            } 
            break;
        case "tel" :
            if ( rules["phone"].regexp.test( $value ) ) {
                return "phone";
            } else {
                 if ( $value.length !== rules["phone"].len || /[^\d]/.test($value) ) {
                        infoWrap.innerHTML = rules["phone"].alertInfo.illegalInput;
                    } else if ( !/^13|15|18|14|17/.test($value) ) {
                        infoWrap.innerHTML = rules["phone"].alertInfo.illegalStart;
                    } 
            }
            break;
        case "email" :
            if ( rules["email"].regexp.test( $value ) ) {
                return "email";
            } else {
                if ( !/^\w/.test($value) ) {
                    infoWrap.innerHTML = rules["email"].alertInfo.illegalStart;
                } else {
                    infoWrap.innerHTML = rules["email"].alertInfo.illegalInput;
                }
            }
            break;
        case "password":
            if( Te.dataset.contentType === "reInputPassWord" ) {
                // 确认密码
                if ( rules["reInputPassWord"].i === $value ) {
                    return "reInputPassWord";
                } else {
                    if ( rules["reInputPassWord"].i === "" ) {
                        infoWrap.innerHTML = rules["reInputPassWord"].alertInfo.emptyInput;
                    } else {
                        infoWrap.innerHTML = rules["reInputPassWord"].alertInfo.illegalInput;
                    }
                }
            } else {
                if ( rules["password"].regexp.test( $value ) ) {//  输入密码
                    rules["reInputPassWord"].i = $value;
                    return "password";
                } else {
                   if ( $value.length < rules["password"].min ) {
                        infoWrap.innerHTML = rules["password"].alertInfo.tooTall;
                    } else if ( !/(?=.*[\d])/.test($value) ) {
                        infoWrap.innerHTML = rules["password"].alertInfo.mustHasDigit;
                    }
                    rules["reInputPassWord"].i = $value; 
                }
            } 
        break;
    }

    return false;
}


require.config({
    paths : {
        "util" : ["util"]
    } 
});

require(["util"] , function (util) {
    $ = new u();

    var navsSlider  = $.$(".navs-slider");
    var navsSliderA = $.$(".navs-slider a");

    var oView       = $.$(".log-in-wrap .view");
    var oLogBtn     = $.$(".log-in-wrap form [type=button]");
    
    var nowActive   = navsSliderA[0]
    navsSlider.addEventListener("click" , function (ev) {
        if( ev.target && ev.target.tagName === "A" && ev.target.nodeType === 1 ) {
            if( ev.target !== nowActive ) {
                $.removeClass(nowActive , "active");
                $.addClass(ev.target , "active");

                navsSlider.dataset.activeIndex === "0" 
                    ? navsSlider.dataset.activeIndex = "1" 
                    : navsSlider.dataset.activeIndex = "0";

                nowActive = navsSliderA[navsSlider.dataset.activeIndex];

                Array.prototype.forEach.call( oView , function ( value , idx ) {
                    $.removeClass(value , "active");
                })

                $.addClass(oView[navsSlider.dataset.activeIndex] , "active");
            }
        }
    });

    
    var registerInputG = $.$(".register .input-wrap input");
    var registerForm = $.$(".register");

    $.addEvent(oLogBtn[0] , "click.register" , function (target) {
        var fD = new FormData(registerForm);
        console.log(fD)
        console.log(oLogBtn[0].form[0].value)
        var rForm = oLogBtn[0].form;
        var username = rForm[0].value;
        var email = rForm[1].value;
        var password = rForm[2].value;
        $.Ajax({
        	url : "regist", 
            method : "POST",
            data : "username=" + username + "&email=" + email +"&password=" + password, 
            requestType:"application/x-www-form-urlencoded",
            success:function(data){
            	var oJson = JSON.parse(data);
            	if(isEmpty(oJson)){
            		window.location.href="index.html";
            	}else{
            		rForm[0].parentNode.children[1].innerHTML = oJson.usernameMsg;
            		rForm[1].parentNode.children[1].innerHTML = oJson.emailMsg;
            	}
            }
        })
        
        
    });
    function isEmpty(obj){
    	for(var value in obj){
    		return false;
    	}
    	return true;
    }
    var SignInInputG   = $.$(".sign-in .input-wrap input")
    var signInForm = $.$(".sign-in");
    $.addEvent(oLogBtn[1] , "click.SignIn" , function (target) {
    	 var rForm = oLogBtn[1].form;
    	 var loginName = rForm[0].value;
    	 var password = rForm[1].value;
    	 
    	 $.Ajax({
    		
    		 url: "login",
    		 method: "POST",
    		 data: "loginName=" + loginName +"&password=" + password,
    		 success:function(data){
    			 var oJson = JSON.parse(data);    			 
    			 if(isEmpty(oJson)){
             		window.location.href="index.html";
             	}else{
             		rForm[0].parentNode.children[1].innerHTML = oJson.usernameMsg;
             		rForm[1].parentNode.children[1].innerHTML = oJson.passwordMsg;
             	}
    		 }
    		 
    	 })    	
    });    
    
    var showView = $.$(".view");
    var riW      = $.$(".register .input-wrap");

    var rl       = $.$(".register .input-wrap label");
    $.delegateEvent( showView[0] , "input.validate" , "blur" , function (ev , target) {
        var index = Array.prototype.indexOf.call( riW , target.parentNode );
        var v = validate( target , rl[index] );
        if( !!v ) {
            if ( v === "username" || v === "email" ) {
                // 如果前端判断成功 ， 发送Ajax
             
                 $.Ajax({
                     url : v, 
                     method : "POST",
                     data : v + "=" + target.value,
                     requestType:"application/x-www-form-urlencoded",
                     success:function(data){
                    	 console.log("chenyanlong")
                    	 if(data === "false"){
                    		target.parentNode.children[1].innerHTML=v +" 已存在"; 
                    	 }
                     }
                 })
            } else {
                rules[v].qualified = true;
            }
            
        }
    },true);
    
   
    $.delegateEvent( showView[0] , "input.validate" , "focus" , function (ev , target) {
        var index = Array.prototype.indexOf.call( riW , target.parentNode );
        rl[index].innerHTML = "";

        rules[target.dataset.contentType].qualified = false;
    },true);
});