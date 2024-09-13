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

    .img-replace-edit-category {
        /* replace text with an image */
        display: inline-block;
        overflow: hidden;
        text-indent: 100%;
        color: transparent;
        white-space: nowrap;
    }

    .cd-popup-edit-category {
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
    .cd-popup-edit-category.is-visible {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-edit-category-container {
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

    .cd-popup-edit-category-container .cd-buttons-add-category:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-edit-category-container .cd-buttons-add-category li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-edit-category-container .cd-buttons-add-category div {
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
    .cd-popup-edit-category-container .cd-buttons-add-category li:first-child div {
        background: #3dceb7;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-edit-category-container .cd-buttons-add-category li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-edit-category-container .cd-buttons-add-category li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-edit-category-container .cd-buttons-add-category li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-edit-category-container .cd-buttons-add-category li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-edit-category-container .cd-buttons-add-category li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-edit-category-container .cd-popup-edit-category-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-edit-category-container .cd-popup-edit-category-close::before, .cd-popup-edit-category-container .cd-popup-edit-category-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-edit-category-container .cd-popup-edit-category-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-edit-category-container .cd-popup-edit-category-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible .cd-popup-edit-category-container {
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-edit-category-container {
            margin: 8em auto;
        }
    }
    #label_edit_category {
        width: 100%;
        margin: 20px;
        text-align: left;
    }
    #boxx_edit_category{
        display: flex;
        justify-content: center;
        align-content: center;
    }

    #box1_edit_category {
        cursor: pointer;
        width: 215px;
        height: 200px;
        margin-right: 5px;
        /*background-color: #68f3e9;*/
        border-radius: 5px;
    }

    #box2_edit_category {
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
    #buttonon_edit_category {
        color: white;
        margin-top: 10px;
        display: flex;
    }/*
    #btn_cancel_edit_category {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save_edit_category {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }*/

    /*    #btn_cancel_edit_category:hover {
            background-color: #CCC;
        }
        #btn_edit_submit_category:hover {
            background-color: #CCC;
        }*/
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
    .close_edit_category {
        padding-top: -10px;
        color: white;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close_edit_category:hover,
    .close_edit_category:focus {
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










    #btn_cancel_edit_category {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save_edit_category {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }

    #btn_cancel_edit_category:hover {
        background-color: #CCC;
    }
    #btn_save_edit_category:hover {
        background-color: #CCC;
    }

    #btn_del_edit_category {
        cursor: pointer;
        width: 135px;
        height: 35px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        border-radius: 3px;
    }
    #btn_del_edit_category:hover {
        background-color: #c52d18;
        color: white;
    }
    #img_filechooser_edit_candidate {
        width: 215px;
        height: 200px;
    }
</style>


<script>
    jQuery(document).ready(function ($) {
// Add event listener for opening and closing details
        $('#table_user').on('click', 'td', function () {
            var $row = $(this).closest("tr");    // Find the row
            var id_candidate_edit = $row.find(".id_candidate").text(); // Find the text
            var candidate_name_edit = $row.find(".candidate_name").text(); // Find the text
            var img_candidate_edit = $(this).parent().find('img').attr('src');
            document.getElementById("id_candidate_edit").value = id_candidate_edit;
            document.getElementById("candidate_name_edit").value = candidate_name_edit;

            var img = document.getElementById('img_filechooser_edit_candidate');
            img.src = img_candidate_edit;





            function toDataUrl(url, callback) {
                var xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    var reader = new FileReader();
                    reader.onloadend = function () {
                        callback(reader.result);
                    }
                    reader.readAsDataURL(xhr.response);
                };
                xhr.open('GET', url);
                xhr.responseType = 'blob';
                xhr.send();
            }
            var proxyUrl = 'https://cors-anywhere.herokuapp.com/',
                    targetUrl = img_candidate_edit;
            toDataUrl(proxyUrl + targetUrl,
                    function (data) {
                        console.log(data)
                        document.getElementById("img_edit_candidate").value = data.replace('data:image/png;base64,', '');
                    });






//            function convertImgToBase64(url, callback, outputFormat) {
//                var canvas = document.createElement('CANVAS');
//                var ctx = canvas.getContext('2d');
//                var img = new Image;
//                img.crossOrigin = 'Anonymous';
//                img.onload = function () {
//                    var dataURL = canvas.toDataURL(outputFormat || 'image/png');
//                    callback.call(this, dataURL);
//                    // Clean up
//                    canvas = null;
//                };
//                img.src = url;
//            }        
//                var imageUrl = img_candidate_edit;
//                console.log('imageUrl', imageUrl);
//                convertImgToBase64(imageUrl, function (base64Img) {
//                    document.getElementById("img_edit_candidate").value = base64Img;
//                });



            $('.cd-popup-edit-category').addClass('is-visible');
        });

        //close popup provide password
        $('.cd-popup-edit-category').on('click', function (event) {
            if ($(event.target).is('.cd-popup-edit-category-close') || $(event.target).is('.cd-popup-edit-category') || $(event.target).is('#btn_cancel_edit_category')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
            }
        });
        //close popup when clicking the esc keyboard button provide password
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup-edit-category').removeClass('is-visible');
            }
        });
    });
