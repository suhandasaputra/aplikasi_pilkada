<%-- 
    Document   : pop_up_add_category
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    /*==============================================================================*/
    .cd-popup-edit-message {
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
    .cd-popup-edit-message.is-visible {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-edit-message-container {
        position: relative;
        width: 100%;
        height: -webkit-fill-available;
        /*max-width: 500px;*/
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

    .cd-popup-edit-message-container .cd-buttons-add-category:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-edit-message-container .cd-buttons-add-category li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-edit-message-container .cd-buttons-add-category div {
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
    .cd-popup-edit-message-container .cd-buttons-add-category li:first-child div {
        background: #3dceb7;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-edit-message-container .cd-buttons-add-category li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-edit-message-container .cd-buttons-add-category li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-edit-message-container .cd-buttons-add-category li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-edit-message-container .cd-buttons-add-category li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-edit-message-container .cd-buttons-add-category li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-edit-message-container .cd-popup-edit-message-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-edit-message-container .cd-popup-edit-message-close::before, .cd-popup-edit-message-container .cd-popup-edit-message-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-edit-message-container .cd-popup-edit-message-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-edit-message-container .cd-popup-edit-message-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible .cd-popup-edit-message-container {
        margin-left: 260px;
        width: 80%;
        height: 87%;
        margin-top: 70px;
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-edit-message-container {
            margin: 8em auto;
        }
    }
    #label_edit_message {
        width: 100%;
        margin: 20px;
        text-align: left;
    }



    #buttonon_edit_message {
        justify-content: center;
        color: white;
        margin-top: 10px;
        display: flex;
    }
    #btn_cancel_edit_message {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save_edit_message {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }

    #btn_cancel_edit_message:hover {
        background-color: #CCC;
    }
    #btn_save_edit_message:hover {
        background-color: #CCC;
    }

    /* The Close Button */
    .close_edit_message {
        padding-top: -10px;
        color: white;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close_edit_message:hover,
    .close_edit_message:focus {
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }

    th {
        background-color: #CCC;
    }
    #table_iso th {
        font-size: 12px;
        background-color: #ACACAC;
        color: white;
        font-weight: 100;
    }
    #table_iso td {
        font-size: 12px;
    }

</style>


<script>
    jQuery(document).ready(function ($) {
        //close popup provide password
        $('.cd-popup-edit-connection').on('click', function (event) {
            if ($(event.target).is('#btn_edit_message')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
                $('.cd-popup-edit-message').addClass('is-visible');
            }
        });


        //close popup pro    vide password
        $('.cd-popup-edit-message').on('click', function (event) {
            if ($(event.target).is('.cd-popup-edit-message-close') || $(event.target).is('.cd-popup-edit-message') || $(event.target).is('#btn_cancel_edit_message')) {
                event.preventDefault();
                $(this).removeClass('is-visible');
            }
        });
        //close popup when clicking the esc keyboard button provide password
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup-edit-message').removeClass('is-visible');
            }
        });
    });


    $(document).ready(function () {
        $('#table_conn').on('click', 'td', function () {
            var $row = $(this).closest("tr");
            var bc = $row.find(".bankcode").text();
            var table = $('#table_iso').DataTable({
                "ajax": {
                    "url": "/bo_mdw/iso?bankcode=" +bc+ "",
                    "type": "GET",
                    "dataSrc": "",
                    "contentType": "application/json"
                },
                "columns": [
                    {data: "field",
                        "className": 'field'
                    },
                    {data: "name_of_field",
                        "className": 'name_of_field'
                    },
                    {data: "format",
                        "className": 'format'
                    },
                    {data: "chars",
                        "className": 'chars'
                    },
                    {data: "length",
                        "className": 'length'
                    }
                ],
                dom: 'Bfrtip',
                buttons: [
                    {
                        extend: 'collection',
                        text: 'Export',
                        buttons:
                                [
                                    {
                                        extend: "copyHtml5",
                                        title: "iso table",
                                        exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                        footer: true
                                    },
                                    {
                                        extend: "csvHtml5",
                                        title: "iso table",
                                        exportOptions: {columns: ':visible:not()'},
                                        footer: true
                                    },
                                    {
                                        extend: "excelHtml5",
                                        title: "iso table",
                                        exportOptions: {columns: ':visible:not()'},
                                        footer: true
                                    },
                                    {
                                        extend: "pdfHtml5",
                                        title: "iso table",
                                        exportOptions: {columns: ':visible:not()'},
                                        footer: true
                                    },
                                    {
                                        extend: "print",
                                        exportOptions: {columns: ':visible:not()'},
                                        footer: true
                                    }
                                ]
                    },
                    {
                        text: 'Add Field',
                        attr: {
                            id: 'add_field'
                        }
                        ,
                        action: function (e, dt, node, config) {
                            openaddfield();
                        }
                    },
                    {
                        text: 'Msg 800 Series',
                        attr: {
                            id: 'msg_800'
                        }
                        ,
                        action: function (e, dt, node, config) {
                            open800();
                        }
                    },
                    {
                        text: 'Msg 400 Series',
                        attr: {
                            id: 'msg_400'
                        }
                        ,
                        action: function (e, dt, node, config) {
                            open400();
                        }
                    },
                    {
                        text: 'Msg 200 Series',
                        attr: {
                            id: 'msg_200'
                        }
                        ,
                        action: function (e, dt, node, config) {
                            open200();
                        }
                    }
                ]
            });
        });
    });
    $.fn.dataTable.ext.errMode = 'none';
    function openaddfield(parameters) {
//        $('.cd-popup-edit-message').removeClass('is-visible');
        $('.cd-popup-add-field').addClass('is-visible-add-field');
    }
    function open800(parameters) {
//        $('.cd-popup-edit-message').removeClass('is-visible');
        $('.cd-popup-800series').addClass('is-visible');
    }
    function open400(parameters) {
//        $('.cd-popup-edit-message').removeClass('is-visible');
        $('.cd-popup-400series').addClass('is-visible');
    }
    function open200(parameters) {
//        $('.cd-popup-edit-message').removeClass('is-visible');
        $('.cd-popup-200series').addClass('is-visible');
    }
</script>
<div class="cd-popup-edit-message" role="alert">
    <div class="cd-popup-edit-message-container" style="color: #29B19C;">
        <label id="label_edit_message">ISO TABLE</label>
        <div class="container">        
            <table class="table" id="table_iso" style="width: 100%">
                <thead>
                    <tr>
                        <th>field</th>
                        <th>name of field</th>
                        <th>format</th>
                        <th>char</th>
                        <th>length</th>
                    </tr>
                </thead>
            </table>
        </div>
        <a href="#0" class="cd-popup-edit-message-close img-replace-edit-category">Close</a>
    </div>
</div>
