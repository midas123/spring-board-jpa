var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            //_this.join();
        	_this.joinFileUpload();
        });
        
        $('#btn-login').on('click', function() {
        	_this.login();
        });
        
        $('.form-control').on('click', function(){
        	var _this2 = $(this).attr('id');
        	if($('#'+_this2+'-error').length>0){
        		$('#'+_this2+'-error').remove();
        	}
        });
    },
    join : function () {
        var data1 = {
            username: $('#username').val(),
            nickname: $('#nickname').val(),
            phonenumber: $('#phonenumber').val(),
            password: $('#password').val(),
            confirmpassword: $('#password2').val()
        };
        console.log("JSON.stringify: "+JSON.stringify(data1));
        $.ajax({
            type: 'POST',
            url: '/registration/2',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data1)
        }).done(function() {
            alert('회원 가입 되었습니다. 회원 이메일을 인증해주세요.');
            location.href="/";
        }).fail(function (response) {
        	markingErrorField(response);
        });
    },
    
    login : function () {
        var data2 = {
            username: $('#username').val(),
            password: $('#password').val()
        };
        console.log(data2);
        $.ajax({
            type: 'POST',
            url: '/login',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data2)
        }).done(function() {
            location.href="/board";
        }).fail(function (response) {
        	console.log(response);
        	alert('계정 또는 비밀번호가 다릅니다.');
        });
    },
    
    joinFileUpload : function(){
    	   var form = $('#form-data')[0];

    	    var data = new FormData(form);

    	    //$("#btnSubmit").prop("disabled", true);

    	    $.ajax({
    	        type: "POST",
    	        enctype: 'multipart/form-data',
    	        url: "/registration",
    	        data: data,
    	        processData: false, //prevent jQuery from automatically transforming the data into a query string
    	        contentType: false,
    	        cache: false,
    	        timeout: 600000,
    	        success : function (data) {
    	        	alert('회원 가입 되었습니다. 회원 이메일을 인증해주세요.');
    	            location.href="/";
    	            //$("#btnSubmit").prop("disabled", false);
    	        },
    	        error : function ($xhr) {
    	        	 var res = $xhr.responseJSON;
    	        	 markingErrorField(res);
    	        }
    	    });
    	
    }
};


var markingErrorField = function (response) {
    //const errorFields = response.responseJSON.errors; //join
    const errorFields = response.errors; //joinFileUpload
    
    if(!errorFields){
        alert(response.response.message);
        return;
    }

    var $field, error;
    for(var i=0, length = errorFields.length; i<length;i++){
        error = errorFields[i];
        $field = $('#'+error['field']);
        if($field && $field.length > 0){
        	$('#'+error['field']+'-error').remove();
            $field.after('<span class="error-message" id="'+error['field']+'-error">'+error.defaultMessage+'</span>');
        }
        
        if(error['code'] == "PasswordMatch"){
        	$('#password2-error').remove();
        	$('#password2').after('<span class="error-message" id="password2-error">'+error.defaultMessage+'</span>');
        }
        
        if(error['code'] == "confirm-email-errormessage"){
        		alert(error.defaultMessage);
        }
       
    }
    return false;
};


main.init();