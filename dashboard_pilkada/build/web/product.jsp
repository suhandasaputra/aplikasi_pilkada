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
            

            #table_product th {
                font-size: 12px;
                background-color: #ACACAC;
                color: white;
                font-weight: 100;
            }
            #table_product td {
                font-size: 12px;
               
            }
        </style>
        <script>
            $(document).ready(function () {
                var table = $('#table_product').DataTable({
                    "ajax": {
                        "url": "/dashboard_pilkada/pds",
                        "type": "GET",
                        "dataSrc": "",
                        "contentType": "application/json"
                    },
                    "columns": [
                        {data: "id_subdistrict",
                            "className": 'id_subdistrict'
                        },
                        {data: "subdistrict_name",
                            "className": 'subdistrict_name'
                        },
                        {data: "district",
                            "className": 'district'
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
                                            title: "List District",
                                            exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                            footer: true
                                        },
                                        {
                                            extend: "csvHtml5",
                                            title: "List District",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "excelHtml5",
                                            title: "List District",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "pdfHtml5",
                                            title: "List District",
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
                                                        District
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
                                                                </i>Add District
                                                            </label>
                                                        </div>
                                                        <div class="container">        
                                                            <table class="table" id="table_product">
                                                                <thead>
                                                                    <tr>
                                                                        <th>id subdistrict</th>
                                                                        <th>subdistrict name</th>
                                                                        <th>district</th>
                                                                    </tr>
                                                                </thead>
                                                            </table>
                                                        </div>
                                                        <%@include file='pop_up_add_product.jsp'%>
                                                        <%@include file='pop_up_edit_product.jsp'%>
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
