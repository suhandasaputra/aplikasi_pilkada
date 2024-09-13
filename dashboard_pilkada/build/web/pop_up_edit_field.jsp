<%-- 
    Document   : pop_up_add_category
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    .cd-buttons-edit-field
    {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
    }

    .img-replace-edit-category {
        /* replace text with an image */
        display: inline-block;
        overflow: hidden;
        text-indent: 100%;
        color: transparent;
        white-space: nowrap;
    }

    .cd-popup-edit-field {
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
    .cd-popup-edit-field.is-visible {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-edit-field-container {
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

    .cd-popup-edit-field-container .cd-buttons-edit-field:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-edit-field-container .cd-buttons-edit-field li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-edit-field-container .cd-buttons-edit-field div {
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
    .cd-popup-edit-field-container .cd-buttons-edit-field li:first-child div {
        background: #3dceb7;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-edit-field-container .cd-buttons-edit-field li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-edit-field-container .cd-buttons-edit-field li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-edit-field-container .cd-buttons-edit-field li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-edit-field-container .cd-buttons-edit-field li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-edit-field-container .cd-buttons-edit-field li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-edit-field-container .cd-popup-edit-field-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-edit-field-container .cd-popup-edit-field-close::before, .cd-popup-edit-field-container .cd-popup-edit-field-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-edit-field-container .cd-popup-edit-field-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-edit-field-container .cd-popup-edit-field-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible .cd-popup-edit-field-container {
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-edit-field-container {
            margin: 8em auto;
        }
    }
    #label_edit_field {
        width: 100%;
        margin: 20px;
        text-align: left;
    }
    #boxx_edit_field{
        display: flex;
        justify-content: center;
        align-content: center;
    }

    #box2_edit_field {
        width: 215px;
        height: 200px;
        margin-left: 5px;
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
    #buttonon_edit_field {
        color: white;
        margin-top: 10px;
        display: flex;
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
    #img_filechooser_edit_category {
        width: 215px;
        height: 200px;
    }

    /* The Close Button */
    .close_edit_fieldgory {
        padding-top: -10px;
        color: white;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close_edit_fieldgory:hover,
    .close_edit_fieldgory:focus {
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }



    /*    #btn_delete_edit_category {
            cursor: pointer;
            width: 100px;
            background-color: #a7a0a0;
            border: 1px solid #CCC;
            margin-right: 8px;
            border-radius: 3px;
        }
    
        #btn_delete_edit_category:hover {
            background-color: #CCC;
        }*/










    #btn_cancel_edit_field {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save_edit_field {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }

    #btn_cancel_edit_field:hover {
        background-color: #CCC;
    }
    #btn_save_edit_field:hover {
        background-color: #CCC;
    }

    #btn_del_edit_field {
        cursor: pointer;
        width: 135px;
        height: 35px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        border-radius: 3px;
    }
    #btn_del_edit_field:hover {
        background-color: #c52d18;
        color: white;
    }
</style>


<script>
    jQuery(document).ready(function ($) {
// Add event listener for opening and closing details
        $('#table_iso').on('click', 'td', function () {
            var $row = $(this).closest("tr");    // Find the row
            var field = $row.find(".field").text(); // Find the text
            var name = $row.find(".name_of_field").text(); // Find the text
            var format = $row.find(".format").text(); // Find the text
            var chars = $row.find(".chars").text(); // Find the text
            var length = $row.find(".length").text(); // Find the text

            document.getElementById("edit_field").value = field;
            document.getElementById("edit_name").value = name;
            document.getElementById("edit_format").value = format;
            document.getElementById("edit_chars").value = chars;
            document.getElementById("edit_length").value = length;

            $('.cd-popup-edit-field').addClass('is-visible');
        });

        //close popup provide password
        $('.cd-popup-edit-field').on('click', function (event) {
            if ($(event.target).is('.cd-popup-edit-field-close') || $(event.target).is('.cd-popup-edit-field') || $(event.target).is('#btn_cancel_edit_field')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
            }
        });
        //close popup when clicking the esc keyboard button provide password
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup-edit-field').removeClass('is-visible');
            }
        });
    });
</script>
<div class="cd-popup-edit-field" role="alert">
    <div class="cd-popup-edit-field-container" style="color: #29B19C;">
        <label id="label_edit_field">Edit Field</label>
        <div id="boxx_edit_field">
            <div id="box2_edit_field">
                <div id="input_edit_category">
                    <form>
                        <div id="myModal_edit_field" class="modal">
                            <div class="modal-content">
                                <span class="close_edit_field">&times;</span>
                                <div id="push_text_edit_field"></div>
                            </div>
                        </div>
                        <input type="text" id="edit_field" name="edit_field" readonly="" required="" placeholder="field" maxlength="3">
                        <input type="text" id="edit_name" name="edit_name" required="" placeholder="name of field" maxlength="50">
                        <!--                        <input type="text" id="status_edit" name="status_edit" required="" placeholder="status" maxlength="1">
                                                <input type="text" id="username_edit" name="username_edit" required="" placeholder="bank name" maxlength="50">-->
                        <select name="edit_format" id="edit_format" style="
                                width: 215px;
                                border-radius: 3px;
                                margin-bottom: 6px;">
                            <option value="FIXED">FIXED</option>
                            <option value="LLVAR">LLVAR</option>
                            <option value="LLLVAR">LLLVAR</option>
                            <option value="HLVAR">HLVAR</option>
                            <option value="HLLVAR">HLLVAR</option>
                        </select>
                        <!--<input type="text" id="add_field_format" name="add_field_format" required="" placeholder="format" maxlength="6">-->
                        <select name="edit_chars" id="edit_chars" style="
                                width: 215px;
                                border-radius: 3px;
                                margin-bottom: 6px;">
                            <option value="a">A</option>
                            <option value="n">N</option>
                            <option value="an">AN</option>
                            <option value="ns">NS</option>
                            <option value="ans">ANS</option>
                            <option value="zpos">ZPOS</option>
                        </select>
                        <input type="text" id="edit_length" name="edit_length" required="" placeholder="length" maxlength="3">
                    </form>
                    <div id="buttonon_edit_field">
                        <div id="btn_cancel_edit_field">Cancel</div>
                        <div id="btn_del_edit_field">Delete</div>
                        <div id="btn_save_edit_field">Update</div>
                    </div>

                    <div id="msgbox_edit"></div>
                </div>
            </div>
        </div>
        <a href="#0" class="cd-popup-edit-field-close img-replace-edit-category">Close</a>
    </div>
