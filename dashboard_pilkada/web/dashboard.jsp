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

        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <script src="https://cdn.jsdelivr.net/npm/vue-apexcharts"></script>
        <title>MIOS</title>
    </head>
    <body class="hold-transition sidebar-mini" onload="graph()">
        <div class="wrapper">
            <!-- Navbar -->
            <%@include file='header.jsp'%>
            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <%@include file='sidebar_left.jsp'%>
            <!--end sidebar-->
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <div class="row">
                    <div id="statistic1" style="margin-left: 25%; margin-top: 10%">
                        <div id="title_bar1" style="position: absolute; margin-left: 20px; margin-top: 10px;">voting pilkada 2020</div>
                        <br>
                        <div id="app1" style="display: contents">
                            <div id="chart1">
                                <apexchart type="pie" width="380" :options="chartOptions1" :series="series"></apexchart>
                            </div>
                        </div>
                    </div>
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
    <script>
        function graph() {
            $.ajax({
                url: "GraphServlet",
                type: 'post',
                dataType: "json",
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (response) {
                    if (response.resp_code == 0000) {

                        var data_prod_name = response.paslon;
                        var data_total = response.perolehan;

                        new Vue({
                            el: '#app1',
                            components: {
                                apexchart: VueApexCharts,
                            },
                            data: {

                                series: data_total,
                                chartOptions1: {
                                    chart1: {
                                        width: 380,
                                        type: 'pie',
                                    },
                                    labels: data_prod_name,
                                    responsive: [{
                                            breakpoint: 480,
                                            options: {
                                                chart: {
                                                    width: 200
                                                },
                                                legend: {
                                                    position: 'bottom'
                                                }
                                            }
                                        }]
                                }
                            }
                        });
                    }
                }
            }
            );
















        }
    </script>
</html>
