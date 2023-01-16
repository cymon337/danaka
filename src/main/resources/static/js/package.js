// <!--  페이지로딩시 카테고리옵션 hide    -->
$("#category-option-rod").hide();
$("#category-option-reel").hide();
$("#category-option-line").hide();

// <!--   카테고리옵션 show, hide    -->
let showOption = function (e) {
    $("#category-option-rod").hide();
    $("#category-option-reel").hide();
    $("#category-option-line").hide();

    // 카테고리 변경시 모든 버튼 active 제거
    if ($(".btn-outline-secondary").hasClass("active") === true) {
        $(".btn-outline-secondary").removeClass("active")
    }
    // 선택옵션 초기화
    categoryOption.deleteAllOption();

    // 카테고리 value read
    var cat = $(e).val();

    switch (cat) {
        case 'rod':
            $("#category-option-rod").show();
            break;

        case 'reel':
            $("#category-option-reel").show();
            break;

        case 'line':
            $("#category-option-line").show();
            break;
        default : alert("카테고리를 선택하세요")
            break;
    }
}

// 카테고리옵션 관련 메소드
let selectedOption = new Array();

let categoryOption = {

    add: function (e) {
        var text = $(e.target).text();
        selectedOption.push(text);
        console.log(selectedOption.toString());
        this.displayOption(selectedOption.toString());
    },
    displayOption: function (optionText) {
        $("#selected-option").text(`선택옵션 : ${optionText}`);
    },
    deleteAllOption: function () {
        selectedOption = [];
        this.displayOption(selectedOption);
    }
}





// <!--   카테고리옵션 선택 활성/비활성    -->
$(".btn-outline-secondary").on("click", function (e) {

    if ($(e.target).hasClass("active") === true) {
        $(e.target).removeClass("active")
    } else {
        $(e.target).addClass("active") ;
        categoryOption.add(e);
    }
});
