<%-- 
    Document   : dashboard
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <%@include file='defaultextend.jsp'%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>MIOS</title>
        <style>
            .card {
                background-color: white;
            }

            #table_detail th {
                background-color: #ACACAC;
                color: white;
                font-weight: 100;
            }
        </style>
        <script>
            $(document).ready(function () {
                $.ajax({
                    contentType: 'application/json',
                    dataType: "json",
                    url: "rpts",
                    type: 'get',
                    success: function (response) {
//                        let mountains = [{"id_votingplace_active": "3", "id_subdistrict": "36.74.07.1005", "id_votingplace": "3", "total_voter": "407", "voter_attend": "0", "voter_not_present": "0", "valid_vote": "0", "invalid_vote": "0", "img_vote": "-", "total_ballots": "0", "surveyor": "-", "paslon01": "0", "paslon02": "0", "paslon03": "0"},
//                            {"id_votingplace_active": "5", "id_subdistrict": "36.74.07.1005", "id_votingplace": "5", "total_voter": "553", "voter_attend": "0", "voter_not_present": "0", "valid_vote": "0", "invalid_vote": "0", "img_vote": "-", "total_ballots": "0", "surveyor": "-", "paslon01": "0", "paslon02": "0", "paslon03": "0"},
//                            {"id_votingplace_active": "7", "id_subdistrict": "36.74.07.1006", "id_votingplace": "1", "total_voter": "500", "voter_attend": "0", "voter_not_present": "0", "valid_vote": "0", "invalid_vote": "0", "img_vote": "-", "total_ballots": "0", "surveyor": "-", "paslon01": "0", "paslon02": "0", "paslon03": "0"}]
                        let mountains = response;
                        function generateTableHead(table, data) {
                            let thead = table.createTHead();
                            let row = thead.insertRow();
                            for (let key of data) {
                                let th = document.createElement("th");
                                let text = document.createTextNode(key);
                                th.appendChild(text);
                                row.appendChild(th);
                            }
                        }

                        function generateTable(table, data) {
                            for (let element of data) {
                                let row = table.insertRow();
                                for (key in element) {
                                    let cell = row.insertCell();
                                    let text = document.createTextNode(element[key]);
                                    cell.appendChild(text);
                                }
                            }
                        }

                        let table = document.querySelector("#table_detail");
                        let data = Object.keys(mountains[0]);
                        generateTableHead(table, data);
                        generateTable(table, mountains);
                    }
                });











//                let mountains = [{"id_votingplace_active": "3", "id_subdistrict": "36.74.07.1005", "id_votingplace": "3", "total_voter": "407", "voter_attend": "0", "voter_not_present": "0", "valid_vote": "0", "invalid_vote": "0", "img_vote": "-", "total_ballots": "0", "surveyor": "-", "paslon01": "0", "paslon02": "0", "paslon03": "0"},
//                    {"id_votingplace_active": "5", "id_subdistrict": "36.74.07.1005", "id_votingplace": "5", "total_voter": "553", "voter_attend": "0", "voter_not_present": "0", "valid_vote": "0", "invalid_vote": "0", "img_vote": "-", "total_ballots": "0", "surveyor": "-", "paslon01": "0", "paslon02": "0", "paslon03": "0"},
//                    {"id_votingplace_active": "7", "id_subdistrict": "36.74.07.1006", "id_votingplace": "1", "total_voter": "500", "voter_attend": "0", "voter_not_present": "0", "valid_vote": "0", "invalid_vote": "0", "img_vote": "-", "total_ballots": "0", "surveyor": "-", "paslon01": "0", "paslon02": "0", "paslon03": "0"}]









//                var table = $('#table_detail').DataTable({
//                    "ajax": {
//                        "url": "/dashboard_pilkada/rpts",
//                        "type": "GET",
//                        "dataSrc": "",
//                        "contentType": "application/json"
//                    },
//                    "columns": [
//                        {data: "id_votingplace_active"},
//                        {data: "id_subdistrict"},
//                        {data: "id_votingplace"},
//                        {data: "total_voter"},
//                        {data: "voter_attend"},
//                        {data: "voter_not_present"},
//                        {data: "valid_vote"},
//                        {data: "invalid_vote"},
//                        {data: "img_vote",
//                            render: function (data) {
//                                var timestamp = new Date().getTime();
//                                var t = '?t='
//                                return '<img src="' + data + t + timestamp + '" height="80px" width="80px">';
//                            }
//                        },
//                        {data: "total_ballots"},
//                        {data: "surveyor"},
//                        {data: "paslon01"},
//                        {data: "paslon02"},
//                        {data: "paslon03"}
//                    ],
//                    dom: 'Bfrtip',
//                    buttons: [
//                        {
//                            extend: 'collection',
//                            text: 'Export',
//                            buttons:
//                                    [
//                                        {
//                                            extend: "copyHtml5",
//                                            title: "Report",
//                                            exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
//                                            footer: true
//                                        },
//                                        {
//                                            extend: "csvHtml5",
//                                            title: "Report",
//                                            exportOptions: {columns: ':visible:not()'},
//                                            footer: true
//                                        },
//                                        {
//                                            extend: "excelHtml5",
//                                            title: "Report",
//                                            exportOptions: {columns: ':visible:not()'},
//                                            footer: true
//                                        },
//                                        {
//                                            extend: "pdfHtml5",
//                                            title: "Report",
//                                            exportOptions: {columns: ':visible:not()'},
//                                            footer: true
//                                        },
//                                        {
//                                            extend: "print",
//                                            exportOptions: {columns: ':visible:not()'},
//                                            footer: true
//                                        }
//                                    ]
//                        }
//                    ],
//
//                });
//                $.fn.dataTable.ext.errMode = 'none';

            });
        </script> 

    </head>
    <body class="hold-transition sidebar-mini">
        <div class="wrapper">
            <!-- Navbar -->
            <%@include file='header.jsp'%>
            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <%@include file='sidebar_left.jsp'%>
            <!--end sidebar-->
            <div class="content-wrapper">
                <div class="content-header">
                    <section class="content">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <p class="text-left" style="color: #29B19C; font-size: 20px;">
                                                        Report
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="tab-content" style="width: 100%">
                                                    <div id="detail" class="tab-pane fade in active show">
                                                        <div class="container" id="ref_tabel_detail">        
                                                            <table class="table" id="table_detail" style="font-size: 12px">
                                                                <thead>
                                                                    <!--                                                                    <tr>
                                                                                                                                            <th>id voting active</th>
                                                                                                                                            <th>id subditrict</th>
                                                                                                                                            <th>id voting</th>
                                                                                                                                            <th>total voter</th>
                                                                                                                                            <th>voter attend</th>
                                                                                                                                            <th>voter not present</th>
                                                                                                                                            <th>valid vote</th>
                                                                                                                                            <th>invalid vote</th>
                                                                                                                                            <th>img vote</th>
                                                                                                                                            <th>total ballots</th>
                                                                                                                                            <th>surveyor</th>
                                                                                                                                            <th>paslon01</th>
                                                                                                                                            <th>paslon02</th>
                                                                                                                                            <th>paslon03</th>
                                                                                                                                        </tr>-->
                                                                </thead>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>    
                                            </div>
                                            <!-- /.row -->
                                        </div>
                                        <!-- ./card-body -->
                                    </div>
                                    <!-- /.card -->
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                    </section>
                </div>
            </div>
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- Main Footer -->
            <%@include file='footer.jsp'%>
        </div>
    </body>
</html>
