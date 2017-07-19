<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Enjoy Zero To Hero</h2>

<div>
<form id="form3" name="export">
    <table>
        <tr>
            <td colspan="2">
                <label for="user">Username:</label>
                <input type="text" id="user" name="user"/>
            </td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <tr>
            <td>
                <input type="button" name="export" value="Export file" onclick="exportFile()">
            </td>
        </tr>
    </table>
</form>

</div>

<script type="text/javascript">
    function exportFile() {
        var username = document.getElementById('user').value;

        if (username != null && username != "") {
            // 1: Use window.open to call ExportFileServlet using its mapping and the following request parameters: template, fileType and username
            // TIP: template = UsernameReport, fileType = pdf
            // TIP: Remember that the application context it's named servlet-app
            var url = "exportfile?template=UsernameReport&Type=pdf&username=" + username;
            var win = window.open(url);
        } else {
            //  1: Use window.open to call ExportFileServlet using its mapping and the following request parameters: template and fileType
            // TIP: Remember that the application context it's named servlet-app
            var url = "exportfile?template=UsernameReport&Type=pdf";
            var win = window.open(url);

        }

    }
</script>

</body>
</html>
