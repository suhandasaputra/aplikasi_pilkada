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
    #buttonon1 {
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

    #btn_cancel1 {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save1 {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }

    #btn_cancel1:hover {
        background-color: #CCC;
    }
    #btn_save1:hover {
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
            document.getElementById("step1").style.display = "block";
            document.getElementById("step2").style.display = "none";
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
        $.getJSON('opt_dis', {}, function (data) {
            var options = '<option value="">All District</option>';
            for (var i = 0; i < data.length; i++) {
                options += '<option value="' + data[i].district + '">' + data[i].district + '</option>';
            }
            $("select#opt_district").html(options);
        });


        $('#opt_district').change(function (event) {
            var district = $("select#opt_district").val();
            $.get('opt_subdis?action=addvol', {
                district: district
            }, function (response) {
                var select = $('#opt_subdistrict');
                select.find('option').remove();
                $.each(response, function (index, data) {
                    $('<option>').val(data.id_subdistrict).text(data.subdistrict_name).appendTo(select);
                });
            });
        });

        $('#opt_subdistrict').change(function (event) {
            var subdis = $("select#opt_subdistrict").val();
            $.get('opt_tps?action=addvol', {
                subdis: subdis
            }, function (response) {
                var select = $('#opt_tps');
                select.find('option').remove();
                $.each(response, function (index, data) {
                    $('<option>').val(data.id_votingplace).text(data.votingplace_name).appendTo(select);
                });
            });
        });



    });
