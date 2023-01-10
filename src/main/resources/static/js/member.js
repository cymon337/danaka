//
// let index = {
//     init: function () {
//         $("#btn-update").on("click",()=>{
//             this.update();
//         });
//
//     },
//
//     update: function () {
//         let data = {
//             nickName: $(#"nickName").val(),
//             password: $("#password").val(),
//             email: $("#email").val(),
//             phone: $("#phone").val()
//
//
//         };
//
//         $.ajax({
//             type: "PUT",
//             url: "/member",
//             data: JSON.stringify(data),
//             contentType: "application/json; charset=utf-8",
//             dataType: "json"
//
//         }).done(function (resp){
//             alert("회원수정이 완료되었습니다.");
//             console.log(resp);
//             location.href="/";
//         }).fail(function (error){
//             alert(JSON.stringify(error));
//         });
//
//     }
//
// }
//
// index.init();