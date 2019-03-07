var main = {
    init : function () {
    	console.log("ready");
        var _this = this;
        $('#btn-save').on('click', function () {
        	console.log("start");
            _this.save();
        });
    },
    save : function () {
    	console.log($('#email').val());
        var data = {
            email: $('#email').val(),
            nickname: $('#nickname').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/join',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원 가입 되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    }

};

main.init();