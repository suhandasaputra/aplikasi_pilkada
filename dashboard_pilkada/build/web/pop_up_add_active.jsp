<%-- 
    Document   : pop_up_add_category
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    .cd-buttons-add-category
    {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
    }

    .img-replace-add-category {
        /* replace text with an image */
        display: inline-block;
        overflow: hidden;
        text-indent: 100%;
        color: transparent;
        white-space: nowrap;
    }

    .cd-popup-add-category {
        position: fixed;
        left: 0;
        top: 0;
        height: 100%;
        width: 100%;
        background-color: rgba(94, 110, 141, 0.9);
        opacity: 0;
        visibility: hidden;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        transition: opacity 0.3s 0s, visibility 0s 0.3s;
    }
    .cd-popup-add-category.is-visible {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-add-category-container {
        position: relative;
        width: 90%;
        height: -webkit-fill-available;
        max-width: 500px;
        margin: 4em auto;
        background: #FFF;
        border-radius: .25em .25em .4em .4em;
        text-align: center;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        -webkit-transform: translateY(-40px);
        -moz-transform: translateY(-40px);
        -ms-transform: translateY(-40px);
        -o-transform: translateY(-40px);
        transform: translateY(-40px);
        /* Force Hardware Acceleration in WebKit */
        -webkit-backface-visibility: hidden;
        -webkit-transition-property: -webkit-transform;
        -moz-transition-property: -moz-transform;
        transition-property: transform;
        -webkit-transition-duration: 0.3s;
        -moz-transition-duration: 0.3s;
        transition-duration: 0.3s;
    }

    .cd-popup-add-category-container .cd-buttons-add-category:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-add-category-container .cd-buttons-add-category li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-add-category-container .cd-buttons-add-category div {
        cursor: pointer;
        display: block;
        height: 60px;
        line-height: 60px;
        text-transform: uppercase;
        color: #FFF;
        -webkit-transition: background-color 0.2s;
        -moz-transition: background-color 0.2s;
        transition: background-color 0.2s;
    }
    .cd-popup-add-category-container .cd-buttons-add-category li:first-child div {
        background: #3dceb7;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-add-category-container .cd-buttons-add-category li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-add-category-container .cd-buttons-add-category li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-add-category-container .cd-buttons-add-category li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-add-category-container .cd-buttons-add-category li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-add-category-container .cd-buttons-add-category li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-add-category-container .cd-popup-add-category-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-add-category-container .cd-popup-add-category-close::before, .cd-popup-add-category-container .cd-popup-add-category-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-add-category-container .cd-popup-add-category-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-add-category-container .cd-popup-add-category-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible .cd-popup-add-category-container {
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-add-category-container {
            margin: 8em auto;
        }
    }
    #label_add_category {
        width: 100%;
        margin: 20px;
        text-align: left;
    }
    #boxx_category{
        display: flex;
        justify-content: center;
        align-content: center;
    }
    #box1_category {
        cursor: pointer;
        width: 215px;
        height: 200px;
        margin-right: 5px;
        /*background-color: #68f3e9;*/
        border-radius: 5px;
    }
    #box2_category {
        width: 215px;
        height: 200px;
        /*margin-left: 5px;*/
        /*background-color: yellow;*/
    }
    input {
        padding-left: 10px;
        border: 1px solid #CCC; 
        border-radius: 3px;
        width: 100%;
        margin-bottom: 5px;
    }
    textarea {
        padding-left: 10px;
        border-radius: 3px;
        width: 100%;
        margin-top: 10px;
        height: 115px;
    }
    #buttonon {
        color: white;
        margin-top: 10px;
        display: flex;
    }
    #btn_cancel {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }

    #btn_cancel:hover {
        background-color: #CCC;
    }
    #btn_save:hover {
        background-color: #CCC;
    }
    .image-upload > input {
        visibility:hidden;
        width:0;
        height:0
    }
    #panel_img {
        margin: 15px;
        height: 300px;
        width: 430px;
        background-color: white;
    }
    #blah {
        width: 430px;;
        height: 300px;
    }
    #btn_upl {
        width: 100px;
    }
    #img_filechooser_category {
        width: 215px;
        height: 200px;
    }
