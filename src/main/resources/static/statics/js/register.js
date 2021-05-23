!function ($) {
    "use strict";

    $(function () {
        $('#signUp').submit(
            function (e) {
                e.preventDefault();
                $('#error').empty();
                const p = $('#signUp').parsley();
                if (p.validate()) {
                    const data = {
                    id: document.querySelector('[name="id"]').value,
                    userName: document.querySelector('[name="name"]').value,
                    password: document.querySelector('[name="password"]').value,
                    email: document.querySelector('[name="email"]').value,
                    phoneNo: document.querySelector('[name="phoneNo"]').value
                }

                    $.ajax({
                        type: 'POST',
                        url: '/api/signUp',
                        data: JSON.stringify(data),
                        contentType: "application/json; charset=UTF-8",
                    }).done(function (data) {
                        swal({
                            title: "관리자가 추가 되었습니다.",
                            type: "success",
                        }, function () {
                            location.href = '/account/login';
                        });
                    }).fail(function (data) {
                        $('#error').append(data.responseJSON.message.toString());
                    });
                }

            });
    });


    $(".select2").change(function () {
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
    $(".not-kor").keyup(function (e) {
        if (!(e.keyCode >= 37 && e.keyCode <= 40)) {
            var v = $(this).val();
            $(this).val(v.replace(/[^A-Za-z0-9~!@#$%^&*()_+|<>?:{}]/gi, ''));
        }
    });
}(window.jQuery);

//
// !function (e) {
//     "use strict";
//     ((e) => {
//         document.querySelector('#signUp').submit((e) => {
//             e.preventDefault();
//             const formData = e.parsley();
//             if (formData.validate()) {
//                 const data = {
//                     id: document.querySelector('[name="id"]').value,
//                     name: document.querySelector('[name="name"]').value,
//                     password: document.querySelector('[name="password"]').value,
//                     email: document.querySelector('[name="email"]').value,
//                     phoneNo: document.querySelector('[name="phoneNo"]').value
//                 }
//
//                 fetch('/api/signUp', {
//                     method: 'post',
//                     body: {
//                         memberDto: data
//                     },
//                     contentType: "application/json; charset=UTF-8"
//                 }).then(res => res.json())
//                     .then(res => {
//                         if (res.success) {
//                             alert('저장완료.');
//                         } else if (res.error) {
//                             alert('실패');
//                         }
//                     })
//             }
//
//         })
//     })();
//
//     window.Parsley.addValidator('userId', {
//         requirementType: 'regexp',
//         validateString: function (value) {
//             const pattern = /^[a-zA-Z0-9_]{4,20}$/;
//             return pattern.test(value);
//         },
//         messages: {
//             ko: "정상적인 아이디가 아닙니다. (한글, 특수문자 제외)"
//         }
//     });
//     window.Parsley.addValidator('minlength', {
//         requirementType: 'string',
//         validateString: function (value) {
//             return value.length > 5;
//         },
//         messages: {
//             ko: "비밀번호는 최소 6자 이상으로 설정해주세요."
//         }
//     });
//
//
//     window.Parsley.addValidator('iff', {
//         requirementType: 'string',
//         validateString: function (value, requirement) {
//             return document.querySelector(requirement).value === value;
//         },
//         messages: {
//             ko: "비밀번호가 일치하지 않습니다."
//         }
//     });
//
//
//     // 한글입력막기 스크립트
//     $(".not-kor").keyup(function (e) {
//         if (!(e.keyCode >= 37 && e.keyCode <= 40)) {
//             var v = $(this).val();
//             $(this).val(v.replace(/[^A-Za-z0-9~!@#$%^&*()_+|<>?:{}]/gi, ''));
//         }
//     });
//
// }(window);