</div>
<script>
    jQuery(document).ready(function ($) {
        $('#table_conn').on('click', 'td', function () {
            var $row = $(this).closest("tr");
            var bco = $row.find(".bankcode").text();
            $('#btn_del_edit_field').unbind().bind('click', function (event) {
                var field = document.getElementById("edit_field").value;
                var modal = document.getElementById("myModal_edit_field");
                var span = document.getElementsByClassName("close_edit_field")[0];
                var push = document.getElementById("push_text_edit_field");

                if (field == "") {
                    push.innerHTML = "<p>field must be fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (field != "") {
                    var datjson3 = new Object();
                    datjson3.field = field;
                    datjson3.bankcode = bco;
                    $.ajax({
                        contentType: 'application/json',
                        dataType: "json",
                        url: "dfi",
                        data: JSON.stringify(datjson3),
                        type: 'post',
                        success: function (response) {
                            if (response.status == 00) {
                                alert('success delete field');
                                $('#table_iso').DataTable().ajax.reload();
                                $('.cd-popup-edit-field').removeClass('is-visible');

                            } else {
                                alert('failed delete field');
                            }
                        }
                    });
                }
            });
        });
    });




    jQuery(document).ready(function ($) {
        $('#table_conn').on('click', 'td', function () {
            var $row = $(this).closest("tr");
            var bcode = $row.find(".bankcode").text();
            $('#btn_save_edit_field').unbind().bind('click', function (event) {

                var edit_field_field = document.getElementById('edit_field').value;
                var edit_field_name_of_field = document.getElementById('edit_name').value;
                var edit_field_format = document.getElementById('edit_format').value;
                var edit_field_char = document.getElementById('edit_chars').value;
                var edit_field_length = document.getElementById('edit_length').value;



                var modal = document.getElementById("myModal_edit_field");
                var span = document.getElementsByClassName("close_edit_field")[0];
                var push = document.getElementById("push_text_edit_field");
                if (edit_field_field == "") {
                    push.innerHTML = "<p>field must be Fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (edit_field_field != "") {
                    if (edit_field_name_of_field == "") {
                        push.innerHTML = "<p>name of field must be fill</p>";
                        modal.style.display = "block";
                        span.onclick = function () {
                            modal.style.display = "none";
                        }
                        window.onclick = function (event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    } else if (edit_field_name_of_field != "") {
                        if (edit_field_format == "") {
                            push.innerHTML = "<p>format must be fill</p>";
                            modal.style.display = "block";
                            span.onclick = function () {
                                modal.style.display = "none";
                            }
                            window.onclick = function (event) {
                                if (event.target == modal) {
                                    modal.style.display = "none";
                                }
                            }
                        } else if (edit_field_format != "") {


                            if (edit_field_char == "") {
                                push.innerHTML = "<p>char must be fill</p>";
                                modal.style.display = "block";
                                span.onclick = function () {
                                    modal.style.display = "none";
                                }
                                window.onclick = function (event) {
                                    if (event.target == modal) {
                                        modal.style.display = "none";
                                    }
                                }
                            } else if (edit_field_char != "") {
                                if (edit_field_length == "") {
                                    push.innerHTML = "<p>length must be fill</p>";
                                    modal.style.display = "block";
                                    span.onclick = function () {
                                        modal.style.display = "none";
                                    }
                                    window.onclick = function (event) {
                                        if (event.target == modal) {
                                            modal.style.display = "none";
                                        }
                                    }
                                } else if (edit_field_length != "") {
                                    var datjson4 = new Object();
                                    datjson4.field = edit_field_field;
                                    datjson4.name = edit_field_name_of_field;
                                    datjson4.format = edit_field_format;
                                    datjson4.char = edit_field_char;
                                    datjson4.length = edit_field_length;
                                    datjson4.bankcode = bcode;

                                    $.ajax({
                                        contentType: 'application/json',
                                        dataType: "json",
                                        url: "ufi",
                                        data: JSON.stringify(datjson4),
                                        type: 'post',
                                        success: function (response) {
                                            if (response.status == 00) {
                                                alert('success update field');
                                                $('#table_iso').DataTable().ajax.reload();
                                                $('.cd-popup-edit-field').removeClass('is-visible');
//                                            window.location.href = "usr";
                                            } else {
                                                alert('failed update field');

                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            });
        });
    });
</script>