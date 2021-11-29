!function ($) {
    "use strict";
    $(function () {
        $('#register').submit(function (e) {
            e.preventDefault();
            $('#error').empty();
            const p = $(this).parsley();
            if (p.validate()) {
                   var userId = $('[name="userId"]').val();
                   var userPassword = $('[name="userOrderPassword"]').val();
                $.ajax({
                    type: 'GET',
                    url: '/api/member/check?userId='+userId+'&userPassword='+userPassword,
                    contentType: "application/json; charset=UTF-8",
                }).done(function (data) {
                   if(data){
                       register();
                   }else{
                       $('#error').append('기존 비밀번호가 다릅니다.');
                   }

                }).fail(function (data) {
                    $('#error').append(data);
                });

            }
        });
    });


    $(".select2").change(function() {
        $(".select2").trigger('input')
    });

    window.Parsley.addValidator('userId', {
        requirementType: 'regexp',
        validateString: function (value) {
            const pattern = /^[a-zA-Z0-9_]{4,20}$/;
            return pattern.test(value);
        },
        messages: {
            ko: "정상적인 아이디가 아닙니다. (한글, 특수문자 제외)"
        }
    });


    window.Parsley.addValidator('minlength', {
        requirementType: 'string',
        validateString: function (value) {
            return value.length > 5;
        },
        messages: {
            ko: "비밀번호는 최소 6자 이상으로 설정해주세요."
        }
    });

    window.Parsley.addValidator('iff', {
        requirementType: 'string',
        validateString: function (value, requirement) {
            return $(requirement).val() === value;
        },
        messages: {
            ko: "비밀번호가 일치하지 않습니다."
        }
    });

    // 한글입력막기 스크립트
    $(".not-kor").keyup(function(e) {
        if (!(e.keyCode >=37 && e.keyCode<=40)) {
            var v = $(this).val();
            $(this).val(v.replace(/[^A-Za-z0-9~!@#$%^&*()_+|<>?:{}]/gi,''));
        }
    });
}(window.jQuery);


function register(){
    const data = {
        userName: $('[name="userName"]').val(),
        userId: $('[name="userId"]').val(),
        enabled: 'true' === $('input[name="enabled"]:checked').val(),
        role: $('[name="role"]').val(),
        userPassword : $('[name="userNewPassword"]').val(),
    };
    $.ajax({
        type: 'PUT',
        url: '/api' + window.location.pathname,
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
    }).done(function (data) {
        swal({
            title: "관리자 " +data+"의 설정이 수정 되었습니다.",
            type: "success",
        }, function () {
            location.href = '/member/'+data;
        });
    }).fail(function (data) {
        $('#error').append(data.responseJSON.errors[0].defaultMessage);
    });
}