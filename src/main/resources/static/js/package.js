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
            searchProductList.selectCategoryOption("RD");
            break;

        case 'reel':
            $("#category-option-reel").show();
            searchProductList.selectCategoryOption("RL");
            break;

        case 'line':
            $("#category-option-line").show();
            searchProductList.selectCategoryOption("LN");
            break;
        default : alert("카테고리를 선택하세요")
            break;
    }

    searchProductList.search();
}

// 카테고리옵션 관련 메소드
let selectedOption = [];   // 선택옵션 list

let categoryOption = {

    addOption: function (e) {
        var text = $(e.target).text();
        selectedOption.push(text);

        console.log(selectedOption.toString());
        this.displayOption(selectedOption.toString());
    },
    removeOption: function (e) {
        var text = $(e.target).text();
        var filtered = selectedOption.filter((item) => item !== text);
        selectedOption = filtered;

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
        categoryOption.removeOption(e);
    } else {
        $(e.target).addClass("active") ;
        categoryOption.addOption(e);
    }
    searchProductList.search();
});

// <!--   상품목록 출력 searchList   -->

let searchProductList = {

    categoryCode: "default",
    result: [],

    // select option 로드, 릴, 라인 스위치문에서 받아옴
    selectCategoryOption: function (categoryCode) {
        this.categoryCode = categoryCode;
    },

    // 상품검색 서버통신 post request
    search: function () {
        console.log("search product");
        var categoryOptionList = selectedOption;
        console.log(categoryOptionList);


        $.ajax({
            url: "package/selectProduct",
            type: "POST",
            traditional : true,
            data: {
                categoryCode: this.categoryCode,
                categoryOptionList: categoryOptionList,
            },
            success : function(productList) {
                console.log("searchProductList() = success");
                this.result = productList;
                console.log(productList);

                console.log("searchProductList() = break");

            },
            error : function () {
                alert("searchProductList() = error");
            }
        });
    },

    // 검색상품 리스트 출력
    display: function () {
        console.log("productList");

    }



}
