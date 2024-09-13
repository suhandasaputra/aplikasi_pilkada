<%-- 
    Document   : newjsp1
    Created on : 11-Dec-2020, 10:30:54
    Author     : matajari
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Build a table</title>
    </head>
    <body>
        <table>
            <!-- here goes our data! -->
        </table>
    </body>
    <script>
//        let mountains = [
//            {name: "Monte Falco", height: 1658, place: "Parco Foreste Casentinesi"},
//            {name: "Monte Falterona", height: 1654, place: "Parco Foreste Casentinesi"},
//            {name: "Poggio Scali", height: 1520, place: "Parco Foreste Casentinesi"},
//            {name: "Pratomagno", height: 1592, place: "Parco Foreste Casentinesi"},
//            {name: "Monte Amiata", height: 1738, place: "Siena"}
//        ];
let mountains = [{"id_votingplace_active":"3","id_subdistrict":"36.74.07.1005","id_votingplace":"3","total_voter":"407","voter_attend":"0","voter_not_present":"0","valid_vote":"0","invalid_vote":"0","img_vote":"-","total_ballots":"0","surveyor":"-","paslon01":"0","paslon02":"0","paslon03":"0"},
                   {"id_votingplace_active":"5","id_subdistrict":"36.74.07.1005","id_votingplace":"5","total_voter":"553","voter_attend":"0","voter_not_present":"0","valid_vote":"0","invalid_vote":"0","img_vote":"-","total_ballots":"0","surveyor":"-","paslon01":"0","paslon02":"0","paslon03":"0"},
                   {"id_votingplace_active":"7","id_subdistrict":"36.74.07.1006","id_votingplace":"1","total_voter":"500","voter_attend":"0","voter_not_present":"0","valid_vote":"0","invalid_vote":"0","img_vote":"-","total_ballots":"0","surveyor":"-","paslon01":"0","paslon02":"0","paslon03":"0"}]
               console.log('ggggg : '+mountains)
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

        let table = document.querySelector("table");
        let data = Object.keys(mountains[0]);
        generateTableHead(table, data);
        generateTable(table, mountains);
    </script>
</html>