</style>


<script>
    jQuery(document).ready(function ($) {
        //open popup add category
        $('#add_category_text').on('click', function (event) {
            event.preventDefault();
            $('.cd-popup-add-category').addClass('is-visible');
        });

        //close popup add category
        $('.cd-popup-add-category').on('click', function (event) {
            if ($(event.target).is('.cd-popup-add-category-close') || $(event.target).is('.cd-popup-add-category') || $(event.target).is('#btn_cancel')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
            }
        });
        //close popup when clicking the esc keyboard button add category
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup-add-category').removeClass('is-visible');
            }
        });



        // select category
        $.getJSON('opt_subdis?action=addact', {}, function (data) {
            var options = '<option value="">All Subdistrict</option>';
            for (var i = 0; i < data.length; i++) {
                options += '<option value="' + data[i].id_subdistrict + '">' + data[i].subdistrict_name + '</option>';
            }
            $("select#opt_subdistrict").html(options);
        });

        $.getJSON('opt_tps?action=addact', {}, function (data) {
            var options = '<option value="">All TPS</option>';
            for (var i = 0; i < data.length; i++) {
                options += '<option value="' + data[i].id_votingplace + '">' + data[i].votingplace_name + '</option>';
            }
            $("select#opt_tps").html(options);
        });

    });
</script>
<div class="cd-popup-add-category" role="alert">
    <div class="cd-popup-add-category-container">
        <label id="label_add_category">Add Activation</label>
        <div id="boxx_category">
            <div id="box2_category">
                <div id="input_category">
                    <form>
                        <div id="myModal_add_cate" class="modal">
                            <div class="modal-content">
                                <span class="close_add_cate">&times;</span>
                                <div id="push_text_add_cate"></div>
                            </div>
                        </div>
                        <select id="opt_subdistrict" style="width: 215px; margin-bottom: 5px;" name="opt_subdistrict">
                        </select>
                        <select id="opt_tps" style="width: 215px; margin-bottom: 5px;" name="opt_tps">
                        </select>
                        <input type="number" id="total_voter" name="total_voter" required="" placeholder="Total Voter">
                    </form>
                    <div id="buttonon">
                        <div id="btn_cancel">Cancel</div>
                        <div id="btn_save">Save</div>
                    </div>

                    <div id="msgbox"></div>
                </div>
            </div>
        </div>
        <a href="#0" class="cd-popup-add-category-close img-replace-add-category">Close</a>
    </div>
</div>
<script>
    jQuery(document).ready(function ($) {
        $('#btn_save').on('click', function (event) {
            var opt_subdistrict = document.getElementById('opt_subdistrict').value;
            var opt_tps = document.getElementById('opt_tps').value;
            var total_voter = document.getElementById('total_voter').value;

            var modal = document.getElementById("myModal_add_cate");
            var span = document.getElementsByClassName("close_add_cate")[0];
            var push = document.getElementById("push_text_add_cate");

            if (opt_subdistrict == "") {
                push.innerHTML = "<p>subdistrict must be choose</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (opt_subdistrict != "") {
                if (opt_tps == "") {
                    push.innerHTML = "<p>TPS must be Fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (opt_tps != "") {
                    if (total_voter == "") {
                        push.innerHTML = "<p>total voter must be Fill</p>";
                        modal.style.display = "block";
                        span.onclick = function () {
                            modal.style.display = "none";
                        }
                        window.onclick = function (event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    } else if (total_voter != "") {
                        var datjson13 = new Object();
                        datjson13.opt_subdistrict = opt_subdistrict;
                        datjson13.opt_tps = opt_tps;
                        datjson13.total_voter = total_voter;

                        $.ajax({
                            contentType: 'application/json',
                            dataType: "json",
                            url: "acs",
                            data: JSON.stringify(datjson13),
                            type: 'post',
                            success: function (response) {
                                if (response.status == 00) {
                                    alert('success add activation');
                                    $('#table_active').DataTable().ajax.reload();
//                                window.location.href = "usr";
                                } else {
                                    alert('failed add activation');

                                }
                            }
                        });
                    }
                }
            }
        });
    });
</script>