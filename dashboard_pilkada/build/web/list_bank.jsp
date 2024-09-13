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
            .add_category {                
                display: inline-block;

            }

            th {
                background-color: #CCC;
            }
            

            #table_bank th {
                font-size: 12px;
                background-color: #ACACAC;
                color: white;
                font-weight: 100;
            }
            #table_bank td {
                font-size: 12px;
            }
        </style>
        <script>
            $(document).ready(function () {
                var table = $('#table_bank').DataTable({
                    "ajax": {
                        "url": "/dashboard_pilkada/lbs",
                        "type": "GET",
                        "dataSrc": "",
                        "contentType": "application/json"
                    },
                    "columns": [
                        {data: "user_id",
                            "className": 'user_id'
                        },
                        {data: "name",
                            "className": 'name'
                        },
                        {data: "phone_number",
                            "className": 'phone_number'
                        },
//                        {data: "password",
//                            "className": 'password'
//                        },
                        {data: "district",
                            "className": 'district',
                            "visible": false,
                            "searchable": false
                        },
                        {data: "address",
                            "className": 'address',
                            "visible": false,
                            "searchable": false
                        },
                        {data: "subdistrict",
                            "className": 'subdistrict'
                        },
                        {data: "create_date",
                            "className": 'create_date'
                        },
                        {data: "city",
                            "className": 'city'
                        },
                        {data: "id_votingplace",
                            "className": 'id_votingplace'
                        },
                        {data: "id_subdistrict",
                            "className": 'id_subdistrict'
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
                                            title: "List volunteer",
                                            exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                            footer: true
                                        },
                                        {
                                            extend: "csvHtml5",
                                            title: "List volunteer",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "excelHtml5",
                                            title: "List volunteer",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "pdfHtml5",
                                            title: "List volunteer",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "print",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        }
                                    ]
                        }
                    ],
                     "order": [[6, "desc"]]
                });
            });
             $.fn.dataTable.ext.errMode = 'none';
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
            <!-- Content Wrapper. Contains page content -->
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
                                                        List Volunteer
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="tab-content" style="width: 100%">
                                                    <div id="category" class="tab-pane fade in active show">
                                                        <div id="add_category">
                                                            <label id="add_category_text" style="margin: 20px;
                                                                   font-weight: 100;
                                                                   color: #29B19C; 
                                                                   cursor: pointer;">
                                                                <i class="icon fa fa-plus-circle" style="margin-right: 5px">
                                                                </i>Add Volunteer
                                                            </label>
                                                        </div>
                                                        <div class="container">        
                                                            <table class="table" id="table_bank">
                                                                <thead>
                                                                    <tr>
                                                                        <th>user id</th>
                                                                        <th>name</th>
                                                                        <th>phone number</th>
                                                                        <!--<th>password</th>-->
                                                                        <th>district</th>
                                                                        <th>address</th>
                                                                        <th>subdistrict</th>
                                                                        <th>create date</th>
                                                                        <th>city</th>
                                                                        <th>id votingplace</th>
                                                                        <th>id subdistrict</th>
                                                                    </tr>
                                                                </thead>
                                                            </table>
                                                        </div>
                                                        <%@include file='pop_up_add_bank.jsp'%>
                                                        <%--<%@include file='pop_up_edit_bank.jsp'%>--%>
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
            <!-- /.content-wrapper -->
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- Main Footer -->
            <%@include file='footer.jsp'%>
        </div>
    </body>

</html>