</script>
<div class="cd-popup-add-category" role="alert">
    <div class="cd-popup-add-category-container">
        <label id="label_add_category">Add Volunteer (Step 1)</label>
        <label id="label_assign">Register The Volunteer</label>
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
                        <div id="step1">
                            <input type="text" id="id_user" name="id_user" required="" placeholder="NIK" maxlength="30">
                            <input type="text" id="name" name="name" required="" placeholder="name" maxlength="50">
                            <input type="text" id="phone_number" name="phone_number" required="" placeholder="phone number" maxlength="15">
                            <input type="text" id="address" name="address" required="" placeholder="address" maxlength="100">
                            <input type="text" id="subdistrict" name="subdistrict" required="" placeholder="kelurahan" maxlength="30">
                            <input type="text" id="district" name="district" required="" placeholder="kecamatan" maxlength="30">
                            <input type="text" id="city" name="city" required="" placeholder="kota" maxlength="30">
                            <input type="password" id="password" name="password" required="" placeholder="password" maxlength="50">
                            <input type="password" id="c_password" name="c_password" required="" placeholder="confirm password" maxlength="50">
                            <div id="buttonon">
                                <div id="btn_cancel">Cancel</div>
                                <div id="btn_save">Save</div>
                            </div>
                        </div>

                        <div id="step2">
                            <input type="text" id="province" name="province" required="" placeholder="province" maxlength="50">
                            <input type="text" id="city" name="city" required="" placeholder="city" maxlength="50">
                            <select id="opt_district" style="width: 215px; margin-bottom: 5px;" name="opt_district">
                            </select>
                            <select id="opt_subdistrict" style="width: 215px; margin-bottom: 5px;" name="opt_subdistrict">
                            </select>
                            <select id="opt_tps" style="width: 215px; margin-bottom: 5px;" name="opt_tps">
                            </select>
                            <input type="text" id="userid1" name="userid1" readonly="" required="" maxlength="30" hidden="">
                            <input type="text" id="phonenumber1" name="phonenumber1" readonly="" required="" maxlength="30" hidden="">
                            <div id="buttonon1">
                                <div id="btn_cancel1">Cancel</div>
                                <div id="btn_save1">Save</div>
                            </div>
                        </div>
                    </form>
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
            var add_id_user = document.getElementById('id_user').value;
            var add_name = document.getElementById('name').value;
            var add_phone_number = document.getElementById('phone_number').value;
            var add_address = document.getElementById('address').value;
            var add_subdistrict = document.getElementById('subdistrict').value;
            var add_district = document.getElementById('district').value;
            var add_city = document.getElementById('city').value;
            var add_password = document.getElementById('password').value;
            var add_c_password = document.getElementById('c_password').value;


            var modal = document.getElementById("myModal_add_cate");
            var span = document.getElementsByClassName("close_add_cate")[0];
            var push = document.getElementById("push_text_add_cate");

            if (add_id_user == "") {
                push.innerHTML = "<p>NIK must be Fill</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (add_id_user != "") {
                if (add_name == "") {
                    push.innerHTML = "<p>name must be fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (add_name != "") {
                    if (add_phone_number == "") {
                        push.innerHTML = "<p>phone number must be fill</p>";
                        modal.style.display = "block";
                        span.onclick = function () {
                            modal.style.display = "none";
                        }
                        window.onclick = function (event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    } else if (add_phone_number != "") {
                        if (add_address == "") {
                            push.innerHTML = "<p>address must be fill</p>";
                            modal.style.display = "block";
                            span.onclick = function () {
                                modal.style.display = "none";
                            }
                            window.onclick = function (event) {
                                if (event.target == modal) {
                                    modal.style.display = "none";
                                }
                            }
                        } else if (add_address != "") {
                            if (add_subdistrict == "") {
                                push.innerHTML = "<p>kelurahan must be fill</p>";
                                modal.style.display = "block";
                                span.onclick = function () {
                                    modal.style.display = "none";
                                }
                                window.onclick = function (event) {
                                    if (event.target == modal) {
                                        modal.style.display = "none";
                                    }
                                }
                            } else if (add_subdistrict != "") {
                                if (add_district == "") {
                                    push.innerHTML = "<p>kecamatan must be fill</p>";
                                    modal.style.display = "block";
                                    span.onclick = function () {
                                        modal.style.display = "none";
                                    }
                                    window.onclick = function (event) {
                                        if (event.target == modal) {
                                            modal.style.display = "none";
                                        }
                                    }
                                } else if (add_district != "") {
                                    if (add_city == "") {
                                        push.innerHTML = "<p>kecamatan must be fill</p>";
                                        modal.style.display = "block";
                                        span.onclick = function () {
                                            modal.style.display = "none";
                                        }
                                        window.onclick = function (event) {
                                            if (event.target == modal) {
                                                modal.style.display = "none";
                                            }
                                        }
                                    } else if (add_city != "") {
                                        if (add_password == "") {
                                            push.innerHTML = "<p>password must be fill</p>";
                                            modal.style.display = "block";
                                            span.onclick = function () {
                                                modal.style.display = "none";
                                            }
                                            window.onclick = function (event) {
                                                if (event.target == modal) {
                                                    modal.style.display = "none";
                                                }
                                            }
                                        } else if (add_password != "") {
                                            if (add_c_password == "") {
                                                push.innerHTML = "<p>confirm password must be fill</p>";
                                                modal.style.display = "block";
                                                span.onclick = function () {
                                                    modal.style.display = "none";
                                                }
                                                window.onclick = function (event) {
                                                    if (event.target == modal) {
                                                        modal.style.display = "none";
                                                    }
                                                }
                                            } else if (add_c_password != "") {


                                                if (add_password !== add_c_password) {
                                                    push.innerHTML = "<p>password not match</p>";
                                                    modal.style.display = "block";
                                                    span.onclick = function () {
                                                        modal.style.display = "none";
                                                    }
                                                    window.onclick = function (event) {
                                                        if (event.target == modal) {
                                                            modal.style.display = "none";
                                                        }
                                                    }
                                                } else if (add_password === add_c_password) {
                                                    var datjson5 = new Object();

                                                    datjson5.id_user = add_id_user;
                                                    datjson5.name = add_name;
                                                    datjson5.phone_number = add_phone_number;
                                                    datjson5.address = add_address;
                                                    datjson5.subdistrict = add_subdistrict;
                                                    datjson5.district = add_district;
                                                    datjson5.city = add_city;
                                                    datjson5.password = add_password;
                                                    datjson5.type = 'step1';

                                                    $.ajax({
                                                        contentType: 'application/json',
                                                        dataType: "json",
                                                        url: "avs",
                                                        data: JSON.stringify(datjson5),
                                                        type: 'post',
                                                        success: function (response) {
                                                            if (response.resp_code == 0000) {
//                                                                alert('success add volunteer');
                                                                document.getElementById('label_add_category').innerHTML = 'Add Volunteer (Step 2)';
                                                                document.getElementById('label_assign').innerHTML = 'Assign to TPS';
                                                                document.getElementById("step1").style.display = "none";
                                                                document.getElementById("step2").style.display = "block";
                                                                document.getElementById("userid1").value = add_id_user;
                                                                document.getElementById("phonenumber1").value = add_phone_number;

                                                            } else {
                                                                alert('failed add volunteer');
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });


















        $('#btn_save1').on('click', function (event) {

            var province = document.getElementById('province').value;
            var city = document.getElementById('city').value;
            var district1 = document.getElementById('opt_district').value;
            var subdistrict1 = document.getElementById('opt_subdistrict').value;
            var tps = document.getElementById('opt_tps').value;
            var userid1 = document.getElementById('userid1').value;
            var phonenumber1 = document.getElementById('phonenumber1').value;



            var modal = document.getElementById("myModal_add_cate");
            var span = document.getElementsByClassName("close_add_cate")[0];
            var push = document.getElementById("push_text_add_cate");

            if (province == "") {
                push.innerHTML = "<p>province must be Fill</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (province != "") {
                if (city == "") {
                    push.innerHTML = "<p>city must be fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (city != "") {
                    if (district1 == "") {
                        push.innerHTML = "<p>district1 number must be fill</p>";
                        modal.style.display = "block";
                        span.onclick = function () {
                            modal.style.display = "none";
                        }
                        window.onclick = function (event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    } else if (district1 != "") {
                        if (subdistrict1 == "") {
                            push.innerHTML = "<p>subdistrict1 must be fill</p>";
                            modal.style.display = "block";
                            span.onclick = function () {
                                modal.style.display = "none";
                            }
                            window.onclick = function (event) {
                                if (event.target == modal) {
                                    modal.style.display = "none";
                                }
                            }
                        } else if (subdistrict1 != "") {
                            if (tps == "") {
                                push.innerHTML = "<p>tps must be fill</p>";
                                modal.style.display = "block";
                                span.onclick = function () {
                                    modal.style.display = "none";
                                }
                                window.onclick = function (event) {
                                    if (event.target == modal) {
                                        modal.style.display = "none";
                                    }
                                }
                            } else if (tps != "") {

                                var datjson6 = new Object();
                                datjson6.province = province;
                                datjson6.city = city;
                                datjson6.district1 = district1;
                                datjson6.subdistrict1 = subdistrict1;
                                datjson6.tps = tps;
                                datjson6.userid1 = userid1;
                                datjson6.phonenumber1 = phonenumber1;
                                datjson6.type = 'step2';

                                $.ajax({
                                    contentType: 'application/json',
                                    dataType: "json",
                                    url: "avs",
                                    data: JSON.stringify(datjson6),
                                    type: 'post',
                                    success: function (response) {
                                        if (response.resp_code == 0000) {
                                            alert('success add volunteer');
                                            $('#table_bank').DataTable().ajax.reload();
                                            $('.cd-popup-add-category').removeClass('is-visible');
                                        } else {
                                            alert('failed add volunteer');
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
</script>