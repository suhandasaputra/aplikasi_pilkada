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


            #table_active th {
                font-size: 12px;
                background-color: #ACACAC;
                color: white;
                font-weight: 100;
            }
            #table_active td {
                font-size: 12px;
            }
        </style>
        <script>
            $(document).ready(function () {
                var table = $('#table_active').DataTable({
                    "ajax": {
                        "url": "/dashboard_pilkada/acs",
                        "type": "GET",
                        "dataSrc": "",
                        "contentType": "application/json"
                    },
                    "columns": [
                        {data: "id_votingplace_active",
                            "className": 'id_votingplace_active'
                        },
                        {data: "id_subdistrict",
                            "className": 'id_subdistrict',
                             "visible": false,
                            "searchable": false
                        },
                        {data: "subdistrict_name",
                            "className": 'subdistrict_name'                      
                        },
                        {data: "id_votingplace",
                            "className": 'id_votingplace',
                             "visible": false,
                            "searchable": false
                        },
                        {data: "votingplace_name",
                            "className": 'votingplace_name'
                        },
                        {data: "total_voter",
                            "className": 'total_voter'
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
                                            title: "List Active Voting Place",
                                            exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                            footer: true
                                        },
                                        {
                                            extend: "csvHtml5",
                                            title: "List Active Voting Place",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "excelHtml5",
                                            title: "List Active Voting Place",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "pdfHtml5",
                                            title: "List Active Voting Place",
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
                    ]
                });
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
                                                        List Voting Place Activated
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
                                                                </i>Add Activation
                                                            </label>
                                                        </div>
                                                        <div class="container">        
                                                            <table class="table" id="table_active">
                                                                <thead>
                                                                    <tr>
                                                                        <th>id voting place activated</th>
                                                                        <th>id subdistrict</th>
                                                                        <th>subdistrict name</th>
                                                                        <th>id votingplace</th>
                                                                        <th>votingplace name</th>
                                                                        <th>total voter</th>
                                                                    </tr>
                                                                </thead>
                                                            </table>
                                                        </div>
                                                        <%@include file='pop_up_add_active.jsp'%>
                                                        <%@include file='pop_up_edit_active.jsp'%>
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