</script>
<div class="cd-popup-edit-category" role="alert">
    <div class="cd-popup-edit-category-container" style="color: #29B19C;">
        <label id="label_edit_category">Edit Candidate</label>
        <div id="boxx_edit_category">
            <div id="box1_edit_category">
                <img id="img_filechooser_edit_candidate">
            </div>
            <div id="formhi_edit_category">
                <input id="file-input-edit-candidate" name="file-input-edit-candidate" type="file" onchange="readURL_edit_candidate(this)" style="display: none"/>
            </div>

            <div id="box2_edit_category">
                <div id="input_edit_category">
                    <form action="edit_cate" id="form_edit_category" method="post">
                        <div id="myModal_edit_cate" class="modal">
                            <div class="modal-content">
                                <span class="close_edit_cate">&times;</span>
                                <div id="push_text_edit_cate"></div>
                            </div>
                        </div>
                        <input type="text" id="id_candidate_edit" name="id_candidate_edit" readonly="" required="" placeholder="id candidate" style="background-color: aquamarine; color: grey; pointer-events: none">
                        <input type="text" id="candidate_name_edit" name="candidate_name_edit" required="" placeholder="candidate name" maxlength="50">
                        <!--<input type="text" id="status_edit" name="status_edit" required="" placeholder="status" maxlength="1">-->
                        <input type="text" hidden="" readonly name="img_edit_candidate" id="img_edit_candidate"/>
                    </form>
                    <div id="buttonon_edit_category">
                        <div id="btn_cancel_edit_category">Cancel</div>
                        <div id="btn_del_edit_category" onclick="sub_del_user()">Delete</div>
                        <div id="btn_save_edit_category">Update</div>
                    </div>

                    <div id="msgbox_edit"></div>
                </div>
            </div>
        </div>
        <a href="#0" class="cd-popup-edit-category-close img-replace-edit-category">Close</a>
    </div>
</div>
<script>
    function readURL_edit_candidate(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#img_filechooser_edit_candidate')
                        .attr('src', e.target.result)
                        .width(215)
                        .height(200);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }


    if (window.File && window.FileReader && window.FileList && window.Blob) {
        document.getElementById('file-input-edit-candidate').addEventListener('change', handleFileSelectedit, false);
    } else {
        alert('The File APIs are not fully supported in this browser.');
    }

    //convert image to base64
    function handleFileSelectedit(evt) {
        var f = evt.target.files[0]; // FileList object
        var reader = new FileReader();
        // Closure to capture the file information.
        reader.onload = (function (theFile) {
            return function (e) {
                var binaryData = e.target.result;
                //Converting Binary Data to base 64
                var base64String = window.btoa(binaryData);
                //showing file converted to base64
                document.getElementById('img_edit_candidate').value = base64String;
//                alert('sukses convert gambar');
            };
        })(f);
        // Read in the image file as a data URL.
        reader.readAsBinaryString(f);
    }














    function sub_del_user() {
        var id_candidate_edit = document.getElementById("id_candidate_edit").value;

        var modal = document.getElementById("myModal_edit_cate");
        var span = document.getElementsByClassName("close_edit_cate")[0];
        var push = document.getElementById("push_text_edit_cate");

        if (id_candidate_edit == "") {
            push.innerHTML = "<p>id candidate must be fill</p>";
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else if (id_candidate_edit != "") {
            var datjson3 = new Object();
            datjson3.id_candidate_edit = id_candidate_edit;
            $.ajax({
                contentType: 'application/json',
                dataType: "json",
                url: "dus",
                data: JSON.stringify(datjson3),
                type: 'post',
                success: function (response) {
                    if (response.resp_code == 0000) {
                        alert('success delete user');
                        $('#table_user').DataTable().ajax.reload();
                    } else {
                        alert('failed delete user');

                    }
                }
            });
        }
    }



    jQuery(document).ready(function ($) {
        //open popup edit category
        $('#box1_edit_category').on('click', function (event) {
            $('#file-input-edit-candidate').trigger('click');
        });


        $('#btn_save_edit_category').on('click', function (event) {
            var id_candidate_edit = document.getElementById('id_candidate_edit').value;
            var candidate_name_edit = document.getElementById('candidate_name_edit').value;
            var img_edit_candidate = document.getElementById('img_edit_candidate').value;

            var modal = document.getElementById("myModal_edit_cate");
            var span = document.getElementsByClassName("close_edit_cate")[0];
            var push = document.getElementById("push_text_edit_cate");
            if (img_edit_candidate == "") {
                push.innerHTML = "<p>image must be choose</p>";
                modal.style.display = "block";
                span.onclick = function () {
                    modal.style.display = "none";
                }
                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            } else if (img_edit_candidate != "") {
                if (candidate_name_edit == "") {
                    push.innerHTML = "<p>candidate name must be fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (candidate_name_edit != "") {
//                    if (status_edit == "") {
//                        push.innerHTML = "<p>status must be fill</p>";
//                        modal.style.display = "block";
//                        span.onclick = function () {
//                            modal.style.display = "none";
//                        }
//                        window.onclick = function (event) {
//                            if (event.target == modal) {
//                                modal.style.display = "none";
//                            }
//                        }
//                    } else if (status_edit != "") {
                    var datjson4 = new Object();
                    datjson4.id_candidate_edit = id_candidate_edit;
                    datjson4.candidate_name_edit = candidate_name_edit;
                    datjson4.img_candidate = img_edit_candidate;

                    $.ajax({
                        contentType: 'application/json',
                        dataType: "json",
                        url: "uus",
                        data: JSON.stringify(datjson4),
                        type: 'post',
                        success: function (response) {
                            if (response.resp_code == 0000) {
                                alert('success update user');
                                $('#table_user').DataTable().ajax.reload();
//                                window.location.href = "usr";
                            } else {
                                alert('failed update user');

                            }
                        }
                    });
//                    }
                }
            }
        });
    });
</script>